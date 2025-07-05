package com.task.yourcsguide.service.file.impl;


import com.task.yourcsguide.entity.BlogPost;
import com.task.yourcsguide.entity.document.Document;
import com.task.yourcsguide.modal.S3ObjectMetadata;
import com.task.yourcsguide.repository.document.DocumentRepository;
import com.task.yourcsguide.service.file.FileService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {
    @Value("${aws.s3.tmp.bucket.name}")
    String tmpBucketName;

    @Value("${aws.s3.bucket-name}")
    String bucketName;

    private final S3Client s3Client;
    private final DocumentRepository documentRepository;

    @Transactional
    @Override
    public String uploadTmp(MultipartFile file) throws IOException {

        String objectName = String.valueOf(UUID.randomUUID());

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(tmpBucketName)
                .key(objectName)
                .metadata(Map.of(
                        "original-filename", file.getOriginalFilename()
                ))
                .contentType(file.getContentType())
                .build();

        RequestBody requestBody = RequestBody.fromBytes(file.getBytes());
        s3Client.putObject(request, requestBody);

        return objectName;
    }

    @Transactional
    @Override
    public Document store(String objectName, BlogPost blogPost) throws BadRequestException {
        S3ObjectMetadata objectMetadata = getObjectMetadata(tmpBucketName, objectName);

        Document document = Document.builder()
                .objectName(objectName)
                .bucketName(bucketName)
                .originalFileName(objectMetadata.originalFilename())
                .fileSize(objectMetadata.size())
                .contentType(objectMetadata.contentType())
                .blogPost(blogPost)
                .build();

        document = documentRepository.saveAndFlush(document);

        // Step 1: Copy to the main bucket
        CopyObjectRequest copyRequest = CopyObjectRequest.builder()
                .copySource(tmpBucketName + "/" + bucketName)  //todo
                .destinationBucket(bucketName)
                .destinationKey(objectName)
                .build();
        s3Client.copyObject(copyRequest);

        // Step 2: Delete from the tmp bucket
        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                .bucket(tmpBucketName)
                .key(objectName)
                .build();
        s3Client.deleteObject(deleteRequest);

        return document;
    }

    @Override
    public Resource download( String objectName)  {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(objectName)
                .build();

        ResponseInputStream<GetObjectResponse> s3Object = s3Client.getObject(request);
        return new InputStreamResource(s3Object);
    }

    public S3ObjectMetadata getObjectMetadata(String bucketName, String key) {
        HeadObjectRequest request = HeadObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        HeadObjectResponse response = s3Client.headObject(request);

        String originalFilename = response.metadata().get("original-filename"); // custom metadata
        long size = response.contentLength();  // size in bytes
        String contentType = response.contentType();

        return new S3ObjectMetadata(
                originalFilename != null ? originalFilename : key.substring(key.lastIndexOf("/") + 1),
                size,
                contentType
        );
    }
}

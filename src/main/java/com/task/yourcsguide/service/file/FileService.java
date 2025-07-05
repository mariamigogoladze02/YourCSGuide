package com.task.yourcsguide.service.file;

import com.task.yourcsguide.entity.BlogPost;
import com.task.yourcsguide.entity.document.Document;
import org.apache.coyote.BadRequestException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    String uploadTmp(MultipartFile file) throws IOException;

    Resource download(String objectName);

    Document store(String objectName, BlogPost blogPost) throws BadRequestException;

}

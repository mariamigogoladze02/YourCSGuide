package com.task.yourcsguide.entity.document;

import com.task.yourcsguide.entity.BlogPost;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "object_name")
    private String objectName;

    @Column(name = "bucket_name")
    private String bucketName;

    @Column(name = "original_file_name")
    private String originalFileName;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "content_type")
    private String contentType;

    @ManyToOne
    @JoinColumn(name = "blog_post_id")
    private BlogPost blogPost;
}

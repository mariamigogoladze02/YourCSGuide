package com.task.yourcsguide.repository;

import com.task.yourcsguide.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
//    List<BlogPost> findByUsername(String username);
}

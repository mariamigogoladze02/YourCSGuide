package com.task.yourcsguide.service;//package com.task.yourcsguide.service;
//
//
//import com.task.yourcsguide.entity.BlogPost;
//import com.task.yourcsguide.entity.Image;
//import com.task.yourcsguide.entity.User;
//import com.task.yourcsguide.repository.BlogPostRepository;
//import com.task.yourcsguide.repository.UserRepository;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//public class BlogPostServiceImpl implements BlogPostService {
//    private final BlogPostRepository blogPostRepository;
//    private final UserRepository userRepository;
//
//    public BlogPostServiceImpl(BlogPostRepository blogPostRepository,
//                               UserRepository userRepository) {
//        this.blogPostRepository = blogPostRepository;
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public BlogPost createBlogPost(BlogPost blogPost) {
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//        String username = userDetails.getUsername();
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
//
//        blogPost.setAuthor(user);
//        blogPost.setCreatedAt(LocalDateTime.now());
//
//        if (blogPost.getImages() != null) {
//            for (Image image : blogPost.getImages()) {
//                image.setBlogPost(blogPost);
//            }
//        }
//
//        return blogPostRepository.save(blogPost);
//    }
//}


import com.task.yourcsguide.repository.BlogPostRepository;
import com.task.yourcsguide.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;


@RequiredArgsConstructor
@Service
public class BlogPostServiceImpl implements BlogPostService {

    private final BlogPostRepository blogPostRepository;
    private final UserRepository userRepository;
    private final S3Client s3Client;

}

package com.task.yourcsguide.repository;

import com.task.yourcsguide.entity.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Page<Profile> findByNameContainingIgnoreCase(String name, Pageable pageable);
}

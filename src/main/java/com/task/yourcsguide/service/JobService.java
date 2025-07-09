package com.task.yourcsguide.service;

import com.task.yourcsguide.entity.dto.JobCreateUpdateDTO;
import com.task.yourcsguide.entity.dto.JobDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobService {
    Page<JobDTO> search(String jobTitle, Pageable pageable);

    JobDTO create(JobCreateUpdateDTO dto);

    JobDTO update(JobCreateUpdateDTO dto);

    JobDTO delete(Long id);

    JobDTO getById(Long id);
}

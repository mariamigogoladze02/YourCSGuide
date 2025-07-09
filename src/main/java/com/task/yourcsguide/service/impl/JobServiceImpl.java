package com.task.yourcsguide.service.impl;

import com.task.yourcsguide.entity.Job;
import com.task.yourcsguide.entity.dto.JobCreateUpdateDTO;
import com.task.yourcsguide.entity.dto.JobDTO;
import com.task.yourcsguide.repository.JobRepository;
import com.task.yourcsguide.service.JobService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Page<JobDTO> search(String jobTitle, Pageable pageable) {
        Page<Job> jobs;

        if (jobTitle == null || jobTitle.isBlank()) {
            jobs = jobRepository.findAll(pageable);
        } else {
            jobs = jobRepository.findByTitleContainingIgnoreCase(jobTitle, pageable);
        }

        return jobs.map(this::toDTO);
    }

    @Override
    public JobDTO getById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
        return toDTO(job);
    }

    @Override
    public JobDTO create(JobCreateUpdateDTO dto) {
        Job job = new Job();

        job.setTitle(dto.getTitle());
        job.setCompanyName(dto.getCompanyName());
        job.setCompanyEmail(dto.getCompanyEmail());
        job.setDescription(dto.getDescription());
        job.setSalaryRange(dto.getSalaryRange());

        return toDTO(jobRepository.save(job));
    }

    @Override
    public JobDTO update(JobCreateUpdateDTO dto) {
        Job job = jobRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Job not found"));

        job.setTitle(dto.getTitle());
        job.setCompanyName(dto.getCompanyName());
        job.setCompanyEmail(dto.getCompanyEmail());
        job.setDescription(dto.getDescription());
        job.setSalaryRange(dto.getSalaryRange());

        return toDTO(jobRepository.save(job));
    }

    @Override
    public JobDTO delete(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        jobRepository.delete(job);
        return toDTO(job);
    }

    private JobDTO toDTO(Job job) {
        JobDTO dto = new JobDTO();
        dto.setId(job.getId());
        dto.setTitle(job.getTitle());
        dto.setCompanyName(job.getCompanyName());
        dto.setCompanyEmail(job.getCompanyEmail());
        dto.setDescription(job.getDescription());
        dto.setSalaryRange(job.getSalaryRange());
        return dto;
    }
}

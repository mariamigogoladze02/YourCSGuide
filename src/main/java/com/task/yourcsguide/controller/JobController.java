package com.task.yourcsguide.controller;

import com.task.yourcsguide.entity.dto.JobCreateUpdateDTO;
import com.task.yourcsguide.entity.dto.JobDTO;
import com.task.yourcsguide.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/jobs")
public class JobController {
    private JobService jobService;

    @GetMapping
    public ResponseEntity<Page<JobDTO>> searchJobs(@RequestParam(value = "title", required = false) String jobTitle,
                                                   Pageable pageable) {
        return ResponseEntity.ok(jobService.search(jobTitle, pageable));
    }

    @PostMapping
    public ResponseEntity<JobDTO> createJob(@Valid @RequestBody JobCreateUpdateDTO dto) {
        return ResponseEntity.ok(jobService.create(dto));
    }

    @PutMapping
    public ResponseEntity<JobDTO> updateJob(@Valid @RequestBody JobCreateUpdateDTO dto) {
        return ResponseEntity.ok(jobService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JobDTO> deleteJob(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.delete(id));
    }

    @Autowired
    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }
}

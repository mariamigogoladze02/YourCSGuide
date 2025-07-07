package com.task.yourcsguide.entity.dto;


public class JobDTO {
    private Long id;
    private String title;
    private String description;
    private String salaryRange;
    private String companyName;
    private String companyEmail;

    public JobDTO() {
    }

    public JobDTO(Long id, String title, String description, String salaryRange, String companyName, String companyEmail) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.salaryRange = salaryRange;
        this.companyName = companyName;
        this.companyEmail = companyEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }
}

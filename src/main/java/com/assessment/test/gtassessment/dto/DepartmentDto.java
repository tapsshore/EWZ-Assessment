package com.assessment.test.gtassessment.dto;

import javax.validation.constraints.NotNull;

public class DepartmentDto {
    @NotNull
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DepartmentDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

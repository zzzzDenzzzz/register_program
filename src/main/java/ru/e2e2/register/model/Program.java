package ru.e2e2.register.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Setter;

@Entity
@Table(name = "programs")
public class Program extends BaseModel {

    @NotBlank
    @Column(name = "category", length = 40)
    private String category;

    @NotBlank
    @Column(name = "subcategory", length = 40)
    private String subcategory;

    @NotBlank
    @Column(name = "details", length = 40)
    private String details;

    @PositiveOrZero
    @Column(name = "version")
    private Integer version;

    public Program() {
    }

    public Program(String category, String subcategory, String details, Integer version) {
        this.category = category;
        this.subcategory = subcategory;
        this.details = details;
        this.version = version;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public String getDetails() {
        return details;
    }

    public Integer getVersion() {
        return version;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}

package ru.e2e2.register.filter;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import ru.e2e2.register.model.Program;

public record ProgramFilter(String nameStarts, String categoryStarts, String subcategoryStarts, String detailsStarts) {
    public Specification<Program> toSpecification() {
        return Specification.where(nameStartsSpec())
                .and(categoryStartsSpec())
                .and(subcategoryStartsSpec())
                .and(detailsStartsSpec());
    }

    private Specification<Program> nameStartsSpec() {
        return ((root, query, cb) -> StringUtils.hasText(nameStarts)
                ? cb.like(cb.lower(root.get("name")), nameStarts.toLowerCase() + "%")
                : null);
    }

    private Specification<Program> categoryStartsSpec() {
        return ((root, query, cb) -> StringUtils.hasText(categoryStarts)
                ? cb.like(cb.lower(root.get("category")), categoryStarts.toLowerCase() + "%")
                : null);
    }

    private Specification<Program> subcategoryStartsSpec() {
        return ((root, query, cb) -> StringUtils.hasText(subcategoryStarts)
                ? cb.like(cb.lower(root.get("subcategory")), subcategoryStarts.toLowerCase() + "%")
                : null);
    }

    private Specification<Program> detailsStartsSpec() {
        return ((root, query, cb) -> StringUtils.hasText(detailsStarts)
                ? cb.like(cb.lower(root.get("details")), detailsStarts.toLowerCase() + "%")
                : null);
    }
}
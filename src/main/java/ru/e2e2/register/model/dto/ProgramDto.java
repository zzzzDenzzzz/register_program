package ru.e2e2.register.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * DTO for {@link ru.e2e2.register.model.Program}
 */
public record ProgramDto(@NotBlank String name, Long id, @NotBlank String category, @NotBlank String subcategory,
                         @NotBlank String details, @PositiveOrZero Integer version) {
}
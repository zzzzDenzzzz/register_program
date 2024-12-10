package ru.e2e2.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.e2e2.register.model.Program;

public interface ProgramRepository extends JpaRepository<Program, Long>, JpaSpecificationExecutor<Program> {
}
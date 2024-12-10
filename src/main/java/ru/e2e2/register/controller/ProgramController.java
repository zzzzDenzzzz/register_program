package ru.e2e2.register.controller;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;
import ru.e2e2.register.filter.ProgramFilter;
import ru.e2e2.register.model.Program;
import ru.e2e2.register.service.ProgramService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin/programs")
public class ProgramController {

    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping
    public PagedModel<Program> getAll(@ModelAttribute ProgramFilter filter, Pageable pageable) {
        Page<Program> programs = programService.getAll(filter, pageable);
        return new PagedModel<>(programs);
    }

    @GetMapping("/{id}")
    public Program getOne(@PathVariable Long id) {
        return programService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<Program> getMany(@RequestParam List<Long> ids) {
        return programService.getMany(ids);
    }

    @PostMapping
    public Program create(@RequestBody @Valid Program program) {
        return programService.create(program);
    }

    @PatchMapping("/{id}")
    public Program patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        return programService.patch(id, patchNode);
    }

    @PatchMapping
    public List<Long> patchMany(@RequestParam @Valid List<Long> ids, @RequestBody JsonNode patchNode) throws IOException {
        return programService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    public Program delete(@PathVariable Long id) {
        return programService.delete(id);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<Long> ids) {
        programService.deleteMany(ids);
    }
}

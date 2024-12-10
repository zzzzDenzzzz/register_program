package ru.e2e2.register.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.e2e2.register.filter.ProgramFilter;
import ru.e2e2.register.model.Program;
import ru.e2e2.register.repository.ProgramRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProgramService {

    private final ProgramRepository programRepository;

    private final ObjectMapper objectMapper;

    public ProgramService(ProgramRepository programRepository, ObjectMapper objectMapper) {
        this.programRepository = programRepository;
        this.objectMapper = objectMapper;
    }

    public Page<Program> getAll(ProgramFilter filter, Pageable pageable) {
        Specification<Program> spec = filter.toSpecification();
        return programRepository.findAll(spec, pageable);
    }

    public Program getOne(Long id) {
        Optional<Program> programOptional = programRepository.findById(id);
        return programOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));
    }

    public List<Program> getMany(List<Long> ids) {
        return programRepository.findAllById(ids);
    }

    public Program create(Program program) {
        return programRepository.save(program);
    }

    public Program patch(Long id, JsonNode patchNode) throws IOException {
        Program program = programRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        objectMapper.readerForUpdating(program).readValue(patchNode);

        return programRepository.save(program);
    }

    public List<Long> patchMany(List<Long> ids, JsonNode patchNode) throws IOException {
        Collection<Program> programs = programRepository.findAllById(ids);

        for (Program program : programs) {
            objectMapper.readerForUpdating(program).readValue(patchNode);
        }

        List<Program> resultPrograms = programRepository.saveAll(programs);
        return resultPrograms.stream()
                .map(Program::getId)
                .toList();
    }

    public Program delete(Long id) {
        Program program = programRepository.findById(id).orElse(null);
        if (program != null) {
            programRepository.delete(program);
        }
        return program;
    }

    public void deleteMany(List<Long> ids) {
        programRepository.deleteAllById(ids);
    }
}

package ru.e2e2.register.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.e2e2.register.model.Program;
import ru.e2e2.register.model.dto.ProgramDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProgramMapper {
    Program toEntity(ProgramDto programDto);

    ProgramDto toProgramDto(Program program);
}
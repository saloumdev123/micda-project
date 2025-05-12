package sen.saloum.Ramli.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sen.saloum.Ramli.dto.EnumValueDto;
import sen.saloum.Ramli.enums.NomFigureBase;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/nom-figures")
public class NomFigureBaseController {

    @GetMapping
    public List<EnumValueDto> getAllNomFigures() {
        return Arrays.stream(NomFigureBase.values())
                .map(value -> new EnumValueDto(value.name(), value.getLabel()))
                .collect(Collectors.toList());
    }
}

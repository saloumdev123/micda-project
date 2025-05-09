package sen.saloum.Ramli.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sen.saloum.Ramli.dto.EnumValueDto;
import sen.saloum.Ramli.enums.TypeFigure;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/type-figures")
public class TypeFigureController {

    @GetMapping
    public List<EnumValueDto> getAllTypeFigures() {
        return Arrays.stream(TypeFigure.values())
                .map(value -> {
                    EnumValueDto dto = new EnumValueDto();
                    dto.setName(value.name());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}

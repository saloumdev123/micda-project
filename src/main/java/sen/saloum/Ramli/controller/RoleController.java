package sen.saloum.Ramli.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sen.saloum.Ramli.dto.EnumValueDto;
import sen.saloum.Ramli.enums.Role;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @GetMapping
    public List<EnumValueDto> getAllRoles() {
        return Arrays.stream(Role.values())
                .map(role -> {
                    EnumValueDto dto = new EnumValueDto();
                    dto.setName(role.name());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}

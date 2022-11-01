package net.gitpet.petserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.gitpet.petserver.domain.Pet;

@Data
@AllArgsConstructor
public class BasicGotchaDto {

    private Long gid;
    private Pet pet;
    private int possibilityWeight;
}

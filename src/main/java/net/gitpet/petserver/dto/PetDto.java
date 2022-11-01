package net.gitpet.petserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PetDto {

    private Long pid;
    private String imgSrc;
}

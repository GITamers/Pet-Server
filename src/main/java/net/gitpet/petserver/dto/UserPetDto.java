package net.gitpet.petserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.gitpet.petserver.domain.Pet;
import net.gitpet.petserver.domain.User;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserPetDto {

    private Long upid;
    private User owner;
    private Long level;
    private LocalDateTime adoptedAt;
    private Pet type;
    private boolean mainPet;
    private Long commitCnt;
}

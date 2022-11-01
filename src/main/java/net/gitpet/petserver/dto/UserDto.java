package net.gitpet.petserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserDto {

    private Long uid;
    private Long point;
    private LocalDateTime createdAt;
    private LocalDateTime commitUpdatedAt;
    private Long commitCnt;
}

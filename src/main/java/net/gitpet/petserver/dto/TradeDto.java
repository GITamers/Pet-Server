package net.gitpet.petserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.gitpet.petserver.domain.User;
import net.gitpet.petserver.domain.UserPet;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TradeDto {

    private Long tid;
    private User seller;
    private int price;
    private UserPet pet;
    private String title;
    private LocalDateTime createdAt;
}

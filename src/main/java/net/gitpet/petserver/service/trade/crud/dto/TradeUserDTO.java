package net.gitpet.petserver.service.trade.crud.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class TradeUserDTO {

    private final long USER_ID;
    private final long POINT;

}

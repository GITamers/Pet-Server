package net.gitpet.petserver.controller.trade.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class ResponseTradeUserDTO {

    private final long USER_ID;
    private final long POINT;

}

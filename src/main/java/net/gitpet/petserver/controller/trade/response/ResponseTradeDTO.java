package net.gitpet.petserver.controller.trade.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public final class ResponseTradeDTO {

    private final long TRADE_ID;
    private final ResponseTradeUserDTO SELLER;
    private final ResponseTradePetDTO PET;
    private final int PRICE;
    private final String TITLE;
    private final LocalDateTime CREATED_AT;

}

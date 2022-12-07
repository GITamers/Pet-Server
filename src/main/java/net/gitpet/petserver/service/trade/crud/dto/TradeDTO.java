package net.gitpet.petserver.service.trade.crud.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public final class TradeDTO {

    private final long TRADE_ID;
    private final TradeUserDTO SELLER;
    private final TradePetDTO PET;
    private final int PRICE;
    private final String TITLE;
    private final LocalDateTime CREATED_AT;

}

package net.gitpet.petserver.service.trade.crud.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public final class TradePetDTO {

    private final long PET_ID;
    private final long PET_LEVEL;
    private final long PET_TYPE_ID;
    private final boolean MAIN_PET;
    private final TradeUserDTO OWNER;

}

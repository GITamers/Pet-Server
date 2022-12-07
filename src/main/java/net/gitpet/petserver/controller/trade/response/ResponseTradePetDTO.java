package net.gitpet.petserver.controller.trade.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public final class ResponseTradePetDTO {

    private final long PET_ID;
    private final long PET_LEVEL;
    private final long PET_TYPE_ID;
    private final boolean MAIN_PET;
    private final ResponseTradeUserDTO OWNER;

}

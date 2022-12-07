package net.gitpet.petserver.service.trade.transaction.dto;

import lombok.Getter;

@Getter
public final class TradeResultDTO {

    private final TradeDTO TRADE_INFO;
    private final TradeUserDTO SELLER;
    private final TradeUserDTO BUYER;
    private final TradeUserPetDTO PET;

    public TradeResultDTO(TradeDTO tradeInfo, TradeUserDTO seller, TradeUserDTO buyer, TradeUserPetDTO pet){
        TRADE_INFO = tradeInfo;
        SELLER = seller;
        BUYER = buyer;
        PET = pet;
    }

}

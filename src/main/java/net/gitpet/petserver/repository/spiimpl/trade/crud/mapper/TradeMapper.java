package net.gitpet.petserver.repository.spiimpl.trade.crud.mapper;

import net.gitpet.petserver.domain.Trade;
import net.gitpet.petserver.domain.User;
import net.gitpet.petserver.service.trade.crud.dto.TradeDTO;
import net.gitpet.petserver.service.trade.crud.dto.TradePetDTO;
import net.gitpet.petserver.service.trade.crud.dto.TradeUserDTO;

import java.util.ArrayList;
import java.util.List;

public final class TradeMapper {

    public static List<TradeDTO> mapTradeToTradeDTO(java.util.List<Trade> trades){
        List<net.gitpet.petserver.service.trade.crud.dto.TradeDTO> tradeDTOs = new ArrayList<>();
        for(Trade trade : trades){
            tradeDTOs.add(
                    net.gitpet.petserver.service.trade.crud.dto.TradeDTO.builder()
                            .TRADE_ID(trade.getTid())
                            .TITLE(trade.getTitle())
                            .CREATED_AT(trade.getCreatedAt())
                            .PET(mapTradeToTradePetDTO(trade))
                            .PRICE(trade.getPrice())
                            .SELLER(mapUserToTradeUserDTO(trade.getSeller()))
                            .build()
            );
        }
        return tradeDTOs;
    }

    private static TradePetDTO mapTradeToTradePetDTO(Trade trade){
        return TradePetDTO.builder()
                .PET_ID(trade.getPet().getUpid())
                .PET_LEVEL(trade.getPet().getLevel())
                .PET_TYPE_ID(trade.getPet().getType().getPid())
                .MAIN_PET(trade.getPet().isMainPet())
                .OWNER(mapUserToTradeUserDTO(trade.getPet().getOwner()))
                .build();
    }

    private static TradeUserDTO mapUserToTradeUserDTO(User user){
        return TradeUserDTO.builder()
                .USER_ID(user.getUid())
                .POINT(user.getPoint())
                .build();
    }

}

package net.gitpet.petserver.controller.trade.mapper;


import net.gitpet.petserver.controller.trade.response.ResponseTradeDTO;
import net.gitpet.petserver.controller.trade.response.ResponseTradePetDTO;
import net.gitpet.petserver.controller.trade.response.ResponseTradeUserDTO;
import net.gitpet.petserver.service.trade.crud.dto.TradeDTO;
import net.gitpet.petserver.service.trade.crud.dto.TradePetDTO;
import net.gitpet.petserver.service.trade.crud.dto.TradeUserDTO;

public final class ResponseTradeDTOMapper {

    public static ResponseTradeDTO mapToResponseTradeDTO(TradeDTO tradeDTO){
        return ResponseTradeDTO.builder()
                .TRADE_ID(tradeDTO.getTRADE_ID())
                .CREATED_AT(tradeDTO.getCREATED_AT())
                .PET(mapToResponseTradePetDTO(tradeDTO.getPET()))
                .PRICE(tradeDTO.getPRICE())
                .SELLER(mapToResponseTradeUserDTO(tradeDTO.getSELLER()))
                .TITLE(tradeDTO.getTITLE())
                .build();
    }

    private static ResponseTradePetDTO mapToResponseTradePetDTO(TradePetDTO tradePetDTO){
        return ResponseTradePetDTO.builder()
                .MAIN_PET(tradePetDTO.isMAIN_PET())
                .PET_LEVEL(tradePetDTO.getPET_LEVEL())
                .PET_ID(tradePetDTO.getPET_ID())
                .PET_TYPE_ID(tradePetDTO.getPET_TYPE_ID())
                .OWNER(mapToResponseTradeUserDTO(tradePetDTO.getOWNER()))
                .build();
    }

    private static ResponseTradeUserDTO mapToResponseTradeUserDTO(TradeUserDTO tradeUserDTO){
        return ResponseTradeUserDTO.builder()
                .USER_ID(tradeUserDTO.getUSER_ID())
                .POINT(tradeUserDTO.getPOINT())
                .build();
    }

}

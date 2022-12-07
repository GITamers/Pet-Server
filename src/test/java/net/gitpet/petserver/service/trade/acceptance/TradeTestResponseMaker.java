package net.gitpet.petserver.service.trade.acceptance;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.gitpet.petserver.controller.trade.response.ResponseTradeDTO;
import net.gitpet.petserver.controller.trade.response.ResponseTradePetDTO;
import net.gitpet.petserver.controller.trade.response.ResponseTradeUserDTO;
import net.gitpet.petserver.testannotation.TestBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@TestBean
public class TradeTestResponseMaker {

    private final ObjectMapper OBJECT_MAPPER;

    public String getExpectedResponse(TradeTestInitializer.Response... responses) {
        List<ResponseTradeDTO> results = new ArrayList<>();
        for(TradeTestInitializer.Response response : responses)
            results.add(makeExpectedResponse(response));
        try {
            return OBJECT_MAPPER.writeValueAsString(results);
        }catch(Exception e){
            throw new IllegalStateException("Can not make expected response of testInfo");
        }
    }

    private ResponseTradeDTO makeExpectedResponse(TradeTestInitializer.Response testInfo){
        return ResponseTradeDTO.builder()
                .SELLER(ResponseTradeUserDTO.builder()
                        .USER_ID(testInfo.getSellerId())
                        .POINT(testInfo.getSellerPoint())
                        .build())
                .PET(ResponseTradePetDTO.builder()
                        .MAIN_PET(testInfo.getIsMainPet())
                        .PET_ID(testInfo.getUserPetId())
                        .OWNER(ResponseTradeUserDTO.builder()
                                .USER_ID(testInfo.getSellerId())
                                .POINT(testInfo.getSellerPoint())
                                .build())
                        .PET_LEVEL(testInfo.getPetLevel())
                        .PET_TYPE_ID(testInfo.getPetTypeId())
                        .build())
                .PRICE(testInfo.getTradePrice())
                .TITLE(testInfo.getTradeTitle())
                .TRADE_ID(testInfo.getTradeId())
                .CREATED_AT(testInfo.getTradeCreatedAt())
                .build();
    }

    @Autowired
    TradeTestResponseMaker(ObjectMapper objectMapper){
        OBJECT_MAPPER = objectMapper;
    }

}

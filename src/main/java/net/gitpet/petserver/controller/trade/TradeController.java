package net.gitpet.petserver.controller.trade;

import net.gitpet.petserver.controller.trade.mapper.ResponseTradeDTOMapper;
import net.gitpet.petserver.controller.trade.request.TradeRequest;
import net.gitpet.petserver.controller.trade.response.ResponseTradeDTO;
import net.gitpet.petserver.service.trade.crud.TradeCRUD;
import net.gitpet.petserver.service.trade.crud.dto.TradeDTO;
import net.gitpet.petserver.service.trade.crud.option.Keyword;
import net.gitpet.petserver.service.trade.crud.option.Order;
import net.gitpet.petserver.service.trade.transaction.Trade;
import net.gitpet.petserver.service.trade.transaction.dto.TradeResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public final class TradeController {

    private final Trade<TradeResultDTO> FACADE_TRADE;
    private final TradeCRUD DEFAULT_TRADE_CRUD;

    @GetMapping(path = "/trade")
    public List<ResponseTradeDTO> readTradeWithFilter(@RequestParam(value = "keyword", required = false) Keyword keyword,
                                                      @RequestParam(value = "order", defaultValue = "ASC") Order order){
        List<TradeDTO> tradeDTOs = new ArrayList<>();
        if(keyword == null) tradeDTOs = DEFAULT_TRADE_CRUD.readAll();
        else tradeDTOs = DEFAULT_TRADE_CRUD.readWithFilter(TradeCRUD.Filter.getBuilder().addKeyword(keyword).setOrder(order).build());
        return getResponseTradeDTOs(tradeDTOs);
    }

    @GetMapping(path = "/trade/{user-id}")
    public List<ResponseTradeDTO> readTradeWithSellerId(@PathVariable("user-id") long userId){
        List<TradeDTO> tradeDTOs = DEFAULT_TRADE_CRUD.readBySellerId(userId);
        return getResponseTradeDTOs(tradeDTOs);
    }

    private List<ResponseTradeDTO> getResponseTradeDTOs(List<TradeDTO> tradeDTOs){
        List<ResponseTradeDTO> responseTradeDTOs = new ArrayList<>();
        for(TradeDTO tradeDTO : tradeDTOs)
            responseTradeDTOs.add(ResponseTradeDTOMapper.mapToResponseTradeDTO(tradeDTO));
        return responseTradeDTOs;
    }

    @PutMapping(path = "/trade")
    public TradeResultDTO transactionTrade(@RequestParam("trade-id") long tradeId,
                                @RequestParam("buyer-id") long buyerId){
        return FACADE_TRADE.transaction(tradeId, buyerId);
    }

    @PutMapping(path = "/trade/{trade-id}")
    public void updateTrade(@PathVariable("trade-id") long tradeId,
                      @RequestBody @Valid TradeRequest tradeRequest){
        DEFAULT_TRADE_CRUD.updateTrade(tradeId, tradeRequest.getTitle(), tradeRequest.getPrice());
    }

    @DeleteMapping(path = "/trade/{trade-id}")
    public void deleteTrade(@PathVariable("trade-id") long tradeId){
        DEFAULT_TRADE_CRUD.deleteTrade(tradeId);
    }

    @PostMapping(path = "/trade")
    public void saveTrade(@RequestBody @Valid TradeRequest tradeRequest){
        DEFAULT_TRADE_CRUD.createTrade(tradeRequest.getSellerId(),
                                        tradeRequest.getPetId(),
                                        tradeRequest.getTitle(),
                                        tradeRequest.getPrice());
    }

    @Autowired
    private TradeController(@Qualifier("facadeTrade") Trade<TradeResultDTO> facadeTrade,
                            @Qualifier("defaultTradeCRUD") TradeCRUD defaultTradeCRUD){
        FACADE_TRADE = facadeTrade;
        DEFAULT_TRADE_CRUD = defaultTradeCRUD;
    }

}

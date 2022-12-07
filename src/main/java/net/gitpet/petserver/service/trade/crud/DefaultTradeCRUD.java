package net.gitpet.petserver.service.trade.crud;

import net.gitpet.petserver.service.trade.crud.dto.TradeDTO;
import net.gitpet.petserver.service.trade.crud.extensible.TradeFilter;
import net.gitpet.petserver.service.trade.crud.extensible.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class DefaultTradeCRUD implements TradeCRUD{

    private final TradeRepository TRADE_REPOSITORY;

    @Autowired
    private DefaultTradeCRUD(TradeRepository tradeRepository){
        TRADE_REPOSITORY = tradeRepository;
    }

    @Override
    public List<TradeDTO> readBySellerId(long sellerId) {
        return TRADE_REPOSITORY.findBySellerId(sellerId);
    }

    @Override
    public List<TradeDTO> readWithFilter(Filter filter) {
        List<TradeDTO> tradeDTOs = readAll();
        List<TradeFilter> filters = filter.getFilters();
        for(TradeFilter tradeFilter : filters)
            tradeDTOs = tradeFilter.filter(tradeDTOs);
        return tradeDTOs;
    }

    @Override
    public void createTrade(long sellerId, long petId, String title, int price) {
        TRADE_REPOSITORY.saveTrade(sellerId, petId, title, price);
    }

    @Override
    public void deleteTrade(long tradeId) {
        TRADE_REPOSITORY.deleteTrade(tradeId);
    }

    @Override
    public void updateTrade(long tradeId, String title, int price) {
        TRADE_REPOSITORY.changeTrade(tradeId, title, price);
    }

    @Override
    public List<TradeDTO> readAll() {
        return TRADE_REPOSITORY.findAll();
    }

}

package net.gitpet.petserver.service.trade.spiimpl.filter.level;

import net.gitpet.petserver.service.trade.crud.dto.TradeDTO;
import net.gitpet.petserver.service.trade.crud.spi.TradeFilter;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractPetLevelFilter implements TradeFilter {

    @Override
    public List<TradeDTO> filter(List<TradeDTO> trades) {
        long level = getLevel();
        List<TradeDTO> results = new ArrayList<>();
        for(TradeDTO tradeDTO : trades){
            if(isDifferentLevel(level, tradeDTO.getPET().getPET_LEVEL())) continue;
            results.add(tradeDTO);
        }
        return results;
    }

    protected abstract long getLevel();

    private boolean isDifferentLevel(long expectedLevel, long level){
        return expectedLevel != level;
    }

}

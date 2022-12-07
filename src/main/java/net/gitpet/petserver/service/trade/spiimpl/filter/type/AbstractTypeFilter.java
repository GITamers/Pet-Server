package net.gitpet.petserver.service.trade.spiimpl.filter.type;

import net.gitpet.petserver.service.trade.crud.dto.TradeDTO;
import net.gitpet.petserver.service.trade.crud.spi.TradeFilter;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractTypeFilter implements TradeFilter {

    @Override
    public List<TradeDTO> filter(List<TradeDTO> trades){
        long petTypeId = getPetTypeId();
        List<TradeDTO> tradeDTOs = new ArrayList<>();
        for(TradeDTO trade : trades){
            if(trade.getPET().getPET_TYPE_ID() != petTypeId) continue;
            tradeDTOs.add(trade);
        }
        return tradeDTOs;
    }

    protected abstract long getPetTypeId();

}

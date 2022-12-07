package net.gitpet.petserver.service.trade.crud.filter.order;

import net.gitpet.petserver.service.trade.crud.dto.TradeDTO;
import net.gitpet.petserver.service.trade.crud.extensible.TradeFilter;

import java.util.Comparator;
import java.util.List;

abstract class AbstractOrderFilter implements TradeFilter {

    public List<TradeDTO> filter(List<TradeDTO> trades){
        trades.sort(getComparator());
        return trades;
    }

    protected abstract Comparator<TradeDTO> getComparator();

}

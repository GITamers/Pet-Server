package net.gitpet.petserver.service.trade.crud.filter.order;

import net.gitpet.petserver.service.trade.crud.dto.TradeDTO;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public final class AscOrderFilter extends AbstractOrderFilter{
    @Override
    protected Comparator<TradeDTO> getComparator() {
        return (o1, o2) -> Integer.compare(o1.getPRICE(), o2.getPRICE());
    }
}

package net.gitpet.petserver.service.trade.crud.spi;

import net.gitpet.petserver.service.trade.crud.dto.TradeDTO;

import java.util.List;

public interface TradeFilter {

    List<TradeDTO> filter(List<TradeDTO> trades);

}

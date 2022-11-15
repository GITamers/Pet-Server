package net.gitpet.petserver.service.trade.extensible;

import net.gitpet.petserver.service.trade.TradeDTO;

public interface TradeRepository {

    TradeDTO getTrade(long id);

    void deleteTrade(long id);

}

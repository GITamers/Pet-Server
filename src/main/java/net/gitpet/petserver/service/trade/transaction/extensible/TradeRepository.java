package net.gitpet.petserver.service.trade.transaction.extensible;

import net.gitpet.petserver.service.trade.transaction.dto.TradeDTO;

public interface TradeRepository {

    TradeDTO getTrade(long id);

    void deleteTradeByItemId(long id);

}

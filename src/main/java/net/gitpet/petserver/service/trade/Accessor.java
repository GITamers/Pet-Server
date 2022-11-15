package net.gitpet.petserver.service.trade;

import net.gitpet.petserver.service.trade.extensible.TradeRepository;
import net.gitpet.petserver.service.trade.extensible.TradeUserRepository;
import org.jvault.annotation.Inject;
import org.jvault.annotation.InternalBean;

@InternalBean(accessClasses = "net.gitpet.petserver.service.trade.Accessor")
public final class Accessor {

    final TradeRepository TRADE_REPOSITORY;
    final TradeUserRepository USER_REPOSITORY;

    @Inject
    private Accessor(@Inject("defaultUserRepository") TradeUserRepository userRepository,
                     @Inject("defaultTradeRepository") TradeRepository tradeRepository){
        TRADE_REPOSITORY = tradeRepository;
        USER_REPOSITORY = userRepository;
    }

}

package net.gitpet.petserver.service.trade.transaction;

import net.gitpet.petserver.service.trade.transaction.dto.TradeDTO;
import net.gitpet.petserver.service.trade.transaction.dto.TradeUserDTO;
import net.gitpet.petserver.service.trade.transaction.extensible.TradeRepository;
import net.gitpet.petserver.service.trade.transaction.extensible.TradeUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class UserTrade implements Trade<List<TradeUserDTO>>{

    private final TradeUserRepository TRADE_USER_REPOSITORY;
    private final TradeRepository TRADE_REPOSITORY;

    @Override
    public List<TradeUserDTO> transaction(long tradeId, long buyerId) {
        TradeDTO trade = TRADE_REPOSITORY.getTrade(tradeId);
        TradeUserDTO seller = trade.getSELLER();
        TradeUserDTO buyer = TRADE_USER_REPOSITORY.getUser(buyerId);

        long price = trade.getPRICE();
        throwIfDoesNotHaveEnoughMoney(price, buyer);

        seller.setMoney(seller.getMoney() + price);
        buyer.setMoney(buyer.getMoney() - price);

        TRADE_USER_REPOSITORY.updateUser(seller);
        TRADE_USER_REPOSITORY.updateUser(buyer);
        return List.of(seller, buyer);
    }

    private void throwIfDoesNotHaveEnoughMoney(long price, TradeUserDTO buyer){
        if(buyer.getMoney() < price) throw new IllegalStateException("Can not buy item cause buyer does not have enough money \"" + buyer.getID() + "\"");
    }

    @Autowired
    private UserTrade(TradeUserRepository tradeUserRepository,
                      TradeRepository tradeRepository){
        TRADE_USER_REPOSITORY = tradeUserRepository;
        TRADE_REPOSITORY = tradeRepository;
    }

}

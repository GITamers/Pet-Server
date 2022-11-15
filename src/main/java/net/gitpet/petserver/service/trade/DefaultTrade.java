package net.gitpet.petserver.service.trade;

import net.gitpet.petserver.service.trade.extensible.TradeRepository;
import net.gitpet.petserver.service.trade.extensible.TradeUserRepository;
import org.jvault.vault.ClassVault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public final class DefaultTrade implements Trade{

    private final TradeRepository TRADE_REPOSITORY;
    private final TradeUserRepository USER_REPOSITORY;

    @Override
    @SuppressWarnings("all")
    public int transaction(long tradeId, long buyerId) {
        int ans = -1;
        TradeDTO tradeItem = TRADE_REPOSITORY.getTrade(tradeId);
        synchronized (tradeItem){
            TradeUserDTO buyer = USER_REPOSITORY.getUser(buyerId);
            synchronized (buyer){
                TradeUserDTO copiedBuyer = buyer.copy();
                TradeUserDTO copiedSeller = tradeItem.SELLER.copy();
                try {
                    transaction(buyer, tradeItem);
                    ans = buyer.money;
                } catch (Exception e){
                    rollback(copiedBuyer, copiedSeller);
                }
            }
        }
        return ans;
    }

    private void transaction(TradeUserDTO buyer, TradeDTO item){
        long price = item.PRICE;
        if(buyer.money < price) throw new IllegalStateException("Can not buy item cause buyer does not have enough money \"" + buyer.ID + "\"");
        TradeUserDTO seller = item.SELLER;
        seller.money += price;
        buyer.money -= price;
        USER_REPOSITORY.updateUser(seller);
        USER_REPOSITORY.updateUser(buyer);
        TRADE_REPOSITORY.deleteTrade(item.ID);
    }

    private void rollback(TradeUserDTO buyer, TradeUserDTO seller){
        USER_REPOSITORY.updateUser(seller);
        USER_REPOSITORY.updateUser(buyer);
    }

    @Autowired
    private DefaultTrade(@Qualifier("tradeVault") ClassVault vault){
        Accessor accessor = vault.inject(Accessor.class);
        USER_REPOSITORY = accessor.USER_REPOSITORY;
        TRADE_REPOSITORY = accessor.TRADE_REPOSITORY;
    }

}

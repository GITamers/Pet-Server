package net.gitpet.petserver.service.trade.transaction;

import net.gitpet.petserver.service.trade.transaction.dto.TradeDTO;
import net.gitpet.petserver.service.trade.transaction.dto.TradeUserDTO;
import net.gitpet.petserver.service.trade.transaction.dto.TradeUserPetDTO;
import net.gitpet.petserver.service.trade.transaction.dto.TradeResultDTO;
import net.gitpet.petserver.service.trade.transaction.extensible.TradeRepository;
import net.gitpet.petserver.service.trade.transaction.extensible.TradeUserRepository;
import net.gitpet.petserver.service.trade.transaction.extensible.UserPetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FacadeTrade implements Trade<TradeResultDTO> {

    private final TradeRepository TRADE_REPOSITORY;
    private final TradeUserRepository USER_REPOSITORY;
    private final UserPetRepository USER_PET_REPOSITORY;
    private final Trade<TradeUserPetDTO> PET_TRADE;
    private final Trade<List<TradeUserDTO>> USER_TRADE;

    @Override
    @Transactional
    @SuppressWarnings("all")
    public final TradeResultDTO transaction(long tradeId, long buyerId) {
        TradeDTO tradeItem = TRADE_REPOSITORY.getTrade(tradeId);
        synchronized (tradeItem){
            TradeUserDTO buyer = USER_REPOSITORY.getUser(buyerId);
            synchronized (buyer){
                TradeUserDTO seller = tradeItem.getSELLER();
                synchronized (seller) {
                    TradeUserDTO copiedBuyer = buyer.copy();
                    TradeUserDTO copiedSeller = seller.copy();
                    TradeUserPetDTO copiedPet = USER_PET_REPOSITORY.getUserPet(tradeItem.getITEM_ID());
                    try {
                        TradeUserPetDTO petResult = PET_TRADE.transaction(tradeItem.getITEM_ID(), copiedBuyer.getID());
                        List<TradeUserDTO> userResults = USER_TRADE.transaction(tradeId, buyerId);
                        TRADE_REPOSITORY.deleteTradeByItemId(tradeItem.getITEM_ID());
                        return new TradeResultDTO(tradeItem, userResults.get(0), userResults.get(1), petResult);
                    } catch (Exception e) {
                        rollback(copiedBuyer, copiedSeller, copiedPet);
                        throw new IllegalStateException("transaction fail on tradeId \"" + tradeId + "\" buyerId \"" + buyerId + "\"");
                    }
                }
            }
        }
    }

    private void rollback(TradeUserDTO buyer, TradeUserDTO seller, TradeUserPetDTO pet){
        USER_REPOSITORY.updateUser(seller);
        USER_REPOSITORY.updateUser(buyer);
        USER_PET_REPOSITORY.updatePet(pet);
    }

    @Autowired
    FacadeTrade(TradeRepository tradeRepository,
                TradeUserRepository tradeUserRepository,
                UserPetRepository userPetRepository,
                @Qualifier("petTrade") Trade<TradeUserPetDTO> petTrade,
                @Qualifier("userTrade") Trade<List<TradeUserDTO>> userTrade){
        USER_REPOSITORY = tradeUserRepository;
        TRADE_REPOSITORY = tradeRepository;
        USER_PET_REPOSITORY = userPetRepository;
        PET_TRADE = petTrade;
        USER_TRADE = userTrade;
    }

}

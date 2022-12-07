package net.gitpet.petserver.repository.spiimpl.trade.crud;

import net.gitpet.petserver.domain.Trade;
import net.gitpet.petserver.domain.User;
import net.gitpet.petserver.domain.UserPet;
import net.gitpet.petserver.repository.UserPetRepository;
import net.gitpet.petserver.repository.UserRepository;
import net.gitpet.petserver.service.trade.crud.dto.TradeDTO;
import net.gitpet.petserver.service.trade.crud.spi.TradeRepository;
import net.gitpet.petserver.repository.spiimpl.trade.crud.mapper.TradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CRUDTradeService implements TradeRepository {

    private final net.gitpet.petserver.repository.TradeRepository TRADE_REPOSITORY;
    private final UserRepository USER_REPOSITORY;
    private final UserPetRepository USER_PET_REPOSITORY;

    @Autowired
    public CRUDTradeService(net.gitpet.petserver.repository.TradeRepository tradeRepository,
                     UserRepository userRepository,
                     UserPetRepository userPetRepository){
        TRADE_REPOSITORY = tradeRepository;
        USER_REPOSITORY = userRepository;
        USER_PET_REPOSITORY = userPetRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TradeDTO> findAll() {
        List<Trade> trades = TRADE_REPOSITORY.findAll();
        return TradeMapper.mapTradeToTradeDTO(trades);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TradeDTO> findBySellerId(long sellerId) {
        List<Trade> trades = TRADE_REPOSITORY.findBySellerId(sellerId);
        return TradeMapper.mapTradeToTradeDTO(trades);
    }

    @Override
    public void saveTrade(long sellerId, long petId, String title, int price) {
        Trade trade = new Trade();
        trade.setSeller(findSeller(sellerId));
        trade.setPet(findPetToSell(petId));
        trade.setTitle(title);
        trade.setCreatedAt(LocalDateTime.now());
        trade.setPrice(price);
        TRADE_REPOSITORY.saveAndFlush(trade);
    }

    private User findSeller(long id){
        return USER_REPOSITORY.findById(id).orElseThrow(
                ()-> new IllegalStateException("Can not find user id \"" + id + "\"")
        );
    }

    private UserPet findPetToSell(long id){
        UserPet userPet = USER_PET_REPOSITORY.findById(id).orElseThrow(
                ()-> new IllegalStateException("Can not find pet id \"" + id + "\"")
        );
        throwIfIsMainPet(userPet);
        return userPet;
    }

    private void throwIfIsMainPet(UserPet userPet){
        if(userPet.isMainPet())
            throw new IllegalStateException("Can not sell main pet id \"" + userPet.getUpid() + "\"");
    }

    @Override
    public void deleteTrade(long tradeId) {
        TRADE_REPOSITORY.deleteById(tradeId);
    }

    @Override
    public void changeTrade(long tradeId, String title, int price) {
        Trade trade = TRADE_REPOSITORY.findById(tradeId).orElseThrow(
                ()-> new IllegalStateException("Can not find trade id \"" + tradeId + "\"")
        );
        trade.setTitle(title);
        trade.setPrice(price);
    }

}

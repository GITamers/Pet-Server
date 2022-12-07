package net.gitpet.petserver.service.trade.transaction;

import net.gitpet.petserver.domain.User;
import net.gitpet.petserver.domain.UserPet;
import net.gitpet.petserver.repository.TradeRepository;
import net.gitpet.petserver.repository.UserRepository;
import net.gitpet.petserver.repository.UserPetRepository;
import net.gitpet.petserver.service.trade.transaction.dto.TradeResultDTO;
import net.gitpet.petserver.service.trade.transaction.repository.TransactionTradeService;
import net.gitpet.petserver.service.trade.transaction.repository.TransactionUserPetService;
import net.gitpet.petserver.service.trade.transaction.repository.TransactionUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TransactionTradeService.class, TransactionUserService.class, TransactionUserPetService.class, FacadeTrade.class, PetTrade.class, UserTrade.class})
public final class FacadeTradeTest {

    @Autowired
    @Qualifier("facadeTrade")
    private Trade<TradeResultDTO> facadeTrade;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TradeRepository tradeRepository;

    @MockBean
    private UserPetRepository userPetRepository;

    @Test
    public void TRADE_SUCCESS_TEST(){
        // given
        User seller = getUser(1, 0);
        User buyer = getUser(2, 1000);
        UserPet sellPet = getUserPet(3, seller);
        net.gitpet.petserver.domain.Trade trade = getTrade(4, seller, sellPet, 1000);

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(seller));
        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.of(buyer));
        Mockito.when(userPetRepository.findById(3L)).thenReturn(Optional.of(sellPet));
        Mockito.when(tradeRepository.findById(4L)).thenReturn(Optional.of(trade));

        // when
        TradeResultDTO tradeResult = facadeTrade.transaction(4, 2);

        // then
        Assertions.assertAll(
                ()-> Assertions.assertEquals(0, tradeResult.getBUYER().getMoney()),
                ()-> Assertions.assertEquals(1000, tradeResult.getSELLER().getMoney()),
                ()-> Assertions.assertEquals(tradeResult.getPET().getOwnerId(), tradeResult.getBUYER().getID())
        );
    }

    @Test
    public void TRADE_FAIL_CAUSE_NOT_ENOUGH_MONEY_TEST(){
        // given
        User seller = getUser(1, 0);
        User buyer = getUser(2, 999);
        UserPet sellPet = getUserPet(3, seller);
        net.gitpet.petserver.domain.Trade trade = getTrade(4, seller, sellPet, 1000);

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(seller));
        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.of(buyer));
        Mockito.when(userPetRepository.findById(3L)).thenReturn(Optional.of(sellPet));
        Mockito.when(tradeRepository.findById(4L)).thenReturn(Optional.of(trade));

        // when & then
        Assertions.assertThrows(IllegalStateException.class, ()-> facadeTrade.transaction(4, 2));
    }

    private User getUser(long id, long point){
        User user = new User();
        user.setUid(id);
        user.setPoint(point);
        user.setCreatedAt(LocalDateTime.now());
        user.setCommitCnt(0L);
        return user;
    }

    private net.gitpet.petserver.domain.Trade getTrade(long id, User seller, UserPet pet, int price){
        net.gitpet.petserver.domain.Trade trade = new net.gitpet.petserver.domain.Trade();
        trade.setTid(id);
        trade.setSeller(seller);
        trade.setPet(pet);
        trade.setPrice(price);
        trade.setCreatedAt(LocalDateTime.now());
        trade.setTitle("test title");
        return trade;
    }

    private UserPet getUserPet(long id, User owner){
        UserPet userPet = new UserPet();
        userPet.setMainPet(false);
        userPet.setLevel(1L);
        userPet.setCommitCnt(100L);
        userPet.setOwner(owner);
        userPet.setAdoptedAt(LocalDateTime.now());
        userPet.setType(null);
        userPet.setUpid(id);
        return userPet;
    }

}

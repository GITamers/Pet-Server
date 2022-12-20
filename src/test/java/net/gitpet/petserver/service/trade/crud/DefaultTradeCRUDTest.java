package net.gitpet.petserver.service.trade.crud;

import net.gitpet.petserver.domain.Pet;
import net.gitpet.petserver.domain.Trade;
import net.gitpet.petserver.domain.User;
import net.gitpet.petserver.domain.UserPet;
import net.gitpet.petserver.repository.UserPetJpaRepository;
import net.gitpet.petserver.repository.UserJpaRepository;
import net.gitpet.petserver.service.trade.crud.dto.TradeDTO;
import net.gitpet.petserver.service.trade.crud.dto.TradePetDTO;
import net.gitpet.petserver.service.trade.crud.dto.TradeUserDTO;
import net.gitpet.petserver.service.trade.spiimpl.filter.level.*;
import net.gitpet.petserver.service.trade.spiimpl.filter.order.AscOrderFilter;
import net.gitpet.petserver.service.trade.spiimpl.filter.order.DescOrderFilter;
import net.gitpet.petserver.service.trade.spiimpl.filter.type.TypeDinoFilter;
import net.gitpet.petserver.service.trade.crud.option.Keyword;
import net.gitpet.petserver.service.trade.crud.option.Order;
import net.gitpet.petserver.repository.spiimpl.trade.crud.CRUDTradeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Level1Filter.class, Level2Filter.class, Level3Filter.class, Level4Filter.class, Level5Filter.class
        , AscOrderFilter.class, DescOrderFilter.class, TypeDinoFilter.class, CRUDTradeService.class, DefaultTradeCRUD.class
        , Keyword.KeywordFilterInjector.class, Order.OrderFilterInjector.class})
public class DefaultTradeCRUDTest {

    @Autowired
    private TradeCRUD defaultTradeCRUD;
    @MockBean
    private net.gitpet.petserver.repository.TradeRepository tradeRepository;
    @MockBean
    private UserJpaRepository userJPARepository;
    @MockBean
    private UserPetJpaRepository userPetJpaRepository;

    @Test
    public void FIND_ALL_TRADE_CRUD_TEST(){
        // given
        User user1 = getUser(1, 0);
        User user2 = getUser(2, 0);
        UserPet userPet1 = getUserPet(1, 0, user1);
        UserPet userPet2 = getUserPet(2, 0, user2);
        Trade trade1 = getTrade(1, 1000, user1, userPet1);
        Trade trade2 = getTrade(2, 500, user2, userPet2);

        // when
        Mockito.when(tradeRepository.findAll()).thenReturn(List.of(trade1, trade2));
        List<TradeDTO> trades = defaultTradeCRUD.readAll();

        // then
        Assertions.assertAll(
                ()-> Assertions.assertEquals(2, trades.size()),
                ()-> Assertions.assertTrue(isEqualTrade(trades.get(0), user1, trade1, userPet1)),
                ()-> Assertions.assertTrue(isEqualTrade(trades.get(1), user2, trade2, userPet2))
        );
    }

    @Test
    public void FIND_BY_SELLER_ID_CRUD_TEST(){
        // given
        User user1 = getUser(1, 0);
        User user2 = getUser(2, 0);
        UserPet userPet1 = getUserPet(1, 0, user1);
        UserPet userPet2 = getUserPet(2, 0, user2);
        Trade trade1 = getTrade(1, 1000, user1, userPet1);
        Trade trade2 = getTrade(2, 500, user2, userPet2);

        // when
        Mockito.when(tradeRepository.findBySellerId(1)).thenReturn(List.of(trade1));
        Mockito.when(tradeRepository.findBySellerId(2)).thenReturn(List.of(trade2));
        List<TradeDTO> trades1 = defaultTradeCRUD.readBySellerId(1);
        List<TradeDTO> trades2 = defaultTradeCRUD.readBySellerId(2);

        // then
        Assertions.assertAll(
                ()-> Assertions.assertEquals(1, trades1.size()),
                ()-> Assertions.assertEquals(1, trades2.size()),
                ()-> Assertions.assertTrue(isEqualTrade(trades1.get(0), user1, trade1, userPet1)),
                ()-> Assertions.assertTrue(isEqualTrade(trades2.get(0), user2, trade2, userPet2))
        );
    }

    @Test
    public void FIND_WITH_LEVEL_FILTER_CRUD_TEST(){
        // given
        User user1 = getUser(1, 0);
        UserPet userPet1 = getUserPet(1, 1, user1);
        UserPet userPet2 = getUserPet(2, 2, user1);
        UserPet userPet3 = getUserPet(3, 2, user1);
        UserPet userPet4 = getUserPet(4, 2, user1);
        UserPet userPet5 = getUserPet(5, 3, user1);
        Trade trade1 = getTrade(1, 1, user1, userPet1);
        Trade trade2 = getTrade(2, 1, user1, userPet2);
        Trade trade3 = getTrade(3, 1, user1, userPet3);
        Trade trade4 = getTrade(4, 1, user1, userPet4);
        Trade trade5 = getTrade(5, 1, user1, userPet5);

        // when
        Mockito.when(tradeRepository.findAll()).thenReturn(List.of(trade1, trade2, trade3, trade4, trade5));
        List<TradeDTO> level1s = defaultTradeCRUD.readWithFilter(TradeCRUD.Filter.getBuilder().addKeyword(Keyword.PET_LEVEL_1).build());
        List<TradeDTO> level2s = defaultTradeCRUD.readWithFilter(TradeCRUD.Filter.getBuilder().addKeyword(Keyword.PET_LEVEL_2).build());
        List<TradeDTO> level3s = defaultTradeCRUD.readWithFilter(TradeCRUD.Filter.getBuilder().addKeyword(Keyword.PET_LEVEL_3).build());

        // then
        Assertions.assertAll(
                ()-> Assertions.assertEquals(1, level1s.size()),
                ()-> Assertions.assertEquals(3, level2s.size()),
                ()-> Assertions.assertEquals(1, level3s.size()),
                ()-> Assertions.assertTrue(isEqualTrade(level1s.get(0), user1, trade1, userPet1)),
                ()-> Assertions.assertTrue(isEqualTrade(level2s.get(0), user1, trade2, userPet2)),
                ()-> Assertions.assertTrue(isEqualTrade(level2s.get(1), user1, trade3, userPet3)),
                ()-> Assertions.assertTrue(isEqualTrade(level2s.get(2), user1, trade4, userPet4)),
                ()-> Assertions.assertTrue(isEqualTrade(level3s.get(0), user1, trade5, userPet5))
        );
    }

    @Test
    public void FIND_WITH_TYPE_FILTER_CRUD_TEST(){
        // given
        User user1 = getUser(1, 0);
        UserPet userPet1 = getUserPet(1, 1, user1);
        UserPet userPet2 = getUserPet(2, 2, user1);
        Trade trade1 = getTrade(1, 1, user1, userPet1);
        Trade trade2 = getTrade(2, 1, user1, userPet2);

        // when
        Mockito.when(tradeRepository.findAll()).thenReturn(List.of(trade1, trade2));
        List<TradeDTO> trades = defaultTradeCRUD.readWithFilter(TradeCRUD.Filter.getBuilder().addKeyword(Keyword.PET_TYPE_DINO).build());

        // then
        Assertions.assertAll(
                ()-> Assertions.assertEquals(2, trades.size()),
                ()-> Assertions.assertTrue(isEqualTrade(trades.get(0), user1, trade1, userPet1)),
                ()-> Assertions.assertTrue(isEqualTrade(trades.get(1), user1, trade2, userPet2))
        );
    }

    @Test
    public void FIND_WITH_ORDER_ASC_CRUD_TEST(){
        // given
        User user1 = getUser(1, 0);
        UserPet userPet1 = getUserPet(1, 1, user1);
        UserPet userPet2 = getUserPet(2, 2, user1);
        Trade trade1 = getTrade(1, 2, user1, userPet1);
        Trade trade2 = getTrade(2, 1, user1, userPet2);

        // when
        Mockito.when(tradeRepository.findAll()).thenReturn(List.of(trade1, trade2));
        List<TradeDTO> trades = defaultTradeCRUD.readWithFilter(TradeCRUD.Filter.getBuilder().setOrder(Order.ASC).build());

        // then
        Assertions.assertAll(
                ()-> Assertions.assertEquals(2, trades.size()),
                ()-> Assertions.assertTrue(isEqualTrade(trades.get(0), user1, trade2, userPet2)),
                ()-> Assertions.assertTrue(isEqualTrade(trades.get(1), user1, trade1, userPet1)),
                ()-> Assertions.assertTrue(trades.get(0).getPRICE() < trades.get(1).getPRICE())
        );
    }

    @Test
    public void FIND_WITH_ORDER_DESC_CRUD_TEST(){
        // given
        User user1 = getUser(1, 0);
        UserPet userPet1 = getUserPet(1, 1, user1);
        UserPet userPet2 = getUserPet(2, 2, user1);
        Trade trade1 = getTrade(1, 2, user1, userPet1);
        Trade trade2 = getTrade(2, 1, user1, userPet2);

        // when
        Mockito.when(tradeRepository.findAll()).thenReturn(List.of(trade1, trade2));
        List<TradeDTO> trades = defaultTradeCRUD.readWithFilter(TradeCRUD.Filter.getBuilder().setOrder(Order.DESC).build());

        // then
        Assertions.assertAll(
                ()-> Assertions.assertEquals(2, trades.size()),
                ()-> Assertions.assertTrue(isEqualTrade(trades.get(0), user1, trade1, userPet1)),
                ()-> Assertions.assertTrue(isEqualTrade(trades.get(1), user1, trade2, userPet2)),
                ()-> Assertions.assertTrue(trades.get(0).getPRICE() > trades.get(1).getPRICE())
        );
    }

    private User getUser(long id, long point){
        User user = new User();
        user.setUid(id);
        user.setPoint(point);
        user.setCreatedAt(LocalDateTime.now());
        user.setCommitCnt(0L);
        return user;
    }

    private UserPet getUserPet(long id, long level, User owner){
        UserPet userPet = new UserPet();
        userPet.setMainPet(false);
        userPet.setLevel(level);
        userPet.setCommitCnt(100L);
        userPet.setOwner(owner);
        userPet.setAdoptedAt(LocalDateTime.now());
        userPet.setType(getPetType());
        userPet.setUpid(id);
        return userPet;
    }

    private Pet getPetType(){
        Pet pet = new Pet();
        pet.setPid(1L);
        return pet;
    }

    private Trade getTrade(long id, int price, User seller, UserPet pet){
        Trade trade = new Trade();
        trade.setTid(id);
        trade.setPrice(price);
        trade.setTitle("test title");
        trade.setPet(pet);
        trade.setCreatedAt(LocalDateTime.now());
        trade.setSeller(seller);
        return trade;
    }

    private boolean isEqualTrade(TradeDTO tradeDTO, User seller, Trade trade, UserPet userPet){
        return isEqualSeller(tradeDTO.getSELLER(), seller)
                && isEqualUserPet(tradeDTO.getPET(), userPet)
                && tradeDTO.getTRADE_ID() == trade.getTid()
                && tradeDTO.getPRICE() == trade.getPrice()
                && tradeDTO.getTITLE().equals(trade.getTitle())
                && tradeDTO.getCREATED_AT() == trade.getCreatedAt();
    }

    private boolean isEqualSeller(TradeUserDTO tradeUserDTO, User seller){
        return tradeUserDTO.getUSER_ID() == seller.getUid()
                && tradeUserDTO.getPOINT() == seller.getPoint();
    }

    private boolean isEqualUserPet(TradePetDTO userPetDTO, UserPet userPet){
        return userPetDTO.getPET_ID() == userPet.getUpid()
                && userPetDTO.getPET_LEVEL() == userPet.getLevel()
                && userPetDTO.getPET_TYPE_ID() == userPet.getType().getPid()
                && userPetDTO.getOWNER().getUSER_ID() == userPet.getOwner().getUid();
    }

}

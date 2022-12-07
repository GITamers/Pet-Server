package net.gitpet.petserver.service.trade.acceptance;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.gitpet.petserver.controller.trade.request.TradeRequest;
import net.gitpet.petserver.controller.trade.response.ResponseTradeDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

@AutoConfigureMockMvc
@SpringBootTest
public class TradeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TradeTestInitializer tradeTestInitializer;

    @Autowired
    private TradeTestResponseMaker tradeTestResponseMaker;

    @Autowired
    private ObjectMapper OBJECT_MAPPER;

    @BeforeEach
    @AfterEach
    public void deprecateAll(){
        tradeTestInitializer.deprecateAll();
        tradeTestInitializer.deprecateAll();
    }

    @Test
    @DisplayName("METHOD = \"GET\", PATH = \"/trade?keyword={keyword}&order={order}\"")
    public void READ_TRADE_WITH_FILTER_TEST() throws Exception {
        // given
        TradeTestInitializer.Response testInfo = tradeTestInitializer.initializeAllInOne(TradeTestInitializer.Request.builder()
                .user(TradeTestInitializer.Request.User.builder()
                        .commitCount(1000)
                        .point(500)
                        .build())
                .userPet(TradeTestInitializer.Request.UserPet.builder()
                        .isMainPet(false)
                        .commitCount(300)
                        .level(1)
                        .build())
                .trade(TradeTestInitializer.Request.Trade.builder()
                        .price(1000)
                        .title("READ_TRADE_WITH_FILTER_TEST")
                        .createdAt(LocalDateTime.now())
                        .build())
                .build());

        String expectedResponse = tradeTestResponseMaker.getExpectedResponse(testInfo);

        // when
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/trade")
                        .param("keyword", "PET_LEVEL_1")
                        .param("order", "ASC"));

        // then
        resultActions.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                MockMvcResultMatchers.content().string(expectedResponse)
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("METHOD = \"GET\", PATH = \"/trade\"")
    public void READ_TRADE_TEST() throws Exception{
        // given
        TradeTestInitializer.Response testInfo1 = tradeTestInitializer.initializeAllInOne(TradeTestInitializer.Request.builder()
                .user(TradeTestInitializer.Request.User.builder()
                        .commitCount(1000)
                        .point(500)
                        .build())
                .userPet(TradeTestInitializer.Request.UserPet.builder()
                        .isMainPet(false)
                        .commitCount(300)
                        .level(1)
                        .build())
                .trade(TradeTestInitializer.Request.Trade.builder()
                        .price(1000)
                        .title("READ_TRADE_WITH_FILTER_TEST")
                        .createdAt(LocalDateTime.now())
                        .build())
                .build());

        TradeTestInitializer.Response testInfo2 = tradeTestInitializer.initializeAllInOne(TradeTestInitializer.Request.builder()
                .user(TradeTestInitializer.Request.User.builder()
                        .commitCount(1000)
                        .point(500)
                        .build())
                .userPet(TradeTestInitializer.Request.UserPet.builder()
                        .isMainPet(false)
                        .commitCount(300)
                        .level(2)
                        .build())
                .trade(TradeTestInitializer.Request.Trade.builder()
                        .price(1000)
                        .title("READ_TRADE_WITH_FILTER_TEST")
                        .createdAt(LocalDateTime.now())
                        .build())
                .build());

        String expectedResponse = tradeTestResponseMaker.getExpectedResponse(testInfo1, testInfo2);

        // when
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/trade"));

        // then
        resultActions.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                MockMvcResultMatchers.content().string(expectedResponse)
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("METHOD = \"GET\", PATH = \"/trade/{user-id}\"")
    public void READ_TRADE_WITH_USER_ID_TEST() throws Exception {
        // given
        TradeTestInitializer.Response testInfo1 = tradeTestInitializer.initializeAllInOne(TradeTestInitializer.Request.builder()
                .user(TradeTestInitializer.Request.User.builder()
                        .commitCount(1000)
                        .point(500)
                        .build())
                .userPet(TradeTestInitializer.Request.UserPet.builder()
                        .isMainPet(false)
                        .commitCount(300)
                        .level(1)
                        .build())
                .trade(TradeTestInitializer.Request.Trade.builder()
                        .price(1000)
                        .title("READ_TRADE_WITH_FILTER_TEST")
                        .createdAt(LocalDateTime.now())
                        .build())
                .build());

        TradeTestInitializer.Response testInfo2 = tradeTestInitializer.initializeAllInOne(TradeTestInitializer.Request.builder()
                .user(TradeTestInitializer.Request.User.builder()
                        .commitCount(1000)
                        .point(500)
                        .build())
                .userPet(TradeTestInitializer.Request.UserPet.builder()
                        .isMainPet(false)
                        .commitCount(300)
                        .level(2)
                        .build())
                .trade(TradeTestInitializer.Request.Trade.builder()
                        .price(1000)
                        .title("READ_TRADE_WITH_FILTER_TEST")
                        .createdAt(LocalDateTime.now())
                        .build())
                .build());

        String expectedResponse = tradeTestResponseMaker.getExpectedResponse(testInfo2);

        // when
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/trade/{user-id}", testInfo2.getSellerId()));

        // then
        resultActions.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                MockMvcResultMatchers.content().string(expectedResponse)
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("METHOD = \"DELETE\", PATH = \"/trade/{trade-id}\"")
    public void DELETE_TRADE_TEST() throws Exception{
        // given
        TradeTestInitializer.Response testInfo1 = tradeTestInitializer.initializeAllInOne(TradeTestInitializer.Request.builder()
                .user(TradeTestInitializer.Request.User.builder()
                        .commitCount(1000)
                        .point(500)
                        .build())
                .userPet(TradeTestInitializer.Request.UserPet.builder()
                        .isMainPet(false)
                        .commitCount(300)
                        .level(1)
                        .build())
                .trade(TradeTestInitializer.Request.Trade.builder()
                        .price(1000)
                        .title("READ_TRADE_WITH_FILTER_TEST")
                        .createdAt(LocalDateTime.now())
                        .build())
                .build());

        String expectedResponse = tradeTestResponseMaker.getExpectedResponse();

        // when
        mvc.perform(MockMvcRequestBuilders.delete("/trade/{trade-id}", testInfo1.getTradeId()));
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/trade"));

        // then
        resultActions.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                MockMvcResultMatchers.content().string(expectedResponse)
        );
    }

    @Test
    @DisplayName("METHOD = \"DELETE\", PATH = \"/trade/{trade-id}\", DELETE_ONE")
    public void DELETE_TRADE_ONE_TEST() throws Exception{
        // given
        TradeTestInitializer.Response testInfo1 = tradeTestInitializer.initializeAllInOne(TradeTestInitializer.Request.builder()
                .user(TradeTestInitializer.Request.User.builder()
                        .commitCount(1000)
                        .point(500)
                        .build())
                .userPet(TradeTestInitializer.Request.UserPet.builder()
                        .isMainPet(false)
                        .commitCount(300)
                        .level(1)
                        .build())
                .trade(TradeTestInitializer.Request.Trade.builder()
                        .price(1000)
                        .title("READ_TRADE_WITH_FILTER_TEST")
                        .createdAt(LocalDateTime.now())
                        .build())
                .build());

        TradeTestInitializer.Response testInfo2 = tradeTestInitializer.initializeAllInOne(TradeTestInitializer.Request.builder()
                .user(TradeTestInitializer.Request.User.builder()
                        .commitCount(1000)
                        .point(500)
                        .build())
                .userPet(TradeTestInitializer.Request.UserPet.builder()
                        .isMainPet(false)
                        .commitCount(300)
                        .level(2)
                        .build())
                .trade(TradeTestInitializer.Request.Trade.builder()
                        .price(1000)
                        .title("READ_TRADE_WITH_FILTER_TEST")
                        .createdAt(LocalDateTime.now())
                        .build())
                .build());

        String expectedResponse = tradeTestResponseMaker.getExpectedResponse(testInfo2);

        // when
        mvc.perform(MockMvcRequestBuilders.delete("/trade/{trade-id}", testInfo1.getTradeId()));
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/trade"));

        // then
        resultActions.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                MockMvcResultMatchers.content().string(expectedResponse)
        );
    }

    @Test
    @DisplayName("METHOD = \"POST\", PATH = \"/trade\"")
    public void SAVE_TRADE_TEST() throws Exception{
        // given
        TradeTestInitializer.Response testInfo1 = tradeTestInitializer.initializeAllInOne(TradeTestInitializer.Request.builder()
                .user(TradeTestInitializer.Request.User.builder()
                        .commitCount(1000)
                        .point(500)
                        .build())
                .userPet(TradeTestInitializer.Request.UserPet.builder()
                        .isMainPet(false)
                        .commitCount(300)
                        .level(1)
                        .build())
                .trade(TradeTestInitializer.Request.Trade.builder()
                        .price(1000)
                        .title("READ_TRADE_WITH_FILTER_TEST")
                        .createdAt(LocalDateTime.now())
                        .build())
                .build());

        TradeRequest tradeRequest = TradeRequest.builder()
                .petId(testInfo1.getUserPetId())
                .sellerId(testInfo1.getSellerId())
                .price(10000)
                .title("NEW_TRADE")
                .build();

        // when
        mvc.perform(MockMvcRequestBuilders.post("/trade").content(OBJECT_MAPPER.writeValueAsString(tradeRequest)).contentType(MediaType.APPLICATION_JSON));

        // then
    }

    @Test
    @DisplayName("METHOD = \"PUT\", PATH = \"/trade/{trade-id}\"")
    public void PUT_TRADE_TEST() throws Exception{
        // given
        TradeTestInitializer.Response testInfo1 = tradeTestInitializer.initializeAllInOne(TradeTestInitializer.Request.builder()
                .user(TradeTestInitializer.Request.User.builder()
                        .commitCount(1000)
                        .point(500)
                        .build())
                .userPet(TradeTestInitializer.Request.UserPet.builder()
                        .isMainPet(false)
                        .commitCount(300)
                        .level(1)
                        .build())
                .trade(TradeTestInitializer.Request.Trade.builder()
                        .price(99999)
                        .title("READ_TRADE_WITH_FILTER_TEST")
                        .createdAt(LocalDateTime.now())
                        .build())
                .build());

        TradeRequest tradeRequest = TradeRequest.builder()
                .title("NEW_TITLE")
                .price(12345)
                .build();

        String expectedResponse = tradeTestResponseMaker.getExpectedResponse(testInfo1);
        expectedResponse = expectedResponse.replace("READ_TRADE_WITH_FILTER_TEST", tradeRequest.getTitle());
        expectedResponse = expectedResponse.replace(""+99999, ""+tradeRequest.getPrice());

        // when
        mvc.perform(MockMvcRequestBuilders.put("/trade/{trade-id}", testInfo1.getTradeId())
                .content(OBJECT_MAPPER.writeValueAsString(tradeRequest)).contentType(MediaType.APPLICATION_JSON)
        );
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/trade/{user-id}", testInfo1.getSellerId()));

        // then
        resultActions.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                MockMvcResultMatchers.content().string(expectedResponse)
        );
    }

    @Test
    @DisplayName("METHOD = \"PUT\", PATH = \"/trade?trade-id={trade-id}&buyer-id={buyer-id}\"")
    public void TRANSACTION_TRADE_TEST() throws Exception{
        // given
        TradeTestInitializer.Response seller = tradeTestInitializer.initializeAllInOne(TradeTestInitializer.Request.builder()
                .user(TradeTestInitializer.Request.User.builder()
                        .commitCount(1000)
                        .point(1)
                        .build())
                .userPet(TradeTestInitializer.Request.UserPet.builder()
                        .isMainPet(false)
                        .commitCount(300)
                        .level(1)
                        .build())
                .trade(TradeTestInitializer.Request.Trade.builder()
                        .price(1000)
                        .title("READ_TRADE_WITH_FILTER_TEST")
                        .createdAt(LocalDateTime.now())
                        .build())
                .build());

        TradeTestInitializer.Response buyer = tradeTestInitializer.initializeAllInOne(TradeTestInitializer.Request.builder()
                .user(TradeTestInitializer.Request.User.builder()
                        .commitCount(1000)
                        .point(1234)
                        .build())
                .userPet(TradeTestInitializer.Request.UserPet.builder()
                        .isMainPet(false)
                        .commitCount(300)
                        .level(1)
                        .build())
                .trade(TradeTestInitializer.Request.Trade.builder()
                        .price(999)
                        .title("READ_TRADE_WITH_FILTER_TEST")
                        .createdAt(LocalDateTime.now())
                        .build())
                .build());

        String expectedResultOfBuyer = tradeTestResponseMaker.getExpectedResponse(buyer);
        expectedResultOfBuyer = expectedResultOfBuyer.replace(""+1234, ""+(1234-1000));

        // when
        mvc.perform(MockMvcRequestBuilders.put("/trade")
                .param("trade-id", ""+seller.getTradeId())
                .param("buyer-id", ""+buyer.getSellerId()));
        ResultActions resultActionsOfBuyer = mvc.perform(MockMvcRequestBuilders.get("/trade/{user-id}", buyer.getSellerId()));
        ResultActions resultActionsOfSeller = mvc.perform(MockMvcRequestBuilders.get("/trade/{user-id}", seller.getSellerId()));

        // then
        resultActionsOfBuyer.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                MockMvcResultMatchers.content().string(expectedResultOfBuyer)
        ).andDo(MockMvcResultHandlers.print());
        resultActionsOfSeller.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                MockMvcResultMatchers.content().string("[]")
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("METHOD = \"PUT\", PATH = \"/trade?trade-id={trade-id}&buyer-id={buyer-id}\", TRADE_ONE_AND_DELETE_ALL_SAME_ITEM")
    public void TRANSACTION_TEST_DELETE_ALL_SAME_ITEM_TEST() throws Exception {
        // given
        TradeTestInitializer.Response seller = tradeTestInitializer.initializeAllInOne(TradeTestInitializer.Request.builder()
                .user(TradeTestInitializer.Request.User.builder()
                        .commitCount(1000)
                        .point(1)
                        .build())
                .userPet(TradeTestInitializer.Request.UserPet.builder()
                        .isMainPet(false)
                        .commitCount(300)
                        .level(1)
                        .build())
                .trade(TradeTestInitializer.Request.Trade.builder()
                        .price(1000)
                        .title("READ_TRADE_WITH_FILTER_TEST")
                        .createdAt(LocalDateTime.now())
                        .build())
                .build());

        TradeTestInitializer.Response buyer = tradeTestInitializer.initializeAllInOne(TradeTestInitializer.Request.builder()
                .user(TradeTestInitializer.Request.User.builder()
                        .commitCount(1000)
                        .point(1234)
                        .build())
                .userPet(TradeTestInitializer.Request.UserPet.builder()
                        .isMainPet(false)
                        .commitCount(300)
                        .level(1)
                        .build())
                .trade(TradeTestInitializer.Request.Trade.builder()
                        .price(999)
                        .title("READ_TRADE_WITH_FILTER_TEST")
                        .createdAt(LocalDateTime.now())
                        .build())
                .build());

        TradeRequest tradePostRequest = TradeRequest.builder()
                .price(2000)
                .title("MUST_DELETED_TRADE")
                .sellerId(seller.getSellerId())
                .petId(seller.getUserPetId())
                .build();

        String expectedResultOfBuyer = tradeTestResponseMaker.getExpectedResponse(buyer);
        expectedResultOfBuyer = expectedResultOfBuyer.replace(""+1234, ""+(1234-1000));

        // when
        mvc.perform(MockMvcRequestBuilders.post("/trade").content(OBJECT_MAPPER.writeValueAsString(tradePostRequest)));
        mvc.perform(MockMvcRequestBuilders.put("/trade")
                .param("trade-id", ""+seller.getTradeId())
                .param("buyer-id", ""+buyer.getSellerId())
        );
        ResultActions resultActionsOfBuyer = mvc.perform(MockMvcRequestBuilders.get("/trade/{user-id}", buyer.getSellerId()));
        ResultActions resultActionsOfSeller = mvc.perform(MockMvcRequestBuilders.get("/trade/{user-id}", seller.getSellerId()));

        // then
        resultActionsOfBuyer.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                MockMvcResultMatchers.content().string(expectedResultOfBuyer)
        ).andDo(MockMvcResultHandlers.print());
        resultActionsOfSeller.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                MockMvcResultMatchers.content().string("[]")
        ).andDo(MockMvcResultHandlers.print());
    }

}

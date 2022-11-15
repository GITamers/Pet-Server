package net.gitpet.petserver.service.trade;

import net.gitpet.petserver.configuration.JvaultConfiguration;
import net.gitpet.petserver.service.crud.develop.ForTest;
import net.gitpet.petserver.service.util.BeanProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@SpringBootTest
public class DefaultTradeTest {

    private AnnotationConfigApplicationContext context;

    @BeforeEach
    public void initContext(){
        context = new AnnotationConfigApplicationContext(
                DefaultTrade.class,
                ForTest.class,
                BeanProvider.class,
                JvaultConfiguration.class);
    }

    @Test
    public void BUY_TEST_MONEY_1000(){
        // given
        Trade defaultTrade = context.getBean(DefaultTrade.class);

        // when
        int leftMoney = defaultTrade.transaction(1, 2);

        // then
        Assertions.assertEquals(500, leftMoney);
    }

}

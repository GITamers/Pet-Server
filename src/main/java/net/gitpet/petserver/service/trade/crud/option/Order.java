package net.gitpet.petserver.service.trade.crud.option;

import net.gitpet.petserver.service.trade.crud.spi.TradeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public enum Order {

    ASC,
    DESC;

    private TradeFilter filter;

    public TradeFilter getFilter(){
        return filter;
    }

    @Component
    public static final class OrderFilterInjector{

        @Autowired
        public OrderFilterInjector(@Qualifier("ascOrderFilter") TradeFilter ascOrderFilter,
                                   @Qualifier("descOrderFilter") TradeFilter descOrderFilter){
            ASC.filter = ascOrderFilter;
            DESC.filter = descOrderFilter;
        }
    }

}

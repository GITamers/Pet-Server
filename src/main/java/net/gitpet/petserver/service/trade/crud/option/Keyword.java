package net.gitpet.petserver.service.trade.crud.option;

import net.gitpet.petserver.service.trade.crud.extensible.TradeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public enum Keyword{

    PET_LEVEL_1,
    PET_LEVEL_2,
    PET_LEVEL_3,
    PET_LEVEL_4,
    PET_LEVEL_5,
    PET_TYPE_DINO;

    private TradeFilter filter;

    public TradeFilter getFilter(){
        return filter;
    }

    @Component
    public static final class KeywordFilterInjector{

        @Autowired
        public KeywordFilterInjector(@Qualifier("level1Filter") TradeFilter level1Filter,
                                   @Qualifier("level2Filter") TradeFilter level2Filter,
                                   @Qualifier("level3Filter") TradeFilter level3Filter,
                                   @Qualifier("level4Filter") TradeFilter level4Filter,
                                   @Qualifier("level5Filter") TradeFilter level5Filter,
                                   @Qualifier("typeDinoFilter") TradeFilter typeDinoFilter){
            PET_LEVEL_1.filter = level1Filter;
            PET_LEVEL_2.filter = level2Filter;
            PET_LEVEL_3.filter = level3Filter;
            PET_LEVEL_4.filter = level4Filter;
            PET_LEVEL_5.filter = level5Filter;
            PET_TYPE_DINO.filter = typeDinoFilter;
        }

    }

}

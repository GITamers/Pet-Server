package net.gitpet.petserver.service.trade.crud;

import net.gitpet.petserver.service.trade.crud.dto.TradeDTO;
import net.gitpet.petserver.service.trade.crud.extensible.TradeFilter;
import net.gitpet.petserver.service.trade.crud.option.Keyword;
import net.gitpet.petserver.service.trade.crud.option.Order;

import java.util.ArrayList;
import java.util.List;

public interface TradeCRUD {

    List<TradeDTO> readAll();

    List<TradeDTO> readBySellerId(long sellerId);

    List<TradeDTO> readWithFilter(Filter filter);

    void createTrade(long sellerId, long petId, String title, int price);

    void deleteTrade(long tradeId);

    void updateTrade(long tradeId, String title, int price);

    final class Filter{

        private final List<Keyword> KEY_WORDS;
        private final Order ORDER;

        private Filter(){
            throw new UnsupportedOperationException("Can not invoke constructor \"TradeCRUD.Filter()\"");
        }

        public Filter(Builder builder){
            KEY_WORDS = builder.KEY_WORDS;
            ORDER = builder.order;
        }

        public static Builder getBuilder(){
            return new Builder();
        }

        List<TradeFilter> getFilters(){
            List<TradeFilter> filters = new ArrayList<>(keywordToFilter());
            filters.add(ORDER.getFilter());
            return filters;
        }

        List<TradeFilter> keywordToFilter(){
            List<TradeFilter> filters = new ArrayList<>();
            for(Keyword keyword : KEY_WORDS) filters.add(keyword.getFilter());
            return filters;
        }

        public static final class Builder{

            private final List<Keyword> KEY_WORDS = new ArrayList<>();
            private Order order = Order.ASC;

            private Builder(){}

            public Builder addKeyword(Keyword keyword){
                KEY_WORDS.add(keyword);
                return this;
            }

            public Builder setOrder(Order order){
                this.order = order;
                return this;
            }

            public Filter build(){
                return new Filter(this);
            }

        }

    }

}

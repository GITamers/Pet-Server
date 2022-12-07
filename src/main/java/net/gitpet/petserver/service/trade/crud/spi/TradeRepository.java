package net.gitpet.petserver.service.trade.crud.spi;

import net.gitpet.petserver.service.trade.crud.dto.TradeDTO;

import java.util.List;

public interface TradeRepository {

    List<TradeDTO> findAll();

    List<TradeDTO> findBySellerId(long sellerId);

    void saveTrade(long sellerId, long petId, String title, int price);

    void deleteTrade(long tradeId);

    void changeTrade(long tradeId, String title, int price);

}

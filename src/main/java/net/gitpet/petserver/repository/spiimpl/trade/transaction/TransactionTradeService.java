package net.gitpet.petserver.repository.spiimpl.trade.transaction;

import net.gitpet.petserver.domain.Trade;
import net.gitpet.petserver.service.trade.transaction.dto.TradeDTO;
import net.gitpet.petserver.service.trade.transaction.spi.TradeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionTradeService implements TradeRepository {

    private final net.gitpet.petserver.repository.TradeRepository TRADE_REPOSITORY;

    private final TransactionUserService USER_SERVICE;

    @Override
    @Transactional(readOnly = true)
    public TradeDTO getTrade(long id) {
        Trade trade = TRADE_REPOSITORY.findById(id).orElseThrow(()-> new IllegalStateException("Can not find trade id \"" + id + "\""));
        return new TradeDTO(trade.getTid(), trade.getPet().getUpid(), trade.getPrice(), USER_SERVICE.getUser(trade.getSeller().getUid()));
    }

    @Override
    @Transactional
    public void deleteTradeByItemId(long itemId) {
        TRADE_REPOSITORY.deleteByPetId(itemId);
    }

    @Autowired
    TransactionTradeService(net.gitpet.petserver.repository.TradeRepository tradeRepository,
                            TransactionUserService transactionUserService){
        TRADE_REPOSITORY = tradeRepository;
        USER_SERVICE = transactionUserService;
    }

}

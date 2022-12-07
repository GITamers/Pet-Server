package net.gitpet.petserver.service.trade.transaction;

public interface Trade<V> {

    V transaction(long tradeId, long buyerId);

}

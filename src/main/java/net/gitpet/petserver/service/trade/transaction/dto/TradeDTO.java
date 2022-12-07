package net.gitpet.petserver.service.trade.transaction.dto;

import lombok.Getter;

import java.util.Objects;

@Getter
public final class TradeDTO {

    private final long ID;
    private final long ITEM_ID;
    private final int PRICE;
    private final TradeUserDTO SELLER;

    public TradeDTO(long id, long itemId, int money, TradeUserDTO seller){
        ID = id;
        ITEM_ID = itemId;
        PRICE = money;
        SELLER = seller;
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof TradeDTO)) return false;
        TradeDTO tradeDTO = (TradeDTO) obj;
        return tradeDTO.ID == ID && tradeDTO.PRICE == PRICE;
    }

    @Override
    public int hashCode(){
        return Objects.hash(ID);
    }

    public TradeDTO copy(){
        return new TradeDTO(ID, ITEM_ID, PRICE, SELLER);
    }

}

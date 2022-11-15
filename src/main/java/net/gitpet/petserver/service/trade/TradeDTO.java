package net.gitpet.petserver.service.trade;

import java.util.Objects;

public final class TradeDTO {

    final long ID;
    final int PRICE;
    final TradeUserDTO SELLER;

    public TradeDTO(long id, int money,  TradeUserDTO seller){
        ID = id;
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

    TradeDTO copy(){
        return new TradeDTO(ID, PRICE, SELLER);
    }

}

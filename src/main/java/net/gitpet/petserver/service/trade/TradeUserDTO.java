package net.gitpet.petserver.service.trade;

import java.util.Objects;

public final class TradeUserDTO {

    final long ID;
    int money;

    public TradeUserDTO(long id, int money){
        ID = id;
        this.money = money;
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof TradeUserDTO)) return false;
        TradeUserDTO tradeUserDTO = (TradeUserDTO) obj;
        return tradeUserDTO.ID == ID && tradeUserDTO.money == money;
    }

    @Override
    public int hashCode(){
        return Objects.hash(ID);
    }

    TradeUserDTO copy(){
        return new TradeUserDTO(ID, money);
    }

}

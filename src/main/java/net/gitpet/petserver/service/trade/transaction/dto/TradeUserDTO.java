package net.gitpet.petserver.service.trade.transaction.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public final class TradeUserDTO {

    private final long ID;
    private long money;

    public TradeUserDTO(long id, long money) {
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

    public TradeUserDTO copy(){
        return new TradeUserDTO(ID, money);
    }

}

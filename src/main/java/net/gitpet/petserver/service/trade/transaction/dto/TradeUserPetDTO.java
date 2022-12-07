package net.gitpet.petserver.service.trade.transaction.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class TradeUserPetDTO {

    private final long ID;
    private long ownerId;

    public TradeUserPetDTO(long id, long ownerId){
        ID = id;
        this.ownerId = ownerId;
    }

    public TradeUserPetDTO copy(){
        return new TradeUserPetDTO(ID, ownerId);
    }

}

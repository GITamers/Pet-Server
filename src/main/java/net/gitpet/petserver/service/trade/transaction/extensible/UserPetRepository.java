package net.gitpet.petserver.service.trade.transaction.extensible;

import net.gitpet.petserver.service.trade.transaction.dto.TradeUserPetDTO;

public interface UserPetRepository {

    TradeUserPetDTO getUserPet(long id);

    void updatePet(TradeUserPetDTO userPetDTO);

}

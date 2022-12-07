package net.gitpet.petserver.service.trade.transaction;

import net.gitpet.petserver.service.trade.transaction.dto.TradeUserPetDTO;
import net.gitpet.petserver.service.trade.transaction.spi.UserPetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class PetTrade implements Trade<TradeUserPetDTO>{

    private final UserPetRepository USER_PET_REPOSITORY;

    @Override
    public TradeUserPetDTO transaction(long petId, long buyerId) {
        TradeUserPetDTO userPet = USER_PET_REPOSITORY.getUserPet(petId);
        userPet.setOwnerId(buyerId);
        USER_PET_REPOSITORY.updatePet(userPet);
        return userPet;
    }

    @Autowired
    private PetTrade(UserPetRepository userPetRepository){
        USER_PET_REPOSITORY = userPetRepository;
    }

}

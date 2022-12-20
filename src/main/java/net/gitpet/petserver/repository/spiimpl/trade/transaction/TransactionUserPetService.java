package net.gitpet.petserver.repository.spiimpl.trade.transaction;

import net.gitpet.petserver.domain.User;
import net.gitpet.petserver.domain.UserPet;
import net.gitpet.petserver.repository.UserJpaRepository;
import net.gitpet.petserver.repository.UserPetJpaRepository;
import net.gitpet.petserver.service.trade.transaction.dto.TradeUserPetDTO;
import net.gitpet.petserver.service.trade.transaction.spi.UserPetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionUserPetService implements UserPetRepository {

    private final UserPetJpaRepository USER_PET_REPOSITORY;
    private final UserJpaRepository USER_REPOSITORY;

    @Override
    @Transactional(readOnly = true)
    public TradeUserPetDTO getUserPet(long id) {
        UserPet userPet = USER_PET_REPOSITORY.findById(id)
                .orElseThrow(()-> new IllegalStateException("Can not find user pet \"" + id + "\""));
        return new TradeUserPetDTO(userPet.getUpid(), userPet.getOwner().getUid());
    }

    @Override
    @Transactional
    public void updatePet(TradeUserPetDTO userPetDTO) {
        UserPet userPet = USER_PET_REPOSITORY.findById(userPetDTO.getID())
                .orElseThrow(()-> new IllegalStateException("Can not find user pet \"" + userPetDTO.getID() + "\""));
        User user = USER_REPOSITORY.findById(userPetDTO.getOwnerId())
                .orElseThrow(()-> new IllegalStateException("Can not find user pet \"" + userPetDTO.getOwnerId() + "\""));
        userPet.setOwner(user);
    }

    @Autowired
    TransactionUserPetService(UserPetJpaRepository userPetJpaRepository,
                              UserJpaRepository userJPARepository){
        USER_PET_REPOSITORY = userPetJpaRepository;
        USER_REPOSITORY = userJPARepository;
    }

}

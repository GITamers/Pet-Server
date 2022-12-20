package net.gitpet.petserver.repository.spiimpl.userpet.crud;

import lombok.RequiredArgsConstructor;
import net.gitpet.petserver.domain.User;
import net.gitpet.petserver.domain.UserPet;
import net.gitpet.petserver.repository.UserJpaRepository;
import net.gitpet.petserver.repository.UserPetJpaRepository;
import net.gitpet.petserver.repository.spiimpl.userpet.crud.mapper.UserPetMapper;
import net.gitpet.petserver.service.userpet.crud.dto.UserPetDTO;
import net.gitpet.petserver.service.userpet.crud.spi.UserPetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CRUDUserPetService implements UserPetRepository {

    private final UserPetJpaRepository USER_PET_REPOSITORY;
    private final UserJpaRepository USER_REPOSITORY;
    private final UserPetMapper USER_PET_MAPPER;

    @Override
    public List<UserPetDTO> findAllByOwner(Long userId) {
        User user = USER_REPOSITORY.findById(userId).get();
        List<UserPet> petList = USER_PET_REPOSITORY.findAllByOwner(user);
        return USER_PET_MAPPER.toDtoList(petList);
    }

    @Override
    public UserPetDTO findUserPetByPetId(Long petId) {
        UserPet userPet = USER_PET_REPOSITORY.findById(petId).get();
        return USER_PET_MAPPER.toDto(userPet);
    }

    @Override
    public void updateMainPet(Long userId, Long petId) {
        User user = USER_REPOSITORY.findById(userId).get();
        UserPet prevMainPet = USER_PET_REPOSITORY.findUserPetsByOwnerAndMainPetIsTrue(user);
        prevMainPet.setMainPet(false);
        USER_PET_REPOSITORY.save(prevMainPet);

        UserPet mainPet = USER_PET_REPOSITORY.findById(petId).get();
        mainPet.setMainPet(true);
        USER_PET_REPOSITORY.save(mainPet);
    }
}

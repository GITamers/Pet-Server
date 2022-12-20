package net.gitpet.petserver.service.userpet.crud;

import lombok.RequiredArgsConstructor;
import net.gitpet.petserver.service.userpet.crud.dto.UserPetDTO;
import net.gitpet.petserver.service.userpet.crud.spi.UserPetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DefaultUserPetCRUD implements UserPetCRUD {

    private final UserPetRepository USER_PET_REPOSITORY;

    @Override
    public List<UserPetDTO> getUserPetList(Long userId) {
        return USER_PET_REPOSITORY.findAllByOwner(userId);
    }

    @Override
    public UserPetDTO getUserPetDetail(Long petId) {
        return USER_PET_REPOSITORY.findUserPetByPetId(petId);
    }

    @Override
    public void updateMainPet(Long userId, Long petId) {
        USER_PET_REPOSITORY.updateMainPet(userId, petId);
    }
}

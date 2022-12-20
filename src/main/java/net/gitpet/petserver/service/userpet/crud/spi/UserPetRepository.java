package net.gitpet.petserver.service.userpet.crud.spi;

import net.gitpet.petserver.service.userpet.crud.dto.UserPetDTO;

import java.util.List;

public interface UserPetRepository {
    List<UserPetDTO> findAllByOwner(Long userId);
    UserPetDTO findUserPetByPetId(Long petId);
    void updateMainPet(Long userId, Long petId);
}

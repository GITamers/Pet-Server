package net.gitpet.petserver.repository.spiimpl.userpet.crud.mapper;

import net.gitpet.petserver.domain.UserPet;
import net.gitpet.petserver.service.userpet.crud.dto.UserPetDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPetMapper extends GenericMapper<UserPetDTO, UserPet> {
}


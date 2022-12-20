package net.gitpet.petserver.controller.userpet;

import lombok.RequiredArgsConstructor;
import net.gitpet.petserver.service.userpet.crud.UserPetCRUD;
import net.gitpet.petserver.service.userpet.crud.dto.UserPetDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pet")
public class UserPetController {

    private final UserPetCRUD DEFAULT_USER_PET_CRUD;

    @GetMapping("/list/{userId}")
    public ResponseEntity<?> getUserPetList(@PathVariable("userId") Long userId) {
        List<UserPetDTO> userPetDtoList = DEFAULT_USER_PET_CRUD.getUserPetList(userId);
        return new ResponseEntity<>(userPetDtoList, HttpStatus.OK);
    }

    @GetMapping("/detail/{userId}")
    public ResponseEntity<?> getUserPetDetail(@PathVariable("userId") Long userId, @RequestParam(value = "petId") Long petId) {
        UserPetDTO userPetDto = DEFAULT_USER_PET_CRUD.getUserPetDetail(petId);
        return new ResponseEntity<>(userPetDto, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateMainPet(@PathVariable("userId") Long userId, @RequestParam(value = "petId") Long petId) {
        DEFAULT_USER_PET_CRUD.updateMainPet(userId, petId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
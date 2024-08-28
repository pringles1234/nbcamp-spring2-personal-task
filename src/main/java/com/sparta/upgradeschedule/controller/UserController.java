package com.sparta.upgradeschedule.controller;

import com.sparta.upgradeschedule.dto.request.CreateUserRequestDto;
import com.sparta.upgradeschedule.dto.response.CreateUserResponseDto;
import com.sparta.upgradeschedule.dto.response.DeleteUserResponseDto;
import com.sparta.upgradeschedule.dto.response.GetUserResponseDto;
import com.sparta.upgradeschedule.dto.response.GetUsersResponseDto;
import com.sparta.upgradeschedule.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<CreateUserResponseDto> createUser(@RequestBody CreateUserRequestDto createUserRequestDto){
        return ResponseEntity.ok(userService.createUser(createUserRequestDto));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<GetUserResponseDto> getUser(@PathVariable(value = "userId") Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/users")
    public ResponseEntity<List<GetUsersResponseDto>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<DeleteUserResponseDto> deleteUser(@PathVariable(value = "userId")Long userId){
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

}

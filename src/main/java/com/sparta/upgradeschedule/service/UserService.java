package com.sparta.upgradeschedule.service;

import com.sparta.upgradeschedule.dto.request.CreateUserRequestDto;
import com.sparta.upgradeschedule.dto.response.*;
import com.sparta.upgradeschedule.entity.User;
import com.sparta.upgradeschedule.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 유저 등록
    public CreateUserResponseDto createUser(CreateUserRequestDto createUserRequestDto) {
        // createUserRequestDto -> Entity
        User user = new User(createUserRequestDto);

        // DB 저장
        User saveUser = userRepository.save(user);

        //Entity -> CreateUserResponseDto
        CreateUserResponseDto createUserResponseDto = new CreateUserResponseDto(saveUser);

        return createUserResponseDto;
    }

    // 유저 단건 조회
    public GetUserResponseDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NullPointerException("멤버가 없습니다."));
        GetUserResponseDto getUserResponseDto = new GetUserResponseDto(user);
        return getUserResponseDto;
    }

    // 유저 전체 조회
    public List<GetUsersResponseDto> getUsers() {
        List<User> userList = userRepository.findAll();

        List<GetUsersResponseDto> userDtoList = new ArrayList<>();
        for(User user : userList){
            GetUsersResponseDto getUsersResponseDto = new GetUsersResponseDto(user);
            userDtoList.add(getUsersResponseDto);
        }
        return userDtoList;
    }

    // 유저 삭제
    public DeleteUserResponseDto deleteUser(Long userId) {
        try {
            userRepository.deleteById(userId);
            return new DeleteUserResponseDto(userId);
        }
        catch(Exception e) {
            throw new IllegalArgumentException("존재하지 않는 유저입니다.");
        }
    }

}

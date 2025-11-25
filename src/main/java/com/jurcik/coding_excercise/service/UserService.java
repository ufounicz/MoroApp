package com.jurcik.coding_excercise.service;

import com.jurcik.coding_excercise.dto.*;
import com.jurcik.coding_excercise.jooq.tables.records.UsersRecord;
import com.jurcik.coding_excercise.repository.UserRepository;
import com.jurcik.coding_excercise.util.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
        UsersRecord createdUser = userRepository.createUser(createUserRequest.name());
        return new CreateUserResponse(createdUser.getId(), createdUser.getName());
    }


    public GetUserResponse getUser(UUID userId) {
        Optional<UsersRecord> user = userRepository.getUser(userId);

        if (user.isEmpty()) {
            throw new UserNotFoundException(userId);
        }

        UsersRecord record = user.get();
        return new GetUserResponse(record.getId(), record.getName());
    }

    public void modifyUser(UUID userId, ModifyUserRequest modifyUserRequest) {
        userRepository.modifyName(userId, modifyUserRequest.name());
    }


    public void deleteUser(UUID userId) {
        userRepository.deleteUser(userId);
    }

    public ListUsersResponse listUsers() {
        UsersRecord[] users = userRepository.listUsers();

        Map<UUID, String> userMap = Arrays.stream(users).collect(Collectors.toMap(UsersRecord::getId, UsersRecord::getName));
        return new ListUsersResponse(userMap);
    }
}

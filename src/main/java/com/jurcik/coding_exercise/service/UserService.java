package com.jurcik.coding_exercise.service;

import com.jurcik.coding_exercise.PermissionValidator;
import com.jurcik.coding_exercise.dto.*;
import com.jurcik.coding_exercise.jooq.tables.records.UsersRecord;
import com.jurcik.coding_exercise.repository.UserRepository;
import com.jurcik.coding_exercise.util.ApiException;
import com.jurcik.coding_exercise.util.PermissionDeniedException;
import com.jurcik.coding_exercise.util.PermissionLevel;
import com.jurcik.coding_exercise.util.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
        String encodedPassword = passwordEncoder.encode(createUserRequest.password());

        if (userRepository.userExists(createUserRequest.username())) {
            throw new ApiException("User already exists!", HttpStatus.CONFLICT);
        }

        UsersRecord createdUser = userRepository.createUser(createUserRequest.name(), createUserRequest.username(), encodedPassword);
        return new CreateUserResponse(createdUser.getId(), createdUser.getName(), createdUser.getUsername());
    }

    public GetUserResponse getUser(UUID userId) {
        Optional<UsersRecord> optionalUsersRecord = userRepository.getUser(userId);

        if (optionalUsersRecord.isEmpty()) {
            throw new UserNotFoundException(userId.toString());
        }

        PermissionLevel permissionLevel = PermissionValidator.checkUserAccess(optionalUsersRecord.get().getUsername());

        if (!PermissionLevel.ENABLED.equals(permissionLevel)) {
            throw new PermissionDeniedException("Incorrect user!");
        }

        UsersRecord record = optionalUsersRecord.get();
        return new GetUserResponse(new User(record.getId(), record.getName(), record.getUsername()));
    }

    public void modifyUser(UUID userId, ModifyUserRequest modifyUserRequest) {
        Optional<UsersRecord> optionalUsersRecord = userRepository.getUser(userId);

        if (optionalUsersRecord.isEmpty()) {
            throw new UserNotFoundException(userId.toString());
        }

        PermissionLevel permissionLevel = PermissionValidator.checkUserAccess(optionalUsersRecord.get().getUsername());

        if (!PermissionLevel.ENABLED.equals(permissionLevel)) {
            throw new PermissionDeniedException("Incorrect user!");
        }

        if (modifyUserRequest.username() != null) {
            if (userRepository.userExists(modifyUserRequest.username())) {
                throw new ApiException("User already exists!", HttpStatus.CONFLICT);
            }
            userRepository.modifyUsername(userId, modifyUserRequest.username());
        }
        if (modifyUserRequest.name() != null) {
            userRepository.modifyName(userId, modifyUserRequest.name());
        }
        if (modifyUserRequest.password() != null) {
            userRepository.modifyPassword(userId, passwordEncoder.encode(modifyUserRequest.password()));
        }
    }

    public void deleteUser(UUID userId) {
        Optional<UsersRecord> optionalUsersRecord = userRepository.getUser(userId);

        if (optionalUsersRecord.isEmpty()) {
            throw new UserNotFoundException(userId.toString());
        }

        PermissionLevel permissionLevel = PermissionValidator.checkUserAccess(optionalUsersRecord.get().getUsername());

        if (!PermissionLevel.ENABLED.equals(permissionLevel)) {
            throw new PermissionDeniedException("Incorrect user!");
        }

        userRepository.deleteUser(userId);
    }

    public ListUsersResponse listUsers() {
        UsersRecord[] users = userRepository.listUsers();

        List<User> userList = Arrays.stream(users).map(e -> new User(e.getId(), e.getName(), e.getUsername())).toList();
        return new ListUsersResponse(userList);
    }
}

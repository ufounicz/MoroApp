package com.jurcik.coding_excercise.controller;

import com.jurcik.coding_excercise.dto.*;
import com.jurcik.coding_excercise.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        CreateUserResponse user = userService.createUser(createUserRequest);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GetUserResponse> getUser(@NotNull @PathVariable UUID userId) {
        GetUserResponse user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<ListUsersResponse> listUsers() {
        ListUsersResponse userList = userService.listUsers();
        return ResponseEntity.ok(userList);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> modifyUser(@NotNull @PathVariable UUID userId, @Valid @RequestBody ModifyUserRequest modifyUserRequest) {
        userService.modifyUser(userId, modifyUserRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@NotNull @PathVariable UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}

package ca.gbc.user.controller;

import ca.gbc.user.dto.LoginRequest;
import ca.gbc.user.dto.UserRequest;
import ca.gbc.user.dto.UserResponse;
import ca.gbc.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
    }

    @PostMapping("/loginUser")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse loginUser(@RequestBody LoginRequest userLogin) {
        return userService.loginUser(userLogin.getUsername(), userLogin.getPassword());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        String updatedUserId = userService.updateUser(id, userRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/users/" + updatedUserId);

        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }
}

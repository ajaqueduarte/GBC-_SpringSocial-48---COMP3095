package ca.gbc.user.service;

import ca.gbc.user.dto.UserRequest;
import ca.gbc.user.dto.UserResponse;
import ca.gbc.user.model.User;
import ca.gbc.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void createUser(UserRequest userRequest) {
        log.info("Creating a new user {}", userRequest.getUsername());

        User user = User.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();

        userRepository.save(user);
        log.info("User {} is saved", user.getId());
    }

    @Override
    public String updateUser(Long userId, UserRequest userRequest) {
        log.info("Updating a user with Id {}", userId);

        User query = User.builder().id(userId).build();
        Optional<User> user = userRepository.findOne(Example.of(query));

        if (user.isPresent()) {
            user.get().setUsername(userRequest.getUsername());
            user.get().setEmail(userRequest.getEmail());
            user.get().setPassword(userRequest.getPassword());

            log.info("User {} is updated", user.get().getId());

            return String.valueOf(userRepository.save(user.get()).getId());
        }

        return "";
    }

    @Override
    public UserResponse loginUser(String username, String password) {
        User query = User.builder().username(username).password(password).build();
        Optional<User> user = userRepository.findOne(Example.of(query));

        return user.map(this::mapToUserResponse).orElse(null);
    }

    @Override
    public void deleteUser(Long userId) {
        log.info("User {} is deleted", userId);
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        log.info("Returning a list of users");
        List<User> users = userRepository.findAll();

        return users.stream().map(this::mapToUserResponse).collect(Collectors.toList());
    }


    @Override
    public UserResponse getUserById(Long userId) {
        log.info("Fetching user with Id {}", userId);

        User query = User.builder().id(userId).build();
        Optional<User> user = userRepository.findOne(Example.of(query));

        if (user.isPresent()) {
            return mapToUserResponse(user.get());
        }

        log.warn("User with Id {} not found", userId);
        return null;  // Or you can throw a custom exception
    }

    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }


}

package ca.gbc.friendship.controller;

import ca.gbc.friendship.dto.FriendshipRequest;
import ca.gbc.friendship.dto.FriendshipResponse;
import ca.gbc.friendship.service.FriendshipImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/friendship")
@RequiredArgsConstructor
public class FriendshipController {
    private final FriendshipImpl friendship; // Service responsible for friendship operations

    // GET request to retrieve friend list of a specific user
    @GetMapping({ "/{userId}" })
    @ResponseStatus(HttpStatus.OK)
    public FriendshipResponse getFriendList(@PathVariable("userId") Long userId) {
        return friendship.getFriendList(userId);
    }

    // POST request to add a friend
    @PostMapping({"/addFriend"})
    @ResponseStatus(HttpStatus.OK)
    public void addFriend(@RequestBody FriendshipRequest friendshipRequest) {
        friendship.addFriend(friendshipRequest);
    }

    // POST request to delete a friend
    @PostMapping({"/deleteFriend"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteFriend(@RequestBody FriendshipRequest friendshipRequest) {
        friendship.deleteFriend(friendshipRequest);
    }
}
package ca.gbc.friendship.controller;

import ca.gbc.friendship.dto.FriendshipRequest;
import ca.gbc.friendship.service.FriendshipImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/friendship")
@RequiredArgsConstructor
public class FriendshipController {
    private final FriendshipImpl friendship;

    @PostMapping({"/addFriend"})
    @ResponseStatus(HttpStatus.OK)
    public void addFriend (FriendshipRequest friendshipRequest) {
        friendship.addFriend(friendshipRequest);
    }

    @PostMapping({"/deleteFriend"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteFriend (FriendshipRequest friendshipRequest) {
        friendship.deleteFriend(friendshipRequest);
    }

}

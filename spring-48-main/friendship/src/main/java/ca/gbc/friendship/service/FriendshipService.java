package ca.gbc.friendship.service;

import ca.gbc.friendship.dto.FriendshipRequest;

public interface FriendshipService {
    void addFriend(FriendshipRequest friendshipRequest);

    void deleteFriend(FriendshipRequest friendshipRequest);
}

package ca.gbc.friendship.service;

import ca.gbc.friendship.dto.FriendshipRequest;
import ca.gbc.friendship.dto.FriendshipResponse;

public interface FriendshipService {
    void addFriend(FriendshipRequest friendshipRequest);
    FriendshipResponse getFriendList(Long userId);

    void deleteFriend(FriendshipRequest friendshipRequest);
}

package ca.gbc.friendship.service;

import ca.gbc.friendship.dto.FriendshipRequest;
import ca.gbc.friendship.model.Friendship;
import ca.gbc.friendship.repository.FriendshipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class FriendshipImpl implements FriendshipService {
    private final FriendshipRepository friendshipRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public void addFriend(FriendshipRequest friendshipRequest) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(friendshipRequest.getUserId()));
        Friendship fs = mongoTemplate.findOne(query, Friendship.class);

        if (fs == null) {
            return;
        }

        fs.getFriends().add(friendshipRequest.getOtherUserId());
        mongoTemplate.save(fs);
    }

    @Override
    public void deleteFriend(FriendshipRequest friendshipRequest) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(friendshipRequest.getUserId()));
        Friendship fs = mongoTemplate.findOne(query, Friendship.class);

        if (fs == null) {
            return;
        }

        fs.getFriends().removeIf(x -> Objects.equals(x, friendshipRequest.getOtherUserId()));
    }
}

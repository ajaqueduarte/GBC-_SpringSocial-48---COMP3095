package ca.gbc.friendship.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value="friendship")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Friendship {
    @Id
    private String id;
    private Long userId;
    private List<Long> friends;
}

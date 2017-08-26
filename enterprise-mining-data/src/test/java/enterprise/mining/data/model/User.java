package enterprise.mining.data.model;

import org.neo4j.ogm.annotation.*;

import java.util.Set;

/**
 * Created by  yuananyun on 2017/8/13.
 */
@NodeEntity(label="User")
public class User {

    @Index(unique = true)
    private String userId;

    /**
     * Neo4j节点编号
     */
    @GraphId
    private Long nodeId;

    private String name;

    @Relationship(type = "IS_FRIEND_OF", direction = Relationship.UNDIRECTED)
    private Set<User> friends;

    @Relationship(direction = Relationship.OUTGOING)
    private Set<Viewing> views;

    private User referredBy;


    public User() {
    }

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<Viewing> getViews() {
        return views;
    }

    public void setViews(Set<Viewing> views) {
        this.views = views;
    }

    public User getReferredBy() {
        return referredBy;
    }

    public void setReferredBy(User referredBy) {
        this.referredBy = referredBy;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }
}

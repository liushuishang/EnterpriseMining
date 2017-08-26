package enterprise.mining.data.model;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by  yuananyun on 2017/8/13.
 */
@RelationshipEntity(type = "HAS_SEEN")
public class Viewing {

    @GraphId
    private Long relationshipId;
    @StartNode
    private User user;
    @EndNode
    private Movie movie;

    private Integer stars;

    public Viewing() {
    }

    public Viewing(User user, Movie movie, Integer stars) {
        this.user = user;
        this.movie = movie;
        this.stars = stars;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Long relationshipId) {
        this.relationshipId = relationshipId;
    }
}

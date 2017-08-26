package enterprise.mining.data.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by  yuananyun on 2017/8/13.
 */
@NodeEntity(label = "Movie")
public class Movie {

    @GraphId
    private Long nodeId;

    private String title;

    @Relationship(direction = Relationship.INCOMING)
    private Iterable<Viewing> views;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Iterable<Viewing> getViews() {
        return views;
    }

    public Movie() {
    }

    public Movie(String title) {
        this.title = title;
    }

    public void setViews(Iterable<Viewing> views) {
        this.views = views;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }
}

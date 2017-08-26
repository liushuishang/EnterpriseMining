package enterprise.mining.data.model.node;

import org.neo4j.ogm.annotation.NodeEntity;

/**
 * 个人
 * Created by  yuananyun on 2017/8/26.
 */
@NodeEntity(label = "Person")
public class Person extends EntityNode {

    public Person() {
    }

    public Person(String id, String name, String ntype) {
        super(id, name, ntype);
    }

    @Override
    public String getNodeType() {
        return "Person";
    }
}

package enterprise.mining.data.model.node;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * 投资者
 * Created by  yuananyun on 2017/8/26.
 */
@NodeEntity(label = "Investor")
public class investor {
    @GraphId
    private Long id;
    private String name;
    private String companyId;
    private double amount;
    private int type;

    public investor(Long id, String name, String companyId, double amount, int type) {
        this.id = id;
        this.name = name;
        this.companyId = companyId;
        this.amount = amount;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

package enterprise.mining.data.model.relationship;

/**
 * Created by  yuananyun on 2017/8/26.
 */
public enum ERelationshipType {
    OWN("OWN", "法人"),
    INVEST_C("INVEST_C","参股"),
    STAFF("SERVE", "任职")
    ;

    private String type;
    private String label;

    ERelationshipType(String type, String label) {
        this.type = type;
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }

}

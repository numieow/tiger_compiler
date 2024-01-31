package symtab;

public class recordField {
    
    private String name;

    private BasicType type;

    public recordField(String name, BasicType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public BasicType getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(BasicType type) {
        this.type = type;
    }

    public String toString() {
        return name + " : " + type.getName();
    }

}

package symtab;

//import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class recordType implements Type {
    private Map<String, Type> fields;
    private int size;
    private String name;

    public recordType(String name) {
        this.fields = new LinkedHashMap<>();
        this.name = name;
    }
    
    public void addField(String name, Type type) throws IllegalArgumentException{
        if (fields.containsKey(name)) {
            throw new IllegalArgumentException("Field " + name + " already exists in record " + this.name);
        } else {
            fields.put(name, type);
        }
        
    }
    public int getSize() {
        return size;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        String res = "record " + name + " {";
        for (Map.Entry<String, Type> entry : fields.entrySet()) {
            res += entry.getKey() + " : " + entry.getValue().getName() + "; ";
        }
        res = res.substring(res.length() - 2);
        res += "}";
        return res;
    }

}


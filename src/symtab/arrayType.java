package symtab;

public class arrayType  implements Type{

    private int size; //Là je sais pas exactement ce qu'on peut faire de size et getSize
    private String name;
    //Pour un type on n'a pas la taille de l'array, ça va servir pour les varibales d'array mdr
    private BasicType type;

    public arrayType(BasicType type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return this.name;
    }

    public BasicType getType() {
        return type;
    }

    public void setType(BasicType type) {
        this.type = type;
    }

    public String toString() {
        return "array of " + type.getName();
    }


}


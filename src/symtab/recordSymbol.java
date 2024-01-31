package symtab;

import java.util.ArrayList;

public class recordSymbol implements Symbol {
    
    private String name;

    private BasicBloc bloc;

    private recordType type;

    /**
     * Contient tous les champs présents dans le record
     */
    private ArrayList<recordField> fields = new ArrayList<recordField>();

    private ArrayList<String> values = new ArrayList<String>();

    public recordSymbol(String name, BasicBloc bloc) {
        this.name = name;
        this.bloc = bloc;
    }

        @Override
        public int getOffset(){
        //TODO : faire la fonction mdr
            return 0;
        }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public BasicBloc getBloc() {
        return bloc;
    }

    @Override
    public void setBloc(BasicBloc bloc) {
        this.bloc = bloc;
    }

    /**
     * Ajoute un champ au record
     * @param field le champ à ajouter
     */
    public void addField(recordField field) {
        fields.add(field);
    }

    public ArrayList<recordField> getFields() {
        return fields;
    }

    /**
     * Retourne le champ dont le nom est passé en paramètre
     * @param name le nom du champ
     * @return le champ dont le nom est passé en paramètre, ou null si le champ n'existe pas
     */
    public recordField getField(String name) {
        for (recordField field : fields) {
            if (field.getName().equals(name)) {
                return field;
            }
        }
        return null;
    }

    public recordType getType() {
        return type;
    }

    public void setType(recordType type) {
        this.type = type;
    }

    /**
     * Ajoute une valeur au record
     * @param value la valeur à ajouter
     */
    public void addValue(String value) {
        values.add(value);

    }

    public ArrayList<String> getValues() {
        return values;
    }

    public String toString() {
        String result = "record " + name + " := "+ type.getName() +" {";
        int index;
        
        for (index = 0; index < values.size() - 1; index++) {
            result += values.get(index) + ", ";
        }
        result += values.get(index) + "}";
        return result;
    }


}

package symtab;

import java.util.ArrayList;
public class arraySymbol implements Symbol {
    
    private String name;
    private BasicBloc bloc;
    private String size;
    private Type type;
    private ArrayList<String> values = new ArrayList<String>();
    private int offset;
    private String valeurInitiale;
    private Type typeDesElements;
    
    public arraySymbol(String name, BasicBloc bloc, String size, Type type) {
        this.name = name;
        this.bloc = bloc;
        this.size = size;
        this.type = type;
        this.valeurInitiale = "";
        this.typeDesElements = null;
    }

    public arraySymbol(String name, BasicBloc bloc, String size, Type type, String valeurInitiale, Type typeEle) {
        this.name = name;
        this.bloc = bloc;
        this.size = size;
        this.type = type;
        this.valeurInitiale = valeurInitiale;
        this.typeDesElements = typeEle;
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
    
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    
    public Type getType() {
        return type;
    }

    public void addValue(String value) {
        this.values.add(value);
    }

    public void setValues(ArrayList<String> values) {
        this.values = values;
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

    public String toString() {
        if(valeurInitiale == "" && typeDesElements == null)
            return name + "[" + size + "]" + " : " + type.getName();
        else{
            return name + "[" + size + "]" + " : " + type.getName() + " ( array of " + typeDesElements.getName() + " )" + " of" + valeurInitiale;
        }
        
    }

    public void setValeurInitiale(String valeurInitiale) {
        this.valeurInitiale = valeurInitiale;
    }

    public String getValeurInitiale() {
        return valeurInitiale;
    }

    public void setTypeDesElements(Type typeDesElements) {
        this.typeDesElements = typeDesElements;
    }

    public Type getTypeDesElements() {
        return typeDesElements;
    }


}

package symtab;

public class BlocGlobal extends BasicBloc {


    public BlocGlobal(BasicBloc parent) {
        super(parent);
        this.imbrication = 1;
        this.region = 1;
    }

    
    public String getName() {
        return "global";
    }

    @Override
    public BasicBloc getBlocEnglobant() {
        return null;
    }
    public int getImbrication(){
        return this.imbrication;
    }
}

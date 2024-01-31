package symtab;

public class whileBloc extends BasicBloc {

    private String cond;

    public whileBloc(BasicBloc parent, String cond) {
        super(parent);
        this.cond = cond;
    }

    public String getName() {
        return "boucle while du bloc " + getBlocEnglobant().getName();
    }

    public String getCond() {
        return cond;
    }

    public void setCond(String s) {
        cond = s;
    }
}

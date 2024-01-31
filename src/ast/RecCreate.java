package ast;

import java.util.Optional;

public class RecCreate implements Ast {

    public <T> T accept(AstVisitor<T> visitor){
        return visitor.visit(this);
    }

    public Ast type_id;
    public Optional<Ast> field_list;

    public RecCreate(Ast type_id, Ast field_list) {
        this.type_id = type_id;
        this.field_list = Optional.of(field_list);
    }

    public RecCreate(Ast type_id) {
        this.type_id = type_id;
        this.field_list = Optional.empty();
    }
}

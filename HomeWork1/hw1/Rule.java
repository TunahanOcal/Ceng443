package hw1;

public interface Rule {
    default void clear(){
        this.getPremise().accept(new ClearVarsVisitor());
        this.getEntails().accept(new ClearVarsVisitor());
    }

    default boolean apply(MathExpression me){
        this.clear();
        if(this.getPremise().match(me)){
            return true;
        }
        this.clear();
        return false;
    }

    MathExpression getPremise();

    MathExpression getEntails();

    MathExpression entails(MathExpression me);
}

package hw1;

public class Sym implements MathExpression {
    private String value;

    public String getValue() {
        return this.value;
    }

    public Sym(String value) {
        this.value = value;
    }

    public <T> T accept(MathVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public boolean match(MathExpression me) {
        if(me instanceof Sym && ((Sym) me).value.equals(this.value)){
            return true;
        }else{
            return false;
        }
    }
}

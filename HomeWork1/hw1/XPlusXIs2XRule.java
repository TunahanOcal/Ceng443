package hw1;

public class XPlusXIs2XRule implements Rule {
    private Var x;

    public XPlusXIs2XRule(Var x) {
        this.x = x;
    }

    public Var getX() {
        return this.x;
    }

    public MathExpression getPremise() {
        Op op = new Op("+",this.x,this.x);
        return op;
    }

    public MathExpression getEntails() {
        Op op = new Op("*",new Num(2),this.x);
        return op;
    }

    public MathExpression entails(MathExpression me) {
        apply(me);
        return getEntails();
    }
}
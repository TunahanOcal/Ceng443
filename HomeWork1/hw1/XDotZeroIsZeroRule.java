package hw1;

public class XDotZeroIsZeroRule implements Rule {
    private Var x;

    public XDotZeroIsZeroRule(Var x) {
        this.x = x;
    }

    public Var getX() {
        return this.x;
    }


    public MathExpression getPremise() {
        Op op = new Op("*",this.x,new Num(0));
        return op;
    }

    public MathExpression getEntails() {
        return new Num(0);
    }

    public MathExpression entails(MathExpression me) {
        apply(me);
        return getEntails();
    }
}

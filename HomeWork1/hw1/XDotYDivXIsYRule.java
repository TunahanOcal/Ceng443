package hw1;

public class XDotYDivXIsYRule implements Rule {
    private Var x;
    private Var y;

    public XDotYDivXIsYRule(Var x, Var y) {
        this.x = x;
        this.y = y;
    }

    public Var getX() {
        return this.x;
    }

    public Var getY() {
        return this.y;
    }

    public MathExpression getPremise() {
        Op op = new Op("/",new Op("*",this.x,this.y),this.x);
        return op;
    }

    public MathExpression getEntails() {
        return this.y;
    }

    public MathExpression entails(MathExpression me) {
        this.apply(me);
        return this.getEntails();
    }
}

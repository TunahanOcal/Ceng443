package hw1;

public class EquationText implements DocElement {
    private MathExpression innerMath;

    public <T> T accept(TextVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public EquationText(MathExpression innerMath) {
        this.innerMath = innerMath;
    }

    public MathExpression getInnerMath() {
        return this.innerMath;
    }
}
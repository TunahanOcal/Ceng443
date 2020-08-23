package hw1;

public class CountAtomicsVisitor implements MathVisitor<Integer> {

    public Integer visit(Op op) {

        int count = 0;
        count += (int) op.getFirst().accept(new CountAtomicsVisitor());
        count += (int) op.getSecond().accept(new CountAtomicsVisitor());
        return count ;
    }

    public Integer visit(Num num) {
        return 1;
    }

    public Integer visit(Sym sym) {
        return 1;
    }

    public Integer visit(Var var) {
        return 1;
    }
}
package hw1;

public class PrintMathMLVisitor implements MathVisitor<String> {


    public String visit(Op op) {
        String text;
        if (op.getOperand().equals("/")) {

            return "<mrow><mfrac>" + op.getFirst().accept(new PrintMathMLVisitor()) +  op.getSecond().accept(new PrintMathMLVisitor()) + "</mfrac></mrow>";
        } else {
            text = "<mrow><mo>(</mo>"+ 
                op.getFirst().accept(new PrintMathMLVisitor()) +
                "<mo>";
            if(op.getOperand().equals("+"))
                text += "+";
            else if(op.getOperand().equals("*"))
                text += "&times;" ;
            else if(op.getOperand().equals("|-"))
                text += "&vdash;";
            text += "</mo>" +
            op.getSecond().accept(new PrintMathMLVisitor()) +
                "<mo>)</mo></mrow>";

            return text;
        }
    }

    public String visit(Num num) {
        return "<mrow><mn>" + num.getValue() + "</mn></mrow>";
    }

    public String visit(Sym sym) {
        return "<mrow><mi>" + sym.getValue() + "</mi></mrow>";
    }

    public String visit(Var var) {
        if (var.getPreviousMatch() == null) {
            return "<mrow><msub><mi>V</mi><mn>" + var.getId() + "</mn></msub></mrow>";
        } else {

            return "<mrow><msub><mi>V</mi><mn>" + var.getId() + "</mn></msub><mo>[</mo>" + var.getPreviousMatch().accept(new PrintMathMLVisitor()) + "<mo>]</mo></mrow>";
        }
    }
}
package hw1;

public class PrintHTMLVisitor implements TextVisitor<String> {

    public String visit(Document document) {
        String elements = "";

        for(int i = 0; i < document.getElements().size(); ++i) {
            elements = elements + (String)((DocElement)document.getElements().get(i)).accept(new PrintHTMLVisitor());
        }

        return "<html><head><title>" + document.getTitle() + "</title></head><body>" + elements + "</body></html>";
    }

    public String visit(EquationText var1) {

        return "<math>" +var1.getInnerMath().accept(new PrintMathMLVisitor())+ "</math>";
    }

    public String visit(Paragraph var1) {
        return "<p>" + var1.getText() + "</p>";
    }
}
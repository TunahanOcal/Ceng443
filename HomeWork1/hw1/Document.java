package hw1;

import java.util.ArrayList;

public class Document implements DocElement {
    private String title;
    private ArrayList<DocElement> elements = new ArrayList<DocElement>();

    public Document(String title) {
        this.title = title;
    }

    public <T> T accept(TextVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public ArrayList<DocElement> getElements() {
        return this.elements;
    }

    public void setElements(ArrayList<DocElement> elements) {
        this.elements = elements;
    }

    public void add(DocElement de) {
        this.elements.add(de);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

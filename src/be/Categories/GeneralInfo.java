package be.Categories;

public class GeneralInfo {
    private final String name;
    private String text;

    public GeneralInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

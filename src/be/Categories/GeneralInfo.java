package be.Categories;

public class GeneralInfo {
    private final String name;
    private final String description;
    private String text;

    public GeneralInfo(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
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

package ei.project.pattern.iterator;

// Represents a single sight
public class Sight {
    private String name;
    private String description;

    public Sight(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return name + " - " + description;
    }
}
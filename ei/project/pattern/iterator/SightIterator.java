package ei.project.pattern.iterator;

public interface SightIterator {
    boolean hasNext();
    Sight next();
    boolean hasPrevious();
    Sight previous();
}

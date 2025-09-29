package ei.project.pattern.iterator;

import java.util.List;

// Virtual Guide Iterator (sequential)
public class VirtualGuideIterator implements SightIterator {
    private List<Sight> sights;
    private int currentIndex = 0;

    public VirtualGuideIterator(List<Sight> sights) {
        this.sights = sights;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < sights.size();
    }

    @Override
    public Sight next() {
        if (!hasNext()) return null;
        return sights.get(currentIndex++);
    }

    @Override
    public boolean hasPrevious() {
        return currentIndex > 1;
    }

    @Override
    public Sight previous() {
        if (hasPrevious()) return sights.get(--currentIndex);
        return null;
    }
}

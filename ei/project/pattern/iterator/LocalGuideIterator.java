package ei.project.pattern.iterator;

import java.util.ArrayList;
import java.util.List;

// Local Human Guide Iterator (filters by interest)
public class LocalGuideIterator implements SightIterator {
    private List<Sight> sights;
    private List<Sight> filteredSights;
    private int currentIndex = 0;

    public LocalGuideIterator(List<Sight> sights, String interest) {
        this.sights = sights;
        filteredSights = new ArrayList<>();
        for (Sight s : sights) {
            if (s.getName().toLowerCase().contains(interest.toLowerCase())) {
                filteredSights.add(s);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return currentIndex < filteredSights.size();
    }

    @Override
    public Sight next() {
        if (!hasNext()) return null;
        return filteredSights.get(currentIndex++);
    }

    @Override
    public boolean hasPrevious() {
        return currentIndex > 1;
    }

    @Override
    public Sight previous() {
        if (hasPrevious()) return filteredSights.get(--currentIndex);
        return null;
    }
}


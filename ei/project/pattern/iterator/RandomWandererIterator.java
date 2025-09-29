package ei.project.pattern.iterator;

import java.util.List;
import java.util.Random;

// Random Wanderer Iterator
public class RandomWandererIterator implements SightIterator {
    private List<Sight> sights;
    private Random random = new Random();
    private int currentIndex = -1;

    public RandomWandererIterator(List<Sight> sights) {
        this.sights = sights;
    }

    @Override
    public boolean hasNext() {
        return !sights.isEmpty();
    }

    @Override
    public Sight next() {
        if (sights.isEmpty()) return null;
        currentIndex = random.nextInt(sights.size());
        return sights.get(currentIndex);
    }

    @Override
    public boolean hasPrevious() {
        return currentIndex >= 0;
    }

    @Override
    public Sight previous() {
        if (hasPrevious()) return sights.get(currentIndex);
        return null;
    }
}

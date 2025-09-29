package ei.project.pattern.iterator;

import java.util.ArrayList;
import java.util.List;

// Collection of sights in Madurai
public class MaduraiSights {
    private List<Sight> sights;

    public MaduraiSights() {
        sights = new ArrayList<>();
    }

    public void addSight(Sight sight) {
        sights.add(sight);
    }

    public List<Sight> getSights() {
        return sights;
    }
}

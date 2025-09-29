package ei.project.pattern.decorator;

// Abstract Decorator (package-private)
abstract class PizzaDecorator implements Pizza {
    protected Pizza tempPizza;

    public PizzaDecorator(Pizza pizza) {
        this.tempPizza = pizza;
    }

    @Override
    public String getDescription() {
        return tempPizza.getDescription();
    }

    @Override
    public double getCost() {
        return tempPizza.getCost();
    }
}

// Cheese Decorator
class CheeseDecorator extends PizzaDecorator {
    public CheeseDecorator(Pizza pizza) { super(pizza); }

    @Override
    public String getDescription() {
        return tempPizza.getDescription() + ", Cheese";
    }

    @Override
    public double getCost() {
        return tempPizza.getCost() + 50;
    }
}

// Olive Decorator
class OliveDecorator extends PizzaDecorator {
    public OliveDecorator(Pizza pizza) { super(pizza); }

    @Override
    public String getDescription() {
        return tempPizza.getDescription() + ", Olives";
    }

    @Override
    public double getCost() {
        return tempPizza.getCost() + 30;
    }
}

// Paneer Decorator
class PaneerDecorator extends PizzaDecorator {
    public PaneerDecorator(Pizza pizza) { super(pizza); }

    @Override
    public String getDescription() {
        return tempPizza.getDescription() + ", Paneer";
    }

    @Override
    public double getCost() {
        return tempPizza.getCost() + 70;
    }
}

// Chicken Decorator
class ChickenDecorator extends PizzaDecorator {
    public ChickenDecorator(Pizza pizza) { super(pizza); }

    @Override
    public String getDescription() {
        return tempPizza.getDescription() + ", Chicken";
    }

    @Override
    public double getCost() {
        return tempPizza.getCost() + 100;
    }
}

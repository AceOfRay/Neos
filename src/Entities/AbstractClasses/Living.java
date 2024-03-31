package Entities.AbstractClasses;

import java.awt.Point;

import WorldModel.World;

public abstract class Living extends Entity {
    protected int health;
    protected int hunger;

    public Living(Point pos, int health, int hunger, World world) {
        super(pos, world);
        this.health = health;
        this.hunger = hunger;

    }
    
    public void decrementHealth() {
        this.health--;
    }
    
    public void decrementHunger() {
        this.hunger--;
    }

    public int getHealth() {
        return this.health;
    }

    public int getHunger() {
        return this.hunger;
    }
}

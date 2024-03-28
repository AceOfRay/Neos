package Entities.Interfaces;

public interface Moving {
    public abstract void findTarget();

    public abstract void moveTowardsTarget();

    public abstract boolean atTarget();
}

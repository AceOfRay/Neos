package Entities.Interfaces;

import Entities.Enums.Action;

public interface Active {
    public abstract void executeAction();

    public abstract void generateNextAction();
}

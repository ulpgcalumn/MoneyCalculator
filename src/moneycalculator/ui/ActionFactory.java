package moneycalculator.ui;

public interface ActionFactory<Entity> {

    public Entity createAction(String action);
    
}
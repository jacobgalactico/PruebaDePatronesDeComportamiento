package clases;

import clases.strategy.HaltStrategy;

public class HaltChecker {
    private HaltStrategy strategy;

    public HaltChecker(HaltStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean checkHalt(Class<?> programClass) {
        return strategy.willHalt(programClass);
    }
}

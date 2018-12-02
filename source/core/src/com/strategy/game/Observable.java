package com.strategy.game;

public interface Observable {
    void addListener(EventListener eventListener);
    void removeListener(EventListener eventListener);
    void updateListeners(Events eventType);
}

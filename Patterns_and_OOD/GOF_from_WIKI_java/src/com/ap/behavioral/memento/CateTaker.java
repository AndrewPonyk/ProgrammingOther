package com.ap.behavioral.memento;

import java.util.ArrayList;
import java.util.List;

class Caretaker {
    public static void main(String[] args) {
        List<Memento> savedStates = new ArrayList<Memento>();
 
        TextEditor originator = new TextEditor();
        originator.set("State1");
        originator.set("State2");
        savedStates.add(originator.saveToMemento());
        originator.set("State3");
        // We can request multiple mementos, and choose which one to roll back to.
        savedStates.add(originator.saveToMemento());
        originator.set("State4");
 
        originator.restoreFromMemento(savedStates.get(1));   
    }
}

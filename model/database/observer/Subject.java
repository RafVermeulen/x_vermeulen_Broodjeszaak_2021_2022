package model.database.observer;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

/************************************************************************/
/* Class extending Observable, to allow subscribing to specific events. */
/************************************************************************/
public class Subject extends Observable {
    private boolean changed = false;
    private HashMap<String, Vector<Observer>> obs;

    public Subject() {
        obs = new HashMap<>();
        for (BestellingEventsEnum event : BestellingEventsEnum.values()) {
            obs.put(event.name(), new Vector<>());
        }
    }

    public synchronized void addObserver(Observer o, BestellingEventsEnum event) {
        if (o == null)
            throw new NullPointerException();
        if (!obs.containsKey(event.name()))
            throw new IllegalArgumentException();
        if (!obs.get(event.name()).contains(o)) {
            obs.get(event.name()).addElement(o);
        }
    }

    public synchronized void deleteObserver(Observer o, BestellingEventsEnum event) {
        if (obs.containsKey(event.name())) {
            obs.get(event.name()).removeElement(o);
        }
    }

    public void notifyObservers(BestellingEventsEnum event) {
        notifyObservers(event, null);
    }

    public void notifyObservers(BestellingEventsEnum event, Object arg) {
        /*
         * a temporary array buffer, used as a snapshot of the state of
         * current Observers.
         */
        Object[] arrLocal;

        synchronized (this) {
            /* We don't want the Observer doing callbacks into
             * arbitrary code while holding its own Monitor.
             * The code where we extract each Observable from
             * the Vector and store the state of the Observer
             * needs synchronization, but notifying observers
             * does not (should not).  The worst result of any
             * potential race-condition here is that:
             * 1) a newly-added Observer will miss a
             *   notification in progress
             * 2) a recently unregistered Observer will be
             *   wrongly notified when it doesn't care
             */
            if (!changed) {
                return;
            }
            if (!obs.containsKey(event.name())) {
                return;
            }
            arrLocal = obs.get(event.name()).toArray();
            clearChanged();
        }

        for (int i = arrLocal.length-1; i>=0; i--)
            ((Observer)arrLocal[i]).update(this, arg);
    }

    public synchronized void deleteObservers() {
        for (BestellingEventsEnum event : BestellingEventsEnum.values()) {
            obs.get(event.name()).removeAllElements();
        }
    }

    public synchronized void deleteObservers(BestellingEventsEnum event) {
        if (obs.containsKey(event.name())) {
            obs.get(event.name()).removeAllElements();
        }
    }

    public synchronized int countObservers(BestellingEventsEnum event) {
        return (obs.containsKey(event.name())) ? obs.get(event.name()).size() : 0;
    }

    protected synchronized void setChanged() { changed = true; }
    protected synchronized void clearChanged() {
        changed = false;
    }
    public synchronized boolean hasChanged() { return changed; }
}

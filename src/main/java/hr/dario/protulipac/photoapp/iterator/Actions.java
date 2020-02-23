package hr.dario.protulipac.photoapp.iterator;


import java.util.NoSuchElementException;

public class Actions<T> implements Container {

    private T[] actions;

    public Actions(T[] actions) {
        this.actions = actions;
    }

    @Override
    public Iterator getIterator() {
        return new ActionIterator();
    }

    private class ActionIterator implements Iterator {

        int index = -1;

        @Override
        public boolean hasNext() {
            return index < actions.length - 1;
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                index++;
                return actions[index];
            }
            throw new NoSuchElementException();
        }
    }
}

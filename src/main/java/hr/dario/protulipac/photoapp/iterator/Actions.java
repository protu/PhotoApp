package hr.dario.protulipac.photoapp.iterator;


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

        int index;

        @Override
        public boolean hasNext() {
            return index < actions.length;
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return actions[index];
            }
            return null;
        }
    }
}

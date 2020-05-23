package hr.dario.protulipac.photoapp.iterator;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ActionsTest {



    @Test
    void hasNext() {
        Integer[] intList = new Integer[]{1,2,3,4,5};
        Integer[] intListCopy = new Integer[intList.length];
        Actions<Integer> actions =new Actions<>(intList);
        Iterator iterator = actions.getIterator();
        int i = 0;
        while (iterator.hasNext()) {
            intListCopy[i++] = (Integer)iterator.next();
        }
        assertArrayEquals(intList, intListCopy);
    }


}
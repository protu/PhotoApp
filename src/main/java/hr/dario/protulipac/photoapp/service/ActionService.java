package hr.dario.protulipac.photoapp.service;

import hr.dario.protulipac.photoapp.iterator.Actions;
import hr.dario.protulipac.photoapp.iterator.Iterator;
import hr.dario.protulipac.photoapp.processing.BlurAction;
import hr.dario.protulipac.photoapp.processing.PhotoAction;
import hr.dario.protulipac.photoapp.processing.SepiaAction;
import hr.dario.protulipac.photoapp.processing.SharpenAction;
import org.springframework.stereotype.Service;

@Service
public class ActionService {

    private static ActionService instance;

    private ActionService() {
    }

    public static ActionService getInstance() {
        if (instance == null) {
            instance = new ActionService();
        }
        return instance;
    }

    private Class<PhotoAction>[] actions = new Class[]{BlurAction.class, SepiaAction.class, SharpenAction.class};
    private Actions<Class> actionsList = new Actions<>(actions);

    public String[] getActionList() {
        Iterator actionIterator = actionsList.getIterator();
        String[] al = new String[actionsList.length()];
        int index = 0;
        while (actionIterator.hasNext()) {
            String className = ((Class) actionIterator.next()).getSimpleName();
            al[index++] = className.substring(0, className.lastIndexOf("Action"));
        }
        return al;
    }

    public Class<PhotoAction>[] getActionClasses () {
        return actions;
    }

}

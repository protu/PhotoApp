package hr.dario.protulipac.photoapp.processing;

import hr.dario.protulipac.photoapp.domain.Picture;
import hr.dario.protulipac.photoapp.domain.PictureInt;
import hr.dario.protulipac.photoapp.service.ActionService;

import java.lang.reflect.InvocationTargetException;

public class PhotoProcessFactory {

    private ActionService actionService = ActionService.getInstance();
    private String[] actions;
    private PictureInt picture;

    public PhotoProcessFactory(String[] actions, Picture picture) {
        this.actions = actions;
        this.picture = (Picture) picture.clone();
    }

    public PictureInt getProcessedPicture() {

        if (actions == null) {
            return picture;
        }

        for (String act : actions) {
            act += "Action";
            for(Class<PhotoAction> actionClass : actionService.getActionClasses()) {
                if (actionClass.getSimpleName().equals(act)) {
                    try {
                        picture = actionClass.getDeclaredConstructor(PictureInt.class).newInstance(picture);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return picture;
    }
}

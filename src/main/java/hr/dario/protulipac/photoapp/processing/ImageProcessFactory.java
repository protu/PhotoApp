package hr.dario.protulipac.photoapp.processing;

import hr.dario.protulipac.photoapp.domain.Picture;
import hr.dario.protulipac.photoapp.domain.PictureInt;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class ImageProcessFactory {
    private String[] actions;
    private PictureInt picture;
    private Logger log = LoggerFactory.getLogger(ImageProcessFactory.class);

    public ImageProcessFactory(String[] actions, Picture picture) {
        this.actions = actions;
        this.picture = (Picture) picture.clone();
    }

    public PictureInt getNewPicture() {

        if (actions == null) {
            return picture;
        }

        for (String act : actions) {
            if (act.equals("blur")) {
                picture = new BlurAction(picture);
            } else if (act.equals("sharpen")) {
                picture = new SharpenAction(picture);
            } else if (act.equals("sepia")) {
                picture = new SephiaAction(picture);
            }
        }

        return picture;
    }
}

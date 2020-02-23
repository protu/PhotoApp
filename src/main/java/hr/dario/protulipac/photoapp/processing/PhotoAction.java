package hr.dario.protulipac.photoapp.processing;

import hr.dario.protulipac.photoapp.domain.Picture;
import hr.dario.protulipac.photoapp.domain.PictureInt;

public abstract class PhotoAction implements PictureInt {

    private PictureInt pictureInt;

    public PhotoAction(PictureInt pictureInt) {
        this.pictureInt = pictureInt;
    }

    public boolean isPicture() {
        return pictureInt instanceof Picture;
    }

    public Picture getPicture() {
        if(isPicture()) { return (Picture) pictureInt; }
        return null;
    }

    @Override
    public String process() {
        return pictureInt.process();
    }
}

package hr.dario.protulipac.photoapp.processing;

import hr.dario.protulipac.photoapp.domain.PictureInt;

public class SharpenAction extends PhotoAction {
    public SharpenAction(PictureInt pictureInt) {
        super(pictureInt);
    }

    @Override
    public String process() {
        return super.process() + " sharpen";
    }
}

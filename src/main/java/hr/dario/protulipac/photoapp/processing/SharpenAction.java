package hr.dario.protulipac.photoapp.processing;

import hr.dario.protulipac.photoapp.domain.PictureInt;

public class SharpenAction extends ImageAction {
    public SharpenAction(PictureInt pictureInt) {
        super(pictureInt);
    }

    @Override
    public String process() {
        addAction("sharpen");
        return super.process();
    }
}

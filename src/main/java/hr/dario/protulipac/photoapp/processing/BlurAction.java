package hr.dario.protulipac.photoapp.processing;

import hr.dario.protulipac.photoapp.domain.PictureInt;

public  class BlurAction extends ImageAction {
    public  BlurAction(PictureInt pictureInt) {
        super(pictureInt);
    }

    @Override
    public String process() {
        return super.process() + " blured";
    }
}

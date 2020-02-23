package hr.dario.protulipac.photoapp.processing;

import hr.dario.protulipac.photoapp.domain.PictureInt;

public class SephiaAction extends ImageAction {
    public SephiaAction(PictureInt pictureInt) {
        super(pictureInt);
    }

    @Override
    public String process() {
        return super.process() + " sephia";
    }
}

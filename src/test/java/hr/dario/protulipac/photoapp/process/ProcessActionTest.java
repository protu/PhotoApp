package hr.dario.protulipac.photoapp.process;

import hr.dario.protulipac.photoapp.domain.Picture;
import hr.dario.protulipac.photoapp.domain.PictureInt;
import hr.dario.protulipac.photoapp.processing.BlurAction;
import hr.dario.protulipac.photoapp.processing.SepiaAction;
import hr.dario.protulipac.photoapp.processing.SharpenAction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProcessActionTest {

    private Picture testPicure = new Picture();

    @Test
    void setTestPicure() {
        assertEquals("original", testPicure.process());
    }

    @Test
    void setBlurPicure() {
        PictureInt bluredPicture = new BlurAction(testPicure);
        assertEquals("original blured", bluredPicture.process());
    }

    @Test
    void setSephiaPicure() {
        PictureInt sephiaPicture = new SepiaAction(testPicure);
        assertEquals("original sephia", sephiaPicture.process());
    }

    @Test
    void setSharpenPicure() {
        PictureInt sharpenPicture = new SharpenAction(testPicure);
        assertEquals("original sharpen", sharpenPicture.process());
    }
}

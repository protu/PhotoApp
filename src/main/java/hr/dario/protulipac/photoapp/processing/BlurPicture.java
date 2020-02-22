package hr.dario.protulipac.photoapp.processing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BlurPicture {

    private BufferedImage bufferedImage;

    private void loadImage(String path) {
        try {
            this.bufferedImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage process() {
        return bufferedImage;
    }

}

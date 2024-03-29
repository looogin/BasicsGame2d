package maverck;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by admin on 25.05.16.
 */

public class SpriteSheet {

    private BufferedImage[] frames;

    public SpriteSheet(String namefile, int row, int col) {
        frames = new BufferedImage[row];
        int i;
        for (i = 0; i <= row - 1; i++) {
            frames[i] = getImage(namefile).getSubimage(i * Constants.SPRITE_WIDTH, col * Constants.SPRITE_HEIGHT, Constants.SPRITE_WIDTH, Constants.SPRITE_HEIGHT);
        }


    }

    private BufferedImage getImage(String path) {
        BufferedImage p1AttackSpriteSheet = null;
        try {
            p1AttackSpriteSheet = ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResource(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p1AttackSpriteSheet;
    }

    public BufferedImage[] getFrames() {

        return frames;
    }


}

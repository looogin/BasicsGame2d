package maverck;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {

    private final BufferedImage[] images;
    private final int interval;
    private int index;
    private long timer, now, lastTime;

    public Animation(BufferedImage[] images, int interval) {
        this.images = images;
        this.interval = interval;
        index = 0;
        timer = 0;
        now = 0;
        lastTime = System.currentTimeMillis();
    }

    public void tick() {
        now = System.currentTimeMillis();
        timer += now - lastTime;
        lastTime = now;
        if (timer >= interval) {
            index++;
            timer = 0;

            if (index == images.length) {
                index = 0;
            }
        }
    }

    public void render(Graphics g, int x, int y, int width, int height) {
        g.drawImage(images[index], x, y, width, height, null);

    }
}
package maverck;

import java.awt.*;

import static maverck.Constants.*;


public class Player {

    private final InputHandler input;
    private final int x1;
    private final int y1;
    private final Animation IdeAnimation;
    private final Animation RunAnimation;
    private int xa = 0;
    private final int ya = 0;
    private int move = 0;


    Player(int x, int y, InputHandler input) {
        this.x1 = x;
        this.y1 = y;
        this.input = input;
        SpriteSheet ide = new SpriteSheet("pic/123.png", 8, 0);
        SpriteSheet run = new SpriteSheet("pic/123.png", 9, 1);
        IdeAnimation = new Animation(ide.getFrames(), 100);
        RunAnimation = new Animation(run.getFrames(), 100);
    }

     void tick() {
        IdeAnimation.tick();
        RunAnimation.tick();
        move = 0;
        //if (input.up.down) ya--;
        //if (input.down.down) ya++;
        if (input.left.down & !input.right.down) {
            xa--;
            move = 1;
        }
        if (input.right.down & !input.left.down) {
            xa++;
            move = 2;
        }
    }


     void render(Graphics g) {
        int x = x1 + xa;
        int y = y1;
         switch (move) {
             case 0 -> IdeAnimation.render(g, x, y, SPRITE_WIDTH * SCALE, SPRITE_HEIGHT * SCALE);
             case 2 -> RunAnimation.render(g, x, y, SPRITE_WIDTH * SCALE, SPRITE_HEIGHT * SCALE);
             case 1 -> RunAnimation.render(g, x + SPRITE_WIDTH * SCALE, y, -SPRITE_WIDTH * SCALE, SPRITE_HEIGHT * SCALE);
         }

    }

}



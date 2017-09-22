package com;

import java.awt.*;

import static com.Constants.*;


public class Player {

    private InputHandler input;
    private int x1, y1;
    private Animation IdeAnimation, RunAnimation;
    private int xa = 0;
    private int ya = 0;
    private SpriteSheet ide, run;
    private int  move =0;


    public Player(int x, int y, InputHandler input) {
        this.x1 = x;
        this.y1 = y;
        this.input = input;
        ide = new SpriteSheet("pic/123.png", 8, 0);
        run = new SpriteSheet("pic/123.png", 9, 1);
        IdeAnimation = new Animation(ide.getFrames(), 100);
        RunAnimation = new Animation(run.getFrames(), 100);
    }

    public void tick() {
        IdeAnimation.tick();
        RunAnimation.tick();
        move=0;
        //if (input.up.down) ya--;
        //if (input.down.down) ya++;
        if (input.left.down&!input.right.down) {
            xa--;
            move = 1;
        }
        if (input.right.down&!input.left.down) {
            xa++;
            move = 2;
        }
    }


    public void render(Graphics g) {
        int x = x1 + xa;
        int y = y1;
        switch (move){
            case 0: IdeAnimation.render(g, x, y, SPRITE_WIDTH * SCALE, SPRITE_HEIGHT * SCALE);
            break;
            case 2:RunAnimation.render(g, x, y, SPRITE_WIDTH * SCALE, SPRITE_HEIGHT * SCALE);
            break;
            case 1:RunAnimation.render(g, x + SPRITE_WIDTH * SCALE, y, -SPRITE_WIDTH * SCALE, SPRITE_HEIGHT * SCALE);
            break;
        }

    }

}



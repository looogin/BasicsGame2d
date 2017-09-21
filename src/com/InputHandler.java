package com;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class InputHandler implements KeyListener {
    private String[] nameKey = {"Up", "Down", "Left", "Right", "Attack", "Menu"};
    private String currentKey = "Ide";

    String getCurrentKey() {
        return currentKey;
    }

    private void setCurrentKey(String currentKey) {
        this.currentKey = currentKey;
    }

    public class Key {
        int presses;
        boolean down;
        private String name;


        Key(String name) {
            keys.add(this);
            this.name = name;

        }

        void toggle(boolean pressed) {
            if (pressed != down) {
                down = pressed;
            }
            if (pressed) {
                presses++;
            }
        }

        void tick() {
            if(down)setCurrentKey(name);
        }
    }

    private List<Key> keys = new ArrayList<>();
    private Key up = new Key(nameKey[0]);
    private Key down = new Key(nameKey[1]);
    private Key left = new Key(nameKey[2]);
    private Key right = new Key(nameKey[3]);
    private Key attack = new Key(nameKey[4]);
    private Key menu = new Key(nameKey[5]);

    public void releaseAll() {
        for (Key key : keys) {
            key.down = false;
        }
    }

    void tick() {
        for (Key key : keys) {
            key.tick();
        }
        System.out.println(getCurrentKey());
    }


    InputHandler(Game game) {
        game.addKeyListener(this);
    }
    boolean blockInpunt;
    public void keyPressed(KeyEvent ke) {
        for (Key key : keys) {
            if (key.down){
                blockInpunt = true;
                break;
            }
            else blockInpunt = false;
        }

        if (!blockInpunt)toggle(ke, true);
    }

    public void keyReleased(KeyEvent ke) {
        if (ke.isActionKey()||!blockInpunt) setCurrentKey("Ide");
        toggle(ke, false);
    }

    private void toggle(KeyEvent ke, boolean pressed) {
        if (ke.getKeyCode() == KeyEvent.VK_NUMPAD8) up.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_NUMPAD2) down.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_NUMPAD4) left.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_NUMPAD6) right.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_W) up.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_S) down.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_A) left.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_D) right.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_UP) up.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_DOWN) down.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) left.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) right.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_TAB) menu.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_ALT) menu.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_ALT_GRAPH) menu.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) attack.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_CONTROL) attack.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_NUMPAD0) attack.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_INSERT) attack.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_ENTER) menu.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_X) menu.toggle(pressed);
        if (ke.getKeyCode() == KeyEvent.VK_C) attack.toggle(pressed);
    }

    public void keyTyped(KeyEvent ke) {
    }


}

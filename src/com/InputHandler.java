package com;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class InputHandler implements KeyListener {
    private String[] nameKey = {"Up", "Down", "Left", "Right", "Attack", "Menu"};
    private String currentKey = "Ide";


    public String getCurrentKey() {
        return currentKey;
    }

    public void setCurrentKey(String currentKey) {
        this.currentKey = currentKey;
    }


    public class Key {
        public int presses, absorbs;
        public boolean down;
        public boolean clicked;
        private String name;


        public Key(String name) {
            keys.add(this);
            this.name = name;

        }

        public void toggle(boolean pressed) {
            if (pressed != down) {
                down = pressed;
            }
            if (pressed) {
                presses++;
            }
        }

        public void tick() {
            if (absorbs < presses) {
                absorbs++;
                setCurrentKey(name);
                clicked = true;
            } else {
                clicked = false;
            }
        }

        public String getName() {
            return name;
        }
    }

    public List<Key> keys = new ArrayList<>();
    public Key up = new Key(nameKey[0]);
    public Key down = new Key(nameKey[1]);
    public Key left = new Key(nameKey[2]);
    public Key right = new Key(nameKey[3]);
    public Key attack = new Key(nameKey[4]);
    public Key menu = new Key(nameKey[5]);

    public void releaseAll() {
        for (Key key : keys) {
            key.down = false;
        }
    }

    public void tick() {
        for (Key key : keys) {
            key.tick();
        }
    }


    public InputHandler(Game game) {
        game.addKeyListener(this);
    }

    public void keyPressed(KeyEvent ke) {
        toggle(ke, true);
    }

    public void keyReleased(KeyEvent ke) {
        if (ke.isActionKey()) setCurrentKey("Ide");
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

package maverck;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serial;
import java.util.concurrent.TimeUnit;

public class Game extends Canvas implements Runnable {
    @Serial
    private static final long serialVersionUID = 1L;
    public static boolean running = false;
    public Thread gameThread;
    private final InputHandler input = new InputHandler(this);
    private Player player1;

    public void init() {


        player1 = new Player(120, 200, input);


    }


    public synchronized void start() {
        if (running) return;
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public void run() {

        // Credit for this run loop goes to MadProgrammer @ StackOverflow
        init();

        final long amountOfTicks = 60;
        long ns = Math.round(1000000000 / (double) amountOfTicks);

        int frames = 0;
        long frameStart = System.currentTimeMillis();

        while (running) {
            long startedAt = System.nanoTime();
            render();
            long completedAt = System.nanoTime();
            long duration = completedAt - startedAt;
            long frameEnd = System.currentTimeMillis();
            tick();


            if (frameEnd - frameStart >= 1000) {
                frames = 0;
                frameStart = System.currentTimeMillis();
            } else {
                frames++;
            }

            long rest = ns - duration;
            if (rest > 0) {
                rest = TimeUnit.MILLISECONDS.convert(rest, TimeUnit.NANOSECONDS);
                try {
                    Thread.sleep(rest);
                } catch (InterruptedException _) {
                }
            }
        }
        stop();
    }

    public void tick() {
        input.tick();
        player1.tick();

    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
            requestFocus();
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        player1.render(g);
        g.dispose();
        bs.show();

    }

    public static void main(String[] args) {

        Game game = new Game();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        game.setPreferredSize(new Dimension(Constants.WIDTH * Constants.SCALE, Constants.HEIGHT * Constants.SCALE));
        JFrame frame = new JFrame("Test");
        frame.setResizable(false);
        frame.setSize(WIDTH * Constants.SCALE, HEIGHT * Constants.SCALE);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(game);
        frame.setVisible(true);
        Dimension frameSize = frame.getPreferredSize();
        frame.setSize(frameSize.width, frameSize.height);
        frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        game.start();
    }

}




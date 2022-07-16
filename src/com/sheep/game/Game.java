package com.sheep.game;

import com.sheep.game.entity.EnemySpawner;
import com.sheep.game.entity.mob.EnemyUnits.Demon;
import com.sheep.game.entity.mob.EnemyUnits.Husk;
import com.sheep.game.entity.mob.Player;
import com.sheep.game.gfx.Screen;
import com.sheep.game.level.CaveLevel.CaveLevel;
import com.sheep.game.level.Level;
import com.sheep.game.util.Keyboard;
import com.sheep.game.util.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable{
    public static final int WIDTH = 240, HEIGHT = WIDTH / 4*3, SCALE = 3;

    private Thread thread;
    private JFrame frame;
    public static Screen screen;

    public static Level level;
    public static Player player;

    private boolean running;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    int xScroll, yScroll;

    public Game(){
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        frame = new JFrame();
        screen = new Screen(WIDTH, HEIGHT);

        level = new CaveLevel(64, 64, 1);

        level.Add(player = new Player(((CaveLevel)level).getPlayerStart().x*16, ((CaveLevel)level).getPlayerStart().y*16, level));

        Keyboard keyboard = new Keyboard();
        Mouse mouse = new Mouse();

        addKeyListener(keyboard);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    public synchronized void start(){
        thread = new Thread(this, "Display");
        running = true;
        thread.start();
    }

    public synchronized void stop(){
        try{
            running = false;
            thread.join();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void tick(){
        level.tick();

        xScroll = (int) player.getX() - WIDTH/2;
        yScroll = (int) player.getY() - HEIGHT/2;

        if(xScroll < 0) xScroll = 0;
        else if(xScroll > level.getWidth()*16 - WIDTH) xScroll = level.getWidth()*16 - WIDTH;
        if(yScroll < 0) yScroll = 0;
        else if(yScroll > level.getHeight()*16 - HEIGHT) yScroll = level.getHeight()*16 - HEIGHT;
    }

    void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        screen.clear();

        level.render(xScroll, yScroll, screen);

        for(int i = 0; i < pixels.length; i++){
            pixels[i] = screen.pixels[i];
        }

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        g.dispose();
        bs.show();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double ns = 1000000000 / 60.0;
        double delta = 0;

        int ticks = 0;
        int frames = 0;

        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1){
                tick();
                ticks++;
                delta--;
            }

            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("ticks: " + ticks + ", frames: " + frames);
                ticks = 0;
                frames = 0;
            }
        }
        stop();
    }

    public static void main(String[] args) {
        Game game = new Game();

        game.frame.setResizable(false);
        game.frame.setTitle("RougeLike");

        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.add(game);

        game.frame.pack();
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }
}

package com.sheep.game;

import com.sheep.game.UI.Menu;
import com.sheep.game.entity.mob.Player;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.CaveLevel.CaveLevel;
import com.sheep.game.level.Level;
import com.sheep.game.util.Keyboard;
import com.sheep.game.util.Mouse;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


public class Game extends Canvas implements Runnable{
    public static final int WIDTH = 240, HEIGHT = WIDTH / 4*3, SCALE = 3;

    private Thread thread;
    private JFrame frame;

    public static Screen screen;

    public static Level level;

    public static Player player;

    public static Menu currentMenu;

    public static boolean running;
    public static boolean gameStarted;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    int xScroll, yScroll;

    public Game(){
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        frame = new JFrame();
        screen = new Screen(WIDTH, HEIGHT);

        currentMenu = Menu.mainMenu;

        Keyboard keyboard = new Keyboard();
        Mouse mouse = new Mouse();

        addKeyListener(keyboard);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    public static void StartGame(){
        currentMenu = null;
        gameStarted = true;

        level = new CaveLevel(64, 64, System.currentTimeMillis());

        level.Add(player = new Player(((CaveLevel)level).getPlayerStart().x*16, ((CaveLevel)level).getPlayerStart().y*16,
                level));
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
        if(currentMenu == null){
            if(gameStarted) {
                level.tick();

                xScroll = (int) player.getX() - WIDTH / 2;
                yScroll = (int) player.getY() - HEIGHT / 2;

                if (xScroll < 0) xScroll = 0;
                else if (xScroll > level.getWidth() * 16 - WIDTH) xScroll = level.getWidth() * 16 - WIDTH;
                if (yScroll < 0) yScroll = 0;
                else if (yScroll > level.getHeight() * 16 - HEIGHT) yScroll = level.getHeight() * 16 - HEIGHT;
            }
        }else{
            currentMenu.tick();
        }

    }

    void drawMinimap(){
        for(int y = 0; y < level.getHeight(); y++){
            for(int x = 0; x < level.getWidth(); x++){
                if(!level.getTile(x, y).solid()){
                    screen.pixels[(y+ screen.getHeight() - level.getHeight())*screen.getWidth()+x + (screen.getWidth() - level.getWidth())] += 0x141414;
                }else if(((CaveLevel)level).getSurroundingWallCount(x, y, 3) < 8){
                    screen.pixels[(y+ screen.getHeight() - level.getHeight())*screen.getWidth()+x + (screen.getWidth() - level.getWidth())] += 0x090909;
                }
            }
        }

        screen.pixels[(int)(player.getY()/16 + screen.getHeight() - level.getHeight())*screen.getWidth()+(int)player.getX()/16+(screen.getWidth() - level.getWidth())] = 0x555555;
    }

    void drawHotbar(){
        for(int i = 0; i < player.getItems().length; i++){
            if(i == player.getCurrentItem())
                screen.renderSpriteFixed(i*16+i*2+4, 4, Sprite.UI_ItemFrame_select, false);
            else
                screen.renderSpriteFixed(i*16+i*2+4, 4, Sprite.UI_ItemFrame, false);

            if(player.getItems()[i] != null)
                screen.renderSpriteFixed(i*16+i*2+4, 4, player.getItems()[i].getIcon(), false);
        }
    }

    void drawHealthBar(){
        for(int i = 0; i < 32; i++){
            float t = i/32f;

            if(t < player.getHealth()/ player.getMaxHealth()){
                for(int j = 0; j < 4; j++)
                    screen.pixels[(4+j)*screen.getWidth()+screen.getWidth() - 32 - 4+i] = 0x00ff00;
            }else{
                for(int j = 0; j < 4; j++)
                    screen.pixels[(4+j)*screen.getWidth()+screen.getWidth() - 32 - 4+i] = 0x232323;
            }
        }
    }

    void drawStaminaBar(){
        for(int i = 0; i < 32; i++){
            float t = i/32f;

            if(t < player.getStamina()/ player.getMaxStamina()){
                for(int j = 0; j < 4; j++)
                    screen.pixels[(10+j)*screen.getWidth()+screen.getWidth() - 32 - 4+i] = 0xffc800;
            }else{
                for(int j = 0; j < 4; j++)
                    screen.pixels[(10+j)*screen.getWidth()+screen.getWidth() - 32 - 4+i] = 0x232323;
            }
        }
    }

    void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        screen.clear();

        if(currentMenu == null){
            if(gameStarted){
                level.render(xScroll, yScroll, screen);

                drawMinimap();

                if(!player.isRemoved()){
                    drawHotbar();
                    drawHealthBar();
                    drawStaminaBar();
                }
            }

        }else{
            currentMenu.render(screen);
        }

        System.arraycopy(screen.pixels, 0, pixels, 0, pixels.length);

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

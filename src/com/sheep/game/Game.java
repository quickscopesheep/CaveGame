package com.sheep.game;

import com.sheep.game.Items.Item;
import com.sheep.game.Items.items.pickaxe;
import com.sheep.game.UI.*;
import com.sheep.game.UI.Menu;
import com.sheep.game.entity.ItemDrop;
import com.sheep.game.entity.mob.Player;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.CaveLevel;
import com.sheep.game.level.Level;
import com.sheep.game.util.input.Keyboard;
import com.sheep.game.util.input.Mouse;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import static java.util.Map.entry;


public class Game extends Canvas implements Runnable{
    public static final int WIDTH = 240, HEIGHT = WIDTH / 4*3, SCALE = 3;

    private Thread thread;
    private JFrame frame;

    public Screen screen;

    public Level[] levels;
    public int currentLevel;

    public Player player;

    public Menu currentMenu;

    public boolean running;
    public boolean gameStarted;

    public GameSettings settings;

    private static Random random;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    int xScroll, yScroll;

    public Map<String, Menu> menus;

    public Game(){
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        frame = new JFrame();
        screen = new Screen(WIDTH, HEIGHT, this);

        random = new Random();

        menus = Map.ofEntries(
                entry("main", new MainMenu(this)),
                entry("start", new StartGameMenu(this)),
                entry("respawn", new RespawnMenu(this)),
                entry("win", new WinMenu(this))
        );

        switchMenu("main");

        Keyboard keyboard = new Keyboard();
        Mouse mouse = new Mouse();

        addKeyListener(keyboard);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    public void StartGame(GameSettings gameSettings){
        switchMenu("");
        settings = gameSettings;

        currentLevel = 0;
        levels = new Level[settings.floors];

        for(int i = 0; i < settings.floors; i++){
            levels[i] = new CaveLevel(64, 64, System.currentTimeMillis(), i, this);
        }

        getLevel().Add(player = new Player(((CaveLevel)getLevel()).getPlayerStart().x*16, ((CaveLevel)getLevel()).getPlayerStart().y*16,
                getLevel(), this));

        player.pickupItem(new pickaxe(player, this));

        gameStarted = true;

        System.out.println("Level Seed: " + getLevel().getSeed() + " , Difficulty: " + settings.difficulty);
    }

    public synchronized void start(){
        thread = new Thread(this, "Display");
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
                getLevel().tick();

                xScroll = (int) player.getX() - WIDTH / 2;
                yScroll = (int) player.getY() - HEIGHT / 2;

                if (xScroll < 0) xScroll = 0;
                else if (xScroll > getLevel().getWidth() * 16 - WIDTH) xScroll = getLevel().getWidth() * 16 - WIDTH;
                if (yScroll < 0) yScroll = 0;
                else if (yScroll > getLevel().getHeight() * 16 - HEIGHT) yScroll = getLevel().getHeight() * 16 - HEIGHT;
            }
        }else{
            currentMenu.tick();
        }


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
                getLevel().render(xScroll, yScroll, screen);

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
        running = true;
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

    public static void main(String[] args) throws IOException {
        Game game = new Game();

        game.frame.setResizable(false);
        game.frame.setTitle("RougeLike");

        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.add(game);

        game.frame.pack();
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        BufferedImage image;

        try {
            image = ImageIO.read(Game.class.getResource("/icon.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        game.frame.setIconImage(image);

        game.start();
    }

    public Level getLevel(){
        return levels[currentLevel];
    }

    public int getLevelIndex(){
        return currentLevel;
    }

    public void ChangeLevel(int level){
        if(level == settings.floors){
            winCondition();
        }

        levels[currentLevel].Remove(player);

        levels[level].Add(player);

        currentLevel = level;
        player.setLevel(levels[level]);

        player.setX(((CaveLevel)levels[level]).getPlayerStart().x*16);
        player.setY(((CaveLevel)levels[level]).getPlayerStart().y*16);
    }

    public void switchMenu(String menu){
        if(menu == null || menu.isBlank() || menu.isEmpty()){
            currentMenu = null;
        }
        
        currentMenu = menus.get(menu);
    }

    public void respawnPlayer(){
        player = new Player(((CaveLevel)getLevel()).getPlayerStart().x*16, ((CaveLevel)getLevel()).getPlayerStart().y*16, getLevel(), this);
        getLevel().Add(player);

        gameStarted = true;
        switchMenu("");
    }

    public void playerDead(){
        for (Item item : player.getItems()) {
            if(item == null) continue;
            levels[currentLevel].Add(new ItemDrop(player.getX() + random.nextInt(-8, 8),
                    player.getY() + random.nextInt(-8, 8), levels[currentLevel], item, 0, this));
        }

        gameStarted = false;
        switchMenu("respawn");
        ((CaveLevel) getLevel()).stopAmbient();
        Player.deaths++;
    }

    public void winCondition(){
        switchMenu("win");
    }
}

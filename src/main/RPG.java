package main;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import main.entity.Player;
import main.entity.entity;

/**
 * @author Whoever can fix the ladders
 * @version Alpha 1.3
 * @see project
 * 
 ChangeList:
 
 1.0 -original APPLET form
 1.01 - updated SEE Project class
 1.02 - package setup, placeholder art, original story
 
 1.10 - Switch to JFrame, the first time
 1.11 - WOO, added basic menu, inputHandling, and constants
 1.12 - added entities, player class, zombie class
 
 1.2 - Added basic 'Game' class
 1.22 - Updated 'Game' class
 
 1.3 - Added Menus/ Instructions/ About
 1.31 - Added Selection sounds and Fixed Spacing on Menus
 1.32 - Implemented new Sound System for the selection and menu screens
 1.33 - 
 
 */



public class RPG extends JFrame implements Runnable{


	private static final long serialVersionUID = 1L;
	
	Image Buffer;
	Graphics gBuffer;
	Random random = new Random();
	
	//SIZE ATTRIBUTES
	public static final int WIDTH = 1024; 
    public static final int HEIGHT = 768;
    public Dimension size;
    //want to have different size screens available
    //most common   640*480   1024*768 800*600 1440*900 1280*720
    
    //INPUT HANDLING VARIABLES
    public InputHandler inputHandler;
    public boolean keys[];
    public int mouseButton, mouseX,mouseY;
    private boolean onScreen;
	
	//CURSOR VARIABLES
    private Cursor emptyCursor, defaultCursor;
	
    //MENU VARIABLES
	private boolean cutScene = false;
	
    //TEST VARIABLES
    Image plr;

    //needs to be moved to player class
	@SuppressWarnings("unused")
	private AreaEnum areaP;

	
	//TIME VARIABLES
	static long startTime;
	private static long elapsedTimeMillis;
	private static int elapsedTime;

	
	//Player and Enemy ArrayList/Variables
	ArrayList<entity> entities = new ArrayList<entity>();

    //Game 'tick', handles switching areas, player, pause;
	ActualGame aGame;

	private boolean running;

	private Thread thread;
	
    public RPG()
    {
    	//CREATING WINDOW WITH CERTAIN DIMENSIONS
    	size = new Dimension(WIDTH, HEIGHT);
    	setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("The Last Templar");
      
        //CREATING THE IMAGE AND BUFFER TO DRAW ON
        Buffer=createImage(size.width,size.height);
        gBuffer=Buffer.getGraphics();
        
        //INPUT BASED ALLOCATIONS
        inputHandler = new InputHandler();
        addKeyListener(inputHandler);
        addFocusListener(inputHandler);
        addMouseListener(inputHandler);
        addMouseMotionListener(inputHandler);
    	
        //CREATES A CURSOR THAT IS BLANK FOR WHEN THE MOUSE IS ON SCREEN
        emptyCursor = Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "empty");
        defaultCursor = getCursor();
        
        aGame = new ActualGame(gBuffer);
        
        aGame.initializeGame();
    }
	
	private void tick() {
		if(onScreen)
			aGame.tick(keys);
	}

	public void paint(Graphics g)
    {
    	g.drawImage(Buffer,0,0,this);
    }
    
    private void handleKeysAndMouse(boolean[] k, int[] m) {

    	keys = k;		
        mouseButton = m[0];
        mouseX = m[1];
        mouseY = m[2];
        onScreen = true;//(m[3]==0)? false:true;
	}
	
	public synchronized void start() {
		if (running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	 public static void main(String args[]){
	    	
	    	RPG game = new RPG();
	    	while(true)
	    		game.run();
	    }
	    
	    public void run()
	    {
	    	try{Thread.sleep(20);}
	    	catch(Exception e){e.printStackTrace();}

	        handleKeysAndMouse(inputHandler.keys, inputHandler.mouse);
	         
	       //setCursor(cutScene)? defaultCursor:emptyCursor);

	        int frames = 0;

			double unprocessedSeconds = 0;
			long lastTime = System.nanoTime();
			double secondsPerTick = 1 / 60.0;
			int tickCount = 0;


			while (true) {
				long now = System.nanoTime();
				long passedTime = now - lastTime;
				lastTime = now;
				if (passedTime < 0) passedTime = 0;
				if (passedTime > 100000000) passedTime = 100000000;

				unprocessedSeconds += passedTime / 1000000000.0;

				boolean ticked = false;
				while (unprocessedSeconds > secondsPerTick) {
					tick();
					unprocessedSeconds -= secondsPerTick;
					ticked = true;

					tickCount++;
					if (tickCount % 60 == 0) {
						System.out.println(frames + " fps");
						lastTime += 1000;
						frames = 0;
					}
					repaint();
				}

				if (ticked) {
					repaint();
					frames++;
				} else {
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
	    }


		public static int getTimePassed()
		{
			elapsedTimeMillis = System.currentTimeMillis()-startTime;
			elapsedTime =  (int) (elapsedTimeMillis/1000);      
			//System.out.println(""+startTime+" End Time  "+elapsedTimeMillis +" Seconds: "+ elapsedTime  );
			return elapsedTime;
		}


		private void cutScene() {
			 ImageIcon icon = new ImageIcon("mikeTest.gif"); 
		     gBuffer.drawImage(icon.getImage(),0,0,HEIGHT,HEIGHT,this);
		     
		}
}
	 
	    
/*
 
  NOTES
  
  Splitting an image is fairly simple,
   and very useful. What we want to do 
   is split the image into rows and columns, 
   and draw each mini image into it's own BufferedImage,
    all of these smaller images will be kept inside a BufferedImage array.
     Keeping the images in an array will allow for
      easy access when we want to animate the sprites.
       Here is how you would split an image into columns and rows:
       
  
  public static BufferedImage[] splitImage(BufferedImage img, int cols, int rows) {  
        int w = img.getWidth()/cols;  
        int h = img.getHeight()/rows;  
        int num = 0;  
        BufferedImage imgs[] = new BufferedImage[w*h];  
        for(int y = 0; y < rows; y++) {  
            for(int x = 0; x < cols; x++) {  
                imgs[num] = new BufferedImage(w, h, img.getType());  
                // Tell the graphics to draw only one block of the image  
                Graphics2D g = imgs[num].createGraphics();  
                g.drawImage(img, 0, 0, w, h, w*x, h*y, w*x+w, h*y+h, null);  
                g.dispose();  
                num++;  
            }  
        }  
        return imgs;  
    }  
  
  
  
   /* if(mouseButton==1 && Calc.ifInBox(mouseX,mouseY,0,0,WIDTH,HEIGHT))
	    	   {
	    		   startTime = System.currentTimeMillis();
	    		   cutScene = true;
	    	   }
	    	   
	    	   if(!cutScene)
	    	   {
	    		   gBuffer.drawImage(Art.getImageFromFile("./images/mikeTest.png"),0,0,this);
	    		   gBuffer.drawImage(Art.getImageFromFile("./images/StartPlay.png"),0,0,this);
	    	   }
	    	   else
	    	   {
	    		   cutScene();
	    	   }
	    	   
	       }
  
  
 
 */
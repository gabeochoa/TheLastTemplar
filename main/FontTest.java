package main;

import java.awt.*;
import java.util.Random;

import javax.swing.JFrame;

import main.alphabet.whiteLetters;



public class FontTest extends JFrame implements Runnable{


	private static final long serialVersionUID = 1L;
	
	Image Buffer;
	Graphics gBuffer;
	Random random = new Random();
	
	//SIZE ATTRIBUTES
	public static final int WIDTH = 1024; 
    public static final int HEIGHT = 768;
    public Dimension size;

	private boolean running;

	private Thread thread;
	
    public FontTest()
    {
    	//CREATING WINDOW WITH CERTAIN DIMENSIONS
    	size = new Dimension(WIDTH, HEIGHT);
    	setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Inappropriate Metal in the Hood");
      
        //CREATING THE IMAGE AND BUFFER TO DRAW ON
        Buffer=createImage(size.width,size.height);
        gBuffer=Buffer.getGraphics();
    }
	


	public void paint(Graphics g)
    {
    	g.drawImage(Buffer,0,0,this);
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
	    	
	    	FontTest game = new FontTest();
	    	while(true)
	    		game.run();
	    }
	    
	    public void run()
	    {
	    	try{Thread.sleep(20);}
	    	catch(Exception e){e.printStackTrace();}

	    	gBuffer.drawImage(Art.getImageFromFile("./images/menu/black.png"),0, 0, RPG.WIDTH, RPG.HEIGHT,null);
	    	
	    	whiteLetters a = new whiteLetters();
	    	
	    	Image[][] letter = a.letter;
	    	
	    	for(int i = 0; i<letter.length; i++)
	    		for(int j=0; j<letter[i].length; j++)
	    			gBuffer.drawImage(letter[i][j], 100 + i*80, 100 + j*80, 40, 40, this);
	    			
	    	repaint();
	    	
	    }

	    
	    
	    

}
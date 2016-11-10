package swingdemo;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics; 
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.awt.event.MouseMotionAdapter; 

/*
 * This solution does not use a Square class. We recommend students 
 * to think about designing an appropriate Square class for this program.
 */

public class Swing {
    
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI(); 
        }
      });
    }

    private static void createAndShowGUI() {
        System.out.println("Created GUI on EDT? "+
        SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        f.add(new MyPanel()); 
        f.pack();
        f.setVisible(true);
    } 
}

class MyPanel extends JPanel {

    private int squareX1 = 50;
    private int squareY1 = 50;
    private Color color1 = Color.RED;
    
    
    private int squareX2 = 100;
    private int squareY2 = 100;    
    private Color color2 = Color.GREEN;
    
    private int squareW = 20;
    private int squareH = 20;
    
    Square square1 = new Square(50, 50, 20, Color.RED);
    Square square2 = new Square(100, 100, 20, Color.GREEN);
    public Square[] squarestorage = {square1, square2}; 
    
    private Color squareBeingDragged = null;

    public MyPanel() {
    	
        setBorder(BorderFactory.createLineBorder(Color.black));       
		
        addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getModifiers() == MouseEvent.BUTTON3_MASK) {
					System.out.println("Right click");
					swapColors();
					return;
				}	
				System.out.println("Mouse pressed, X: " + e.getX() + " Y: " + e.getY());
				

				if (inWhichSquare(e.getX(), e.getY()) != Color.GREEN) {					
					moveSquare(e.getX(), e.getY(), Color.RED);
				}
				
			}

			
		});

		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				//System.out.println("Mouse dragged, X: " + e.getX() + " Y: " + e.getY());
				if (inWhichSquare(e.getX()+2, e.getY()+2) == Color.RED 
						|| squareBeingDragged == Color.RED) {
					squareBeingDragged = Color.RED;
					moveSquare(e.getX(), e.getY(), Color.RED);
					System.out.println("Red square is moved to X: " + e.getX() + ", Y: " + e.getY());
				
				}else if (inWhichSquare(e.getX()+2, e.getY()+2) == Color.GREEN 
						|| squareBeingDragged == Color.GREEN) {
					
					squareBeingDragged = Color.GREEN;
					moveSquare(e.getX(), e.getY(), Color.GREEN);
					System.out.println("Green square is moved to X: " + e.getX() + ", Y: " + e.getY());
				}
			}
		});
		
		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				System.out.println("Mouse released, X: " + e.getX() + " Y: " + e.getY());
				squareBeingDragged = null;
				
			}
		});
	}
    
    private void swapColors() {
		Color temp = color1;
		color1 = color2;
		color2 = temp;
		repaint();		
	}
    
	private Color inWhichSquare (int x, int y) {
		
		if (x >= squarestorage[0].x && x <= squarestorage[0].x + squarestorage[0].width && y >= squarestorage[0].y && y <= squarestorage[0].y + squarestorage[0].width) {
			return squarestorage[0].getcolor();
		}else if (x >= squarestorage[1].x && x <= squarestorage[1].x + squarestorage[1].width && y >= squarestorage[1].y && y <= squarestorage[1].y + squarestorage[1].width) {
			//System.out.println(color2);
			return squarestorage[1].getcolor();
		}		
		return null;
	}
	
	

	private void moveSquare(int x, int y, Color color) {
		
		if (color == color1) {			
			squarestorage[0].switchcoord(x, y);		
		}else if(color == color2){
			squarestorage[1].switchcoord(x, y);				
		}		
		repaint();
	}
	
	

    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);       
        g.drawString("This is my custom Panel!",10,20);
        
        g.setColor(squarestorage[0].getcolor());
        g.fillRect(squarestorage[0].x, squarestorage[0].y, squarestorage[0].width, squarestorage[0].width);
        g.setColor(Color.BLACK);
        g.drawRect(squarestorage[0].x, squarestorage[0].y, squarestorage[0].width, squarestorage[0].width);     
		
        g.setColor(squarestorage[1].getcolor());
		g.fillRect(squarestorage[1].x, squarestorage[1].y, squarestorage[1].width, squarestorage[1].width);
		g.setColor(Color.BLACK);
		g.drawRect(squarestorage[1].x, squarestorage[1].y, squarestorage[1].width, squarestorage[1].width);
    }  
    
    public class Square{
    	int x;
    	int y;
    	int width;
    	boolean onsquare = false;
    	Color squarecolor;
    	
    	public Square(int x, int y, int width, Color squarecolor){
    		this.x = x;
    		this.y = y;
    		this.width = width;
    		this.squarecolor = squarecolor;
    	}
    	
    	public void switchbool(){
    		if (this.onsquare == false){
    			this.onsquare = true;
    		}
    		else{
    			this.onsquare = false;}
    		}
    	
    	public void switchcoord(int newx, int newy){
    		this.x = newx;
    		this.y = newy;
    	}
    	
    	public Color getcolor(){
    		return squarecolor;
    	}
    	
    	public void newcolor(Color newsquarecolor){
    		this.squarecolor = newsquarecolor;
    	}
    }
}

package keylistenerexample;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author helmuthtrefftz
 */
public class KeyListenerExample extends JPanel implements KeyListener {

    public static final int FRAME_WIDTH = 500;
    public static final int FRAME_HEIGHT = 500;
    
//    Point points[]={new Point(200, 200),
//                    new Point(300, 200), new Point(300, 300), new Point(250, 150), new Point(200, 300)};
//    Point edges[]={new Point(0,1), new Point(1,2), new Point(0,3), new Point(2,4), new Point(4,0), new Point(3,1)};
    
    Point points[]={new Point(0, 0),
                    new Point(100, 0), 
                    new Point(100, 100), 
                    new Point(50, -50), 
                    new Point(0, 100)};
    Point edges[]={new Point(0,1), 
                    new Point(1,2), 
                    new Point(0,3), 
                    new Point(2,4), 
                    new Point(4,0), 
                    new Point(3,1)};
    
    public KeyListenerExample() {
        // El panel, por defecto no es "focusable". 
        // Hay que incluir estas líneas para que el panel pueda
        // agregarse como KeyListsener.
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);        
    }

        @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (Point edge : edges) {
            g.drawLine(200+points[edge.x].x, 200+points[edge.x].y, 200+points[edge.y].x, 200+points[edge.y].y);
        }
        
    }
    
    
    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int tecla = e.getKeyCode();
        switch (tecla) {
            case KeyEvent.VK_D:
                for (Point point : points) {
                    point.x += 10;
                }
                break;
            case KeyEvent.VK_A:
                for (Point point : points) {
                    point.x -= 10;
                }   
                break;
            case KeyEvent.VK_W:
                for (Point point : points) {
                    point.y -= 10;
                }
                break;
            case KeyEvent.VK_S:
                for (Point point : points) {
                    point.y += 10;
                }
                break;
            case KeyEvent.VK_Z: // Reduce image size.
                for (Point point : points) {
                    point.x *= 0.9;
                    point.y *= 0.9;
                }
                break;
            case KeyEvent.VK_X: // Increase image size.
                for (Point point : points) {
                    point.x *= 1.1;
                    point.y *= 1.1;
                }
                break;
            case KeyEvent.VK_N: // Reduce image size.
                int angle = 20;
                for (Point point : points) {
                    point.x = (int)(point.x*Math.cos(Math.toRadians(angle))-point.y*Math.sin(Math.toRadians(angle)));
                    point.y = (int)(point.x*Math.sin(Math.toRadians(angle))+point.y*Math.cos(Math.toRadians(angle)));
                }
                break;
            case KeyEvent.VK_M: // Increase image size.
                angle = -20;
                for (Point point : points) {
                    point.x = (int)(point.x*Math.cos(Math.toRadians(angle))-point.y*Math.sin(Math.toRadians(angle)));
                    point.y = (int)(point.x*Math.sin(Math.toRadians(angle))+point.y*Math.cos(Math.toRadians(angle)));
                }
                break;
            default:
                break;
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    /**
     * In the main method, the frame and panels are created.
     * And the KeyListeners are added.
     * @param args 
     */
    
    
    public static void main(String[] args) {
        KeyListenerExample kle = new KeyListenerExample();

        
        
        
        // Create a new Frame
        JFrame frame = new JFrame("Ejemplo KeyListener");
        // Upon closing the frame, the application ends
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Add a panel called DibujarCasita3D
        frame.add(kle);

        // Asignarle tamaño
        frame.setSize(KeyListenerExample.FRAME_WIDTH, KeyListenerExample.FRAME_HEIGHT);
        // Put the frame in the middle of the window
        frame.setLocationRelativeTo(null);
        // Show the frame
        frame.setVisible(true);
    }    
}
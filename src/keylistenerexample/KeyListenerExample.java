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
    
    double mat[][] = new double[3][3];
    
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
    // TRANSLATION:
            case KeyEvent.VK_D:
                mini(10, 0);
                move();
                break;
            case KeyEvent.VK_A:
                mini(-10, 0);
                move();
                break;
            case KeyEvent.VK_W:
                mini(-10, 1);
                move();
                break;
            case KeyEvent.VK_S:
                mini(10, 1);
                move();
                break;
    // SCALING:
            case KeyEvent.VK_Z: // Reduce image size.
                fill();
                double sx = 0.8, sy = 0.8;
                mat[0][0] = sx;
                mat[1][1] = sy;
                mat[2][2] = 1;
                move();
                
                break;
            case KeyEvent.VK_X: // Increase image size.
                fill();
                sx = 1.2; sy = 1.2;
                mat[0][0] = sx;
                mat[1][1] = sy;
                mat[2][2] = 1;
                move();
                
                break;
    // ROTATION:
            case KeyEvent.VK_N: // Reduce image size.
                rot_matrix(-45);
                break;
            case KeyEvent.VK_M: // Increase image size.
                rot_matrix(45);
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

    private void mini(int dx, int pos) {
        fill();
        for (int i = 0; i < 3; i++) {
            mat[i][i] = 1;
        }
        mat[pos][2] = dx;
    }
    
    private void move(){
        int pt[][] = new int[3][1];
        for (Point point : points) {
            pt[0][0]= point.x;
            pt[1][0]= point.y;
            pt[2][0]=1;
            pt = multiply(mat,pt);
            point.x = pt[0][0];
            point.y = pt[1][0];
        }
    }
    
    private void fill(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mat[i][j] = 0;
            }
        }
    }
    
    private int[][] multiply(double matrixA[][], int matrixB[][]){
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;
        
        int[][] matrixC = new int [rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for(int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return matrixC;
    }
    
    private void rot_matrix(int angle){
        mat[0][0] = Math.cos(angle);
        mat[1][1] = Math.cos(angle);
        mat[0][1] = -Math.sin(angle);
        mat[1][0] = Math.sin(angle);
        move();
    }
}
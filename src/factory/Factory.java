//*
 /* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;//package name

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Factory extends JApplet implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

    Graphics2D myPic;
    Image dbImage, master;
    private Graphics dbg;
    Timer timer;
    int var;

    public Factory() {//program name
        timer = new Timer(60, this);
        timer.setInitialDelay(100);     //starts timer
        timer.start();
        Timer timer = new Timer(2000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //this will run every 2 seconds

            }

        });
        /**
         * @param args the command line arguments
         */

        try {//READ
            FileReader fr = new FileReader("save.txt"); //reads from text file (located in "files"
            BufferedReader br = new BufferedReader(fr);
            //read and puts each line in the text document into a variable
            var = Integer.parseInt(br.readLine());//line 1 in doc
            var = Integer.parseInt(br.readLine()); //line 2 in doc

            System.out.println("Loaded");//if it works
        } catch (IOException a) {
            System.out.println("Couldn't Load");//if it fails
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame(""); //name on program
        JApplet applet = new Factory();        //sets up the window
        f.getContentPane().add("Center", applet);
        applet.init();
        f.pack();
        f.setSize(925, 777);     //sets the window size
        f.setVisible(true); //makes it visible
        f.setResizable(false);//makes in unsizable
        f.setBounds(400, 25, 925, 757);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //stops program if you x out the window    
    }

    public void paint(Graphics g) {
        dbImage = createImage(getWidth(), getHeight());      //creats and image the size of the screen
        dbg = dbImage.getGraphics();        //double buffers the panel
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }

    public void paintComponent(Graphics g) {
        myPic = (Graphics2D) g;

        //draw here
        myPic.fillRect(300, 700, 30, 12);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        requestFocus();
        setFocusTraversalKeysEnabled(false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //key presses
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            //runs if escape is pressed
            try {
                FileWriter fw = new FileWriter("save.txt");//set place to write to in "Files"
                PrintWriter pw = new PrintWriter(fw); //starts writing
                pw.println(var);//writes var to line 1 of doc
                System.out.println("Saved");//it worked
                pw.close(); //stop writing
            } catch (IOException a) {
                System.out.println("ERROR");//it didnt work
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

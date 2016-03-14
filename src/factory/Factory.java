//*
 /* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;//package name

import java.awt.Color;
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
    int var, posX, posY;
    int[][][] grid = new int[27][27][8];

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
            for (int i = 0; i < 8; i++) {
                for (int c = 0; c < 27; c++) {
                    for (int r = 0; r < 27; r++) {
                        grid[c][r][i] = Integer.parseInt(br.readLine());
                    }
                }
            }
            System.out.println("Loaded");//if it works
        } catch (IOException a) {
            System.out.println("Couldn't Load");//if it fails
        }

        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
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
        f.setBounds(700, 0, 817, 839);
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
        for (int c = 0; c < 27; c++) {
            for (int r = 0; r < 27; r++) {
                myPic.setColor(Color.black);
                myPic.drawRect(c * 30, r * 30, 30, 30);
                if (grid[c][r][0] == 0) {
                    myPic.setColor(Color.green);
                    myPic.fillRect(c * 30 + 1, r * 30 + 1, 29, 29);
                } else if (grid[c][r][0] == 1) {
                    myPic.setColor(Color.orange);
                    myPic.fillRect(c * 30 + 1, r * 30 + 1, 29, 29);
                } else if (grid[c][r][0] == 2) {
                    myPic.setColor(Color.red);
                    myPic.fillRect(c * 30 + 1, r * 30 + 1, 29, 29);
                } else if (grid[c][r][0] == 3) {
                    myPic.setColor(Color.lightGray);
                    myPic.fillRect(c * 30 + 1, r * 30 + 1, 29, 29);
                } else if (grid[c][r][0] == 4) {
                    myPic.setColor(Color.blue);
                    myPic.fillRect(c * 30 + 1, r * 30 + 1, 29, 29);
                } else if (grid[c][r][0] == 5) {
                    myPic.setColor(Color.black);
                    myPic.fillRect(c * 30 + 1, r * 30 + 1, 29, 29);
                } else if (grid[c][r][0] == 6) {
                    myPic.setColor(Color.darkGray);
                    myPic.fillRect(c * 30 + 1, r * 30 + 1, 29, 29);
                }
            }
        }

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
                for (int i = 0; i < 8; i++) {
                    for (int c = 0; c < 27; c++) {
                        for (int r = 0; r < 27; r++) {
                            pw.println(grid[c][r][i]);//writes var to line 1 of doc
                        }
                    }
                }
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
        String res = "", machine = ", None";
        posX = (int) Math.floor(e.getX() / 30);
        posY = (int) Math.floor(e.getY() / 30);
        if (grid[posX][posY][0] == 0) {
            res = ", Grass";
        } else if (grid[posX][posY][0] == 1) {
            res = ", Iron";
        } else if (grid[posX][posY][0] == 2) {
            res = ", Copper";
        } else if (grid[posX][posY][0] == 3) {
            res = ", Stone";
        } else if (grid[posX][posY][0] == 4) {
            res = ", Water";
        } else if (grid[posX][posY][0] == 5) {
            res = ", Oil";
        } else if (grid[posX][posY][0] == 6) {
            res = ", Coal";
        }
        System.out.println(posX + "," + posY + res + machine);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

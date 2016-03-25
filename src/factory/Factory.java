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
import java.awt.Point;
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

    Methods m = new Methods();
    Point p = new Point();
    Graphics2D myPic;
    Image dbImage, master;
    private Graphics dbg;
    Timer timer;
    int var, posX, posY, mode = 1, resC;
    double resN;
    long t = 0;
    int[][][] grid = new int[27][27][8];
    double[] rez = new double[6];
    boolean re = false;
    Timer time;

    public Factory() {//program name
        timer = new Timer(60, this);
        timer.setInitialDelay(100);     //starts timer
        timer.start();
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
            for (int i = 0; i < 6; i++) {
                rez[i] = Integer.parseInt(br.readLine());
            }
            System.out.println("Loaded");//if it works
        } catch (IOException a) {
            System.out.println("Couldn't Load");//if it fails
        }
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame(""); //name on program
        JApplet applet = new Factory();        //sets up the window
        f.getContentPane().add("Center", applet);
        applet.init();
        f.pack();

        f.setVisible(true); //makes it visible
        f.setResizable(false);//makes in unsizable
        f.setBounds(700, 0, 902, 929);
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

        //System.out.println(rez[0]);
        //draw here
        if (mode == 0) {

        }
        if (mode == 1 || mode == 2) {
            for (int c = 0; c < 27; c++) {
                for (int r = 0; r < 27; r++) {
                    myPic.setColor(Color.black);
                    myPic.drawRect(c * 31, r * 31, 31, 31);

                    myPic.setColor(m.color(grid[c][r][0]));
                    myPic.fillRect(c * 31 + 1, r * 31 + 1, 30, 30);
                }
            }

            for (int i = 0; i < 6; i++) {
                myPic.setColor(Color.black);
                myPic.fillRect(843, i * 36 + 49, 18, 18);
                myPic.drawString((int) rez[i] + "", 864, i * 36 + 62);
                myPic.drawRect(i * 65 + 250, 839, 60, 60);
                myPic.setColor(m.color(i + 1));
                myPic.fillRect(844, i * 36 + 50, 16, 16);
            }
            myPic.drawRect(10, 854, 45, 20);
            myPic.drawRect(55, 854, 45, 20);
            myPic.drawRect(100, 854, 45, 20);
            myPic.drawRect(145, 854, 45, 20);
            myPic.drawRect(190, 854, 45, 20);

            myPic.setColor(Color.red);
            if (resN >= .2) {
                myPic.fillRect(11, 855, 44, 19);
            }
            if (resN >= .4) {
                myPic.fillRect(56, 855, 44, 19);
            }
            if (resN >= .6) {
                myPic.fillRect(101, 855, 44, 19);
            }
            if (resN >= .8) {
                myPic.fillRect(146, 855, 44, 19);
            }
            if (resN >= 1) {
                myPic.fillRect(191, 855, 44, 19);
            }
            if (resN >= 1.2) {
                resN = 0;
                rez[resC] += 1;
            }
        }
        if (mode == 2) {
            myPic.setColor(Color.black);
            myPic.drawRect(getWidth() / 2 - 250, 100, 500, 600);
            myPic.setColor(Color.white);
            myPic.fillRect(getWidth() / 2 - 249, 101, 499, 599);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        requestFocus();
        setFocusTraversalKeysEnabled(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        re = false;
        posX = m.gridX(e.getX());
        posY = m.gridX(e.getY());
        if (posX < 27 && posY < 27) {
            if (e.getButton() == 1) {
                if (re == false) {
                    time = new Timer(150, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (grid[posX][posY][0] == 1) {
                                resC = 0;
                                resN += 0.1;
                            } else if (grid[posX][posY][0] == 2) {
                                resC = 1;
                                resN += 0.1;
                            } else if (grid[posX][posY][0] == 3) {
                                resC = 2;
                                resN += 0.1;
                            } else if (grid[posX][posY][0] == 4) {
                                resC = 3;
                                resN += 0.1;
                            } else if (grid[posX][posY][0] == 5) {
                                resC = 4;
                                resN += 0.1;
                            } else if (grid[posX][posY][0] == 6) {
                                resC = 5;
                                resN += .1;
                            }
                            repaint();
                        }
                    });
                    time.setRepeats(true);
                    time.start();

                    t = 0;
                }
            } else if (e.getButton() == 3) {

            } else if (e.getButton() == 2) {
                String res = "", machine = ", None";
                posX = m.gridX(e.getX());
                posY = m.gridX(e.getY());

                res = m.text(grid[posX][posY][0], grid[posX][posY][1]);
                System.out.println(posX + "," + posY + res + machine);
            }

            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        re = true;
        time.stop();
        resN = 0;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

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

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //key presses
        if (e.getKeyCode() == KeyEvent.VK_E) {
            mode = 2;
            System.out.println("Ff");
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
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
                for (int i = 0; i < 6; i++) {
                    pw.println(rez[i]);
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

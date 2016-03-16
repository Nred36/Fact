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
    long t = 0;
    int[][][] grid = new int[27][27][8];
    int[] rez = new int[6];
    boolean re = false;

    public Factory() {//program name
        timer = new Timer(60, this);
        timer.setInitialDelay(100);     //starts timer        
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

        f.setVisible(true); //makes it visible
        f.setResizable(false);//makes in unsizable
        f.setBounds(700, 0, 875, 839);
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

        System.out.println(rez[0]);
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
        myPic.setColor(Color.black);
        for (int i = 0; i < 6; i++) {
            myPic.fillRect(816, i * 36 + 49, 18, 18);
            myPic.drawString(rez[i] + "", 837, i * 36 + 62);
        }
        //Iron
        myPic.setColor(Color.orange);
        myPic.fillRect(817, 50, 16, 16);
        //Copper
        myPic.setColor(Color.red);
        myPic.fillRect(817, 86, 16, 16);
        //Stone
        myPic.setColor(Color.lightGray);
        myPic.fillRect(817, 122, 16, 16);
        //Water
        myPic.setColor(Color.blue);
        myPic.fillRect(817, 158, 16, 16);
        //Oil
        myPic.setColor(Color.black);
        myPic.fillRect(817, 194, 16, 16);
        //Coal
        myPic.setColor(Color.darkGray);
        myPic.fillRect(817, 230, 16, 16);
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
        if (e.getKeyCode() == KeyEvent.VK_E) {

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
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        re = true;
        initThread();
        if (e.getButton() == 1) {
            System.out.println("p");
        }
        re = false;
        posX = (int) Math.floor(e.getX() / 30);
        posY = (int) Math.floor(e.getY() / 30);
        if (posX < 27 && posY < 27) {
            if (e.getButton() == 1) {
                Timer timer;
                timer = new Timer(2000, new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (re == false) {
                            if (grid[posX][posY][0] == 1) {
                                rez[0]++;
                            } else if (grid[posX][posY][0] == 2) {
                                rez[1]++;
                            } else if (grid[posX][posY][0] == 3) {
                                rez[2]++;
                            } else if (grid[posX][posY][0] == 4) {
                                rez[3]++;
                            } else if (grid[posX][posY][0] == 5) {
                                rez[4]++;
                            } else if (grid[posX][posY][0] == 6) {
                                rez[5]++;
                            }
                            t = 0;
                        }
                    }
                });

            } else if (e.getButton() == 3) {

            } else if (e.getButton() == 2) {
                String res = "", resNum = "", machine = ", None";
                posX = (int) Math.floor(e.getX() / 30);
                posY = (int) Math.floor(e.getY() / 30);
                if (grid[posX][posY][0] == 0) {
                    res = ", Grass";
                    resNum = "Infinte";
                } else if (grid[posX][posY][0] == 1) {
                    res = ", Iron";
                    resNum = String.valueOf(grid[posX][posY][1]);
                } else if (grid[posX][posY][0] == 2) {
                    res = ", Copper";
                    resNum = String.valueOf(grid[posX][posY][1]);
                } else if (grid[posX][posY][0] == 3) {
                    res = ", Stone";
                    resNum = String.valueOf(grid[posX][posY][1]);
                } else if (grid[posX][posY][0] == 4) {
                    res = ", Water";
                    resNum = "Infinte";
                } else if (grid[posX][posY][0] == 5) {
                    res = ", Oil";
                    resNum = String.valueOf(grid[posX][posY][1]);
                } else if (grid[posX][posY][0] == 6) {
                    res = ", Coal";
                    resNum = String.valueOf(grid[posX][posY][1]);
                }
                System.out.println(posX + "," + posY + res + ": " + resNum + machine);
            }

            repaint();
        }
    }
    volatile private boolean isRunning = false;

    private synchronized boolean checkAndMark() {
        if (isRunning) {
            return false;
        }
        isRunning = true;
        return true;
    }

    private void initThread() {
        if (checkAndMark()) {
            new Thread() {
                public void run() {
                    do {
                        //do something
                    } while (re);
                    isRunning = false;
                }
            }.start();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        re = false;
        System.out.println("r");
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
}

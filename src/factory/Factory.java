//*
 /* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;//package name

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
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
    int var, posX, posY, mode = 0, resC, x, y;
    double resN;
    long t = 0;
    int[][][] grid = new int[27][27][8];
    String[] machine = new String[20];
    String[] res = new String[7];
    double[] rez = new double[6];
    int[][] cost = new int[20][7];
    int[][] inv = new int[6][2];
    boolean re = true;
    Timer time;
    String text = "";

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
                rez[i] = Double.parseDouble(br.readLine());
            }
            for (int i = 0; i < 20; i++) {
                machine[i] = br.readLine();
            }
            for (int i = 0; i < 7; i++) {
                res[i] = br.readLine();
            }
            for (int c = 0; c < 20; c++) {
                for (int r = 0; r < 7; r++) {
                    cost[c][r] = Integer.parseInt(br.readLine());
                }
            }
            for (int i = 0; i < 6; i++) {
                inv[i][0] = Integer.parseInt(br.readLine());
                inv[i][1] = Integer.parseInt(br.readLine());
            }
            System.out.println("Loaded");//if it works
        } catch (IOException a) {
            System.out.println("Couldn't Load");//if it fails
        }
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);

        Timer run = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (int c = 0; c < 27; c++) {
                    for (int r = 0; r < 27; r++) {
                        if (grid[c][r][2] == 0) {
                            if (grid[c][r][0] != 0 && grid[c][r][0] < 7) {
                                rez[grid[c][r][0] - 1] += 1;
                            }
                            System.out.println(c);
                            if (grid[c+1][r][0] != 0 && grid[c+1][r][0] < 7) {
                                rez[grid[c + 1][r][0] - 1] += 1;
                            }
                            if (grid[c][r+1][0] != 0 && grid[c][r+1][0] < 7) {
                                rez[grid[c][r + 1][0] - 1] += 1;
                            }
                            if (grid[c+1][r+1][0] != 0 && grid[c][r][0] < 7) {
                                rez[grid[c + 1][r + 1][0] - 1] += 1;
                            }
                        }
                    }
                }
            }
        }
        );
        run.setRepeats(true);
        run.start();
    }

    public static void main(String[] args) {
        JFrame f = new JFrame(""); //name on program
        JApplet applet = new Factory();        //sets up the window
        f.getContentPane().add("Center", applet);
        applet.init();
        f.pack();

        f.setVisible(true); //makes it visible
        f.setResizable(false);//makes in unsizable
        f.setBounds(500, 25, 902, 929);
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
        myPic.setFont(new Font("Dialog", Font.PLAIN, 12));

        if (mode == 0) {
            myPic.setFont(new Font("Dialog", Font.PLAIN, 24));
            myPic.drawString("New", getWidth() / 2, 100);
            myPic.drawString("Continue", getWidth() / 2, 200);
            //myPic.fillRect(12,12,333,444);
        }
        if (mode == 1 || mode == 2 || mode == 3 || mode == 4) {
            for (int c = 0; c < 27; c++) {
                for (int r = 0; r < 27; r++) {
                    myPic.setColor(Color.black);
                    myPic.drawRect(c * 31, r * 31, 31, 31);
                    myPic.setColor(m.color(grid[c][r][0]));
                    myPic.setColor(m.iColor(grid[c][r][2]));
                    myPic.fillRect(c * 31 + 1, r * 31 + 1, 30, 30);
                }
            }

            for (int i = 0; i < 6; i++) {//Side Bars
                myPic.setColor(Color.black);
                myPic.fillRect(843, i * 36 + 49, 18, 18);
                myPic.drawString((int) rez[i] + "", 864, i * 36 + 62);
                myPic.drawRect(i * 65 + 250, 839, 60, 60);
                myPic.setColor(m.color(i + 1));
                myPic.fillRect(844, i * 36 + 50, 16, 16);
                myPic.setColor(Color.white);
                myPic.setColor(m.iColor(inv[i][0]));//Item
                myPic.fillRect(i * 65 + 251, 840, 59, 59);
                myPic.setColor(Color.white);
                myPic.drawString((int) inv[i][1] + "", i * 65 + 302, 850);
            }
            myPic.setColor(Color.black);
            myPic.drawString(text, 675, 875);//Info

            myPic.drawRect(10, 854, 45, 20);//Progress Bar
            myPic.drawRect(55, 854, 45, 20);
            myPic.drawRect(100, 854, 45, 20);
            myPic.drawRect(145, 854, 45, 20);
            myPic.drawRect(190, 854, 45, 20);
            myPic.setColor(Color.red);//Progress Bar Inside
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
            myPic.setColor(Color.white);
            myPic.fillRect(getWidth() / 2 - 249, 101, 499, 599);
            myPic.setColor(Color.black);
            myPic.drawRect(getWidth() / 2 - 250, 100, 500, 600);
            for (int i = 0; i < 20; i++) {
                myPic.drawString(machine[i], 220, i * 29 + 140);
                myPic.drawString("Buy", 665, i * 29 + 140);
                myPic.drawRect(663, i * 29 + 128, 23, 15);
                myPic.drawString(cost[i][1] + "", 345, i * 29 + 140);
                myPic.drawString(cost[i][2] + "", 394, i * 29 + 140);
                myPic.drawString(cost[i][3] + "", 469, i * 29 + 140);
                myPic.drawString(cost[i][4] + "", 532, i * 29 + 140);
                myPic.drawString(cost[i][5] + "", 584, i * 29 + 140);
                myPic.drawString(cost[i][6] + "", 632, i * 29 + 140);

            }
            myPic.drawString(res[0], 340, 120);
            myPic.drawString(res[1], 385, 120);
            myPic.drawString(res[2], 456, 120);
            myPic.drawString(res[3], 518, 120);
            myPic.drawString(res[4], 579, 120);
            myPic.drawString(res[5], 623, 120);
        }
        if (mode == 3) {
            myPic.setColor(m.iColor(inv[resC][0]));//Item   
            if (m.size(resC) == 0) {
                myPic.fillRect(x, y, 30, 30);
            } else if (m.size(resC) == 1) {
                myPic.fillRect(x, y, 61, 61);
            }
        }
        if (mode == 4) {
            myPic.setColor(Color.white);
            myPic.fillRect(getWidth() / 2 - 249, 101, 499, 599);
            myPic.setColor(Color.black);
            myPic.drawRect(getWidth() / 2 - 250, 100, 500, 600);
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

        Rectangle p = new Rectangle(e.getX(), e.getY(), 1, 1);
        if (mode == 0) {

        } else if (mode == 1) {
            for (int i = 0; i < 6; i++) {
                Rectangle r = new Rectangle(i * 65 + 250, 839, 60, 60);
                if (p.intersects(r) && inv[i][0] != 99) {
                    mode = 3;
                    resC = inv[i][0];
                }
            }
            if (m.grid(x) < 27 && m.grid(y) < 27 && grid[m.grid(x)][m.grid(y)][2] == 1) {
                mode = 4;
            }
        } else if (mode == 2) {
            for (int i = 0; i < 20; i++) {
                Rectangle r = new Rectangle(663, i * 29 + 128, 23, 15);
                if (p.intersects(r)) {
                    if ((cost[i][1] <= rez[0]) && (cost[i][2] <= rez[1]) && (cost[i][3] <= rez[2]) && (cost[i][4] <= rez[3]) && (cost[i][5] <= rez[4]) && (cost[i][6] <= rez[5])) {
                        for (int c = 0; c < 6; c++) {
                            rez[c] -= cost[i][c + 1];
                        }
                        for (int c = 0; c < 6; c++) {
                            if (inv[c][0] == 99 || inv[c][0] == i) {
                                inv[c][0] = i;
                                inv[c][1] += 1;
                                c = 10;
                                i = 30;
                            }
                        }
                    }
                }
            }

        } else if (mode == 3) {
            posX = m.grid(e.getX());
            posY = m.grid(e.getY());
            if (posX < 27 && posY < 27) {
                if (m.size(resC) == 0) {
                    grid[posX][posY][2] = resC;
                } else if (m.size(resC) == 1) {
                    grid[posX + 1][posY][2] = resC;
                    grid[posX][posY + 1][2] = resC;
                    grid[posX + 1][posY + 1][2] = resC;
                    grid[posX][posY][2] = resC;
                }
                inv[resC][1] -= 1;
                if (inv[resC][1] == 0) {
                    inv[resC][0] = 99;
                    inv[resC][1] = 0;
                }
                System.out.println(grid[posX][posY][2]);
            }
            mode = 1;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        re = false;
        posX = m.grid(e.getX());
        posY = m.grid(e.getY());
        if (posX < 27 && posY < 27) {
            if (e.getButton() == 1) {
                if (mode == 1) {
                    if (re == false) {
                        //time = new Timer(350, new ActionListener() {
                        time = new Timer(35, new ActionListener() {
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

                }
            } else if (e.getButton() == 3) {

            } else if (e.getButton() == 2) {
                String machine = ", None";
                posX = m.grid(e.getX());
                posY = m.grid(e.getY());

                text = posX + "," + posY + m.text(grid[posX][posY][0], grid[posX][posY][1]) + machine;
            }

            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == 1 && mode == 1 && m.grid(e.getX()) < 27 && m.grid(e.getY()) < 27) {
            re = true;
            time.stop();
            resN = 0;
        }
        if (mode == 0) {
            mode = 1;
        }
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
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //key presses
        if (e.getKeyCode() == KeyEvent.VK_E) {
            if (mode == 1) {
                mode = 2;
            } else {
                mode = 1;
            }
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            mode = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            //runs if escape is pressed

            try {
                FileWriter fw = new FileWriter("save.txt");//set place to write to in "Files"
                PrintWriter pw = new PrintWriter(fw); //starts writing
                for (int i = 0; i < 8; i++) {
                    for (int c = 0; c < 27; c++) {
                        for (int r = 0; r < 27; r++) {
                            // grid[c][r][2] = 99;
                            pw.println(grid[c][r][i]);
                        }
                    }
                }
                for (int i = 0; i < 6; i++) {
                    pw.println(rez[i]);
                }
                for (int i = 0; i < 20; i++) {
                    pw.println(machine[i]);
                }
                for (int i = 0; i < 7; i++) {
                    pw.println(res[i]);
                }
                for (int c = 0; c < 20; c++) {
                    for (int r = 0; r < 7; r++) {
                        pw.println(cost[c][r]);
                    }
                }
                for (int i = 0; i < 6; i++) {
                    pw.println(inv[i][0]);
                    pw.println(inv[i][1]);
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

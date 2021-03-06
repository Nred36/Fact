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
import javax.swing.ImageIcon;
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
    int var, posX, posY, mode = 1, resC, x, y;
    double resN;
    long t = 0;
    String[] picz = new String[30];
    Image[] img = new Image[30];
    int[][][] grid = new int[27][27][9];
    String[] machine = new String[20];
    String[] res = new String[7];
    double[] rez = new double[8];
    int[][] cost = new int[20][7];
    int[][] inv = new int[6][2];
    boolean re = true, first = false;
    Timer time;
    String text = "";

    public Factory() {//program name
        /*
         mode 0: Main Menu
         mode 1: Game
         mode 2: Craft
         mode 3: Placer        
         mode 4: Inventory
         mode 5: Furnace
         */
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
            first = Boolean.parseBoolean(br.readLine());
            for (int i = 0; i < 9; i++) {
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
            for (int i = 0; i < picz.length; i++) {
                picz[i] = br.readLine();
                img[i] = new ImageIcon(picz[i]).getImage();
            }
            System.out.println("Loaded");//if it works
        } catch (IOException a) {
            System.out.println("Couldn't Load");//if it fails
        }
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);

        if (first == true) {
            for (int c = 0; c < 27; c++) {
                for (int r = 0; r < 27; r++) {
                    if (grid[c][r][0] != 0 && grid[c][r][0] < 7) {
                        grid[c][r][1] = m.rNum(500, 2500);
                    }
                }
            }
        }
        Timer run = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (int c = 0; c < 27; c++) {
                    for (int r = 0; r < 27; r++) {
                        if (grid[c][r][2] == 0) {
                            if (m.sizeX(resC) >= 30 && grid[c][r][0] != 0 && grid[c][r][0] < 7 && c < 27 && r < 27) {
                                rez[grid[c][r][0] - 1] += 1;
                                grid[c][r][1] -= 1;
                            }
                            if (c < 26 && r < 27 && m.sizeX(resC) >= 60 && grid[c + 1][r][0] != 0 && grid[c + 1][r][0] < 7) {
                                rez[grid[c + 1][r][0] - 1] += 1;
                                grid[c + 1][r][1] -= 1;
                            }
                            if (c < 27 && r < 26 && m.sizeY(resC) >= 60 && grid[c][r + 1][0] != 0 && grid[c][r + 1][0] < 7) {
                                rez[grid[c][r + 1][0] - 1] += 1;
                                grid[c][r + 1][1] -= 1;
                            }
                            if (c < 26 && r < 26 && m.sizeY(resC) >= 60 && m.sizeX(resC) >= 60 && grid[c + 1][r + 1][0] != 0 && grid[c + 1][r + 1][0] < 7) {
                                rez[grid[c + 1][r + 1][0] - 1] += 1;
                                grid[c + 1][r + 1][1] -= 1;
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
        f.setBounds(480, -25, 902, 929);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //stops program if you x out the window
    }
// <editor-fold defaultstate="collapsed" desc="paint">

    public void paint(Graphics g) {
        dbImage = createImage(getWidth(), getHeight());      //creats and image the size of the screen
        dbg = dbImage.getGraphics();        //double buffers the panel
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }
// </editor-fold>

    public void paintComponent(Graphics g) {
        myPic = (Graphics2D) g;
        myPic.setFont(new Font("Dialog", Font.PLAIN, 12));

        if (mode == 0) {
            myPic.setFont(new Font("Dialog", Font.PLAIN, 24));
            myPic.drawString("New", getWidth() / 2, 100);
            myPic.drawString("Continue", getWidth() / 2, 200);
            //myPic.fillRect(12,12,333,444);
        } else if (mode != 10) {
            for (int c = 0; c < 27; c++) {
                for (int r = 0; r < 27; r++) {
                    myPic.setColor(Color.black);
                    myPic.drawRect(c * 31, r * 31, 31, 31);
                    myPic.drawImage(img[grid[c][r][0]], c * 31 + 1, r * 31 + 1, 30, 30, null);
                    myPic.drawImage(img[grid[2][r][0]+10], c * 31 + 1, r * 31 + 1, 30, 30, null);
                    if (grid[c][r][1] == 0 && grid[c][r][0] < 7 && grid[c][r][0] != 0) {
                        grid[c][r][0] = 0;
                    }
                }
            }

            for (int i = 0; i < 6; i++) {//Side Bars
                myPic.setColor(Color.black);
                myPic.fillRect(843, i * 36 + 49, 18, 18);//res ring
                myPic.drawString((int) rez[i] + "", 864, i * 36 + 62);//resource number
                myPic.drawRect(i * 65 + 250, 839, 60, 60);
                myPic.setColor(m.color(i + 1));//res colour
                myPic.fillRect(844, i * 36 + 50, 16, 16);//resource display
                myPic.setColor(Color.white);
                myPic.setColor(m.iColor(inv[i][0]));//Item
                myPic.fillRect(i * 65 + 251, 840, 59, 59);//inventory
                myPic.setColor(Color.white);
                myPic.drawString((int) inv[i][1] + "", i * 65 + 302, 850);//Num Items
            }
            for (int i = 0; i < 2; i++) {
                myPic.setColor(Color.black);
                myPic.fillRect(843, i * 36 + 265, 18, 18);//res ring
                myPic.drawString((int) rez[i + 6] + "", 864, i * 36 + 278);//resource number               
                myPic.setColor(m.color(i + 8));//res colour
                myPic.fillRect(844, i * 36 + 266, 16, 16);//resource display
            }

            myPic.setColor(Color.black);
            myPic.drawString(text, 175, 175);//Info

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
                //store[0][0][0][0]=1;
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
            myPic.fillRect(x, y, m.sizeX(resC), m.sizeY(resC));

        }
        if (mode == 4) {//Inventory
            myPic.setColor(Color.white);
            myPic.fillRect(getWidth() / 2 - 199, 160, 399, 410);
            myPic.setColor(Color.black);
            myPic.setFont(new Font("Dialog", Font.PLAIN, 15));
            myPic.drawString("Inventory", getWidth() / 2 - 29, 180);
            myPic.drawRect(getWidth() / 2 - 200, 159, 400, 411);
            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 5; y++) {
                    myPic.setColor(Color.black);
                    myPic.drawRect(x * 70 + (getWidth() / 2 - 175), y * 70 + 195, 70, 70);
                    //myPic.setColor(m.color(invent[x][y][0][0]));
                    myPic.fillRect(x * 70 + (getWidth() / 2 - 165), y * 70 + 205, 50, 50);
                }
            }
        }
        if (mode == 5 || mode == 6) {//Furnace
            myPic.setColor(Color.white);
            myPic.fillRect(293, 160, 299, 310);
            myPic.setColor(Color.black);
            myPic.setFont(new Font("Dialog", Font.PLAIN, 15));
            myPic.drawString("Furnace", 421, 180);
            myPic.drawRect(292, 159, 300, 311);

            myPic.drawRect(340, 210, 70, 70);
            myPic.drawRect(474, 210, 70, 70);

            myPic.setColor(m.color(grid[posX][posY][7]));
            myPic.fillRect(341, 211, 69, 69);
            myPic.setColor(m.color(6));
            myPic.fillRect(475, 211, 69, 69);

            myPic.setColor(Color.white);
            myPic.drawString((int) grid[posX][posY][7] + "", 400, 225);//Num Items
            myPic.drawString((int) grid[posX][posY][5] + "", 534, 225);//Num Fuel
        }
        if (mode == 6) {
            myPic.setColor(Color.RED);//Item
            myPic.fillRect(x, y, 12, 12);
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
        if (e.getButton() == 1) {
            Rectangle p = new Rectangle(e.getX(), e.getY(), 1, 1);
            if (mode == 0) {

            } else if (mode == 1) {
                posX = m.grid(e.getX(), posX);
                posY = m.grid(e.getY(), posY);
                for (int i = 0; i < 6; i++) {
                    Rectangle r = new Rectangle(i * 65 + 250, 839, 60, 60);
                    if (p.intersects(r) && inv[i][0] != 99) {
                        mode = 3;
                        resC = inv[i][0];
                    }
                }
                if (grid[posX][posY][2] == 1) {
                    mode = 5;
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
                posX = m.grid(e.getX(), posX);
                posY = m.grid(e.getY(), posY);
                if (m.sizeX(resC) >= 30) {
                    grid[posX][posY][2] = resC;
                }
                if (m.sizeX(resC) >= 60 && posX < 26) {
                    grid[posX + 1][posY][2] = resC;
                }
                if (m.sizeY(resC) >= 60 && posY < 26) {
                    grid[posX][posY + 1][2] = resC;
                }
                if (m.sizeY(resC) >= 60 && m.sizeX(resC) >= 60 && posX < 26 && posY < 26) {
                    grid[posX + 1][posY + 1][2] = resC;
                }

                inv[resC][1] -= 1;
                if (inv[resC][1] <= 0) {
                    inv[resC][0] = 99;
                    inv[resC][1] = 0;
                }

                mode = 1;
            } else if (mode == 5) {
                for (int i = 0; i < 6; i++) {
                    Rectangle res = new Rectangle(843, i * 36 + 49, 18, 18);
                    if (p.intersects(res)) {
                        mode = 6;
                        resC = i;
                    }
                }
            } else if (mode == 6) {
                Rectangle i = new Rectangle(340, 210, 70, 70);
                Rectangle f = new Rectangle(474, 210, 70, 70);
                if (p.intersects(i)) {
                    mode = 5;
                    grid[posX][posY][7] = resC + 1;
                    grid[posX][posY][8]++;
                    rez[0]--;
                } else if (p.intersects(f)) {
                    mode = 5;
                    if (m.fuel(resC) == true) {
                        grid[posX][posY][5]++;
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        re = false;

        if (e.getButton() == 1) {
            if (mode == 1) {
                posX = m.grid(e.getX(), posX);
                posY = m.grid(e.getY(), posY);
                if (re == false) {
                    //time = new Timer(350, new ActionListener() {
                    time = new Timer(1, new ActionListener() {
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
                            if (resN >= 1.2) {
                                grid[posX][posY][1] -= 1;
                            }
                        }
                    });
                    time.setRepeats(true);
                    time.start();
                    t = 0;
                }
            }
        } else if (e.getButton() == 3) {

        } else if (e.getButton() == 2) {
            posX = m.grid(e.getX(), posX);
            posY = m.grid(e.getY(), posY);

            text = posX + "," + posY + m.text(grid[posX][posY][0], grid[posX][posY][1]) + grid[posX][posY][3] + ": " + grid[posX][posY][5];
        }

        repaint();

    }

    @Override
    public void mouseReleased(MouseEvent e
    ) {
        if (e.getButton() == 1 && mode == 1 && m.grid(e.getX(), posX) < 27 && m.grid(e.getY(), posY) < 27) {
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
    public void mouseEntered(MouseEvent e
    ) {

    }

    @Override
    public void mouseExited(MouseEvent e
    ) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e
    ) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e
    ) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void keyTyped(KeyEvent e
    ) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e
    ) {
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            System.exit(1);
        }
        //key presses
        if (e.getKeyCode() == KeyEvent.VK_E) {
            if (mode == 1) {
                mode = 2;
            } else {
                mode = 1;
            }
            repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (mode != 1) {
                mode = 1;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_TAB) {
            if (mode == 1) {
                mode = 4;
            } else {
                mode = 1;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_F5) {
            try {
                FileWriter fw = new FileWriter("save.txt");//set place to write to in "Files"
                PrintWriter pw = new PrintWriter(fw); //starts writing
                pw.println(first);
                for (int i = 0; i < 9; i++) {
                    for (int c = 0; c < 27; c++) {
                        for (int r = 0; r < 27; r++) {
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
                System.out.println("Saved");
                pw.close();
            } catch (IOException a) {
                System.out.println("ERROR");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e
    ) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import java.awt.Color;

/**
 *
 * @author naree1878
 */
public class Methods {

    public int rNum(int min, int max) {
        int rnd = (int) Math.ceil(Math.random() * (max - min + 1) + (min - 1));
        return (rnd);
    }

    public int gridX(int x) {
        int posX = (int) Math.floor(x / 31);
        return (posX);
    }

    public int gridY(int y) {
        int posY = (int) Math.floor(y / 31);
        return (posY);
    }

    public int wait(int t) {
        long i = System.currentTimeMillis();

        while ((System.currentTimeMillis() - i) / 1000 < t) {
        }
        return (1);
    }

    public String text(int i, int n) {
        String o = "", res = "", resNum = "";

        if (i == 0) {
            res = ", Grass";
            resNum = "Infinte";
        } else if (i == 1) {
            res = ", Iron";
            resNum = String.valueOf(n);
        } else if (i == 2) {
            res = ", Copper";
            resNum = String.valueOf(n);
        } else if (i == 3) {
            res = ", Stone";
            resNum = String.valueOf(n);
        } else if (i == 4) {
            res = ", Water";
            resNum = "Infinte";
        } else if (i == 5) {
            res = ", Oil";
            resNum = String.valueOf(n);
        } else if (i == 6) {
            res = ", Coal";
            resNum = String.valueOf(n);
        }
        o = res + ": " + resNum;
        return (o);
    }

    public Color color(int i) {
        Color c;
        if (i == 0) {//Grass
            c = new Color(0, 255, 0);
        } else if (i == 1) {//Iron
            c = new Color(255, 150, 50);
        } else if (i == 2) {//Copper
            c = new Color(255, 0, 0);
        } else if (i == 3) {//Stone
            c = new Color(175, 175, 175);
        } else if (i == 4) {//Water
            c = new Color(0, 0, 255);
        } else if (i == 5) {//Oil
            c = new Color(20, 20, 20);
        } else if (i == 6) {//Coal
            c = new Color(45, 45, 45);
        } else {
            c = new Color(0, 0, 0);
        }

        return (c);
    }

}

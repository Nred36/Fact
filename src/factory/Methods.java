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

    private int sizeY = 0;

    public int rNum(int min, int max) {
        int rnd = (int) Math.ceil(Math.random() * (max - min + 1) + (min - 1));
        return (rnd);
    }

    public int grid(int x) {
        int pos = (int) Math.floor(x / 31);
        return (pos);
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
            res = ", Forest";
            resNum = String.valueOf(n);
        } else if (i == 5) {
            res = ", Oil";
            resNum = String.valueOf(n);
        } else if (i == 6) {
            res = ", Coal";
            resNum = String.valueOf(n);
        } else if (i == 7) {
            res = ", Water";
            resNum = "Infinte";
        }
        o = res + ": " + resNum;
        return (o);
    }

    public int sizeX(int i) {
        if (i == 0) {//Ecavator
            i = 30;
            sizeY = 30;
        } else if (i == 1) {//Crate
            i = 60;
            sizeY = 60;
        } else if (i == 2) {//Storage Container

        } else if (i == 3) {//Conveyor Belt

        } else if (i == 4) {//Pump

        } else if (i == 5) {//Pipe

        } else if (i == 6) {//Boiler

        } else if (i == 7) {//Steam Turbine

        } else if (i == 8) {//Power Line

        } else if (i == 9) {//

        } else if (i == 10) {//

        } else if (i == 11) {//

        } else if (i == 12) {//

        } else if (i == 13) {//

        } else if (i == 14) {//

        } else if (i == 15) {//

        } else if (i == 16) {//

        } else if (i == 17) {//

        } else if (i == 18) {//

        } else if (i == 19) {//

        }
        return (i);
    }

    public int sizeY() {
        return (sizeY);
    }

    public Color iColor(int i) {
        Color c;
        if (i == 0) {//Ecavator
            c = new Color(25, 0, 0);
        } else if (i == 1) {//Crate
            c = new Color(50, 0, 0);
        } else if (i == 2) {//Storage Container
            c = new Color(75, 0, 0);
        } else if (i == 3) {//Conveyor Belt
            c = new Color(100, 0, 0);
        } else if (i == 4) {//Pump
            c = new Color(125, 0, 0);
        } else if (i == 5) {//Pipe
            c = new Color(150, 0, 0);
        } else if (i == 6) {//Boiler
            c = new Color(175, 0, 0);
        } else if (i == 7) {//Steam Turbine
            c = new Color(200, 0, 0);
        } else if (i == 8) {//Power Line
            c = new Color(225, 0, 0);
        } else if (i == 9) {//
            c = new Color(250, 0, 0);
        } else if (i == 10) {//
            c = new Color(0, 25, 0);
        } else if (i == 11) {//
            c = new Color(0, 50, 0);
        } else if (i == 12) {//
            c = new Color(0, 75, 0);
        } else if (i == 13) {//
            c = new Color(0, 100, 0);
        } else if (i == 14) {//
            c = new Color(0, 125, 0);
        } else if (i == 15) {//
            c = new Color(0, 150, 0);
        } else if (i == 16) {//
            c = new Color(0, 175, 0);
        } else if (i == 17) {//
            c = new Color(0, 200, 0);
        } else if (i == 18) {//
            c = new Color(0, 225, 0);
        } else if (i == 19) {//
            c = new Color(0, 250, 0);
        } else {
            c = null;
        }
        return (c);
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
        } else if (i == 4) {//Forest
            c = new Color(255, 255, 45);
        } else if (i == 5) {//Oil
            c = new Color(20, 20, 20);
        } else if (i == 6) {//Coal
            c = new Color(45, 45, 45);
        } else if (i == 7) {//Water
            c = new Color(0, 0, 255);
        } else {
            c = new Color(0, 0, 0);
        }

        return (c);
    }

}

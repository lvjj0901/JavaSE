package com.ljj.designpattern.decoratorproxyadapotor;

/**
 * Description:
 *
 * @Author Jason Lyu
 * @Create 2025-04-11 7:16 p.m.
 * @Version 1.0
 */
public class AdapterPattern {
    public static void main(String[] args) {
        LegacyRectangle legacyRectangle = new LegacyRectangle();
        Shape shape = new RectangleAdapter(legacyRectangle);
        shape.draw(10,20,50,30);
    }
}

/**
 * target
 */
interface Shape{
    void draw(int x,int y, int width,int height);
}

/**
 * Adaptee
 */
class LegacyRectangle{
    //Old version rectangle drawing method:
    // parameters are upper left corner(x1,y1) and lower right corner(x2,y2)
    public void display(int x1,int y1,int x2,int y2){
        System.out.printf("LegacyRectangle: (%d,%d) to (%d,%d)\n", x1, y1, x2, y2);
    }
}

/**
 * adapter
 */

class RectangleAdapter implements Shape{
    private LegacyRectangle adaptee;
    public RectangleAdapter(LegacyRectangle adaptee){
        this.adaptee = adaptee;
    }
    @Override
    public void draw(int x, int y, int width, int height) {
        int x2 = x+width;
        int y2 = y+height;
        adaptee.display(x,y,x2,y2);
    }
}
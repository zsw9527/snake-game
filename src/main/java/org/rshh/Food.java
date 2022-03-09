package org.rshh;

import java.util.Random;

/**
 * 食物
 */
public class Food {
    private int x;
    private int y;

    public Food() {
        //生成位置随机
        Random rd = new Random();
        this.x = rd.nextInt(8) * 50;
        this.y = rd.nextInt(8) * 50;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void reBorn() {
        Random rd = new Random();
        this.x = rd.nextInt(8) * 50;
        this.y = rd.nextInt(8) * 50;
    }
}

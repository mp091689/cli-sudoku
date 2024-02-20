package com.mp091689.clisudoku;

public class Cursor {
    private int xMin;
    private int xMax;
    private int yMin;
    private int yMax;
    private int x;
    private int y;

    public Cursor(int xMin, int xMax, int yMin, int yMax, int x, int y) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.x = x;
        this.y = y;
    }

    public int getxMin() {
        return xMin;
    }

    public void setxMin(int xMin) {
        this.xMin = Math.min(getxMin(), xMin);
        if (x > xMin) {
            this.x = xMin;
        }
    }

    public int getxMax() {
        return xMax;
    }

    public void setxMax(int xMax) {
        this.xMax = Math.max(getxMax(), xMax);
        if (x > xMax) {
            this.x = xMax;
        }
    }

    public int getyMin() {
        return yMin;
    }

    public void setyMin(int yMin) {
        this.yMin = Math.min(getyMin(), yMin);
        if (y > yMin) {
            this.y = yMin;
        }
    }

    public int getyMax() {
        return yMax;
    }

    public void setyMax(int yMax) {
        this.yMax = Math.max(getyMax(), yMax);
        if (y > yMax) {
            this.y = yMax;
        }
    }

    public int x() {
        return x;
    }

    public void setX(int x) {
        this.x = Math.max(Math.min(x, xMax), xMin);
    }

    public int y() {
        return y;
    }

    public void setY(int y) {
        this.y = Math.max(Math.min(y, yMax), yMin);
    }

    public void up() {
        setY(y() - 1);
    }

    public void down() {
        setY(y() + 1);
    }

    public void left() {
        setX(x() - 1);
    }

    public void right() {
        setX(x() + 1);
    }
}

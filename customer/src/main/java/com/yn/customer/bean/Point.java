package com.yn.customer.bean;

import java.util.Objects;

/**
 * Point
 *
 * @author arthurwang
 * @version 1.0
 * 2025/4/16 15:09
 **/
public class Point {
    private int x;
    private int y;
    private final String desc;

    public Point(int x, int y, String desc) {
        this.x = x;
        this.y = y;
        this.desc = desc;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point point = (Point) obj;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

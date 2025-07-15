package com.yn.customer.designer;

import java.util.HashMap;

/**
 * Flyweight
 *
 * @author arthurwang
 * @version 1.0
 * 2025/6/23 10:30
 **/
public class Flyweight {

    // 抽象享元类
    interface Shape {
        void draw(String color);
    }

    static class Circle implements Shape {
        private final String type = "Circle";
        private int radius;

        public Circle(int radius) {
            this.radius = radius;
        }

        @Override
        public void draw(String color) {
            System.out.println("Circle: Draw() [Color: " + color + ", radius: " + radius + ", type: " + type + "]");
        }
    }

    static class ShapeFactory {
        private static final HashMap<Integer, Shape> circleMap = new HashMap<>();

        public static Shape getCircle(int radius) {
            Shape circle = circleMap.get(radius);

            if (circle == null) {
                circle = new Circle(radius);
                circleMap.put(radius, circle);
                System.out.println("Creating circle of radius: " + radius);
            }
            return circle;
        }
    }

    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        for (int i = 0; i < 10; i++) {
            Shape shape = shapeFactory.getCircle(i);
            shape.draw("red");
        }
    }
}

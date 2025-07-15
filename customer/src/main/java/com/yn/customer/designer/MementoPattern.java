package com.yn.customer.designer;

import java.util.ArrayList;
import java.util.List;

/**
 * MementoPattern
 *
 * @author arthurwang
 * @version 1.0
 * 2025/6/23 10:07
 **/
public class MementoPattern {


    // 创建一个备忘录类
    public static class Memento {
        private String state;

        public Memento(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }
    }

    // 创建发起人类
    public static class Originator {
        private String state;

        public void setState(String state) {
            this.state = state;
            System.out.println("当前状态为：" + state);
        }

        public String getState() {
            return state;
        }

        public Memento createMemento() {
            return new Memento(state);
        }

        public void restoreMemento(Memento memento) {
            state = memento.getState();
            System.out.println("恢复状态为：" + state);
        }
    }

    // 管理备忘录
    public static class Caretaker {
        private List<Memento> mementoList = new ArrayList<Memento>();

        public void add(Memento memento) {
            mementoList.add(memento);
        }

        public Memento get(int index) {
            return mementoList.get(index);
        }
    }

    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        originator.setState("state1");
        caretaker.add(originator.createMemento());

        originator.setState("state2");
        caretaker.add(originator.createMemento());

        originator.setState("state3");

        originator.restoreMemento(caretaker.get(0));
        originator.restoreMemento(caretaker.get(1));
    }
}

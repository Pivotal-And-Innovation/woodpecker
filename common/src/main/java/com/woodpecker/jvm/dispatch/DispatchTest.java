package com.woodpecker.jvm.dispatch;

/**
 * 单分派、多分派演示
 *
 * @author Glenn
 * @since 2017-03-24
 */
public class DispatchTest {

    private static class QQ {
    }
    private static class _360 {
    }

    private static class Father {
        public void HardChoice(QQ arg) {
            System.out.println("Father choose qq.");
        }

        public void HardChoice(_360 arg) {
            System.out.println("Father choose _360.");
        }
    }

    private static class Son extends Father {
        @Override
        public void HardChoice(QQ arg) {
            System.out.println("Son choose qq.");
        }

        @Override
        public void HardChoice(_360 arg) {
            System.out.println("Son choose _360.");
        }
    }

    public static void main(String[] args) {
        Father father = new Father();
        Father son = new Son();
        father.HardChoice(new _360());
        son.HardChoice(new QQ());
    }

}

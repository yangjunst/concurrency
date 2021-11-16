package demo.sub;

import demo.ConstructorDemo;

class Sub extends ConstructorDemo {
    protected Sub(String str) {
        super(str);
    }

    public static void main(String[] args) {
        new Sub("hello");
    }
}

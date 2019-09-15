package com.lyj;

public class Main {

    //客户端调用
    public static void main(String[] args) {
        Demo demo = new DemoService().getDemoPort();
        String response = demo.hello("hi");
        System.out.println(response);
    }
}

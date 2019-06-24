package com.my.thread.first;

import java.util.concurrent.TimeUnit;

/**
 * @author GP12161
 * @ClassName FirstThreadDemo
 * Date 2019/6/24 15:34
 **/
public class FirstThreadDemo implements Runnable {

    public static Integer money = 100;

    @Override
    public void run() {
        if (money > 0) {
            synchronized (FirstThreadDemo.class) {
                System.out.println("当前金额是："+money);
            }
        }
    }

    public static void main(String[] args){
        try{
            Runnable t1=new FirstThreadDemo();
            money += 20;
            new Thread(t1).start();
            TimeUnit.SECONDS.sleep(1);
            Runnable t2 =new FirstThreadDemo();
            money -= 50;
            new Thread(t2).start();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

}

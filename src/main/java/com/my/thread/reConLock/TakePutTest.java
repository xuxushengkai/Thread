package com.my.thread.reConLock;

/**
 * @ClassName TakePutTest
 * @Description TODO
 * Author shengjunjie
 * Date 2019/6/26 14:13
 **/
public class TakePutTest {
    public static void main(String[] args) {
        TakePutThread takePutThread = new TakePutThread();
        for(int i=0; i<15; i++){
            new TakeThread("p"+i,takePutThread).start();
            new PutThread("t"+i,takePutThread,i).start();
        }
    }

    public static class TakeThread extends Thread{

        private TakePutThread take;
        private String name;

        public TakeThread(String name,TakePutThread take) {
            this.name = name;
            this.take = take;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(100);
                System.out.println(name + " is taking");
                take.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static class PutThread extends Thread{

        private TakePutThread take;
        private String name;
        private int num;

        public PutThread(String name,TakePutThread take,int num) {
            this.name = name;
            this.take = take;
            this.num = num;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(100);
                System.out.println(name + "is puting");
                take.put(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

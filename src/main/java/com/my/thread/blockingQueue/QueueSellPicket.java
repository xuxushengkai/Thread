package com.my.thread.blockingQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @ClassName QueueSellPicket
 * @Description TODO
 * Author shengjunjie
 * Date 2019/6/27 10:43
 **/
public class QueueSellPicket {

    ArrayBlockingQueue ticketQueue = new ArrayBlockingQueue(10);
    {
        removeTicket();
    }


    public void addStu(CarStudent stu){

        try {
            System.out.println("当前进入车考学生:"+stu.getName()+"  学号:"+stu.getStuCode());
            ticketQueue.put(stu);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void removeTicket(){
        new Thread(()->{
            while(true){
                try {
                    CarStudent stu = (CarStudent) ticketQueue.take();
                    System.out.println("当前出来考生:"+stu.getName()+"  学号:"+stu.getStuCode());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        QueueSellPicket queueSellPicket = new QueueSellPicket();
        List<String> headName = new ArrayList<>();
        headName.add("张");
        headName.add("李");
        headName.add("赵");
        List<String> bodyName = new ArrayList<>();
        bodyName.add("一");
        bodyName.add("二");
        bodyName.add("三");
        Random random = new Random();
        for(int i=1;i<=10;i++){
            int num  = random.nextInt(3);
            CarStudent ticket  = new CarStudent();
            ticket.setName(headName.get(num) + bodyName.get(num));
            ticket.setStuCode(i);
            queueSellPicket.addStu(ticket);
        }
    }
}

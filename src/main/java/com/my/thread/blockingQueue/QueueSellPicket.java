package com.my.thread.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @ClassName QueueSellPicket
 * @Description TODO
 * Author shengjunjie
 * Date 2019/6/27 10:43
 **/
public class QueueSellPicket {

    ArrayBlockingQueue ticketQueue = new ArrayBlockingQueue(10);

    public void addTicket(TrainTicket ticket){
        try {
            System.out.println("当前放置车票:"+ticket.getName()+"  座位号:"+ticket.getSiteCode());
            ticketQueue.put(ticket);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void removeTicket(){
      while(true){
          try {
              TrainTicket ticket = (TrainTicket) ticketQueue.take();
              System.out.println("当前取出车票:"+ticket.getName()+"  座位号:"+ticket.getSiteCode());
          } catch (InterruptedException e) {
              e.printStackTrace();
              System.out.println(e.getMessage());
          }
      }
    }

    public static void main(String[] args) {

        QueueSellPicket queueSellPicket = new QueueSellPicket();
        for(int i=0;i<10;i++){
            TrainTicket ticket  = new TrainTicket();
            ticket.setName("G62001");
            ticket.setSiteCode(i);
            queueSellPicket.addTicket(ticket);
        }
        queueSellPicket.removeTicket();
    }
}

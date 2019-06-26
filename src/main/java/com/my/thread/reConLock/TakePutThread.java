package com.my.thread.reConLock;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName TakePutThread
 * @Description TODO
 * Author shengjunjie
 * Date 2019/6/26 11:46
 **/
public class TakePutThread<E>{

    //1、初始化数据
    ReentrantLock lock = new ReentrantLock();
    Condition conEmpty = lock.newCondition();
    Condition conFull = lock.newCondition();
    public final int length = 10;
    Queue<E> queue = new ArrayDeque<>(length);

    //2、Put
    public void put(E el) {
        try {
            lock.lock();
            while (queue.size() >= length) {
                System.out.println("队列已满,线程"+Thread.currentThread().getName()
                        + "正在等待空位,需要添加的数据是:"+el);
                conFull.await();
            }
            queue.add(el);
            System.out.println("已添加的数据是:"+el+"，已有"+queue.size()+"个元素加入队列");
            conEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //3、take
    public Object take() {
        E el = null;
        try {
            lock.lock();
            while (queue.isEmpty()) {
                System.out.println("队列为空,线程"+Thread.currentThread().getName() + "等待进入");
                conEmpty.await();
            }
            //放置元素
            el = queue.poll();
            queue.add(el);
            System.out.println("释放的数据是:"+el+"，还剩"+queue.size()+"个元素");
            conFull.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return el;
    }

}

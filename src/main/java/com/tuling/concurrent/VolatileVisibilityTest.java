package com.tuling.concurrent;

/**
 * ClassName:VolatileVisibilityTest
 * Package:com.tuling.concurrent
 * Description:
 *
 * @date:2020/6/8 10:53
 * @author:jiangjing
 */

/**
 * volatile 关键字可以解决多线程的可见性和有序性，但是不能够解决的原子性
 *
 * read  load  use assign  store write
 *
 * 最初使用总线加锁的方式  在read是lock     在write是unlock  当多的线程使用同一个共享变量时就会等待排队  效率低下
 *
 * MESI缓存一致性协议   在store时lock   在write时unlock   加锁范围大大减小
 * 其原理是主要是通过汇编语言lock 前缀指令
 *
 *
 *
 */
public class VolatileVisibilityTest {
    /**
     * 共享数据
     */
    private static volatile boolean initFlag = false;
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("waiting data..");
                while (!initFlag){

                }
                System.out.println("获取到新的数据，initFlag值为"+initFlag);
            }
        }).start();

        Thread.sleep(2000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("数据获取中");
                initFlag=true;
                System.out.println("prepare  end....");

            }
        }).start();

    }

    private String name;
}

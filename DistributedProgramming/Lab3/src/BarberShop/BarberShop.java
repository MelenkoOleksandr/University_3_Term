package BarberShop;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class BarberShop {
   LinkedBlockingQueue<Visitor> visitors = new LinkedBlockingQueue<>();

   public void standInQueue(Visitor visitor) {
       visitors.add(visitor);
   }

   public Visitor getVisitor() throws InterruptedException {
       return visitors.take();
   }

   public boolean hasVisitors() {
       return visitors.size() > 0;
   }
}

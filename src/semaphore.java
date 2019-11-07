import java.util.*;
public class semaphore
  {
    
   //This is an example class for using a primitive synchronization (semaphore, lock). Please note that you
   //can ONLY put the synchronization keyword within these type of classes, and nowhere else within the program.

      private int count = 0;
      public semaphore(int init_val)
      {
        count = init_val;
      }
      public synchronized  void P()
      {
        count = count - 1;
        if ( count < 0) {
          try {
            wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
      public synchronized  void v() {
        count = count + 1;
        if (count <= 0) notify();
      }
  }


import java.util.*;

class user implements Runnable
   {											
       private int id;
       private int num_elements;
       public static Buffer buf;
       private semaphore semaphore;

     public user(int i, int el, Buffer b, semaphore s)							//Created user will add a certain number of elements to the buffer.
     {
         id = i;
         num_elements = el;
         buf = b;
         semaphore = s;

     }

     public void add_elements() throws InterruptedException {
         int n = 0;
         while(num_elements > 0)
         {
             semaphore.P();
             buf.add(new Integer(n));
             n++;
             num_elements--;
             System.out.print("User" + id + " added element" + buf.getElements()  + "/" + buf.getBuf_size() + "\n");
             Thread.sleep(50);
             semaphore.v();
         }
      }

       @Override
       public void run() {
           try {
               this.add_elements();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
   }   
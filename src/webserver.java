import java.util.*;

class webserver implements Runnable
  {										//Web server removes elements from the buffer
    private int id;
    private int num_elements;
    public static Buffer buf;
    private semaphore semaphore;
    public webserver(int id, int el, Buffer b, semaphore s)
    {
      this.id = id;
      num_elements = el;
      buf = b;
      semaphore = s;
    }
    public void remove_elements() throws InterruptedException {

      int n = 0;
      while (num_elements > 0)
      {
        semaphore.P();
        buf.remove(new Integer(n));
        n++;
        System.out.print("Server" + id + " removed element" + buf.getElements()  + "/" + buf.getBuf_size() + "\n");
        Thread.sleep(50);
        semaphore.v();
      }
    }



    @Override
    public void run() {
      try {
        this.remove_elements();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
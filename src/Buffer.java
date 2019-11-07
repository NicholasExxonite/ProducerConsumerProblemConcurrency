import java.util.*;

public class Buffer							//Provides data and operations onto the fixed-length buffer
  {     									
	private LinkedList<Object> buf_list;				
    private int elements;
    private int buf_size;
    private semaphore semaphore;
	
     public Buffer(int n, semaphore s)						//Buffer creation, with n indicating the maximum capacity
	{
	   buf_list = new LinkedList<Object>();
	   elements = 0;
	   buf_size = n;
	   semaphore = s;

    }

      public int getElements() {
          return elements;
      }

      public int getBuf_size() {
          return buf_size;
      }

      public LinkedList<Object> getBuf_list() {
          return buf_list;
      }

      public void add(int e) throws InterruptedException {

        //semaphore.P();
          if (elements == buf_size)
          {
              System.out.print("Buffer full");
              Thread.sleep(50);
              return;
          }
        buf_list.add(e);
        elements++;
        //semaphore.v();
    }
    public void remove(int e) throws InterruptedException {
        // semaphore.P();
        if(elements == 0)
        {
            System.out.print("Buffer empty!");
            Thread.sleep(50);
            return;

        }
        buf_list.remove(e);
        elements--;
        //semaphore.v();

    }
}	  

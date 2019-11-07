import java.util.*;
import java.util.concurrent.Semaphore;

public class startServer
   {
    ArrayList<user> users = new ArrayList<>();
    ArrayList<webserver> servers = new ArrayList<>();
  	Buffer b;										//Creation of buffer object
    private semaphore semaphore = new semaphore(1);

        public startServer(int numElements, int bufferSize, int num_users, int webservers)
   {												//Creates execution scenario between user and webservers on buffer
        
        long startTime = System.currentTimeMillis();		
																
												//Instantiate all objects (webserver, users, buffer)
	b = new Buffer(bufferSize, semaphore);
	
	for (int i = 0; i < num_users; i++)											//Equally subdivide user inputted elements across all user objects
    {
        users.add(new user(i, numElements/num_users, b, semaphore));
    }
    for (int i = 0; i<= webservers; i++)
    {
        servers.add(new webserver(i, (numElements/webservers), b, semaphore));
    }


	for( user u : users)
    {
        Thread thread = new Thread(u);
        thread.start();
    }
    for (webserver s : servers)
    {
        Thread thread = new Thread(s);
        thread.start();
    }
	System.out.println("-----------------------");
	
												//Outputs the total number of elements added/removed from user and webserver		

	System.out.println("-----------------------");
	//System.out.println("Buffer has " + X + " elements remaining");			//Check to see buffer if all elements produced from users have been successfully removed by webservers
	System.out.println("-----------------------");
												//Checks if all users and web servers successfully finished
				
	long endTime = System.currentTimeMillis();
	System.out.println("-----------------------");
     	System.out.println("Program took " + (endTime - startTime) + " milliseconds to complete");		
	
    }
  
public static void main(String[] args)
  {
    int users = 10;
    int servers = 10;
    int totalElements = users*servers;
    int bufferSize = 20;
    //bufferSize = users * servers;
    //semaphore sem = new semaphore(1);

    System.out.println("Enter buffer capacity" +"\n" + bufferSize) ;					//Insert user inputted values for program execution
    
    System.out.println("Enter number of users" + "\n" + users);
    
    System.out.println("Enter number of servers" + "\n" + servers);
    
    System.out.println("Enter total number of elements" + "\n" + totalElements);

    startServer start = new startServer(totalElements, bufferSize, users, servers);
  }
}

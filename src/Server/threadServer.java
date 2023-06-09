import java.io.*;
import java.net.*;

public class threadServer {
    public static void main(String[] args)
    {
        ServerSocket server = null;
        try {

            server = new ServerSocket(8000);
            server.setReuseAddress(true);
            System.out.println("Server started");
			System.out.println("Waiting for a client");
  
            while (true) {
                Socket client = server.accept();
                System.out.println("New client connected @ "
                                   + client.getInetAddress()
                                         .getHostAddress());
  
                ClientHandler clientSock = new ClientHandler(client);
                new Thread(clientSock).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        finally {
                try {
                    server.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
        }   
    }
  
    // ClientHandler class
    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;
  
        // Constructor
        public ClientHandler(Socket socket)
        {
            this.clientSocket = socket;
        }
  
        public void run()
        {
            PrintWriter out = null;
            BufferedReader in = null;
            String command = "";
            try {
                    
                out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
  
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
  
                while (!command.equalsIgnoreCase("QUIT")){

                    try
                    {
                        command = in.readLine();
                        System.out.println("Response for ");
                    }
                    catch(IOException i)
                    {
                        System.out.println(i);
                    }
                }

                // close connection
                clientSocket.close();
                out.close();
                in.close();
                System.out.println("Closing connection");
                
                
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

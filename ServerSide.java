package clientServerPack;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class ServerSide {
	static String connectionTerminatedMsg = "Connection Closed";
	static List<String> endConnectionWords = new ArrayList<String>();
	static private void endArray() {
		endConnectionWords.add("Close");
		endConnectionWords.add("close");
		endConnectionWords.add("Bye");
		endConnectionWords.add("bye");
		endConnectionWords.add("Goodbye");
		endConnectionWords.add("goodbye");
	}
	
    public static void main(String[] args) throws IOException {
    	
        ServerSocket serverSocket = new ServerSocket(5000);
        Socket socket = serverSocket.accept();
        
        endArray();
        
        DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
        DataInputStream inStream = new DataInputStream(socket.getInputStream());
        Scanner sc = new Scanner(System.in);
        
        while(true) {
        //Server MSG to Client
        System.out.print("Server: ");
        String sentMsg = sc.nextLine();
        if (endConnectionWords.contains(sentMsg)) {
        	System.out.println(connectionTerminatedMsg); dataOut.writeUTF(sentMsg); break;} // connection terminator at server side
        dataOut.writeUTF(sentMsg);
        //MSG sent
        
        //Get Client MSG
        String receivedMsg = inStream.readUTF();
        if (endConnectionWords.contains(receivedMsg)) {
        	System.out.println(connectionTerminatedMsg); break;} // connection terminator from Client side
        System.out.println("Client: " + receivedMsg);
        //MSG received
        }
        
      
        sc.close();
        dataOut.close();
        inStream.close();
        socket.close();
        serverSocket.close();


    }
}

package clientServerPack;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;



public class ClientSide {
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
    	
        Socket socket = new Socket("localhost",5000);
        
        endArray();
        
        DataInputStream inStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
        Scanner sc = new Scanner(System.in);
        
        while(true) {
            //Get Server MSG
            String receivedMsg = inStream.readUTF();
            
            if (endConnectionWords.contains(receivedMsg)) {
            	System.out.println(connectionTerminatedMsg); break;} // connection terminator from server side
            
            System.out.println("Server: " + receivedMsg);
            //MSG received
            
            //Client MSG to Server
        	System.out.print("Client: ");
        	String sentMsg = sc.nextLine();
        	
        	if(endConnectionWords.contains(sentMsg)) {
        		System.out.println(connectionTerminatedMsg); dataOut.writeUTF(sentMsg); break;} // connection terminator at client side
        	
        	dataOut.writeUTF(sentMsg);
        	//MSG Sent
        }
        
        sc.close();
        dataOut.close();
        inStream.close();
        socket.close();
        
    }
}

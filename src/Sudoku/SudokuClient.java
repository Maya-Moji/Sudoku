package Sudoku;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class SudokuClient {

	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 9090;
			
	public static void main(String[] args) throws IOException{
		
		Socket socket = new Socket(SERVER_IP,SERVER_PORT); //through this, a connection will be established and the Socket accept() blocking call in Server's side will be removed
		
		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())); //to read from server
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in)); //to read from user
		PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
		
		
		while(true) {
		System.out.println("> ");
		String command = keyboard.readLine();
		
		if(command .equals("quit")) { 
				break;}
		
		out.println(command);
		
		String serverResponse = input.readLine();
		System.out.println("Server says: "+serverResponse);
		}
		
		
		
		socket.close();
		System.exit(0);
		
	}
	
}

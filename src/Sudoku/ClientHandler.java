package Sudoku;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Sudoku.GameView.Splash;

public class ClientHandler implements Runnable{

	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	
	public ClientHandler(Socket clientSocket) throws IOException { //constructor
		this.client  = clientSocket;
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new PrintWriter(client.getOutputStream(),true);
	}
	
	

	
	@Override
	public void run() {
		try {
		//while(true){
			
	        GameView theView = new GameView();
	        GameView.Splash splashObject = theView.new Splash();
	        GameModel theModel = new GameModel();
	        GameController theController = new GameController(theView, theModel);


	        splashObject.SplashScreen();
	        theController.start();
			
//		String request = in.readLine();
//		if(request.contains("name")) {
//			out.println(SudokuServer.getRandomName());
//		} else {
//			out.println("Type 'Tell me a name' to get a random name");
//		}
		//}
		} catch (Exception e) {
			System.err.println("Exception in client handler");
			System.err.println(e.getStackTrace());
		} finally {
		
		System.out.println("[Server] Game opened. Closing.");

		out.close();
		try {
		in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		}
	}
		
	
	
	
	
	
	
}

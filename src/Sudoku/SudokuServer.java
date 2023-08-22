package Sudoku;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Sudoku.GameView.Splash;

import java.io.BufferedReader;


public class SudokuServer {

//	private static String[] names = {"wily", "Felix","Carlsbad","Hobob"};
//	private static String[] adjs = {"the gentle","the un-gentle","the overwrought","the urbane"};
	private static final int PORT = 9090;
	
	private static ArrayList <ClientHandler> clients = new ArrayList <>();
	private static  ExecutorService pool = Executors.newFixedThreadPool(5);
	
	public static void main(String[] args) throws IOException {
		
		ServerSocket listener = new ServerSocket(PORT); //TODO this should be in start button action listener
		
		while(true) {
	        GameView theView = new GameView();
	        GameView.ServerSplash splashObject = theView.new ServerSplash();
	        splashObject.SplashScreen();
	        GameView.ServerUI sUIobject = theView.new ServerUI();
	        
		System.out.println("[Server] Waiting for client connection...");
		// TODO client UI "connect" action goes before the next line
		Socket client = listener.accept(); //has a built in blocking call - accept() method can only handle one client at a time
		System.out.println("[Server] Connected to client!");
		ClientHandler clientThread = new ClientHandler(client);
		clients.add(clientThread);
		
		pool.execute(clientThread); //clientThread is not runnable object

		}

		

	}
		
//	public static String getRandomName() {
//		String name = names[(int)(Math.random()*names.length)];
//		String adj = adjs[(int)(Math.random()*adjs.length)];
//		return name + " " +adj;
//	}
		
	
	
}

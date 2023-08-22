package Sudoku;

/**
 * File name: GameView.java
 * @author [Shaunessee Green & 040830222] / [Maya Jaffary & 041016868]
 * Course: CST8221 -JAP Lab Section 301
 * Assignment: 2.2
 * Professor: Paulo Sousa
 * Date: July 23 2022
 * Compiler: Eclipse IDE Version: 2022-03 (4.23.0) JDK: 11.0.11
 * Purpose: This file performs the calculations for the view class
 */
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import Sudoku.GameView.ServerUI;

/**
 * Class name: GameView Method List: setTableGrid, setNumberButton,
 * validatingTiles, gameDifficulty, showSolution, hideSolution, checkForWinner,
 * addResetListener, addPlayListener, addDimensionListener, addSubmitListener,
 * addNewGameListener, addShowListener, addHideListener, addDifficultyListener,
 * addClearListener, addMenuSolution, addMenuExit, addMenuNewGame, addMenuColor,
 * addMenuAbout,addRandButton Constant list:serialVersionUID Purpose: Performs
 * calculations for the view class
 */
public class GameView extends JFrame {

	/**
	 *  Serializable class declares static final serial version UID field of type long
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * background color
	 */
	private Color myBackGColor = new Color(243, 229, 243);

	/**
	 * reset button
	 */
	JButton resetBut = new JButton("RESET");
	
	/**
	 * design radio button
	 */
	JRadioButton designButton = new JRadioButton("Design");
	
	/**
	 * points label
	 */
	JLabel points = new JLabel("Points: ");
	
	/**
	 * submit button
	 */
	JButton submitBut = new JButton("SUBMIT");
	
	/**
	 * random button
	 */
	JButton randBut = new JButton("RANDOM");
	
	/**
	 * show button
	 */
	JButton showBut = new JButton("SHOW");
	
	/**
	 * hide button
	 */
	JButton hideBut = new JButton("HIDE");
	
	/**
	 * clear button
	 */
	JButton clearBut = new JButton("CLEAR");
	
	/**
	 * new game button
	 */
	JButton newGameBut = new JButton("NEW GAME");
	
	/**
	 * play button
	 */
	JRadioButton playButton = new JRadioButton("Play");

	/**
	 * sudoku table
	 */
	JPanel sudokuTable;
	
	/**
	 * sudoku number buttons
	 */
	JPanel sudokuNums;
	/**
	 * initializing dimension - to be set from difBox
	 */
	int dimChoice = 2;
	
	/**
	 * grid dimension
	 */
	int gridDim = dimChoice * dimChoice;

	/**
	 * creating combo box
	 */
	JComboBox<Object> dimBox;

	/**
	 * difficulty box
	 */
	JComboBox<Object> difBox;

	/**
	 * number buttons
	 */
	private Button[] numbersButtons;
	/**
	 * Array for table fields
	 */
	private JButton[][] tableFields = new JButton[gridDim + 1][gridDim + 1];

	/**
	 * initial history array
	 */
	private String historyArray[][];

	/**
	 * history panel
	 */
	JScrollPane history;

	/**
	 * number buttons' display text
	 */
	private String buttonLabel = "";
	
	/**
	 * timer label
	 */
	JLabel timerLabel;

	/**
	 * timer 
	 */
	private Timer timer;

	/**
	 * Integer indicating which internal grid the user have clicked on.
	 */
	private int square = 0;

	/**
	 * picked number
	 */
	private int pickedNum;

	/**
	 * error for validation
	 */
	private boolean error;

	/**
	 * history text area
	 */
	private JTextArea historyTextArea;

	/**
	 * count for arrayfile
	 */
	private int count = 1;

	/**
	 * to help open saved array
	 */
	final JFrame openFile;

	/**
	 * solution array
	 */
	private String[][] solutionArr;

	/**
	 * temporary array for show and hide solution
	 */
	private String[][] temp;

	/**
	 * number of cells to be showing during difficulty
	 */
	private int cellShowing;

	/**
	 * new game menu item
	 */
	private JMenuItem newGame = new JMenuItem("New");
	
	/**
	 * solution menu item
	 */
	private JMenuItem solution = new JMenuItem("Solution");
	
	/**
	 * exit menu item
	 */
	private JMenuItem exit = new JMenuItem("Exit");
	
	/**
	 * color menu item
	 */
	private JMenuItem colors = new JMenuItem("Colors");
	
	/**
	 * about menu item
	 */
	private JMenuItem about = new JMenuItem("About");

	/**
	 * color class variable to change colors
	 */
	private Color color;

	/**
	 * about picture
	 */
	private JDialog aboutDialog;

	/**
	 * win picture
	 */
	private JDialog winDialog;

	/**
	 * complete picture
	 */
	private JDialog completeDialog;

	/**
	 * top panel of game
	 */
	private JPanel topPanel = new JPanel();
	
	/**
	 * right panel of game
	 */
	private JPanel rightPanel = new JPanel();
	
	/**
	 * center panel of game
	 */
	private JPanel centerPanel = new JPanel();
	
	/**
	 * bottom panel of game
	 */
	private JPanel bottomPanel = new JPanel();
	
	/**
	 * left panel of game
	 */
	private JPanel leftPanel = new JPanel();
	
	/**
	 * counts points
	 */
	private int pointCount = 0;

	
	/**
	 * Class that holds components of SudokuServer UI 
	 * @author Maya/Shaunessee
	 *
	 */
	class ServerUI {
		ServerUI() {
		// create the frame
		JFrame serverFrame = new JFrame();
		serverFrame.setTitle("Server Title: Maya Jaffary - Shaunessee Green"); //sets title of frame
		serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application when x'd
		serverFrame.setResizable(false); //prevent frame from being resized
		serverFrame.setSize(760,510); //sets the x and y dimensions
		serverFrame.setLayout(new BorderLayout(10,10)); //set up the frame with border layout
		serverFrame.getContentPane().setBackground(myBackGColor);
				
		// create the main panels
		JPanel imagePanel = new JPanel(); 
		imagePanel.setBackground(myBackGColor);
		imagePanel.setPreferredSize(new Dimension(580,100));
		serverFrame.add(imagePanel,BorderLayout.CENTER);
		imagePanel.setLayout(new FlowLayout());
		JPanel thePanel = new JPanel(); 
		thePanel.setBackground(myBackGColor);
		thePanel.setPreferredSize(new Dimension(580,100));
		serverFrame.add(thePanel,BorderLayout.SOUTH);
		thePanel.setLayout(new FlowLayout());
			
		// image panel contents
		ImageIcon sudokuServer = new ImageIcon("sudoku_server.png");
		JLabel labelForImage = null;
	    if (sudokuServer != null) {
	    	labelForImage = new JLabel("", sudokuServer, JLabel.CENTER);
	    } 
		// main panel contents
	    JLabel labPort = new JLabel("Port:");
	    JTextField txtPort = new JTextField("9090"); 
	    JButton start = new JButton("Start"); 
	    start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
		        GameView.ClientUI cUIobject = new ClientUI();
	    	}
		});
	    JButton results = new JButton("Results");
	    JCheckBox chkFinalize = new JCheckBox(); 
	    JLabel labFinilize = new JLabel("Finalize");
	    JButton end = new JButton("End"); 
	    JTextArea serverTxtArea = new JTextArea();
			
	    imagePanel.add(labelForImage);
	    thePanel.add(labPort);
	    thePanel.add(txtPort);
	    thePanel.add(start);
	    thePanel.add(results);
	    thePanel.add(chkFinalize);
	    thePanel.add(labFinilize);
	    thePanel.add(end);
	    thePanel.add(serverTxtArea);
	    
		serverFrame.setVisible(true); //make frame visible after all components have been added
		}
	}
	
	
	
	/**
	 * Class that holds components of ClientServer UI 
	 * @author Maya/Shaunessee
	 *
	 */
	class ClientUI {
		ClientUI() {
		// create the frame
		JFrame serverFrame = new JFrame();
		serverFrame.setTitle("Server Title: Maya Jaffary - Shaunessee Green"); //sets title of frame
		serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application when x'd
		serverFrame.setResizable(false); //prevent frame from being resized
		serverFrame.setSize(760,510); //sets the x and y dimensions
		serverFrame.setLayout(new BorderLayout(10,10)); //set up the frame with border layout
		serverFrame.getContentPane().setBackground(myBackGColor);
				
		// create the main panels
		JPanel imagePanel = new JPanel(); 
		imagePanel.setBackground(myBackGColor);
		imagePanel.setPreferredSize(new Dimension(580,100));
		serverFrame.add(imagePanel,BorderLayout.CENTER);
		imagePanel.setLayout(new FlowLayout());
		JPanel thePanel = new JPanel(); 
		thePanel.setBackground(myBackGColor);
		thePanel.setPreferredSize(new Dimension(580,100));
		serverFrame.add(thePanel,BorderLayout.SOUTH);
		thePanel.setLayout(new FlowLayout());
			
		// image panel contents
		ImageIcon sudokuServer = new ImageIcon("sudoku_client.png");
		JLabel labelForImage = null;
	    if (sudokuServer != null) {
	    	labelForImage = new JLabel("", sudokuServer, JLabel.CENTER);
	    } 
		// main panel contents
	    JLabel labUser = new JLabel("User:");
	    JTextField txtUser = new JTextField(); 
	    JLabel labServer = new JLabel("Server:");
	    JTextField txtServer = new JTextField(); 
	    JLabel labPort = new JLabel("Port:");
	    JTextField txtPort = new JTextField(); 
	    JButton connect = new JButton("Connect"); 
	    JButton end = new JButton("End"); 
	    //TODO more buttons for sending and receiving data
	    JTextArea clientTxtArea = new JTextArea();
			
	    imagePanel.add(labelForImage);
	    thePanel.add(labUser);
	    thePanel.add(txtUser);
	    thePanel.add(labServer);
	    thePanel.add(txtServer);
	    thePanel.add(labPort);
	    thePanel.add(txtPort);
	    thePanel.add(connect);
	    thePanel.add(end);
	    thePanel.add(clientTxtArea);
	    
		serverFrame.setVisible(true); //make frame visible after all components have been added
		}
	}
	
	/**
	 * Class that displays a splash screen before the game UI opens
	 * @author Maya/Shaunessee
	 *
	 */
	class ServerSplash {

		void SplashScreen() {
			JWindow sudokuSplash = new JWindow();
			ImageIcon sudokuWelcome = new ImageIcon("sudoku_server.png");

			if (sudokuWelcome != null) {
				JLabel sudokuLabel = new JLabel("", sudokuWelcome, SwingConstants.CENTER);
				sudokuSplash.getContentPane().add(sudokuLabel);
			}

			sudokuSplash.setSize(760, 510);
			sudokuSplash.setVisible(true);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sudokuSplash.setVisible(false);
			sudokuSplash.dispose();
		}

	}
	
	/**
	 * Class that displays a splash screen before the game UI opens
	 * @author Maya/Shaunessee
	 *
	 */
	class Splash {

		void SplashScreen() {
			JWindow sudokuSplash = new JWindow();
			ImageIcon sudokuWelcome = new ImageIcon("sudoku.png");

			if (sudokuWelcome != null) {
				JLabel sudokuLabel = new JLabel("", sudokuWelcome, SwingConstants.CENTER);
				sudokuSplash.getContentPane().add(sudokuLabel);
			}

			sudokuSplash.setSize(760, 510);
			sudokuSplash.setVisible(true);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sudokuSplash.setVisible(false);
			sudokuSplash.dispose();
		}

	}
	


	GameView() {

		// ***create the frame
		this.setTitle("Title: Maya Jaffary - Shaunessee Green"); // sets title of frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application when x'd
		this.setResizable(false); // prevent frame from being resized
		this.setSize(760, 510); // sets the x and y dimensions
		this.setLayout(new BorderLayout(10, 10)); // set up the frame with border layout
		this.getContentPane().setBackground(myBackGColor);

		// ***create the main panels
		topPanel = new JPanel(); // includes mode and dimension
		topPanel.setBackground(myBackGColor);
		topPanel.setPreferredSize(new Dimension(100, 50));

		rightPanel = new JPanel(); // includes main tools
		rightPanel.setBackground(myBackGColor);
		rightPanel.setPreferredSize(new Dimension(170, 100));

		centerPanel = new JPanel(); // includes sudoku table and sudoku numbers
		centerPanel.setBackground(myBackGColor);
		centerPanel.setPreferredSize(new Dimension(580, 100));

		bottomPanel = new JPanel(); // used as margin
		bottomPanel.setBackground(myBackGColor);
		bottomPanel.setPreferredSize(new Dimension(750, 10));

		leftPanel = new JPanel(); // used as margin
		leftPanel.setPreferredSize(new Dimension(10, 510));

		// *** adding main panels to main frame
		this.add(topPanel, BorderLayout.NORTH);
		this.add(rightPanel, BorderLayout.EAST);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
		this.add(leftPanel, BorderLayout.WEST);

		// MENU************************************

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu gameMenu = new JMenu("Game");
		JMenu helpMenu = new JMenu("Help");

		menuBar.add(gameMenu);
		menuBar.add(helpMenu);

		try {
			ImageIcon newGamePic = new ImageIcon("piciconnew.gif");
			newGame.setIcon(newGamePic);
		} catch (Exception e){
			System.out.println(e);
		}

		try {
			ImageIcon solutionPic = new ImageIcon("piciconsol.gif");
			solution.setIcon(solutionPic);
		} catch (Exception e){
			System.out.println(e);
		}

		try {
			ImageIcon exitPic = new ImageIcon("piciconext.gif");
			exit.setIcon(exitPic);
		} catch (Exception e){
			System.out.println(e);
		}
		

		gameMenu.add(newGame);
		gameMenu.addSeparator();
		gameMenu.add(solution);
		gameMenu.addSeparator();
		gameMenu.add(exit);

		try {
			ImageIcon colorPic = new ImageIcon("piciconcol.gif");
			colors.setIcon(colorPic);
		} catch (Exception e){
			System.out.println(e);
		}
		
		try {
			ImageIcon aboutPic = new ImageIcon("piciconabt.gif");
			about.setIcon(aboutPic);
		} catch (Exception e){
			System.out.println(e);
		}
		

		aboutDialog = new JDialog(this, "About Game");
		try {
			aboutDialog.add(new JLabel(new ImageIcon("sudoku_about.png")));
		} catch (Exception e){
			System.out.println(e);
		}
		
		aboutDialog.setSize(900, 400);
		// aboutDialog.setIcon(aboutDialogPic);

		helpMenu.add(colors);
		helpMenu.addSeparator();
		helpMenu.add(about);

		// End of Menu Items*****************************************

		// ***rightPanel Content ********************

		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.setBorder(new LineBorder(Color.gray, 1, false));
		// Difficulty box *** TODO: needs event listener
		JLabel difficultLabel = new JLabel("Difficulty:");
		difficultLabel.setForeground(Color.black);
		String difOptions[] = { "easy", "medium", "hard" };
		difBox = new JComboBox<Object>(difOptions);
		difBox.setMaximumSize(new Dimension(250, 25));

		// TODO Add timer and submit button once grid is setup
		timerLabel = new JLabel();
		timerLabel.setForeground(Color.black);
		timerLabel.setBounds(50, 100, 90, 20);

		// creating textArea
		historyTextArea = new JTextArea();
		history = new JScrollPane(historyTextArea);
		history.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		history.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		rightPanel.getRootPane().add(history);

		// set up for new Game button
		openFile = new JFrame("Open A Saved Configuration");
		openFile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		openFile.setLayout(new BorderLayout());

		// Adding components to rightPanel

		rightPanel.add(difficultLabel);
		rightPanel.add(difBox);
		rightPanel.add(timerLabel);
		rightPanel.add(points);
		rightPanel.add(submitBut);
		rightPanel.add(randBut);
		rightPanel.add(resetBut);
		rightPanel.add(history);
		rightPanel.add(showBut);
		rightPanel.add(hideBut);
		rightPanel.add(clearBut);
		rightPanel.add(newGameBut);

		// ***centerPanel Content
		centerPanel.setLayout(new BorderLayout(10, 10));
		// ***create two internal panels
		sudokuTable = new JPanel();
		sudokuTable.setBackground(Color.black);
		sudokuTable.setPreferredSize(new Dimension(500, 400));
		sudokuTable.setBorder(new LineBorder(Color.black, 2, true));
		sudokuNums = new JPanel();
		sudokuNums.setBackground(myBackGColor);
		sudokuNums.setPreferredSize(new Dimension(80, 400));
		sudokuNums.setBorder(new LineBorder(myBackGColor, 1, false));
		// ***adding internal panels to centerPanel
		centerPanel.add(sudokuTable, BorderLayout.CENTER);
		centerPanel.add(sudokuNums, BorderLayout.EAST);

		// ***sudokuNums Content
		sudokuNums.setLayout(new BoxLayout(sudokuNums, BoxLayout.Y_AXIS));
		sudokuNums.setSize(80, 400);
		sudokuNums.setVisible(true);

		// ***topPanel Content
		JLabel modeLabel = new JLabel("Mode:");
		// ***radio buttons

		designButton.setSelected(true);
		designButton.setBackground(myBackGColor);
		playButton.setBackground(myBackGColor);

		// makes sure only 1 button is selected
		ButtonGroup gameMode = new ButtonGroup();
		gameMode.add(designButton);
		gameMode.add(playButton);

		topPanel.add(modeLabel);
		topPanel.add(designButton);
		topPanel.add(playButton);

		// TODO set it so default can call
		// for default set up
		// timerLabel.setVisible(false);
		points.setVisible(false);
		showBut.setVisible(false);
		hideBut.setVisible(false);
		clearBut.setVisible(false);
		newGameBut.setVisible(false);
		submitBut.setVisible(true);
		randBut.setVisible(true);
		resetBut.setVisible(true);
		history.setVisible(true);

		// creating JComboBox for dimensions
		JLabel dimensionLabel = new JLabel("Dimension:");
		String dimOptions[] = { "2", "3", "4" };
		dimBox = new JComboBox<Object>(dimOptions);
		dimBox.setBounds(50, 100, 90, 20);

		topPanel.add(dimensionLabel);
		topPanel.add(dimBox);

		winDialog = new JDialog(this, "WINNER");
		try {
			winDialog.add(new JLabel(new ImageIcon("sudoku_winner.png")));
		} catch (Exception e){
			System.out.println(e);
		}
		
		winDialog.setSize(900, 400);

		completeDialog = new JDialog(this, "Complete");
		try {
			completeDialog.add(new JLabel(new ImageIcon("sudoku_end.png")));
		} catch (Exception e){
			System.out.println(e);
		}
		
		completeDialog.setSize(900, 400);

	}

	/*
	 * ###########################################################
	 * METHODS/SETTERS/GETTERS
	 */

	/**
	 * creates number buttons
	 * 
	 * @param numberButtons - fields that were created in the model
	 */
	public void setNumberButton(Button[] numberButtons) {
		this.numbersButtons = numberButtons;

		for (int num = 1; num < gridDim + 1; num++) {
			numbersButtons[num].setSize(50, 50);
			numbersButtons[num].setBackground(Color.white);
			// ActionListener numberListener;
			numbersButtons[num].addActionListener(null);
			sudokuNums.add(numbersButtons[num]);
			validate();
			repaint();
		}
	}

	/**
	 * creates original grid layout
	 * 
	 * @param tableFields - fields that were created in model
	 */
	public void setTableGrid(JButton[][] tableFields) {
		this.tableFields = tableFields;

		// the section bellow allows calling action listener with no exception thrown
		// but throws exception when changing dimensions
		sudokuTable.setLayout(new GridLayout(gridDim, gridDim));
		for (int row = 1; row <= gridDim; row++) {
			for (int col = 1; col <= gridDim; col++) {
				tableFields[row][col].setSize(10, 10);
				tableFields[row][col].setBorder(new LineBorder(Color.black, 1, false));
				// setting the background color for cells
				tableFields[row][col].setBackground(Color.white);
				if (row <= dimChoice | (row > dimChoice * 2 & row <= dimChoice * 3)) {
					if (col <= dimChoice | (col > dimChoice * 2 & col <= dimChoice * 3)) {
						tableFields[row][col].setBackground(new Color(201, 201, 201));
					}
				} else if ((row > dimChoice & row <= dimChoice * 2) | (row > dimChoice * 3 & row <= dimChoice * 4)) {
					if ((col > dimChoice & col <= dimChoice * 2) | (col > dimChoice * 3 & col <= dimChoice * 4)) {
						tableFields[row][col].setBackground(new Color(201, 201, 201));
					}
				}

				tableFields[row][col].addActionListener(null);
				// adding cells to sudokuTable
				sudokuTable.add(tableFields[row][col]);
				validate();
				repaint();
			}
		}
	}

	/**
	 * validates the tile before adding to grid
	 * 
	 * @param i - row of value to be added
	 * @param j - column of value to be added
	 */
	public void validatingTiles(int i, int j) {

		// appending selected dimension to history text area
		historyTextArea.append("\n");
		historyTextArea.append("dim ");
		historyTextArea.append(Integer.toString(dimChoice));
		historyTextArea.append(",");

		if (error == true) {
			tableFields[i][j].setBackground(Color.red);
			--pointCount;
			if (pointCount < 0) {
				pointCount = 0;
			}
			points.setText("Points: " + String.valueOf(pointCount));
		}
		if (error == false) {
			++pointCount;
			points.setText("Points: " + String.valueOf(pointCount));
			tableFields[i][j].setText(buttonLabel);
			// adding the configuration into an array

			historyArray[i][j] = tableFields[i][j].getText();
			tableFields[i][j].setBackground(Color.green);
			// appending the configuration to textArea
			StringBuilder sb = new StringBuilder();
		}
		historyTextArea.append("\n");

		// TODO below shouldn't be in this nested for loop but this entire function is
		// being called from within a nested for loop in controller
		for (int w = 1; w <= gridDim; w++) {
			for (int z = 1; z <= gridDim; z++) {
				historyTextArea.append(historyArray[w][z]);
				historyTextArea.append(",");
			}
			historyTextArea.append("\n");
		}
		// checking for completion of game
		boolean historyArrayIsFull = true;
		for (int row = 1; row <= gridDim; row++) {
			for (int col = 1; col <= gridDim; col++) {

				if (historyArray[row][col] == null) { // if one of the elements is null set variable to false
					historyArrayIsFull = false;
				}

			}
		}
		if (historyArrayIsFull == true) {
			checkForWinner();
		}
		error = false;
	}

	/**
	 * Adds loaded solution that is adjusted for difficulty to grid
	 * 
	 * @param i - row to be added
	 * @param j - column to be added
	 */
	public void gameDifficulty(int i, int j) {

		historyTextArea.append("\n");
		historyTextArea.append("dim ");
		historyTextArea.append(Integer.toString(dimChoice));
		historyTextArea.append(",");

		tableFields[i][j].setText(buttonLabel);
		// adding the configuration into an array

		historyArray[i][j] = tableFields[i][j].getText();

		// appending the configuration to textArea
		StringBuilder sb = new StringBuilder();

		historyTextArea.append("\n");
		// TODO below shouldn't be in this nested for loop but this entire function is
		// being called from within a nested for loop in controller
		for (int w = 1; w <= gridDim; w++) {
			for (int z = 1; z <= gridDim; z++) {
				historyTextArea.append(historyArray[w][z]);
				historyTextArea.append(",");
			}
			historyTextArea.append("\n");
		}

	}

	/**
	 * Shows entire solution array
	 * 
	 * @param solutionArr - array loaded by user
	 */
	public void showSolution(String[][] solutionArr) {

		this.solutionArr = solutionArr;
		temp = new String[gridDim + 1][gridDim + 1];
		for (int row = 1; row <= gridDim; row++) {
			for (int col = 1; col <= gridDim; col++) {
				if (tableFields[row][col].getText() == null) {
					temp[row][col] = "";
				} else {
					temp[row][col] = tableFields[row][col].getText();
				}

			}
		}

		for (int row = 1; row <= gridDim; row++) {
			for (int col = 1; col <= gridDim; col++) {
				tableFields[row][col].setText(solutionArr[row][col]);
			}
		}
	}

	/**
	 * Hides solution
	 */
	public void hideSolution() {

		for (int row = 1; row <= gridDim; row++) {
			for (int col = 1; col <= gridDim; col++) {

				if (temp[row][col] == null) {
					tableFields[row][col].setText("");
				} else {
					tableFields[row][col].setText(temp[row][col]);
				}
			}
		}
	}

	/**
	 * Once array is full it checks what dialog to display
	 */
	public void checkForWinner() {
		boolean check = true;

		if (solutionArr == null) {
			winDialog.setVisible(true); //dialog when random is generated
		} else {
			for (int row = 1; row <= gridDim; row++) {
				for (int col = 1; col <= gridDim; col++) {

					if (tableFields[row][col].getText() != solutionArr[row][col]) {
						check = false;
					}
				}
			}
			if (check == true) {
				winDialog.setVisible(true);

			} else if (check == false) {
				completeDialog.setVisible(true);
			}
		}

	}

	// action listener methods
	/**
	 * reset button listener
	 * 
	 * @param listenForReset - action listener for reset button
	 */
	void addResetListener(ActionListener listenForReset) {

		resetBut.addActionListener(listenForReset);
	}

	/**
	 * design radio button listener
	 * 
	 * @param listenForDesign - action listener for design radio button
	 */
	void addDesignListener(ActionListener listenForDesign) {

		designButton.addActionListener(listenForDesign);

	}

	/**
	 * play radio button listener
	 * 
	 * @param listenForPlay - action listener for play radio button
	 */
	void addPlayListener(ActionListener listenForPlay) {

		playButton.addActionListener(listenForPlay);

	}

	/**
	 * dimension combo box listener
	 * 
	 * @param listenForDimension - listen for user input
	 */
	void addDimensionListener(ActionListener listenForDimension) {

		dimBox.addActionListener(listenForDimension);

	}

	/**
	 * save button listener
	 * 
	 * @param listenForSave - listen for save button
	 */
	void addSubmitListener(ActionListener listenForSave) {
		submitBut.addActionListener(listenForSave);
	}

	/**
	 * new game button listener
	 * 
	 * @param listenForNewGame - listen for new game button
	 */
	void addNewGameListener(ActionListener listenForNewGame) {
		newGameBut.addActionListener(listenForNewGame);
	}

	/**
	 * show button listener
	 * 
	 * @param listenForShow - listen for show button
	 */
	void addShowListener(ActionListener listenForShow) {

		showBut.addActionListener(listenForShow);
	}

	/**
	 * hide button listener
	 * 
	 * @param listenForHide - listen for hide button
	 */
	void addHideListener(ActionListener listenForHide) {
		hideBut.addActionListener(listenForHide);
	}

	/**
	 * difficulty combo box listener
	 * 
	 * @param listenForDifficulty - listen for difficulty
	 */
	void addDifficultyListener(ActionListener listenForDifficulty) {

		difBox.addActionListener(listenForDifficulty);
	}

	/**
	 * clear button listener
	 * 
	 * @param listenForClear - listen for clear button
	 */
	void addClearListener(ActionListener listenForClear) {

		clearBut.addActionListener(listenForClear);
	}

	/**
	 * menu solution listener
	 * 
	 * @param listenForMenuSolution - listen for menu option selection
	 */
	void addMenuSolution(ActionListener listenForMenuSolution) {

		solution.addActionListener(listenForMenuSolution);
	}

	/**
	 * menu exit listener
	 * 
	 * @param listenForMenuExit - listen for exit selection
	 */
	void addMenuExit(ActionListener listenForMenuExit) {

		exit.addActionListener(listenForMenuExit);
	}

	/**
	 * menu new game listener
	 * 
	 * @param listenForMenuNewGame - listen for new game selection
	 */
	void addMenuNewGame(ActionListener listenForMenuNewGame) {

		newGame.addActionListener(listenForMenuNewGame);
	}

	/**
	 * menu color listener
	 * 
	 * @param listenForMenuColor - listen for menu color solution
	 */
	void addMenuColor(ActionListener listenForMenuColor) {

		colors.addActionListener(listenForMenuColor);
	}

	/**
	 * menu about listener
	 * 
	 * @param listenForMenuAbout - listen for menu about selection
	 */
	void addMenuAbout(ActionListener listenForMenuAbout) {

		about.addActionListener(listenForMenuAbout);
	}

	/**
	 * random button listener
	 * 
	 * @param listenForRandom - listen for random button
	 */
	void addRandButton(ActionListener listenForRandom) {

		randBut.addActionListener(listenForRandom);
	}

	// setters**************************************

	/**
	 * @param solutionArr the solutionArr to set
	 */
	public void setSolutionArr(String[][] solutionArr) {
		this.solutionArr = solutionArr;
	}

	/**
	 * @param myBackGColor the myBackGColor to set
	 */
	public void setMyBackGColor(Color myBackGColor) {
		this.myBackGColor = myBackGColor;
		this.getContentPane().setBackground(myBackGColor);
		this.topPanel.setBackground(myBackGColor);
		this.leftPanel.setBackground(myBackGColor);
		this.bottomPanel.setBackground(myBackGColor);
		this.rightPanel.setBackground(myBackGColor);
		this.centerPanel.setBackground(myBackGColor);
		this.designButton.setBackground(myBackGColor);
		this.playButton.setBackground(myBackGColor);
	}

	/**
	 * @param cellShowing the cellShowing to set
	 */
	public void setCellShowing(int cellShowing) {
		this.cellShowing = cellShowing;
	}

	/**
	 * @param timer the timer to set
	 */
	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public void setGridDimensionChoice(int choice) {
		dimChoice = choice;

	}

	/**
	 * @param dimChoice the dimChoice to set
	 */
	public void setDimChoice(int dimChoice) {
		this.gridDim = dimChoice * dimChoice; // update the value of gridDim
		this.dimChoice = dimChoice; // set the value of gridDim
	}

	/**
	 * @param gridDim the gridDim to set
	 */
	public void setGridDim(int gridDim) {
		this.gridDim = gridDim;
	}

	public void setGridDimension(int dim) {
		gridDim = dim;

	}

	public void setPickedNum(int pickedNum) {
		this.pickedNum = pickedNum;
	}

	/**
	 * @param buttonLabel the buttonLabel to set
	 */
	public void setButtonLabel(String buttonLabel) {
		this.buttonLabel = buttonLabel;
	}

	/**
	 * @param square the square to set
	 */
	public void setSquare(int square) {
		this.square = square;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	/**
	 * @param historyArray the historyArray to set
	 */
	public void setHistoryArray(String[][] historyArray) {
		this.historyArray = historyArray;
	}

	/**
	 * @param tableFields the tableFields to set
	 */
	public void setTableFields(JButton[][] tableFields) {
		this.tableFields = tableFields;
	}

	/**
	 * @param timerLabel the timerLabel to set
	 */
	public void setTimerLabel(JLabel timerLabel) {
		this.timerLabel = timerLabel;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	public void setDimBox(int dim) {
		dimBox.setSelectedItem(dim);
		dimBox.updateUI();
		System.out.println(dimBox.getSelectedItem() + "from the view");
	}

	/**
	 * @param aboutDialog set to visible
	 */
	public void setAboutDialog(Boolean vis) {
		aboutDialog.setVisible(vis);
	}

	/**
	 * @param pointCount the pointCount to set
	 */
	public void setPointCount(int pointCount) {
		this.pointCount = pointCount;
		points.setText("Points: " + String.valueOf(pointCount));
	}

	// getters*************************************
	/**
	 * @return the dimChoice
	 */
	public int getDimChoice() {
		return dimChoice;
	}

	/**
	 * @return the gridDim
	 */
	public int getGridDim() {
		return gridDim;
	}

	/**
	 * @return the pickedNum
	 */
	public int getPickedNum() {
		return pickedNum;
	}

	/**
	 * @return the buttonLabel
	 */
	public String getButtonLabel() {
		return buttonLabel;
	}

	/**
	 * @return the square
	 */
	public int getSquare() {
		return square;
	}

	/**
	 * @return the historyArray
	 */
	public String[][] getHistoryArray() {
		return historyArray;
	}

	/**
	 * @return the tableFields
	 */
	public JButton[][] getTableFields() {
		return tableFields;
	}

	/**
	 * @return the timerLabel
	 */
	public JLabel getTimerLabel() {
		return timerLabel;
	}

	/**
	 * @return the timer
	 */
	public Timer getTimer() {
		return timer;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @return the openFile
	 */
	public JFrame getOpenFile() {
		return openFile;
	}

	/**
	 * @return the cellShowing
	 */
	public int getCellShowing() {
		return cellShowing;
	}

	/**
	 * return numbers array
	 */
	public Button[] getNumberButton() {
		return numbersButtons;

	}

	/**
	 * @return the myBackGColor
	 */
	public Color getMyBackGColor() {
		return myBackGColor;
	}

	/**
	 * @return the solutionArr
	 */
	public String[][] getSolutionArr() {
		return solutionArr;
	}

	/**
	 * @return tableFields array
	 */
	public JButton[][] getTableGrid() {
		return tableFields;

	}

	/**
	 * @return the aboutDialog
	 */
	public JDialog getAboutDialog() {
		return aboutDialog;
	}

	/**
	 * @return the pointCount
	 */
	public int getPointCount() {
		return pointCount;
	}

}

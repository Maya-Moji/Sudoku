package Sudoku;
/**
 * File name: GameController.java
 * @author [Shaunessee Green & 040830222] / [Maya Jaffary & 041016868]
 * Course: CST8221 -JAP Lab Section 301
 * Assignment: 2.2
 * Professor: Paulo Sousa
 * Date: June 23 2022
 * Compiler: Eclipse IDE Version: 2022-03 (4.23.0) JDK: 11.0.11
 * Purpose: This file performs the calculations for the view class
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.Timer;

/**
 * Class name: GameController
 * Method List: start, removeAndReload
 * Constant list:
 * Purpose: connects the view and model class
 */
public class GameController {

	/**
	 * the game view 
	 */
	private GameView theView;
	
	/**
	 * the game model
	 */
	private GameModel theModel;

	public GameController(GameView theView, GameModel theModel) {
		this.theView = theView;
		this.theModel = theModel;

	}

	/**
	 * start the game
	 */
	public void start() {

		theView.setVisible(true);
		theView.addResetListener(new ResetListener());
		theView.addDesignListener(new DesignListener());
		theView.addPlayListener(new PlayListener());
		theView.addDimensionListener(new DimensionListener());
		theView.addSubmitListener(new SubmitListener());
		theView.addNewGameListener(new NewGameListener());
		theView.addShowListener(new ShowListener());
		theView.addHideListener(new HideListener());
		theView.addDifficultyListener(new DifficultyListener());
		theView.addClearListener(new ClearListener());
		theView.addMenuAbout(new MenuAboutListener());
		theView.addMenuColor(new MenuColorListener());
		theView.addMenuExit(new MenuExitListener());
		theView.addMenuNewGame(new MenuNewGameListener());
		theView.addMenuSolution(new MenuSolutionListener());
		theView.addRandButton(new RandListener());
		// creates number buttons
		theModel.createNumberButtons();
		theView.setNumberButton(theModel.getNumbersButtons());

		// creating listener for each button
		for (int k = 1; k < theModel.getGridDim() + 1; k++) {
			theView.getNumberButton()[k].addActionListener(new NumberListener());
		}

		// creates tiles
		theModel.createTableFields();

		theView.setTableGrid(theModel.getTableFields());
		// creating listener for each tile on initial load
		for (int row = 1; row <= theModel.getGridDim(); row++) {
			for (int column = 1; column <= theModel.getGridDim(); column++) {
				theView.getTableGrid()[row][column].addActionListener(new TileListener());
			}
		}

	}

	/**
	 * removes previous set up and reloads new setup
	 */
	public void removeAndReload() {
		theView.sudokuNums.removeAll();
		theView.sudokuTable.removeAll();

		theView.repaint();
		theView.revalidate();

		// setting number buttons
		theModel.createNumberButtons();
		theView.setNumberButton(theModel.getNumbersButtons());
		for (int k = 1; k < theModel.getGridDim() + 1; k++) {
			theView.getNumberButton()[k].addActionListener(new NumberListener());
		}

		theModel.createTableFields();
		theView.setTableGrid(theModel.getTableFields());
		// creating listener for each tile
		for (int i = 1; i < theModel.getGridDim() + 1; i++) {
			for (int j = 1; j < theModel.getGridDim() + 1; j++) {
				theView.getTableGrid()[i][j].addActionListener(new TileListener());
			}
		}
	}
	
	/**
	 * method for setting cell location in view 
	 * @param i - for looping through array
	 * @param j - for looping through array
	 * @return the cell location from view
	 */
	public int getSquare(int i, int j) {
		if (i <= theView.getDimChoice()) {
			if (j <= theView.getDimChoice()) {
				theView.setSquare(11);
			} else if (j <= theView.getDimChoice() * 2) {
				theView.setSquare(12);
			} else if (j <= theView.getDimChoice() * 3) {
				theView.setSquare(13);
			} else {
				theView.setSquare(14);
			}
		} else if (i > theView.getDimChoice() & i <= theView.getDimChoice() * 2) { // rows dimChoice to
																					// dimChoice*2
			if (j <= theView.getDimChoice()) {
				theView.setSquare(21);
			} else if (j <= theView.getDimChoice() * 2) {
				theView.setSquare(22);
			} else if (j <= theView.getDimChoice() * 3) {
				theView.setSquare(23);
			} else {
				theView.setSquare(24);
			}
		} else if (i > theView.getDimChoice() * 2 & i <= theView.getDimChoice() * 3) { // rows
																						// dimChoice*2
																						// to
																						// dimChoice*3
			if (j <= theView.getDimChoice()) {
				theView.setSquare(31);
			} else if (j <= theView.getDimChoice() * 2) {
				theView.setSquare(32);
			} else if (j <= theView.getDimChoice() * 3) {
				theView.setSquare(33);
			} else {
				theView.setSquare(34);
			}
		} else { // rows dimChoice*3 to dimChoice*4
			if (j <= theView.getDimChoice()) {
				theView.setSquare(41);
			} else if (j <= theView.getDimChoice() * 2) {
				theView.setSquare(42);
			} else if (j <= theView.getDimChoice() * 3) {
				theView.setSquare(43);
			} else {
				theView.setSquare(44);
			}
		}
		return theView.getSquare();
	}
	
	/**
	 * This method shuffles the cell values of the temporary array created
	 * @param randArray -temporary random array generated by action listener
	 * @return temp - shuffled cell value
	 */
	int shuffleCells(int randArray[][]) {
	    //System.out.println("shuffled array: ");
	    int counter2 = 0;
	    int temp = 0;
        int gridDim = theModel.getGridDim();
        int randRow;
        int randCol;
	    do {
	        for (int row = 0; row < gridDim; row++) {
	            for (int col = 0; col < gridDim; col++) {

	                //generating random rows and columns
	                randRow = theModel.getRandomCell();
	                randCol = theModel.getRandomCell();
				    
	                //swapping positions
	                temp = randArray[row][col];
	                randArray[row][col] = randArray[randRow  -1][randCol -1];
	                randArray[randRow - 1][randCol - 1] = temp;

				    //System.out.print(temp);

					
	                counter2++;
	                
	            }
			    //System.out.println();
	        }
	    } while(counter2 != (gridDim*gridDim));
	    
		return temp;
	}
	
	
	/**
	 * This method validates the random table generated and adds it to historyArray and tableFields
	 * @param randArray -temporary random array generated by action listener
	 */
	void validatingRand(int randArray[][]) {

    for (int row = 0; row < theModel.getGridDim(); row++) {
    	for (int col = 0; col < theModel.getGridDim(); col++) {

                int currentCellLocation = getSquare(row+1, col+1);

			    int currentCellValue = randArray[row][col];
    			theModel.setPickedNum(currentCellValue);
				if (currentCellValue <= 15) {
					theView.setButtonLabel(Integer.toHexString(currentCellValue).toUpperCase());
				} else {
					theView.setButtonLabel("G");
				}
				
    			theModel.setSquare(currentCellLocation);
    			theView.setHistoryArray(theModel.getHistoryArray());
    			theModel.validatingTiles(row+1, col+1); // this method sets the value of boolean error

    			if((theModel.getError()== false)) {
    				theView.setError(theModel.getError());
    				theView.validatingTiles(row+1, col+1);
    				theModel.setHistoryArray(theView.getHistoryArray());
    				System.out.print(theModel.getHistoryArray()[row+1][col+1]);
    			}

    	}
    	System.out.println();
    }
	}

	/**
	 * Class name: RandListener
	 * Method List: actionPerformed
	 * Constant list:
	 * Purpose: random button action listener
	 */
	class RandListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			removeAndReload();

			// APPROACH 2: only functional for dim 2
			//creating randArray with values from 1 to gridDim being repeated gridDim times.
		    int counter = 1;
		    int randArray[][] = new int[theModel.getGridDim()][theModel.getGridDim()];
		    for (int i = 0; i < theModel.getGridDim(); i++) {  
		        for (int j = 0; j < theModel.getGridDim(); j++) { 
		            randArray[i][j] = counter++;            
		        }
		        counter=1;
		    }  
		    
		    //shuffling cells
		    shuffleCells(randArray);

		   // validatingRand(randArray);
		    
                //validating
//    		do {
//		    	shuffleCells(randArray);
		    for (int row = 0; row < theModel.getGridDim(); row++) {
		    	for (int col = 0; col < theModel.getGridDim(); col++) {
		    		do {
		    		
				    	shuffleCells(randArray);
		                int currentCellLocation = getSquare(row+1, col+1);

					    int currentCellValue = randArray[row][col];
		    			theModel.setPickedNum(currentCellValue);
						if (currentCellValue <= 15) {
							theView.setButtonLabel(Integer.toHexString(currentCellValue).toUpperCase());
						} else {
							theView.setButtonLabel("G");
						}
						
		    			theModel.setSquare(currentCellLocation);
		    			theView.setHistoryArray(theModel.getHistoryArray());
		    			theModel.validatingTiles(row+1, col+1); // this method sets the value of boolean error

		    		} while (theModel.getError()== true);

		    		theView.setError(theModel.getError());
		    		theView.validatingTiles(row+1, col+1);
		    		theModel.setHistoryArray(theView.getHistoryArray());
		    		System.out.print(theModel.getHistoryArray()[row+1][col+1]);


		    	}
		    	System.out.println();
		    }
		    
//    		boolean isTableFull = true;
//		    for (int row = 0; row < theModel.getGridDim(); row++) {
//		    	for (int col = 0; col < theModel.getGridDim(); col++) {
//		    		if(theModel.getHistoryArray()[row+1][col+1] == null) {
//		    			isTableFull = false;
//		    		}
//		    	}
//		    }
//		    
//		    while(isTableFull == false) {
//		    	removeAndReload();
//			    shuffleCells(randArray);
//			    validatingRand(randArray);
//		    }
		    
		    
		//} while (theModel.getError()== true);
		    


		    

	// APPROACH 1: only functional for dim 2		
//			for (int row = 1; row <= theModel.getGridDim(); row++) {
//				for (int col = 1; col <= theModel.getGridDim(); col++) {
//					do {	
//						int num = theModel.RandomGenerator();
//			
//						if (num <= 15) {
//							theView.setButtonLabel(Integer.toHexString(num).toUpperCase());
//						} else {
//							theView.setButtonLabel("G");
//						}
//						getSquare(row,col); //sets square in view
//
//						// validation: sending to model to help with tile validating
//						theModel.setPickedNum(num);
//						theModel.setSquare(theView.getSquare());
//						theView.setHistoryArray(theModel.getHistoryArray());
//						theModel.validatingTiles(row, col); // this method sets the value of boolean error
//
//					} while (theModel.getError()== true);
//						
//					
//					
//					theView.setError(theModel.getError());
//					theView.validatingTiles(row, col);
//					theModel.setHistoryArray(theView.getHistoryArray());
//
//
//				}
//			}
			
			
		}
	}

	/**
	 * Class name: ClearListener
	 * Method List: actionPerformed
	 * Constant list:
	 * Purpose: clear button action listener
	 */
	class ClearListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			removeAndReload();

			for (int row = 1; row < theModel.getGridDim() + 1; row++) {
				for (int col = 1; col < theModel.getGridDim() + 1; col++) {

					if (theModel.isInitial() == true) {
						theView.setButtonLabel(theModel.getDifficultyArrayInitial()[row][col]);

					} else {
						theView.setButtonLabel(theModel.getDifficultyArray()[row][col]);

					}

					theModel.setSquare(theView.getSquare());
					theView.setHistoryArray(theModel.getHistoryArray());
					theModel.validatingTiles(row, col); // this method sets the value of boolean error

					theView.gameDifficulty(row, col);
//					//sudokuTable.add(tableFields[i][j]);
					theModel.setHistoryArray(theView.getHistoryArray());
				}
			}

		}

	}

	/**
	 * Class name: MenuAboutListener
	 * Method List: actionPerformed
	 * Constant list:
	 * Purpose: menu about selection action listener
	 */
	class MenuAboutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			theView.setAboutDialog(true);
		}

	}

	/**
	 * Class name: MenuColorListener
	 * Method List: actionPerformed
	 * Constant list:
	 * Purpose: menu color selection action listener
	 */
	class MenuColorListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Color color = JColorChooser.showDialog(null, "Select a color", theView.getMyBackGColor());
			theView.setMyBackGColor(color);
		}

	}

	/**
	 * Class name: MenuExitListener
	 * Method List: actionPerformed
	 * Constant list:
	 * Purpose: menu exit selection action listener
	 */
	class MenuExitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}

	}

	/**
	 * Class name: MenuNewGameListener
	 * Method List: actionPerformed
	 * Constant list:
	 * Purpose: menu new game selection action listener

	 */
	class MenuNewGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			theView.playButton.setSelected(true);
			NewGameListener ng = new NewGameListener();
			ng.actionPerformed(e);
		}

	}

	/**
	 * Class name: MenuSolution Listener
	 * Method List: actionPerformed
	 * Constant list:
	 * Purpose: menu solution selection action listener
	 */
	class MenuSolutionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			ShowListener s = new ShowListener();
			s.actionPerformed(e);
		}

	}

	/**
	 * Class name: DifficultyListener
	 * Method List: actionPerformed
	 * Constant list:
	 * Purpose: difficulty combo box action listener
	 */
	class DifficultyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			removeAndReload();
			theModel.setDifLevel(theView.difBox.getSelectedItem().toString());
			theModel.setInitial(false);
//			theModel.difficultyCalculation();
//			theView.setCellShowing(theModel.getCellShowing());
			theView.setCellShowing(theModel.difficultyCalculation());
			int cellCount = 1;

			while (cellCount <= theModel.getCellShowing()) {

				int row = theModel.getRandomCell();
				int col = theModel.getRandomCell();

				cellLocationDetermination cellLocDeter = new cellLocationDetermination();
				cellLocDeter.actionPerformed(e); //sets cell location in view
				theView.setButtonLabel(theModel.getSolutionArr()[row][col]);

				theModel.setSquare(theView.getSquare());
				theView.setHistoryArray(theModel.getHistoryArray());
				theModel.validatingTiles(row, col); // this method sets the value of boolean error

				theView.gameDifficulty(row, col);
				theModel.setHistoryArray(theView.getHistoryArray());
				cellCount = cellCount + 1;
				theModel.setDifficultyArray(row, col);
				System.out.println(theModel.getDifficultyArray()[row][col]);
				
			}

		}

	}

	/**
	 * Class name: HideListener
	 * Method List: actionPerformed
	 * Constant list:
	 * Purpose: hide button action listener
	 */
	class HideListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (theModel.getSolutionArr() != null) {
				theView.hideSolution();
			} else {
				System.out.println("No solution avaiable");
			}
		}

	}

	/**
	 * Class name: ShowListener
	 * Method List: actionPerformed
	 * Constant list:
	 * Purpose: show button action listener
	 */
	class ShowListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (theModel.getSolutionArr() != null) {
				theView.showSolution(theModel.getSolutionArr());
			} else {
				System.out.println("No solution avaiable");
			}

		}

	}

	/**
	 * Class name: NewGameListener
	 * Method List: actionPerformed
	 * Constant list:
	 * Purpose: new game button action listener
	 */
	class NewGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			File workingDirectory = new File(System.getProperty("user.dir"));
			if (!workingDirectory.exists() || !workingDirectory.isDirectory()) {
				System.out.println("The user.dir directory doesn't exist");
				return;
			}
			JFileChooser FChooser = new JFileChooser();
			FChooser.setCurrentDirectory(workingDirectory);
			// FChooser.addChoosableFileFilter(savedFileFormat); //filter not functional yet
			// - not a requirement
			String text = "not read";
			String[] tokens;
			BufferedReader reader = null;
			String fileName = "";
			if (FChooser.showOpenDialog(theView.getOpenFile()) == JFileChooser.APPROVE_OPTION) {
				fileName = FChooser.getSelectedFile().getName();
				System.out.println("You have picked " + fileName + " file.");
				try {
					reader = new BufferedReader(new FileReader(fileName));
				} catch (NullPointerException ne) {
					System.out.println("Null pointer when instantiating BufferedReader.");
				} catch (FileNotFoundException fnf) {
					System.out.println("File was not found.");
				}
				try {
					text = reader.readLine();
					System.out.println("You have read the file. Firstline is : " + text);
					tokens = text.split(",");
					int dim = Integer.parseInt(tokens[0]);
					theModel.setDimChoice(dim);
					theView.setDimChoice(dim);

					// removes and reloads new configuration
					removeAndReload();

					PlayListener playMode = new PlayListener();
					playMode.actionPerformed(e);

					String arr = tokens[1];

					// Send string to be turned into array
					theModel.solutionArray(arr);
					theView.setSolutionArr(theModel.getSolutionArr());
					// setting default difficulty to easy
					theModel.difficultyCalculation();
					theView.setCellShowing(theModel.getCellShowing());
					int cellCount = 0;
					while (cellCount <= theModel.getCellShowing()) {

						int row = theModel.getRandomCell();
						int col = theModel.getRandomCell();

						if (theModel.getHistoryArray()[row][col] == null) {
							cellLocationDetermination cellLocDeter = new cellLocationDetermination();
							cellLocDeter.actionPerformed(e);
							theView.setButtonLabel(theModel.getSolutionArr()[row][col]);

							theModel.setSquare(theView.getSquare());
							theView.setHistoryArray(theModel.getHistoryArray());
							theModel.validatingTiles(row, col); // this method sets the value of boolean error

							theView.gameDifficulty(row, col);
							theModel.setHistoryArray(theView.getHistoryArray());
							cellCount = cellCount + 1;
							theModel.setDifficultyArrayInitial(row, col);
						}
					}

				} catch (NullPointerException ne) {
					System.out.println("Reader was initialized to null.");
				} catch (IOException io) {
					System.out.println("There has been input/output exception while reading the file.");
				}
			}
		}
	}

	/**
	 * Class name: SubmitListener
	 * Method List: actionPerformed
	 * Constant list:
	 * Purpose: submit button action listener
	 */
	class SubmitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			/**
			 * Boolean variable that indicates if the configuration is complete or not
			 */
			boolean historyArrayIsFull = false;
			int count = theView.getCount();
			for (int i = 1; i <= theModel.getGridDim(); i++) {
				for (int j = 1; j <= theModel.getGridDim(); j++) {
					if (theModel.getHistoryArray() != null) { // if array is initialized
						historyArrayIsFull = true;
						if (theModel.getHistoryArray()[i][j] == null) { // if one of the elements is null set variable
																		// to false
							historyArrayIsFull = false;
						}
					}

				}
			}
			try {
				if (!historyArrayIsFull) {
					System.out.println("Please complete the grid before submission.");
				} else {
					if (historyArrayIsFull) {
						PrintWriter writer = new PrintWriter(new File("ArrayFile " + count));
						writer.write(theModel.getDimChoice() + ",");
						for (int i = 1; i <= theModel.getGridDim(); i++) {
							for (int j = 1; j <= theModel.getGridDim(); j++) {
								writer.write(theModel.getHistoryArray()[i][j] + "");
							}
						}
						writer.println();
						writer.flush();
						writer.close();
						System.out.println("ArrayFile " + count); // test
						ResetListener re = new ResetListener();
						re.actionPerformed(e);
						theView.setCount(count + 1);
						;

					}
				}

			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				System.out.println("No such file exists.");
			}

		}

	}

	/**
	 * Class name: NumberListener
	 * Method List: actionPerformed
	 * Constant list:
	 * Purpose: number button action listener
	 */
	class NumberListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			for (int i = 1; i < theModel.getGridDim() + 1; i++) {
				if (e.getSource() == theView.getNumberButton()[i]) {
					int pickedNum = i;
					theView.setPickedNum(pickedNum);

				}
			}

		}

	}

	/**
	 * Class name: TileListener
	 * Method List: actionPerformed
	 * Constant list:
	 * Purpose: grid tile button action listener
	 */
	class TileListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO to complete this
			for (int i = 1; i < theModel.getGridDim() + 1; i++) {
				for (int j = 1; j < theModel.getGridDim() + 1; j++) {
					if (e.getSource() == theView.getTableGrid()[i][j]) {
						// converting button labels from integers to hex+G
						int pickedNum = theView.getPickedNum();

						if (pickedNum <= 15) {
							theView.setButtonLabel(Integer.toHexString(pickedNum).toUpperCase());
						} else {
							theView.setButtonLabel("G");
						}
						cellLocationDetermination cellLocDeter = new cellLocationDetermination();
						cellLocDeter.actionPerformed(e);

						// validating
						// sending to model to help with tile validating
						theModel.setPickedNum(pickedNum);
						theModel.setSquare(theView.getSquare());
						theView.setHistoryArray(theModel.getHistoryArray());
						theModel.validatingTiles(i, j); // this method sets the value of boolean error

						theView.setError(theModel.getError());

						theView.validatingTiles(i, j);
						// sudokuTable.add(tableFields[i][j]);

						theModel.setHistoryArray(theView.getHistoryArray());

					} // ends action listener
				} // ends inner for
			} // ends outer for

		}

	}

	/**
	 * Class name: cellLocationDetermination
	 * Method List: actionPerformed
	 * Constant list:
	 * Purpose: sets the square for validation
	 */
	class cellLocationDetermination implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			for (int i = 1; i <= theModel.getGridDim(); i++) {
				for (int j = 1; j <= theModel.getGridDim(); j++) {
//					if (ae.getSource()== null) {
//						ae.setSource(theView.getButtonLabel());
//					}
					
					if (ae.getSource() == theView.getTableGrid()[i][j]) {
						getSquare(i, j);
//						if (i <= theView.getDimChoice()) {
//							if (j <= theView.getDimChoice()) {
//								theView.setSquare(11);
//							} else if (j <= theView.getDimChoice() * 2) {
//								theView.setSquare(12);
//							} else if (j <= theView.getDimChoice() * 3) {
//								theView.setSquare(13);
//							} else {
//								theView.setSquare(14);
//							}
//						} else if (i > theView.getDimChoice() & i <= theView.getDimChoice() * 2) { // rows dimChoice to
//																									// dimChoice*2
//							if (j <= theView.getDimChoice()) {
//								theView.setSquare(21);
//							} else if (j <= theView.getDimChoice() * 2) {
//								theView.setSquare(22);
//							} else if (j <= theView.getDimChoice() * 3) {
//								theView.setSquare(23);
//							} else {
//								theView.setSquare(24);
//							}
//						} else if (i > theView.getDimChoice() * 2 & i <= theView.getDimChoice() * 3) { // rows
//																										// dimChoice*2
//																										// to
//																										// dimChoice*3
//							if (j <= theView.getDimChoice()) {
//								theView.setSquare(31);
//							} else if (j <= theView.getDimChoice() * 2) {
//								theView.setSquare(32);
//							} else if (j <= theView.getDimChoice() * 3) {
//								theView.setSquare(33);
//							} else {
//								theView.setSquare(34);
//							}
//						} else { // rows dimChoice*3 to dimChoice*4
//							if (j <= theView.getDimChoice()) {
//								theView.setSquare(41);
//							} else if (j <= theView.getDimChoice() * 2) {
//								theView.setSquare(42);
//							} else if (j <= theView.getDimChoice() * 3) {
//								theView.setSquare(43);
//							} else {
//								theView.setSquare(44);
//							}
//						}
					}
				}
			}
		}
	}
	
	

	/**
	 * Class name: ResetListener
	 * Method List: actionPerformed
	 * Constant list:
	 * Purpose: reset button action listener
	 */
	class ResetListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			removeAndReload();
		}

	}

	/**
	 * Class name: DesignListener
	 * Method List: actionPerformed
	 * Constant list:
	 * Purpose: design button action listener
	 */
	class DesignListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			theView.timerLabel.setVisible(false);
			theView.points.setVisible(false);
			theView.showBut.setVisible(false);
			theView.hideBut.setVisible(false);
			theView.clearBut.setVisible(false);
			theView.newGameBut.setVisible(false);
			theView.submitBut.setVisible(true);
			theView.randBut.setVisible(true);
			theView.resetBut.setVisible(true);
			theView.history.setVisible(true);

		}

	}

	/**
	 * Class name: PlayListener
	 * Method List: actionPerformed
	 * Constant list:
	 * Purpose: play button action listener
	 */
	class PlayListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			theView.setPointCount(0);
			theView.submitBut.setVisible(false);
			theView.randBut.setVisible(false);
			theView.resetBut.setVisible(false);
			theView.history.setVisible(false);
			theView.timerLabel.setVisible(true);
			theView.points.setVisible(true);
			theView.showBut.setVisible(true);
			theView.hideBut.setVisible(true);
			theView.clearBut.setVisible(true);
			theView.newGameBut.setVisible(true);

			if (theView.getTimer() == null) {
				Timer time = theView.getTimer();
				time = new Timer(1000, new TimerListener());

				time.setInitialDelay(1);
				time.start();
				theView.setTimer(time);
			} else {
				theView.getTimer().stop();
				Timer time = theView.getTimer();
				time = new Timer(1000, new TimerListener());

				time.setInitialDelay(1);
				time.start();
				theView.setTimer(time);
			}
		}

	}

	/**
	 * Class name: TimerListener
	 * Method List: actionPerformed
	 * Constant list:
	 * Purpose: timer button action listener
	 */
	class TimerListener implements ActionListener {

		int second = 0;
		int minute = 0;

		@Override
		public void actionPerformed(ActionEvent e) {

			second++;

			theView.timerLabel.setText("Timer: " + minute + ":" + second);

			if (second == 60) {
				second = 0;
				minute++;
				theView.timerLabel.setText("Timer: " + minute + ":" + second);
			}

		}

	}

	/**
	 * Class name: DimensionListener
	 * Method List: actionPerformed
	 * Constant list:
	 * Purpose: dimension action listener
	 */
	class DimensionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// setting the value of dimChoice to selected item
			theView.setDimChoice(Integer.parseInt((String) theView.dimBox.getSelectedItem()));// sets gridDim as well
			theModel.setDimChoice(Integer.parseInt((String) theView.dimBox.getSelectedItem()));// sets gridDim as well

			removeAndReload();
			

		}

	}

}

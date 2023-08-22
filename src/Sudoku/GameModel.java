package Sudoku;

/**
 * File name: GameModel.java
 * @author [Shaunessee Green & 040830222] / [Maya Jaffary & 041016868]
 * Course: CST8221 -JAP Lab Section 301
 * Assignment: 2.2
 * Professor: Paulo Sousa
 * Date: July 23 2022
 * Compiler: Eclipse IDE Version: 2022-03 (4.23.0) JDK: 11.0.11
 * Purpose: This file performs the calculations for the view class
 */
import java.awt.Button;
import java.awt.Color;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
/**
 * Class name: GameModel
 * Method List: validatingTiles, getRandomCell, difficultyCalculation, solutionArray, createTableFields,
 * createNumberButton, gridDimension
 * Constant list:serialVersionUID
 * Purpose: Performs calculations for the view class
 */
public class GameModel {

//**********************************************	
//*****************VARIABLES********************
//**********************************************
	/**
	 * Serializable class declares static final serial version UID field of type
	 * long
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * initializing second and minute for timer
	 */
	private int second, minute = 0;

	/**
	 * number buttons' display text
	 */
	private String buttonLabel = "";
	/**
	 * cells' display text
	 */
	private String cellLabel = "";

	/**
	 * Integer indicating which internal grid the user have clicked on.
	 */
	private int square = 0;

	/**
	 * counter for naming configuration file
	 */
	private int count = 0;

	/**
	 * String builder for printing arrays
	 */
	private StringBuilder sb;

	/**
	 * array for number buttons
	 */
	private Button[] numbersInitial; // TODO delete if not using anymore

	/**
	 * array for number buttons
	 */
	private Button[] numbersButtons;

	/**
	 * array for history
	 */
	private String historyArray[][];

	/**
	 * initial history array
	 */

	/**
	 * 2D array for grid cells
	 */
	private JButton[][] tableFields;

	/**
	 * initializing dimension - to be set from difBox
	 */
	private int dimChoice = 2;

	/**
	 * initializing grid dimension variable
	 */
	private int gridDim = dimChoice * dimChoice;

	/**
	 * the String to be paste onto cells upon clicking - default selection is 1
	 */
	private int pickedNum = 1;

	/**
	 * Error found when validating grids
	 */
	private boolean error;

	/**
	 * Array that holds loaded solution
	 */
	private String[][] solutionArr;

	/**
	 * difficulty level
	 */
	private String difLevel;

	/**
	 * number of cells that should be showing
	 */
	private int cellShowing;

	/**
	 * array that holds the initial solution array with some values hidden for
	 * difficulty
	 */
	private String[][] difficultyArrayInitial;

	/**
	 * array that holds the solution array with some values hidden for difficulty
	 */
	private String[][] difficultyArray;

	/**
	 * keep track if initial
	 */
	private boolean initial = true;
	// **********************************************
//*************GETTERS & SETTERS****************
//**********************************************

	/**
	 * @return the difficultyArray
	 */
	public String[][] getDifficultyArray() {
		return difficultyArray;
	}

	/**
	 * @param difficultyArray the difficultyArray to set
	 */
	public void setDifficultyArray(int row, int col) {
		this.difficultyArray[row][col] = solutionArr[row][col];
	}

	/**
	 * @return the difficultyArray
	 */
	public String[][] getDifficultyArrayInitial() {
		return difficultyArrayInitial;
	}

	/**
	 * @param difficultyArray the difficultyArray to set
	 */
	public void setDifficultyArrayInitial(int row, int col) {
		this.difficultyArrayInitial[row][col] = solutionArr[row][col];
	}

	/**
	 * @return the difLevel
	 */
	public String getDifLevel() {
		return difLevel;
	}

	/**
	 * @param difLevel the difLevel to set
	 */
	public void setDifLevel(String difLevel) {
		this.difLevel = difLevel;
		difficultyCalculation();
	}

	/**
	 * @return the solutionArr
	 */
	public String[][] getSolutionArr() {
		return solutionArr;
	}

	/**
	 * @param solutionArr the solutionArr to set
	 */
	public void setSolutionArr(String[][] solutionArr) {
		this.solutionArr = solutionArr;
	}

	// error
	/**
	 * @return the error
	 */
	public boolean getError() {
		return error;
	}

	/*********************
	 * NOT SURE IF NEEDED
	 * 
	 * @param error the error to set
	 */
	public void setError(boolean error) {
		this.error = error;
	}

	// second
	/**
	 * @return the second
	 */
	public int getSecond() {
		return second;
	}

	/**
	 * @param second the second to set
	 */
	public void setSecond(int second) {
		this.second = second;
	}

	// minute
	/**
	 * @return the minute
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * @param minute the minute to set
	 */
	public void setMinute(int minute) {
		this.minute = minute;
	}

	/**
	 * @return the buttonLabel
	 */
	public String getButtonLabel() {
		return buttonLabel;
	}

	/**
	 * @param buttonLabel the buttonLabel to set
	 */
	public void setButtonLabel(String buttonLabel) {
		this.buttonLabel = buttonLabel;
	}

	/**
	 * @return the cellLabel
	 */
	public String getCellLabel() {
		return cellLabel;
	}

	/**
	 * @param cellLabel the cellLabel to set
	 */
	public void setCellLabel(String cellLabel) {
		this.cellLabel = cellLabel;
	}

	// square
	/**
	 * @return the square
	 */
	public int getSquare() {
		return square = 0;
	}

	/**
	 * @param square the square to set
	 */
	public void setSquare(int square) {
		this.square = square;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the sb
	 */
	public StringBuilder getSb() {
		return sb;
	}

	/**
	 * @param sb the sb to set
	 */
	public void setSb(StringBuilder sb) {
		this.sb = sb;
	}

	/**
	 * @return the dimChoice
	 */
	public int getDimChoice() {
		return dimChoice;
	}

	/**
	 * @param dimChoice the dimChoice to set
	 */
	public void setDimChoice(int dimChoice) {
		gridDimension(dimChoice); // updating value of gridDim when dimChoice is set
		this.dimChoice = dimChoice;
	}

	/**
	 * @param gridDim the gridDim to set
	 */
	public void setGridDim(int gridDim) { // this won't be used as we never sent gridDim directly
		this.gridDim = gridDim;
	}

	/**
	 * @return the gridDim
	 */
	public int getGridDim() {
		return gridDim;
	}

	/**
	 * @return the numbersInitial
	 */
	public Button[] getNumbersInitial() {
		return numbersInitial;
	}

	/**
	 * @param numbersInitial the numbersInitial to set
	 */
	public void setNumbersInitial(Button[] numbersInitial) {
		this.numbersInitial = numbersInitial;
	}


	/**
	 * @return the historyArray
	 */
	public String[][] getHistoryArray() {
		return historyArray;
	}

	/**
	 * @param historyArray the historyArray to set
	 */
	public void setHistoryArray(String[][] historyArray) {
		this.historyArray = historyArray;
	}

	/**
	 * @return the numbersButtons
	 */
	public Button[] getNumbersButtons() {
		return numbersButtons;
	}

	/**
	 * @param numbersButtons the numbersButtons to set
	 */
	public void setNumbersButtons(Button[] numbersButtons) {
		this.numbersButtons = numbersButtons;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the tableFields
	 */
	public JButton[][] getTableFields() {
		return tableFields;
	}

	/**
	 * @param tableFields the tableFields to set
	 */
	public void setTableFields(JButton[][] tableFields) {
		this.tableFields = tableFields;
	}

	/**
	 * reseting array to null
	 */
	public void setTableFieldsReset(JButton[][] tableFields, int i, int j) {
		this.tableFields[i][j] = tableFields[i][j];
		tableFields[i][j].setText(null);
	}

	/**
	 * reseting array to null
	 */
	public void setHistoryArrayReset(String[][] historyArray, int i, int j) {
		this.historyArray[i][j] = historyArray[i][j];
		historyArray[i][j] = null;
	}

	/**
	 * @return the pickedNum
	 */
	public int getPickedNum() {
		return pickedNum;
	}

	/**
	 * @param pickedNum the pickedNum to set
	 */
	public void setPickedNum(int pickedNum) {
		this.pickedNum = pickedNum;
	}

	/**
	 * @return the cellShowing
	 */
	public int getCellShowing() {
		return cellShowing;
	}

	/**
	 * @param cellShowing the cellShowing to set
	 */
	public void setCellShowing(int cellShowing) {
		this.cellShowing = cellShowing;
	}
	
	/**
	 * @return the initial
	 */
	public boolean isInitial() {
		return initial;
	}

	/**
	 * @param initial the initial to set
	 */
	public void setInitial(boolean initial) {
		this.initial = initial;
	}

//**********************************************	
//******************METHODS*********************
//**********************************************
	/**
	 * method for calculating grid dimension
	 * 
	 * @param dimChoice
	 */
	public void gridDimension(int dimChoice) {

		gridDim = dimChoice * dimChoice;
	}

	
	/**
	 * creates number buttons
	 */
	public void createNumberButtons() {

		// setting default when program first loads
		if (gridDim == 0) {
			gridDim = 4;
		}

		// creating buttons
		numbersButtons = new Button[gridDim + 1];
		for (int num = 1; num < gridDim + 1; num++) {
			// converting numbers 1-16 to be displayed as 1-9 plus A-G
			if (num <= 15) {
				buttonLabel = Integer.toHexString(num).toUpperCase();
			} else {
				buttonLabel = "G";
			}
			numbersButtons[num] = new Button(" " + buttonLabel + " ");
		}
	}

	/**
	 * Creates the grid
	 */
	public void createTableFields() { // TODO not using anymore

		if (gridDim == 0) {
			gridDim = 4;
		}
		// initializing history array
		historyArray = new String[gridDim + 1][gridDim + 1];
		// re-initializing grid cells
		tableFields = new JButton[gridDim + 1][gridDim + 1];
		// initializing temp array for difficulty

		for (int row = 1; row <= gridDim; row++) {
			for (int col = 1; col <= gridDim; col++) {
				tableFields[row][col] = new JButton();

			}
		}
		setTableFields(tableFields);
	}

	/**
	 * integer copy of historyArray
	 */
	//int[][] numArray = new int[gridDim + 1][gridDim + 1];
	/**
	 * Checks if tiles are able to go into grid
	 * @param i - for looping through array
	 * @param j - for looping through array
	 */
	public boolean validatingTiles(int i, int j) {

		// copying historyArray of string to numArray of int
		int[][] numArray = new int[gridDim + 1][gridDim + 1];

		// create array to check against picked number
		for (int k = 1; k <= gridDim; k++) {
			for (int r = 1; r <= gridDim; r++) {

				if (historyArray[k][r] == null) {
					numArray[k][r] = 0;
				} else if (historyArray[k][r] == "G") {
					numArray[k][r] = 16;
				} else {
					numArray[k][r] = Integer.valueOf(historyArray[k][r], 16);
				}

			}
		}
		// check if number is valid

		error = false;
		// validating rows and columns
		for (int s = 1; s <= gridDim; s++) {
			if (numArray[i][s] == pickedNum || numArray[s][j] == pickedNum) {
				error = true;
			}
		}
		// validating internal grids

		for (int row = 1; row <= gridDim; row++) {
			for (int col = 1; col <= gridDim; col++) { // rows 1 to dimChoice
				if (row <= dimChoice) {
					if (col <= dimChoice) {
						if (numArray[row][col] == pickedNum & square == 11) {
							error = true;
						}
					} else if (col <= dimChoice * 2) {
						if (numArray[row][col] == pickedNum & square == 12) {
							error = true;
						}
					} else if (col <= dimChoice * 3) {
						if (numArray[row][col] == pickedNum & square == 13) {
							error = true;
						}
					} else {
						if (numArray[row][col] == pickedNum & square == 14) {
							error = true;
						}
					}
				} else if (row > dimChoice & row <= dimChoice * 2) { // rows dimChoice to dimChoice*2
					if (col <= dimChoice) {
						if (numArray[row][col] == pickedNum & square == 21) {
							error = true;
						}
					} else if (col <= dimChoice * 2) {
						if (numArray[row][col] == pickedNum & square == 22) {
							error = true;
						}
					} else if (col <= dimChoice * 3) {
						if (numArray[row][col] == pickedNum & square == 23) {
							error = true;
						}
					} else {
						if (numArray[row][col] == pickedNum & square == 24) {
							error = true;
						}
					}
				} else if (row > dimChoice * 2 & row <= dimChoice * 3) { // rows dimChoice*2 to dimChoice*3
					if (col <= dimChoice) {
						if (numArray[row][col] == pickedNum & square == 31) {
							error = true;
						}
					} else if (col <= dimChoice * 2) {
						if (numArray[row][col] == pickedNum & square == 32) {
							error = true;
						}
					} else if (col <= dimChoice * 3) {
						if (numArray[row][col] == pickedNum & square == 33) {
							error = true;
						}
					} else {
						if (numArray[row][col] == pickedNum & square == 34) {
							error = true;
						}
					}
				} else { // rows dimChoice*3 to dimChoice*4
					if (col <= dimChoice) {
						if (numArray[row][col] == pickedNum & square == 41) {
							error = true;
						}
					} else if (col <= dimChoice * 2) {
						if (numArray[row][col] == pickedNum & square == 42) {
							error = true;
						}
					} else if (col <= dimChoice * 3) {
						if (numArray[row][col] == pickedNum & square == 43) {
							error = true;
						}
					} else {
						if (numArray[row][col] == pickedNum & square == 44) {
							error = true;
						}
					}
				}
			}
		}
		return error;

	}
	
	





	/**
	 * Creates a string version of solution array
	 * @param token1 - loaded solution array
	 */
	public void solutionArray(String token1) {

		String[] splitText = token1.split("");

		solutionArr = new String[gridDim + 1][gridDim + 1];
		int index = 0;
		for (int row = 1; row <= gridDim; row++) {
			for (int col = 1; col <= gridDim; col++) {
				solutionArr[row][col] = splitText[index];
				index++;
			}
		}

	}

	/**
	 * Calculates how many cells should be showing based on difficulty selected
	 */
	public int difficultyCalculation() {

		if (initial == true) {
			difficultyArrayInitial = new String[gridDim + 1][gridDim + 1];
		} else {
			difficultyArray = new String[gridDim + 1][gridDim + 1];

		}

		System.out.println(difLevel);

		int totalCell = gridDim * gridDim;
		cellShowing = 0;
		// setting default
		if (difLevel == null) {
			difLevel = "easy";
		}

		if (difLevel == "easy") {

			cellShowing = (int) (totalCell * 0.75);

		} else if (difLevel == "medium") {

			cellShowing = (int) (totalCell * 0.5);
			// 50% showing; find value
		} else if (difLevel == "hard") {

			cellShowing = (int) (totalCell * 0.25);

			// 25% showing; find value
		}

		System.out.println(cellShowing);
		return cellShowing;
	}

	/**
	 * returns a random value for a row or column
	 * @return - random value
	 */
	public int getRandomCell() {

		int min = 1;
		int max = gridDim;

		int num = (int) (Math.random() * (max - min + 1) + min);

		return num;
	}
	
	/**
	 * Secure random variable for random game generator
	 */
	SecureRandom rand2;
	public int RandomGenerator() {
		//create a random number
		rand2 = new SecureRandom();
		//long seed= 100;
		//rand.setSeed(seed);
		int upperbound = gridDim; // 0 to 15 for dim 4
		int tile_rand = rand2.nextInt(upperbound)+1; // 1 to 16
		//translate numbers to hex
		//String tile_rand_hex;
			
		return tile_rand;
	}
}

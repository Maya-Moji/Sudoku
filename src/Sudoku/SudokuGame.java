package Sudoku;

public class SudokuGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        GameView theView = new GameView();
        GameView.Splash splashObject = theView.new Splash();
        GameModel theModel = new GameModel();
        GameController theController = new GameController(theView, theModel);


        splashObject.SplashScreen();
        theController.start();
	}

}


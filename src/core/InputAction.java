package core;

import utils.PositionCounter;

public class InputAction {
    private final Window window;
    private final GameScreen gameScreen;

    public InputAction(Window window, GameScreen gameScreen){
        this.window = window;
        this.gameScreen = gameScreen;
    }
    public void typeLetter(char ch){
        // if there are empty boxes in current row
        if (PositionCounter.getColumn() < LetterGrid.COLUMN){
            // get the letter box at current position
            LetterBox currentBox = gameScreen.getCurrentBox();
            // set current letter box text to current input char
            currentBox.setText(Character.toString(ch));
            // move the position pointer ahead
            PositionCounter.setColumn(PositionCounter.getColumn() + 1);
        }
    }


    public void pressEnter() {
        try {
            InputValidator.InputType inputStatus =
                    InputValidator.checkInput(gameScreen.getCurrentRow(),
                            WordPicker.getAnswer());

            if (inputStatus == InputValidator.InputType.CORRECT_WORD) {
                // win code
                window.changeScreen(Screen.ScreenType.WIN);
            }

            if (inputStatus == InputValidator.InputType.WRONG_WORD ||
                    PositionCounter.getRow() < LetterGrid.ROW) {
                // wrong word but still have chance
                PositionCounter.setRow(PositionCounter.getRow() + 1);
                PositionCounter.setColumn(0);
            }
            if (PositionCounter.getRow() == 5){ // lose
                // fetch the current answer and set it to label
                window.loseScreen.answerLabel.setText("Answer is: " + WordPicker.getAnswer());
                window.changeScreen(Screen.ScreenType.LOSE);
            }
        } catch (NotEnoughLettersException e) {
            System.out.println("Word is too short!");
        }
    }


    public void pressBackspace (){
        // move back position pointer by one column
        if (PositionCounter.getColumn() > 0){
            PositionCounter.setColumn(PositionCounter.getColumn() - 1);
        }

        // clear the box letter at current position
        gameScreen.getCurrentBox().setText(" ");
    }
}

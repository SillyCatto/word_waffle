package core;

import utils.PositionCounter;

public class InputAction {
    private final Window window;
    private final GameScreen gameScreen;
    public int score;

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

                // Scoring system

                int Guesses = PositionCounter.getRow();
                System.out.println(PositionCounter.getRow());
                switch (Guesses) {
                    case 0:
                        score = 100;
                        break;
                    case 1:
                        score = 80;
                        break;
                    case 2:
                        score = 60;
                        break;
                    case 3:
                        score = 40;
                        break;
                    case 4:
                        score = 20;
                        break;
                    default:
                        score = 0; // Failure
                        break;
                }
                System.out.println("Your score: " + score);
                window.changeScreen(Screen.ScreenType.WIN);
            } else if (inputStatus == InputValidator.InputType.WRONG_WORD &&
                    PositionCounter.getRow() < LetterGrid.ROW ) {
                if (PositionCounter.getRow() == 4) { // lose
                    // fetch the current answer and set it to label
                    window.loseScreen.answerLabel.setText("Answer is: " + WordPicker.getAnswer());
                    window.changeScreen(Screen.ScreenType.LOSE);
                } else {
                    // wrong word but still have chance
                    PositionCounter.setRow(PositionCounter.getRow() + 1);
                    PositionCounter.setColumn(0);
                }
            }
        } catch (NotEnoughLettersException e) {
            System.out.println("Word is too short!");
        }
    }

    public int getScore(){
        return score;
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

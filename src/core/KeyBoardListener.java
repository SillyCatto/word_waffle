package core;

import utils.PositionCounter;

public class KeyBoardListener{
    public static void typeLetter(char ch){
        // if there are empty boxes in current row
        if (PositionCounter.getColumn() < LetterGrid.COLUMN){
            // get the letter box at current position
            LetterBox currentBox = GameScreen.get().getCurrentBox();
            // set current letter box text to current input char
            currentBox.setText(Character.toString(ch));
            // move the position pointer ahead
            PositionCounter.setColumn(PositionCounter.getColumn() + 1);
        }
    }


    public static void pressEnter() {
        try {
            InputValidator.InputType inputStatus =
                    InputValidator.checkInput(GameScreen.get().getCurrentRow(),
                            WordPicker.getAnswer());

            if (inputStatus == InputValidator.InputType.CORRECT_WORD) {
                // win code
            } else if (inputStatus == InputValidator.InputType.WRONG_WORD ||
                    PositionCounter.getRow() < LetterGrid.ROW) {
                // wrong word but still have chance
                PositionCounter.setRow(PositionCounter.getRow() + 1);
                PositionCounter.setColumn(0);
            } else { // lose

            }
        } catch (NotEnoughLettersException e) {
            System.out.println("Word is too short!");
        }
    }


    public static void pressBackspace (){
        // move back position pointer by one column
        if (PositionCounter.getColumn() > 0){
            PositionCounter.setColumn(PositionCounter.getColumn() - 1);
        }

        // clear the box letter at current position
        GameScreen.get().getCurrentBox().setText(" ");
    }
}

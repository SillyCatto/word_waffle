package core;

import utils.PositionCounter;

public class InputValidator {
    public enum InputType{
        CORRECT_WORD,
        WRONG_WORD,
    }

    public static InputType checkInput(LetterBox[] gridRow, String currentAnswer)
            throws NotEnoughLettersException {

        InputType status;
        int correctLetterCount = 0;
        LetterBox letterBox;

        if (PositionCounter.getColumn() == LetterGrid.COLUMN){ // length of the word is 5
            // main loop to check the current input
            for (int i = 0; i < gridRow.length; i++){
                // get the box at i-th position of row
                letterBox = gridRow[i];
                // get the letter in the current box
                char currentLetter = letterBox.getText().charAt(0);
                // get the index of current input letter in the current answer
                int currentIndex = currentAnswer.indexOf(currentLetter);

                if (currentAnswer.indexOf(currentLetter) != -1){ // letter is found
                    if (currentIndex == i){ // index matched - correct
                        correctLetterCount ++;
                        letterBox.updateBoxColor(LetterBox.BoxType.GREEN);
                    }else{ // index didn't match
                        letterBox.updateBoxColor(LetterBox.BoxType.YELLOW);
                    }

                    // replace recently checked index with '*'
                    // so that it doesn't get checked again
                    char[] checkedAnswer = currentAnswer.toCharArray();
                    checkedAnswer[currentIndex] = '*';
                    currentAnswer = new String(checkedAnswer);

                } else { // current letter not found in the current answer
                    letterBox.updateBoxColor(LetterBox.BoxType.GRAY);
                }
            }
        } else { // word is short
            throw new NotEnoughLettersException("Word is too short!");
        }

        if(correctLetterCount == 5){ // all five letters matched - correct answer
            status = InputType.CORRECT_WORD;
        } else { // letters didn't match - wrong answer
            status = InputType.WRONG_WORD;
        }
        return status;
    }
}

package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.PositionCounter;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {
    @BeforeEach
    public void setUp() {
        // Reset PositionCounter before each test
        PositionCounter.reset();
    }

    @Test
    public void testCorrectWord() throws NotEnoughLettersException {
        // set Column position to simulate a full row input
        PositionCounter.setColumn(LetterGrid.COLUMN);

        LetterBox[] gridRow = {
                new LetterBox(), new LetterBox(), new LetterBox(),
                new LetterBox(), new LetterBox()
        };
        gridRow[0].setText("h");
        gridRow[1].setText("e");
        gridRow[2].setText("l");
        gridRow[3].setText("l");
        gridRow[4].setText("o");

        String answer = "hello";
        InputValidator.InputType result = InputValidator.checkInput(gridRow, answer);

        assertEquals(InputValidator.InputType.CORRECT_WORD, result);
        for (LetterBox box : gridRow) {
            assertEquals(LetterBox.BoxType.GREEN, box.getCurrentBoxType());
        }
    }

    @Test
    public void testWrongWordWithSomeCorrectLetters() throws NotEnoughLettersException {
        // set Column position to simulate a full row input
        PositionCounter.setColumn(LetterGrid.COLUMN);

        LetterBox[] gridRow = {
                new LetterBox(), new LetterBox(), new LetterBox(),
                new LetterBox(), new LetterBox()
        };
        gridRow[0].setText("h");
        gridRow[1].setText("e");
        gridRow[2].setText("l");
        gridRow[3].setText("l");
        gridRow[4].setText("a");

        String answer = "hello";
        InputValidator.InputType result = InputValidator.checkInput(gridRow, answer);

        assertEquals(InputValidator.InputType.WRONG_WORD, result);
        assertEquals(LetterBox.BoxType.GREEN, gridRow[0].getCurrentBoxType());
        assertEquals(LetterBox.BoxType.GREEN, gridRow[1].getCurrentBoxType());
        assertEquals(LetterBox.BoxType.GREEN, gridRow[2].getCurrentBoxType());
        assertEquals(LetterBox.BoxType.GREEN, gridRow[3].getCurrentBoxType());
        assertEquals(LetterBox.BoxType.GRAY, gridRow[4].getCurrentBoxType());
    }

    @Test
    public void testLettersInWrongPositions() throws NotEnoughLettersException {
        // set Column position to simulate a full row input
        PositionCounter.setColumn(LetterGrid.COLUMN);

        LetterBox[] gridRow = {
                new LetterBox(), new LetterBox(), new LetterBox(),
                new LetterBox(), new LetterBox()
        };
        gridRow[0].setText("o");
        gridRow[1].setText("l");
        gridRow[2].setText("l");
        gridRow[3].setText("e");
        gridRow[4].setText("h");

        String answer = "hello";
        InputValidator.InputType result = InputValidator.checkInput(gridRow, answer);

        assertEquals(InputValidator.InputType.WRONG_WORD, result);
        assertEquals(LetterBox.BoxType.YELLOW, gridRow[0].getCurrentBoxType());
        assertEquals(LetterBox.BoxType.YELLOW, gridRow[1].getCurrentBoxType());
        assertEquals(LetterBox.BoxType.YELLOW, gridRow[2].getCurrentBoxType());
        assertEquals(LetterBox.BoxType.YELLOW, gridRow[3].getCurrentBoxType());
        assertEquals(LetterBox.BoxType.YELLOW, gridRow[4].getCurrentBoxType());
    }

}
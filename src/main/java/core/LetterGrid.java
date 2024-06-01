package core;

import javax.swing.*;
import java.awt.*;

public class LetterGrid extends JPanel {
    private final LetterBox[][] grid = new LetterBox[5][5];
    protected static final int ROW = 5;
    protected static final int COLUMN = 5;

    public LetterGrid(){
        final int WIDTH = 320;
        final int HEIGHT = 320;

        this.setSize(WIDTH, HEIGHT);
        this.setLayout(new GridLayout(ROW, COLUMN, 5, 5));

        // create the 5*5 grid array
        for (int row = 0; row < this.grid.length; row++){
            for (int col = 0; col < this.grid[row].length; col++){
                this.grid[row][col] = new LetterBox();
                this.add(this.grid[row][col]);
            }
        }
    }


    public LetterBox getBox(int row, int col){
        return this.grid[row][col];
    }

    public LetterBox[] getRow(int row){
        return this.grid[row];
    }

    protected void reset(){
        for (LetterBox[] letterBoxes : this.grid) { //initialize the array
            for (LetterBox letterBox : letterBoxes) {
                letterBox.clear();
            }
        }
    }

}

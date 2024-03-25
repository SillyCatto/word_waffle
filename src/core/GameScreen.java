package core;

import utils.PositionCounter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameScreen extends Screen{
    private InputMap inputMap;
    private ActionMap actionMap;

    private LetterGrid grid;

    private InputAction inputAction;

    private WordPicker wordListFile;

    public GameScreen(Window window){
        super(window);
        initialize();
        update();
    }

    @Override
    public void initialize() {
        // offset panel for gap
        JPanel gridOffset = new JPanel();
        gridOffset.setPreferredSize(new Dimension(550, 100));
        gridOffset.setOpaque(false);

        // create grid panel
        grid = new LetterGrid();
        grid.setPreferredSize(new Dimension(320, 320));
        grid.setOpaque(false);

        this.add(gridOffset);
        this.add(grid);
        this.setBackground(Color.darkGray);

        // init input map and action map
        inputAction = new InputAction(window, this);
        inputMap = this.grid.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        actionMap = this.grid.getActionMap();

        // init word picker and pick a random word
        this.wordListFile = new WordPicker("./src/resources/wordlist.txt");
        this.wordListFile.generateWord();
    }

    public LetterGrid getGrid() {
        return grid;
    }

    public WordPicker getWordListFile() {
        return wordListFile;
    }


    public LetterBox getCurrentBox(){
        return this.grid.getBox(PositionCounter.getRow(),
                PositionCounter.getColumn());
    }

    public LetterBox[] getCurrentRow(){
        return this.grid.getRow(PositionCounter.getRow());
    }


    @Override
    public void update() {
        // handle letter key press
        for (char c = 'a'; c <= 'z'; c++) {
            final char key = c;
            this.inputMap.put(KeyStroke.getKeyStroke(key), Character.toString(key));
            this.actionMap.put(Character.toString(key), new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    inputAction.typeLetter(Character.toUpperCase(key));
                }
            });
        }

        // handle enter key
        this.inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        actionMap.put("Enter", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                inputAction.pressEnter();
            }
        });

        // handle backspace key
        this.inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "Backspace");
        actionMap.put("Backspace", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                inputAction.pressBackspace();
            }
        });
    }


    public void reset(LetterGrid grid){
        // clear the grid
        grid.reset();

        // reset position pointer
        PositionCounter.setRow(0);
        PositionCounter.setColumn(0);

    }
}

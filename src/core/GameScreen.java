package core;

import utils.PositionCounter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameScreen extends Screen{
    private static GameScreen instance = null;

    private InputMap inputMap;
    private ActionMap actionMap;
    private LetterGrid grid;
    private WordPicker wordList;

    private GameScreen(){

        JPanel gridOffset = new JPanel();
        gridOffset.setPreferredSize(new Dimension(550, 100));
        gridOffset.setOpaque(false);

        grid = new LetterGrid();
        grid.setPreferredSize(new Dimension(320, 320));
        grid.setOpaque(false);

        this.add(gridOffset);
        this.add(grid);
        this.setBackground(Color.darkGray);

        // init input map and action map
        inputMap = this.grid.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        actionMap = this.grid.getActionMap();

        // init word picker and pick a random word
        this.wordList = new WordPicker("./src/resources/wordlist.txt");

    }

    public static GameScreen get(){
        if (instance == null){
            instance = new GameScreen();
        }
        return instance;
    }


    public LetterBox getCurrentBox(){
        return GameScreen.get().grid.getBox(PositionCounter.getRow(),
                PositionCounter.getColumn());
    }

    public LetterBox[] getCurrentRow(){
        return GameScreen.get().grid.getRow(PositionCounter.getRow());
    }


    @Override
    public void update(JFrame frame) {
        // generate word
        this.wordList.generateWord();

        // handle letter key press
        for (char c = 'a'; c <= 'z'; c++) {
            final char key = c;
            this.inputMap.put(KeyStroke.getKeyStroke(key), Character.toString(key));
            this.actionMap.put(Character.toString(key), new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    KeyBoardListener.typeLetter(Character.toUpperCase(key));
                }
            });
        }


        // handle enter key
        this.inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        actionMap.put("Enter", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                KeyBoardListener.pressEnter();
            }
        });


        // handle backspace key
        this.inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "Backspace");
        actionMap.put("Backspace", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                KeyBoardListener.pressBackspace();
            }
        });
    }


    public void restart(){
        this.wordList.generateWord();
    }


}

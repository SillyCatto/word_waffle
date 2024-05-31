package utils;

public class PositionCounter {
    private static int row = 0;

    private static int column = 0;

    public static int getRow(){
        return row;
    }

    public static int getColumn(){
        return column;
    }

    public static void setRow(int r){
        PositionCounter.row = r;
    }

    public static void setColumn(int c){
        PositionCounter.column = c;
    }
}

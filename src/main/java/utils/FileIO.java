package utils;

import java.io.*;

public class FileIO {
    public enum File{
        READER,
        WRITER
    }
    private String out = null;
    private BufferedReader fileReader = null;
    private BufferedWriter fileWriter = null;
    private String filePath = null;
    public FileIO(File fileHandlerType, String filePath){
        try {
            if (fileHandlerType == File.READER){
                this.filePath = filePath;
                fileReader = new BufferedReader(new FileReader(filePath));
            } else if (fileHandlerType == File.WRITER) {
                this.filePath = filePath;
                fileWriter = new BufferedWriter(new FileWriter(filePath));
            }
        } catch (FileNotFoundException e){
            System.err.println("File not found in: " + filePath);
            e.printStackTrace();
        } catch (IOException e){
            System.err.println("Error during writing to file");
            e.printStackTrace();
        }
    }

    public String readLine() throws IOException {
        if (fileReader != null){
            out = fileReader.readLine();
        }
        return out;
    }

    public void writeLine(String content) throws IOException {
        if (fileWriter != null){
            fileWriter.write(content);
        }
    }

    public void close(){
        try{
            if (fileReader != null){
                fileReader.close();
            } else if (fileWriter != null) {
                fileWriter.close();
            }
        }catch (IOException e){
            System.out.println("Error closing file: " + this.filePath);
            e.printStackTrace();
        }
    }
}

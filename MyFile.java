import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.FileWriter;   // Import the FileWriter class to write to files
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets; 
import java.util.*;


/*
 * Class MyFile handeles the below file operations
 * When an object is created, a file is created by default (if not existing already)
 * readFile() - to read all the contents of the file
 * writeFile() - to replace the entire file content with new data
 * updateLine() - to replace the content of a particular line of the file
 * deleteFile() - to delte the file
 */
public class MyFile {
    
    String filename;
    
    /**
     * The Constructor takes in the Filename as an argument
     * The File is created newly, if it is not existing
     */
    MyFile(String name){

        filename = name;

        try {

            File myObj = new File(filename);

            if(myObj.createNewFile()) { // A new file is created while the object is created
              System.out.println("File created: " + myObj.getName());

            }else { // if the file already exists, this block gets executed
              System.out.println("File already exists.");
            }
          }catch(IOException e) { // In case of an IOException while creating the file, it is handle here
            System.out.println("An error occurred."); 
          }
    }

    /**
     * readFile() reads the specified file line by line, and outputs it into the console
     * It throws FileNotFoundException if the file is not found
     */
    public void readFile() throws FileNotFoundException{

      File myObj = new File(filename); // define a File instance
      Scanner myReader = new Scanner(myObj); // a scanner is created for the file mentioned

      while (myReader.hasNextLine()) { // read file line by line
        String data = myReader.nextLine();
        System.out.println(data);
      }
      myReader.close();  // close the scanner
    }

    /**
     * deleteFile() deletes the entire file
     * It throws IOException error incase if unable to delete the file
     */
    public void deleteFile() throws IOException{

      File file = new File(filename);

      if (file.delete()) {
          System.out.println("File deleted successfully");
      }
      else {
          System.out.println("Failed to delete the file");
      }
    }
    /**
     * writeFile() clears the existing content of the file, and writes new data into it
     * It throws IOException error incase if unable to write to the file
     */
    public void writeFile(String content) throws IOException{

      FileWriter myWriter = new FileWriter(filename);
      myWriter.write(content);
      System.out.println("Successfully wrote to the file.");

      myWriter.close();
    }

    /**
     * updateLine() clears the existing data of a particular line in the file. 
     * It then replaces it with the new data passed through the parameters
     * It throws IOException error incase if unable to replace the contents in the given line of the file
     */
    public void updateLine(int lineNumber, String content) throws IOException{

      Path path = Paths.get(filename);
      List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
      lines.set(lineNumber - 1, content);
      Files.write(path, lines, StandardCharsets.UTF_8);

    }

    public static void main(String []args){

        MyFile file1 = new MyFile("sherin.txt"); 

        // trying to read the contents of the File inside a try catch block to handle FileNotFound Exception
        try{
          file1.readFile();
        }catch(FileNotFoundException e){
          System.out.println("Error while reading file: File not found");
        }

        // trying to write to a file 
        String content = "This is the new data entered using writeFile()";
        try{
          file1.writeFile(content);
        }catch(IOException e){
          System.out.println("IO Error while trying to write to the file");
        }

        // trying to update a line of the file 
        String update = "This is the new data entered using writeFile()";
        try{
          file1.writeFile(update);
        }catch(IOException e){
          System.out.println("IO Error while trying to update a line of the file");
        }

        // trying to delete a file 
        try{
          file1.deleteFile();
        }catch(IOException e){
          System.out.println("IO Error while trying to delete the file");
        }

    }
}

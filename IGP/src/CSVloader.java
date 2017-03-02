import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.List;

public class CSVloader {
    
    //Opens and parses through file.
    //Based off the code BIF812 sequenceloader
    public void loadCSVFromFile(){
        //the next two lines show the dialog
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(null);
        //pass the selected file to the loadSequenceFromFile method
        loadCSVFromFile(fc.getSelectedFile().getAbsolutePath());
    }
    
    /**
     * loads a sequence from the indicated file
     * @param filepath the path to the file that contains the sequence to load
     */
    public String loadCSVFromFile(String filepath){
        String tempHolder;
        try {
            tempHolder = new String(Files.readAllBytes(Paths.get(filepath)));
          //new String(Files.lines(Paths.get(filepath)));
            return tempHolder;
            //lines.forEach(s -> System.out.println(s));
        } catch (IOException e) {
            return "error "+e;
        }

    }
}

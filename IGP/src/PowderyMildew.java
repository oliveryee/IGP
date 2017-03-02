import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author sqlitetutorial.net
 */
public class PowderyMildew {
    public static void main(String[] args) {
        System.out.println("Welcome to Test using MiniGenBankSeq");
        
        //Creates instance of Scanner to accept inputs
        Scanner scan= new Scanner(System.in);
         
        System.out.println("Select an option:");
        System.out.println("1. Make Table");
        System.out.println("2. Insert Data");
        System.out.println("3. Select Data");
        System.out.println("4. Calculate Risk Index");
        
        //Takes option
        String text= scan.nextLine();
        scan.close();
        
        //Creation of database driver for database interaction
        Database d1 = new Database();
        switch (Integer.parseInt(text)){
            
            case 1:     d1.makeTable();
                        break;
                        
                        //Destination for Sample has to be specified
            case 2:     d1.insertData("C:\\Users\\Me\\Dropbox\\SEMESTER2\\BIF812\\SampleData.txt");
                        break;
                        
            case 3:     d1.printData();
                        break;
                        
            case 4:     RiskIndex index = new RiskIndex();
                        //Placeholder date. Can add another argument accept for future iterations
                        //Given Start date-->Find Date where sporulation is possible (i.e. Blaeser)-->Return entries
                        index.calculateIndex(index.selectDate("2000-02-02"));
                        break;            
            default:    System.out.println("Invalid selection");
        }
    }
}


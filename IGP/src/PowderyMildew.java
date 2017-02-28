import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author sqlitetutorial.net
 */
public class PowderyMildew {
    public static void main(String[] args) {
        System.out.println("Welcome to Test using MiniGenBankSeq");
        
        //Creates insteance of Scanner to accept inputs
        Scanner scan= new Scanner(System.in);
        
        //Takes in file (i.e. 10k.txt) Do not forget directory 
        System.out.println("Select an option:");
        System.out.println("1. Make Table");
        System.out.println("2. Insert Data");
        System.out.println("3. Select Data");
        System.out.println("4. Calculate Risk Index");
        String text= scan.nextLine();
        Database d1 = new Database();
        switch (Integer.parseInt(text)){
            case 1:     d1.makeTable();
                        break;
            case 2:     d1.insertData("C:\\Users\\Me\\Dropbox\\SEMESTER2\\BIF812\\SampleData.txt");
                        break;
            case 3:     d1.printData();
                        break;
            case 4:     RiskIndex index = new RiskIndex();
                        index.calculateIndex(index.selectDate("2000-02-02"));
                        break;            
            default:    System.out.println("Invalid selection");
        }
    }
}


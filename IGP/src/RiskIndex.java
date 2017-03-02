import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class RiskIndex extends Database{
    //Connects to database and opens Blaeser table retrieving the closest entry to starting date.
    public String selectDate(String date){
        Connection c = null;
        Statement stmt = null;
        String returnDate = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = connectDatabase();
          c.setAutoCommit(false);
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT DATE FROM BLAESER WHERE DATE<='" + date +"' LIMIT 1;"); 
          return rs.getString("DATE");
        } catch ( Exception e ) {
          String error = e.getClass().getName() + ": " + e.getMessage() ;
          return error;
        }
    }
    //Calculates risk index
    //See http://ipm.ucanr.edu/PMG/r302100311.html
    public void calculateIndex(String startDate){
        Connection c = null;
        Statement stmt = null;
        int index = 0;

        String checkDate = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = connectDatabase();
          c.setAutoCommit(false);
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT DATE,GROUP_CONCAT(CASE WHEN TEMP>=21 THEN 1 ELSE 0 END) AS VALID FROM TESTWEATHER WHERE DATE>=" + startDate +" GROUP BY DATE;" );
             while ( rs.next() ) {
             String date = rs.getString("DATE");
             //int temp = rs.getInt("TEMP");
             String valid = rs.getString("VALID");
             
             String[] splitArray = valid.split(",");
             int count = 0;
             int max = 0;
             
             for(String item:splitArray){
                 if (item.equals("1")){
                     count++;
                 }
                 else if (item.equals("2")){
                     count = 0;
                     max = 0;
                     break;
                 }
                 else{
                     if (count>max){
                         max=count;
                     }
                     count=0;
                 }
             }
             
             if (count>max){
                 max=count;
             }

             if(index>=60 && index<=100 && max>=6){
                 index+=20;
             }
             else if(index<60 && max>=6){
                 index+=20;
             }
             else if(index<60 && max<6){
                 index=0;
             }
             else {
                 index-=10;
             }

             System.out.println( "DATE = " + date);
             System.out.println( "MAX = " + max);
             System.out.println( "Index = " + index);
          }
          rs.close();
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Operation done successfully");
} 
}

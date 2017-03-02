import java.sql.*;

public class Database {

    public Connection connectDatabase(){
        try{
            //Need a special jar file
            //I think it was sqlite.jdbc-3.7.2.jar
            //Not entirely sure cause i downloaded 4 of them
            //check github.com/xerial
            //Youtube a tutorial if needed
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Me\\Dropbox\\SEMESTER2\\BIF812\\test.db");
            System.out.println("Connection successful");
            return conn;
        }catch(Exception e){
            System.out.println("well fuck :" + e);
            return null;
        }
      }
    public void makeTable(){
        Statement stmt = null;
        Connection c = null;
        try {
          c = connectDatabase();
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          
          String sql = "CREATE TABLE TESTWEATHER " +
                       "(ID INT PRIMARY KEY     NOT NULL," +
                       " DATE           date    NOT NULL, " + 
                       " TIME           time     NOT NULL, " +
                       " TEMP           INT     NOT NULL, " +
                       " HUMIDITY        INT NOT NULL," + 
                       "WETNESS         INT  NOT NULL);";
          stmt.executeUpdate(sql);
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Table created successfully");
    }
    public void insertData(String filepath){
        Connection c = null;
        Statement stmt = null;
        String holder;
        
        try {
          c = connectDatabase();
          c.setAutoCommit(false);
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          System.out.println("Printing file");
          CSVloader csvloader = new CSVloader();
          
          //Saves files as a string
          holder = csvloader.loadCSVFromFile(filepath);
          //String is divided by new line 
          String lines[] = holder.split("\\r?\\n");
          //Each string is divided by , and stored into arrays
          //arrays called on to make SQL insert statement
          for(String i : lines ) {
              String values[] = i.split(",");
              String sql = "INSERT INTO TESTWEATHER (ID,DATE,TIME,TEMP,HUMIDITY,WETNESS) VALUES ("
                      + values[0] + ", "+"'"
                      + values[1] + "', "+"'"
                      + values[2] + "', "
                      + values[3] + ", "
                      + values[4] + ", "
                      + values[5] + ");";
                             
              System.out.println(sql);
              stmt.executeUpdate(sql);
            }

          stmt.close();
          c.commit();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Records created successfully");
    }
    public void printData(){
            Connection c = null;
            Statement stmt = null;
            try {
              c = connectDatabase();
              c.setAutoCommit(false);
              System.out.println("Opened database successfully");

              stmt = c.createStatement();
              ResultSet rs = stmt.executeQuery( "SELECT * FROM TESTWEATHER;" );
              while ( rs.next() ) {
                 int id = rs.getInt("ID");
                 String date = rs.getString("DATE");
                 String time = rs.getString("TIME");
                 int temp = rs.getInt("TEMP");
                 int humidity = rs.getInt("HUMIDITY");
                 int wetness = rs.getInt("WETNESS");

                 System.out.println( "ID = " + id);
                 System.out.println( "DATE = " + date);
                 System.out.println( "TIME = " + time);
                 System.out.println( "TEMPERATURE = " + temp );
                 System.out.println( "HUMIDITY = " + humidity );
                 System.out.println( "WETNESS = " + wetness );
                 System.out.println();
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

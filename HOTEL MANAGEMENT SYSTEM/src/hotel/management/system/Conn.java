
package hotel.management.system;
import java.sql.*;

public class Conn {
    Connection c;
    Statement s;
    Conn(){
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        c = DriverManager.getConnection("jdbc:mysql:///hotelmanagementsystem","root","premprem2525");
        s=c.createStatement();
    }
        catch(Exception e){
            e.printStackTrace();
        }
        }
}

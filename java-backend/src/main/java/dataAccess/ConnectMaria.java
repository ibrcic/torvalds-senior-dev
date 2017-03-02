/**
 * 
 */
package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import antlr.Token;

/**
 * @author Noah
 *
 */
public class ConnectMaria {

   Connection conn;
   /**
    * @throws SQLException 
    * @throws ClassNotFoundException 
    * 
    */
   public ConnectMaria(String username, String password) throws ClassNotFoundException, SQLException {
      conn = connect(username, password);
   }

  public ConnectMaria(Token token) {
	// TODO Auto-generated constructor stub
}

public java.sql.Connection connect(String username, String password) throws ClassNotFoundException, SQLException
  {
      String driver = "org.mariadb.jdbc.Driver";
//         For testing
//         String url = "jdbc:mariadb://localhost:3306/DB?user=root&password=myPassword");";
      String url = "jdbc:mariadb://torvalds.ist.rit.edu:3306/DB";
      Class.forName(driver);
      Connection conn = DriverManager.getConnection(url, username, password);
      return conn;
  }

public Connection getConn() {
	return conn;
}
   
}

package dz.aak.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
 
public class SQLiteTest{
  private static Connection con;
 
public void run() throws Exception {
 
  // sqlite driver
  Class.forName("org.sqlite.JDBC");
  // database path, if it's new database,
  // it will be created in the project folder
  con = DriverManager.getConnection("jdbc:sqlite:mydb.db");
  Statement stat = con.createStatement();
  stat.executeUpdate("drop table if exists user");
 
  //creating table
  stat.executeUpdate("create table user(id integer,"
    + "firstName varchar(30)," + "age INT," + "sex varchar(15),"
    + "weight INT," + "height INT,"
    + "primary key (id));");
 
  // inserting data
  PreparedStatement prep = con
    .prepareStatement("insert into user values(?,?,?,?,?,?);");
  prep.setString(2, "John");
  prep.setString(3, "21");
  prep.setString(4, "male");
  prep.setString(5, "77");
  prep.setString(6, "185");
  prep.execute();
 
  // getting data
  ResultSet res = stat.executeQuery("select * from user");
  while (res.next()) {
     System.out.println(res.getString("id") + " " + res.getString("age")
              + " " + res.getString("firstName") + " "
              + res.getString("sex") + " " + res.getString("weight")
              + " " + res.getString("height"));
  }
}
  /**
  * @param args
  */
  public static void main(String[] args) {
    try {
      new SQLiteTest().run();
    } catch (Exception e) {
       e.printStackTrace();
      }
  }
 
}
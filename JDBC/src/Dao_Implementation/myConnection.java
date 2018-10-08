package Dao_Implementation;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import ValueObject.TransactionDetails;


public class myConnection {
	Connection connection = null;
	 
    public Connection getConnection(){
    	try {
    		
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			System.out.println("conecting to database ...");
			
			FileReader file = new FileReader("src/Resources/db.properties");

			Properties properties = new Properties();

			properties.load(file);

			connection = DriverManager.getConnection(properties.getProperty("url"),
					properties.getProperty("username"), properties.getProperty("password"));
			 
			
			 
		}catch(Exception e) {
			e.printStackTrace();
		}
		
        return connection;
    }
    public void insert(TransactionDetails TransactionDetails) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO hmkcode.TransactionDetailss (id ,name) VALUES (NULL , ?)");
            preparedStatement.setString(1,  TransactionDetails.getFirst_name());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
    }
    
    
    public void closeConnection(){
        try {
              if (connection != null) {
                  connection.close();
              }
            } catch (Exception e) { 
                //do nothing
            }
    }
 
}

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 
import java.sql.*;

public class CustomerDB {

	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);

		System.out.println("Press (1) to search for another customer or press (2) to Edit a customer's address. ");
		int response = keyboard.nextInt();
		System.out.println("what is the person's last name? "); 

		String name = keyboard.next();  

		int cont = 1;


		while (cont ==1 || cont ==2){ 
			
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Customers?autoReconnect=true&useSSL=false","root","password");  
			PreparedStatement preparedStmt;


			switch(response){ 

			
			case(1):
				
			String query = ("SELECT* FROM customers WHERE LastName = '"+name+"'"); 
			preparedStmt = con.prepareStatement(query); 

			//preparedStmt.setString(1, name);

			Statement stmt=con.createStatement(); 
			ResultSet rs=stmt.executeQuery(query);  


			while(rs.next()){  
				System.out.println(rs.getString(1)+"\n" +"  "+rs.getString(2) + "  "+ rs.getString(3)+ "\n" +"  "+rs.getString(4)+ "\n" +"  "+rs.getString(5)+ "\n" +"  "+rs.getString(6) + "\n"+"  "+rs.getString(7) +"  "+rs.getString(8) + "\n" +"  "+rs.getString(9) + "\n" +"  "+rs.getString(10) + "\n" +"  "+rs.getString(11) + "\n" +"  "+rs.getString(12)); 
			} 

			break;

			case(2):

			System.out.println("Enter a customer ID number"); 
			cont = keyboard.nextInt(); 
			break;  

			} 
			
			con.close(); 

		} catch(Exception e){ System.out.println(e); 
		keyboard.close(); 
		
		} 
		System.out.println("Press (1) to search for another customer or press (2) to Edit a customer's address.");
		cont = keyboard.nextInt(); 

		if(cont ==1){
		System.out.println("what is the person's last name? "); 
		name = keyboard.next();  
		}

		}  



	}  


}



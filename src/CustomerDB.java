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

		System.out.println("Press (1) to search for another customer or press (2) to Edit a customer's address. Press anything else to quit. ");
		int response = keyboard.nextInt();
		keyboard.nextLine();
		System.out.println("what is the person's last name? "); 

		String name = keyboard.nextLine();  

		//int cont = 1;


		while (response ==1 || response ==2){ 
			
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

			System.out.println("Enter the person's title.");
			String title = keyboard.nextLine();
			
			System.out.println("Enter the person first name.");
			String fname = keyboard.next(); 
			
			
			System.out.println("Enter the person's street address"); 
			String address = keyboard.nextLine(); 
			
			keyboard.nextLine();
			
			System.out.println("Enter the persons city."); 
			String city = keyboard.next();  
			
			System.out.println("Enter the state");
			String state = keyboard.next(); 
			
			System.out.println("Enter the persons zipcode."); 
			String zip = keyboard.next(); 
			
			System.out.println("Enter the persons email address.");
			String email = keyboard.next(); 
			
			System.out.println("Enter the persons position."); 
			String position = keyboard.next(); 
			
			System.out.println("Enter the persons company.");
			String company = keyboard.next();  
			
			String fullname = fname.concat(" ").concat(name);
			
			query = ("Insert INTO customers (FullName, Title, FirstName, LastName, StreetAddress, City, State, ZipCode, EmailAddress, Position, Company)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			preparedStmt = con.prepareStatement(query);
			
			
			preparedStmt.setString(1, fullname);
			preparedStmt.setString(2, title); 
			preparedStmt.setString(3, fname);
			preparedStmt.setString(4, name);
			preparedStmt.setString(5, address); 
			preparedStmt.setString(6, state);
			preparedStmt.setString(7, city);
			preparedStmt.setString(8, zip);
			preparedStmt.setString(9, email);
			preparedStmt.setString(10, position);
			preparedStmt.setString(11, company);			
			
			preparedStmt.executeUpdate();
			System.out.println("Inserted ");
			break;
			}
			
			con.close(); 

		} catch(Exception e){ System.out.println(e); 
		keyboard.close(); 
		
		} 
		System.out.println("Press (1) to search for another customer or press (2) to Edit a customer's address.");
		response = keyboard.nextInt(); 
		keyboard.nextLine();

		if(response ==1){
		System.out.println("what is the person's last name? "); 
		name = keyboard.nextLine();  
		}

		}  



	}  


}



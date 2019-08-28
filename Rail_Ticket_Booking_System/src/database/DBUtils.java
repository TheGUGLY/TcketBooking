package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import pojos.Ticket;

public class DBUtils {
	
	Connection connection = null;
	Statement bookTicketStatement = null;
	{
		try{
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tested", "postgres", "bamtam");
			System.out.println("connection : "+connection);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}	
	
	public boolean bookTicket(Ticket ticket){
		try{
		bookTicketStatement = connection.createStatement();
		System.out.println(connection);
		String insertTicket = 
				"INSERT INTO TICKET(TNUMBER,PNAME,PHONE,SEATS,TRAIN_NUMBER,TRAIN_NAME,TOTAL_PRICE) "
				+ "VALUES ("+ticket.getTicketNumber()+",'"+ticket.getName()+"','"+ticket.getPhonenumber()+"',"+ticket.getSeats()+",'"+ticket.getTrain_number()+"','"+ticket.getTrain_name()+"',"+ticket.getTotalPrice()+")";
		bookTicketStatement.execute(insertTicket);
		return true;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}
}

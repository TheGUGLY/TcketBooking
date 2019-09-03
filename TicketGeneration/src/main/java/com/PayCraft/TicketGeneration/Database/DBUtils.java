package com.PayCraft.TicketGeneration.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.PayCraft.TicketGeneration.pojos.Ticket;

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
	
	public Set<String> getStations(){
		try{
			Set<String> stations = new HashSet<String>();
			Statement getStations = connection.createStatement();
			String getStationQuery = "Select * from Stations";
			ResultSet rs = getStations.executeQuery(getStationQuery);
			while(rs.next()){
				stations.add(rs.getString("STATION_NAME"));
			}
			return stations;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean bookTicket(Ticket ticket){
		try{
		bookTicketStatement = connection.createStatement();
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

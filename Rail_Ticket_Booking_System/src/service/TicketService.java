package service;

import java.util.ArrayList;
import java.util.List;

import database.DBUtils;
import pojos.Ticket;
import pojos.Train;

public class TicketService {
	private DBUtils db = new DBUtils();
	public List<Train> fetchTrains(String source,String destination){
		List<Train> trains = new ArrayList<Train>();
		trains.add(new Train(12123,"Train1"));
		trains.add(new Train(12232,"Train2"));
		trains.add(new Train(73221,"Train3"));
		return trains;
	}
	
	public boolean bookTicket(Ticket ticket){
		return db.bookTicket(ticket);
	}
}

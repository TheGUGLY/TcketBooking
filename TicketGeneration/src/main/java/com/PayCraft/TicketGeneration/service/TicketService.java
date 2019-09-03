package com.PayCraft.TicketGeneration.service;

import java.util.Set;

import com.PayCraft.TicketGeneration.Database.DBUtils;
import com.PayCraft.TicketGeneration.pojos.Ticket;

public class TicketService {
	
	private DBUtils db = new DBUtils();
	
	
	public Set<String> getStations(){
		return db.getStations();
	}
	
	
	public boolean bookTicket(Ticket ticket){
		return db.bookTicket(ticket);
	}
}

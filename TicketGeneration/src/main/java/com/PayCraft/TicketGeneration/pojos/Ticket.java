package com.PayCraft.TicketGeneration.pojos;

public class Ticket {
		private int ticketNumber;
		private String name;
		private String phonenumber;
		private int seats;
		private String train_number;
		private String train_name;
		private int totalPrice;
		private int MAX=9999,MIN=999;
		@Override
		public String toString() {
			return "Ticket [name=" + name + ", phonenumber=" + phonenumber + ", seats=" + seats + ", train_number="
					+ train_number + ", train_name=" + train_name + ", totalPrice=" + totalPrice + "]";
		}
		public String getName() {
			return name;
		}
		public String getPhonenumber() {
			return phonenumber;
		}
		public int getSeats() {
			return seats;
		}
		
		public String getTrain_number() {
			return train_number;
		}
		public String getTrain_name() {
			return train_name;
		}
		public int getTotalPrice() {
			return totalPrice;
		}
		public Ticket(String name, String phonenumber, int seats, String train_number, String train_name, int totalPrice) {
			super();
			this.ticketNumber = ((Double)(Math.random()*((MAX-MIN)+1)+MIN)).intValue();
			this.name = name;
			this.phonenumber = phonenumber;
			this.seats = seats;
			this.train_number = train_number;
			this.train_name = train_name;
			this.totalPrice = totalPrice;
		}
		public int getTicketNumber() {
			return ticketNumber;
		}
		public Ticket() {
			super();
		}
		
		
		
}

package main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import pojos.Ticket;
import service.TicketService;
import java.awt.Color;

public class Booking_Details extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textName;
	private JTextField txtPhone;
	private Integer price;
	private JLabel lblShowPrice;
	private Integer priceToShow;
	private Ticket ticket;
	private TicketService service = new TicketService();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Booking_Details frame = new Booking_Details("","");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Booking_Details(String trainName,String trainNumber) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 434, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(46, 178, 61, 16);
		contentPane.add(lblName);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number :");
		lblPhoneNumber.setBounds(46, 222, 113, 16);
		contentPane.add(lblPhoneNumber);
		
		JLabel lblNumberOfSeats = new JLabel("Number of seats :");
		lblNumberOfSeats.setBounds(46, 267, 113, 16);
		contentPane.add(lblNumberOfSeats);
		
		JLabel lblTotalPrice = new JLabel("Total Price :");
		lblTotalPrice.setBounds(46, 318, 91, 16);
		contentPane.add(lblTotalPrice);
		
		textName = new JTextField();
		textName.setBounds(243, 173, 130, 26);
		contentPane.add(textName);
		textName.setColumns(10);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(243, 217, 130, 26);
		contentPane.add(txtPhone);
		txtPhone.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				priceToShow = Integer.parseInt(comboBox.getSelectedItem().toString()) * price;
				lblShowPrice.setText(priceToShow.toString());
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		comboBox.setBounds(243, 263, 130, 27);
		contentPane.add(comboBox);
		
		JLabel lblMessage = new JLabel("");
		lblMessage.setForeground(Color.RED);
		lblMessage.setBounds(41, 369, 352, 16);
		contentPane.add(lblMessage);
		
		JButton btnConfirmBooking = new JButton("Confirm Booking");
		btnConfirmBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name =  textName.getText();
				if(name.isEmpty()){
					lblMessage.setText("Please Enter Name");
					return;
				}
				String phoneNumber = txtPhone.getText();
				if(phoneNumber.isEmpty()){
					lblMessage.setText("Please Enter valid Phone Number");
					return;
				}
				int seats = Integer.parseInt(comboBox.getSelectedItem().toString());
				ticket = new Ticket(name, phoneNumber, seats, trainNumber, trainName,priceToShow);
				System.out.println(ticket.getTicketNumber());
			boolean status = service.bookTicket(ticket);
			if(!status){
				JOptionPane.showMessageDialog(getParent(),"Ticket Couldn't be booked");  
			}
			else{
				JOptionPane.showMessageDialog(getParent(),"Ticket booked Successfully");
				showTicket();
				
			}
			}
		});
		btnConfirmBooking.setBounds(153, 397, 142, 29);
		contentPane.add(btnConfirmBooking);
		
		price = (Integer.parseInt(trainNumber) - 1000)/2;
		lblShowPrice = new JLabel(price.toString());
		lblShowPrice.setBounds(275, 318, 61, 16);
		contentPane.add(lblShowPrice);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(18, 6, 395, 155);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTrainNumber = new JLabel("Train Number :");
		lblTrainNumber.setBounds(21, 23, 94, 16);
		panel.add(lblTrainNumber);
		
		JLabel lblTrainName = new JLabel("Train Name :");
		lblTrainName.setBounds(21, 63, 91, 16);
		panel.add(lblTrainName);
		
		JLabel labelShowTrainNumber = new JLabel(trainNumber);
		labelShowTrainNumber.setBounds(196, 23, 61, 16);
		panel.add(labelShowTrainNumber);
		
		JLabel labelShowTrainName = new JLabel(trainName);
		labelShowTrainName.setBounds(196, 63, 61, 16);
		panel.add(labelShowTrainName);
		
	}
	
	private void showTicket(){
		this.dispose();
		TicketDisplay display = new TicketDisplay(ticket);
		display.setVisible(true);
	}
}

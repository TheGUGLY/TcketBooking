package com.PayCraft.TicketGeneration.UI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.PayCraft.TicketGeneration.service.TicketService;

public class Home extends JFrame {
	
	private TicketService service = new TicketService();
	private JPanel contentPane;
	private String toStation = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 6, 343, 393);
		tabbedPane.setBackground(new Color(255, 127, 80));
		contentPane.add(tabbedPane);
		
		JPanel StationPane = new JPanel();
		tabbedPane.addTab("Station", null, StationPane, "Select Destination Station");
		tabbedPane.setEnabledAt(0, true);
		tabbedPane.setBackgroundAt(0, new Color(255, 140, 0));
		StationPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Add Ticket");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(89, 317, 117, 29);
		StationPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 274, 322, 39);
		StationPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblQuantity = new JLabel("Quantity Selection");
		lblQuantity.setBounds(19, 15, 115, 16);
		panel.add(lblQuantity);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(210, 11, 64, 20);
		panel.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 322, 274);
		StationPane.add(panel_1);
		panel_1.setLayout(null);
		List<JButton> buttons = new ArrayList<JButton>();
		int x=0,y=0,count=2;
		for(String station : service.getStations()){
			JButton button = new JButton(station);
			button.setBounds(x, y, 150, 25);
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) { 
					//toStation = getText();
				}
			});
			panel_1.add(button);
			buttons.add(button);
			if(buttons.size()>=count){
				count = count+2;
				x=0;
				y = y+20;
			}
			else{
				x+=175;
			}
		}
		
		
		JPanel ZonePane = new JPanel();
		tabbedPane.addTab("Zone", null, ZonePane, null);
		tabbedPane.setBackgroundAt(1, new Color(255, 140, 0));
		tabbedPane.setEnabledAt(1, true);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(346, 6, 328, 342);
		panel_2.setBackground(new Color(240, 248, 255));
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(255, 140, 0), 2, true));
		panel_3.setBackground(new Color(255, 140, 0));
		panel_3.setBounds(0, 0, 328, 30);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblSummary = new JLabel("Summary");
		lblSummary.setBounds(6, 6, 61, 16);
		panel_3.add(lblSummary);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 35, 328, 24);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(6, 6, 32, 16);
		panel_4.add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(70, 6, 16, 16);
		panel_4.add(lblTo);
		
		JLabel lblQty = new JLabel("Qty");
		lblQty.setBounds(118, 6, 32, 16);
		panel_4.add(lblQty);
		
		JLabel lblFare = new JLabel("Fare");
		lblFare.setBounds(179, 6, 32, 16);
		panel_4.add(lblFare);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(448, 360, 117, 29);
		contentPane.add(btnNewButton_1);
	}
}

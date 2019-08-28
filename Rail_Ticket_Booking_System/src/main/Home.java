package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jdatepicker.JDatePicker;

import pojos.Train;
import service.TicketService;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Home extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TicketService ts = new TicketService();
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JLabel lblAvailableTrains ;
	private String colNames[] = {"Train_Number","Train_Name"};
	private DefaultTableModel model = new DefaultTableModel(colNames,10); 
//			new DefaultTableModel(new Object[][] {
//	      { "some", "text" }, { "any", "text" }, { "even", "more" },
//	      { "text", "strings" }, { "and", "other" }, { "text", "values" } },
//			new Object[] {  });
	private JTable table;
	

	/**
	 * Launch the application. 98260 12345
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 829, 584);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(22, 15, 788, 44);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label_1 = new JLabel("Train Reservation System");
		label_1.setFont(new Font("Krungthep", Font.BOLD | Font.ITALIC, 18));
		label_1.setBounds(229, 6, 248, 24);
		panel.add(label_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBounds(22, 71, 193, 216);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("From :");
		label.setBounds(6, 25, 40, 16);
		panel_1.add(label);
		
		JLabel lblTo = new JLabel("To :");
		lblTo.setBounds(6, 60, 40, 16);
		panel_1.add(lblTo);
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setBounds(6, 91, 61, 16);
		panel_1.add(lblDate);
		
		JComboBox comboBox_Source = new JComboBox();
		comboBox_Source.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
			}
		});
		comboBox_Source.setModel(new DefaultComboBoxModel(new String[] {"Select", "JHANSI", "KANPUR", "BANARAS", "BANGLORE", "ALLAHABAD", "LUCKNOW", "NEW DELHI", "AGRA", "GWALIOR", "INDORE", "BHOPAL"}));
		comboBox_Source.setBounds(58, 21, 129, 27);
		panel_1.add(comboBox_Source);
		
		JComboBox comboBox_Destination = new JComboBox();
		comboBox_Destination.setModel(new DefaultComboBoxModel(new String[] {"Select", "JHANSI", "KANPUR", "BANARAS", "BANGLORE", "ALLAHABAD", "LUCKNOW", "NEW DELHI", "AGRA", "GWALIOR", "INDORE", "BHOPAL"}));
		comboBox_Destination.setBounds(58, 56, 129, 27);
		panel_1.add(comboBox_Destination);
		
		JDatePicker datePicker = new JDatePicker();
		datePicker.getFormattedTextField().setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		datePicker.setBounds(47, 84, 140, 29);
		panel_1.add(datePicker);
		
		
		JButton btnFindTrains = new JButton("Find Trains");
		btnFindTrains.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String source = comboBox_Source.getSelectedItem().toString();
				if(comboBox_Source.getSelectedIndex() == 0){
					JOptionPane.showMessageDialog(getParent(), "Please Select the Source");
					comboBox_Source.requestFocus();
					return;
				}
				String destination = comboBox_Destination.getSelectedItem().toString();
				if(comboBox_Destination.getSelectedIndex() == 0){
					JOptionPane.showMessageDialog(getParent(), "Please Select the Destination");
					comboBox_Destination.requestFocus();
					return;
				}
				//LocalDate date = (  (Date) datePicker.getModel().getValue() ).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
//				if(date == null){
//					JOptionPane.showMessageDialog(getParent(), "Please Select a Travell Date");
//					datePicker.requestFocus();
//					return;
//				}
//				if(date.isBefore((LocalDate.now()))){
//					JOptionPane.showMessageDialog(getParent(), "Humans Can't Travell in the past time.");
//					datePicker.requestFocus();
//					return;
//				}
				if(source.equals(destination)){
					comboBox_Source.requestFocus();
					JOptionPane.showMessageDialog(getParent(), "Source and destination Station can't be same");
					return ; 
				}
				lblAvailableTrains.setVisible(true);
				List<Train> trains = ts.fetchTrains(source,destination);
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				for(Train t :trains){
					Object[] obj = new Object[2];
					obj[0] = t.getTrain_number();
					obj[1] = t.getTrain_name();
					model.addRow(obj);
				}
				table.setModel(model);
				
			}
		});
		
		btnFindTrains.setBounds(41, 147, 117, 29);
		panel_1.add(btnFindTrains);
		
		JButton btnResetSearch = new JButton("Reset Search");
		btnResetSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_Source.setSelectedIndex(0);
				comboBox_Destination.setSelectedIndex(0);
			}
		});
		btnResetSearch.setBounds(41, 181, 117, 29);
		panel_1.add(btnResetSearch);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_2.setBounds(782, 67, -519, 413);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		table = new JTable((TableModel)model);
		table.setBorder(new LineBorder(new Color(255, 0, 0), 3, true));
		table.setShowVerticalLines(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 33, -520, 380);
		panel_2.add(scrollPane);
		scrollPane.setViewportView(table);
		
		
		
		JButton button = new JButton("Check Availability & Book");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				//String trainName = table.getValueAt(row, 0).toString();
				//String trainNumber = table.getValueAt(row, 1).toString();
				Booking_Details booking = new Booking_Details("TestTrain","1111");//trainName,trainNumber);
				booking.setVisible(true);
			}
		});
		button.setBounds(420, 501, 205, 29);
		contentPane.add(button);
		
		lblAvailableTrains = new JLabel("Available Trains");
		lblAvailableTrains.setBounds(475, 78, 121, 16);
		contentPane.add(lblAvailableTrains);
		lblAvailableTrains.setVisible(false);
	}
}

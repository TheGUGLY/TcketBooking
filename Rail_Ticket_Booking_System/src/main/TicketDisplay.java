package main;

import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import pojos.Ticket;

public class TicketDisplay extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicketDisplay frame = new TicketDisplay(new Ticket());
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
	public TicketDisplay(Ticket ticket) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 375, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(21, 18, 319, 37);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblRailTicket = new JLabel("Rail Ticket");
		lblRailTicket.setBounds(116, 10, 73, 16);
		panel.add(lblRailTicket);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(21, 81, 319, 259);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Train Number :");
		label.setBounds(23, 80, 94, 16);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("Train Name :");
		label_1.setBounds(23, 108, 91, 16);
		panel_1.add(label_1);
		
		JLabel lblPassengerName = new JLabel("Passenger Name :");
		lblPassengerName.setBounds(23, 139, 113, 16);
		panel_1.add(lblPassengerName);
		
		JLabel label_5 = new JLabel("Phone Number :");
		label_5.setBounds(23, 167, 113, 16);
		panel_1.add(label_5);
		
		JLabel label_6 = new JLabel("Number of seats :");
		label_6.setBounds(23, 195, 113, 16);
		panel_1.add(label_6);
		
		JLabel label_7 = new JLabel("Total Price :");
		label_7.setBounds(23, 223, 91, 16);
		panel_1.add(label_7);
		
		JLabel lblTicketNumber = new JLabel("Ticket Number :");
		lblTicketNumber.setBounds(23, 52, 113, 16);
		panel_1.add(lblTicketNumber);
		
		JLabel lblShowTicketNumber = new JLabel(((Integer)ticket.getTicketNumber()).toString());
		lblShowTicketNumber.setBounds(148, 52, 165, 16);
		panel_1.add(lblShowTicketNumber);
		
		JLabel lblShowTrainNumber = new JLabel(ticket.getTrain_number());
		lblShowTrainNumber.setBounds(148, 80, 165, 16);
		panel_1.add(lblShowTrainNumber);
		
		JLabel lblShowTrainName = new JLabel(ticket.getTrain_name());
		lblShowTrainName.setBounds(148, 108, 165, 16);
		panel_1.add(lblShowTrainName);
		
		JLabel lblShowPName = new JLabel(ticket.getName());
		lblShowPName.setBounds(148, 139, 165, 16);
		panel_1.add(lblShowPName);
		
		JLabel lblShowPNumber = new JLabel(ticket.getPhonenumber());
		lblShowPNumber.setBounds(148, 167, 165, 16);
		panel_1.add(lblShowPNumber);
		
		JLabel lblShowSeats = new JLabel(((Integer)ticket.getSeats()).toString());
		lblShowSeats.setBounds(148, 195, 165, 16);
		panel_1.add(lblShowSeats);
		
		JLabel lblShowPrice = new JLabel(((Integer)ticket.getTotalPrice()).toString());
		lblShowPrice.setBounds(148, 223, 165, 16);
		panel_1.add(lblShowPrice);
		
		JButton btnSaveTicket = new JButton("Save Ticket");
		btnSaveTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String location = chooseLocation();
				System.out.println(location);
				BufferedImage image = createImage(panel_1);
				if(saveImage(image,ticket.getTicketNumber(),location)){
					JOptionPane.showMessageDialog(getParent(), "Ticket Saved");
				}
				else{
					JOptionPane.showMessageDialog(getParent(), "Ticket couldn't be Saved");
				}
			}
		});
		btnSaveTicket.setBounds(21, 373, 117, 29);
		contentPane.add(btnSaveTicket);
		
		JButton btnPrintTicket = new JButton("Print Ticket");
		btnPrintTicket.setBounds(137, 373, 117, 29);
		contentPane.add(btnPrintTicket);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnDone.setBounds(252, 373, 117, 29);
		contentPane.add(btnDone);
	}
	
	//getiing jpanel as Bufferedimage
	public BufferedImage createImage(JPanel panel) {
	    int w = panel.getWidth();
	    int h = panel.getHeight();
	    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g = bi.createGraphics();
	    panel.paint(g);
	    g.dispose();
	    return bi;
	}
	
	// saving ticket panel as image
	public boolean saveImage(BufferedImage image,int number,String location){
		File outputfile = new File(location+"/Ticket"+number+".jpg");
		try{
		ImageIO.write(image, "jpg", outputfile);
		return true;
		}
		catch(IOException ioe){
			System.out.println(ioe.getMessage());
			return false;
		}
		
	}
	//location sellection to save ticket as image
	public String chooseLocation(){
		JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle("choosertitle");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);
	    return chooser.getCurrentDirectory().getAbsolutePath();
	}
}

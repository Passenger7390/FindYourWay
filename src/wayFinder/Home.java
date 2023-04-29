package wayFinder;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
//import java.text.SimpleDateFormat;
import java.util.*;

public class Home {
	
	ImageIcon rtuBench = new ImageIcon(Home.class.getResource("/resources/benches.png"));
	ImageIcon rtuBench1 = new ImageIcon(Home.class.getResource("/resources/bench1.jpeg"));
	Image temp;
	int x = 1920;
	int y = 1080;
	private JFrame frame;
	private JTextField textField;
	
	int choice;
	ButtonGroup g = new ButtonGroup();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// Add code to install java on target machine
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
	}
	
	// Code ni Jaycy -------------------------------------------------------------------------
	/*
	 * class ImagePanel extends JComponent {
	    private Image image;
	    ImagePanel(Image image) {
	        this.image = image;
	    }
	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, this);
	    }
	} 
	 */
	
	// End ng Code ni Jaycy ------------------------------------------------------------------

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Welcome");
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.getContentPane().setFocusTraversalPolicyProvider(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(x,y);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		
		// Scaling Image
				temp = rtuBench.getImage();
				temp = temp.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
				rtuBench = new ImageIcon(temp);
				
				temp = rtuBench1.getImage();
				temp = temp.getScaledInstance(x, y, Image.SCALE_SMOOTH);
				rtuBench1 = new ImageIcon(temp);
		
		JPanel floors = new JPanel();
		floors.setBounds(0, 0, x, y);
		frame.getContentPane().add(floors);
		floors.setLayout(null);
		
		JButton thirdBtn = new JButton("");
		thirdBtn.setIcon(new ImageIcon(Home.class.getResource("/resources/1st-floor-engraved-sign-se-5835_L2_003_wh.png")));
		thirdBtn.setOpaque(false);
		thirdBtn.setContentAreaFilled(false);
		thirdBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { choice = 3; }});
		thirdBtn.setBounds(1318, 372, 350, 551);
		floors.add(thirdBtn);
		
		JButton secondBtn = new JButton("");
		secondBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { choice = 2; }});
		secondBtn.setIcon(new ImageIcon(Home.class.getResource("/resources/1st-floor-engraved-sign-se-5835_L2_002_wh.png")));
		secondBtn.setContentAreaFilled(false);
		secondBtn.setOpaque(false);
		secondBtn.setBounds(802, 372, 350, 551);
		floors.add(secondBtn);
		
		JButton firstBtn = new JButton("");
		firstBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { choice = 1; }});
		firstBtn.setOpaque(false);
		firstBtn.setContentAreaFilled(false);
		firstBtn.setIcon(new ImageIcon(Home.class.getResource("/resources/1st-floor-engraved-sign-se-5835_L2_001_wh.png")));
		firstBtn.setBounds(258, 372, 350, 551);
		floors.add(firstBtn);
		
		g.add(firstBtn);
		g.add(secondBtn);
		g.add(thirdBtn);
		
		JLabel title1lbl = new JLabel("that you will go!");
		title1lbl.setForeground(new Color(255, 255, 255));
		title1lbl.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 99));
		title1lbl.setBounds(468, 196, 990, 184);
		floors.add(title1lbl);
		
		JLabel titlelbl = new JLabel("Choose the floor level");
		titlelbl.setHorizontalAlignment(SwingConstants.CENTER);
		titlelbl.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 99));
		titlelbl.setForeground(new Color(255, 255, 255));
		titlelbl.setBounds(258, 1, 1410, 320);
		floors.add(titlelbl);
		
		JLabel rtuB1lbl = new JLabel("");
		rtuB1lbl.setBounds(0, 0, x, y);
		rtuB1lbl.setIcon(rtuBench1);
		floors.add(rtuB1lbl);
		
		floors.setVisible(false);
		
		JPanel map = new JPanel();
		map.setBackground(new Color(210, 209, 167));
		map.setBounds(0, 0, x, y);
		frame.getContentPane().add(map);
		map.setLayout(null);
		
		JLabel camMaplbl = new JLabel("");
		camMaplbl.setIcon(new ImageIcon(Home.class.getResource("/resources/campus_map.png")));
		camMaplbl.setBounds(115, 23, 338, 178);
		map.add(camMaplbl);
		
		JLabel maplbl = new JLabel("");
		maplbl.setIcon(new ImageIcon(Home.class.getResource("/resources/2BF73735-82D0-4B7A-912A-E624C164DCE5.JPEG")));
		maplbl.setBounds(115, 212, 977, 811);
		map.add(maplbl);
		
		JButton nextbtn = new JButton("");
		nextbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				map.setVisible(false);
				floors.setVisible(true);
			}});
		nextbtn.setBorderPainted(false);
		nextbtn.setIcon(new ImageIcon(Home.class.getResource("/resources/images.png")));
		nextbtn.setBounds(1618, 944, 292, 125);
		map.add(nextbtn);
		
		JLabel legendslbl = new JLabel("");
		legendslbl.setIcon(new ImageIcon(Home.class.getResource("/resources/legends.png")));
		legendslbl.setBounds(1383, 55, 277, 113);
		map.add(legendslbl);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Home.class.getResource("/resources/bldgs.png")));
		lblNewLabel.setBounds(1299, 195, 428, 714);
		map.add(lblNewLabel);
		map.setVisible(false);
		
		
		
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/resources/RTU_Seal.png")));
		frame.getContentPane().setLayout(null);
		
		JPanel home = new JPanel();
		home.setBounds(0, 0, x, y);
		
		frame.getContentPane().add(home);
		home.setLayout(null);
		
		JButton exitbtn = new JButton("");
		exitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { frame.dispose(); }});
		
		JButton loginbtn = new JButton("");
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home.setVisible(false);
				map.setVisible(true);
			}});
		loginbtn.setIcon(new ImageIcon(Home.class.getResource("/resources/awd.png")));
		loginbtn.setBounds(1238, 627, 212, 70);
		home.add(loginbtn);
		exitbtn.setIcon(new ImageIcon(Home.class.getResource("/resources/images (2).png")));
		exitbtn.setBounds(1698, 995, 212, 74);
		home.add(exitbtn);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 40));
		textField.setBounds(1073, 531, 546, 54);
		home.add(textField);
		textField.setColumns(10);
		
		JLabel rtuHeader = new JLabel("");
		rtuHeader.setIcon(new ImageIcon(Home.class.getResource("/resources/RTU_Portal_Header.png")));
		rtuHeader.setBounds(254, 10, 1418, 379);
		home.add(rtuHeader);
		
		JLabel studNum = new JLabel("STUDENT NUMBER: ");
		studNum.setForeground(new Color(0, 64, 128));
		studNum.setFont(new Font("Arial", Font.PLAIN, 72));
		studNum.setBounds(368, 505, 747, 107);
		home.add(studNum);
		JLabel homebg = new JLabel("");
		homebg.setBounds(0, 0, x, y);
		homebg.setIcon(rtuBench);
		home.add(homebg);
		
	}
}



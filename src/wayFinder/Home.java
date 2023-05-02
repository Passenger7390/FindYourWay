package wayFinder;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Home {
	
	ImageIcon rtuBench = new ImageIcon(Home.class.getResource("/resources/benches.png"));
	ImageIcon rtuBench1 = new ImageIcon(Home.class.getResource("/resources/bench1.jpeg"));
	ImageIcon fmap = new ImageIcon(Home.class.getResource("/resources/C685686A-EEDC-42E1-BEC5-71BF7B8DD910.JPEG"));
	ImageIcon back = new ImageIcon(Home.class.getResource("/resources/png-clipart-logo-organization-graphy-brand-go-back-button-angle-text.png"));
	ImageIcon smap = new ImageIcon(Home.class.getResource("/resources/1B335755-976F-4A7E-B1CA-9E470732AA65.JPEG"));
	ImageIcon exit = new ImageIcon(Home.class.getResource("/resources/images (2).png"));
	ImageIcon tmap = new ImageIcon(Home.class.getResource("/resources/B9EDB404-BC52-467A-8590-B1D145C43187.JPEG"));
	
	int x = 1920;
	int y = 1080;
	private JFrame frame;
	private JTextField textField;
	

	private JRadioButton firstBtn = new JRadioButton("");
	private JRadioButton secondBtn = new JRadioButton("");
	private JRadioButton thirdBtn = new JRadioButton("");
	
	private JButton btnNextFlr = new JButton("");
	private JButton loginbtn = new JButton("");
	private JButton exitbtn = new JButton("");
	private JButton btnBack1 = new JButton("");
	private JButton btnBack2 = new JButton("");
	private JButton btnBack3 = new JButton("");
	private JButton exitbtn_1 = new JButton("");
	private JButton exitbtn2 = new JButton("");
	private JButton exitbtn3 = new JButton("");
	private JButton nextbtn = new JButton("");
	
	//maps
	private JLabel fmaplbl = new JLabel("");
	private JLabel fmaplbl2 = new JLabel("");
	private JLabel fmaplbl3 = new JLabel("");
	
	//floor titles
	JLabel lbl1stFlr = new JLabel("1st Floor");
	JLabel lbl2ndFlr = new JLabel("2nd Floor");
	JLabel lbl3rdFlr = new JLabel("3rd Floor");
	
	private JPanel map = new JPanel();
	private JPanel floors = new JPanel();
	private JPanel home = new JPanel();
	private JPanel firstPnl = new JPanel();
	private JPanel secondPnl = new JPanel();
	private JPanel thirdPnl = new JPanel();
	
	int choice;
	ButtonGroup g = new ButtonGroup();
	Border blueBorder = new LineBorder(Color.blue, 5);
	
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
		connect();
		initialize();
		
	}
	
	// Code ni Jaycy -------------------------------------------------------------------------
	private void connect() {
		Connection con;
		String link = "jdbc:mysql://localhost/test";
		String user = "root";
		String pass = "adminroot";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(link,user,pass);
			//System.out.println("Connected Successfully!");
			//JOptionPane.showMessageDialog(null, "Connected Successfully!");
		} catch(ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Connection Problem!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Connection Problem!");
			e.printStackTrace();
		}
	}
	
	private ImageIcon scaleImg(ImageIcon imgico, int x, int y) {
		Image temp;
		temp = imgico.getImage();
		temp = temp.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		imgico = new ImageIcon(temp);
		return imgico;
	}
	
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
				rtuBench = scaleImg(rtuBench, x, y);
				rtuBench1 = scaleImg(rtuBench1, x ,y);	
				fmap = scaleImg(fmap, 460, 650);
				back = scaleImg(back, 129, 150);
				smap = scaleImg(smap, 460, 650);
				tmap = scaleImg(tmap, 460, 650);
		
		thirdPnl.setBounds(0, 0, x, y);
		frame.getContentPane().add(thirdPnl);
		thirdPnl.setBackground(new Color(210, 209, 167));
		thirdPnl.setVisible(false);
		thirdPnl.setLayout(null);
				
		lbl3rdFlr.setBounds(735, 50, 450, 150);
		lbl3rdFlr.setHorizontalAlignment(SwingConstants.CENTER);
		lbl3rdFlr.setFont(new Font("Segoe UI", Font.BOLD, 99));
		thirdPnl.add(lbl3rdFlr);
			
		btnBack3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floors.setVisible(true);
				secondPnl.setVisible(false);
				thirdPnl.setVisible(false);
				firstPnl.setVisible(false);
				home.setVisible(false);
				map.setVisible(false);
			}
		});
		btnBack3.setBounds(25, 25, 129, 150);
		btnBack3.setOpaque(false);
		btnBack3.setContentAreaFilled(false);
		btnBack3.setBorderPainted(false);
		btnBack3.setIcon(back);
		thirdPnl.add(btnBack3);
		
		exitbtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home.setVisible(true);
				map.setVisible(false);
				firstPnl.setVisible(false);
				secondPnl.setVisible(false);
				thirdPnl.setVisible(false);
				floors.setVisible(false);
			}
		});
		exitbtn3.setIcon(exit);
		exitbtn3.setBounds(1698, 995, 212, 74);
		thirdPnl.add(exitbtn3);
		
		fmaplbl3.setIcon(tmap);
		fmaplbl3.setBounds(1267, 238, 460, 650);
		thirdPnl.add(fmaplbl3);
		
		secondPnl.setBounds(0, 0, x, y);
		frame.getContentPane().add(secondPnl);
		secondPnl.setVisible(false);
		secondPnl.setBackground(new Color(210, 209, 167));
		secondPnl.setLayout(null);
		
		lbl2ndFlr.setBounds(735, 50, 450, 150);
		lbl2ndFlr.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2ndFlr.setFont(new Font("Segoe UI", Font.BOLD, 99));
		secondPnl.add(lbl2ndFlr);
		
		btnBack2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floors.setVisible(true);
				secondPnl.setVisible(false);
				thirdPnl.setVisible(false);
				firstPnl.setVisible(false);
				home.setVisible(false);
				map.setVisible(false);
				
			}
		});
		btnBack2.setIcon(back);
		btnBack2.setBounds(25, 25, 129, 150);
		btnBack2.setOpaque(false);
		btnBack2.setContentAreaFilled(false);
		btnBack2.setBorderPainted(false);
		secondPnl.add(btnBack2);
		
		exitbtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home.setVisible(true);
				map.setVisible(false);
				firstPnl.setVisible(false);
				secondPnl.setVisible(false);
				thirdPnl.setVisible(false);
				floors.setVisible(false);
			}
		});
		exitbtn2.setIcon(exit);
		exitbtn2.setBounds(1698, 995, 212, 74);
		secondPnl.add(exitbtn2);
		
		fmaplbl2.setIcon(smap);
		fmaplbl2.setBounds(1267, 238, 460, 650);
		secondPnl.add(fmaplbl2);
		
		firstPnl.setBounds(0, 0, x, y);
		frame.getContentPane().add(firstPnl);	
		firstPnl.setBackground(new Color(210, 209, 167));
		firstPnl.setLayout(null);
		firstPnl.setVisible(false);
		
		lbl1stFlr.setFont(new Font("Segoe UI", Font.BOLD, 99));
		lbl1stFlr.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1stFlr.setBounds(745, 50, 430, 150);
		firstPnl.add(lbl1stFlr);
		
		fmaplbl.setIcon(fmap);
		fmaplbl.setBounds(1267, 238, 460, 650);
		firstPnl.add(fmaplbl);
		
		btnBack1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				floors.setVisible(true);
				secondPnl.setVisible(false);
				thirdPnl.setVisible(false);
				firstPnl.setVisible(false);
				home.setVisible(false);
				map.setVisible(false);
			}
		});
		btnBack1.setOpaque(false);
		btnBack1.setBorderPainted(false);
		btnBack1.setContentAreaFilled(false);
		btnBack1.setIcon(back);
		btnBack1.setBounds(25, 25, 129, 150);
		firstPnl.add(btnBack1);
		
		exitbtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home.setVisible(true);
				map.setVisible(false);
				firstPnl.setVisible(false);
				secondPnl.setVisible(false);
				thirdPnl.setVisible(false);
				floors.setVisible(false);
			}
		});
		exitbtn_1.setIcon(exit);
		exitbtn_1.setBounds(1698, 995, 212, 74);
		firstPnl.add(exitbtn_1);
		
		floors.setBounds(0, 0, x, y);
		frame.getContentPane().add(floors);
		floors.setLayout(null);
		firstBtn.setBorder(blueBorder);
		firstBtn.setContentAreaFilled(false);
		firstBtn.setBackground(new Color(255, 255, 255));
		firstBtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					choice = 1;
					secondBtn.setBorderPainted(false);
					thirdBtn.setBorderPainted(false);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					secondBtn.setBorderPainted(true);
					thirdBtn.setBorderPainted(true);
				}
			}
		});
		firstBtn.setBorderPainted(true);
		firstBtn.setOpaque(false);
		firstBtn.setIcon(new ImageIcon(Home.class.getResource("/resources/1st-floor-engraved-sign-se-5835_L2_001_wh.png")));
		firstBtn.setBounds(258, 372, 350, 551);
		floors.add(firstBtn);
		
		secondBtn.setBorder(blueBorder);
		secondBtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					choice = 2;
					firstBtn.setBorderPainted(false);
					thirdBtn.setBorderPainted(false);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					firstBtn.setBorderPainted(true);
					thirdBtn.setBorderPainted(true);
				}
			}
		});
		secondBtn.setBorderPainted(true);
		secondBtn.setIcon(new ImageIcon(Home.class.getResource("/resources/1st-floor-engraved-sign-se-5835_L2_002_wh.png")));
		secondBtn.setContentAreaFilled(false);
		secondBtn.setOpaque(false);
		secondBtn.setBounds(802, 372, 350, 551);
		floors.add(secondBtn);
		
		thirdBtn.setBorder(blueBorder);
		thirdBtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					choice = 3;
					firstBtn.setBorderPainted(false);
					secondBtn.setBorderPainted(false);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					firstBtn.setBorderPainted(true);
					secondBtn.setBorderPainted(true);
				}
			}
		});
		thirdBtn.setBorderPainted(true);
		thirdBtn.setIcon(new ImageIcon(Home.class.getResource("/resources/1st-floor-engraved-sign-se-5835_L2_003_wh.png")));
		thirdBtn.setOpaque(false);
		thirdBtn.setContentAreaFilled(false);
		thirdBtn.setBounds(1325, 372, 350, 551);
		floors.add(thirdBtn);
		
		btnNextFlr.setBorderPainted(false);
		btnNextFlr.setContentAreaFilled(false);
		btnNextFlr.setOpaque(false);
		btnNextFlr.setIcon(new ImageIcon(Home.class.getResource("/resources/next.png")));
		btnNextFlr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(choice) {
				case 1:
					floors.setVisible(false);
					map.setVisible(false);
					home.setVisible(false);
					firstPnl.setVisible(true);
					//System.out.println(choice);
					break;
				case 2:
					floors.setVisible(false);
					map.setVisible(false);
					home.setVisible(false);
					secondPnl.setVisible(true);
					//System.out.println(choice);
					break;
				case 3:
					floors.setVisible(false);
					map.setVisible(false);
					home.setVisible(false);
					thirdPnl.setVisible(true);
					//System.out.println(choice);
					break;
				}
			}
		});
		btnNextFlr.setBounds(1618, 944, 292, 125);
		floors.add(btnNextFlr);

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
		
		nextbtn.setOpaque(false);
		nextbtn.setContentAreaFilled(false);
		nextbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				map.setVisible(false);
				floors.setVisible(true);
			}});
		nextbtn.setBorderPainted(false);
		nextbtn.setIcon(new ImageIcon(Home.class.getResource("/resources/next.png")));
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
		
		home.setBounds(0, 0, x, y);
		
		frame.getContentPane().add(home);
		home.setLayout(null);
		
		exitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { frame.dispose(); }});
		
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home.setVisible(false);
				map.setVisible(true);
			}});
		loginbtn.setIcon(new ImageIcon(Home.class.getResource("/resources/awd.png")));
		loginbtn.setBounds(1238, 627, 212, 70);
		home.add(loginbtn);
		exitbtn.setIcon(exit);
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



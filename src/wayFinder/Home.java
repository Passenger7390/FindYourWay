package wayFinder;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Home {
	
	private ImageIcon rtuHome = new ImageIcon(Home.class.getResource("/resources/Login page.png")); // Icon
	private Connection con;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	//1st floor
	private ImageIcon itc103 = new ImageIcon(Home.class.getResource("/resources/1st/103.jpg"));
	private ImageIcon itc105 = new ImageIcon(Home.class.getResource("/resources/1st/105.jpg"));
	private ImageIcon cpeFaculty = new ImageIcon(Home.class.getResource("/resources/1st/CPE FaCULTY.jpg"));
	private ImageIcon map103 = new ImageIcon(Home.class.getResource("/resources/1st/Map 103.png"));
	private ImageIcon map105 = new ImageIcon(Home.class.getResource("/resources/1st/Map 105.png"));
	private ImageIcon mapFaculty = new ImageIcon(Home.class.getResource("/resources/1st/Map CPE Faculty.png"));
	
	// 2nd floor
	private ImageIcon itc213 = new ImageIcon(Home.class.getResource("/resources/2nd/213.jpg"));
	private ImageIcon map213 = new ImageIcon(Home.class.getResource("/resources/2nd/Map 213.png"));
	
	// 3rd floor
	private ImageIcon itc305 = new ImageIcon(Home.class.getResource("/resources/3rd/305.jpg"));
	private ImageIcon itc309A = new ImageIcon(Home.class.getResource("/resources/3rd/309-A.jpg"));
	private ImageIcon itc309B = new ImageIcon(Home.class.getResource("/resources/3rd/309-B.jpg"));
	private ImageIcon map305 = new ImageIcon(Home.class.getResource("/resources/3rd/Map 305.png"));
	private ImageIcon map309A = new ImageIcon(Home.class.getResource("/resources/3rd/Map 309A.png"));
	private ImageIcon map309B = new ImageIcon(Home.class.getResource("/resources/3rd/Map 309B.png"));
	
	private int x = 1920;
	private int y = 1080;
	private JFrame frame;
	private JTextField textField;
	
	private JButton btnNextFlr = new JButton("");
	private JButton loginbtn = new JButton("");
	private JButton btnBack1 = new JButton("");
	private JButton btnBack2 = new JButton("");
	private JButton btnBack3 = new JButton("");
	private JButton exitbtn_1 = new JButton("");
	private JButton exitbtn2 = new JButton("");
	private JButton exitbtn3 = new JButton("");
	private JButton nextbtn = new JButton("");
	
	//maps
	private JLabel fmaplbl = new JLabel("");
	private JLabel smaplbl = new JLabel("");
	private JLabel tmaplbl = new JLabel("");
	private ImageIcon fmap = new ImageIcon(Home.class.getResource("/resources/1st/1stmap.png"));
	private ImageIcon smap = new ImageIcon(Home.class.getResource("/resources/2nd/2ndmap.png"));
	private ImageIcon tmap = new ImageIcon(Home.class.getResource("/resources/3rd/3rdmap.png"));
	
	//floor titles
	private JLabel firstFlrbg = new JLabel("");
	
	private JPanel map = new JPanel();
	private JPanel floors = new JPanel();
	private JPanel home = new JPanel();
	private JPanel firstPnl = new JPanel();
	private JPanel secondPnl = new JPanel();
	private JPanel thirdPnl = new JPanel();
	
	private ButtonGroup g = new ButtonGroup();
	
	private final JButton itc103Btn = new JButton("");
	private final JButton itc105Btn = new JButton("");
	private final JButton cpeFacultyBtn = new JButton("");
	private final JLabel roompic = new JLabel("");
	private final JLabel secondFlrbg = new JLabel("");
	private final JButton itc213Btn = new JButton("");
	private final JLabel roompic2 = new JLabel("");
	private final JLabel thirdFlrbg = new JLabel("");
	private final JLabel roompic3 = new JLabel("");
	private final JButton itc305Btn = new JButton("");
	private final JButton itc309BBtn = new JButton("");
	private final JButton itc309ABtn = new JButton("");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

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
		
		
	}
	
	// Custom Codes ===================================================================================================================
	private void connect() {
		
		String link = "jdbc:mysql://localhost/test";
		String user = "root";
		String pass = "adminroot";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(link,user,pass);
			initialize();
			
		} catch(ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Connection Problem!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Connection Problem!");
			//e.printStackTrace();
		}
	}
	
	private ImageIcon scaleImg(ImageIcon imgico, int x, int y) {
		Image temp;
		temp = imgico.getImage();
		temp = temp.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		imgico = new ImageIcon(temp);
		return imgico;
	}
	
	// End of Custom codes ===================================================================================================================

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
				rtuHome = scaleImg(rtuHome, x, y);				
				fmap = scaleImg(fmap, 601, 850);
				smap = scaleImg(smap, 601, 850);
				tmap = scaleImg(tmap, 601, 850);
		
		firstPnl.setBounds(0, 0, x, y);
		frame.getContentPane().add(firstPnl);	
		firstPnl.setBackground(new Color(210, 209, 167));
		firstPnl.setLayout(null);
		firstPnl.setVisible(false);
		roompic.setBounds(106, 347, 546, 499);
		
		firstPnl.add(roompic);
		cpeFacultyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roompic.setIcon(scaleImg(cpeFaculty, roompic.getWidth(), roompic.getHeight()));
				fmaplbl.setIcon(scaleImg(mapFaculty, fmaplbl.getWidth(), fmaplbl.getHeight()));
			}
		});
		cpeFacultyBtn.setOpaque(false);
		cpeFacultyBtn.setContentAreaFilled(false);
		cpeFacultyBtn.setBorderPainted(false);
		cpeFacultyBtn.setBounds(766, 780, 436, 135);
		
		firstPnl.add(cpeFacultyBtn);
		itc105Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roompic.setIcon(scaleImg(itc105, roompic.getWidth(), roompic.getHeight()));
				fmaplbl.setIcon(scaleImg(map105, fmaplbl.getWidth(), fmaplbl.getHeight()));
			}
		});
		itc105Btn.setOpaque(false);
		itc105Btn.setContentAreaFilled(false);
		itc105Btn.setBorderPainted(false);
		itc105Btn.setBounds(766, 546, 436, 135);
		
		firstPnl.add(itc105Btn);
		itc103Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roompic.setIcon(scaleImg(itc103, roompic.getWidth(), roompic.getHeight()));
				fmaplbl.setIcon(scaleImg(map103, fmaplbl.getWidth(), fmaplbl.getHeight()));
			}
		});
		itc103Btn.setOpaque(false);
		itc103Btn.setContentAreaFilled(false);
		itc103Btn.setBorderPainted(false);
		itc103Btn.setBounds(766, 312, 436, 135);
		firstPnl.add(itc103Btn);
		
		fmaplbl.setIcon(fmap);
		fmaplbl.setBounds(1270, 190, 601, 850);
		firstPnl.add(fmaplbl);
		btnBack1.setIcon(null);
		
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
		btnBack1.setBounds(0, 0, 130, 129);
		firstPnl.add(btnBack1);
		exitbtn_1.setContentAreaFilled(false);
		exitbtn_1.setBorderPainted(false);
		exitbtn_1.setOpaque(false);
		
		exitbtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home.setVisible(true);
				map.setVisible(false);
				firstPnl.setVisible(false);
				secondPnl.setVisible(false);
				thirdPnl.setVisible(false);
				floors.setVisible(false);
				textField.setText(null);
			}
		});
		exitbtn_1.setIcon(null);
		exitbtn_1.setBounds(0, 956, 130, 124);
		firstPnl.add(exitbtn_1);
		firstFlrbg.setIcon(new ImageIcon(Home.class.getResource("/resources/1st Floor.png")));
		
		firstFlrbg.setBounds(0, 0, x, y);
		firstPnl.add(firstFlrbg);
		
		secondPnl.setBounds(0, 0, x, y);
		frame.getContentPane().add(secondPnl);
		secondPnl.setVisible(false);
		secondPnl.setBackground(new Color(210, 209, 167));
		secondPnl.setLayout(null);
		
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
		roompic2.setBounds(106, 347, 546, 499);
		
		secondPnl.add(roompic2);
		itc213Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roompic2.setIcon(scaleImg(itc213, roompic2.getWidth(), roompic2.getHeight()));
				smaplbl.setIcon(scaleImg(map213, smaplbl.getWidth(), smaplbl.getHeight()));
			}
		});
		itc213Btn.setOpaque(false);
		itc213Btn.setBorderPainted(false);
		itc213Btn.setContentAreaFilled(false);
		itc213Btn.setBounds(766, 547, 436, 129);
		
		secondPnl.add(itc213Btn);
		btnBack2.setIcon(null);
		btnBack2.setBounds(0, 0, 124, 129);
		btnBack2.setOpaque(false);
		btnBack2.setContentAreaFilled(false);
		btnBack2.setBorderPainted(false);
		secondPnl.add(btnBack2);
		exitbtn2.setOpaque(false);
		exitbtn2.setContentAreaFilled(false);
		exitbtn2.setBorderPainted(false);
		
		exitbtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home.setVisible(true);
				map.setVisible(false);
				firstPnl.setVisible(false);
				secondPnl.setVisible(false);
				thirdPnl.setVisible(false);
				floors.setVisible(false);
				textField.setText(null);
			}
		});
		exitbtn2.setBounds(0, 957, 124, 123);
		secondPnl.add(exitbtn2);
		
		smaplbl.setIcon(smap);
		smaplbl.setBounds(1270, 190, 601, 850);
		secondPnl.add(smaplbl);
		secondFlrbg.setIcon(new ImageIcon(Home.class.getResource("/resources/2nd Floor.png")));
		secondFlrbg.setBounds(0, 0, x, y);
		
		secondPnl.add(secondFlrbg);
		
		thirdPnl.setBounds(0, 0, x, y);
		frame.getContentPane().add(thirdPnl);
		thirdPnl.setBackground(new Color(210, 209, 167));
		thirdPnl.setVisible(false);
		thirdPnl.setLayout(null);
		
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
		itc309ABtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roompic3.setIcon(scaleImg(itc309A, roompic3.getWidth(), roompic3.getHeight()));
				tmaplbl.setIcon(scaleImg(map309A, tmaplbl.getWidth(), tmaplbl.getHeight()));
			}
		});
		itc309ABtn.setOpaque(false);
		itc309ABtn.setContentAreaFilled(false);
		itc309ABtn.setBorderPainted(false);
		itc309ABtn.setBounds(766, 547, 435, 133);
		
		thirdPnl.add(itc309ABtn);
		itc309BBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roompic3.setIcon(scaleImg(itc309B, roompic3.getWidth(), roompic3.getHeight()));
				tmaplbl.setIcon(scaleImg(map309B, tmaplbl.getWidth(), tmaplbl.getHeight()));
			}
		});
		itc309BBtn.setOpaque(false);
		itc309BBtn.setContentAreaFilled(false);
		itc309BBtn.setBorderPainted(false);
		itc309BBtn.setBounds(766, 781, 435, 133);
		
		thirdPnl.add(itc309BBtn);
		itc305Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roompic3.setIcon(scaleImg(itc305, roompic3.getWidth(), roompic3.getHeight()));
				tmaplbl.setIcon(scaleImg(map305, tmaplbl.getWidth(), tmaplbl.getHeight()));
			}
		});
		itc305Btn.setOpaque(false);
		itc305Btn.setContentAreaFilled(false);
		itc305Btn.setBorderPainted(false);
		itc305Btn.setBounds(766, 313, 435, 133);
		
		thirdPnl.add(itc305Btn);
		roompic3.setBounds(106, 347, 546, 499);
		
		thirdPnl.add(roompic3);
		btnBack3.setBounds(0, 0, 125, 127);
		btnBack3.setOpaque(false);
		btnBack3.setContentAreaFilled(false);
		btnBack3.setBorderPainted(false);
		btnBack3.setIcon(null);
		thirdPnl.add(btnBack3);
		exitbtn3.setContentAreaFilled(false);
		exitbtn3.setBorderPainted(false);
		exitbtn3.setOpaque(false);
		
		exitbtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home.setVisible(true);
				map.setVisible(false);
				firstPnl.setVisible(false);
				secondPnl.setVisible(false);
				thirdPnl.setVisible(false);
				floors.setVisible(false);
				textField.setText(null);
			}
		});
		exitbtn3.setIcon(null);
		exitbtn3.setBounds(0, 953, 125, 127);
		thirdPnl.add(exitbtn3);
		
		tmaplbl.setIcon(tmap);
		tmaplbl.setBounds(1270, 190, 601, 850);
		thirdPnl.add(tmaplbl);
		thirdFlrbg.setIcon(new ImageIcon(Home.class.getResource("/resources/3rd Floor.png")));
		thirdFlrbg.setBounds(0, 0, x, y);
		
		thirdPnl.add(thirdFlrbg);
		
		floors.setBounds(0, 0, x, y);
		frame.getContentPane().add(floors);
		floors.setLayout(null);
		
		btnNextFlr.setBorderPainted(false);
		btnNextFlr.setContentAreaFilled(false);
		btnNextFlr.setOpaque(false);
		btnNextFlr.setIcon(null);
		btnNextFlr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home.setVisible(true);
				map.setVisible(false);
				floors.setVisible(false);
				firstPnl.setVisible(false);
				secondPnl.setVisible(false);
				thirdPnl.setVisible(false);
				textField.setText(null);
			}
		});
		btnNextFlr.setBounds(1796, 957, 124, 123);
		floors.add(btnNextFlr);
		
		JButton firstBtn = new JButton("");
		firstBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstPnl.setVisible(true);
				floors.setVisible(false);
				roompic.setIcon(null);
			}
		});
		firstBtn.setBorderPainted(false);
		firstBtn.setContentAreaFilled(false);
		firstBtn.setOpaque(false);
		firstBtn.setBounds(139, 266, 436, 132);
		floors.add(firstBtn);
		
		JButton secondBtn = new JButton("");
		secondBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secondPnl.setVisible(true);
				floors.setVisible(false);
				roompic2.setIcon(null);
			}
		});
		secondBtn.setBorderPainted(false);
		secondBtn.setContentAreaFilled(false);
		secondBtn.setOpaque(false);
		secondBtn.setBounds(140, 509, 435, 132);
		floors.add(secondBtn);
		
		JButton thirdBtn = new JButton("");
		thirdBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thirdPnl.setVisible(true);
				floors.setVisible(false);
				roompic3.setIcon(null);
			}
		});
		thirdBtn.setOpaque(false);
		thirdBtn.setContentAreaFilled(false);
		thirdBtn.setBorderPainted(false);
		thirdBtn.setBounds(140, 756, 436, 132);
		floors.add(thirdBtn);
		
		JLabel rtuB1lbl = new JLabel("");
		rtuB1lbl.setBounds(0, 0, x, y);
		rtuB1lbl.setIcon(new ImageIcon(Home.class.getResource("/resources/Floor Selection.png")));
		floors.add(rtuB1lbl);
		
		floors.setVisible(false);
		g.add(firstBtn);
		g.add(secondBtn);
		g.add(thirdBtn);
		
		map.setBackground(new Color(210, 209, 167));
		map.setBounds(0, 0, x, y);
		frame.getContentPane().add(map);
		map.setLayout(null);
		
		nextbtn.setOpaque(false);
		nextbtn.setContentAreaFilled(false);
		nextbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				map.setVisible(false);
				floors.setVisible(true);
			}});
		nextbtn.setBorderPainted(false);
		nextbtn.setIcon(null);
		nextbtn.setBounds(1761, 918, 159, 162);
		map.add(nextbtn);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Home.class.getResource("/resources/Campus Map.png")));
		lblNewLabel.setBounds(0, 0, x, y);
		map.add(lblNewLabel);
		map.setVisible(false);
		
		home.setBounds(0, 0, x, y);
		
		frame.getContentPane().add(home);
		home.setLayout(null);
		loginbtn.setOpaque(false);
		loginbtn.setBorderPainted(false);
		loginbtn.setContentAreaFilled(false);
		
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = "";	
				try	{
					String query = "SELECT student_num FROM students WHERE student_num = ?";
					
					stmt = con.prepareStatement(query);
					stmt.setString(1, textField.getText());
					rs = stmt.executeQuery();
					while (rs.next()) {
						id = rs.getString("student_num");
					}
				} catch(Exception c) {
					JOptionPane.showMessageDialog(null, c);
					textField.setText("");
				}
				
				boolean isTrue = id.equals(textField.getText());
				
				if (textField.getText().length() == 0) { // textfield should not be empty
					JOptionPane.showMessageDialog(null, "Please enter a student number!", "Login Failed!", JOptionPane.ERROR_MESSAGE);
				}
				else if (isTrue == true) { // if the student number matches in the db
					home.setVisible(false);
					map.setVisible(isTrue);
				} 
				else { 
					textField.setText("");
					JOptionPane.showMessageDialog(null, "Student number does not match in the records!", "Login Failed!", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}});
		loginbtn.setIcon(null);
		loginbtn.setBounds(233, 674, 593, 122);
		home.add(loginbtn);
		
		textField = new JTextField();
		textField.setBorder(null);
		textField.setOpaque(false);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 40));
		textField.setBounds(233, 423, 593, 115);
		home.add(textField);
		textField.setColumns(10);
		
		JLabel homebg = new JLabel("");
		homebg.setBounds(0, 0, x, y);
		homebg.setIcon(rtuHome);
		home.add(homebg);
		
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/resources/RTU_Seal.png")));
		frame.getContentPane().setLayout(null);
	}
}

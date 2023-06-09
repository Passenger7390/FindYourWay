package wayFinder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;



class Button extends JButton {
	
	Button() {
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
	}
}

class Button1 extends JButton { // Rounded Button
	private Color backgroundColor;
    private int arcWidth;
    private int arcHeight;
    
    Button1(Color backgroundColor, int arcWidth, int arcHeight) {
        this.backgroundColor = backgroundColor;
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        setContentAreaFilled(false);
        setFocusPainted(false);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
        
        g2.setColor(getForeground());
        g2.setFont(getFont());
        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(getText(), x, y);
        
        g2.dispose();
    }
    
    @Override
    protected void paintBorder(Graphics g) {
        // Do not paint the button border
    }
    
    public void changeColor(Color color) {
    	this.backgroundColor = color;
    	repaint();
    }
    
}

public class Home {
	
	ImageIcon rtuHome = new ImageIcon(Home.class.getResource("/resources/Login page.png")); // Icon
	ImageIcon mapChoice = new ImageIcon(Home.class.getResource("/resources/UI UPDATED/LOG IN AND CAMPUS MAP/2.png"));
	private Connection con;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static double a = screenSize.getWidth();
	private static double b = screenSize.getHeight();
	static int x = new Double(a).intValue();
	static int y = new Double(b).intValue();
	
	private JFrame frame;
	private JTextField studnum;
	private Button loginbtn = new Button();
	
	private JPanel map = new JPanel();
	private static JPanel floors = new JPanel();
	private JPanel home = new JPanel();
	
	//private ButtonGroup g = new ButtonGroup();
	//private int f = 0;
	
	// Buildings Button
	private Button b31 = new Button();
	private Button b3 = new Button();
	private Button b10 = new Button();
	Button b4 = new Button();
	Button b41 = new Button();
	Button b1 = new Button();
	private final Button b7 = new Button();
	private final Button b6 = new Button();
	private final Button b11 = new Button();
	
	private final static JLabel floornumberlbl = new JLabel("1st Floor");
	private final static JLabel floorBG = new JLabel("");
	
	
	private final static JLabel floorplanlbl = new JLabel("");
	private final Button backBtn = new Button();
	private final Button homeBtn = new Button();
	private final Button1 roomsBtn = new Button1(new Color(0, 191, 99, 0), 90, 90);
	private final Button1 officesBtn = new Button1(new Color(0, 191, 99, 0), 90, 90);
	
	static int toggle = 0;
	
	
	
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
		
		String link = "jdbc:mysql://localhost/test"; // Replace with database link
		String user = "root"; // Username of database
		String pass = "adminroot"; // Password of database
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(link,user,pass);
			initialize();
		} catch(ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Connection Problem!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Connection Problem!");
			e.printStackTrace();
		}
	}
	
	static ImageIcon scaleImg(ImageIcon imgico, int x, int y) {
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
		frame.setSize(screenSize);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		
		// Scaling Image
				rtuHome = scaleImg(rtuHome, x, y);	
				mapChoice = scaleImg(mapChoice, x, y);
		
		floors.setBounds(0, 0, x, y);
		frame.getContentPane().add(floors);
		floors.setLayout(null);
		floornumberlbl.setHorizontalAlignment(SwingConstants.CENTER);
		floornumberlbl.setFont(new Font("Tahoma", Font.PLAIN, 50));
		floornumberlbl.setBounds(989, 46, 404, 74);
		
		floors.add(floornumberlbl);
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggle = 0;
				if (Profeta.i == 1) {
					Profeta.clean();
				}
				
				if (Ob.i == 1) {
					Ob.clean();
				}
				
				if (Wellness.i == 1) {
					Wellness.clean();
				}
				
				if (Estolas.i == 1) {
					Estolas.clean();
				}
				
				if (Rd.i == 1) {
					Rd.clean();
				}
				
				if (MBldg.i == 1) {
					MBldg.clean();
				}
				
				if (Snagah.i == 1) {
					Snagah.clean();
				}
				
				floors.revalidate();
				floors.repaint();
				
				roomsBtn.changeColor(new Color(0, 191,99, 0));
				officesBtn.changeColor(new Color(0, 191,99, 0));
				
				floors.setVisible(false);
				map.setVisible(true);
			}
		});
		backBtn.setBounds(1793, 0, 127, 129);
		
		Profeta profeta = new Profeta();
		Ob old = new Ob();
		Wellness wellness = new Wellness();
		Estolas estolas = new Estolas();
		Rd rd = new Rd();
		Snagah snagah = new Snagah();
		MBldg mbldg = new MBldg();
		
		
		floors.add(backBtn);
		roomsBtn.setBorderPainted(true);
		roomsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggle = 1;
				roomsBtn.changeColor(new Color(0, 191,99, 125));
				officesBtn.changeColor(new Color(0, 191,99, 0));
			}
		});
		roomsBtn.setBounds(108, 153, 340, 102);
		floors.add(roomsBtn);
		officesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggle = 2;
				officesBtn.changeColor(new Color(0, 191,99, 125));
				roomsBtn.changeColor(new Color(0, 191,99, 0));
			}
		});
		officesBtn.setBounds(108, 301, 341, 106);		
		floors.add(officesBtn);		
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggle = 0;
				if (Profeta.i == 1) {
					Profeta.clean();
				}
				
				if (Ob.i == 1) {
					Ob.clean();
				}
				
				if (Wellness.i == 1) {
					Wellness.clean();
				}
				
				if (Estolas.i == 1) {
					Estolas.clean();
				}
				
				if (Rd.i == 1) {
					Rd.clean();
				}
				
				if (MBldg.i == 1) {
					MBldg.clean();
				}
				
				if (Snagah.i == 1) {
					Snagah.clean();
				}
				
				floors.revalidate();
				floors.repaint();
				
				roomsBtn.changeColor(new Color(0, 191,99, 0));
				officesBtn.changeColor(new Color(0, 191,99, 0));
				
				floors.setVisible(false);
				map.setVisible(false);
				studnum.setText(null);
				home.setVisible(true);
			}
		});
		homeBtn.setBounds(0, 956, 131, 124);				
		floors.add(homeBtn);				
		floorplanlbl.setBounds(623, 197, 1133, 685);
		floors.add(floorplanlbl);				
		floorBG.setBounds(0, 0, x, y);
		
		floors.add(floorBG);
		floors.setVisible(false);
		
		map.setBackground(new Color(210, 209, 167));
		map.setBounds(0, 0, x, y);
		frame.getContentPane().add(map);
		map.setLayout(null);
		b3.addActionListener(new ActionListener() {				// Main Building
			public void actionPerformed(ActionEvent e) {
				map.setVisible(false);
				floors.setVisible(true);
				floorplanlbl.setIcon(null);
				mbldg.init();
			}
		});
		b7.addActionListener(new ActionListener() {				// SNAGAH
			public void actionPerformed(ActionEvent e) {				
				map.setVisible(false);
				floors.setVisible(true);
				floorplanlbl.setIcon(null);
				snagah.init();
			}
		});
		b7.setBounds(1038, 151, 141, 113);
		
		map.add(b7);
		b6.addActionListener(new ActionListener() {				// R&D
			public void actionPerformed(ActionEvent e) {				
				map.setVisible(false);
				floors.setVisible(true);
				floorplanlbl.setIcon(null);
				rd.init();
			}
		});

		b11.addActionListener(new ActionListener() {			// PROFETA
			public void actionPerformed(ActionEvent e) {
				map.setVisible(false);
				floors.setVisible(true);
				floorplanlbl.setIcon(null);
				profeta.init();
			}
		});
		b11.setBounds(400, 276, 400, 170);
		map.add(b11);
		b6.setBounds(888, 190, 141, 140);
		map.add(b6);
		b3.setBounds(473, 748, 406, 70);
		map.add(b3);
		b10.addActionListener(new ActionListener() {			// Estolas
			public void actionPerformed(ActionEvent e) {
				map.setVisible(false);
				floors.setVisible(true);
				floorplanlbl.setIcon(null);
				estolas.init();
			}
		});
		
		b10.setBounds(212, 257, 167, 258);
		map.add(b10);
		b4.addActionListener(new ActionListener() {				// Old Building
			public void actionPerformed(ActionEvent e) {
				map.setVisible(false);
				floors.setVisible(true);
				floorplanlbl.setIcon(null);
				old.init();
			}
		});
		b4.setBounds(210, 629, 669, 78);
		map.add(b4);
		b41.addActionListener(new ActionListener() {				// Old Building
			public void actionPerformed(ActionEvent e) {
				map.setVisible(false);
				floors.setVisible(true);
				floorplanlbl.setIcon(null);
				old.init();
			}
		});
		b41.setBounds(812, 276, 66, 463);
		map.add(b41);
		
		b1.addActionListener(new ActionListener() {				// Wellness
			public void actionPerformed(ActionEvent e) {
				map.setVisible(false);
				floors.setVisible(true);
				floorplanlbl.setIcon(null);
				wellness.init();
			}
		});
		b1.setBounds(440, 847, 314, 161);
		map.add(b1);
		b31.addActionListener(new ActionListener() {			// Main Building
			public void actionPerformed(ActionEvent e) {
				map.setVisible(false);
				floors.setVisible(true);
				floorplanlbl.setIcon(null);
				mbldg.init();
			}
		});
		b31.setBounds(812, 817, 67, 191);
		
		map.add(b31);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(mapChoice);
		lblNewLabel.setBounds(0, 0, x, y);
		
		map.add(lblNewLabel);
		map.setVisible(false);
		
		home.setBounds(0, 0, x, y);
		
		frame.getContentPane().add(home);
		home.setLayout(null);
		
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = "";	
				
//				boolean isTrue1 = studnum.getText().equals("demo"); // ===================================
//				if (isTrue1 == true) {		// ================ REMOVE THESE AFTER THE DEMO ================
//					home.setVisible(false); // ================ REMOVE THESE AFTER THE DEMO ================
//					map.setVisible(isTrue1); // ============================================================
//				}
				
				try	{
					String query = "SELECT student_num FROM students WHERE student_num = ?";
					
					stmt = con.prepareStatement(query);
					stmt.setString(1, studnum.getText());
					rs = stmt.executeQuery();
					while (rs.next()) {
						id = rs.getString("student_num");
					}
				} catch(Exception c) {
					JOptionPane.showMessageDialog(null, c);
					studnum.setText("");
				}
				
				boolean isTrue = id.equals(studnum.getText());
				
				
				if (studnum.getText().length() == 0) { // textfield should not be empty
					JOptionPane.showMessageDialog(null, "Please enter a student number!", "Login Failed!", JOptionPane.ERROR_MESSAGE);
				}
				else if (isTrue == true) { // if the student number matches in the db
					home.setVisible(false);
					map.setVisible(isTrue);
				} 
				else { 
					studnum.setText("");
					JOptionPane.showMessageDialog(null, "Student number does not match in the records!", "Login Failed!", JOptionPane.ERROR_MESSAGE);
				}
			}});
		loginbtn.setIcon(null);
		loginbtn.setBounds(233, 674, 593, 122);
		home.add(loginbtn);
		
		studnum = new JTextField();
		studnum.setBorder(null);
		studnum.setOpaque(false);
		studnum.setFont(new Font("Tahoma", Font.PLAIN, 40));
		studnum.setBounds(233, 423, 593, 115);
		home.add(studnum);
		studnum.setColumns(10);
		
		JLabel homebg = new JLabel("");
		homebg.setBounds(0, 0, x, y);
		homebg.setIcon(rtuHome);
		home.add(homebg);
		
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/resources/RTU_Seal.png")));
		frame.getContentPane().setLayout(null);
	}
	
	static class Ob implements ActionListener {
		private static int f = 3;
		private ImageIcon icon = new ImageIcon(Home.class.getResource("/resources/UI UPDATED/ROOM OR OFFICE SELECTION/old building display.png"));
		private static Button[] floor = new Button[f];
		public static int i = 0;
		
		// Floors
		ImageIcon floor1 = new ImageIcon(Home.class.getResource("/resources/OLD BUILDING/FLOORS/1st.png"));
		ImageIcon floor2 = new ImageIcon(Home.class.getResource("/resources/OLD BUILDING/FLOORS/2nd.png"));
		ImageIcon floor3 = new ImageIcon(Home.class.getResource("/resources/OLD BUILDING/FLOORS/3rd.png"));
		
		// Offices
		ImageIcon office1 = new ImageIcon(Home.class.getResource("/resources/OLD BUILDING/OFFICE HIGHLIGHTS/1st.png"));
		ImageIcon office2 = new ImageIcon(Home.class.getResource("/resources/OLD BUILDING/OFFICE HIGHLIGHTS/2nd.png"));
		ImageIcon office3 = new ImageIcon(Home.class.getResource("/resources/OLD BUILDING/OFFICE HIGHLIGHTS/3rd.png"));
		
		// Rooms
		ImageIcon room1 = new ImageIcon(Home.class.getResource("/resources/OLD BUILDING/ROOM HIGHLIGHTS/1st.png"));
		ImageIcon room2 = new ImageIcon(Home.class.getResource("/resources/OLD BUILDING/ROOM HIGHLIGHTS/2nd.png"));
		ImageIcon room3 = new ImageIcon(Home.class.getResource("/resources/OLD BUILDING/ROOM HIGHLIGHTS/3rd.png"));
		
		public void init() {
			floorBG.setIcon(scaleImg(icon, x, y));
			for (int i = 0; i < f; i++) {
				floor[i] = new Button();
				floor[i].addActionListener(this);
				floors.add(floor[i]);
			}
			i = 1;
			
			floor[0].setBounds(149, 563, 250, 74);
			floor[1].setBounds(150, 661, 250, 74);
			floor[2].setBounds(150, 761, 250, 74);
		}
		
		public static void clean() {
			for (int i = 0; i < f; i++) {
				floors.remove(floor[i]);
			}
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// Floors
			if (e.getSource() == floor[0]) {
				floornumberlbl.setText("1st Floor");
				floorplanlbl.setIcon(scaleImg(floor1, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if (e.getSource() == floor[1]) {
				floornumberlbl.setText("2nd Floor");
				floorplanlbl.setIcon(scaleImg(floor2, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if (e.getSource() == floor[2]) {
				floornumberlbl.setText("3rd Floor");
				floorplanlbl.setIcon(scaleImg(floor3, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			// Offices
			if ((e.getSource() == floor[0]) && (toggle == 2)) {
				floornumberlbl.setText("1st Floor");
				floorplanlbl.setIcon(scaleImg(office1, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
			}
			
			if ((e.getSource() == floor[1]) && (toggle == 2)) {
				floornumberlbl.setText("2nd Floor");
				floorplanlbl.setIcon(scaleImg(office2, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
			}
			
			if ((e.getSource() == floor[2]) && (toggle == 2)) {
				floornumberlbl.setText("3rd Floor");
				floorplanlbl.setIcon(scaleImg(office3, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			// Rooms
			if ((e.getSource() == floor[0]) && (toggle == 1)) {
				floornumberlbl.setText("1st Floor");
				floorplanlbl.setIcon(scaleImg(room1, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
			}
			
			if ((e.getSource() == floor[1]) && (toggle == 1)) {
				floornumberlbl.setText("2nd Floor");
				floorplanlbl.setIcon(scaleImg(room2, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
			}
			
			if ((e.getSource() == floor[2]) && (toggle == 1)) {
				floornumberlbl.setText("3rd Floor");
				floorplanlbl.setIcon(scaleImg(room3, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			
			
		}
	}
	
	static class MBldg implements ActionListener {
		private static int f = 4;
		ImageIcon icon = new ImageIcon(Home.class.getResource("/resources/UI UPDATED/ROOM OR OFFICE SELECTION/main building display.png"));
		private static Button[] floor = new Button[f];
		public static int i = 0;
		
		// Floors
		ImageIcon floor1 = new ImageIcon(Home.class.getResource("/resources/MAIN BUILDING/FLOORS/MAIN BUILDING 1.png"));
		ImageIcon floor2 = new ImageIcon(Home.class.getResource("/resources/MAIN BUILDING/FLOORS/MAIN BUILDING 2.png"));
		ImageIcon floor3 = new ImageIcon(Home.class.getResource("/resources/MAIN BUILDING/FLOORS/MAIN BUILDING 3.png"));
		ImageIcon floor4 = new ImageIcon(Home.class.getResource("/resources/MAIN BUILDING/FLOORS/MAIN BUILDING 4.png"));
				
		// Offices
		ImageIcon office1 = new ImageIcon(Home.class.getResource("/resources/MAIN BUILDING/OFFICE HIGHLIGHT/1st.png"));
				
		// Rooms
		ImageIcon room2 = new ImageIcon(Home.class.getResource("/resources/MAIN BUILDING/ROOM HIGHLIGHT/2nd.png"));
		ImageIcon room3 = new ImageIcon(Home.class.getResource("/resources/MAIN BUILDING/ROOM HIGHLIGHT/3rd.png"));
		ImageIcon room4 = new ImageIcon(Home.class.getResource("/resources/MAIN BUILDING/ROOM HIGHLIGHT/4th.png"));
		
		
		public void init() {
			floorBG.setIcon(scaleImg(icon, x, y));
			for (int i = 0; i < f; i++) {
				floor[i] = new Button();
				floor[i].addActionListener(this);
				floors.add(floor[i]);
			}
			
			i = 1;
			floor[0].setBounds(155, 562, 226, 67);
			floor[1].setBounds(156, 652, 226, 67);
			floor[2].setBounds(155, 743, 226, 67);
			floor[3].setBounds(156, 835, 226, 67);
		}
		
		public static void clean() {
			for (int i = 0; i < f; i++) {
				floors.remove(floor[i]);
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// Floors
			if (e.getSource() == floor[0]) {
				floornumberlbl.setText("1st Floor");
				floorplanlbl.setIcon(scaleImg(floor1, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if (e.getSource() == floor[1]) {
				floornumberlbl.setText("2nd Floor");
				floorplanlbl.setIcon(scaleImg(floor2, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if (e.getSource() == floor[2]) {
				floornumberlbl.setText("3rd Floor");
				floorplanlbl.setIcon(scaleImg(floor3, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if (e.getSource() == floor[3]) {
				floornumberlbl.setText("4th Floor");
				floorplanlbl.setIcon(scaleImg(floor4, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			// Offices
			if ((e.getSource() == floor[0]) && (toggle == 2)) {
				floornumberlbl.setText("1st Floor");
				floorplanlbl.setIcon(scaleImg(office1, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
			}
			
			// Rooms
			if ((e.getSource() == floor[1]) && (toggle == 1)) {
				floornumberlbl.setText("2nd Floor");
				floorplanlbl.setIcon(scaleImg(room2, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
			}
			
			if ((e.getSource() == floor[2]) && (toggle == 1)) {
				floornumberlbl.setText("3rd Floor");
				floorplanlbl.setIcon(scaleImg(room3, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if ((e.getSource() == floor[3]) && (toggle == 1)) {
				floornumberlbl.setText("4th Floor");
				floorplanlbl.setIcon(scaleImg(room4, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}	
		}
	}
	
	static class Estolas implements ActionListener {
		private static int f = 4;
		private ImageIcon icon = new ImageIcon(Home.class.getResource("/resources/UI UPDATED/ROOM OR OFFICE SELECTION/estolas display.png"));
		private static Button[] floor = new Button[f];
		public static int i = 0;
		
		// Floors
		ImageIcon floor1 = new ImageIcon(Home.class.getResource("/resources/ESTOLAS BUILDING/FLOORS/1ST FLOOR ESTOLAS.png"));
		ImageIcon floor2 = new ImageIcon(Home.class.getResource("/resources/ESTOLAS BUILDING/FLOORS/2ND FLOOR ESTOLAS.png"));
		ImageIcon floor3 = new ImageIcon(Home.class.getResource("/resources/ESTOLAS BUILDING/FLOORS/3RD FLOOR ESTOLAS.png"));
		ImageIcon floor4 = new ImageIcon(Home.class.getResource("/resources/ESTOLAS BUILDING/FLOORS/4TH FLOOR ESTOLAS.png"));
		
		// Offices
		ImageIcon office1 = new ImageIcon(Home.class.getResource("/resources/ESTOLAS BUILDING/OFFICE HIGHLIGHT/1st.png"));
		ImageIcon office2 = new ImageIcon(Home.class.getResource("/resources/ESTOLAS BUILDING/OFFICE HIGHLIGHT/2nd.png"));
		ImageIcon office3 = new ImageIcon(Home.class.getResource("/resources/ESTOLAS BUILDING/OFFICE HIGHLIGHT/3rd.png"));
		ImageIcon office4 = new ImageIcon(Home.class.getResource("/resources/ESTOLAS BUILDING/OFFICE HIGHLIGHT/4th.png"));
		
		// Rooms
		ImageIcon room1 = new ImageIcon(Home.class.getResource("/resources/ESTOLAS BUILDING/ROOM HIGHLIGHT/1st.png"));
		ImageIcon room2 = new ImageIcon(Home.class.getResource("/resources/ESTOLAS BUILDING/ROOM HIGHLIGHT/2nd.png"));
		ImageIcon room3 = new ImageIcon(Home.class.getResource("/resources/ESTOLAS BUILDING/ROOM HIGHLIGHT/3rd.png"));
		ImageIcon room4 = new ImageIcon(Home.class.getResource("/resources/ESTOLAS BUILDING/ROOM HIGHLIGHT/4th.png"));
		
		public void init() {
			floorBG.setIcon(scaleImg(icon, x, y));
			for (int i = 0; i < f; i++) {
				floor[i] = new Button();
				floor[i].addActionListener(this);
				floors.add(floor[i]);
			}
			i = 1;
			floor[0].setBounds(155, 562, 226, 67);
			floor[1].setBounds(156, 652, 226, 67);
			floor[2].setBounds(155, 743, 226, 67);
			floor[3].setBounds(156, 835, 226, 67);
		}
		
		public static void clean() {
			for (int i = 0; i < f; i++) {
				floors.remove(floor[i]);
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == floor[0]) {
				floornumberlbl.setText("1st Floor");
				floorplanlbl.setIcon(scaleImg(floor1, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if (e.getSource() == floor[1]) {
				floornumberlbl.setText("2nd Floor");
				floorplanlbl.setIcon(scaleImg(floor2, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if (e.getSource() == floor[2]) {
				floornumberlbl.setText("3rd Floor");
				floorplanlbl.setIcon(scaleImg(floor3, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if (e.getSource() == floor[3]) {
				floornumberlbl.setText("4th Floor");
				floorplanlbl.setIcon(scaleImg(floor4, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			// Offices
			if ((e.getSource() == floor[0]) && (toggle == 2)) {
				floornumberlbl.setText("1st Floor");
				floorplanlbl.setIcon(scaleImg(office1, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
			}
			
			if ((e.getSource() == floor[1]) && (toggle == 2)) {
				floornumberlbl.setText("2nd Floor");
				floorplanlbl.setIcon(scaleImg(office2, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
			}
			
			if ((e.getSource() == floor[2]) && (toggle == 2)) {
				floornumberlbl.setText("3rd Floor");
				floorplanlbl.setIcon(scaleImg(office3, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if ((e.getSource() == floor[3]) && (toggle == 2)) {
				floornumberlbl.setText("4th Floor");
				floorplanlbl.setIcon(scaleImg(office4, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			// Rooms
			if ((e.getSource() == floor[0]) && (toggle == 1)) {
				floornumberlbl.setText("1st Floor");
				floorplanlbl.setIcon(scaleImg(room1, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
			}
			
			if ((e.getSource() == floor[1]) && (toggle == 1)) {
				floornumberlbl.setText("2nd Floor");
				floorplanlbl.setIcon(scaleImg(room2, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
			}
			
			if ((e.getSource() == floor[2]) && (toggle == 1)) {
				floornumberlbl.setText("3rd Floor");
				floorplanlbl.setIcon(scaleImg(room3, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if ((e.getSource() == floor[3]) && (toggle == 1)) {
				floornumberlbl.setText("4th Floor");
				floorplanlbl.setIcon(scaleImg(room4, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}	
			
		}
		
	}
	
	static class Snagah implements ActionListener {
		private static int f = 9;
		private ImageIcon icon = new ImageIcon(Home.class.getResource("/resources/UI UPDATED/ROOM OR OFFICE SELECTION/snagah display.png"));
		private static Button[] floor = new Button[f];
		public static int i = 0;
		// Floors 
		ImageIcon floor2 = new ImageIcon(Home.class.getResource("/resources/SNAGAH BUILDING/FLOORS/2nd.png"));
		ImageIcon floor3 = new ImageIcon(Home.class.getResource("/resources/SNAGAH BUILDING/FLOORS/3rd.png"));
		ImageIcon floor4 = new ImageIcon(Home.class.getResource("/resources/SNAGAH BUILDING/FLOORS/4th.png"));
		ImageIcon floor5 = new ImageIcon(Home.class.getResource("/resources/SNAGAH BUILDING/FLOORS/5th.png"));
		ImageIcon floor6 = new ImageIcon(Home.class.getResource("/resources/SNAGAH BUILDING/FLOORS/6th.png"));
		ImageIcon floor7 = new ImageIcon(Home.class.getResource("/resources/SNAGAH BUILDING/FLOORS/7th.png"));
		ImageIcon floor8 = new ImageIcon(Home.class.getResource("/resources/SNAGAH BUILDING/FLOORS/8th.png"));
		ImageIcon floor9 = new ImageIcon(Home.class.getResource("/resources/SNAGAH BUILDING/FLOORS/9th.png"));
		ImageIcon floor10 = new ImageIcon(Home.class.getResource("/resources/SNAGAH BUILDING/FLOORS/10th.png"));
		
		// Offices
		ImageIcon office2 = new ImageIcon(Home.class.getResource("/resources/SNAGAH BUILDING/OFFICE HIGHLIGHT/2nd.png"));
		ImageIcon office3 = new ImageIcon(Home.class.getResource("/resources/SNAGAH BUILDING/OFFICE HIGHLIGHT/3rd.png"));
		ImageIcon office4 = new ImageIcon(Home.class.getResource("/resources/SNAGAH BUILDING/OFFICE HIGHLIGHT/4h.png"));
		ImageIcon office5 = new ImageIcon(Home.class.getResource("/resources/SNAGAH BUILDING/OFFICE HIGHLIGHT/5th.png"));
		ImageIcon office6 = new ImageIcon(Home.class.getResource("/resources/SNAGAH BUILDING/OFFICE HIGHLIGHT/6th.png"));
		ImageIcon office7 = new ImageIcon(Home.class.getResource("/resources/SNAGAH BUILDING/OFFICE HIGHLIGHT/7th.png"));
		ImageIcon office8 = new ImageIcon(Home.class.getResource("/resources/SNAGAH BUILDING/OFFICE HIGHLIGHT/8th.png"));
		ImageIcon office9 = new ImageIcon(Home.class.getResource("/resources/SNAGAH BUILDING/OFFICE HIGHLIGHT/9th.png"));
		ImageIcon office10 = new ImageIcon(Home.class.getResource("/resources/SNAGAH BUILDING/OFFICE HIGHLIGHT/10th.png"));
		
		// Room
		ImageIcon room2 = new ImageIcon(Home.class.getResource("/resources/SNAGAH BUILDING/ROOM HIGHLIGHT/2nd.png"));
		ImageIcon room3 = new ImageIcon(Home.class.getResource("/resources/SNAGAH BUILDING/ROOM HIGHLIGHT/3rd.png"));
		ImageIcon room4 = new ImageIcon(Home.class.getResource("/resources/SNAGAH BUILDING/ROOM HIGHLIGHT/4th.png"));
		ImageIcon room57 = new ImageIcon(Home.class.getResource("/resources/SNAGAH BUILDING/ROOM HIGHLIGHT/5th.png"));
		ImageIcon room8 = new ImageIcon(Home.class.getResource("/resources/SNAGAH BUILDING/ROOM HIGHLIGHT/8th.png"));
		ImageIcon room9 = new ImageIcon(Home.class.getResource("/resources/SNAGAH BUILDING/ROOM HIGHLIGHT/9th.png"));
		ImageIcon room10 = new ImageIcon(Home.class.getResource("/resources/SNAGAH BUILDING/ROOM HIGHLIGHT/10th.png"));
		
		public void init() {
			floorBG.setIcon(scaleImg(icon, x, y));
			for (int i = 0; i < f; i++) {
				floor[i] = new Button();
				floor[i].addActionListener(this);
				floors.add(floor[i]);
			}
			
			i = 1;
			floor[0].setBounds(51, 529, 225, 65);	floor[3].setBounds(51, 801, 225, 65);	floor[6].setBounds(307, 709, 225, 65);
			floor[1].setBounds(52, 619, 225, 65);	floor[4].setBounds(306, 529, 225, 65);	floor[7].setBounds(306, 801, 225, 65);
			floor[2].setBounds(50, 711, 225, 65);	floor[5].setBounds(305, 619, 225, 65);	floor[8].setBounds(179, 895, 225, 65);
		}
		
		public static void clean() {
			for (int i = 0; i < f; i++) {
				floors.remove(floor[i]);
			}
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
				// Floors
				if (e.getSource() == floor[0]) {
					floornumberlbl.setText("2nd Floor");
					floorplanlbl.setIcon(scaleImg(floor2, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
					System.out.println("2nd Floor");
				}
				
				if (e.getSource() == floor[1]) {
					floornumberlbl.setText("3rd Floor");
					floorplanlbl.setIcon(scaleImg(floor3, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}
				
				if (e.getSource() == floor[2]) {
					floornumberlbl.setText("4th Floor");
					floorplanlbl.setIcon(scaleImg(floor4, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}
				
				if (e.getSource() == floor[3]) {
					floornumberlbl.setText("5th Floor");
					floorplanlbl.setIcon(scaleImg(floor5, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}
				
				if (e.getSource() == floor[4]) {
					floornumberlbl.setText("6th Floor");
					floorplanlbl.setIcon(scaleImg(floor6, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}

				if (e.getSource() == floor[5]) {
					floornumberlbl.setText("7th Floor");
					floorplanlbl.setIcon(scaleImg(floor7, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}
				
				if (e.getSource() == floor[6]) {
					floornumberlbl.setText("8th Floor");
					floorplanlbl.setIcon(scaleImg(floor8, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}
				
				if (e.getSource() == floor[7]) {
					floornumberlbl.setText("9th Floor");
					floorplanlbl.setIcon(scaleImg(floor9, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}
				
				if (e.getSource() == floor[8]) {
					floornumberlbl.setText("10th Floor");
					floorplanlbl.setIcon(scaleImg(floor10, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}
				
				// Offices
				if ((e.getSource() == floor[0]) && (toggle == 2)) {
					floornumberlbl.setText("2nd Floor");
					floorplanlbl.setIcon(scaleImg(office2, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}
				
				if ((e.getSource() == floor[1]) && (toggle == 2)) {
					floornumberlbl.setText("3rd Floor");
					floorplanlbl.setIcon(scaleImg(office3, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}
				
				if ((e.getSource() == floor[2]) && (toggle == 2)) {
					floornumberlbl.setText("4th Floor");
					floorplanlbl.setIcon(scaleImg(office4, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}
				
				if ((e.getSource() == floor[3]) && (toggle == 2)) {
					floornumberlbl.setText("5th Floor");
					floorplanlbl.setIcon(scaleImg(office5, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}
				
				if ((e.getSource() == floor[4]) && (toggle == 2)) {
					floornumberlbl.setText("6th Floor");
					floorplanlbl.setIcon(scaleImg(office6, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}

				if ((e.getSource() == floor[5]) && (toggle == 2)) {
					floornumberlbl.setText("7th Floor");
					floorplanlbl.setIcon(scaleImg(office7, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}
				
				if ((e.getSource() == floor[6]) && (toggle == 2)) {
					floornumberlbl.setText("8th Floor");
					floorplanlbl.setIcon(scaleImg(office8, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}
				
				if ((e.getSource() == floor[7]) && (toggle == 2)) {
					floornumberlbl.setText("9th Floor");
					floorplanlbl.setIcon(scaleImg(office9, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}
				
				if ((e.getSource() == floor[8]) && (toggle == 2)) {
					floornumberlbl.setText("10th Floor");
					floorplanlbl.setIcon(scaleImg(office10, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}
				
				// Rooms
				if ((e.getSource() == floor[0]) && (toggle == 1)) {
					floornumberlbl.setText("2nd Floor");
					floorplanlbl.setIcon(scaleImg(room2, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}
				
				if ((e.getSource() == floor[1]) && (toggle == 1)) {
					floornumberlbl.setText("3rd Floor");
					floorplanlbl.setIcon(scaleImg(room3, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}
				
				if ((e.getSource() == floor[2]) && (toggle == 1)) {
					floornumberlbl.setText("4th Floor");
					floorplanlbl.setIcon(scaleImg(room4, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}
				
				if ((e.getSource() == floor[3]) && (toggle == 1)) {
					floornumberlbl.setText("5th Floor");
					floorplanlbl.setIcon(scaleImg(room57, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}
				
				if ((e.getSource() == floor[4]) && (toggle == 1)) {
					floornumberlbl.setText("6th Floor");
					floorplanlbl.setIcon(scaleImg(room57, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}

				if ((e.getSource() == floor[5]) && (toggle == 1)) {
					floornumberlbl.setText("7th Floor");
					floorplanlbl.setIcon(scaleImg(room57, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}
				
				if ((e.getSource() == floor[6]) && (toggle == 1)) {
					floornumberlbl.setText("8th Floor");
					floorplanlbl.setIcon(scaleImg(room8, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}
				
				if ((e.getSource() == floor[7]) && (toggle == 1)) {
					floornumberlbl.setText("9th Floor");
					floorplanlbl.setIcon(scaleImg(room9, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}
				
				if ((e.getSource() == floor[8]) && (toggle == 1)) {
					floornumberlbl.setText("10th Floor");
					floorplanlbl.setIcon(scaleImg(room10, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}
				
		}
	}

	static class Rd implements ActionListener {
		private static int f = 6;
		private ImageIcon icon = new ImageIcon(Home.class.getResource("/resources/UI UPDATED/ROOM OR OFFICE SELECTION/research and development display.png"));
		private static Button[] floor = new Button[f];
		public static int i = 0;
		
		// Floors
		ImageIcon floor1 = new ImageIcon(Home.class.getResource("/resources/RND BUILDING/FLOORS/1st.png"));
		ImageIcon floor2 = new ImageIcon(Home.class.getResource("/resources/RND BUILDING/FLOORS/2nd.png"));
		ImageIcon floor3 = new ImageIcon(Home.class.getResource("/resources/RND BUILDING/FLOORS/3rd.png"));
		ImageIcon floor4 = new ImageIcon(Home.class.getResource("/resources/RND BUILDING/FLOORS/4th.png"));
		ImageIcon floor5 = new ImageIcon(Home.class.getResource("/resources/RND BUILDING/FLOORS/5th.png"));
		ImageIcon floor6 = new ImageIcon(Home.class.getResource("/resources/RND BUILDING/FLOORS/6th.png"));
		
		// Office
		ImageIcon office1 = new ImageIcon(Home.class.getResource("/resources/RND BUILDING/OFFICE HIGHLIGHT/1st.png"));
		ImageIcon office2 = new ImageIcon(Home.class.getResource("/resources/RND BUILDING/OFFICE HIGHLIGHT/2nd.png"));
		ImageIcon office3 = new ImageIcon(Home.class.getResource("/resources/RND BUILDING/OFFICE HIGHLIGHT/3rd.png"));
		ImageIcon office4 = new ImageIcon(Home.class.getResource("/resources/RND BUILDING/OFFICE HIGHLIGHT/4th.png"));
		ImageIcon office5 = new ImageIcon(Home.class.getResource("/resources/RND BUILDING/OFFICE HIGHLIGHT/5th.png"));
		ImageIcon office6 = new ImageIcon(Home.class.getResource("/resources/RND BUILDING/OFFICE HIGHLIGHT/6th.png"));
		
		// Rooms
		ImageIcon room1 = new ImageIcon(Home.class.getResource("/resources/RND BUILDING/ROOM HIGHLIGHT/1st.png"));
		ImageIcon room2 = new ImageIcon(Home.class.getResource("/resources/RND BUILDING/ROOM HIGHLIGHT/2nd.png"));
		ImageIcon room3 = new ImageIcon(Home.class.getResource("/resources/RND BUILDING/ROOM HIGHLIGHT/3rd.png"));
		ImageIcon room4 = new ImageIcon(Home.class.getResource("/resources/RND BUILDING/ROOM HIGHLIGHT/4th.png"));
		ImageIcon room5 = new ImageIcon(Home.class.getResource("/resources/RND BUILDING/ROOM HIGHLIGHT/5th.png"));
		ImageIcon room6 = new ImageIcon(Home.class.getResource("/resources/RND BUILDING/ROOM HIGHLIGHT/6th.png"));
		
		public void init() {
			floorBG.setIcon(scaleImg(icon, x, y));
			for (int i = 0; i < f; i++) {
				floor[i] = new Button();
				floor[i].addActionListener(this);
				floors.add(floor[i]);
			}
			i = 1;
			floor[0].setBounds(50, 561, 226, 67);
			floor[1].setBounds(51, 650, 226, 67);
			floor[2].setBounds(51, 741, 226, 67);
			floor[3].setBounds(51, 832, 226, 67);
			floor[4].setBounds(306, 562, 226, 67);
			floor[5].setBounds(306, 650, 226, 67);
		}
		
		public static void clean() {
			for (int i = 0; i < f; i++) {
				floors.remove(floor[i]);
			}
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// Floors
			if (e.getSource() == floor[0]) {
				floornumberlbl.setText("1st Floor");
				floorplanlbl.setIcon(scaleImg(floor1, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if (e.getSource() == floor[1]) {
				floornumberlbl.setText("2nd Floor");
				floorplanlbl.setIcon(scaleImg(floor2, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if (e.getSource() == floor[2]) {
				floornumberlbl.setText("3rd Floor");
				floorplanlbl.setIcon(scaleImg(floor3, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if (e.getSource() == floor[3]) {
				floornumberlbl.setText("4th Floor");
				floorplanlbl.setIcon(scaleImg(floor4, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if (e.getSource() == floor[4]) {
				floornumberlbl.setText("5th Floor");
				floorplanlbl.setIcon(scaleImg(floor5, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if (e.getSource() == floor[5]) {
				floornumberlbl.setText("6th Floor");
				floorplanlbl.setIcon(scaleImg(floor6, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			// Offices
			if ((e.getSource() == floor[0]) && (toggle == 2)) {
				floornumberlbl.setText("1st Floor");
				floorplanlbl.setIcon(scaleImg(office1, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
			}
			
			if ((e.getSource() == floor[1]) && (toggle == 2)) {
				floornumberlbl.setText("2nd Floor");
				floorplanlbl.setIcon(scaleImg(office2, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
			}
			
			if ((e.getSource() == floor[2]) && (toggle == 2)) {
				floornumberlbl.setText("3rd Floor");
				floorplanlbl.setIcon(scaleImg(office3, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if ((e.getSource() == floor[3]) && (toggle == 2)) {
				floornumberlbl.setText("4th Floor");
				floorplanlbl.setIcon(scaleImg(office4, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if ((e.getSource() == floor[4]) && (toggle == 2)) {
				floornumberlbl.setText("5th Floor");
				floorplanlbl.setIcon(scaleImg(office5, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if ((e.getSource() == floor[5]) && (toggle == 2)) {
				floornumberlbl.setText("6th Floor");
				floorplanlbl.setIcon(scaleImg(office6, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			// ROoms
			if ((e.getSource() == floor[0]) && (toggle == 1)) {
				floornumberlbl.setText("1st Floor");
				floorplanlbl.setIcon(scaleImg(room1, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
			}
			
			if ((e.getSource() == floor[1]) && (toggle == 1)) {
				floornumberlbl.setText("2nd Floor");
				floorplanlbl.setIcon(scaleImg(room2, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
			}
			
			if ((e.getSource() == floor[2]) && (toggle == 1)) {
				floornumberlbl.setText("3rd Floor");
				floorplanlbl.setIcon(scaleImg(room3, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if ((e.getSource() == floor[3]) && (toggle == 1)) {
				floornumberlbl.setText("4th Floor");
				floorplanlbl.setIcon(scaleImg(room4, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if ((e.getSource() == floor[4]) && (toggle == 1)) {
				floornumberlbl.setText("5th Floor");
				floorplanlbl.setIcon(scaleImg(room5, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if ((e.getSource() == floor[5]) && (toggle == 1)) {
				floornumberlbl.setText("6th Floor");
				floorplanlbl.setIcon(scaleImg(room6, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
		}
	}
	
	static class Wellness implements ActionListener {
		private static int f = 7;
		private static Button[] floor = new Button[f];
		private ImageIcon icon = new ImageIcon(Home.class.getResource("/resources/UI UPDATED/ROOM OR OFFICE SELECTION/wellness display.png"));
		public static int i = 0;
		
		// Floors
		ImageIcon floor1 = new ImageIcon(Home.class.getResource("/resources/WELLNESS BUILDING/FLOORS/1st FLOOR.png"));
		ImageIcon floor2 = new ImageIcon(Home.class.getResource("/resources/WELLNESS BUILDING/FLOORS/2ND FLOOR.png"));
		ImageIcon floor3 = new ImageIcon(Home.class.getResource("/resources/WELLNESS BUILDING/FLOORS/3rd FLOOR.png"));
		ImageIcon floor4 = new ImageIcon(Home.class.getResource("/resources/WELLNESS BUILDING/FLOORS/4th FLOOR.png"));
		ImageIcon floor5 = new ImageIcon(Home.class.getResource("/resources/WELLNESS BUILDING/FLOORS/5th FLOOR.png"));
		ImageIcon floor6 = new ImageIcon(Home.class.getResource("/resources/WELLNESS BUILDING/FLOORS/6th FLOOR.png"));
		ImageIcon floor7 = new ImageIcon(Home.class.getResource("/resources/WELLNESS BUILDING/FLOORS/7th FLOOR.png"));
		
		// Offices
		ImageIcon office1 = new ImageIcon(Home.class.getResource("/resources/WELLNESS BUILDING/OFFICE HIGHLIGHT/1st.png"));
		ImageIcon office2 = new ImageIcon(Home.class.getResource("/resources/WELLNESS BUILDING/OFFICE HIGHLIGHT/2nd.png"));
		ImageIcon office3 = new ImageIcon(Home.class.getResource("/resources/WELLNESS BUILDING/OFFICE HIGHLIGHT/3rd.png"));
		ImageIcon office4 = new ImageIcon(Home.class.getResource("/resources/WELLNESS BUILDING/OFFICE HIGHLIGHT/4th.png"));
		ImageIcon office5 = new ImageIcon(Home.class.getResource("/resources/WELLNESS BUILDING/OFFICE HIGHLIGHT/5th.png"));
		
		
		
		public void init() {
			floorBG.setIcon(scaleImg(icon, x, y));
			for (int i = 0; i < f; i++) {
				floor[i] = new Button();
				floor[i].addActionListener(this);
				floors.add(floor[i]);
			}
			i = 1;

			floor[0].setBounds(52, 561, 226, 67);	floor[4].setBounds(305, 559, 226, 67);
			floor[1].setBounds(53, 650, 226, 67);	floor[5].setBounds(305, 650, 226, 67);
			floor[2].setBounds(49, 742, 227, 67);	floor[6].setBounds(306, 740, 227, 67);
			floor[3].setBounds(50, 831, 227, 67);
		}
		
		public static void clean() {
			for (int i = 0; i < f; i++) {
				floors.remove(floor[i]);
			}
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// Floors
			if (e.getSource() == floor[0]) {
				floornumberlbl.setText("1st Floor");
				floorplanlbl.setIcon(scaleImg(floor1, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if (e.getSource() == floor[1]) {
				floornumberlbl.setText("2nd Floor");
				floorplanlbl.setIcon(scaleImg(floor2, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if (e.getSource() == floor[2]) {
				floornumberlbl.setText("3rd Floor");
				floorplanlbl.setIcon(scaleImg(floor3, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if (e.getSource() == floor[3]) {
				floornumberlbl.setText("4th Floor");
				floorplanlbl.setIcon(scaleImg(floor4, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if (e.getSource() == floor[4]) {
				floornumberlbl.setText("5th Floor");
				floorplanlbl.setIcon(scaleImg(floor5, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if (e.getSource() == floor[5]) {
				floornumberlbl.setText("6th Floor");
				floorplanlbl.setIcon(scaleImg(floor6, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if (e.getSource() == floor[6]) {
				floornumberlbl.setText("7th Floor");
				floorplanlbl.setIcon(scaleImg(floor7, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			// Offices
			if ((e.getSource() == floor[0]) && (toggle == 2)) {
				floornumberlbl.setText("1st Floor");
				floorplanlbl.setIcon(scaleImg(office1, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
			}
			
			if ((e.getSource() == floor[1]) && (toggle == 2)) {
				floornumberlbl.setText("2nd Floor");
				floorplanlbl.setIcon(scaleImg(office2, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
			}
			
			if ((e.getSource() == floor[2]) && (toggle == 2)) {
				floornumberlbl.setText("3rd Floor");
				floorplanlbl.setIcon(scaleImg(office3, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if ((e.getSource() == floor[3]) && (toggle == 2)) {
				floornumberlbl.setText("4th Floor");
				floorplanlbl.setIcon(scaleImg(office4, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
			
			if ((e.getSource() == floor[4]) && (toggle == 2)) {
				floornumberlbl.setText("5th Floor");
				floorplanlbl.setIcon(scaleImg(office5, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				
			}
		
			
		}
	}
	
	static class Profeta implements ActionListener {
		private static int f = 5;
		private ImageIcon icon = new ImageIcon(Home.class.getResource("/resources/UI UPDATED/ROOM OR OFFICE SELECTION/profeta display.png"));
		private static Button[] floor = new Button[f];
		public static int i = 0;
		
		// Floors
		ImageIcon floor1 = new ImageIcon(Home.class.getResource("/resources/PROFETA BUILDING/FLOORS/PROFETA 1.png"));
		ImageIcon floor2 = new ImageIcon(Home.class.getResource("/resources/PROFETA BUILDING/FLOORS/PROFETA 2.png"));
		ImageIcon floor3 = new ImageIcon(Home.class.getResource("/resources/PROFETA BUILDING/FLOORS/PROFETA 3.png"));
		ImageIcon floor4 = new ImageIcon(Home.class.getResource("/resources/PROFETA BUILDING/FLOORS/PROFETA 4.png"));
		ImageIcon floor5 = new ImageIcon(Home.class.getResource("/resources/PROFETA BUILDING/FLOORS/PROFETA 5.png"));
		
		// OFFICES
		ImageIcon office1 = new ImageIcon(Home.class.getResource("/resources/PROFETA BUILDING/OFFICE HIGHLIGHT/1st.png"));
		ImageIcon office2 = new ImageIcon(Home.class.getResource("/resources/PROFETA BUILDING/OFFICE HIGHLIGHT/2nd.png"));
		ImageIcon office3 = new ImageIcon(Home.class.getResource("/resources/PROFETA BUILDING/OFFICE HIGHLIGHT/3rd.png"));
		ImageIcon office4 = new ImageIcon(Home.class.getResource("/resources/PROFETA BUILDING/OFFICE HIGHLIGHT/4th.png"));
		ImageIcon office5 = new ImageIcon(Home.class.getResource("/resources/PROFETA BUILDING/OFFICE HIGHLIGHT/5th.png"));
		
		// Rooms
		ImageIcon room3 = new ImageIcon(Home.class.getResource("/resources/PROFETA BUILDING/ROOM HIGHLIGHT/3rd.png"));
		ImageIcon room4 = new ImageIcon(Home.class.getResource("/resources/PROFETA BUILDING/ROOM HIGHLIGHT/4th.png"));
		ImageIcon room5 = new ImageIcon(Home.class.getResource("/resources/PROFETA BUILDING/ROOM HIGHLIGHT/5th.png"));		
		
		public void init() {
			floorBG.setIcon(scaleImg(icon, x, y));
			for (int i = 0; i < f; i++) {
				floor[i] = new Button();
				floor[i].addActionListener(this);
				floors.add(floor[i]);
			}
			i = 1;
			
			floor[0].setBounds(49, 560, 227, 67);
			floor[1].setBounds(49, 651, 227, 67);
			floor[2].setBounds(50, 741, 227, 67);
			floor[3].setBounds(50, 834, 227, 67);
			floor[4].setBounds(305, 561, 227, 67);

		}
		
		public static void clean() {
			for (int i = 0; i < f; i++) {
				floors.remove(floor[i]);
			}
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
				// Floors
				if (e.getSource() == floor[0]) {
					floornumberlbl.setText("1st Floor");
					floorplanlbl.setIcon(scaleImg(floor1, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
					
				}
				
				if (e.getSource() == floor[1]) {
					floornumberlbl.setText("2nd Floor");
					floorplanlbl.setIcon(scaleImg(floor2, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
					
				}
				
				if (e.getSource() == floor[2]) {
					floornumberlbl.setText("3rd Floor");
					floorplanlbl.setIcon(scaleImg(floor3, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
					
				}
				
				if (e.getSource() == floor[3]) {
					floornumberlbl.setText("4th Floor");
					floorplanlbl.setIcon(scaleImg(floor4, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
					
				}
				
				if (e.getSource() == floor[4]) {
					floornumberlbl.setText("5th Floor");
					floorplanlbl.setIcon(scaleImg(floor5, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
					
				}
				
				// Rooms
				
				if ((e.getSource() == floor[2]) && (toggle == 1)) {
					floornumberlbl.setText("3rd Floor");
					floorplanlbl.setIcon(scaleImg(room3, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
					
				}
				
				if ((e.getSource() == floor[3]) && (toggle == 1)) {
					floornumberlbl.setText("4th Floor");
					floorplanlbl.setIcon(scaleImg(room4, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
					
				}
				
				if ((e.getSource() == floor[4]) && (toggle == 1)) {
					floornumberlbl.setText("5th Floor");
					floorplanlbl.setIcon(scaleImg(room5, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
					
				}
				
				// Offices
				if ((e.getSource() == floor[0]) && (toggle == 2)) {
					floornumberlbl.setText("1st Floor");
					floorplanlbl.setIcon(scaleImg(office1, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}
				
				if ((e.getSource() == floor[1]) && (toggle == 2)) {
					floornumberlbl.setText("2nd Floor");
					floorplanlbl.setIcon(scaleImg(office2, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
				}
				
				if ((e.getSource() == floor[2]) && (toggle == 2)) {
					floornumberlbl.setText("3rd Floor");
					floorplanlbl.setIcon(scaleImg(office3, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
					
				}
				
				if ((e.getSource() == floor[3]) && (toggle == 2)) {
					floornumberlbl.setText("4th Floor");
					floorplanlbl.setIcon(scaleImg(office4, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
					
				}
				
				if ((e.getSource() == floor[4]) && (toggle == 2)) {
					floornumberlbl.setText("5th Floor");
					floorplanlbl.setIcon(scaleImg(office5, floorplanlbl.getWidth(), floorplanlbl.getHeight()));
					
				}
				
		}
	}
}


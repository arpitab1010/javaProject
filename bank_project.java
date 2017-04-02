import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.String.*;
import javax.swing.border.*;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
class bank_project extends JFrame implements ActionListener
{
	JFrame f;
	JLabel l1;
	JLabel l2;
	JLabel l3;
	JTextField tf1;
	JPasswordField tf2;
	JButton b1;
	JButton b2;
	JButton b3;
	bank_project()
	{
		setLayout(null);
		//setSize(260,200);
		setVisible(true);
		setBounds(550,250,260,200);
		getContentPane().setBackground(Color.cyan);
		Color c1=new Color(0,0,0);
		l1=new JLabel("ADMIN");
		l1.setForeground(c1);
		l1.setBounds(100,10,80,20);
		l2=new JLabel("Username");
		l2.setBounds(20,30,80,20);
		tf1=new JTextField();
		tf1.setBounds(100,30,80,20);
		l3=new JLabel("Password");
		l3.setBounds(20,60,80,20);
		tf2=new JPasswordField();
		tf2.setBounds(100,60,80,20);
		b1=new JButton("PROCEED");
		b1.setBounds(15,100,100,20);
		b1.addActionListener(this);
		b2=new JButton("EXIT");
		b2.setBounds(130,100,80,20);
		b2.addActionListener(this);
		b3=new JButton("CHANGE PASSWORD");
		b3.setBounds(35,130,160,20);
		b3.addActionListener(this);
		add(l1);
		add(l2);
		add(l3);
		add(tf1);
		add(tf2);
		add(b1);
		add(b2);
		add(b3);
		tf1.requestFocus(true);
	}
	public void actionPerformed(ActionEvent e1)
	{
		try
		{
			tf1.requestFocus(true);
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:bank_data");
			if(e1.getSource()==b1)
			{
				PreparedStatement ps=con.prepareStatement("select * from admin where username=? and password=?");
				ps.setString(1,tf1.getText());
				ps.setString(2,tf2.getText());
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					JOptionPane.showMessageDialog(this,"Administartion has been logged in");
					dispose();
					new login();
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Not a Valid Admin");
					tf1.setText("");
					tf2.setText("");
				}
			}
			else if(e1.getSource()==b2)
			{
				dispose();
			}
			else if(e1.getSource()==b3)
			{
				dispose();
				new change();
			}
		}
		catch(Exception ex)
		{
			System.out.println("Exception is "+ex);
		}
	}
	public static void main(String args[])
	{
		bank_project b=new bank_project();
	}
}
class change extends JFrame implements ActionListener
{
	JLabel l1;
	JLabel l2;
	JLabel l3;
	JLabel l4;
	JLabel l5;
	JTextField tf1;
	JPasswordField tf2;
	JPasswordField tf3;
	JPasswordField tf4;
	JButton b1;
	JButton b2;
	JButton b3;
	change()
	{
		setLayout(null);
		//setSize(400,250);
		setBounds(550,200,400,250);
		setVisible(true);
		getContentPane().setBackground(Color.cyan);
		l1=new JLabel("CHANGE PASSWORD");
		Font f1=new Font("Arial Rounded MT Bold",Font.BOLD,30);
		l1.setFont(f1);
		l1.setBounds(20,20,400,30);
		l2=new JLabel("Username");
		l2.setBounds(20,50,120,20);
		tf1=new JTextField();
		tf1.setBounds(150,50,80,20);
		l3=new JLabel("Old Password");
		l3.setBounds(20,80,120,20);
		tf2=new JPasswordField();
		tf2.setBounds(150,80,80,20);
		l4=new JLabel("New Password");
		l4.setBounds(20,110,120,20);
		tf3=new JPasswordField();
		tf3.setBounds(150,110,80,20);
		l5=new JLabel("Confirm Password");
		l5.setBounds(20,140,120,20);
		tf4=new JPasswordField();
		tf4.setBounds(150,140,80,20);
		b3=new JButton("CHECK");
		b3.setBounds(30,170,80,20);
		b3.addActionListener(this);
		b1=new JButton("RESET");
		b1.setBounds(30,170,80,20);
		b1.addActionListener(this);
		b2=new JButton("BACK");
		b2.setBounds(130,170,80,20);
		b2.addActionListener(this);
		add(l1);
		add(l2);
		add(l3);
		add(tf1);
		add(tf2);
		add(b2);
		add(b3);
	}
	public void actionPerformed(ActionEvent e1)
	{
		try
		{
			tf1.requestFocus(true);
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:bank_data");
			if(e1.getSource()==b3)
			{
				PreparedStatement ps=con.prepareStatement("select * from admin where username=? and password=?");
				ps.setString(1,tf1.getText());
				ps.setString(2,tf2.getText());
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					JOptionPane.showMessageDialog(this,"Username and Password is confirmed");
					JOptionPane.showMessageDialog(this,"Enter new Password");
					add(l4);
					add(l5);
					add(tf3);
					add(tf4);
					l4.setVisible(true);
					l5.setVisible(true);
					tf3.setVisible(true);
					tf4.setVisible(true);
					add(b1);
					b1.setVisible(true);
					b3.setVisible(false);
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Invalid Username or Password");
					dispose();
					new change();
					tf1.setText("");
					tf2.setText("");
				}
			}
			else if(e1.getSource()==b1)
			{
				if((tf3.getText()).equals(tf4.getText()))
				{
						PreparedStatement ps1=con.prepareStatement("update admin set password=? where username=? and password=?");
						ps1.setString(1,tf3.getText());
						ps1.setString(2,tf1.getText());
						ps1.setString(3,tf2.getText());
						ps1.executeUpdate();
						JOptionPane.showMessageDialog(this,"Your Password has been Changed");
						dispose();
						new bank_project();
				}
				else
				{
					JOptionPane.showMessageDialog(this,"New Password and Confirm Password didn't match");
					tf3.setText("");
					tf4.setText("");
				}
			}
			else if(e1.getSource()==b2)
			{
					dispose();
					new bank_project();
			}
		}
		catch(Exception ex)
		{
			System.out.println("Exception is"+ex);
		}
	}
}
class login extends JFrame implements ActionListener
{
	JLabel l;
	JLabel l1;
	JLabel l2;
	JLabel l3;
	JTextField tf1;
	JPasswordField tf2;
	JButton b1;
	JButton b2;
	JButton b3;
	JButton b4;
	login()
	{
		setLayout(null);
		setVisible(true);
		//setSize(320,250);
		setBounds(550,200,320,250);
		getContentPane().setBackground(Color.green);
		l=new JLabel("USER LOGIN");
		l.setBounds(100,20,200,20);
		l3=new JLabel("USER SIGNUP");
		l3.setBounds(100,20,200,20);
		l1=new JLabel("USERNAME");
		l1.setBounds(20,50,80,20);
		tf1=new JTextField();
		tf1.setBounds(120,50,80,20);
		l2=new JLabel("PASSWORD");
		l2.setBounds(20,80,80,20);
		tf2=new JPasswordField();
		tf2.setBounds(120,80,80,20);
		b1=new JButton("LOGIN");
		b1.setBounds(220,80,80,30);
		b1.addActionListener(this);
		b2=new JButton("CANCEL");
		b2.setBounds(50,130,80,30);
		b2.addActionListener(this);
		b3=new JButton("SIGNUP");
		b3.setBounds(220,40,80,30);
		b3.addActionListener(this);
		b4=new JButton("RESET");
		b4.setBounds(140,130,80,30);
		b4.addActionListener(this);
		add(l1);
		add(l);
		add(l2);
		add(tf1);
		add(tf2);
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		tf1.requestFocus(true);
	}
	public void actionPerformed(ActionEvent e1)
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:bank_data");
			if(e1.getSource()==b1)
			{
				PreparedStatement ps=con.prepareStatement("select * from login where username=? and password=?");
				ps.setString(1,tf1.getText());
				ps.setString(2,tf2.getText());
				ResultSet rs=ps.executeQuery();
				String msg="Welcome "+tf1.getText();
				if(rs.next())
				{
					JOptionPane.showMessageDialog(this,msg);
					setVisible(false);
					new bank_p();
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Not a Valid USER");
					tf1.setText("");
					tf2.setText("");
				}
			}
			else if(e1.getSource()==b2)
			{
				dispose();
			}
			else if(e1.getSource()==b3)
			{
				dispose();
				new sign();
			}
			else if(e1.getSource()==b4)
			{
				dispose();
				new change1();
			}
		}
		catch(Exception ex)
		{
			System.out.println("Exception is in login"+ex);
		}
	}
}
class change1 extends JFrame implements ActionListener
{
	JLabel l1;
	JLabel l2;
	JLabel l3;
	JLabel l4;
	JLabel l5;
	JTextField tf1;
	JPasswordField tf2;
	JPasswordField tf3;
	JPasswordField tf4;
	JButton b1;
	JButton b2;
	JButton b3;
	change1()
	{
		setLayout(null);
		//setSize(400,250);
		setBounds(550,200,400,250);
		setVisible(true);
		getContentPane().setBackground(Color.blue);
		l1=new JLabel("CHANGE PASSWORD");
		Font f1=new Font("Arial Rounded MT Bold",Font.BOLD,30);
		l1.setFont(f1);
		l1.setBounds(20,20,400,30);
		l2=new JLabel("Username");
		l2.setBounds(20,50,120,20);
		tf1=new JTextField();
		tf1.setBounds(150,50,80,20);
		l3=new JLabel("Old Password");
		l3.setBounds(20,80,120,20);
		tf2=new JPasswordField();
		tf2.setBounds(150,80,80,20);
		l4=new JLabel("New Password");
		l4.setBounds(20,110,120,20);
		tf3=new JPasswordField();
		tf3.setBounds(150,110,80,20);
		l5=new JLabel("Confirm Password");
		l5.setBounds(20,140,120,20);
		tf4=new JPasswordField();
		tf4.setBounds(150,140,80,20);
		b3=new JButton("CHECK");
		b3.setBounds(30,170,80,20);
		b3.addActionListener(this);
		b1=new JButton("RESET");
		b1.setBounds(30,170,80,20);
		b1.addActionListener(this);
		b2=new JButton("BACK");
		b2.setBounds(130,170,80,20);
		b2.addActionListener(this);
		add(l1);
		add(l2);
		add(l3);
		add(tf1);
		add(tf2);
		add(b2);
		add(b3);
	}
	public void actionPerformed(ActionEvent e1)
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:bank_data");
			if(e1.getSource()==b3)
			{
				PreparedStatement ps=con.prepareStatement("select * from login where username=? and password=?");
				ps.setString(1,tf1.getText());
				ps.setString(2,tf2.getText());
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					JOptionPane.showMessageDialog(this,"Your Username and Password is confirmed");
					JOptionPane.showMessageDialog(this,"Enter new password "+tf1.getText());
					add(l4);
					add(l5);
					l4.setVisible(true);
					l5.setVisible(true);
					add(tf3);
					add(tf4);
					tf3.setVisible(true);
					tf4.setVisible(true);
					b3.setVisible(false);
					add(b1);
					b1.setVisible(true);
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Invalid Username and Password");
					dispose();
					new change1();
					tf1.setText("");
					tf2.setText("");
				}
			}
			else if(e1.getSource()==b1)
			{
				if((tf3.getText().equals(tf4.getText())))
				{
					PreparedStatement ps1=con.prepareStatement("update login set password=? where username=? and password=?");
					ps1.setString(1,tf3.getText());
					ps1.setString(2,tf1.getText());
					ps1.setString(3,tf2.getText());
					ps1.executeUpdate();
					JOptionPane.showMessageDialog(this,"Your Password has been updated "+tf1.getText());
					dispose();
					new login();
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Your new password and confirm password didn't match "+tf1.getText());
					tf3.setText("");
					tf4.setText("");
				}
			}
			else if(e1.getSource()==b2)
			{
				dispose();
				new login();
			}
		}
		catch(Exception ex)
		{
			System.out.println("Exception is"+ex);
		}
	}
}
class sign extends JFrame implements ActionListener
{
	JLabel l1;
	JLabel l2;
	JLabel l3;
	JTextField tf1;
	JPasswordField tf2;
	JButton b1;
	JButton b2;
	sign()
	{
		setLayout(null);
		setVisible(true);
		//setSize(300,250);
		setBounds(550,200,300,250);
		getContentPane().setBackground(Color.green);
		l1=new JLabel("USER SIGNUP");
		l1.setBounds(100,20,200,20);
		l2=new JLabel("USERNAME");
		l2.setBounds(20,50,80,20);
		tf1=new JTextField();
		tf1.setBounds(120,50,80,20);
		l3=new JLabel("PASSWORD");
		l3.setBounds(20,80,80,20);
		tf2=new JPasswordField();
		tf2.setBounds(120,80,80,20);
		b1=new JButton("SIGNUP");
		b1.setBounds(50,150,80,20);
		b1.addActionListener(this);
		b2=new JButton("BACK");
		b2.setBounds(150,150,80,20);
		b2.addActionListener(this);
		add(l1);
		add(l2);
		add(l3);
		add(tf1);
		add(tf2);
		add(b1);
		add(b2);
	}
	public void actionPerformed(ActionEvent e1)
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:bank_data");
			if(e1.getSource()==b1)
			{
				PreparedStatement ps=con.prepareStatement("insert into login values(?,?)");
				ps.setString(1,tf1.getText());
				ps.setString(2,tf2.getText());
				if((tf1.getText().equals(""))&&(tf2.getText().equals("")))
				{
					JOptionPane.showMessageDialog(this,"Enter Username and Password");
					tf1.setText("");
					tf2.setText("");
				}
				else
				{
					//PreparedStatement ps=con.prepareStatement("insert into login values(?,?)");
					//ps.setString(1,tf1.getText());
					//ps.setString(2,tf2.getText());
					ps.executeUpdate();
					String msg="Welcome "+tf1.getText();
					con.close();
					JOptionPane.showMessageDialog(this,"You are now a user");
					JOptionPane.showMessageDialog(this,msg);
					tf1.setText("");
					tf2.setText("");
					dispose();
					new login();
				}
				PreparedStatement ps1=con.prepareStatement("insert into bank_table values(?)");
				//ps.setString(1,tf1.getText());
				ps1.setString(1,tf2.getText());
			}
			else if(e1.getSource()==b2)
			{
				dispose();
				new login();
			}
		}
		catch(Exception ex)
		{
			System.out.println("Exception"+ex);
		}
	}
}
class bank_p extends JFrame implements ActionListener
{
	JLabel l1;
	JLabel l2;
	JLabel l3;
	JButton b1;
	JButton b2;
	JLabel l4;
	JLabel l5;
	JLabel l6;
	JLabel l7;
	JButton b3;
	bank_p()
	{
		setLayout(null);
		setSize(1366,768);
		setVisible(true);
		getContentPane().setBackground(Color.blue);
		l1=new JLabel("PROJECT ON PUNJAB NATIONAL BANK");
		Font f1=new Font("Bodoni MT Black",Font.BOLD,50);
		l1.setFont(f1);
		l1.setBounds(100,50,1300,50);
		l2=new JLabel("the name you can BANK upon...");
		Font f2=new Font("Bradley Hand ITC",Font.ITALIC,20);
		l2.setFont(f2);
		l2.setBounds(1000,105,500,30);
		Font f4=new Font("Aharoni",Font.ITALIC,30);
		l3=new JLabel("SUBMITTED BY: Arpit Bansal");
		l3.setFont(f4);
		l3.setBounds(30,550,500,30);
		l4=new JLabel("ROLL NO.: 11504889");
		l4.setFont(f4);
		l4.setBounds(30,600,500,30);
		l5=new JLabel("B.TECH Computer Science");
		l5.setFont(f4);
		l5.setBounds(30,650,600,30);
		l6=new JLabel("LOVELY PROFESSIONAL UNIVERSITY");
		Font f3=new Font("Book Antiqua",Font.ITALIC,30);
		l6.setFont(f3);
		l6.setBounds(400,230,600,30);
		l7=new JLabel("JALANDHAR, PUNJAB");
		l7.setFont(f3);
		l7.setBounds(500,270,500,30);
		ImageIcon i1=new ImageIcon("lpu_logo.png");
		Border border=BorderFactory.createRaisedBevelBorder();
		Border tripleBorder=new CompoundBorder(new CompoundBorder(border,border),border);
		UIManager.put("Button.border",tripleBorder);
		b1=new JButton("Enter");
		//b1.setMnemonic('X');
		b1.setBounds(1200,650,80,30);
		b1.addActionListener(this);
		b2=new JButton("Exit");
		b2.setBounds(1100,650,80,30);
		b2.addActionListener(this);
		b3=new JButton(i1);
		b3.setBounds(490,320,339,154);
		b3.addActionListener(this);
		add(l1);
		add(l2);
		add(l3);
		add(l4);
		add(l5);
		add(l6);
		add(b1);
		add(b2);
		add(b3);
		add(l7);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			dispose();
			new bank_project1();
		}
		else if(e.getSource()==b3)
		{
			dispose();
			new lovely();
		}
		else
		{
			dispose();
			new login();
		}
	}
}
class bank_project1 extends JFrame implements ActionListener
{
	JLabel l;
	JLabel ll;
	JLabel l1;
	JLabel l2;
	JButton b1;
	JButton b2;
	JButton b3;
	JButton b4;
	JButton b5;
	JButton b6;
	JButton b7;
	JButton b8;
	JButton b9;
	bank_project1()
	{
		setLayout(null);
		setSize(1366,768);
		setVisible(true);
		Color c1=new Color(255,255,0);
		ImageIcon i3=new ImageIcon("line.gif");
		l=new JLabel(i3);
		l.setBounds(180,130,532,24);
		ll=new JLabel(i3);
		ll.setBounds(712,130,532,24);
		l1=new JLabel("AVAILABLE FACILITIES");
		l1.setForeground(c1);
		Font f1=new Font("Bodoni MT Black",Font.BOLD,80);
		l1.setFont(f1);
		l1.setBounds(150,50,1300,80);
		ImageIcon i1=new ImageIcon("smilies.jpg");
		l2=new JLabel(i1);
		l2.setBounds(0,0,1360,730);
		b1=new JButton("Create new Account");
		b1.setBounds(550,200,300,30);
		b1.addActionListener(this);
		b2=new JButton("Transaction");
		b2.setBounds(550,250,300,30);
		b2.addActionListener(this);
		b3=new JButton("View All Account");
		b3.setBounds(550,300,300,30);
		b3.addActionListener(this);
		b4=new JButton("Any Problems...");
		b4.setBounds(550,500,300,30);
		b4.addActionListener(this);
		b5=new JButton("Not Interested?");
		b5.setBounds(550,550,300,30);
		b5.addActionListener(this);
		ImageIcon i2=new ImageIcon("back.jpg");
		b6=new JButton(i2);
		b6.setBounds(1200,600,120,60);
		b6.addActionListener(this);
		b7=new JButton("Delete an Account");
		b7.setBounds(550,350,300,30);
		b7.addActionListener(this);
		b8=new JButton("Amount Transfer");
		b8.setBounds(550,400,300,30);
		b8.addActionListener(this);
		b9=new JButton("View Transaction Details");
		b9.setBounds(550,450,300,30);
		b9.addActionListener(this);
		add(l2);
		l2.add(ll);
		l2.add(l);
		l2.add(l1);
		l2.add(b1);
		l2.add(b2);
		l2.add(b3);
		l2.add(b4);
		l2.add(b5);
		l2.add(b6);
		l2.add(b7);
		l2.add(b8);
		l2.add(b9);
	}
	public void actionPerformed(ActionEvent e1)
	{
		if(e1.getSource()==b1)
		{
			dispose();
			new create();
		}
		else if(e1.getSource()==b2)
		{
			dispose();
			new trans();
		}
		else if(e1.getSource()==b3)
		{
			int response=JOptionPane.showConfirmDialog(this,"Only Admin can access it","Warning Message",JOptionPane.YES_NO_OPTION);
			if(response==JOptionPane.YES_OPTION)
			{
				dispose();
				new view();
			}
			//JOptionPane.showMessageDialog(this,"So if you are not an Admin \nExit this window and choose another field");
			else
			{
				dispose();
				new bank_project1();
			}
		}
		else if(e1.getSource()==b4)
		{
			dispose();
			new help();
		}
		else if(e1.getSource()==b5)
		{
			dispose();
		}
		else if(e1.getSource()==b6)
		{
			dispose();
			new bank_p();
		}
		else if(e1.getSource()==b7)
		{
			dispose();
			new delete();
		}
		else if(e1.getSource()==b8)
		{
			dispose();
			new transfer();
		}
		else if(e1.getSource()==b9)
		{
			dispose();
			new record();
		}
	}
}
class record extends JFrame implements ActionListener
{
	JLabel l1;
	JLabel l2;
	JTextField tf1;
	JButton b1;
	JButton b2,b3;
	JLabel l22;
	JPasswordField jp1;
	record()
	{
		setLayout(null);
		setSize(1366,768);
		setVisible(true);
		getContentPane().setBackground(Color.cyan);
		Color c1=new Color(255,255,255);
		Font f1=new Font("Magneto",Font.ITALIC,30);
		l1=new JLabel("Enter your Account number");
		l1.setForeground(c1);
		l1.setFont(f1);
		l1.setBounds(100,50,500,30);
		tf1=new JTextField();
		tf1.setBounds(650,50,100,30);
		l22=new JLabel("Enter Password");
		l22.setFont(f1);
		l22.setForeground(c1);
		l22.setBounds(100,100,500,30);
		jp1=new JPasswordField();
		jp1.setBounds(650,100,100,30);
		b3=new JButton("CHECK");
		b3.setBounds(450,150,100,30);
		b3.addActionListener(this);
		b1=new JButton("VIEW");
		b1.setBounds(450,150,100,30);
		b1.addActionListener(this);
		b2=new JButton("EXIT");
		b2.setBounds(1000,650,100,30);
		b2.addActionListener(this);
		Date d=Calendar.getInstance().getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("dd:MM:yyyy;HH:mm:ss");
		l2=new JLabel(sdf.format(d));
		add(l1);
		add(tf1);
		add(l22);
		add(jp1);
		add(b3);
		add(b2);
	}
	public void actionPerformed(ActionEvent e1)
	{
		try
		{
			
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:bank_data");
			if(e1.getSource()==b1)
			{
				if((tf1.getText().equals(""))||(jp1.getText().equals("")))
				{
					JOptionPane.showMessageDialog(this,"Enter Fields VAlues");
					tf1.setText("");
					jp1.setText("");
					tf1.requestFocus(true);
				}
				else
				{
					tf1.setEditable(false);
					jp1.setEditable(false);
					int count=0;
					String s[]={"Date","AccNo","Type","Amount","Balance"};
					PreparedStatement ps=con.prepareStatement("select count (*) from record where Accountno=?");
					ps.setInt(1,Integer.parseInt(tf1.getText()));
					ResultSet rs=ps.executeQuery();
					if(rs.next())
					{
						count=rs.getInt(1);
					}
					JOptionPane.showMessageDialog(this,"Total Transaction:"+count);
					String s1[][]=new String[count][5];
					PreparedStatement ps1=con.prepareStatement("select * from record where Accountno=?");
					ps1.setInt(1,Integer.parseInt(tf1.getText()));
					ResultSet rs1=ps1.executeQuery();
					int i=0;
					while(rs1.next())
					{
						s1[i][0]=rs1.getString(1);
						s1[i][1]=rs1.getString(2);
						s1[i][2]=rs1.getString(3);
						s1[i][3]=rs1.getString(4);
						s1[i][4]=rs1.getString(5);
						i++;
					}
					JTable t1=new JTable(s1,s);
					int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
					int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
					JScrollPane sp=new JScrollPane(t1,v,h);
					sp.setBounds(300,300,700,250);
					add(sp);
					sp.setVisible(true);
				}
			}
			else if(e1.getSource()==b2)
			{
				dispose();
				new bank_project1();
			}
			else if(e1.getSource()==b3)
			{
				if((tf1.getText().equals(""))||(jp1.getText().equals("")))
				{
					JOptionPane.showMessageDialog(this,"Enter Fields VAlues");
					tf1.setText("");
					jp1.setText("");
					tf1.requestFocus(true);
				}
				else
				{
					PreparedStatement ps=con.prepareStatement("select * from bank_table where Accountno=? and password=?");
					ps.setInt(1,Integer.parseInt(tf1.getText()));
					ps.setString(2,jp1.getText());
					ResultSet rs=ps.executeQuery();
					if(rs.next())
					{
						JOptionPane.showMessageDialog(this,"Account Verified");
						b3.setVisible(false);
						tf1.setEditable(false);
						jp1.setEditable(false);
						add(b1);
						b1.setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(this,"Account number or Password is invalid");
						tf1.setText("");
						jp1.setText("");
						tf1.requestFocus(true);
					}
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println("Exception-:"+ex);
		}
	}
}
class delete extends JFrame implements ActionListener
{
	JLabel l;
	JLabel l1;
	JLabel l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22;
	JTextField tf1;
	JPasswordField jp1;
	JButton b1;
	JButton b2,b3;
	String name,date,address,contact,gender,religion,category,type;
	int bal;
	delete()
	{
		setLayout(null);
		setVisible(true);
		setSize(1370,730);
		getContentPane().setBackground(Color.cyan);
		Color c1=new Color(0,255,255);
		Font f2=new Font("Magneto",Font.ITALIC,30);
		ImageIcon i1=new ImageIcon("delete.jpg");
		ImageIcon i2=new ImageIcon("b20.gif");
		l=new JLabel(i1);
		l.setBounds(0,0,1370,730);
		l1=new JLabel("Enter Account number");
		l1.setForeground(c1);
		l1.setFont(f2);
		l1.setBounds(200,100,650,30);
		tf1=new JTextField();
		tf1.setBounds(900,120,100,30);
		l22=new JLabel("Enter Password");
		l22.setFont(f2);
		l22.setForeground(c1);
		l22.setBounds(200,150,650,30);
		jp1=new JPasswordField();
		jp1.setBounds(900,150,100,30);
		b1=new JButton("CHECK");
		b1.setBounds(1100,120,100,30);
		b1.addActionListener(this);
		b3=new JButton(i2);
		b3.setBounds(50,50,120,40);
		b3.addActionListener(this);
		l4=new JLabel("Name:");
		l4.setForeground(c1);
		l4.setFont(f2);
		l4.setBounds(300,200,300,30);
		l5=new JLabel();
		l5.setForeground(c1);
		l5.setFont(f2);
		l5.setBounds(650,200,400,30);
		l6=new JLabel("Date of Birth:");
		l6.setForeground(c1);
		l6.setFont(f2);
		l6.setBounds(300,250,300,30);
		l7=new JLabel();
		l7.setForeground(c1);
		l7.setFont(f2);
		l7.setBounds(650,250,400,30);
		l8=new JLabel("Address:");
		l8.setForeground(c1);
		l8.setFont(f2);
		l8.setBounds(300,300,300,30);
		l9=new JLabel();
		l9.setForeground(c1);
		l9.setFont(f2);
		l9.setBounds(650,300,720,30);
		l10=new JLabel("Contact number:");
		l10.setForeground(c1);
		l10.setFont(f2);
		l10.setBounds(300,350,300,30);
		l11=new JLabel();
		l11.setForeground(c1);
		l11.setFont(f2);
		l11.setBounds(650,350,400,30);
		l12=new JLabel("Gender:");
		l12.setForeground(c1);
		l12.setFont(f2);
		l12.setBounds(300,400,300,30);
		l13=new JLabel();
		l13.setForeground(c1);
		l13.setFont(f2);
		l13.setBounds(650,400,400,30);
		l14=new JLabel("Religion:");
		l14.setForeground(c1);
		l14.setFont(f2);
		l14.setBounds(300,450,300,30);
		l15=new JLabel();
		l15.setForeground(c1);
		l15.setFont(f2);
		l15.setBounds(650,450,400,30);
		l16=new JLabel("Category:");
		l16.setForeground(c1);
		l16.setFont(f2);
		l16.setBounds(300,500,300,30);
		l17=new JLabel();
		l17.setForeground(c1);
		l17.setFont(f2);
		l17.setBounds(650,500,400,30);
		l18=new JLabel("Type of Account:");
		l18.setForeground(c1);
		l18.setFont(f2);
		l18.setBounds(300,550,300,30);
		l19=new JLabel();
		l19.setForeground(c1);
		l19.setFont(f2);
		l19.setBounds(650,550,400,30);
		l20=new JLabel("Balance:");
		l20.setForeground(c1);
		l20.setFont(f2);
		l20.setBounds(300,600,300,30);
		l21=new JLabel();
		l21.setForeground(c1);
		l21.setFont(f2);
		l21.setBounds(650,600,400,30);
		b2=new JButton("SURE TO DELETE ACCOUNT");
		b2.setBounds(600,640,400,30);
		b2.addActionListener(this);
		add(l);
		l.add(l1);
		l.add(tf1);
		l.add(b1);
		l.add(b3);
		l.add(l22);
		l.add(jp1);
		tf1.requestFocus(true);
	}
	public void actionPerformed(ActionEvent e1)
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:bank_data");
			if(e1.getSource()==b1)
			{
				if((tf1.getText().equals(""))||(jp1.getText().equals("")))
				{
					JOptionPane.showMessageDialog(this,"Enter Field Values");
					tf1.setText("");
					jp1.setText("");
					tf1.requestFocus(true);
				}
				else
				{
					l.add(l4);
					l.add(l6);
					l.add(l8);
					l.add(l10);
					l.add(l12);
					l.add(l14);
					l.add(l16);
					l.add(l18);
					l.add(l20);
					l.add(l5);
					l.add(l7);
					l.add(l9);
					l.add(l11);
					l.add(l13);
					l.add(l15);
					l.add(l17);
					l.add(l19);
					l.add(l21);
					l4.setVisible(true);
					l6.setVisible(true);
					l8.setVisible(true);
					l10.setVisible(true);
					l12.setVisible(true);
					l14.setVisible(true);
					l16.setVisible(true);
					l18.setVisible(true);
					l20.setVisible(true);
					l5.setVisible(true);
					l7.setVisible(true);
					l9.setVisible(true);
					l11.setVisible(true);
					l13.setVisible(true);
					l15.setVisible(true);
					l17.setVisible(true);
					l19.setVisible(true);
					l21.setVisible(true);
					l.add(b2);
					b2.setVisible(true);
					PreparedStatement ps=con.prepareStatement("select * from bank_table where Accountno=? and password=?");
					ps.setInt(1,Integer.parseInt(tf1.getText()));
					ps.setString(2,jp1.getText());
					ResultSet rs=ps.executeQuery();
					if(rs.next())
					{
						tf1.setEditable(false);
						jp1.setEditable(false);
						JOptionPane.showMessageDialog(this,"Your Account details");
						name=rs.getString("namee");
						date=rs.getString("datebirth");
						address=rs.getString("Address");
						contact=rs.getString("Contactno");
						gender=rs.getString("Gender");
						religion=rs.getString("Religion");
						category=rs.getString("Category");
						type=rs.getString("acctype");
						bal=Integer.parseInt(rs.getString("Balance"));
						l5.setText(String.valueOf(name));
						l7.setText(String.valueOf(date));
						l9.setText(String.valueOf(address));
						l11.setText(String.valueOf(contact));
						l13.setText(String.valueOf(gender));
						l15.setText(String.valueOf(religion));
						l17.setText(String.valueOf(category));
						l19.setText(String.valueOf(type));
						l21.setText(String.valueOf(bal));
					}
					else
					{
						JOptionPane.showMessageDialog(this,"Your Account number or Password is invalid");
						tf1.setText("");
						jp1.setText("");
						tf1.requestFocus(true);
					}
				}
			}
			else if(e1.getSource()==b2)
			{
				int response=JOptionPane.showConfirmDialog(null,"Are you really want to delete your account?","Account Deletion",JOptionPane.YES_NO_OPTION);
				if(response==JOptionPane.YES_OPTION)
				{
					PreparedStatement ps1=con.prepareStatement("delete * from bank_table where Accountno=?");
					ps1.setInt(1,Integer.parseInt(tf1.getText()));
					ps1.executeUpdate();
					PreparedStatement ps2=con.prepareStatement("delete * from record where Accountno=?");
					ps2.setInt(1,Integer.parseInt(tf1.getText()));
					ps2.executeUpdate();
					JOptionPane.showMessageDialog(this,"Account has been deleted");
					dispose();
					new delete();
				}
				else if(response==JOptionPane.NO_OPTION)
				{
					dispose();
					new bank_project1();
				}
				else
				{
					dispose();
					new delete();
				}
			}
			else if(e1.getSource()==b3)
			{
				dispose();
				new bank_project1();
			}
		}
		catch(Exception ex)
		{
			System.out.println("Exception is"+ex);
		}
	}
}
class transfer extends JFrame implements ActionListener
{
	JLabel l;
	JLabel l1;
	JLabel l2;
	JLabel l3;
	JLabel l4;
	JLabel l5;
	JLabel l6,l22;
	JPasswordField jp1;
	JTextField tf1;
	JTextField tf2;
	JTextField tf3;
	JButton b1;
	JButton b2;
	JButton b3;
	JButton b4;
	//JButton b5;
	transfer()
	{
		setLayout(null);
		setSize(1370,730);
		setVisible(true);
		ImageIcon i1=new ImageIcon("transfer.jpg");
		Color c1=new Color(255,255,255);
		Font f2=new Font("Magneto",Font.ITALIC,30);
		l=new JLabel(i1);
		l.setBounds(0,0,1366,730);
		l1=new JLabel("Enter Account Number of Debitting person");
		l1.setForeground(c1);
		l1.setFont(f2);
		l1.setBounds(50,120,800,30);
		tf1=new JTextField();
		tf1.setBounds(900,120,100,30);
		l22=new JLabel("Password");
		l22.setFont(f2);
		l22.setForeground(c1);
		l22.setBounds(50,180,800,30);
		jp1=new JPasswordField();
		jp1.setBounds(900,180,100,30);
		l2=new JLabel("Enter Amount to be transferred");
		l2.setForeground(c1);
		l2.setFont(f2);
		l2.setBounds(50,180,800,30);
		tf2=new JTextField();
		tf2.setBounds(900,180,100,30);
		l3=new JLabel("Enter Account number of Crediting person");
		l3.setForeground(c1);
		l3.setFont(f2);
		l3.setBounds(50,240,800,30);
		tf3=new JTextField();
		tf3.setBounds(900,240,100,30);
		l4=new JLabel("Debitted");
		l5=new JLabel("Credited");
		b1=new JButton("OK");
		b1.setBounds(400,400,100,30);
		b1.addActionListener(this);
		b2=new JButton("VIEW");
		b2.setBounds(400,400,100,30);
		b2.addActionListener(this);
		b3=new JButton("EXIT");
		b3.setBounds(800,400,100,30);
		b3.addActionListener(this);
		b4=new JButton("CHECK");
		b4.setBounds(400,400,100,30);
		b4.addActionListener(this);
		//b5=new JButton("RECORD");
		//b5.setBounds(600,550,100,30);
		//b5.addActionListener(this);
		Date d=Calendar.getInstance().getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("dd:MM:yyyy;HH:mm:ss");
		l6=new JLabel(sdf.format(d));
		add(l);
		l.add(l1);
		l.add(l22);
		l.add(jp1);
		//l.add(l2);
		l.add(l3);
		l.add(tf1);
		//l.add(tf2);
		l.add(tf3);
		l.add(b4);
		l.add(b3);
		tf1.requestFocus(true);
	}
	public void actionPerformed(ActionEvent e1)
	{
		try
		{
			int amt=0,bal=0,bal1=0;
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:bank_data");
			if(e1.getSource()==b1)
			{
				if((tf2.getText().equals("")))
				{
					JOptionPane.showMessageDialog(this,"Enter Amount");
					tf2.requestFocus(true);
				}
				else
				{
					PreparedStatement ps=con.prepareStatement("select * from bank_table where Accountno=?");
					ps.setInt(1,Integer.parseInt(tf1.getText()));
					ResultSet rs=ps.executeQuery();
					if(rs.next())
					{
						bal=Integer.parseInt(rs.getString("Balance"));
					}
					
					System.out.println(""+bal);
					amt=Integer.parseInt(tf2.getText());
					if(bal<amt+1000)
					{
						JOptionPane.showMessageDialog(this,"Tranfer of money is not possible");
						JOptionPane.showMessageDialog(this,"You have Rs."+bal+" and you can only transfer Rs."+(bal-1000));
						//tf1.setText("");
						tf2.setText("");
						//tf3.setText("");
						tf2.requestFocus(true);
					}
					else
					{
						bal=bal-amt;
						System.out.println(""+bal);
						System.out.println(""+amt);
						PreparedStatement ps1=con.prepareStatement("update bank_table set Balance=? where Accountno=?");
						ps1.setInt(1,bal);
						ps1.setInt(2,Integer.parseInt(tf1.getText()));
						ps1.executeUpdate();
						PreparedStatement ps4=con.prepareStatement("insert into record values(?,?,?,?,?)");
						ps4.setString(1,l6.getText());
						ps4.setInt(2,Integer.parseInt(tf1.getText()));
						ps4.setString(3,l4.getText());
						ps4.setInt(4,Integer.parseInt(tf2.getText()));
						ps4.setInt(5,bal);
						ps4.executeUpdate();
			
						//TRANSFERING CODE
						
						PreparedStatement ps2=con.prepareStatement("select * from bank_table where Accountno=?");
						ps2.setInt(1,Integer.parseInt(tf3.getText()));
						ResultSet rs1=ps2.executeQuery();
						if(rs1.next())
						{
							bal1=Integer.parseInt(rs1.getString("Balance"));
						}
						System.out.println(""+bal1);
						bal1=bal1+amt;
						System.out.println(""+bal1);
						PreparedStatement ps3=con.prepareStatement("update bank_table set Balance=? where Accountno=?");
						ps3.setInt(1,bal1);
						ps3.setInt(2,Integer.parseInt(tf3.getText()));
						ps3.executeUpdate();
						System.out.println(""+bal1);
						PreparedStatement ps5=con.prepareStatement("insert into record values(?,?,?,?,?)");
						ps5.setString(1,l6.getText());
						ps5.setInt(2,Integer.parseInt(tf3.getText()));
						ps5.setString(3,l5.getText());
						ps5.setInt(4,Integer.parseInt(tf2.getText()));
						ps5.setInt(5,bal1);
						ps5.executeUpdate();
						tf1.setEditable(false);
						tf2.setEditable(false);
						tf3.setEditable(false);
						b1.setVisible(false);
						l.add(b2);
						b2.setVisible(true);
						JOptionPane.showMessageDialog(this,"Amount Transferered");
						JOptionPane.showMessageDialog(this,"Your records has been saved successfully");
					}
				}
			}
			else if(e1.getSource()==b2)
			{
				dispose();
				new acc();
			}
			else if(e1.getSource()==b3)
			{
				dispose();
				new bank_project1();
			}
			else if(e1.getSource()==b4)
			{
				if((tf1.getText().equals(""))||(tf3.getText().equals(""))||(jp1.getText().equals("")))
				{
					JOptionPane.showMessageDialog(this,"Fields can't be empty");
					tf1.setText("");
					tf2.setText("");
					jp1.setText("");
					tf1.requestFocus(true);
				}
				else
				{
					PreparedStatement ps=con.prepareStatement("select * from bank_table where Accountno=? and password=?");
					ps.setInt(1,Integer.parseInt(tf1.getText()));
					ps.setString(2,jp1.getText());
					ResultSet rs=ps.executeQuery();
					if(rs.next())
					{
						PreparedStatement ps1=con.prepareStatement("select * from bank_table where Accountno=?");
						ps1.setInt(1,Integer.parseInt(tf3.getText()));
						ResultSet rs1=ps1.executeQuery();
						if(rs1.next())
						{
							JOptionPane.showMessageDialog(this,"Account Numbers "+tf1.getText()+" and"+tf3.getText()+" exists");
							JOptionPane.showMessageDialog(this,"Enter Amount to be transferred");
							b4.setVisible(false);
							l22.setVisible(false);
							jp1.setVisible(false);
							l.add(b1);
							l.add(tf2);
							l.add(l2);
							b1.setVisible(true);
							l2.setVisible(true);
							tf2.setVisible(true);
							tf1.setEditable(false);
							tf3.setEditable(false);
							tf2.requestFocus(true);
						}
						else
						{
							JOptionPane.showMessageDialog(this,"Account Numbers "+tf3.getText()+" doesn't exist");
							tf1.setText("");
							jp1.setText("");
							tf3.setText("");
							tf1.requestFocus(true);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(this,"Account Numbers "+tf1.getText()+" doesn't exist");
						tf1.setText("");
						jp1.setText("");
						tf3.setText("");
						tf1.requestFocus(true);
					}
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(""+ex);
		}
	}
}
class lovely extends JFrame implements ActionListener
{
	JButton b1;
	lovely()
	{
		setLayout(null);
		setSize(1200,830);
		setVisible(true);
		ImageIcon i1=new ImageIcon("lpu9.jpg");
		b1=new JButton(i1);
		b1.setBounds(10,10,1198,827);
		b1.addActionListener(this);
		add(b1);
	}
	public void actionPerformed(ActionEvent e2)
	{
		if(e2.getSource()==b1)
		{
			dispose();
			new bank_p();
		}
	}
}
class create extends JFrame implements ActionListener,ItemListener
{
	JLabel l;
	JLabel l1;
	JLabel l2;
	JLabel l3;
	JLabel l4;
	JLabel l5;
	JLabel l6;
	JLabel l7;
	JLabel l8;
	JLabel l9;
	JLabel l10;
	JLabel l11;
	JTextField tf1;
	JTextField tf2;
	JTextField tf3;
	//JTextField tf4;
	//JTextField tf5;
	//JTextField tf6;
	//JTextField tf7;
	JTextField tf8;
	JTextField ta1;
	JLabel l12,l13,l14,l15;
	JPasswordField jp1;
	JLabel ldate;
	JLabel lcredit;
	JButton b1;
	JButton b2;
	JButton b4;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	JComboBox cb,cb1,cb2,cb3;
	create()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			System.out.println("driver loded successfully");
			con=DriverManager.getConnection("jdbc:odbc:bank_data");
			System.out.println("database connected successfully");
		}
		catch(Exception ex1)
		{
			System.out.println("Exception name"+ex1);
		}
		setLayout(null);
		setSize(1360,730);
		setVisible(true);
		Font f2=new Font("Magneto",Font.ITALIC,30);
		Color c1=new Color(255,255,255);
		Color c2=new Color(0,0,255);
		ImageIcon i=new ImageIcon("create.jpg");
		l=new JLabel(i);
		l.setBounds(0,0,1360,730);
		Font f1=new Font("Bodoni MT Black",Font.BOLD,50);
		l1=new JLabel("PERSONAL DETAILS");
		l1.setForeground(c1);
		l1.setFont(f1);
		l1.setBounds(400,50,1300,50);
		l2=new JLabel("Name");
		l2.setFont(f2);
		l2.setForeground(c2);
		l2.setBounds(450,150,300,30);
		tf1=new JTextField();
		tf1.setBounds(800,150,200,30);
		l3=new JLabel("Date of Birth");
		l3.setFont(f2);
		l3.setForeground(c2);
		l3.setBounds(450,200,300,30);
		tf2=new JTextField();
		tf2.setBounds(800,200,200,30);
		l4=new JLabel("Address");
		l4.setFont(f2);
		l4.setForeground(c2);
		l4.setBounds(450,250,300,30);
		ta1=new JTextField();
		ta1.setBounds(800,250,200,30);
		l5=new JLabel("Contact Number");
		l5.setFont(f2);
		l5.setForeground(c2);
		l5.setBounds(450,300,300,30);
		tf3=new JTextField();
		tf3.setBounds(800,300,200,30);
		l6=new JLabel("Gender");
		l6.setFont(f2);
		l6.setForeground(c2);
		l6.setBounds(450,350,300,30);
		//tf4=new JTextField();
		//tf4.setBounds(800,350,200,30);
		String ssss[]={"","Male","Female"};
		cb3=new JComboBox(ssss);
		cb3.setBounds(800,350,200,30);
		cb3.addItemListener(this);
		add(cb3);
		l15=new JLabel();
		l7=new JLabel("Religion");
		l7.setFont(f2);
		l7.setForeground(c2);
		l7.setBounds(450,400,300,30);
		//tf5=new JTextField();
		//tf5.setBounds(800,400,200,30);
		String sss[]={"","Hindu","Muslim","Sikh"};
		cb2=new JComboBox(sss);
		cb2.setBounds(800,400,200,30);
		cb2.addItemListener(this);
		add(cb2);
		l14=new JLabel();
		l8=new JLabel("Category");
		l8.setFont(f2);
		l8.setForeground(c2);
		l8.setBounds(450,450,300,30);
		//tf6=new JTextField();
		//tf6.setBounds(800,450,200,30);
		String ss[]={"","General","BC","OBC","SC","ST"};
		cb1=new JComboBox(ss);
		cb1.setBounds(800,450,200,30);
		cb1.addItemListener(this);
		add(cb1);
		l13=new JLabel();
		l9=new JLabel("Type of Account");
		l9.setFont(f2);
		l9.setForeground(c2);
		l9.setBounds(450,500,300,30);
		//tf7=new JTextField();
		//tf7.setBounds(800,500,200,30);
		String s[]={"","Savings","Current","Children","Seniors"};
		cb=new JComboBox(s);
		cb.setBounds(800,500,200,30);
		cb.addItemListener(this);
		add(cb);
		l10=new JLabel("Balance");
		l10.setFont(f2);
		l10.setForeground(c2);
		l10.setBounds(450,550,300,30);
		tf8=new JTextField();
		tf8.setBounds(800,550,200,30);
		l11=new JLabel("Password");
		l11.setFont(f2);
		l11.setForeground(c2);
		l11.setBounds(450,600,300,30);
		jp1=new JPasswordField();
		jp1.setBounds(800,600,200,30);
		b1=new JButton("Exit");
		b1.setBounds(100,650,80,30);
		b1.addActionListener(this);
		ImageIcon i3=new ImageIcon("submit.jpg");
		b2=new JButton(i3);
		b2.setBounds(1100,650,200,50);
		b2.addActionListener(this);
		ImageIcon i4=new ImageIcon("b20.gif");
		b4=new JButton(i4);
		b4.setBounds(20,20,120,40);
		b4.addActionListener(this);
		l12=new JLabel();
		Date d=Calendar.getInstance().getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("dd:MM:yyyy;HH:mm:ss");
		ldate=new JLabel(sdf.format(d));
		lcredit=new JLabel("Credited");
		add(l);
		l.add(l1);
		l.add(l2);
		l.add(l3);
		l.add(l4);
		l.add(l5);
		l.add(l6);
		l.add(l7);
		l.add(l8);
		l.add(l9);
		l.add(l10);
		l.add(l11);
		l.add(ta1);
		l.add(tf1);
		l.add(tf2);
		l.add(tf3);
		//l.add(tf4);
		//l.add(tf5);
		//l.add(tf6);
		//l.add(tf7);
		l.add(tf8);
		l.add(jp1);
		l.add(b1);
		l.add(b2);
		l.add(b4);
		tf1.requestFocus(true);
	}
	public void actionPerformed(ActionEvent e1)
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			System.out.println("driver loded successfully");
			con=DriverManager.getConnection("jdbc:odbc:bank_data");
			System.out.println("database connected successfully");
			int accno=10000;
			if(e1.getSource()==b2)
			{
				if((tf1.getText().equals(""))||(ta1.getText().equals(""))||(tf2.getText().equals(""))||(tf3.getText().equals(""))||(l12.getText().equals(""))||(l13.getText().equals(""))||(l14.getText().equals(""))||(l15.getText().equals(""))||(tf8.getText().equals("")))
				{
					JOptionPane.showMessageDialog(this,"Enter every details please");
					tf1.setText("");
					tf2.setText("");
					ta1.setText("");
					tf3.setText("");
					//tf4.setText("");
					//tf5.setText("");
					//tf6.setText("");
					//tf7.setText("");
					cb.setSelectedIndex(0);
					cb1.setSelectedIndex(0);
					cb2.setSelectedIndex(0);
					cb3.setSelectedIndex(0);
					tf8.setText("");
					jp1.setText("");
					tf1.requestFocus(true);
				}
				else
				{
					ps=con.prepareStatement("select max(Accountno) from bank_table");
					ResultSet rs=ps.executeQuery();
					while(rs.next())
					{
						accno=rs.getInt(1);
					}
					accno++;
					String msg="Your Account number is"+accno;
					PreparedStatement ps1=con.prepareStatement("insert into bank_table values(?,?,?,?,?,?,?,?,?,?,?)");
					ps1.setInt(1,accno);
					ps1.setString(2,tf1.getText());
					ps1.setString(3,tf2.getText());
					ps1.setString(4,ta1.getText());
					ps1.setString(5,tf3.getText());
					ps1.setString(6,l15.getText());
					ps1.setString(7,l14.getText());
					ps1.setString(8,l13.getText());
					ps1.setString(9,l12.getText());
					ps1.setInt(10,Integer.parseInt(tf8.getText()));
					ps1.setString(11,jp1.getText());
					ps1.executeUpdate();
					JOptionPane.showMessageDialog(this,"Congratulations your account information has been successfully saved!");
					JOptionPane.showMessageDialog(this,msg);
					JOptionPane.showMessageDialog(this,"HAPPY BANKING");
					PreparedStatement ps2=con.prepareStatement("insert into record values(?,?,?,?,?)");
					ps2.setString(1,ldate.getText());
					ps2.setInt(2,accno);
					ps2.setString(3,lcredit.getText());
					ps2.setInt(4,Integer.parseInt(tf8.getText()));
					ps2.setInt(5,Integer.parseInt(tf8.getText()));
					ps2.executeUpdate();
					System.out.println("Record saved");
					
					tf1.setText("");
					tf2.setText("");
					ta1.setText("");
					tf3.setText("");
					cb1.setSelectedIndex(0);
					//tf4.setText("");
					//tf5.setText("");
					//tf6.setText("");
					//tf7.setText("");
					cb.setSelectedIndex(0);
					cb1.setSelectedIndex(0);
					cb2.setSelectedIndex(0);
					cb3.setSelectedIndex(0);
					tf8.setText("");
					jp1.setText("");
					tf1.requestFocus(true);
				}
			}
			else if(e1.getSource()==b1)
			{
				dispose();
			}
			else if(e1.getSource()==b4)
			{	
				dispose();
				new bank_project1();
			}
		}
		catch(Exception ex2)
		{
			System.out.println("Exception is"+ex2);
		}
	}
	public void itemStateChanged(ItemEvent e)
	{
		String s1=(String)cb.getSelectedItem();
		l12.setText(s1);
		String s2=(String)cb1.getSelectedItem();
		l13.setText(s2);
		String s3=(String)cb2.getSelectedItem();
		l14.setText(s3);
		String s4=(String)cb3.getSelectedItem();
		l15.setText(s4);
		
	}
}
class trans extends JFrame implements ActionListener
{
	JLabel l1;
	JButton b1;
	JButton b2;
	JButton b3;
	JButton b4;
	trans()
	{
		setLayout(null);
		setSize(1360,730);
		setVisible(true);
		getContentPane().setBackground(Color.blue);
		Color c1=new Color(0,255,255);
		l1=new JLabel("TRANSACTION FACILITIES");
		Font f1=new Font("Berlin Sans FB Demi",Font.BOLD,100);
		l1.setFont(f1);
		l1.setForeground(c1);
		l1.setBounds(100,80,1260,100);
		Font f2=new Font("Arial Black",Font.ITALIC,30);
		b1=new JButton("DEPOSIT");
		b1.setFont(f2);
		b1.setBounds(450,270,500,50);
		b1.addActionListener(this);
		b2=new JButton("WITHDRAW");
		b2.setFont(f2);
		b2.setBounds(450,350,500,50);
		b2.addActionListener(this);
		b3=new JButton("VIEW ACCOUNT");
		b3.setFont(f2);
		b3.setBounds(450,430,500,50);
		b3.addActionListener(this);
		b4=new JButton("BACK");
		b4.setFont(f2);
		b4.setBounds(450,510,500,50);
		b4.addActionListener(this);
		add(l1);
		add(b1);
		add(b2);
		add(b3);
		add(b4);
	}
	public void actionPerformed(ActionEvent e6)
	{
		if(e6.getSource()==b1)
		{
			dispose();
			new deposit();
		}
		else if(e6.getSource()==b2)
		{
			dispose();
			new withdraw();
		}
		else if(e6.getSource()==b3)
		{
			dispose();
			new acc();
		}
		else
		{
			dispose();
			new bank_project1();
		}
	}
}
class acc extends JFrame implements ActionListener
{
	JLabel l1;
	JLabel l2;
	JLabel l4;
	JLabel l5;
	JLabel l6;
	JLabel l7;
	JLabel l8;
	JLabel l9;
	JLabel l10;
	JLabel l11;
	JLabel l12;
	JLabel l13;
	JLabel l14;
	JLabel l15;
	JLabel l16;
	JLabel l17;
	JLabel l18;
	JLabel l19;
	JLabel l20;
	JLabel l21;
	JLabel l22,l23;
	JPasswordField jp1,jp2;
	JTextField tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8,tf9,tf10;
	JButton b1;
	JButton b2;
	JButton b3;
	JButton b4;
	JButton b5;
	String name,date,address,contact,gender,religion,category,type,pass;
	int bal;
	acc()
	{
		setLayout(null);
		setSize(1370,730);
		setVisible(true);
		Font f1=new Font("Bauhaus 93",Font.ITALIC,80);
		Font f2=new Font("Magneto",Font.ITALIC,30);
		Font f3=new Font("Bauhaus 93",Font.ITALIC,50);
		getContentPane().setBackground(Color.cyan);
		//Color c1=new Color(255,200,0);
		Color c2=new Color(255,255,255);
		l1=new JLabel("ACCOUNT DETAILS");
		l1.setForeground(c2);
		l1.setFont(f1);
		l1.setBounds(300,20,800,80);
		l2=new JLabel("Account number");
		l2.setFont(f2);
		l2.setBounds(200,120,300,30);
		tf1=new JTextField();
		tf1.setBounds(520,120,100,30);
		b1=new JButton("VIEW");
		b1.setBounds(700,120,100,30);
		b1.addActionListener(this);
		b2=new JButton("UPDATE");
		b2.setBounds(900,120,100,30);
		b2.addActionListener(this);
		b3=new JButton("SAVE");
		b3.setBounds(900,120,100,30);
		b3.addActionListener(this);
		ImageIcon i1=new ImageIcon("b20.gif");
		b4=new JButton(i1);
		b4.setBounds(50,50,120,40);
		b4.addActionListener(this);
		b5=new JButton("CLEAR");
		b5.setBounds(700,120,100,30);
		b5.addActionListener(this);
		l4=new JLabel("Name:");
		l4.setFont(f2);
		l4.setBounds(300,200,300,30);
		l5=new JLabel();
		l5.setFont(f2);
		l5.setBounds(650,200,400,30);
		tf2=new JTextField();
		tf2.setBounds(650,200,400,30);
		l6=new JLabel("Date of Birth:");
		l6.setFont(f2);
		l6.setBounds(300,250,300,30);
		l7=new JLabel();
		l7.setFont(f2);
		l7.setBounds(650,250,400,30);
		tf3=new JTextField();
		tf3.setBounds(650,250,400,30);
		l8=new JLabel("Address:");
		l8.setFont(f2);
		l8.setBounds(300,300,300,30);
		l9=new JLabel();
		l9.setFont(f2);
		l9.setBounds(650,300,720,30);
		tf4=new JTextField();
		tf4.setBounds(650,300,400,30);
		l10=new JLabel("Contact number:");
		l10.setFont(f2);
		l10.setBounds(300,350,300,30);
		l11=new JLabel();
		l11.setFont(f2);
		l11.setBounds(650,350,400,30);
		tf5=new JTextField();
		tf5.setBounds(650,350,400,30);
		l12=new JLabel("Gender:");
		l12.setFont(f2);
		l12.setBounds(300,400,300,30);
		l13=new JLabel();
		l13.setFont(f2);
		l13.setBounds(650,400,400,30);
		tf6=new JTextField();
		tf6.setBounds(650,400,400,30);
		l14=new JLabel("Religion:");
		l14.setFont(f2);
		l14.setBounds(300,450,300,30);
		l15=new JLabel();
		l15.setFont(f2);
		l15.setBounds(650,450,400,30);
		tf7=new JTextField();
		tf7.setBounds(650,450,400,30);
		l16=new JLabel("Category:");
		l16.setFont(f2);
		l16.setBounds(300,500,300,30);
		l17=new JLabel();
		l17.setFont(f2);
		l17.setBounds(650,500,400,30);
		tf8=new JTextField();
		tf8.setBounds(650,500,400,30);
		l18=new JLabel("Type of Account:");
		l18.setFont(f2);
		l18.setBounds(300,550,300,30);
		l19=new JLabel();
		l19.setFont(f2);
		l19.setBounds(650,550,400,30);
		tf9=new JTextField();
		tf9.setBounds(650,550,400,30);
		l20=new JLabel("Balance:");
		l20.setFont(f2);
		l20.setBounds(300,600,300,30);
		l21=new JLabel();
		l21.setFont(f2);
		l21.setBounds(650,600,400,30);
		tf10=new JTextField();
		tf10.setBounds(650,600,400,30);
		l22=new JLabel("Enter Password");
		l22.setFont(f2);
		//l22.setForeground(c2);
		l22.setBounds(200,160,300,30);
		jp1=new JPasswordField();
		jp1.setBounds(520,160,100,30);
		l23=new JLabel("Password");
		l23.setFont(f2);
		l23.setBounds(300,650,300,30);
		jp2=new JPasswordField();
		jp2.setBounds(650,650,400,30);
		add(l1);
		add(l2);
		add(tf1);
		add(b1);
		add(b4);
		add(l22);
		add(jp1);
		tf1.requestFocus(true);
	}
	public void actionPerformed(ActionEvent e1)
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:bank_data");
			if(e1.getSource()==b1)
			{
				if((tf1.getText().equals(""))||(jp1.getText().equals("")))
				{
					JOptionPane.showMessageDialog(this,"Enter Fields Values");
					tf1.setText("");
					jp1.setText("");
					tf1.requestFocus(true);
				}
				else
				{
					add(l4);
					add(l6);
					add(l8);
					add(l10);
					add(l12);
					add(l14);
					add(l16);
					add(l18);
					add(l20);
					l4.setVisible(true);
					l6.setVisible(true);
					l8.setVisible(true);
					l10.setVisible(true);
					l12.setVisible(true);
					l14.setVisible(true);
					l16.setVisible(true);
					l18.setVisible(true);
					l20.setVisible(true);
					add(l5);
					add(l7);
					add(l9);
					add(l11);
					add(l13);
					add(l15);
					add(l17);
					add(l19);
					add(l21);
					l5.setVisible(true);
					l7.setVisible(true);
					l9.setVisible(true);
					l11.setVisible(true);
					l13.setVisible(true);
					l15.setVisible(true);
					l17.setVisible(true);
					l19.setVisible(true);
					l21.setVisible(true);
					PreparedStatement ps=con.prepareStatement("select * from bank_table where Accountno=? and password=?");
					ps.setInt(1,Integer.parseInt(tf1.getText()));
					ps.setString(2,jp1.getText());
					ResultSet rs=ps.executeQuery();
					if(rs.next())
					{
						JOptionPane.showMessageDialog(this,"Your ACCOUNT Details");
						name=rs.getString("namee");
						date=rs.getString("datebirth");
						address=rs.getString("Address");
						contact=rs.getString("Contactno");
						gender=rs.getString("Gender");
						religion=rs.getString("Religion");
						category=rs.getString("Category");
						type=rs.getString("acctype");
						bal=Integer.parseInt(rs.getString("Balance"));
						l5.setText(String.valueOf(name));
						l7.setText(String.valueOf(date));
						l9.setText(String.valueOf(address));
						l11.setText(String.valueOf(contact));
						l13.setText(String.valueOf(gender));
						l15.setText(String.valueOf(religion));
						l17.setText(String.valueOf(category));
						l19.setText(String.valueOf(type));
						l21.setText(String.valueOf(bal));
						b1.setVisible(false);
						add(b2);
						add(b5);
						b5.setVisible(true);
						b2.setVisible(true);
						tf1.setEditable(false);
						jp1.setEditable(false);
					}
					else
					{
						JOptionPane.showMessageDialog(this,"Your Account number or password is invalid");
						tf1.setText("");
						jp1.setText("");
						tf1.requestFocus(true);
					}
				}
			}
			else if(e1.getSource()==b2)
			{
				l5.setVisible(false);
				l7.setVisible(false);
				l9.setVisible(false);
				l11.setVisible(false);
				l13.setVisible(false);
				l15.setVisible(false);
				l17.setVisible(false);
				l19.setVisible(false);
				l21.setVisible(false);
				l23.setVisible(true);
				add(l4);
				add(l6);
				add(l8);
				add(l10);
				add(l12);
				add(l14);
				add(l16);
				add(l18);
				add(l20);
				add(l23);
				l4.setVisible(true);
				l6.setVisible(true);
				l8.setVisible(true);
				l10.setVisible(true);
				l12.setVisible(true);
				l14.setVisible(true);
				l16.setVisible(true);
				l18.setVisible(true);
				l20.setVisible(true);
				add(tf2);
				add(tf3);
				add(tf4);
				add(tf5);
				add(tf6);
				add(tf7);
				add(tf8);
				add(tf9);
				add(tf10);
				add(jp2);
				jp2.setVisible(true);
				tf2.setVisible(true);
				tf3.setVisible(true);
				tf4.setVisible(true);
				tf5.setVisible(true);
				tf6.setVisible(true);
				tf7.setVisible(true);
				tf8.setVisible(true);
				tf9.setVisible(true);
				tf10.setVisible(true);
				PreparedStatement ps=con.prepareStatement("select * from bank_table where Accountno=? and password=?");
				ps.setInt(1,Integer.parseInt(tf1.getText()));
				ps.setString(2,jp1.getText());
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					name=rs.getString("namee");
					date=rs.getString("datebirth");
					address=rs.getString("Address");
					contact=rs.getString("Contactno");
					gender=rs.getString("Gender");
					religion=rs.getString("Religion");
					category=rs.getString("Category");
					type=rs.getString("acctype");
					bal=Integer.parseInt(rs.getString("Balance"));
					pass=rs.getString("password");
				}
				tf2.setEditable(false);
				tf10.setEditable(false);
				tf3.setEditable(false);
				tf6.setEditable(false);
				tf8.setEditable(false);
				tf2.setText(String.valueOf(name));
				tf3.setText(String.valueOf(date));
				tf4.setText(String.valueOf(address));
				tf5.setText(String.valueOf(contact));
				tf6.setText(String.valueOf(gender));
				tf7.setText(String.valueOf(religion));
				tf8.setText(String.valueOf(category));
				tf9.setText(String.valueOf(type));
				tf10.setText(String.valueOf(bal));
				jp2.setText(String.valueOf(pass));
				b2.setVisible(false);
				b5.setVisible(false);
				add(b3);
				b3.setVisible(true);
			}
		else if(e1.getSource()==b3)
		{
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//System.out.println("loaded");
			//Connection con1=DriverManager.getConnection("jdbc:odbc:bank_data");
			//System.out.println("connected loaded");
			PreparedStatement ps1=con.prepareStatement("update bank_table set Address=?,Contactno=?,Religion=?,acctype=?,password=? where Accountno=?");
			ps1.setString(1,tf4.getText());
			ps1.setString(2,tf5.getText());
			ps1.setString(3,tf7.getText());
			ps1.setString(4,tf9.getText());
			ps1.setString(5,jp2.getText());
			ps1.setInt(6,Integer.parseInt(tf1.getText()));
			ps1.executeUpdate();
			JOptionPane.showMessageDialog(this,"Your Account details has been successfully updated");
			dispose();
			new acc();
		}
		else if(e1.getSource()==b4)
		{
			dispose();
			new trans();
		}
		else if(e1.getSource()==b5)
		{
			dispose();
			new acc();
		}
		}
		catch(Exception ex)
		{
			System.out.println("Exception is"+ex);
		}
	}
}
class deposit extends JFrame implements ActionListener
{
	JLabel l;
	JLabel l1;
	JLabel l2;
	JLabel l3;
	JLabel l4;
	JLabel l5;
	JLabel l6;
	JLabel l7;
	JLabel l8;
	JLabel l9;
	JLabel l10;
	JLabel l11;
	JLabel l12;
	JLabel l13;
	JButton b1;
	JButton b2;
	JButton b3;
	JButton b4;
	JPasswordField jp1;
	JLabel l22;
	JTextField tf1;
	JTextField tf2;
	String name,type;
	deposit()
	{
		setLayout(null);
		setSize(1360,730);
		setVisible(true);
		getContentPane().setBackground(Color.cyan);
		ImageIcon i1=new ImageIcon("deposit.gif");
		Font f3=new Font("Magneto",Font.ITALIC,30);
		l13=new JLabel("Credited");
		l13.setBounds(10,10,100,20);
		//add(l13);
		l=new JLabel(i1);
		l.setBounds(1000,100,300,225);
		l1=new JLabel("CASH DEPOSIT");
		l1.setForeground(Color.blue);
		Font f1=new Font("Berlin Sans FB Demi",Font.BOLD,50);
		l1.setFont(f1);
		l1.setBounds(500,50,1000,50);
		l2=new JLabel("Enter your Account Number");
		l2.setFont(f3);
		l2.setBounds(200,120,500,30);
		tf1=new JTextField();
		tf1.setBounds(750,120,100,30);
		l22=new JLabel("Enter Password");
		l22.setFont(f3);
		//l11.setForeground(c2);
		l22.setBounds(200,170,500,30);
		jp1=new JPasswordField();
		jp1.setBounds(750,170,100,30);
		b4=new JButton("VERIFY");
		b4.setBounds(650,220,100,30);
		b4.addActionListener(this);
		l3=new JLabel("Enter Amount to be deposit");
		l3.setFont(f3);
		l3.setBounds(200,170,500,30);
		tf2=new JTextField();
		tf2.setBounds(750,170,100,30);
		Font f2=new Font("Blackadder ITC",Font.ITALIC,30);
		b1=new JButton("OK");
		b1.setBounds(650,220,100,30);
		b1.addActionListener(this);
		l8=new JLabel("Account Holder Name");
		l8.setFont(f3);
		l8.setBounds(100,300,600,30);
		l9=new JLabel();
		l9.setFont(f3);
		l9.setBounds(750,300,400,30);
		l4=new JLabel("Your previous Account balance was:");
		l4.setFont(f3);
		l4.setBounds(100,350,600,30);
		l5=new JLabel("Your current Account balance is:");
		l5.setFont(f3);
		l5.setBounds(100,400,600,30);
		l6=new JLabel();
		l6.setFont(f3);
		l6.setBounds(750,350,400,30);
		l7=new JLabel();
		l7.setFont(f3);
		l7.setBounds(750,400,400,30);
		l10=new JLabel("Type of Account");
		l10.setFont(f3);
		l10.setBounds(100,450,600,30);
		l11=new JLabel();
		l11.setFont(f3);
		l11.setBounds(750,450,400,30);
		ImageIcon i2=new ImageIcon("b20.gif");
		b2=new JButton(i2);
		//b2.setFont(f2);
		b2.setBounds(30,20,150,40);
		b2.addActionListener(this);
		b3=new JButton("RECORD");
		b3.setBounds(1200,600,100,40);
		b3.addActionListener(this);
		Date d1=Calendar.getInstance().getTime();
		SimpleDateFormat sdf1=new SimpleDateFormat("dd:MM:yyyy;HH:mm:ss");
		l12=new JLabel(sdf1.format(d1));
		add(l);
		add(l1);
		add(l2);
		//add(l3);
		add(tf1);
		//add(tf2);
		//add(l4);
		//add(l5);
		//add(l6);
		//add(l7);
		//add(l8);
		//add(l9);
		//add(l10);
		//add(l11);
		//add(b1);
		add(b2);
		//add(b3);
		add(b4);
		add(l22);
		add(jp1);
		tf1.requestFocus(true);
	}
	public void actionPerformed(ActionEvent e1)
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			System.out.println("Load");
			Connection con=DriverManager.getConnection("jdbc:odbc:bank_data");
			System.out.println("connectedoad");
			int pbal=0,dp,cbal;
			if(e1.getSource()==b1)
			{
				if((tf2.getText().equals("")))
				{
					JOptionPane.showMessageDialog(this,"Enter the Fields First");
					//tf1.setText("");
					tf2.setText("");
					tf2.requestFocus(true);
				}
				else
				{
					add(l4);
					add(l5);
					add(l6);
					add(l7);
					add(l8);
					add(l9);
					add(l10);
					add(l11);
					l4.setVisible(true);
					l5.setVisible(true);
					l6.setVisible(true);
					l7.setVisible(true);
					l8.setVisible(true);
					l9.setVisible(true);
					l10.setVisible(true);
					l11.setVisible(true);
					PreparedStatement ps=con.prepareStatement("select * from bank_table where Accountno=?");
					ps.setInt(1,Integer.parseInt(tf1.getText()));
					ResultSet rs=ps.executeQuery();
					if(rs.next())
					{
						name=rs.getString("namee");
						pbal=Integer.parseInt(rs.getString("Balance"));
						type=rs.getString("acctype");
						dp=Integer.parseInt(tf2.getText());
						if(dp>50000)
						{
							JOptionPane.showMessageDialog(this,"You can't deposit more than Rs.50,000");
							tf2.setText("");
							tf2.requestFocus(true);
						}
						else
						{
							cbal=pbal+dp;
							PreparedStatement ps1=con.prepareStatement("update bank_table set Balance=? where Accountno=?");
							ps1.setInt(1,cbal);
							ps1.setInt(2,Integer.parseInt(tf1.getText()));
							ps1.executeUpdate();
							l9.setText(String.valueOf(name));
							l6.setText(String.valueOf(pbal));
							l7.setText(String.valueOf(cbal));
							l11.setText(String.valueOf(type));
							JOptionPane.showMessageDialog(this,"Amount has been deposited");
							PreparedStatement ps2=con.prepareStatement("insert into record values(?,?,?,?,?)");
							ps2.setString(1,l12.getText());
							ps2.setInt(2,Integer.parseInt(tf1.getText()));
							ps2.setString(3,l13.getText());
							ps2.setInt(4,Integer.parseInt(tf2.getText()));
							ps2.setInt(5,Integer.parseInt(l7.getText()));
							ps2.executeUpdate();
							JOptionPane.showMessageDialog(this,"Your record has been successfully saved");
						}
					}
					else 
					{
						JOptionPane.showMessageDialog(this,"Please enter a valid Account number");
					}
				}
			}
			else if(e1.getSource()==b2)
			{
				dispose();
				new trans();
			}
			else if(e1.getSource()==b3)
			{
				int i=0;
				int count=0;
				String s[]={"Date","AccNo","Type","Amount","Balance"};
				System.out.println("loaded");
				System.out.println("connected loaded");
				PreparedStatement ps=con.prepareStatement("select count(*) from record where Accountno=? and Type=?");
				ps.setInt(1,Integer.parseInt(tf1.getText()));
				ps.setString(2,l13.getText());
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					count=rs.getInt(1);
				}
				JOptionPane.showMessageDialog(this,"Total transaction from bank is "+count);
				String s1[][]=new String[count][5];
				PreparedStatement ps1=con.prepareStatement("select * from record where Accountno=? and Type=?");
				ps1.setInt(1,Integer.parseInt(tf1.getText()));
				ps1.setString(2,l13.getText());
				ResultSet rs1=ps1.executeQuery();
				while(rs1.next())
				{
					s1[i][0]=rs1.getString(1);
					s1[i][1]=rs1.getString(2);
					s1[i][2]=rs1.getString(3);
					s1[i][3]=rs1.getString(4);
					s1[i][4]=rs1.getString(5);
					i++;
				}
				JTable t1=new JTable(s1,s);
				//t1.setEditable(false);
				int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
				int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
				JScrollPane sp=new JScrollPane(t1,v,h);
				sp.setBounds(10,500,700,100);
				//sp.setEditable(false);
				//add(l1);
				//add(b1);
				//add(t1);
				add(sp);
				sp.setVisible(true);
			}
			else if(e1.getSource()==b4)
			{
				if((tf1.getText().equals(""))||(jp1.getText().equals("")))
				{
					JOptionPane.showMessageDialog(this,"Enter fields values");
					tf1.setText("");
					jp1.setText("");
					tf1.requestFocus(true);
				}
				else
				{
					PreparedStatement ps=con.prepareStatement("select * from bank_table where Accountno=? and password=?");
					ps.setInt(1,Integer.parseInt(tf1.getText()));
					ps.setString(2,jp1.getText());
					ResultSet rs=ps.executeQuery();
					if(rs.next())
					{
						JOptionPane.showMessageDialog(this,"Your account is verified");
						JOptionPane.showMessageDialog(this,"Enter Amount");
						jp1.setVisible(false);
						l22.setVisible(false);
						b4.setVisible(false);
						l3.setVisible(true);
						tf2.setVisible(true);
						b1.setVisible(true);
						add(l3);
						add(tf2);
						add(b1);
						add(b3);
						tf1.setEditable(false);
						tf2.requestFocus(true);
					}
					else
					{
						JOptionPane.showMessageDialog(this,"Your account is  not verified");
						tf1.setText("");
						jp1.setText("");
						tf1.requestFocus(true);
					}
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println("EOF Exception in deposit"+ex);
		}
	}
}
class withdraw extends JFrame implements ActionListener
{
	JLabel l;
	JLabel l1;
	JLabel l2;
	JLabel l3;
	JLabel l4;
	JLabel l5;
	JLabel l6;
	JLabel l7;
	JLabel l8;
	JLabel l9;
	JLabel l10;
	JLabel l11;
	JLabel l12;
	JLabel l13;
	JLabel l22;
	JPasswordField jp1;
	JButton b1;
	JButton b2;
	JButton b3;
	JButton b4;
	JTextField tf1;
	JTextField tf2;
	String name,type;
	withdraw()
	{
		setLayout(null);
		setSize(1360,730);
		setVisible(true);
		getContentPane().setBackground(Color.blue);
		ImageIcon i1=new ImageIcon("withdraw.gif");
		Color c1=new Color(0,255,255);
		l13=new JLabel("Debitted");
		l13.setBounds(10,10,100,20);
		//add(l13);
		l=new JLabel(i1);
		l.setBounds(1100,70,270,300);
		l1=new JLabel("CASH WITHDRAW");
		Font f1=new Font("Berlin Sans FB Demi",Font.BOLD,50);
		l1.setForeground(c1);
		l1.setFont(f1);
		l1.setBounds(500,50,500,50);
		Font f2=new Font("Magneto",Font.ITALIC,30);
		l2=new JLabel("Enter your Account Number");
		l2.setFont(f2);
		l2.setBounds(200,120,500,30);
		tf1=new JTextField();
		tf1.setBounds(750,120,200,30);
		l22=new JLabel("Enter Password");
		l22.setFont(f2);
		//l22.setForeground(c2);
		l22.setBounds(200,170,500,30);
		jp1=new JPasswordField();
		jp1.setBounds(750,170,200,30);
		b4=new JButton("VERIFY");
		b4.setBounds(550,220,100,30);
		b4.addActionListener(this);
		l3=new JLabel("Enter Amount to be withdraw");
		l3.setFont(f2);
		l3.setBounds(200,170,500,30);
		tf2=new JTextField();
		tf2.setBounds(750,170,200,30);
		b1=new JButton("OK");
		b1.setBounds(550,220,100,30);
		b1.addActionListener(this);
		l8=new JLabel("Account Holder Name");
		l8.setFont(f2);
		l8.setBounds(100,300,600,30);
		l9=new JLabel();
		l9.setFont(f2);
		l9.setBounds(750,300,300,30);
		l4=new JLabel("Your previous Account balance was:");
		l4.setFont(f2);
		l4.setBounds(100,350,600,30);
		l5=new JLabel("Your current Account balance is:");
		l5.setFont(f2);
		l5.setBounds(100,400,600,30);
		l6=new JLabel();
		l6.setFont(f2);
		l6.setBounds(750,350,200,30);
		l7=new JLabel();
		l7.setFont(f2);
		l7.setBounds(750,400,200,30);
		l10=new JLabel("Type of Account");
		l10.setFont(f2);
		l10.setBounds(100,450,600,30);
		l11=new JLabel();
		l11.setFont(f2);
		l11.setBounds(750,450,200,30);
		ImageIcon i2=new ImageIcon("b20.gif");
		b2=new JButton(i2);
		b2.setBounds(30,20,150,40);
		b2.addActionListener(this);
		b3=new JButton("RECORD");
		b3.setBounds(1200,600,100,40);
		b3.addActionListener(this);
		Date d2=Calendar.getInstance().getTime();
		SimpleDateFormat sdf2=new SimpleDateFormat("dd:MM:yyyy;HH:mm:ss");
		l12=new JLabel(sdf2.format(d2));
		add(l);
		add(l1);
		add(l2);
		//add(l3);
		add(tf1);
		//add(tf2);
		//add(l4);
		//add(l5);
		//add(l6);
		//add(l7);
	//	add(l8);
		//add(l9);
//		add(l10);
//		add(l11);
		//add(b1);
		add(b2);
		//add(b3);
		add(l22);
		add(jp1);
		add(b4);
		tf1.requestFocus(true);
	}
	public void actionPerformed(ActionEvent e1)
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:bank_data");
			int pbal=0,cbal,wd;
			if(e1.getSource()==b1)
			{
				l5.setVisible(true);
				l6.setVisible(true);
				l7.setVisible(true);
				l8.setVisible(true);
				l9.setVisible(true);
				l10.setVisible(true);
				l11.setVisible(true);
				add(l4);
				add(l5);
				add(l6);
				add(l7);
				add(l8);
				add(l9);
				add(l10);
				add(l11);
				l4.setVisible(true);
				if((tf2.getText().equals("")))
				{
					JOptionPane.showMessageDialog(this,"Please enter Fields First");
					//tf1.setText("");
					tf2.setText("");
					tf2.requestFocus(true);
				}
				else
				{
					tf2.requestFocus(true);
					PreparedStatement ps=con.prepareStatement("select * from bank_table where Accountno=?");
					ps.setInt(1,Integer.parseInt(tf1.getText()));
					ResultSet rs=ps.executeQuery();
					if(rs.next())
					{
						name=rs.getString("namee");
						pbal=Integer.parseInt(rs.getString("Balance"));
						type=rs.getString("acctype");
						wd=Integer.parseInt(tf2.getText());
						if(wd+1000>pbal)
						{
							JOptionPane.showMessageDialog(this,"You can't withdraw money");
							JOptionPane.showMessageDialog(this,"You can withdraw at the max"+(pbal-1000));
							tf2.setText("");
							tf2.requestFocus(true);
						}
						else
						{
							cbal=pbal-wd;
							PreparedStatement ps1=con.prepareStatement("update bank_table set Balance=? where Accountno=?");
							ps1.setInt(1,cbal);
							ps1.setInt(2,Integer.parseInt(tf1.getText()));
							ps1.executeUpdate();
							l9.setText(String.valueOf(name));
							l6.setText(String.valueOf(pbal));
							l7.setText(String.valueOf(cbal));
							l11.setText(String.valueOf(type));
							JOptionPane.showMessageDialog(this,"Ammount has been withdrawn");
							PreparedStatement ps2=con.prepareStatement("insert into record values(?,?,?,?,?)");
							ps2.setString(1,l12.getText());
							ps2.setInt(2,Integer.parseInt(tf1.getText()));
							ps2.setString(3,l13.getText());
							ps2.setInt(4,Integer.parseInt(tf2.getText()));
							ps2.setInt(5,cbal);
							ps2.executeUpdate();
							JOptionPane.showMessageDialog(this,"Your record has been successfully saved");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(this,"Account number doesn't exist");
						tf1.setText("");
						tf2.setText("");
						tf1.requestFocus(true);
					}
				}
			}
			else if(e1.getSource()==b2)
			{
				dispose();
				new trans();
			}
			else if(e1.getSource()==b3)
			{
				int i=0;
				int count=0;
				String s[]={"Date","AccNo","Type","Amount","Balance"};
				System.out.println("loaded");
				System.out.println("connected loaded");
				PreparedStatement ps=con.prepareStatement("select count(*) from record where Accountno=? and Type=?");
				ps.setInt(1,Integer.parseInt(tf1.getText()));
				ps.setString(2,l13.getText());
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					count=rs.getInt(1);
				}
				JOptionPane.showMessageDialog(this,"Total transaction from bank is "+count);
				String s1[][]=new String[count][5];
				PreparedStatement ps1=con.prepareStatement("select * from record where Accountno=? and Type=?");
				ps1.setInt(1,Integer.parseInt(tf1.getText()));
				ps1.setString(2,l13.getText());
				ResultSet rs1=ps1.executeQuery();
				while(rs1.next())
				{
					s1[i][0]=rs1.getString(1);
					s1[i][1]=rs1.getString(2);
					s1[i][2]=rs1.getString(3);
					s1[i][3]=rs1.getString(4);
					s1[i][4]=rs1.getString(5);
					i++;
				}
				JTable t1;
				JScrollPane sp;
				t1=new JTable(s1,s);
				//t1.setEditable(false);
				int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
				int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
				sp=new JScrollPane(t1,v,h);
				sp.setBounds(10,500,700,100);
				//sp.setEditable(false);
				//add(l1);
				//add(b1);
				//add(t1);
				add(sp);
				sp.setVisible(true);
			}
			else if(e1.getSource()==b4)
			{
				if((tf1.getText().equals(""))||(jp1.getText().equals("")))
				{
					JOptionPane.showMessageDialog(this,"Enter fields values");
					tf1.setText("");
					jp1.setText("");
					tf1.requestFocus(true);
				}
				else
				{
					PreparedStatement ps=con.prepareStatement("select * from bank_table where Accountno=? and password=?");
					ps.setInt(1,Integer.parseInt(tf1.getText()));
					ps.setString(2,jp1.getText());
					ResultSet rs=ps.executeQuery();
					if(rs.next())
					{
						JOptionPane.showMessageDialog(this,"Your account is verified");
						JOptionPane.showMessageDialog(this,"Enter Amount");
						jp1.setVisible(false);
						l22.setVisible(false);
						b4.setVisible(false);
						l3.setVisible(true);
						tf2.setVisible(true);
						b1.setVisible(true);
						add(l3);
						add(tf2);
						add(b1);
						add(b3);
						tf1.setEditable(false);
						tf2.requestFocus(true);
					}
					else
					{
						JOptionPane.showMessageDialog(this,"Your account is  not verified");
						tf1.setText("");
						jp1.setText("");
						tf1.requestFocus(true);
					}
				}
			}
		}
		catch(Exception ex1)
		{
			System.out.println("Exception is in withdraw"+ex1);
		}
	}
}
class view extends JFrame implements ActionListener 
{
	JLabel l;
	JLabel l1;
	JLabel l2;
	JLabel l3;
	JTextField tf1;
	JPasswordField tf2;
	JButton b1;
	JButton b2;
	view()
	{
		setLayout(null);
		setSize(1370,730);
		setVisible(true);
		getContentPane().setBackground(Color.white);
		ImageIcon i1=new ImageIcon("Bank.png");
		ImageIcon i2=new ImageIcon("admin.jpg");
		Color c1=new Color(255,255,255);
		l3=new JLabel(i2);
		l3.setBounds(0,0,1370,730);
		Font f3=new Font("Magneto",Font.ITALIC,30);
		l=new JLabel(i1);
		l.setBounds(600,30,219,200);
		l1=new JLabel("Enter Admin Username");
		l1.setForeground(c1);
		l1.setFont(f3);
		l1.setBounds(400,350,400,30);
		tf1=new JTextField();
		tf1.setBounds(850,350,120,30);
		//tf1.setFont(f3);
		l2=new JLabel("Enter Admin Password");
		l2.setFont(f3);
		l2.setForeground(c1);
		l2.setBounds(400,400,400,30);
		tf2=new JPasswordField();
		tf2.setBounds(850,400,120,30);
		//tf2.setFont(f3);
		b1=new JButton("PROCEED");
		b1.setBounds(550,500,100,30);
		b1.addActionListener(this);
		b2=new JButton("EXIT");
		b2.setBounds(700,500,100,30);
		b2.addActionListener(this);
		add(l3);
		l3.add(l);
		l3.add(l1);
		l3.add(l2);
		l3.add(tf1);
		l3.add(tf2);
		l3.add(b1);
		l3.add(b2);
	}
	public void actionPerformed(ActionEvent e6)
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:bank_data");
			int bal;
			if(e6.getSource()==b1)
			{
				PreparedStatement ps=con.prepareStatement("select * from admin where username=? and password=?");
				ps.setString(1,tf1.getText());
				ps.setString(2,tf2.getText());
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					JOptionPane.showMessageDialog(this,"Welcome Admin");
					dispose();
					new viewall();
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Check your password once");
					tf1.setText("");
					tf2.setText("");
					tf1.requestFocus(true);
				}
			}
			else if(e6.getSource()==b2)
			{
				dispose();
				new bank_project1();
			}
		}
		catch(Exception ex)
		{
			System.out.println("Exception is"+ex);
		}
	}
}
class viewall extends JFrame implements ActionListener
{
	JLabel l1;
	JButton b1;
	JTable t1;
	JScrollPane sp;
	ResultSet rs;
	ResultSet rs1;
	Connection con;
	Connection con1;
	PreparedStatement ps;
	PreparedStatement ps1;
	int i=0;
	viewall()
	{
	
		try
		{
			setTitle("ADMIN PAGE");
			setLayout(null);
			setSize(1370,730);
			setVisible(true);
			getContentPane().setBackground(Color.gray);
			Font f1=new Font("Arial Black",Font.BOLD,50);
			l1=new JLabel("All ACCOUNT Details");
			l1.setFont(f1);
			l1.setBounds(400,20,700,50);
			b1=new JButton("EXIT");
			b1.setBounds(1000,650,80,30);
			b1.addActionListener(this);
			int count=0;
			String s[]={"AccountNo","Name","D.O.B","Address","ContactNo","Gender","Religion","Category","acctype","Balance"};
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			System.out.println("loaded");
			Connection con=DriverManager.getConnection("jdbc:odbc:bank_data");
			System.out.println("connected loaded");
			PreparedStatement ps=con.prepareStatement("select count(*) from bank_table");
			rs=ps.executeQuery();
			while(rs.next())
			{
				count=rs.getInt(1);
			}
			JOptionPane.showMessageDialog(this,"Total number of Accounts in bank is "+count);
			String s1[][]=new String[count][10];
			PreparedStatement ps1=con.prepareStatement("select * from bank_table");
			rs=ps1.executeQuery();
			while(rs.next())
			{
				s1[i][0]=rs.getString(1);
				s1[i][1]=rs.getString(2);
				s1[i][2]=rs.getString(3);
				s1[i][3]=rs.getString(4);
				s1[i][4]=rs.getString(5);
				s1[i][5]=rs.getString(6);
				s1[i][6]=rs.getString(7);
				s1[i][7]=rs.getString(8);
				s1[i][8]=rs.getString(9);
				s1[i][9]=rs.getString(10);
				i++;
			}
			t1=new JTable(s1,s);
			//t1.setEditable(false);
			int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
			int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
			sp=new JScrollPane(t1,v,h);
			sp.setBounds(30,100,1260,500);
			//sp.setEditable(false);
			add(l1);
			add(b1);
			//add(t1);
			add(sp);
		}
		catch(Exception ex)
		{
			System.out.println("Exception is"+ex);
		}
	}
	public void actionPerformed(ActionEvent e1)
	{
		if(e1.getSource()==b1)
		{
			dispose();
			new bank_project1();
		}
	}
}
class help extends JFrame implements ActionListener
{
	JButton b1;
	JLabel l1;
	JButton b2;
	help()
	{
		setLayout(null);
		setSize(1360,730);
		setVisible(true);
		getContentPane().setBackground(Color.gray);
		l1=new JLabel("Is your problem is under mentioned");
		l1.setBounds(20,50,500,30);
		Checkbox cb1=new Checkbox("Site not Properly Working");
		cb1.setBounds(30,100,200,20);
		Checkbox cb2=new Checkbox("What is the minimum amount we should deposit");
		cb2.setBounds(30,140,300,20);
		b1=new JButton("Any other");
		b1.setBounds(50,180,100,20);
		b1.addActionListener(this);
		b2=new JButton("EXIT");
		b2.setBounds(180,180,100,20);
		b2.addActionListener(this);
		add(l1);
		add(b1);
		add(cb1);
		add(cb2);
		add(b2);
	}
	public void actionPerformed(ActionEvent e8)
	{
		if(e8.getSource()==b1)
		{
			dispose();
			new complaint();
		}
		else if(e8.getSource()==b2)
		{
			dispose();
		}
	}
	public static void main(String args[])
	{
		help h=new help();
	}
}
class detail extends JFrame implements ActionListener
{
	JLabel l1;
	JLabel l2;
	JButton b1;
	JButton b2;
	detail()
	{
		setLayout(null);
		setSize(1360,730);
		setVisible(true);
		getContentPane().setBackground(Color.red);
		l1=new JLabel("UserName");
		l1.setBounds(50,50,80,20);
		JTextField tf1=new JTextField();
		tf1.setBounds(150,50,80,20);
		l2=new JLabel("Password");
		l2.setBounds(50,100,80,20);
		JTextField tf2=new JTextField();
		tf2.setBounds(150,100,80,20);
		b1=new JButton("SUBMIT");
		b1.setBounds(80,200,80,20);
		b1.addActionListener(this);
		b2=new JButton("BACK");
		b2.setBounds(200,200,80,20);
		b2.addActionListener(this);
		add(l1);
		add(l2);
		add(tf1);
		add(tf2);
		add(b1);
		add(b2);
	}
	public void actionPerformed(ActionEvent e6)
	{
		if(e6.getSource()==b1)
		{
			dispose();
			new successful();
		}
		else if(e6.getSource()==b2)
		{
			dispose();
			new bank_project1();
		}
	}
	public static void main(String args[])
	{
		detail d=new detail();
	}
}
class successful extends JFrame implements ActionListener
{
	JLabel l1;
	JButton b1;
	successful()
	{
		setLayout(null);
		setSize(1360,730);
		setVisible(true);
		getContentPane().setBackground(Color.green);
		l1=new JLabel("CONGRATULATIONS....");
		Font f1=new Font("Bodoni MT Black",Font.BOLD,50);
		l1.setFont(f1);
		l1.setBounds(300,50,900,80);
		ImageIcon i1=new ImageIcon("relax.jpg");
		b1=new JButton(i1);
		b1.setBounds(600,400,500,209);
		b1.addActionListener(this);
		add(l1);
		add(b1);
	}
	public void actionPerformed(ActionEvent e7)
	{
		if(e7.getSource()==b1)
		{
			dispose();
		}
		else
		{
		}
	}
	public static void main(String[] args)
	{
		successful ss=new successful();
	}
}
class complaint extends JFrame implements ActionListener
{
	JLabel l1;
	JButton b1;
	complaint()
	{
		setLayout(null);
		setVisible(true);
		getContentPane().setBackground(Color.red);
		setTitle("CUSTOMER COMPLAINT FORM");
		setSize(1360,730);
		l1=new JLabel("Your complaint");
		l1.setBounds(20,20,100,20);
		JTextArea ta1=new JTextArea();
		ta1.setBounds(30,50,100,100);
		b1=new JButton("SUBMIT");
		b1.setBounds(80,200,80,30);
		b1.addActionListener(this);
		add(l1);
		add(ta1);
		add(b1);
	}
	public void actionPerformed(ActionEvent e9)
	{
		if(e9.getSource()==b1)
		{
			dispose();
			new submitted();
		}
		else
		{
		}
	}
	public static void main(String args[])
	{
		complaint c=new complaint();
	}
}
class submitted extends JFrame implements ActionListener
{
	JLabel l1;
	JButton b1;
	submitted()
	{
		setLayout(null);
		setSize(1360,730);
		setVisible(true);
		getContentPane().setBackground(Color.green);
		l1=new JLabel("Your complaint is successfully delivered to the main branch and it will be taken into consideration soon");
		l1.setBounds(20,100,1000,30);
		b1=new JButton("EXIT");
		b1.setBounds(80,200,80,50);
		b1.addActionListener(this);
		add(l1);
		add(b1);
	}
	public void actionPerformed(ActionEvent e10)
	{
		if(e10.getSource()==b1)
		{
			dispose();
		}
		else
		{
		}
	}
}
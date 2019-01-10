import javax.swing.*;
import java.sql.*;
import java.sql.Connection.*;
import java.sql.DriverManager.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.*;
class LoginFrame extends JFrame implements ActionListener 
{
 
	Container container=getContentPane();
	JLabel main=new JLabel("SECRET TRUTH",JLabel.CENTER);	
	JLabel userLabel=new JLabel("USERNAME:",JLabel.CENTER);
        JLabel emailL=new JLabel("Use your email id as Username");
	JLabel passwordLabel=new JLabel("PASSWORD:");
	JTextField name=new JTextField();
	JPasswordField lname=new JPasswordField();
	JButton loginButton=new JButton("LOGIN");
	JButton resetButton=new JButton("RESET");
	JButton registerButton=new JButton("REGISTER");
    //JButton nextButton=new JButton("NEXT");

	JCheckBox showPassword=new JCheckBox("Show Password");

	Font f=new Font("Times Roman",Font.BOLD,18);
	Font f1=new Font("Times Roman",Font.PLAIN,18); 
	Font f2=new Font("Georgia",Font.BOLD,68);  
	LoginFrame()
	{
		//Calling methods inside constructor.
	setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
	addActionEvent();
 	getContentPane().setBackground(Color.black);
    	}
	public void paint(Graphics g)
{
	super.paint(g);
	g.setColor(Color.white);
	g.drawRect(400,150,650,450);
	g.drawString("If you don't have an account..",500,490);
	//g.drawString("Secret Truth",50,50);
}
	 public void setLayoutManager()
   {
       container.setLayout(null);
   }
   public void setLocationAndSize()
   {
       //Setting location and Size of each components using setBounds() method.
        userLabel.setBounds(500, 170, 120, 30);
	emailL.setBounds(630,200,200,30);
        passwordLabel.setBounds(500, 250, 120, 30);
        name.setBounds(630, 170, 225, 30);
        lname.setBounds(630, 250, 225, 30);
        showPassword.setBounds(630, 300, 150, 30);
        loginButton.setBounds(630, 360, 110, 30);
        resetButton.setBounds(750, 360, 105, 30);
	main.setBounds(410,20,600,80);
	registerButton.setBounds(735,435,120,30);
    	//nextButton.setBounds(900,435,120,30);

   }
   public void addComponentsToContainer()
   {
      //Adding each components to the Container
       container.add(userLabel);

	userLabel.setForeground(Color.WHITE);
	userLabel.setFont(f);
	passwordLabel.setForeground(Color.WHITE);
passwordLabel.setFont(f);
	setFont(f1);
       container.add(passwordLabel);
       container.add(name);
	container.add(emailL);
       container.add(lname);
       container.add(showPassword);
	showPassword.setBackground(Color.BLACK);
	showPassword.setForeground(Color.WHITE);
       container.add(loginButton);
       container.add(resetButton);
	container.add(main);
main.setFont(f2);
main.setForeground(Color.WHITE);
emailL.setForeground(Color.WHITE);
container.add(registerButton);

//container.add(nextButton);
   }
 

 public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
	registerButton.addActionListener(this);
   // nextButton.addActionListener(this);
    }
 
    
    public void actionPerformed(ActionEvent e) 
{
//Coding Part of LOGIN button
	if (e.getSource() == loginButton) {
	//System.out.println("Button clicked");
           
	try
	{
   		Class.forName("com.mysql.jdbc.Driver");

		//Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/imagestegano","root","");
        	Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3307/imagestegano","root","");

		//Statement stmt=con.createStatement();
        Statement stmt1=con1.createStatement();
		String nam=name.getText().toString();
		String lnam=lname.getText().toString();
    
		/*String query="insert into login(Uname,Pwd)values(?,?);";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1,nam);
		ps.setString(2,lnam);
		ps.execute();
		System.out.println("1 row inserted");*/
       
        ResultSet rec=stmt1.executeQuery("SELECT email_id,Pwd FROM register ");
        while(rec.next())
        {
            if(nam.equals(rec.getString("email_id")))
            {
                if(lnam.equals(rec.getString("Pwd")))
                {
		//JOptionPane.showMessageDialog(null,"Username and Password valid !!");
                    //System.out.println("Username and Password valid !!");
			
			new SteganoExample();

                }
                else
                {
			JOptionPane.showMessageDialog(null,"Username and Password invalid .Plz do register first!!");

                    //System.out.println("Username and Password invalid .Plz do register first!!");
                }
            }
        }
        
 		//stmt.close();
        stmt1.close();
        con1.close();

//		con.close();
}catch(Exception ae)
{
	System.out.print(ae);
}
}

//Coding Part of RESET button
		if (e.getSource() == resetButton) 
		{
		name.setText("");
            lname.setText("");
        }
       //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) 
	{
		if (showPassword.isSelected())
		{
			lname.setEchoChar((char) 0);
		}
		else
		{
                	lname.setEchoChar('*');
		}
	}



//Coding Part of Register button
if(e.getSource() == registerButton){
 setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//dispose();
new RegistrationForm();
}

/*Coding part of next buttton
if(e.getSource() == nextButton)
{
  setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
 // dispose();
new SteganoExample();
//new Stegano();

   
}
*/
}
}

public class Login
{
    public static void main(String[] a)
	{
        	LoginFrame frame=new LoginFrame();
			
	frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        	frame.setTitle("SECRET TRUTH-Login Form");
	frame.setLocationRelativeTo(null);
        	frame.setVisible(true);
        	//frame.setBounds(10,10,340,600);
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		//frame.setResizable(false);
	}
}
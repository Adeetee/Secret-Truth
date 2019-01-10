import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
 class RegistrationForm extends JFrame implements ActionListener {
    
    JLabel nameLabel=new JLabel("ENTER YOUR NAME");
    JLabel passwordLabel=new JLabel("ENTER YOUR PASSWORD");
    JLabel confirmPasswordLabel=new JLabel("CONFIRM PASSWORD");
    JLabel emailLabel=new JLabel("ENTER EMAIL ID");
    JTextField nameTextField=new JTextField();
    JPasswordField passwordField=new JPasswordField();
    JPasswordField confirmPasswordField=new JPasswordField();
    JTextField emailTextField=new JTextField();
    JButton registerButton=new JButton("REGISTER");
    JButton resetButton=new JButton("RESET");
    
    //JButton backButton=new JButton("BACK");




    RegistrationForm()
    {
        createWindow();
	SetForegroundColor();
        setLocationAndSize();
        addComponentsToFrame();
        actionEvent();
    }
public void paint(Graphics g)
{
	super.paint(g);
	g.setColor(Color.white);
	g.drawRect(400,150,550,400);
	
	
}
    public void createWindow()
    {
       
        
        /*setBounds(40,40,380,600);*/
        getContentPane().setBackground(Color.black);
        //getContentPane().setLayout(null);
        setVisible(true);
	setSize(Toolkit.getDefaultToolkit().getScreenSize());
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // frame.setResizable(false);
    }
    public void setLocationAndSize()
    {
        nameLabel.setBounds(490,180,150,30); 
        passwordLabel.setBounds(490,210,150,70);
        confirmPasswordLabel.setBounds(490,260,140,70);
        emailLabel.setBounds(490,310,100,70);
        nameTextField.setBounds(660,180,180,23);
        passwordField.setBounds(660,230,180,23);
        confirmPasswordField.setBounds(660,280,180,23);
      
        emailTextField.setBounds(660,330,180,23);
        registerButton.setBounds(520,400,100,35);
        resetButton.setBounds(680,400,100,35);
	//backButton.setBounds(400,430,100,35);	
    }

    public void addComponentsToFrame()
    {
        add(nameLabel);
        add(passwordLabel);
        add(confirmPasswordLabel);
        add(emailLabel);
        add(nameTextField);
        add(passwordField);
        add(confirmPasswordField);
        add(emailTextField);
        add(registerButton);
     	add(resetButton);
	//add(backButton);
    }

	public void SetForegroundColor()
  {
  	nameLabel.setForeground(Color.WHITE); 
        passwordLabel.setForeground(Color.WHITE); 
        confirmPasswordLabel.setForeground(Color.WHITE);  
        emailLabel.setForeground(Color.WHITE); 
        nameTextField.setForeground(Color.BLACK); 
         passwordField.setForeground(Color.BLACK); 
        confirmPasswordField.setForeground(Color.BLACK);  
        emailTextField.setForeground(Color.BLACK); 
      }



public void actionEvent()
    {
        registerButton.addActionListener(this);
        resetButton.addActionListener(this);
	//backButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
//CODING OF REGISTER BUTTON
        if(e.getSource()==registerButton)
        {
            try {
                //Creating Connection Object
                Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3307/imagestegano","root","");
                //Preapared Statement
                PreparedStatement Pstatement=connection.prepareStatement("insert into register values(?,?,?,?)");
                //Specifying the values of it's parameter
                Pstatement.setString(1,nameTextField.getText());
                
             Pstatement.setString(2,passwordField.getText());
                Pstatement.setString(3,confirmPasswordField.getText());
                               Pstatement.setString(4,emailTextField.getText());
                //Checking for the Password match
                if(passwordField.getText().equalsIgnoreCase(confirmPasswordField.getText()))
                {
                    //Executing query
                    Pstatement.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Data Registered Successfully");
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"password did not match");
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
 }
/*CODING PART OF BACK BUTTON
if(e.getSource() == backButton)
{
	 setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	//dispose();
	new LoginFrame();


}

*/


//CODING PART OF RESET BUTTON
        if(e.getSource()==resetButton)
        {
           
            nameTextField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");
            emailTextField.setText("");
        }

    }
}
public class Main {
    public static void main(String[] args)
    {
        RegistrationForm register=new RegistrationForm();
	register.setTitle("SECRET TRUTH-Registration Form");
    }
}
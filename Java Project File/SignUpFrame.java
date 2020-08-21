import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

class SignUpFrame extends JFrame{
	JButton previous,signUp;
	JLabel name,email,dateOfBirth,password;
	JTextField nameText,emailText,dateOfBirthText,passwordText;
	
	
	public SignUpFrame(){
		super("SignUp window");
		
		//Label
		name = new JLabel("Name:");
		email = new JLabel("Email:");
		dateOfBirth = new JLabel("Date of Birth:");
		password = new JLabel("Password:");
		
		//text field
		nameText = new JTextField(20);
		emailText = new JTextField("@gmail.com",20);
		dateOfBirthText = new JTextField("day/month/year",20);
		passwordText = new JTextField(20);
		
		//buttons
		previous = new JButton("Previous page");
		signUp = new JButton("Sign Up");
		
		
		add(name);add(nameText);
		add(email);add(emailText);
		add(dateOfBirth);add(dateOfBirthText);
		add(password);add(passwordText);
		
		add(previous);
		add(signUp);
		
		SignUpButtonSensor bs=new SignUpButtonSensor(this);
		previous.addActionListener(bs);
		signUp.addActionListener(bs);


		setSize(700,500);
		int screenWidth=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		
		setLocation((screenWidth-280)/2,120);
		setLayout(new FlowLayout());
	}
}

class SignUpButtonSensor implements ActionListener{
	int numOfRowsUpdated=0;
	Database objectOfDatabase = new Database();
	SignUpFrame objectOfSignUpFrame;
	public SignUpButtonSensor(SignUpFrame x){
		objectOfSignUpFrame=x;
	}
	public void actionPerformed(ActionEvent ae){
		boolean flag=true;
		String nameText=objectOfSignUpFrame.nameText.getText();
		String emailText=objectOfSignUpFrame.emailText.getText();
		String dateOfBirthText=objectOfSignUpFrame.dateOfBirthText.getText();
		String passwordText=objectOfSignUpFrame.passwordText.getText();
		String s=ae.getActionCommand();
		
		if(s.equals("Previous page")){
			System.out.println("Previous button presed");
			System.out.println("Previous page Button pressed");
			new LoginFrame().setVisible(true);
			objectOfSignUpFrame.dispose();
		}
		else{
			if(emailText.length()==9 || passwordText.length()==0){
				JOptionPane.showMessageDialog(null,"You must provide name ,password and email");
				flag=false;
			}
			if(flag && s.equals("Sign Up")){
				
				try{
					
					String query = "INSERT INTO customer(name,email,dateOfBirth,password) VALUES ('"+nameText+"','"+emailText+"','"+dateOfBirthText+"','"+passwordText+"')";
				    numOfRowsUpdated=objectOfDatabase.updateDB(query);
					
					new LoginFrame().setVisible(true);
			        objectOfSignUpFrame.dispose();
					
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
				
			}
		}		
	}
}
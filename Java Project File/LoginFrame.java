import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;

class WindowSensor extends WindowAdapter{
	public void windowClosing(WindowEvent we){
        System.out.println("Window is closing");
		System.exit(0);
	}
}
class LoginFrame extends JFrame{
	JButton login,signUp,cancel;
	JTextField email,password;
	JLabel emailLabel,passwordLabel;
	
	
	public LoginFrame(){
		super ("Login Frame");
		emailLabel = new JLabel("EMAIL:");
		passwordLabel = new JLabel("PASSWORD:");
		
		email = new JTextField("@gmail.com",30);
		password = new JTextField(30);
		
		
		login = new JButton("Login");
		signUp = new JButton("Sign Up");
		cancel = new JButton("Exit");
		
		add(emailLabel);add(email);
		add(passwordLabel);add(password);
		
		add(login);
		add(signUp);add(cancel);
		
		LoginButtonSensor bs=new LoginButtonSensor(this);
		login.addActionListener(bs);
		signUp.addActionListener(bs);
		cancel.addActionListener(bs);
		
		setSize(700,500);
		int screenWidth=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		
		setLocation((screenWidth-280)/2,120);
		setLayout(new FlowLayout());
		
	}
}
class LoginButtonSensor implements ActionListener{
	ResultSet rs=null;
	Database objectOfDatabase = new Database();
	LoginFrame objectOfLoginFrame;
	public LoginButtonSensor(LoginFrame f){
		objectOfLoginFrame = f;
		
	}
	public void actionPerformed(ActionEvent ae){
		boolean flag = true;
		String email = objectOfLoginFrame.email.getText();
		String password = objectOfLoginFrame.password.getText();
		String s = ae.getActionCommand();
		
		if(s.equals("Exit")){
			System.exit(0);
			
		}
		if(s.equals("Sign Up")){
			System.out.println("SignUp Button pressed");
			new SignUpFrame().setVisible(true);
			objectOfLoginFrame.dispose();
		}
		else{
			if(email.length()==9 || password.length()==0){
				JOptionPane.showMessageDialog(null,"You must provide User name and Password");
				flag=false;
			}
			if(flag && s.equals("Login")){
				String sql="select * from customer where email='"+email+"' and password='"+password+"'";
				rs=objectOfDatabase.getData(sql);
				try{
					if(rs.next()){
						objectOfLoginFrame.dispose();
						new HomeFrame().setVisible(true);
						
					}
					else{
						JOptionPane.showMessageDialog(null,"You must need to provide Valid User name and Password");
					}
					objectOfDatabase.close();
				}
				catch(SQLException ex){
					ex.printStackTrace();
				}
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
}
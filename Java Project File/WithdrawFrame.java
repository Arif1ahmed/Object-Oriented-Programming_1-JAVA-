import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

class WithdrawFrame extends JFrame
{
	
	JButton previous,logout,withdrawButton;
	JLabel withdraw,email,password;
	static JTextField withdrawText,emailText,passwordText;
	public WithdrawFrame()
	{
		super("Withdraw window");
		withdraw = new JLabel("Enter Amount:");
		email = new JLabel("Enter Email:");
		password = new JLabel("Enter Password:");
		withdrawText = new JTextField(30);
		emailText = new JTextField("@gmail.com",30);
		passwordText = new JTextField(30);
		previous = new JButton("Previous page");
		logout = new JButton("LogOut");
		withdrawButton = new JButton("Withdraw");
		add(withdraw);
		add(withdrawText);
		add(email);
		add(emailText);
		add(password);
		add(passwordText);
		add(withdrawButton);
		add(previous);
		add(logout);
		
		WithdrawButtonSensor ds=new WithdrawButtonSensor(this);
		previous.addActionListener(ds);
		logout.addActionListener(ds);
		withdrawButton.addActionListener(ds);
		setSize(700,500);
		int screenWidth=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		
		setLocation((screenWidth-280)/2,120);
		setLayout(new FlowLayout());
	}	
	
	
}
class WithdrawButtonSensor implements ActionListener{
	ResultSet rs=null;
	int numOfRowsUpdated=0;
	Database objectOfDatabase = new Database();
	WithdrawFrame objectOfWithdrawFrame;
	public WithdrawButtonSensor(WithdrawFrame f){
		objectOfWithdrawFrame = f;
		
	}
	public void actionPerformed(ActionEvent ae){
		boolean flag = true;
		String email = objectOfWithdrawFrame.emailText.getText();
		String password = objectOfWithdrawFrame.passwordText.getText();
		String withdraw = objectOfWithdrawFrame.withdrawText.getText();
		String s = ae.getActionCommand();
		int numb;
		if(s.equals("LogOut")){
			System.out.println("LogOut Button pressed");
			new LoginFrame().setVisible(true);
			objectOfWithdrawFrame.dispose();
			
		}
		if(s.equals("Previous page")){
			System.out.println("Previous page Button pressed");
			new HomeFrame().setVisible(true);
			objectOfWithdrawFrame.dispose();
		}
		if(s.equals("Withdraw")){
			System.out.println("Withdraw Button pressed");
			if(email.length()==9 || password.length()==0){
			if(objectOfWithdrawFrame.withdrawText.getText()==""){
				JOptionPane.showMessageDialog(null,"You must provide User name , Password.");
				flag=false;
			}
			if(flag && s.equals("Withdraw")){
				numb=Integer.parseInt(withdraw);
				String sql="select * from customer where email='"+email+"' and password='"+password+"'";
				rs=objectOfDatabase.getData(sql);
				if(numb<=0)
				{
					JOptionPane.showMessageDialog(null," NO Amount Withdraw");
				}

				else
				{
					try{
						int num,num1,numf;
						
						rs=objectOfDatabase.getData(sql);
						rs.next();
						num1=Integer.parseInt(rs.getString("Withdraw"));
						num=Integer.parseInt(rs.getString("Balance"));
						JOptionPane.showMessageDialog(null,"Amount Withdraw Successfully , Amount is "+numb);
						numf=num-numb;
						num1=num1+numb;
						String ss="update customer set Withdraw="+num1+" where email="+email+"";
						String aa="update customer set Balance="+numf+" where email="+email+"";
						numOfRowsUpdated=objectOfDatabase.updateDB(ss);
						numOfRowsUpdated=objectOfDatabase.updateDB(aa);
						new HomeFrame().setVisible(true);
						objectOfWithdrawFrame.dispose();
					}
					catch(Exception ex){
						JOptionPane.showMessageDialog(null,"Amount can not be Withdraw");
						
					}
					
				}
				
			}
		}
		
	}
}
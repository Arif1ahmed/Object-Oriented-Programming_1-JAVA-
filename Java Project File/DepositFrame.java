import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;






class DepositFrame extends JFrame
{
	
	JButton previous,logout,depositButton;
	JLabel deposit,email,password;
	static JTextField depositText,emailText,passwordText;
	public DepositFrame()
	{
		super("Deposit window");
		deposit = new JLabel("Enter Amount:");
		email = new JLabel("Enter Email:");
		password = new JLabel("Enter Password:");
		depositText = new JTextField(30);
		emailText = new JTextField("@gmail.com",30);
		passwordText = new JTextField(30);
		previous = new JButton("Previous page");
		logout = new JButton("LogOut");
		depositButton = new JButton("Deposit");
		add(deposit);
		add(depositText);
		add(email);
		add(emailText);
		add(password);
		add(passwordText);
		add(depositButton);
		add(previous);
		add(logout);
		
		DepositButtonSensor ds=new DepositButtonSensor(this);
		previous.addActionListener(ds);
		logout.addActionListener(ds);
		depositButton.addActionListener(ds);
		
		
		setSize(700,500);
		int screenWidth=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		
		setLocation((screenWidth-280)/2,120);
		setLayout(new FlowLayout());
	}	
	
	
	
	
}
class DepositButtonSensor implements ActionListener{
	ResultSet rs=null;
	int numOfRowsUpdated=0;
	Database objectOfDatabase = new Database();
	DepositFrame objectOfDepositFrame;
	public DepositButtonSensor(DepositFrame f){
		objectOfDepositFrame = f;
		
	}
	public void actionPerformed(ActionEvent ae){
		boolean flag = true;
		String email = objectOfDepositFrame.emailText.getText();
		String password = objectOfDepositFrame.passwordText.getText();
		String deposit = objectOfDepositFrame.depositText.getText();
		String s = ae.getActionCommand();
		int numb;
		if(s.equals("LogOut")){
			System.out.println("LogOut Button pressed");
			new LoginFrame().setVisible(true);
			objectOfDepositFrame.dispose();
			
		}
		if(s.equals("Previous page")){
			System.out.println("Previous page Button pressed");
			new HomeFrame().setVisible(true);
			objectOfDepositFrame.dispose();
		}
		else{
			if(email.length()==9 || password.length()==0){
				JOptionPane.showMessageDialog(null,"You must provide User name , Password.");
				flag=false;
			}
			if(flag && s.equals("Deposit")){
				numb=Integer.parseInt(deposit);
				String sql="select * from customer where email='"+email+"' and password='"+password+"'";
				rs=objectOfDatabase.getData(sql);
				if(numb<=0)
				{
					JOptionPane.showMessageDialog(null," NO Amount Deposited");
				}

				else
				{
					try{
						int num,num1,numf;
						
						rs=objectOfDatabase.getData(sql);
						rs.next();
						num1=Integer.parseInt(rs.getString("Deposit"));
						num=Integer.parseInt(rs.getString("Balance"));
						JOptionPane.showMessageDialog(null,"Amount Deposited Successfully , Amount is "+numb);
						numf=num+numb;
						num1=num1+numb;
						String ss="update customer set Deposit="+num1+" where email="+email+"";
						String aa="update customer set Balance="+numf+" where email="+email+"";
						numOfRowsUpdated=objectOfDatabase.updateDB(ss);
						numOfRowsUpdated=objectOfDatabase.updateDB(aa);
						new HomeFrame().setVisible(true);
						objectOfDepositFrame.dispose();
					}
					catch(Exception ex){
						JOptionPane.showMessageDialog(null,"Amount can not be DEPOSITED");
						
					}
				}
				
			}
		}
		
	}
}
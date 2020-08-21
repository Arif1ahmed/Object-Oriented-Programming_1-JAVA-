import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

class TransactionFrame extends JFrame
{
	
	JButton previous,logout,transfer;
	JLabel enterAmount,email,password;
	JTextField enterAmountText,emailText,passwordText;
	public TransactionFrame()
	{
		super("Transaction window");
		enterAmount = new JLabel("Enter Amount:");
		enterAmountText = new JTextField(30);
		
		
		email = new JLabel("Email of Received Account:");
		emailText = new JTextField("@gmail.com",30);
		
		password = new JLabel("Enter Password:");
		passwordText = new JTextField(30);
		
		transfer = new JButton("Transfer Money");
		
		previous = new JButton("Previous page");
		logout = new JButton("LogOut");
		
		add(enterAmount);
		add(enterAmountText);
		add(email);
		add(emailText);
		add(password);
		add(passwordText);
		add(transfer);
		add(previous);
		add(logout);
		
		
		TransferButtonSensor ds=new TransferButtonSensor(this);
		transfer.addActionListener(ds);
		previous.addActionListener(ds);
		logout.addActionListener(ds);
		
		setSize(700,500);
		int screenWidth=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		
		setLocation((screenWidth-280)/2,120);
		setLayout(new FlowLayout());
	}	
	
	
}

class TransferButtonSensor implements ActionListener{
	ResultSet rs=null;
	int numOfRowsUpdated=0;
	Database objectOfDatabase = new Database();
	LoginFrame objectOfLoginFrame = new LoginFrame();
	TransactionFrame objectOfTransactionFrame;
	public TransferButtonSensor(TransactionFrame f){
		objectOfTransactionFrame = f;
		
	}
	public void actionPerformed(ActionEvent ae){
		boolean flag = true;
		String email = objectOfLoginFrame.email.getText();
		String password = objectOfTransactionFrame.passwordText.getText();
		String amount = objectOfTransactionFrame.enterAmountText.getText();
		String receivedEmail = objectOfTransactionFrame.emailText.getText();
		

		String s = ae.getActionCommand();
		int numb;
		if(s.equals("LogOut")){
			System.out.println("LogOut Button pressed");
			new LoginFrame().setVisible(true);
			objectOfTransactionFrame.dispose();
			
		}
		if(s.equals("Previous page")){
			System.out.println("Previous page Button pressed");
			new HomeFrame().setVisible(true);
			objectOfTransactionFrame.dispose();
		}
		else{
			if(email.length()==9 || password.length()==0){
				JOptionPane.showMessageDialog(null,"You must provide User name , Password.");
				flag=false;
			}
			if(flag && s.equals("Transfer Money")){
				numb=Integer.parseInt(amount);
				String sql1="select * from customer where email='"+email+"' and password='"+password+"'";
				String sql2="select * from customer where email='"+receivedEmail+"'";
				rs=objectOfDatabase.getData(sql1);
				rs=objectOfDatabase.getData(sql2);
				if(numb<=0)
				{
					JOptionPane.showMessageDialog(null," NO Amount Deposited");
				}

				else
				{
					try{
						int num,num1,numf;
						
						rs=objectOfDatabase.getData(sql1);
						rs.next();
						num1=Integer.parseInt(rs.getString("Balance"));
						
						
						rs=objectOfDatabase.getData(sql2);
						rs.next();
						num=Integer.parseInt(rs.getString("Balance"));
						
						JOptionPane.showMessageDialog(null,"Amount transfered Successfully , Amount is "+numb);
						numf=num+numb;
						num1=num1-numb;
						String ss="update customer set Deposit="+num1+" where email="+email+"";
						String aa="update customer set Balance="+numf+" where email="+receivedEmail+"";
						numOfRowsUpdated=objectOfDatabase.updateDB(ss);
						numOfRowsUpdated=objectOfDatabase.updateDB(aa);
					}
					catch(Exception ex){
						JOptionPane.showMessageDialog(null,"Amount can not be DEPOSITED");
						
					}
				}
				
			}
		}
		
	}
}


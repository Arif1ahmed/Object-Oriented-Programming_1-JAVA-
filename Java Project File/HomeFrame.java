import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;




class HomeFrame extends JFrame implements ActionListener
{
	JButton profile,deposit,withdraw,tran,logout;
	public HomeFrame()
	{
		super("Home Window");
		
		JButton deposit = new JButton("Deposit");
		JButton withdraw = new JButton("Withdraw");
		JButton tran = new JButton("Transaction");
		JButton logout = new JButton("LogOut");
		
		add(deposit);
		add(withdraw);
		add(tran);
		add(logout);
		
		deposit.addActionListener(this);
		withdraw.addActionListener(this);
		tran.addActionListener(this);
		logout.addActionListener(this);
		this.addWindowListener(new WindowSensor());
		
		
		setSize(700,500);
		int screenWidth=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		
		setLocation((screenWidth-280)/2,120);
		setLayout(new FlowLayout());
	}
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		
		if(command=="Deposit")
		{
			System.out.println("Deposit Button pressed");
			new DepositFrame().setVisible(true);
			this.dispose();
				
		}
		if(command=="Withdraw")
		{
			System.out.println("Withdraw Button pressed");
			new WithdrawFrame().setVisible(true);
			this.dispose();
				
		}	
		if(command=="Transaction")
		{
			System.out.println("Transaction Button pressed");
			new TransactionFrame().setVisible(true);
			this.dispose();
				
		}	
		if(command=="LogOut")
		{
			System.out.println("LogOut Button pressed");
			new LoginFrame().setVisible(true);
			this.dispose();
				
		}	
	}
}
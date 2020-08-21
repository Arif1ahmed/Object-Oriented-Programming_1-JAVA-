import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

class ProfileFrame extends JFrame implements ActionListener
{
	
	JButton previous,logout;
	JLabel need;
	public ProfileFrame()
	{
		super("Profile window");
		need = new JLabel("Name");
		previous = new JButton("Previous page");
		logout = new JButton("LogOut");
		add(need);
		add(previous);
		add(logout);
		previous.addActionListener(this);
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
		if(command=="Previous page")
		{
			System.out.println("Previous page Button pressed");
			new HomeFrame().setVisible(true);
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
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPage implements ActionListener
{
    JFrame frame;
    ImageIcon image;
    JLabel label1, label2, imagelabel;
    JButton deposit, withdraw, checkbalance,transferamnt, logout; 
    String Name;
    String AccNum;
    String AccPass;


    public MenuPage(String AccNum, String AccPass, String Name)
    {
        this.AccNum = AccNum;
        this.AccPass = AccPass;
        this.Name = Name;

        image = new ImageIcon("HDFC.png");
        imagelabel = new JLabel(image);
        Dimension dimension = imagelabel.getPreferredSize();
        imagelabel.setBounds(20, 20, dimension.width, dimension.height);

        label1 = new JLabel();
        label1.setText("Hello "+Name+"...");
        label1.setFont(new Font("Century", Font.BOLD, 17));
        label1.setForeground(Color.RED);
        label1.setBounds(300, 70, 400, 100);

        label2 = new JLabel();
        label2.setText("Choose one option to continue : ");
        label2.setFont(new Font("Century", Font.PLAIN, 14));
        label2.setForeground(Color.BLACK);
        label2.setBounds(300, 100, 400, 100);

        deposit = new JButton();
        deposit.setText("Deposit");
        deposit.setBounds(300, 180, 120, 35);
        deposit.addActionListener(this);

        withdraw = new JButton();
        withdraw.setText("Withdraw");
        withdraw.setBounds(450, 180, 120, 35);
        withdraw.addActionListener(this);

        checkbalance = new JButton();
        checkbalance.setText("Check Balance");
        checkbalance.setBounds(300, 250, 120, 35);
        checkbalance.addActionListener(this);

        transferamnt = new JButton();
        transferamnt.setText("Transfer Amount");
        transferamnt.setBounds(450, 250, 120, 35);
        transferamnt.addActionListener(this);

        logout = new JButton();
        logout.setText("Logout");
        logout.setBounds(375, 320, 120, 35);
        logout.addActionListener(this);


        frame = new JFrame();
        frame.setLayout(null);
        frame.add(label1);
        frame.add(label2);
        frame.add(deposit);
        frame.add(withdraw);
        frame.add(checkbalance);
        frame.add(transferamnt);
        frame.add(logout);
        frame.add(imagelabel);
        frame.setSize(900, 550);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    public void actionPerformed(ActionEvent e)
    {
        int iRet = 0;

        if(e.getSource() == deposit)
        {
            DepositPage depositpage = new DepositPage(AccNum, AccPass, Name);
            frame.dispose();
        }
        else if(e.getSource() == withdraw)
        {
            WithdrawPage withdrawpage = new WithdrawPage(AccNum, AccPass, Name);
            frame.dispose();
        }
        else if(e.getSource() == checkbalance)
        {
            Bank_Management_System bmsobj = new Bank_Management_System();
            iRet = bmsobj.checkBalance(Integer.parseInt(AccNum), AccPass);

            JOptionPane.showMessageDialog(frame, "Your Account Balance is : "+iRet+" ₹");
        }
        else if(e.getSource() == transferamnt)
        {
            BankTrasferPage banktrnsf = new BankTrasferPage(AccNum, AccPass, Name);
            frame.dispose();
        }
        else if(e.getSource() == logout)
        {
            iRet = JOptionPane.showConfirmDialog(frame, "Are you sure you want to logout ?","Selct an option", JOptionPane.YES_NO_OPTION);
            if(iRet == JOptionPane.YES_OPTION)
            {
                StartPage startpage = new StartPage();
                frame.dispose();
            }
        }
    }
}

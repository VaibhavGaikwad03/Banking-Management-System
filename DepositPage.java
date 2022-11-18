import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DepositPage implements ActionListener
{
    JFrame frame;
    ImageIcon image;
    JLabel label1, label2, imagelabel;
    JTextField text;
    JButton deposit, cancel;
    String AccNum;
    String AccPass;
    String Name;

    public DepositPage(String AccNum, String AccPass, String Name)
    {
        this.AccNum = AccNum;
        this.AccPass = AccPass;
        this.Name = Name;

        image = new ImageIcon("HDFC.png");
        imagelabel = new JLabel(image);
        Dimension dimension = imagelabel.getPreferredSize();
        imagelabel.setBounds(20, 20, dimension.width, dimension.height);

        label1 = new JLabel();
        label1.setText("HDFC Bank");
        label1.setFont(new Font("Century", Font.BOLD, 20));
        label1.setForeground(Color.RED);
        label1.setBounds(370, 50, 400, 100);

        label2 = new JLabel();
        label2.setText("Enter the Amount you want to Deposit :");
        label2.setFont(new Font("Century", Font.PLAIN, 14));
        label2.setForeground(Color.BLACK);
        label2.setBounds(300, 100, 400, 100);

        text = new JTextField();
        text.setBounds(300, 170, 260, 30);

        deposit = new JButton();
        deposit.setText("Deposit");
        deposit.setBounds(300,210, 80, 30);
        deposit.addActionListener(this);

        cancel = new JButton();
        cancel.setText("Cancel");
        cancel.setBounds(390,210, 80, 30);
        cancel.addActionListener(this);

        frame = new JFrame();
        frame.setLayout(null);
        frame.add(label1);
        frame.add(label2);
        frame.add(text);
        frame.add(deposit);
        frame.add(cancel);
        frame.add(imagelabel);
        frame.setSize(900, 550);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e)
    {
        int i = 0;
        boolean bRet = false;

        if(e.getSource() == deposit)
        {
            int accnum = 0;
            int amnt = 0;
            String Amount = text.getText();
            char cArr[] = Amount.toCharArray();

            for(i = 0; i < cArr.length; i++)
            {
                if((cArr[i] < '0') || (cArr[i] > '9'))
                {
                    break;
                }
            }

            if(Amount.equals(""))
            {
                JOptionPane.showMessageDialog(frame, "Please enter the deposite amount !");
            }
            else if(i != cArr.length)
            {
                JOptionPane.showMessageDialog(frame, "Please enter numbers only !");
            }
            else
            {
                Bank_Management_System bmsobj = new Bank_Management_System();
                bRet = bmsobj.deposit(Integer.parseInt(AccNum), AccPass, Integer.parseInt(Amount));

                if(bRet == true)
                {
                    JOptionPane.showMessageDialog(frame, "Amount deposited successfully.");
                    MenuPage menupage = new MenuPage(AccNum, AccPass, Name);
                    frame.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "Failed to Deposit your amount !");
                }
            }
        }
        else if(e.getSource() == cancel)
        {
            MenuPage menupage = new MenuPage(AccNum, AccPass, Name);
            frame.dispose();
        }
    }
}

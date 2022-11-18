import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginPage implements ActionListener
{
    JFrame frame;
    ImageIcon image;
    JLabel label, accnum, pass, imagelabel;
    JButton login, cancel;
    JTextField username;
    JPasswordField password;
    private int iAttempts;

    public LoginPage()
    {
        iAttempts = 3;

        image = new ImageIcon("HDFC.png");
        imagelabel = new JLabel(image);
        Dimension dimension = imagelabel.getPreferredSize();
        imagelabel.setBounds(20, 20, dimension.width, dimension.height);

        label = new JLabel();
        label.setText("Hello, Welcome to HDFC Bank...");
        label.setFont(new Font("Century", Font.BOLD, 20));
        label.setForeground(Color.RED);
        label.setBounds(300, 70, 400, 100);

        accnum = new JLabel();
        accnum.setText("Account no : ");
        accnum.setFont(new Font("Times new roman", Font.BOLD, 16));
        accnum.setForeground(Color.BLACK);
        accnum.setBounds(300, 140, 200, 100);

        username = new JTextField();
        username.setBounds(400, 175, 200, 30);

        pass = new JLabel();
        pass.setText("Password    : ");
        pass.setFont(new Font("Times new roman", Font.BOLD, 16));
        pass.setForeground(Color.BLACK);
        pass.setBounds(300, 190, 200, 100);

        password = new JPasswordField();
        password.setBounds(400, 225, 200, 30);

        login = new JButton();
        login.setText("Log in");
        login.setBounds(300,270, 80, 25);
        login.addActionListener(this);

        cancel = new JButton();
        cancel.setText("Cancel");
        cancel.setBounds(400,270, 80, 25);
        cancel.addActionListener(this);

        frame = new JFrame();
        frame.setLayout(null);
        frame.add(label);
        frame.add(accnum);
        frame.add(username);
        frame.add(pass);
        frame.add(password);
        frame.add(login);
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
        String uName;
        boolean bRet = false;
        int i = 0;
        String AccountNo = username.getText();
        char Password[] = password.getPassword();
        String sPassword = new String(Password);
        char cArr[] = AccountNo.toCharArray();

        if(e.getSource() == login)
        {
            for(i = 0; i < cArr.length; i++)
            {
                if((cArr[i] < '0') || (cArr[i] > '9'))
                {
                    break;
                }
            }

            if(AccountNo.equals(""))
            {
                JOptionPane.showMessageDialog(frame, "Please enter the username !");
            }
            else if(sPassword.equals(""))
            {
                JOptionPane.showMessageDialog(frame, "Please enter the password !");
            }
            else if(i != cArr.length)
            {
                JOptionPane.showMessageDialog(frame, "Please enter your account number in numeric format !");
            }
            else
            {
                Bank_Management_System obj = new Bank_Management_System();
                bRet = obj.login(Integer.parseInt(AccountNo), sPassword);
                uName = obj.Name;

                if(bRet == true)
                {
                    JOptionPane.showMessageDialog(frame, "Login Successful...", "HDFC Bank", JOptionPane.INFORMATION_MESSAGE);
                    MenuPage menupage = new MenuPage(AccountNo, sPassword, uName);
                    frame.dispose();
                }
                else
                {
                    iAttempts--;
                    JOptionPane.showMessageDialog(frame, "Invalid Account number or Password !", "HDFC Bank", JOptionPane.WARNING_MESSAGE);
                    if(iAttempts == 0)
                    {
                        JOptionPane.showMessageDialog(frame, "you have exceeded maximum login attempts !", "Error", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                }
            }
        }
        else if(e.getSource() == cancel)
        {
            StartPage startpage = new StartPage();
            frame.dispose();
        }
    }
}

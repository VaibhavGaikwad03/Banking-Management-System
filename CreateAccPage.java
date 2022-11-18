import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccPage implements ActionListener
{
    JFrame frame;
    ImageIcon image;
    JLabel label1, label2, accname, pass, confirmpass, imagelabel;
    JTextField name;
    JPasswordField pass1, pass2;
    JButton createacc, cancel;

    public CreateAccPage()
    {
        image = new ImageIcon("HDFC.png");
        imagelabel = new JLabel(image);
        Dimension dimension = imagelabel.getPreferredSize();
        imagelabel.setBounds(20, 20, dimension.width, dimension.height);

        label1 = new JLabel();
        label1.setText("Hello, Welcome to HDFC Bank...");
        label1.setFont(new Font("Century", Font.BOLD, 18));
        label1.setForeground(Color.RED);
        label1.setBounds(300, 50, 300, 100);

        label2 = new JLabel();
        label2.setText("To Create an Account fill the below information : ");
        label2.setFont(new Font("Century", Font.PLAIN, 15));
        label2.setForeground(Color.BLACK);
        label2.setBounds(300, 90, 400, 100);

        accname = new JLabel();
        accname.setText("Enter your name   : ");
        accname.setFont(new Font("Times new roman", Font.BOLD, 16));
        accname.setForeground(Color.BLACK);
        accname.setBounds(300, 140, 200, 100);

        name = new JTextField();
        name.setBounds(435, 175, 200, 30);

        pass = new JLabel();
        pass.setText("Create Password   : ");
        pass.setFont(new Font("Times new roman", Font.BOLD, 16));
        pass.setForeground(Color.BLACK);
        pass.setBounds(300, 190, 200, 100);

        pass1 = new JPasswordField();
        pass1.setBounds(435,225, 200, 30);

        confirmpass = new JLabel();
        confirmpass.setText("Confirm Password : ");
        confirmpass.setFont(new Font("Times new roman", Font.BOLD, 16));
        confirmpass.setForeground(Color.BLACK);
        confirmpass.setBounds(300, 240, 200, 100);

        pass2 = new JPasswordField();
        pass2.setBounds(435,275, 200, 30);

        createacc = new JButton();
        createacc.setText("Create Account");
        createacc.setBounds(300,320, 130, 30);
        createacc.addActionListener(this);

        cancel = new JButton();
        cancel.setText("Cancel");
        cancel.setBounds(440,320, 80, 30);
        cancel.addActionListener(this);


        frame = new JFrame();
        frame.setLayout(null);
        frame.add(label1);
        frame.add(label2);
        frame.add(accname);
        frame.add(name);
        frame.add(pass);
        frame.add(pass1);
        frame.add(confirmpass);
        frame.add(pass2);
        frame.add(createacc);
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
        if(e.getSource() == createacc)
        {
            boolean bRet = false;
            char Pass[] = pass1.getPassword();
            char ConfirmPass[] = pass2.getPassword();
            String sName = name.getText();
            String sPass = new String(Pass);
            String sConfirmPass = new String(ConfirmPass);

            if(!sPass.equals(sConfirmPass))
            {
                JOptionPane.showMessageDialog(frame, "Password does not match !");
            }
            else if(sPass.length() < 8)
            {
                JOptionPane.showMessageDialog(frame, "Password should have minimum 8 alphanumeric characters !");
            }
            else
            {
                Bank_Management_System obj = new Bank_Management_System();
                bRet = obj.createAccount(sName, sPass);

                if(bRet == true)
                {
                    JOptionPane.showMessageDialog(frame, "Your Account Successfully Created...\nYour Account Number is : "+obj.AccNo+"\nKindly note your Account Number.\nThank You!");
                    LoginPage loginpage = new LoginPage();
                    frame.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "Something went wrong !", "Error!", JOptionPane.ERROR_MESSAGE);
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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class StartPage implements ActionListener
{
    JFrame frame;
    ImageIcon image;
    JLabel label, imagelabel;
    JButton login, createacc, exit;
    JPanel panel;
    GridLayout layout;

    public StartPage()
    {
        image = new ImageIcon("HDFC.png");
        imagelabel = new JLabel(image);
        Dimension dimension = imagelabel.getPreferredSize();
        imagelabel.setBounds(20, 20, dimension.width, dimension.height);

        label = new JLabel();
        label.setText("Hello, Welcome to HDFC Bank.");
        label.setFont(new Font("Century", Font.BOLD, 17));
        label.setForeground(Color.RED);
        label.setBounds(295, 80, 500, 100);

        login = new JButton();
        login.setText("Login");
        login.setBounds(350, 200, 150, 40);
        login.addActionListener(this);

        createacc = new JButton();
        createacc.setText("Create an Account");
        createacc.setBounds(350, 280, 150, 40);
        createacc.addActionListener(this);

        exit = new JButton();
        exit.setText("Exit");
        exit.setBounds(350, 360, 150, 40);
        exit.addActionListener(this);


        frame = new JFrame();
        frame.setLayout(null);
        frame.add(label);
        frame.add(imagelabel);
        frame.add(login);
        frame.add(createacc);
        frame.add(exit);
        frame.setSize(900, 550);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == login)
        {
            LoginPage loginobj = new LoginPage();
            frame.dispose();
        }
        else if(e.getSource() == createacc)
        {
            CreateAccPage createacc = new CreateAccPage();
            frame.dispose();
        }
        else
        {
            int iRet = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit ?", "Select an option", JOptionPane.YES_NO_OPTION);
            
            if(iRet == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        }
    }

    public static void main(String args[])
    {
        StartPage obj = new StartPage();
    }
}

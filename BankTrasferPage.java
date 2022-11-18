import java.awt.*;

import javax.print.attribute.standard.MediaSize.NA;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankTrasferPage implements ActionListener
{
    JFrame frame;
    ImageIcon image;
    JLabel label,accnumlabel, amntlabel, imagelabel;
    JButton transfer, cancel;
    JTextField accountnum, amount;
    String AccNum, AccPass, Name, TransferAccName;

    public BankTrasferPage(String AccNum, String AccPass, String Name)
    {
        this.AccNum = AccNum;
        this.AccPass = AccPass;
        this.Name = Name;

        image = new ImageIcon("HDFC.png");
        imagelabel = new JLabel(image);
        Dimension dimension = imagelabel.getPreferredSize();
        imagelabel.setBounds(20, 20, dimension.width, dimension.height);

        label = new JLabel();
        label.setText("HDFC Bank");
        label.setFont(new Font("Century", Font.BOLD, 20));
        label.setForeground(Color.RED);
        label.setBounds(370, 50, 400, 100);

        accnumlabel = new JLabel();
        accnumlabel.setText("Account No : ");
        dimension = accnumlabel.getPreferredSize();
        accnumlabel.setFont(new Font("Times new roman", Font.BOLD, 16));
        accnumlabel.setForeground(Color.BLACK);
        accnumlabel.setBounds(280, 150, dimension.width+20, dimension.height);

        accountnum = new JTextField();
        accountnum.setBounds(380,140,200, 30);

        amntlabel = new JLabel();
        amntlabel.setText("Amount : ");
        dimension = accnumlabel.getPreferredSize();
        amntlabel.setFont(new Font("Times new roman", Font.BOLD, 16));
        amntlabel.setForeground(Color.BLACK);
        amntlabel.setBounds(280, 200, dimension.width+20, dimension.height);

        amount = new JTextField();
        amount.setBounds(380,190,200, 30);

        transfer = new JButton();
        transfer.setText("Transfer");
        transfer.setBounds(280,250, 90, 30);
        transfer.addActionListener(this);

        cancel = new JButton();
        cancel.setText("Cancel");
        cancel.setBounds(390,250, 90, 30);
        cancel.addActionListener(this);

        frame = new JFrame();
        frame.setLayout(null);
        frame.add(imagelabel);
        frame.add(label);
        frame.add(accnumlabel);
        frame.add(accountnum);
        frame.add(amntlabel);
        frame.add(amount);
        frame.add(transfer);
        frame.add(cancel);
        frame.setSize(900, 550);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void actionPerformed(ActionEvent e)
    {
        int i = 0, j = 0;
        int iRet = 0;
        boolean bRet = false;
        String Name = null;
        String accnum = accountnum.getText();
        char cAccNum[] = accnum.toCharArray();
        String transferamount = amount.getText();
        char cTransferAmnt[] = transferamount.toCharArray();

        if(e.getSource() == transfer)
        {
            for(i = 0; i < cAccNum.length; i++)
            {
                if((cAccNum[i] < '0') || (cAccNum[i] > '9'))
                {
                    break;
                }
            }

            for(j = 0; j < cTransferAmnt.length; j++)
            {
                if((cTransferAmnt[j] < '0') || (cTransferAmnt[j] > '9'))
                {
                    break;
                }
            }
            if(accnum.equals(""))
            {
                JOptionPane.showMessageDialog(frame, "Please enter the account number !","Error !", JOptionPane.ERROR_MESSAGE);
            }
            else if(transferamount.equals(""))
            {
                JOptionPane.showMessageDialog(frame, "Please enter the amount !","Error !", JOptionPane.ERROR_MESSAGE);
            }
            else if(i != cAccNum.length)
            {
                JOptionPane.showMessageDialog(frame, "Please enter the account number in numeric format !","Error !", JOptionPane.ERROR_MESSAGE);
            }
            else if(j != cTransferAmnt.length)
            {
                JOptionPane.showMessageDialog(frame, "Please enter the amount in numeric format !","Error !", JOptionPane.ERROR_MESSAGE);
            }
            else 
            {
                Bank_Management_System bmsboj = new Bank_Management_System();
                Name = bmsboj.confirmUser(Integer.parseInt(accnum));

                if(Name == "-1")
                {
                    JOptionPane.showMessageDialog(frame, "Invalid Account Number !", "Error !", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    iRet = JOptionPane.showConfirmDialog(frame, "Name : "+Name, "CONFIRMATION", JOptionPane.OK_CANCEL_OPTION);
                    if(iRet == 0)
                    {
                        bRet = bmsboj.bankTransfer(Integer.parseInt(AccNum), AccPass, Integer.parseInt(accnum), Integer.parseInt(transferamount));
                        if(bRet == true)
                        {
                            JOptionPane.showMessageDialog(frame, "Amount transferred successfully...", "HDFC Bank", JOptionPane.INFORMATION_MESSAGE);
                            MenuPage menupage = new MenuPage(this.AccNum, this.AccPass, this.Name);
                            frame.dispose();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(frame, "Your account balance is lower than the minimum transfer amount !", "Error!", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
            
        }
        else if(e.getSource() == cancel)
        {
            MenuPage menupage = new MenuPage(this.AccNum, this.AccPass, this.Name);
            frame.dispose();
        }
    }
}

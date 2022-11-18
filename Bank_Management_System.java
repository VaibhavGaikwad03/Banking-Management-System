import java.lang.*;
import java.util.*;
import java.sql.*;


public class Bank_Management_System 
{
    private String Driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/bank";
    private String db_uname = "root";
    private String db_pass = "";
    private Connection con = null;
    public int AccNo;
    public String Name;
    public String AccPass;
    public int AccBalance;


    public Bank_Management_System()
    {
        try
        {
            Class.forName(Driver);

            con = DriverManager.getConnection(url, db_uname, db_pass);
    
            Statement st = con.createStatement();
        }
        catch(ClassNotFoundException cobj)
        {
            System.out.println(cobj);
        }
        catch(SQLException sobj)
        {
            System.out.println(sobj);
        }
    }

/* 
##############################################################################
##
##  Function name : login
##  Input : int, String
##  Output : boolean
##  Description : It is used to login into the application
##  Author : Vaibhav Tukaram Gaikwad
##  Date : 26-10-2022
##
##############################################################################
*/

    public boolean login(int AccNo, String AccPass)
    {
        Scanner sobj = new Scanner(System.in);
        boolean bRetFlag = false;
        String query = "select accountno, name, password from hdfcbank"; 

        try
        {
            Statement stmt = this.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(bRetFlag = rs.next())
            {
                if(AccNo == Integer.parseInt(rs.getString("accountno")) && (AccPass.equals(rs.getString("password"))))
                {
                    this.AccNo = Integer.parseInt(rs.getString("accountno"));
                    this.AccPass = rs.getString("password");
                    this.Name = rs.getString("name");
                    break;
                }
                
            }
        }
        catch(SQLException seobj)
        {
            System.out.println(seobj);
        }

        if(bRetFlag == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

/* 
##############################################################################
##
##  Function name : createAccount
##  Input : String, String
##  Output : boolean
##  Description : It is used to create an account into the application
##  Author : Vaibhav Tukaram Gaikwad
##  Date : 26-10-2022
##
##############################################################################
*/

    public boolean createAccount(String uName, String uPass)
    {
        int Balance = 0;
        int iRet = 0;
        String Query = "insert into hdfcbank values (?,?,?,?)";
        Scanner sobj = new Scanner(System.in);

        Random robj = new Random();
        this.AccNo = robj.nextInt(99999999);
        this.Name = uName;
        this.AccPass = uPass;
        this.AccBalance = Balance;

        try
        {
            PreparedStatement ps = this.con.prepareStatement(Query);
            ps.setInt(1, this.AccNo);
            ps.setString(2, this.Name);
            ps.setString(3, this.AccPass);
            ps.setInt(4, this.AccBalance);
            iRet = ps.executeUpdate();
        }
        catch(SQLException sqlobj)
        {
            System.out.println(sqlobj);
        }

        if(iRet == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

/* 
##############################################################################
##
##  Function name : deposit
##  Input : int, String, int
##  Output : boolean
##  Description : It is used to deposit an amount in a bank account
##  Author : Vaibhav Tukaram Gaikwad
##  Date : 26-10-2022
##
##############################################################################
*/

    public boolean deposit(int acc_num, String acc_pass, int depositamnt)
    {
        int db_balance = 0;
        int iRet = 0;
        String SelectQuery = "select balance from hdfcbank where accountno = ? and password = ?";
        String UpdateQuery = "update hdfcbank set balance = ? where accountno = ? and password = ?";

        try
        {
            PreparedStatement ps = con.prepareStatement(SelectQuery);
            ps.setInt(1, acc_num);
            ps.setString(2, acc_pass);

            ResultSet rs = ps.executeQuery();

            rs.next();
            db_balance = Integer.parseInt(rs.getString("balance"));

            depositamnt = depositamnt + db_balance;

            PreparedStatement pst = con.prepareStatement(UpdateQuery);
            pst.setInt(1, depositamnt);
            pst.setInt(2, acc_num);
            pst.setString(3, acc_pass);
            iRet = pst.executeUpdate();
        }
        catch(SQLException seobj)
        {
            System.out.println(seobj);
        }

        if(iRet < 1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

/* 
##############################################################################
##
##  Function name : withdraw
##  Input : int, String, int
##  Output : boolean
##  Description : It is used to withdraw an amount in a bank account
##  Author : Vaibhav Tukaram Gaikwad
##  Date : 26-10-2022
##
##############################################################################
*/

    public boolean withdraw(int acc_num, String acc_pass, int withdrawamnt)
    {
        int db_balance = 0;
        int iRet = 0;
        String SelectQuery = "select balance from hdfcbank where accountno = ? and password = ?";
        String UpdateQuery = "update hdfcbank set balance = ? where accountno = ? and password = ?";

        try
        {
            PreparedStatement ps = con.prepareStatement(SelectQuery);
            ps.setInt(1, acc_num);
            ps.setString(2, acc_pass);
            ResultSet rs =  ps.executeQuery();

            rs.next();

            db_balance = Integer.parseInt(rs.getString("balance"));

            withdrawamnt = db_balance - withdrawamnt;

            PreparedStatement pst = con.prepareStatement(UpdateQuery);

            if(withdrawamnt < 1)
            {
                return false;
            }
            else
            {
                pst.setInt(1, withdrawamnt);
                pst.setInt(2, acc_num);
                pst.setString(3, acc_pass);

                iRet = pst.executeUpdate();
            }

        }
        catch(SQLException seobj)
        {
            System.out.println(seobj);
        }
        if(iRet < 1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

/* 
##############################################################################
##
##  Function name : checkBalance
##  Input : int, String
##  Output : int
##  Description : It is used to check the balance of the bank account
##  Author : Vaibhav Tukaram Gaikwad
##  Date : 26-10-2022
##
##############################################################################
*/

    public int checkBalance(int acc_num, String acc_pass)
    {
        int AccBalance = 0;
        String SelectQuery = "select balance from hdfcbank where accountno = ? and password = ?";

        try
        {
            PreparedStatement pst = con.prepareStatement(SelectQuery);
            pst.setInt(1, acc_num);
            pst.setString(2, acc_pass);
            pst.executeQuery();

            ResultSet rs = pst.getResultSet();
            rs.next();

            AccBalance = rs.getInt("balance");

        }
        catch(SQLException seobj)
        {
            System.out.println(seobj);
        }
        return AccBalance;
    }

    public String confirmUser(int acc_numX)
    {
        String ConfirmName = null;
        String SelectQuery = "select * from hdfcbank where accountno = ?";
        
        try
        {
            PreparedStatement ps = con.prepareStatement(SelectQuery);
            ps.setInt(1, acc_numX);

            ResultSet rs = ps.executeQuery();

            rs.next();
            if(Integer.parseInt(rs.getString("accountno")) == acc_numX)
            {
                ConfirmName = rs.getString("name");
            }
        }
        catch(SQLException obj)
        {
            System.out.println(obj);
        }

        if(ConfirmName == null)
        {
            return "-1";
        }
        else
        {
            return ConfirmName;
        }
    }

/* 
##############################################################################
##
##  Function name : bankTransfer
##  Input : int, String, int, int
##  Output : int
##  Description : It is used to check the balance of the bank account
##  Author : Vaibhav Tukaram Gaikwad
##  Date : 26-10-2022
##
##############################################################################
*/

    public boolean bankTransfer(int acc_num, String acc_pass, int acc_numX, int amount)
    {
        int iRet = 0;
        int db_balance = 0;
        String ConfirmName = null;
        String SelectQuery = "select balance from hdfcbank where accountno = ? and password = ?";
        String UpdateQuery = "update hdfcbank set balance = ? where accountno = ? and password = ?";
        String SelectQueryX = "select * from hdfcbank where accountno = ?";
        String UpdateQueryX = "update hdfcbank set balance = ? where accountno = ?";

        try
        {
            PreparedStatement pstmt = con.prepareStatement(SelectQueryX);
            pstmt.setInt(1, acc_numX);

            ResultSet rslt = pstmt.executeQuery();

            rslt.next();
            if(Integer.parseInt(rslt.getString("accountno")) == acc_numX)
            {

                PreparedStatement ps = con.prepareStatement(SelectQuery);
                ps.setInt(1, acc_num);
                ps.setString(2, acc_pass);

                ResultSet rs = ps.executeQuery();

                rs.next();
                db_balance = Integer.parseInt(rs.getString("balance"));

                if(db_balance < amount)
                {
                    return false;
                }

                db_balance = db_balance - amount;

                PreparedStatement pst = con.prepareStatement(UpdateQuery);
                pst.setInt(1, db_balance);
                pst.setInt(2, acc_num);
                pst.setString(3, acc_pass);
                iRet = pst.executeUpdate();

                db_balance = Integer.parseInt(rslt.getString("balance"));

                db_balance = amount + db_balance;

                PreparedStatement pstment = con.prepareStatement(UpdateQueryX);
                pstment.setInt(1, db_balance);
                pstment.setInt(2, acc_numX);
                iRet = pstment.executeUpdate();

                return true;
            }

        }
        catch(SQLException seobj)
        {
            System.out.println(seobj);
        }
        return false;
    }

    // public static void main(String args[])
    // {
    //     Bank_Management_System bobj = new Bank_Management_System();
    //     bobj.bankTransfer(1, "1", 15210589, 100);
    // }
}
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.sql.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  
public class DepositServlet extends HttpServlet{
public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
res.setContentType("text/html");
PrintWriter out = res.getWriter();
String pwd = req.getParameter("pwd");
String accno = req.getParameter("acc");
String name = req.getParameter("uname");
String amnt = req.getParameter("amnt");
int amount = Integer.parseInt(amnt);
try{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb?useSSL=false","root","Lakshmi@4583");
Statement stm = con.createStatement();
ResultSet rs1 = stm.executeQuery("select * from cus where  Bankaccno='"+accno+"' and  Password='"+pwd+"'");
if(rs1.next()){
ResultSet rs = stm.executeQuery("select Balance from cus where Bankaccno='"+accno+"'");
if(rs.next()){
String n = rs.getString(1);
int balance1 = Integer.parseInt(n);
if(balance1 - amount > 0){
String q1 = "update cus set Balance = Balance + '"+amount+"' where Bankaccno='"+accno+"'";
int x2 = stm.executeUpdate(q1);
out.print("<html><br><br><center>Withdraw successful<br></center></html>");
out.println("<center><h2>Withdraw Succesfull...</h2></center>");
}
else{
out.print("<html><br><br><center>failure<br></center></html>");
out.println("<center><h2>Withdraw Failed due to Insufficient Balance...</h2></center>");
}
}
}
else{

    out.print("<html><br><br><center><img src = loginfailure.png width = 400 height = 400 <br></center></html>");
out.println("<br><br><center>Sender username or password is wrong<br></center>");
}
}
catch(Exception e){
out.print(e);
}
}
}
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.sql.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  
public class TransactionServlet extends HttpServlet{
public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
res.setContentType("text/html");
PrintWriter out = res.getWriter();
String pwd = req.getParameter("pwd");
String saccno = req.getParameter("saccno");
String raccno = req.getParameter("raccno");
String sname = req.getParameter("sname");
String rname = req.getParameter("rname");
String amnt = req.getParameter("amnt");
int amount = Integer.parseInt(amnt);
  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
   LocalDateTime now = LocalDateTime.now();  
try{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb?useSSL=false","root","Lakshmi@4583");
Statement stm = con.createStatement();
ResultSet rs1 = stm.executeQuery("select * from cus where  Bankaccno='"+saccno+"' and  Password='"+pwd+"'");
if(rs1.next()){
ResultSet rs = stm.executeQuery("select Balance from cus where Bankaccno='"+saccno+"'");
if(rs.next()){
String n = rs.getString(1);
int balance1 = Integer.parseInt(n);
if(balance1 - amount > 0){
String q = "update cus set Balance = Balance +'"+amount+"' where Bankaccno='"+raccno+"'";
int x = stm.executeUpdate(q);
String q1 = "update cus set Balance = Balance - '"+amount+"' where Bankaccno='"+saccno+"'";
int x2 = stm.executeUpdate(q1);

    out.print("<html><br><br><center><img src = transactionsuccess.gif width = 400 height = 400 ><br></center></html>");
out.println("<center><h2>Transaction Succesfull...</h2></center>");
String q2 = "insert into transaction values('"+sname+"','"+rname+"',"+amount+",'"+dtf.format(now)+"')";
PreparedStatement stm1 = con.prepareStatement(q2);
int x1 = stm1.executeUpdate();
}
else{

    out.print("<html><br><br><center><img src = loginfailure.png width = 400 height = 400 ><br></center></html>");
out.println("<center><h2>Transaction Failed due to Insufficient Balance...</h2></center>");
String q2 = "insert into transaction values('"+sname+"','"+rname+"',"+amount+",'"+dtf.format(now)+" (Failed) "+"')";
PreparedStatement stm1 = con.prepareStatement(q2);
int x1 = stm1.executeUpdate();
}
}
}
else{

    out.print("<html><br><br><center><img src = loginfailure.png width = 400 height = 400> <br></center></html>");
out.println("<br><br><center>Sender username or password is wrong<br></center>");
}
}
catch(Exception e){
out.print(e);
}
}
}
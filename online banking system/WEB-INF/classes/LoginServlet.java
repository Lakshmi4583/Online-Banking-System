import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.sql.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  
public class LoginServlet extends HttpServlet{
public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
res.setContentType("text/html");
PrintWriter out = res.getWriter();
String acno = req.getParameter("acno");
String pwd = req.getParameter("pwd");
try{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb?useSSL=false","root","Lakshmi@4583");
Statement stm = con.createStatement();
ResultSet rs = stm.executeQuery("select * from cus where Bankaccno='"+acno+"'and Password='"+pwd+"'");
if(rs.next()){
res.sendRedirect("home.html");
}
else{
    out.print("<html><br><br><center><img src = loginfailure.png width = 400 height = 400 <br></center></html>");

out.print("<center><h1>Username or password are wrong...</h1></center>");
}
con.close();
}catch(Exception e){
System.out.println(e.getMessage());
}
}
}
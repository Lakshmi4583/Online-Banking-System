import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.util.*;
public class CustomerServlet extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
{
res.setContentType("text/html");
PrintWriter out=res.getWriter();
out.println("<center><h1>Customer Details</h1></center>");
try{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb?useSSL=false","root","Lakshmi@4583");
Statement stm = con.createStatement();
ResultSet r=stm.executeQuery("select * from cus");
out.println("<center> <table width=100% height=100% border=1px >");
out.println("<tr><th>Bank Account Number</th> <th> Name</th> <th> Email  </th> <th>Balance</th> </tr>");
while(r.next())
{
out.println("<tr><center> <td> "+r.getString(1)+"</td></center> ");
out.println("<center><td> "+r.getString(2)+"</td></center> ");
out.println("<center><td> "+r.getString(3)+"</td></center>");
out.println("<center><td> "+r.getInt(4)+"</td></center></tr> ");
}
out.println("</table></center>");
con.close();
}
catch(SQLException sq)
{
out.println("sql exception"+sq);
}

catch(ClassNotFoundException cl)
{
out.println("class not found"+cl);
}
}
}
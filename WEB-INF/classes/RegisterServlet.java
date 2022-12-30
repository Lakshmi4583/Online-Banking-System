import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class RegisterServlet extends HttpServlet{
public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
res.setContentType("text/html");
PrintWriter out = res.getWriter();
String username = req.getParameter("uname");
String password = req.getParameter("pwd");
int balance = Integer.parseInt(req.getParameter("balance"));
String email = req.getParameter("email");
Random rd = new Random();
List<String> accn = Arrays.asList("0", "1","2","3","4","5","6","7", "8", "9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
String accnn = "";
for(int i = 0;i < 10; i++){
int idx = rd.nextInt(accn.size());
accnn += accn.get(idx);
}
try{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb?useSSL=false","root","Lakshmi@4583");
String q = "insert into cus(Bankaccno, Name, Email, Balance, Password) values(?,?,?,?,?)";
PreparedStatement stm = con.prepareStatement(q);
stm.setString(1, accnn);
stm.setString(2, username);
stm.setString(3, email);
stm.setInt(4, balance);
stm.setString(5, password);
int x = stm.executeUpdate();
System.out.println("Data upadated sucessfully...." + x);
if(x > 0){
out.print("<html><br><br><center><img src = success3.jpeg width = 500 height = 400 <br></center></html>");
out.println("<h2><center>Your account number is " + accnn +" used for login...</center></h2>");
out.print("<html><br><br><br><center><h1>Go to <a href = index.html>Login page </h1><br></center></html>");
}
else{
out.println("<html>Register Not successful</html>");
}
con.close();}
catch(Exception e){System.out.println(e.getMessage());
}}}
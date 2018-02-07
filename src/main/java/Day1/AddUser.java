package Day1;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
public class AddUser extends HttpServlet {
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		Connection conn=null;
		String dml="insert into t_user values(null,?,?,?)";
		request.setCharacterEncoding("UTF-8");
		String uname=request.getParameter("uname");
		String pwd=request.getParameter("pwd");
		String phone=request.getParameter("phone");
		response.setCharacterEncoding("UTF-8");
		try {
			conn=DBUtils.getConnection();
			PreparedStatement ps=conn.prepareStatement(dml);
			ps.setString(1, uname);
			ps.setString(2, pwd);
			ps.setString(3, phone);
			int n=ps.executeUpdate();
			System.out.println("++++"+n);
			if(n>0){
				System.out.println("OJBK");
			}else{
				System.out.println("插入失败");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		System.out.println("uname:"+uname+"pwd"+pwd+"phone"+phone);
		
	}
}

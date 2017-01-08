package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import manager.UserManager;
import DTO.UserDTO;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		response.setContentType("application/json;charset=utf-8");
		
		String userId = request.getParameter("userId");
		String name = request.getParameter("name");
		int platform = Integer.parseInt(request.getParameter("platform"));
		
		UserManager userM = UserManager.getInstance();
		int result = userM.insertUser(userId, name, platform);
		
		String userAgent = request.getHeader("User-Agent").split("/")[0];
		JSONObject registerJson = new JSONObject();
		/*---------------모바일을 사용하는 경우 --------------------*/
		System.out.println("Register Result Code : " + result);
		if ( userAgent.toLowerCase().equals("okhttp")  ) {
			try {
				
				if(result == 1) {
					registerJson.put("resultCode", "1");
					registerJson.put("timestamp", System.currentTimeMillis());
					registerJson.put("userId", userId);
					registerJson.put("name", name);
					registerJson.put("platform", platform);
				} else {
					registerJson.put("resultCode", "0");
					registerJson.put("errorCode", "");
					registerJson.put("errorDescription", "");
				}
			} catch(NullPointerException npe) {
				npe.printStackTrace();
				registerJson.put("resultCode", "-1");
				registerJson.put("errorCode", "");
				registerJson.put("errorDescription", "");
				
			} finally {
				System.out.println(registerJson);
				PrintWriter pw = response.getWriter();
				pw.print(registerJson.toString());
				pw.close();
			}
			return ;
		} 
	}

}

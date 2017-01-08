package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import DTO.MyScoreDTO;
import manager.UserManager;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserManager userMgr;
       
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
		
		userMgr = UserManager.getInstance();
		
		String userId = request.getParameter("userId");
		String service = request.getParameter("service");
		
		System.out.println("User Service : " + service + " / " + userId);
		switch(service) {
		case "delete" :
			jsonOut(response, userMgr.deleteUser(userId));
			break;
		}
	}
	
private void jsonOut(HttpServletResponse response, int result) throws ServletException, IOException {
		
		JSONObject scoreJson = new JSONObject();
		try {
			if(result == 1) {
				scoreJson.put("resultCode", "1");
				scoreJson.put("timestamp", System.currentTimeMillis());
			} else {
				scoreJson.put("resultCode", "1");
				scoreJson.put("errorCode", "");
				scoreJson.put("errorDescription", "");
			}
			
		} catch(NullPointerException npe) {
			npe.printStackTrace();
			scoreJson.put("resultCode", "-1");
			scoreJson.put("errorCode", "");
			scoreJson.put("errorDescription", "");
			
		} finally {
			
			PrintWriter pw = response.getWriter();
			pw.print(scoreJson.toString());
			pw.close();
		}
		return ;
	}

}

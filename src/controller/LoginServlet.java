package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.UserManager;

import org.json.simple.JSONObject;

import DTO.LoginView;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;
//import DAO.ScoreDAO;
//import DTO.ScoreDTO;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("GET request.");
		doPost(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		//System.out.println("POST request.");
		response.setContentType("application/json;charset=utf-8");
		
		String userAgent = request.getHeader("User-Agent").split("/")[0];
		//System.out.println("Login UserAgent : " + userAgent+"\nContentType : " + request.getContentType());
	
		String userId = request.getParameter("userId");
		String name = request.getParameter("name");
		String platformStr = request.getParameter("platform");
		int platform = -1;
		if(platformStr != null) {
			platform = Integer.parseInt(platformStr);
		}
		
		System.out.println("-----------------------------------------\nRequest Id : "+userId+"\nRequest Name : "
				+name+"\nRequest Platform : " + platform);
		
		UserManager userM = UserManager.getInstance();
		LoginView loginView = userM.login(userId,name,platform);

		JSONObject loginJson = new JSONObject();
		/*---------------모바일을 사용하는 경우 --------------------*/
		//if ( userAgent.toLowerCase().equals("okhttp")  ) {
			try {
				loginJson.put("resultCode", "1");
				loginJson.put("timestamp", System.currentTimeMillis());
				loginJson.put("userId", loginView.getUserId());
				loginJson.put("name", loginView.getName());
				loginJson.put("platform", loginView.getPlatform());
				loginJson.put("exp", loginView.getExp());
				loginJson.put("userLevel", loginView.getUserLevel());
				loginJson.put("item1Cnt", loginView.getItem1Cnt());
				loginJson.put("item2Cnt", loginView.getItem2Cnt());
				loginJson.put("item3Cnt", loginView.getItem3Cnt());
				loginJson.put("item4Cnt", loginView.getItem4Cnt());
				
			} catch(NullPointerException npe) {
				npe.printStackTrace();
				loginJson.put("resultCode", "0");
				loginJson.put("errorCode", "");
				loginJson.put("errorDescription", "");
				
			} finally {
				System.out.println("Login Server Response : OK\n-----------------------------------------\n");
				
				PrintWriter pw = response.getWriter();
				pw.print(loginJson.toString());
				pw.close();
			}
			return ;
		//} // if end.	
		
	}
	
	public static String getBody(HttpServletRequest request) throws IOException {
		 
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
 
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        return stringBuilder.toString();
    }
}

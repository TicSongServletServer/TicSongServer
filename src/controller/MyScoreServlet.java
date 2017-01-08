package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.MyScoreManager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import DTO.MyScoreDTO;
import DTO.ScoreView;

/**
 * Servlet implementation class ScoreServlet
 */
public class MyScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MyScoreManager myScoreMgr;
  
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
		
		myScoreMgr = MyScoreManager.getInstance();
		
		String userId = request.getParameter("userId");
		int exp, userLevel = 0;
		
		String service = request.getParameter("service");
		System.out.println("MyScore Service : " + service + " / " + userId);
		switch(service) {
			case "insert" :
				exp = Integer.parseInt(request.getParameter("exp"));
				userLevel = Integer.parseInt(request.getParameter("userLevel"));
				
				jsonOut(response, myScoreMgr.insertMyScore(new MyScoreDTO(userId, exp, userLevel)));
				break;
			
			case "update" :
				exp = Integer.parseInt(request.getParameter("exp"));
				userLevel = Integer.parseInt(request.getParameter("userLevel"));
				
				jsonOut(response, myScoreMgr.updateMyScore(new MyScoreDTO(userId, exp, userLevel)));
				break;
			
			case "get" :
				jsonOut(response, myScoreMgr.getMyScore(userId));
				break;
			
			case "list" :
				List<ScoreView> topRanker = myScoreMgr.getScores();
				//System.out.println("[ Top 20 ]\n" + topRanker + "\n");
				jsonOut(response, topRanker);
				break;
				
			case "friends" :
				String friendsJson = request.getParameter("friends");
				System.out.println("frJson Request : " + friendsJson);
				
				//List<String> fList = friendsJsonToList(friendsJson);
				List<ScoreView> fListOut = myScoreMgr.getFriendScores(friendsJsonToList(friendsJson));
				
				System.out.println("[ Friend List Result ] \n" + fListOut + "\n");
				
				jsonOut(response, fListOut);
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
				scoreJson.put("resultCode", "0");
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
	
	private void jsonOut(HttpServletResponse response, MyScoreDTO score) throws ServletException, IOException {
		JSONObject scoreJson = new JSONObject();
		try {
			if(score != null) {
				scoreJson.put("resultCode", "1");
				scoreJson.put("timestamp", System.currentTimeMillis());
				scoreJson.put("userId", score.getUserId());
				scoreJson.put("exp", score.getExp());
				scoreJson.put("userLevel", score.getUserLevel());
			} else {
				scoreJson.put("resultCode", "0");
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
	
	private void jsonOut(HttpServletResponse response, List<ScoreView> scoreList) throws ServletException, IOException {
		
		JSONArray scoreArr = new JSONArray();
		JSONObject scoreJson = null;
		try {
			if(scoreList != null) {
				for(ScoreView score : scoreList) {
					scoreJson = new JSONObject();
					scoreJson.put("resultCode", "1");
					scoreJson.put("timestamp", System.currentTimeMillis());
					scoreJson.put("userId", score.getUserId());
					scoreJson.put("name", score.getName());
					scoreJson.put("exp", score.getExp());
					scoreJson.put("userLevel", score.getUserLevel());
					
					scoreArr.add(scoreJson);
				}
			} else {
				scoreJson.put("resultCode", "0");
				scoreJson.put("errorCode", "");
				scoreJson.put("errorDescription", "");
			}
			
		} catch(NullPointerException npe) {
			npe.printStackTrace();
			scoreJson.put("resultCode", "-1");
			scoreJson.put("errorCode", "");
			scoreJson.put("errorDescription", "");
			
		} finally {
			//System.out.println(scoreArr);
			PrintWriter pw = response.getWriter();
			pw.print(scoreArr.toString());
			pw.close();
		}
		return ;
	}
	
	/*private void jsonOut(HttpServletResponse response, ArrayList<ScoreView> friendList) throws ServletException, IOException {
		
		System.out.println("friendList : " +friendList );
		JSONArray scoreArr = new JSONArray();
		JSONObject scoreJson = null;
		try {
			if(friendList != null) {
				for(ScoreView friend : friendList) {
					scoreJson = new JSONObject();
					scoreJson.put("resultCode", "1");
					scoreJson.put("timestamp", System.currentTimeMillis());
					scoreJson.put("userId", friend.getUserId());
					scoreJson.put("name", friend.getName());
					scoreJson.put("exp", friend.getExp());
					scoreJson.put("userLevel", friend.getUserLevel());
					
					scoreArr.add(scoreJson);
				}
			} else {
				scoreJson.put("resultCode", "0");
				scoreJson.put("errorCode", "");
				scoreJson.put("errorDescription", "");
			}
			
		} catch(NullPointerException npe) {
			npe.printStackTrace();
			scoreJson.put("resultCode", "-1");
			scoreJson.put("errorCode", "");
			scoreJson.put("errorDescription", "");
			
		} finally {
			System.out.println(scoreArr);
			PrintWriter pw = response.getWriter();
			pw.print(scoreArr.toString());
			pw.close();
		}
		return ;
		
	}*/
	
	private List<String> friendsJsonToList(String jsonArr) {
		
		JSONArray arr = null;
		List<String> list = null;
		try {
			
			JSONParser jsonParser = new JSONParser();
			arr = (JSONArray) jsonParser.parse(jsonArr);
			
			list = new ArrayList<String>(arr.size());
			for(int i=0; i<arr.size(); i++)
				list.add( ((JSONObject)arr.get(i)).get("friendId").toString() );
			
		} catch (ParseException pe) {
			pe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}

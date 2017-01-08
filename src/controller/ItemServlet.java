package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.ItemManager;

import org.json.simple.JSONObject;

import DTO.ItemDTO;

/**
 * Servlet implementation class MyPageServlet
 */
public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ItemManager itemM;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		
		itemM = ItemManager.getInstance();
		
		String userId = request.getParameter("userId");
		int item1Cnt,item2Cnt,item3Cnt,item4Cnt = 0;
		
		String service = request.getParameter("service");
		System.out.println("Item Service : " + service + " / " + userId);
		switch(service) {
		case "insert" :
			item1Cnt = Integer.parseInt(request.getParameter("item1Cnt"));
			item2Cnt = Integer.parseInt(request.getParameter("item2Cnt"));
			item3Cnt = Integer.parseInt(request.getParameter("item3Cnt"));
			item4Cnt = Integer.parseInt(request.getParameter("item4Cnt"));
			
			jsonOut(response, itemM.insertItem(new ItemDTO(userId, item1Cnt,item2Cnt,item3Cnt,item4Cnt)));
			break;
		
		case "update" :
			item1Cnt = Integer.parseInt(request.getParameter("item1Cnt"));
			item2Cnt = Integer.parseInt(request.getParameter("item2Cnt"));
			item3Cnt = Integer.parseInt(request.getParameter("item3Cnt"));
			item4Cnt = Integer.parseInt(request.getParameter("item4Cnt"));
			
			jsonOut(response, itemM.updateItem(new ItemDTO(userId, item1Cnt,item2Cnt,item3Cnt,item4Cnt)));
			break;
		
		case "get" :
			jsonOut(response, itemM.getItem(userId));
			break;
		/*
		case "all" :
			break;*/
		}
	}
	
	private void jsonOut(HttpServletResponse response, int result) throws ServletException, IOException {
		
		JSONObject myPageJson = new JSONObject();
		try {
			if(result == 1) {
				myPageJson.put("resultCode", "1");
				myPageJson.put("timestamp", System.currentTimeMillis());
			} else {
				myPageJson.put("resultCode", "0");
				myPageJson.put("errorCode", "");
				myPageJson.put("errorDescription", "");
			}
			
		} catch(NullPointerException npe) {
			npe.printStackTrace();
			myPageJson.put("resultCode", "-1");
			myPageJson.put("errorCode", "");
			myPageJson.put("errorDescription", "");
		} finally {
			PrintWriter pw = response.getWriter();
			pw.print(myPageJson.toString());
			pw.close();
		}
		return ;
	}
	
	private void jsonOut(HttpServletResponse response, ItemDTO item) throws ServletException, IOException {
		JSONObject myPageJson = new JSONObject();
		try {
			if(item != null) {
				myPageJson.put("resultCode", "1");
				myPageJson.put("timestamp", System.currentTimeMillis());
				myPageJson.put("userId", item.getUserId());
				myPageJson.put("item1Cnt", item.getItem1Cnt());
				myPageJson.put("item2Cnt", item.getItem2Cnt());
				myPageJson.put("item3Cnt", item.getItem3Cnt());
				myPageJson.put("item4Cnt", item.getItem4Cnt());
			} else {
				myPageJson.put("resultCode", "0");
				myPageJson.put("errorCode", "");
				myPageJson.put("errorDescription", "");
			}
			
		} catch(NullPointerException npe) {
			npe.printStackTrace();
			myPageJson.put("resultCode", "-1");
			myPageJson.put("errorCode", "");
			myPageJson.put("errorDescription", "");
			
		} finally {
			
			PrintWriter pw = response.getWriter();
			pw.print(myPageJson.toString());
			pw.close();
		}
		return ;
	}

}

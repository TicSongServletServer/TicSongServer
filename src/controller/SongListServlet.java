package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import module.ReadSongListModule;

public class SongListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String type = request.getParameter("type");
		
		if (type.equals("xml")) {
			response.setContentType("text/xml;charset=utf-8"); 
			songListXmlOut(request, response);
		} else {
			response.setContentType("application/json;charset=utf-8");
			songListJsonOut(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void songListJsonOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String jsonSong = (String)request.getServletContext().getAttribute("jsonSongs");
			PrintWriter pw = response.getWriter();
			pw.print(jsonSong);
			pw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void songListXmlOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			Document doc = (Document)request.getServletContext().getAttribute("xmlSongs");
			PrintWriter pw = response.getWriter();
			
			if (doc != null) {
				pw.print(getStringFromDocument(doc));
				pw.close();
			} else {
				pw.print(getStringFromDocument(new ReadSongListModule(request.getServletContext()).getSongDoc()));
				pw.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private String getStringFromDocument(Document doc) {
	    try {
	       DOMSource domSource = new DOMSource(doc);
	       StringWriter writer = new StringWriter();
	       StreamResult result = new StreamResult(writer);
	       TransformerFactory tf = TransformerFactory.newInstance();
	       Transformer transformer = tf.newTransformer();
	       transformer.transform(domSource, result);
	       return writer.toString();
	    } catch(TransformerException ex) {
	       ex.printStackTrace();
	       return null;
	    } catch(Exception e) {
	    	e.printStackTrace();
	    	return null;
	    }
	} 
}

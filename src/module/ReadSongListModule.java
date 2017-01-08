package module;

import java.io.File;
import java.io.FileReader;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;

public class ReadSongListModule {

	private ServletContext ctx;
	private DocumentBuilder builder;
	private Document songDoc;
	private String jsonSong;
	
	public ReadSongListModule(ServletContext ctx) {
		this.ctx = ctx;
		readSongList();
		readJsonSongList();
	}
	
	private void readSongList() {	
		try {
			
			File fXmlFile = new File(ctx.getRealPath(File.separator + "WEB-INF" + File.separator + "raw" + File.separator + "string-array.xml"));
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			songDoc = builder.parse(fXmlFile);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Fatal : Song List File Read ERROR !!!");
		}
	}
	
	public Document getSongDoc() {
		return songDoc;
	}
	
	private void readJsonSongList() {
		
		JSONParser parser;
		
		try {
			
			parser = new JSONParser();
			Object obj = parser.parse(new FileReader(ctx.getRealPath(File.separator + "WEB-INF" + File.separator + "raw" + File.separator + "songs.json")));
			JSONObject jsonObject = (JSONObject) obj;
			jsonSong = jsonObject.toJSONString();
			System.out.println(jsonSong);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Fatal : Song List File Read ERROR !!!");
		}
	}
	
	public String getJsonSong() {
		return jsonSong;
	}
	
}

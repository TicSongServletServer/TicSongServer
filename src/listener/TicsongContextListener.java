package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import module.ReadSongListModule;

/**
 * Application Lifecycle Listener implementation class TicsongContextListener
 *
 */
public class TicsongContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public TicsongContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent event)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
         // TODO Auto-generated method stub
    	readSongList(event.getServletContext());
    }
    
    public void readSongList(ServletContext ctx) {
    	ReadSongListModule songModule = new ReadSongListModule(ctx);
    	ctx.setAttribute("xmlSongs",songModule.getSongDoc());
    	ctx.setAttribute("jsonSongs",songModule.getJsonSong());
    	
    }
	
}

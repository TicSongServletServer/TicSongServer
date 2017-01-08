package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import module.DBConnection;
import DTO.MyScoreDTO;
import DTO.ScoreView;

public class MyScoreDAO {
	
	private final String USER_CHECK_SQL = "select * from myscore where userid=?;";
	private final String INSERT_MYSCORE_SQL = "insert into myscore (userid,exp,userlevel) values(?,?,?);";
	private final String UPDATE_MYSCORE_SQL = "update myscore set exp=?, userlevel=? where userid=?;";
	private final String RETRIEVE_MYSCORE_SQL = "select * from myscore where userid=?;";
	private final String RETRIEVE_FRIEND_SCORE_SQL = "select * from score_view where userid=?;";
	private final String RETRIEVE_TOP20_SQL = "select * from score_view limit 20;";
	
	private static MyScoreDAO myScoreDAO;
	static {
		myScoreDAO = new MyScoreDAO();
	}
	public static MyScoreDAO getInstance() {
		return myScoreDAO;
	}
	private MyScoreDAO(){}
	
	
	public int insertMyScore(MyScoreDTO myScore) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		int success = 0;
		
		try {
			conn = DBConnection.getInstance().getConn();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(INSERT_MYSCORE_SQL);
			pstmt.setString(1, myScore.getUserId());
			pstmt.setInt(2, myScore.getExp());
			pstmt.setInt(3, myScore.getUserLevel());
			success = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e ) {
	        e.printStackTrace();
	        if (conn != null) {
	            try {
	                System.err.print("Transaction is being Rolled back");
	                conn.rollback();
	            } catch(SQLException se) {
	                se.printStackTrace();
	            }
	        }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	if (conn != null) {
	            try {
	                System.err.print("Transaction is being Rolled back");
	                conn.rollback();
	            } catch(SQLException se) {
	                se.printStackTrace();
	            }
	        }
	    } finally {
	    	if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) {
            	try { 
            		conn.setAutoCommit(true);
                	conn.close(); 
            	} catch(SQLException ex) {}
            } 
	    }
		return success;
	}
	
	public int updateMyScore(MyScoreDTO myScore) {
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		int success = 0;
		
		try {
			conn = DBConnection.getInstance().getConn();

            pstmt = conn.prepareStatement(USER_CHECK_SQL);
            pstmt.setString(1, myScore.getUserId());
            rs = pstmt.executeQuery();
            
            conn.setAutoCommit(false);
                
            if(rs.next()){
					
                    pstmt = conn.prepareStatement(UPDATE_MYSCORE_SQL);
                    pstmt.setInt(1, myScore.getExp());
                    pstmt.setInt(2, myScore.getUserLevel());
                    pstmt.setString(3, myScore.getUserId());
                    pstmt.executeUpdate();
                    
                    success = 1;
				}else
					success = 0;
				
				conn.commit();
		} catch (SQLException e ) {
	        e.printStackTrace();
	        if (conn != null) {
	            try {
	                System.err.print("Transaction is being Rolled back");
	                conn.rollback();
	            } catch(SQLException se) {
	                se.printStackTrace();
	            }
	        }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	if (conn != null) {
	            try {
	                System.err.print("Transaction is being Rolled back");
	                conn.rollback();
	            } catch(SQLException se) {
	                se.printStackTrace();
	            }
	        }
	    } finally {
	    	if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) {
            	try { 
            		conn.setAutoCommit(true);
                	conn.close(); 
            	} catch(SQLException ex) {}
            } 
	    }
		return success;
	}
	
	public MyScoreDTO getMyScore(String userId) {
		
		PreparedStatement pstmt = null;
		MyScoreDTO myScoreDTO = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getInstance().getConn();
			
			pstmt = conn.prepareStatement(RETRIEVE_MYSCORE_SQL);
			pstmt.setString(1,userId);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				myScoreDTO = new MyScoreDTO(userId, rs.getInt("exp"),rs.getInt("userlevel"));
			}
		} catch ( Exception ex ) {
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		return myScoreDTO;
	}
	
	public List<ScoreView> getScores() {
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		List<ScoreView> scoreList = null;
		
		try {
			scoreList = new ArrayList<ScoreView>();
			conn = DBConnection.getInstance().getConn();
			
            pstmt = conn.prepareStatement(RETRIEVE_TOP20_SQL);
            rs = pstmt.executeQuery();
                
            while(rs.next()){
            	scoreList.add(new ScoreView(rs.getString("userId"), rs.getString("name"),
            			rs.getInt("exp"),rs.getInt("userLevel")));
			}
		} catch (SQLException e ) {
	        e.printStackTrace();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	    	if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) {
            	try { 
                	conn.close(); 
            	} catch(SQLException ex) {}
            } 
	    }
		return scoreList;
	}
	
	public List<ScoreView> getFriendScores(List<String> friendList) {
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		List<ScoreView> friends = null;
		
		try {
			friends = new ArrayList<ScoreView>();
			conn = DBConnection.getInstance().getConn();
			
			for(String friendId : friendList) {
				pstmt = conn.prepareStatement(RETRIEVE_FRIEND_SCORE_SQL);
				pstmt.setString(1,friendId);
	            rs = pstmt.executeQuery();
	            
	            if(rs.next()) {
	            	friends.add(new ScoreView(rs.getString("userid"), rs.getString("name")
	            			,rs.getInt("exp"),rs.getInt("userLevel")));
	            }
			}
			
		} catch (SQLException e ) {
	        e.printStackTrace();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	    	if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) {
            	try { 
                	conn.close(); 
            	} catch(SQLException ex) {}
            } 
	    }
		return friends;
	}

}

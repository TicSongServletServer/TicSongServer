package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.ItemDTO;
import DTO.MyScoreDTO;
import module.DBConnection;

public class ItemDAO {
	
	private final String USER_CHECK_SQL = "select * from item where userid=?;";
	private final String INSERT_ITEM_INIT = "insert into item (userid,item1Cnt,item2Cnt,item3Cnt,item4Cnt) values(?,?,?,?,?);";
	private final String UPDATE_ITEM_SQL = "update item set item1Cnt=?, item2Cnt=?, item3Cnt=?, item4Cnt=? where userid=?;";
	private final String RETRIEVE_ITEM_SQL = "select * from item where userid=?;";
	
	private static ItemDAO itemDAO;
	static {
		itemDAO = new ItemDAO();
	}
	public static ItemDAO getInstance() {
		return itemDAO;
	}
	private ItemDAO(){}
	
	
    public int insertItem(ItemDTO item) {
        PreparedStatement pstmt = null;
        Connection conn = null;
        int success = 0;

        try {
            conn = DBConnection.getInstance().getConn();
            conn.setAutoCommit(false);

            pstmt = conn.prepareStatement(INSERT_ITEM_INIT);
            pstmt.setString(1, item.getUserId());
            pstmt.setInt(2, item.getItem1Cnt());
            pstmt.setInt(3, item.getItem2Cnt());
            pstmt.setInt(4, item.getItem3Cnt());
            pstmt.setInt(5, item.getItem4Cnt());
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
    
    public int updateItem(ItemDTO item) {
        PreparedStatement pstmt = null;
        Connection conn = null;
        ResultSet rs = null;
        int success = 0;

        try {
            conn = DBConnection.getInstance().getConn();

            pstmt = conn.prepareStatement(USER_CHECK_SQL);
            pstmt.setString(1, item.getUserId());
            rs = pstmt.executeQuery();
            conn.setAutoCommit(false);
            
            if(rs.next()){
                
                pstmt = conn.prepareStatement(UPDATE_ITEM_SQL);
                pstmt.setInt(1, item.getItem1Cnt());
                pstmt.setInt(2, item.getItem2Cnt());
                pstmt.setInt(3, item.getItem3Cnt());
                pstmt.setInt(4, item.getItem4Cnt());
                pstmt.setString(5, item.getUserId());
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
    
    public ItemDTO getItem(String userId) {
		
		PreparedStatement pstmt = null;
		ItemDTO itemDTO = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getInstance().getConn();
			
			pstmt = conn.prepareStatement(RETRIEVE_ITEM_SQL);
			pstmt.setString(1,userId);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				itemDTO = new ItemDTO(userId, rs.getInt("item1Cnt"), rs.getInt("item2Cnt")
						,rs.getInt("item3Cnt"), rs.getInt("item4Cnt"));
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
		return itemDTO;
	}

}

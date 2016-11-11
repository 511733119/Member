package utils;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import sun.misc.BASE64Encoder;
import dao.Maps;

/**
 * Created by hjc on 2016/6/6.
 */
public class JdbcUtils {

		private static String USERNAME ;
		
		private static String PASSWORD ;
		
		private static String DRIVER ;
		
		private static String URL ;
		
		private static Connection connection;
		
		static{
			loadConfig();
		}
		
		public static void loadConfig(){
			try {
				InputStream inStream = JdbcUtils.class
						.getResourceAsStream("/jdbc.properties");
				Properties prop = new Properties();
				prop.load(inStream);
				USERNAME = prop.getProperty("jdbc.username");
				PASSWORD = prop.getProperty("jdbc.password");
				DRIVER = prop.getProperty("jdbc.driver");
				URL = prop.getProperty("jdbc.url");
			} catch (Exception e) {
				throw new RuntimeException("连接数据库失败",e);
			}
		}
		
		public JdbcUtils(){
			
		}
		
		public static Connection getConnection(){
			try {
				if(connection==null){
					Class.forName(DRIVER);
					connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				}
			} catch (Exception e) {
				throw new RuntimeException("get connection error");
			}
			return connection;
		}
		

    public static void closeAll(Connection con, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
            if (con != null && !con.isClosed()) {
                con.close();
                con.close();
                con = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void closeAll2(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                con.close();
                con = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 取得ResultSet再进一步处理
     * @param sql
     * @return
     */
    public static ResultSet getData(String sql,int start,int end){
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,start);
            preparedStatement.setInt(2,end);
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 取得ResultSet再进一步处理
     * @param sql
     * @return
     */
    public static ResultSet getMyCardData(String sql,String username,int start,int end){
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2,start);
            preparedStatement.setInt(3,end);
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 取得ResultSet再进一步处理
     * @param sql
     * @return
     */
    public static ResultSet getMemberBuyProductData( String username,int start,int end){
        try {
        	String sql =  "SELECT p.ProductID,p.Version,p.PType,p.Content,p.bound FROM user u,product p,buyproduct b where b.id=p.id and u.username=b.username and b.username=? limit ?,?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2,start);
            preparedStatement.setInt(3,end);
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    /**
	 * 登录校验
	 * @param username 用户名
	 * @return 该用户名的密码和姓名的集合
	 * @throws Exception
	 */
	public static ResultSet ManagerLoginCheck(String username) throws Exception{
		
		try {
			String sql ="select password,username from manager where username=\'"+username+"\'";
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			return  preparedStatement.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException("login错误");
		}
	}
	
	/**
	 * 登录校验
	 * @param username 用户名
	 * @return 该用户名的密码和姓名的集合
	 * @throws Exception
	 */
	public static ResultSet PersonalMemberLoginCheck(String username) throws Exception{
		
		try {
			String sql ="select password,username from user where username=\'"+username+"\' and GroupNumber=\'1\'";
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			return  preparedStatement.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException("loginCheck函数错误");
		}
	}
	
	/**
	 * 登录校验
	 * @param username 用户名
	 * @return 该用户名的密码和姓名的集合
	 * @throws Exception
	 */
	public static ResultSet CompanyMemberLoginCheck(String username) throws Exception{
		
		try {
			String sql ="select password,username from user where username=\'"+username+"\' and GroupNumber=\'2\'";
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			return  preparedStatement.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException("loginCheck函数错误");
		}
	}
	
	
	
	/**
	 * 登录校验
	 * @param username 用户名
	 * @return
	 */
	public static ResultSet ManagerLogin( String username ) {
		
		ResultSet resultSet = null;
		
		try {
			resultSet = ManagerLoginCheck(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	/**
	 * 登录校验
	 * @param username 用户名
	 * @return
	 */
	public static ResultSet PersonalMemberLogin( String username ) {
		
		ResultSet resultSet = null;
		
		try {
			resultSet = PersonalMemberLoginCheck(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	/**
	 * 登录校验
	 * @param username 用户名
	 * @return
	 */
	public static ResultSet CompanyMemberLogin( String username ) {
		
		ResultSet resultSet = null;
		
		try {
			resultSet = CompanyMemberLoginCheck(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	/**
	 * 检查该用户名是否已被使用
	 * @param username 用户名
	 * @return 该用户名的信息
	 */
	public static boolean usernameCheckIfExist(String username){
		
		ResultSet resultSet = null;
		boolean bool = false;
		String sql = "select * from user where username=\'"+username+"\'";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				bool = false;
			}else {
				bool = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bool;
	}
	
	/**
	 * 检查该邮箱是否已被使用
	 * @param email 邮箱
	 * @return 该邮件对应的用户的信息
	 */
	public static boolean emailCheckIfExist(String email){
		ResultSet resultSet = null;
		boolean bool = false;
		String sql = "select * from user where email=\'"+email+"\'";
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				bool = false;
			}else {
				bool = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bool;
	}
	
	//对用户密码进行MD5加密
	public static String MD5(String oldStr){

		byte[] oldBytes = oldStr.getBytes();
		MessageDigest md ;
		try {
			md  = MessageDigest.getInstance("MD5");
			byte[]newBytes = md.digest(oldBytes);
			BASE64Encoder encoder = new BASE64Encoder();
			String newStr = encoder.encode(newBytes);
			return newStr;
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
}

	

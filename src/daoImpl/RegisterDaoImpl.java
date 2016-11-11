package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.JdbcUtils;
import dao.Maps;
import dao.RegisterDao;

/*
 * 注册类
 */
public class RegisterDaoImpl implements RegisterDao {
	/*
	 * 检查用户名是否存在
	 */
	@Override
	public boolean checkUsername(String username){
		return JdbcUtils.usernameCheckIfExist(username);
	}
	/*
	 * 检查邮箱是否存在
	 */
	@Override
	public boolean checkEmail(String email){
		return JdbcUtils.emailCheckIfExist(email);
	}
	/*
	 * 注册用户
	 */
	@Override
	public int addUser(String username, String password, String question,
			String answer, String safecode, String img, String email,int GroupNumber,String shenfen) {
		PreparedStatement preparedStatement = null;
		int result = 0;
		String usernumber = getNewId();
//		System.out.println("usernumber:"+usernumber);
		try {
			preparedStatement = JdbcUtils.getConnection().prepareStatement(Maps.getAddMemberSql());
			preparedStatement.setString(1, usernumber);
			preparedStatement.setString(2, username);
			preparedStatement.setString(3, password);
			preparedStatement.setString(4, question);
			preparedStatement.setString(5, answer);
			preparedStatement.setString(6, safecode);
			preparedStatement.setString(7, img);
			preparedStatement.setString(8, email);
			preparedStatement.setInt(9, GroupNumber);
			preparedStatement.setString(10, shenfen);
			result =  preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	/*
	 * 给新注册会员分配ID
	 */
	@Override
	public String getNewId() {
		
		String maxNumber = "";
		String temp ="";
		try {
			PreparedStatement preparedStatement = JdbcUtils.getConnection().prepareStatement(Maps.getSelectMaxIdSql());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				maxNumber = rs.getString(1);
			}
			if(maxNumber==null || "".equals(maxNumber)){
				maxNumber="HY0001";
			}else {
				temp = maxNumber.substring(2);
				int i = Integer.parseInt(temp);
				i++;
				//再还原为字符串
				temp = String.valueOf(i);
				int len = temp.length();
				//凑够4位
				for(int j=0;j<4-len;j++){
					temp = "0"+temp;
				}
				temp = "HY"+temp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return temp;
	}
	/*
	 * 对密码进行加密
	 */
	@Override
	public String MD5(String oldStr) {
	    return JdbcUtils.MD5(oldStr);
	}
}

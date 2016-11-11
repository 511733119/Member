package dao;

import java.sql.ResultSet;

import com.google.gson.JsonObject;

import utils.JdbcUtils;
import utils.JsonUtils;

public class LoginDao {

	/*
	 * 检查如果是管理员登录
	 */
	public ResultSet ManagerLogin(String username) {
		return JdbcUtils.ManagerLogin(username);
	}
	
	/*
	 * 检查如果是个人会员登录
	 */
	public ResultSet PersonalMemberLogin(String username) {
		return JdbcUtils.PersonalMemberLogin(username);
	}
	
	/*
	 * 检查如果是企业会员登录
	 */
	public ResultSet CompanyMemberLogin(String username) {
		return JdbcUtils.CompanyMemberLogin(username);
	}
	
	//会员如果成功登录，则获得两个积分
	public JsonObject update(String username){
        return JsonUtils.getResult(Maps.getUpdateUserPoint(),1,username);
    }

}

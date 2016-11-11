package dao;

import utils.JsonUtils;

import com.google.gson.JsonObject;

import daoImpl.RegisterDaoImpl;

/**
 * Created by hjc on 2016/7/2.
 */
public class MemberDao {

	public static JsonObject add(String usernumber, String username,
			String password, String question, String answer, String safecode,
			String img, String email, String GroupNumber, String shenfen) {
		return JsonUtils.getResult(Maps.getAddPersonalMemberSql(), 10,
				usernumber, username, password, question, answer, safecode,
				img, email, GroupNumber, shenfen);
	}

	public static JsonObject update(String GroupNumber, String shenfen,
			String usernumber) {
		return JsonUtils.getResult(Maps.getUpdateMemberNumberById(),3,GroupNumber, shenfen,
				usernumber);
	}
	
	public static JsonObject updateMember(String question, String answer,
			String safecode, String usernumber) {
		return JsonUtils.getResult(Maps.getUpdateMemberSql(),4,question, answer, safecode,
				usernumber);
	}

	public static JsonObject delete(String usernumber) {
		return JsonUtils.getResult(Maps.getDeletePersonalMemberSql(), 1,
				usernumber);
	}

	public static JsonObject deleteUserinfo(String username) {
		return JsonUtils.getResult(Maps.getDeleteUserinfoSql(), 1,
				username);
	}
	
	public static JsonObject deleteCompanyinfo(String username) {
		return JsonUtils.getResult(Maps.getDeleteCompanyInfoSql(), 1,
				username);
	}
	
	public static JsonObject updatepoint(Integer point, String username) {
		return JsonUtils
				.getResult(Maps.getUpdatePointSql(), 2, point, username);
	}

	public static JsonObject usepoint(Integer point, String username) {
		return JsonUtils.getResult(Maps.getUsePointSql(), 3, username, point,
				username);
	}
}

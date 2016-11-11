package dao;

import java.sql.Date;

import utils.JsonUtils;

import com.google.gson.JsonObject;

/*
 * 添加个人会员信息类
 */
public class AddPersonalMessageDao {

	/*
	 * 完善个人会员信息
	 */
	public JsonObject CompletePersonalMessage( String username,String uname,String sex,Date birthday,
			String zjlb,String zjhm,String szsf,String city,String addr,String yzbm ,String Tel,String homepage,String qq){
		return JsonUtils.getResult(Maps.getAddPersonalMessageSql(),13 ,  username, uname, sex, birthday,
				 zjlb, zjhm, szsf, city, addr, yzbm,Tel,homepage,qq);
	}

}

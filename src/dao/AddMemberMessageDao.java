package dao;

import com.google.gson.JsonObject;

import utils.JsonUtils;

public class AddMemberMessageDao {

	public JsonObject addCompanyMessage(String sql, int total, String username,
			String C_Name, String C_PostCode, String C_Province, String C_City,
			String C_Address, String C_ConactName, String C_Vocation,
			String C_WebSite, String C_size, String C_Tel, String C_BankName,
			String C_Capital) {
		return JsonUtils.getResult(sql, 13, username, C_Name, C_PostCode,
				C_Province, C_City, C_Address, C_ConactName, C_Vocation,
				C_WebSite, C_size, C_Tel, C_BankName, C_Capital);
	}
}

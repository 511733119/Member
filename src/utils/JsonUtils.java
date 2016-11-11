package utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * Created by hjc on 2016/6/6.
 */
public class JsonUtils {

	/**
	 * @param sql
	 *            要执行的SQL语句
	 * @param total
	 *            指定要设置参数个数
	 * @param object
	 *            传入要设置的参数值
	 * @return JsonObject
	 */
	public static JsonObject getResult(String sql, int total, Object... object) {
		try {

			PreparedStatement preparedStatement = JdbcUtils.getConnection()
					.prepareStatement(sql);

			for (int i = 0; i < object.length; i++) {
				preparedStatement.setObject(i + 1, object[i]);
			}

			preparedStatement.execute();
			// initialize an json object
			JsonObject jsonObject = new JsonObject();

			jsonObject.add("success", new JsonPrimitive(true));

			return jsonObject;

		} catch (SQLException e) {
			e.printStackTrace();
			JsonObject jsonObject = new JsonObject();
			// 若失败，输入指定格式json给前台处理
			jsonObject.add("errorMsg", new JsonPrimitive("出错了"));
			return jsonObject;

		}
	}

	/**
	 * @param sql
	 *            要执行的SQL语句
	 * @param total
	 *            指定要设置参数个数
	 * @param object
	 *            传入要设置的参数值
	 * @return JsonObject
	 */
	public static void getResultWithoutReturn(String sql, int total, Object... object) {
		try {

			PreparedStatement preparedStatement = JdbcUtils.getConnection()
					.prepareStatement(sql);

			for (int i = 0; i < object.length; i++) {
				preparedStatement.setObject(i + 1, object[i]);
			}

			preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param sql
	 *            要执行的SQL语句
	 * @param total
	 *            指定要设置参数个数
	 * @param object
	 *            传入要设置的参数值
	 * @return JsonObject
	 */
	public static int getCurrentPointResult(String sql, int total,
			Object... object) {
		int point = 0;
		try {
			// get preparedstatement
			PreparedStatement preparedStatement = JdbcUtils.getConnection()
					.prepareStatement(sql);
			for (int i = 0; i < object.length; i++) {
				preparedStatement.setObject(i + 1, object[i]);
			}

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				point = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return point;
	}

	/**
	 * @param sql
	 *            要执行的SQL语句
	 * @param total
	 *            指定要设置参数个数
	 * @param object
	 *            传入要设置的参数值
	 * @return JsonObject
	 */
	public static boolean getCheckCardNumberResult(String sql, int total,
			Object... object) {
		try {
			// get preparedstatement
			PreparedStatement preparedStatement = JdbcUtils.getConnection()
					.prepareStatement(sql);
			for (int i = 0; i < object.length; i++) {
				preparedStatement.setObject(i + 1, object[i]);
			}

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				return true;
			}

			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}
	}

	/**
	 * 以json格式返回会员信息
	 * 
	 * @param sql
	 * @param start
	 * @param end
	 * @return
	 */
	public static JsonArray getJsondata(String sql, int start, int end) {
		ResultSet data = JdbcUtils.getData(sql, start, end);
		JsonArray jsonArray = new JsonArray();
		try {
			while (data.next()) {
				JsonObject jsonObject = new JsonObject();
				jsonObject.add("usernumber",
						new JsonPrimitive(data.getString("usernumber")));
				jsonObject.add("username",
						new JsonPrimitive(data.getString("username")));
				jsonObject.add("email",
						new JsonPrimitive(data.getString("email")));
				jsonObject.add("question",
						new JsonPrimitive(data.getString("question")));
				jsonObject.add("answer",
						new JsonPrimitive(data.getString("answer")));
				jsonObject.add("safecode",
						new JsonPrimitive(data.getString("safecode")));
				jsonArray.add(jsonObject);

			}
			return jsonArray;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	/**
	 * 以json格式返回会员积分
	 * 
	 * @param sql
	 * @param start
	 * @param end
	 * @return
	 * @throws SQLException
	 */

	public static JsonArray getJsonPointdata(String sql, int start, int end) {
		ResultSet data = JdbcUtils.getData(sql, start, end);
		JsonArray jsonArray = new JsonArray();

		try {
			while (data.next()) {
				JsonObject jsonObject = new JsonObject();
				jsonObject.add("username",
						new JsonPrimitive(data.getString("username")));
				jsonObject
						.add("point", new JsonPrimitive(data.getInt("point")));
				jsonArray.add(jsonObject);

			}
			return jsonArray;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	/**
	 * 以json格式返回商品积分
	 * 
	 * @param sql
	 * @param start
	 * @param end
	 * @return
	 * @throws SQLException
	 */

	public static JsonArray getJsonBounddata(String sql, int start, int end) {
		ResultSet data = JdbcUtils.getData(sql, start, end);
		JsonArray jsonArray = new JsonArray();

		try {
			while (data.next()) {
				JsonObject jsonObject = new JsonObject();
				jsonObject.add("id", new JsonPrimitive(data.getInt("id")));
				jsonObject.add("ProductID",
						new JsonPrimitive(data.getString("ProductID")));
				jsonObject.add("Version",
						new JsonPrimitive(data.getString("Version")));
				jsonObject.add("PType",
						new JsonPrimitive(data.getString("PType")));
				jsonObject.add("Content",
						new JsonPrimitive(data.getString("Content")));
				jsonObject
						.add("bound", new JsonPrimitive(data.getInt("bound")));
				jsonArray.add(jsonObject);

			}
			return jsonArray;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	/**
	 * 以json格式返回会员信息
	 * 
	 * @param sql
	 * @param start
	 * @param end
	 * @return
	 */
	public static JsonArray getAllMemberJsondata(String sql, int start, int end) {
		ResultSet data = JdbcUtils.getData(sql, start, end);
		JsonArray jsonArray = new JsonArray();
		try {
			while (data.next()) {
				JsonObject jsonObject = new JsonObject();
				jsonObject.add("usernumber",
						new JsonPrimitive(data.getString("usernumber")));
				jsonObject.add("username",
						new JsonPrimitive(data.getString("username")));
				jsonObject.add("GroupNumber",
						new JsonPrimitive(data.getString("GroupNumber")));
				jsonArray.add(jsonObject);

			}
			return jsonArray;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	/**
	 * 以json格式返回已购买点卡的信息
	 * 
	 * @param sql
	 * @param start
	 * @param end
	 * @return
	 */
	public static JsonArray getAllMyCardJsondata(String sql, String username,
			int start, int end) {
		ResultSet data = JdbcUtils.getMyCardData(sql, username, start, end);
		JsonArray jsonArray = new JsonArray();
		try {
			while (data.next()) {
				JsonObject jsonObject = new JsonObject();
				jsonObject.add("CardNumber",
						new JsonPrimitive(data.getString("CardNumber")));
				jsonObject.add("CardPassword",
						new JsonPrimitive(data.getString("CardPassword")));
				jsonObject.add("Point",
						new JsonPrimitive(data.getString("Point")));
				jsonObject.add("TimeOutDate",
						new JsonPrimitive(data.getString("TimeOutDate")));
				jsonObject.add("AddTime",
						new JsonPrimitive(data.getString("AddTime")));
				jsonArray.add(jsonObject);

			}
			return jsonArray;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	/**
	 * 以json格式返回公告信息数据
	 * 
	 * @param sql
	 * @param start
	 * @param end
	 * @return
	 * @throws SQLException
	 */

	public static JsonArray getJsonAnnouncementdata(String sql, int start,
			int end) {
		ResultSet data = JdbcUtils.getData(sql, start, end);
		JsonArray jsonArray = new JsonArray();

		try {
			while (data.next()) {
				JsonObject jsonObject = new JsonObject();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String pubdate = format.format(data.getTimestamp("pubdate"));
				// String AddTime=format.format(data.getTimestamp("AddTime"));
				// String UserTime=format.format(data.getTimestamp("UserTime"));
				jsonObject.add("aid", new JsonPrimitive(data.getInt("aid")));
				jsonObject.add("title",
						new JsonPrimitive(data.getString("title")));
				jsonObject.add("people",
						new JsonPrimitive(data.getString("people")));
				jsonObject.add("pubdate", new JsonPrimitive(pubdate));

				jsonArray.add(jsonObject);

			}
			return jsonArray;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	/**
	 * 以json格式返回购买商品列表信息
	 * 
	 * @param sql
	 * @param start
	 * @param end
	 * @return
	 */
	public static JsonArray getShowBuyProductJsondata(String sql, int start,
			int end) {
		ResultSet data = JdbcUtils.getData(sql, start, end);
		JsonArray jsonArray = new JsonArray();
		try {
			while (data.next()) {
				JsonObject jsonObject = new JsonObject();
				jsonObject.add("username",
						new JsonPrimitive(data.getString("username")));
				jsonObject.add("shenfen",
						new JsonPrimitive(data.getString("shenfen")));
				jsonObject.add("ProductID",
						new JsonPrimitive(data.getString("ProductID")));
				jsonObject.add("Version",
						new JsonPrimitive(data.getString("Version")));
				jsonObject.add("PType",
						new JsonPrimitive(data.getString("PType")));
				jsonObject.add("Content",
						new JsonPrimitive(data.getString("Content")));
				jsonObject
						.add("bound", new JsonPrimitive(data.getInt("bound")));
				jsonArray.add(jsonObject);

			}
			return jsonArray;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	/**
	 * 以json格式返回会员所购买商品的列表信息
	 * 
	 * @param sql
	 * @param start
	 * @param end
	 * @return
	 */
	public static JsonArray getShowMemberBuyProductJsondata(String username,
			int start, int end) {
		ResultSet data = JdbcUtils
				.getMemberBuyProductData(username, start, end);
//		System.out.println(data);
		JsonArray jsonArray = new JsonArray();
		try {
			while (data.next()) {
				JsonObject jsonObject = new JsonObject();

				jsonObject.add("ProductID",
						new JsonPrimitive(data.getString("ProductID")));
				jsonObject.add("Version",
						new JsonPrimitive(data.getString("Version")));
				jsonObject.add("PType",
						new JsonPrimitive(data.getString("PType")));
				jsonObject.add("Content",
						new JsonPrimitive(data.getString("Content")));
				jsonObject
						.add("bound", new JsonPrimitive(data.getInt("bound")));
				jsonArray.add(jsonObject);

			}
			return jsonArray;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	/**
	 * 以json格式返回商品信息数据
	 * 
	 * @param sql
	 * @param start
	 * @param end
	 * @return
	 * @throws SQLException
	 */

	public static JsonArray getJsonProductdata(String sql, int start, int end) {
		ResultSet data = JdbcUtils.getData(sql, start, end);
		JsonArray jsonArray = new JsonArray();

		try {
			while (data.next()) {
				JsonObject jsonObject = new JsonObject();
				
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String addTime = format.format(data.getTimestamp("addTime"));
				
				jsonObject.add("id", new JsonPrimitive(data.getInt("id")));
				jsonObject.add("ProductID",
						new JsonPrimitive(data.getString("ProductID")));
				jsonObject.add("Version",
						new JsonPrimitive(data.getString("Version")));
				jsonObject.add("PType",
						new JsonPrimitive(data.getString("PType")));
				jsonObject.add("Content",
						new JsonPrimitive(data.getString("Content")));
				jsonObject
						.add("bound", new JsonPrimitive(data.getInt("bound")));
				jsonObject.add("useNum",
						new JsonPrimitive(data.getInt("useNum")));

				jsonObject.add("addTime", new JsonPrimitive(addTime));
				jsonObject.add("URL_1",
						new JsonPrimitive(data.getString("URL_1")));
				jsonObject.add("UserDel",
						new JsonPrimitive(data.getInt("UserDel")));
				jsonArray.add(jsonObject);

			}
			return jsonArray;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}


	/**
	 * 以json格式返回点卡信息数据
	 * 
	 * @param sql
	 * @param start
	 * @param end
	 * @return
	 * @throws SQLException
	 */

	public static JsonArray getJsonCarddata(String sql, int start, int end) {
		ResultSet data = JdbcUtils.getData(sql, start, end);
		JsonArray jsonArray = new JsonArray();

		try {
			while (data.next()) {
				JsonObject jsonObject = new JsonObject();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String TimeOutDate = format.format(data
						.getTimestamp("TimeOutDate"));
				// String AddTime=format.format(data.getTimestamp("AddTime"));
				// String UserTime=format.format(data.getTimestamp("UserTime"));
				jsonObject.add("CardID",
						new JsonPrimitive(data.getInt("CardID")));
				jsonObject.add("CardNumber",
						new JsonPrimitive(data.getString("CardNumber")));
				jsonObject.add("CardPassword",
						new JsonPrimitive(data.getString("CardPassword")));
				jsonObject
						.add("Point", new JsonPrimitive(data.getInt("Point")));
				jsonObject.add("TimeOutDate", new JsonPrimitive(TimeOutDate));
				jsonObject
						.add("isUse", new JsonPrimitive(data.getInt("isUse")));
				jsonArray.add(jsonObject);

			}
			return jsonArray;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

}

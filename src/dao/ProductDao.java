package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import utils.JdbcUtils;
import utils.JsonUtils;

import com.google.gson.JsonObject;
import com.mysql.jdbc.PreparedStatement;

/**
 * Created by hjc on 2016/6/12.
 */
public class ProductDao {

    public static JsonObject add(String ProductID,String Version,String PType,String Content,int bound,String URL_1,Date addTime){
        return JsonUtils.getResult(Maps.getAddProductSql(),7,ProductID,Version,PType,Content,bound,URL_1,addTime);
    }
    
    public static JsonObject addToBuyProduct(String username,int id){
        return JsonUtils.getResult(Maps.getAddToBuyProduct(),2,username,id);
    }
    
    public static JsonObject update(int id,String ProductID,String Version,String PType,String Content){
        return JsonUtils.getResult(Maps.getUpdateProductSql(),5,ProductID,Version,PType,Content,id);
    }
    
    public static JsonObject updatePoint(int point , String username){
        return JsonUtils.getResult(Maps.getUpdateMemberPoint(),2,point,username);
    }

    public static JsonObject updateProductUseNum(int id){
        return JsonUtils.getResult(Maps.getUpdateProductUsenumSql(),1,id);
    }
    
    public static JsonObject delete(String ids){
        return JsonUtils.getResult(Maps.getDeleteProductSql(ids),0);
    }

	public JsonObject updatebound(Integer bound, Integer id) {
		return JsonUtils.getResult(Maps.getUpdateBoundSql(), 2,bound,id);
	}
    
    public static int getProductid(String sql,String ProductID){
    	int id = 0;
    	try {
			PreparedStatement preparedStatement = (PreparedStatement) JdbcUtils.getConnection().prepareStatement(sql);
			preparedStatement.setString(1, ProductID);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				id=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return id;
    }
}

package dao;

import java.sql.Timestamp;
import java.util.Date;

import utils.JsonUtils;

import com.google.gson.JsonObject;


public class CardDao {

    public static JsonObject add(String CardNumber,String CardPassword ,int Point,Timestamp TimeOutDate,Timestamp addTime){
        return JsonUtils.getResult(Maps.getAddCardSql(),5,CardNumber,CardPassword,Point,TimeOutDate,addTime);
    }
    
    public static JsonObject update(int CardID,String CardNumber,String CardPassword ,int point,String TimeOutDate){
        return JsonUtils.getResult(Maps.getUpdateCardSql(),5,CardNumber,CardPassword,point,TimeOutDate,CardID);
    }

    public static JsonObject delete(String ids){
        return JsonUtils.getResult(Maps.getDeleteCardSql(ids),0);
    }
    
    public static JsonObject Recharge(String CardNumber,String CardPassword,int Point,String username,Date TimeOutDate,Date AddTime){
    	return JsonUtils.getResult(Maps.getAddCardSql(), 6, CardNumber,CardPassword,Point,username,TimeOutDate,AddTime);
    }
    
    public static JsonObject exchange(String CardNumber,int point,int Point,String username){
    	return JsonUtils.getResult(Maps.getExchangeSql(),5, CardNumber,point,Point,username,CardNumber);
    }
    
    public static boolean checkCardNumber(String CardNumber,String CardPassword){
    	return JsonUtils.getCheckCardNumberResult(Maps.getCheckCardNumberSql(), 2, CardNumber,CardPassword);
    }
    
    public static int showCurrentPoint(String CardNumber){
    	return JsonUtils.getCurrentPointResult(Maps.getSelectPointFromCardSql(), 1, CardNumber);
    }
}

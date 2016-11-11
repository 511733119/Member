package dao;

import java.util.Date;

import utils.JsonUtils;

import com.google.gson.JsonObject;

public class AnnouncementDao {

	public JsonObject add(String title, String people, java.sql.Date pubdate,
			String announcement) {
		return JsonUtils.getResult(Maps.getAddAnnouncementSql(), 4 ,  title, people, pubdate, announcement);
	}

    public static JsonObject delete(String ids){
        return JsonUtils.getResult(Maps.getDeleteAnnouncementSql(ids),0);
    }
    
    public static void addComment(int aid,String username,String content){
    	 JsonUtils.getResultWithoutReturn(Maps.getAddCommentSql(),4, aid,username,content);
    }
}

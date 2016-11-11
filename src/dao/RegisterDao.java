package dao;

/*
 * 注册接口
 */
public interface RegisterDao {
	
	/*
	 * 检查用户名是否存在
	 */
	public boolean checkUsername(String username);
	/*
	 * 检查邮箱是否存在
	 */
	public boolean checkEmail(String email);
	
	/*
	 * 注册用户
	 */
	public int addUser( String username,String password,String question,
			String answer,String safecode,String img,String email,int GroupNumber,String shenfen);

	/*
	 * 为新注册用户分配ID
	 */
	public String getNewId();
	
	/*
	 * 对密码进行加密
	 */
	public String MD5(String oldStr);
    	
	
}

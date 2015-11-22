public class ResultCode {
	
	public static final int RESULT_DEFAULT = -1;
	
	/**
	 * 执行成功
	 */
	public static final int RESULT_OK = 0;
	
	/**
	 * 参数错误
	 */
	public static final int RESULT_PARAM_ERROR = 1;

	/**
	 * 账户不存在
	 */
	public static final int RESULT_NO_ACCOUNT = 2;

	/**
	 * 密码错误
	 */
	public static final int RESULT_WRONG_PASSWORD = 3;

	/**
	 * 账户已存在
	 */
	public static final int RESULT_ACCOUNT_EXISTS = 4;

}

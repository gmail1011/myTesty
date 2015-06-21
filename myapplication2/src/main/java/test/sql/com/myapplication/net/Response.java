package test.sql.com.myapplication.net;


/**
 * 响应基类
 * 
 * @author tucheng
 * @date 2015-06-21
 */
public class Response {
	/**
	 * 响应类型
	 */
	private final String type;
	
	/**
	 * 失败或者成功
	 */
	private boolean isSuccess;
	
	/**
	 * 错误码
	 */
	private int errorCode;
	/**
	 * 错误原因
	 */
	private String errorMsg;

	/**
	 * 
	 * @param type  String
	 */
	public Response(String type) {
		this.type = type;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean flag) {
		this.isSuccess = flag;
	}

	public String getContentTag() {
		return null;
	}
	
	public String[] getContentTags() {
		return null;
	}
	
	public String getType() {
		return type;
	}

	/**
	 * @param content String
	 */
	public void parseContent(String content) {
		
	}
	
	/**
	 * @param content
	 * @param index
	 */
	public void parseContent(String content,int index) {
		
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}

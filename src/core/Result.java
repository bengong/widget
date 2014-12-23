package core;

import org.nutz.json.Json;


public class Result {

	public String code;
	public String message;
	public String data;
	public String description;
	
	public Throwable throwable;
	
	/** 成功标识 */
	public final static String SUCCESS = "success";
	public final static String WARN = "warn";
	public final static String ERROR = "error";
	public final static String THROWABLE = "throwable";
	
	public Result() {
		super();
		
		this.code = SUCCESS;
	}
	
	public static Result sucess(String message) {
		Result result = new Result();
		
		result.code = SUCCESS;
		result.message = message;
		
		return result;
	}
	
	public static Result warn(String message) {
		Result result = new Result();
		
		result.code = WARN;
		result.message = message;
		
		return result;
	}
	
	public static Result error(String message) {
		Result result = new Result();
		
		result.code = ERROR;
		result.message = message;
		
		return result;
	}
	
	public static Result exception(Throwable throwable) {
		Result result = new Result();
		
		result.code = THROWABLE;
		result.message = throwable.getMessage();
		result.throwable = throwable;
		
		return result;
	}
	
	public boolean isSuccess() {
		return code.toLowerCase().equalsIgnoreCase(SUCCESS);
	}
	
	public boolean isWarn() {
		return code.equalsIgnoreCase(WARN);
	}
	
	public boolean isError() {
		return code.equalsIgnoreCase(ERROR);
	}
	
	public boolean isThrowable() {
		return code.equalsIgnoreCase(THROWABLE);
	}
	
	public <T> T fromJson(Class<T> type) {
		return Json.fromJson(type, data);
	}
}

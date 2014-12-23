package core.widget;

import java.util.HashMap;
import java.util.Map;

/**
 * 註冊中心。
 */
public final class Register {

	public static Map<String, Provider> map = new HashMap<String, Provider>();
	
	public static void register(String key, Provider provider) {
		map.put(key, provider);
	}
	
	public static <T> T get(Class<T> clazz) {
		return (T)map.get(clazz.getName());
	}
}

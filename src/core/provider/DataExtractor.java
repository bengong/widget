package core.provider;

import core.widget.Provider;

/**
 * 數據抽取工具。
 */
public interface DataExtractor extends Provider {
	
	public Object getValue(Object data, String key);
}

package com.zongtui.filter.simhash;
/**
 * 数据本地存储的服务接口
 * 
 * ClassName: KeyValueDataStore <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * date: 2015年4月13日 下午11:46:26 <br/>
 *
 * @author Administrator
 * @version 
 * @since JDK 1.7
 */
public interface KeyValueDataStore<K, V> {
	/**
	 * 将数据放入到数据库中
	 * 
	 * @param key
	 *            主键
	 * @param value
	 *            值
	 */
	void putToStore(String name, K key, V value);

	/**
	 * 从存储中获取指定键对应的值
	 * 
	 * @param key
	 *            主键
	 */
	V getFromStore(String name, K key);

	/**
	 * 从数据存储中删除指定的记录
	 * 
	 * @param key
	 *            主键
	 */
	void deleteFromStore(String name, K key);
}

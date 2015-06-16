package com.zongtui.filter.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.Bytes;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryOperators;
import com.zongtui.filter.mongodb.MongoDbConn;

public class MongoDB {
	
	//使用数据库连接池进行链接
	DB  mongoConn = MongoDbConn.getInstance().getDBConnection();
	DBCollection coll = mongoConn.getCollection("webpage");
	/*
	 * 查询表中的所有数据
	 */
	private void queryAll() {
	    print("查询表中的所有数据：");
	    //db游标
	    DBCursor cur = coll.find();
	    while (cur.hasNext()) {
	        print(cur.next());
	    }
	}
	/*
	 * 个数
	 */
	private void queryAllCount() {
		print("count: " + coll.count());
	}
	
	
	/*
	 * 添加一条操作
	 */
	public void addOnlyOne() {
		 DBObject user = new BasicDBObject();
	    user.put("name", "hoojo");
	    user.put("age", 24);
	    //users.save(user)保存，getN()获取影响行数
	    //print(users.save(user).getN());
	    
	    //扩展字段，随意添加字段，不影响现有数据
	    user.put("sex", "男");
	    print(coll.save(user).getN());
		
	}
	/*
	 * 添加list
	 */
	public void addList() {
		 DBObject user = new BasicDBObject();
		//添加List集合
	    List<DBObject> list = new ArrayList<DBObject>();
	    list.add(user);
	    DBObject user2 = new BasicDBObject("name", "lucy");
	    user.put("age", 22);
	    list.add(user2);
	    //添加List集合
	    print(coll.insert(list).getN());
	    //查询下数据，看看是否添加成功
	    print("count: " + coll.count());
	    queryAll();
		
	}
	/*
	 * 删除单条
	 */
	public void removeOnlyOne() {
//	    queryAll();
	    print("删除id = 55803d6c56c2b8d144510ae5：" + coll.remove(new BasicDBObject("_id", new ObjectId("55803d6c56c2b8d144510ae5"))).getN());
	}
	/*
	 * 删除条件
	 */
	public void remove() {
//	    queryAll();
	    print("remove age >= 24: " + coll.remove(new BasicDBObject("age", new BasicDBObject("$gte", 24))).getN());
	}
	
	/*
	 * 修改
	 */
	
	public void modify() {
	    print("修改：" + coll.update(new BasicDBObject("_id", new ObjectId("4dde25d06be7c53ffbd70906")), new BasicDBObject("age", 99)).getN());
	    print("修改：" + coll.update(
	            new BasicDBObject("_id", new ObjectId("4dde2b06feb038463ff09042")), 
	            new BasicDBObject("age", 121),
	            true,//如果数据库不存在，是否添加
	            false//多条修改
	            ).getN());
	    print("修改：" + coll.update(
	            new BasicDBObject("name", "haha"), 
	            new BasicDBObject("name", "dingding"),
	            true,//如果数据库不存在，是否添加
	            true//false只修改第一天，true如果有多条就不修改
	            ).getN());
	    
	    //当数据库不存在就不修改、不添加数据，当多条数据就不修改
	    //print("修改多条：" + coll.updateMulti(new BasicDBObject("_id", new ObjectId("4dde23616be7c19df07db42c")), new BasicDBObject("name", "199")));
	}
	
	
	public void query() {
	    //查询所有
	    //queryAll();
	    
	    //查询id = 4de73f7acd812d61b4626a77
	    print("find id = 4de73f7acd812d61b4626a77: " + coll.find(new BasicDBObject("_id", new ObjectId("4de73f7acd812d61b4626a77"))).toArray());
	    
	    //查询age = 24
	    print("find age = 24: " + coll.find(new BasicDBObject("age", 24)).toArray());
	    
	    //查询age >= 24
	    print("find age >= 24: " + coll.find(new BasicDBObject("age", new BasicDBObject("$gte", 24))).toArray());
	    print("find age <= 24: " + coll.find(new BasicDBObject("age", new BasicDBObject("$lte", 24))).toArray());
	    
	    print("查询age!=25：" + coll.find(new BasicDBObject("age", new BasicDBObject("$ne", 25))).toArray());
	    print("查询age in 25/26/27：" + coll.find(new BasicDBObject("age", new BasicDBObject(QueryOperators.IN, new int[] { 25, 26, 27 }))).toArray());
	    print("查询age not in 25/26/27：" + coll.find(new BasicDBObject("age", new BasicDBObject(QueryOperators.NIN, new int[] { 25, 26, 27 }))).toArray());
	    print("查询age exists 排序：" + coll.find(new BasicDBObject("age", new BasicDBObject(QueryOperators.EXISTS, true))).toArray());
	    
	    print("只查询age属性：" + coll.find(null, new BasicDBObject("age", true)).toArray());
	    print("只查属性：" + coll.find(null, new BasicDBObject("age", true), 0, 2).toArray());
	    print("只查属性：" + coll.find(null, new BasicDBObject("age", true), 0, 2, Bytes.QUERYOPTION_NOTIMEOUT).toArray());
	    
	    //只查询一条数据，多条去第一条
	    print("findOne: " + coll.findOne());
	    print("findOne: " + coll.findOne(new BasicDBObject("age", 26)));
	    print("findOne: " + coll.findOne(new BasicDBObject("age", 26), new BasicDBObject("name", true)));
	    
	    //查询修改、删除
	    print("findAndRemove 查询age=25的数据，并且删除: " + coll.findAndRemove(new BasicDBObject("age", 25)));
	    
	    //查询age=26的数据，并且修改name的值为Abc
	    print("findAndModify: " + coll.findAndModify(new BasicDBObject("age", 26), new BasicDBObject("name", "Abc")));
	    print("findAndModify: " + coll.findAndModify(
	        new BasicDBObject("age", 28), //查询age=28的数据
	        new BasicDBObject("name", true), //查询name属性
	        new BasicDBObject("age", true), //按照age排序
	        false, //是否删除，true表示删除
	        new BasicDBObject("name", "Abc"), //修改的值，将name修改成Abc
	        true, 
	        true));
	    
	    queryAll();
	}
	
	
	

	
	
	
	
	/*
	 * 打印操作
	 */
    public void print(Object o) {
        System.out.println(o);
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MongoDB qureryAll= new MongoDB();
		qureryAll.queryAll();
		qureryAll.queryAllCount();
//		qureryAll.remove();
	}

}

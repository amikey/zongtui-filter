package com.zongtui.filter.mongodb;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;
import com.zongtui.filter.config.ConfigManager;

public class MongoDbConn
{
  private static MongoDbConn instance = null;

  private String host = null;
  private int port = 0;
  private List<ServerAddress> replSet = null;
  private MongoOptions options = null;
  private String database = null;
  private String username = null;
  private String password = null;
  private Mongo mongo = null;
  private DB db = null;

  private MongoDbConn()
  {
    this.host = ConfigManager.getInstance().getConfigValue("db_ip");
    this.port = Integer.valueOf(ConfigManager.getInstance().getConfigValue("db_port")).intValue();
    this.database = ConfigManager.getInstance().getConfigValue("db_name");
//    this.host = "192.168.184.130";
//    this.port = 27017;
//    this.database = "nutch";
    
  }

  public static MongoDbConn getInstance()
  {
    if (instance == null) {
      instance = new MongoDbConn();
    }
    return instance;
  }

  public void init(String database)
  {
    this.database = database;
  }

  public void init(String host, int port, String database) {
    this.host = host;
    this.port = port;
    this.database = database;
  }

  public void init(String host, int port, String database, String username, String password) {
    this.host = host;
    this.port = port;
    this.database = database;
    this.username = username;
    this.password = password;
  }

  public void init(List<ServerAddress> replSet, String database) {
    this.replSet = replSet;
    this.database = database;
  }

  public void init(List<ServerAddress> replSet, String database, String username, String password) {
    this.replSet = replSet;
    this.database = database;
    this.username = username;
    this.password = password;
  }

  public MongoOptions getOptions() {
    return this.options;
  }

  public void setOptions(MongoOptions options) {
    this.options = options;
  }

  private void connect()
  {
    try
    {
      if ((this.host != null) && (this.port != 0))
      {
        ServerAddress address = new ServerAddress(this.host, this.port);

        if (this.options != null)
          this.mongo = new Mongo(address, this.options);
        else {
          this.mongo = new Mongo(address);
        }
      }
      else if (this.replSet != null)
      {
        if (this.options != null)
          this.mongo = new Mongo(this.replSet, this.options);
        else {
          this.mongo = new Mongo(this.replSet);
        }

      }

      if (this.mongo != null)
        this.db = this.mongo.getDB(this.database);
      else {
    	  System.out.println("无法取得数据库实例，请确认配置信息");
//        SystemLog.getInstance().showMsg("无法取得数据库实例，请确认配置信息");
      }

      if ((this.username != null) && (this.password != null))
        if (this.db.authenticate(this.username, this.password.toCharArray())) {
        	System.out.println("登陆成功：" + this.username);
//          SystemLog.getInstance().showMsg("登陆成功：" + this.username);
        } else {
          this.db = null;
          System.out.println("登陆失败：" + this.username);
//          SystemLog.getInstance().showMsg("登陆失败：" + this.username);
        }
    }
    catch (UnknownHostException e)
    {
//      SystemLog.getInstance().showMsg("数据库地址错误");
    	System.out.println("数据库地址错误");
      e.printStackTrace();
    }
  }

  public DB getDBConnection()
  {
    if (this.db == null) {
      connect();
    }

    return this.db;
  }
}
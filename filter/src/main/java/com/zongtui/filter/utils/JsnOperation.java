package com.zongtui.filter.utils;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Jackson JSon function:
 * 		object->jackson Json
 * & 
 * 		jackson Json -> POJO class
 * 
 * Note:
 * 		Error:org.codehaus.jackson.map.JsonMappingException: No suitable constructor found for type
 * 		solution:To solve this exception, all you have to do is to create the default constructor for your 
 * 		POJO class which you are trying to marshal/unmarshal. This simple solution will fix this error. 
 * 
 * @author xiaobin
 *
 */
public class JsnOperation {
	private static Logger log = LoggerFactory.getLogger(JsnOperation.class
			.getName());
	
	public ObjectMapper mapper = null; // can reuse, share globally  
	
	
	private static volatile JsnOperation st;  
    
    private JsnOperation() {
    		if(null == mapper){
    			mapper = new ObjectMapper(); 
    		}		
    };  
      
    public static JsnOperation getInstance() throws NumberFormatException, IOException {  
        if (null == st) {  
            synchronized (JsnOperation.class) {  
                if (st == null) {  
                    st = new JsnOperation();  
                    return st;  
                }  
            }  
        }  
        return st;  
    }
		
	/*
	 * write a PROJ as JSon
	 */
    /**
     *      把JavaBean转换为json字符串
     *      (1)普通对象转换：toJson(Student)
     *      (2)List转换：toJson(List)
     *      (3)Map转换:toJson(Map)
     * 我们发现不管什么类型，都可以直接传入这个方法
     *
     * @param object JavaBean对象
     * @return json字符串
     */
    public  String toJSon(Object object) {
        if (mapper == null) {
        	mapper = new ObjectMapper();
        }
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
        	log.error(e.getClass()+","+e.getMessage());
            e.printStackTrace();
        }
 
        return null;
    }
    
    
    
    /**
     *      使用泛型方法，把json字符串转换为相应的JavaBean对象。
     *      (1)转换为普通JavaBean：readValue(json,Student.class)
     *      (2)转换为List:readValue(json,List.class).但是如果我们想把json转换为特定类型的List，比如List<Student>，就不能直接进行转换了。
     *         因为readValue(json,List.class)返回的其实是List<Map>类型，你不能指定readValue()的第二个参数是List<Student>.class，所以不能直接转换。
     *         我们可以把readValue()的第二个参数传递为Student[].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List。
     *      (3)转换为Map：readValue(json,Map.class)
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我们使用泛型，得到的也是泛型
     *
     * @param content 要转换的JavaBean类型
     * @param valueType 原始json字符串数据
     * @return JavaBean对象
     */
    public <T> T readValue(String content, Class<T> valueType) {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        try {
            return mapper.readValue(content, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return null;
    }
    
    public static void treeModel(ObjectMapper mapper, String line) throws Exception {
		// can either use mapper.readTree(JsonParser), or bind to JsonNode
		JsonNode rootNode = mapper.readTree(line);

		// ensure that "last name" isn't "Xmler"; if is, change to "Jsoner"
		JsonNode nameNode = rootNode.path("name");
		String lastName = nameNode.path("last").getTextValue();
		if ("xmler".equalsIgnoreCase(lastName)) {
			((ObjectNode) nameNode).put("last", "Jsoner");
		}
		// write it out
		mapper.writeValue(new File("user-modified.json"), rootNode);
	}
		
	
	public void release(){
			mapper = null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub


	}

}

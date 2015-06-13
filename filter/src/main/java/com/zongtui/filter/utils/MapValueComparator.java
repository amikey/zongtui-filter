package com.zongtui.filter.utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 
* @ClassName: MapValueComparator
* @Description: 在map中按照value的值大小排序
* @author yifan
* @date 2015年6月11日 上午10:13:08
*
 */
public class MapValueComparator  implements Comparator<String>{
		  
    Map<String, Integer> base;  
    public MapValueComparator(HashMap<String, Integer> map) {  
        this.base = map;  
    }  
  
    // Note: this comparator imposes orderings that are inconsistent with equals.      
    public int compare(String a, String b) {  
        if (base.get(a) >= base.get(b)) {  
            return -1;  
        } else {  
            return 1;  
        } // returning 0 would merge keys  
    }  
    
	public static void main(String[] args) {
		StringBuffer s = new StringBuffer("中国,华东地区,山东省,烟台市:10;");
		StringBuffer allValueNum = new StringBuffer();
		//System.out.println(s.subSequence(0, s.length()-1));
		HashMap<String,Integer> map = new HashMap<String,Integer>();  
		MapValueComparator vc = new MapValueComparator(map);
		TreeMap<String, Integer> sorted_map = new TreeMap<String,Integer>(vc);
		//20:0;22:0;15:943;13:2845;14:0;
		map.put("20",  0);
		map.put("22", 0);
		map.put("15", 943);
		map.put("13", 2845);
		map.put("14", 0);
		
		sorted_map.putAll(map);
		
		System.out.println(sorted_map);
		
		Set<String> keys = sorted_map.keySet();
		for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			allValueNum = allValueNum.append(key).append(":").append(map.get(key)).append(";"); 
		}
		
		System.out.println(allValueNum.substring(0, allValueNum.length()-1));
		
	}
}

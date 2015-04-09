
/**
 * Project Name:filter
 * File Name:Md5TreeFilterImpl.java
 * Package Name:com.zongtui.filter.md5tree
 * Date:2015-4-9上午10:31:10
 * Copyright (c) 2015, 众推项目组版权所有.
 *
 */
package com.zongtui.filter.md5tree;

import com.zongtui.filter.IFilter;
import com.zongtui.filter.Page;
import org.magicwerk.brownies.collections.GapList;

import java.security.MessageDigest;
import java.util.HashSet;
import java.util.List;

/**
 * ClassName: Md5TreeFilterImpl <br/>
 * Function: MD5去重树 <br/>
 * Date:2015-4-9上午10:31:10
 *
 * @author Jason
 * @since JDK 1.7
 */
public class Md5TreeFilterImpl implements IFilter {

    List<HashSet<String>> setList = new GapList<HashSet<String>>();

    private int shareCount;

    public Md5TreeFilterImpl(int shardCount, int shardNo) {
        shareCount = shardCount;
        for (int i = 0; i < shardCount; i++) {
            setList.add(new HashSet(shardNo));
        }
    }

    /**
     * Similar:判断内容的相似性，为1表示已经存在，为0表示不存在，为浮点数则判断相似度的值. <br/>
     *
     * @param page
     * @return
     * @author feng
     * @since JDK 1.7
     */
    @Override
    public float Similar(Page page) {
        float similarValue = 1;
        String url = MD5(page.getUrl());
        int index = Math.abs(url.hashCode() % shareCount);
        if (!setList.get(index).contains(url)) {
            setList.get(index).add(url);
            similarValue = 0;
        }
        return similarValue;
    }


    public final static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

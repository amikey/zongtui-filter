
/**
 * Project Name:filter
 * File Name:Md5HashFilterImpl.java
 * Package Name:com.zongtui.filter.md5hash
 * Date:2015-4-9上午10:31:10
 * Copyright (c) 2015, 众推项目组版权所有.
 *
 */
package com.zongtui.filter.md5hash;

import com.zongtui.filter.IFilter;
import com.zongtui.filter.Page;
import com.zongtui.filter.rabin.utils.MD5Utils;

import java.security.MessageDigest;
import java.util.HashSet;

/**
 * ClassName: Md5HashFilterImpl <br/>
 * Function: MD5去重树 <br/>
 * Date:2015-4-9上午10:31:10
 *
 * @author Jason
 * @since JDK 1.7
 */
public class Md5HashFilterImpl implements IFilter {

    HashSet<String> set = new HashSet<String>();



    public Md5HashFilterImpl(int shardNo) {
        set = new HashSet(shardNo);
    }

    /**
     * Similar:判断内容的相似性，为1表示已经存在，为0表示不存在，为浮点数则判断相似度的值. <br/>
     *
     * @param page
     * @return
     * @author feng
     * @since JDK 1.7
     */
    public float Similar(Page page) {
        float similarValue = 1;
        String url = MD5Utils.MD5(page.getUrl());

        if (!set.contains(url)) {
            set.add(url);
            similarValue = 0;
        }
        return similarValue;
    }


}

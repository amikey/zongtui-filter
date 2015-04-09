/**
 * Project Name:filter
 * File Name:RabinHashFilterImpl.java
 * Package Name:com.zongtui.filter.robin
 * Date:2015-4-9上午10:31:10
 * Copyright (c) 2015, 众推项目组版权所有.
 *
 */
package com.zongtui.filter.robin;

import com.zongtui.filter.IFilter;
import com.zongtui.filter.Page;
import com.zongtui.filter.robin.utils.RabinHashFunction32;

import java.util.HashSet;


/**
 * ClassName: RabinHashFilterImpl <br/>
 * Function: RabinHash算法 <br/>
 * Date:2015-4-9上午10:31:10
 *
 * @author Jason
 * @since JDK 1.7
 */
public class RabinHashFilterImpl implements IFilter {
    HashSet<Integer> set = null;

    private int rabinCount = 20;

    public RabinHashFilterImpl(int count, int rabinCount) {
        set = new HashSet<Integer>(count);
        rabinCount = rabinCount;
    }

    /**
     * Similar:判断内容的相似性，为1表示已经存在，为0表示不存在，为浮点数则判断相似度的值. <br/>
     *
     * @param page
     * @return
     * @author Jason
     * @since JDK 1.7
     */
    @Override
    public float Similar(Page page) {
        float value = 1;
        RabinHashFunction32 rabin = new RabinHashFunction32(rabinCount);
        int rabinCode = rabin.hash(page.getUrl());
        if (!set.contains(rabinCode)) {
            value = 0;
            set.add(rabinCode);
        }
        return value;
    }
}

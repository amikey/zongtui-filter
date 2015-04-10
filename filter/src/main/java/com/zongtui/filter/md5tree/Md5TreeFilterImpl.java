

/**
 * Project Name:filter
 * File Name:Md5TreeFilterImpl.java
 * Package Name:com.zongtui.filter.md5hash
 * Date:2015-4-9上午10:31:10
 * Copyright (c) 2015, 众推项目组版权所有.
 */
package com.zongtui.filter.md5tree;

import com.zongtui.filter.IFilter;
import com.zongtui.filter.Page;
import com.zongtui.filter.utils.MD5Utils;

/**
 * ClassName: Md5TreeFilterImpl <br/>
 * Function: MD5去重树 <br/>
 * Date:2015-4-9上午10:31:10
 *
 * @author Jason
 * @since JDK 1.7
 */
public class Md5TreeFilterImpl implements IFilter {

    Tree tree = new Tree();

    public Tree getTree() {
        return tree;
    }

    public float similar(Page page) {
        float similarValue = 1;
        String url = MD5Utils.MD5(page.getUrl());

        if (!tree.contain(url)) {
            tree.add(url);
            similarValue = 0;
        }
        return similarValue;
    }
}

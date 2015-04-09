package com.zongtui.filter.md5tree;

import com.zongtui.filter.IFilter;
import com.zongtui.filter.Page;
import com.zongtui.filter.rabin.utils.MD5Utils;

/**
 * Created by Jason on 2015/4/9.
 */
public class Md5TreeFilterImpl implements IFilter {

    Tree tree = new Tree();


    public float Similar(Page page) {
        float similarValue = 1;
        String url = MD5Utils.MD5(page.getUrl());

        if (!tree.contains(url)) {
            tree.add(url);
            similarValue = 0;
        }
        return similarValue;
    }
}

/**
 * Project Name:filter
 * File Name:Page.java
 * Package Name:com.zongtui.filter
 * Date:2015-4-8下午3:28:10
 * Copyright (c) 2015, 众推项目组版权所有.
 *
 */
package com.zongtui.filter;

import java.io.Serializable;

/**
 * ClassName: Page <br/>
 * Function: 过滤器传输参数 <br/>
 * date: 2015-4-8 下午3:24:10 <br/>
 *
 * @author Jason
 * @version
 * @since JDK 1.7
 */
public class Page implements Serializable {


    private static final long serialVersionUID = -1216808296255057386L;

    public Page(String url, String content) {
        this.url = url;
        this.content = content;
    }

    /**
     * url:页面的url.
     */
    private String url;

    /**
     * content:页面的内容.
     */
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

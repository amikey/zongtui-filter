
/**
 * Project Name:filter
 * File Name:BloomRemoveFilter.java
 * Package Name:com.zongtui.filter
 * Date:2015-4-8下午3:31:10
 * Copyright (c) 2015, 众推项目组版权所有.
 *
 */
package com.zongtui.filter.bloom;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.zongtui.filter.IFilter;
import com.zongtui.filter.Page;

import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName: BloomFilter <br/>
 * Function: BloomFilter实现 <br/>
 * date: 2015-4-8 下午3:31:10 <br/>
 *
 * @author Jason
 * @since JDK 1.7
 */
public class BloomFilterImpl implements IFilter {

    /**
     * 需要插入的数据量.
     */
    private int expectedInsertions;

    /**
     * 精准度.
     */
    private double fpp;

    /**
     * 当前数据量.
     */
    private AtomicInteger counter;

    public BloomFilterImpl() {
        this(1000, 0.01);
    }

    public BloomFilterImpl(int expectedInsertions) {
        this(expectedInsertions, 0.01);
    }

    public BloomFilterImpl(int expectedInsertions, double fpp) {
        this.expectedInsertions = expectedInsertions;
        this.fpp = fpp;
        this.bloomFilter = rebuildBloomFilter();
    }

    private final BloomFilter<CharSequence> bloomFilter;

    protected BloomFilter<CharSequence> rebuildBloomFilter() {
        counter = new AtomicInteger(0);
        return BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), expectedInsertions, fpp);
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
        boolean isDuplicate = bloomFilter.mightContain(page.getUrl());
        if (!isDuplicate) {
            bloomFilter.put(page.getUrl());
            counter.incrementAndGet();
            similarValue = 0;
        }
        return similarValue;
    }


}

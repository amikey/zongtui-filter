package com.zongtui.filter;

import com.zongtui.filter.md5hash.Md5HashFilterImpl;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Administrator on 2015/4/8.
 */
public class Md5HashFilterTest {

    @Test
    public void testFilter() throws Exception {
        IFilter filter = new Md5HashFilterImpl(1000000);
        Page page1 = new Page("a", null);
        Page page2 = new Page("b", null);
        float value = filter.Similar(page1);
        System.out.println(value);
        value = filter.Similar(page1);
        System.out.println(value);
        value = filter.Similar(page2);
        System.out.println(value);
        value = filter.Similar(page2);
        System.out.println(value);

    }

    @Ignore("long time")
    @Test
    public void testMemoryCost() throws Exception {
        int times = 100000;
        IFilter filter = new Md5HashFilterImpl(100000);
        long freeMemory = Runtime.getRuntime().freeMemory();
        long time = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            filter.Similar(new Page(i + "www.baidu.com/dididiidd/dadadaadadad", null));
        }
        System.out.println("耗时为：" + (System.currentTimeMillis() - time)+"毫秒");
        System.out.println("内存消耗为：:" + (Runtime.getRuntime().freeMemory()-freeMemory)/(1024*1024)+"M");
    }

    @Ignore("long time")
    @Test
    public void testHitCorrect() throws Exception {
        int times = 1000000;
        IFilter filter = new Md5HashFilterImpl(1000000);
        int right = 0;
        int wrong = 0;
        int missCheck = 0;
        for (int i = 0; i < times; i++) {
            float similar =  filter.Similar(new Page(i + "", null));
            if (similar > 0) {
                wrong++;
            } else {
                right++;
            }
            similar = filter.Similar(new Page(i + "", null));
            if (similar < 1) {
                missCheck++;
            }
        }

        System.out.println("正确数 : " + right + " 错误数: " + wrong + " miss数: " + missCheck);
    }
}

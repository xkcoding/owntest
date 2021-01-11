package com.xkcoding.test.test48;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 缓存测试
 * </p>
 *
 * @author 一珩（沈扬凯 yk.shen@tuya.com）
 * @date 2021-01-11 15:56
 */
public class CaffeineTest {
    private static final Cache<String, Object> cache =
            Caffeine.newBuilder().initialCapacity(5).maximumSize(5).expireAfterWrite(5, TimeUnit.SECONDS).executor(Runnable::run).build();

    public static void main(String[] args) throws InterruptedException {
        cache.put("a", "a");
        cache.put("b", "b");
        cache.put("c", "c");
        cache.put("d", "d");
        cache.put("e", "e");
        Object aData = cache.getIfPresent("e");
        System.out.println("e = " + aData + ",cache:" + cache.asMap().keySet());
        System.out.println("cache = " + cache.estimatedSize() + ",cache:" + cache.asMap().keySet());

        Thread.sleep(1_000);
        cache.put("f", "f");
        System.out.println("cache = " + cache.estimatedSize() + ",cache:" + cache.asMap().keySet());
        aData = cache.getIfPresent("a");
        System.out.println("a = " + aData + ",cache:" + cache.asMap().keySet());

        Thread.sleep(3_000);
        Object bData = cache.getIfPresent("b");
        System.out.println("b = " + bData + ",cache:" + cache.asMap().keySet());
        System.out.println("cache = " + cache.estimatedSize() + ",cache:" + cache.asMap().keySet());

        Thread.sleep(2_000);
        bData = cache.getIfPresent("b");
        System.out.println("b = " + bData + ",cache:" + cache.asMap().keySet());
        System.out.println("cache = " + cache.estimatedSize() + ",cache:" + cache.asMap().keySet());

    }
}

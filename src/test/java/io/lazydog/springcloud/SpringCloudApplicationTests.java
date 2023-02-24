package io.lazydog.springcloud;

import cn.hutool.core.thread.ConcurrencyTester;
import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.fastjson2.JSONObject;
import io.lazydog.springcloud.feign.AcfunFeign;
import io.lazydog.springcloud.feign.BiliFegin;
import io.lazydog.springcloud.feign.ZhihuFeign;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@SpringBootTest
class SpringCloudApplicationTests {
    @Autowired
    ZhihuFeign zhihuFeign;
    @Autowired
    AcfunFeign acfunFeign;

    @Test
    void zhihu401() {
        final JSONObject userById = zhihuFeign.getBut401();
        System.out.println(userById);
    }

    @Test
    void config(){
        final JSONObject config = zhihuFeign.getConfig();
        System.out.println(config);
    }

    @Test
    void acfun1(){
        final JSONObject bili = acfunFeign.searchDefault();
        System.out.println(bili);
    }

    @Autowired
    BiliFegin biliFegin;

    @Test
    void bili(){
        final JSONObject getlist = biliFegin.getlist();
        System.out.println(getlist);
    }

    @Test
    void useTime(){
//        final long start = System.currentTimeMillis();
//        for (int i = 0; i < 10; i++) {
//            final JSONObject config = zhihuFeign.getConfig();
//            final JSONObject getlist = biliFegin.getlist();
//            final JSONObject bili = acfunFeign.searchDefault();
//        }
        List<Long> sum=new Vector<>(10000);
        final ConcurrencyTester concurrencyTester = ThreadUtil.concurrencyTest(40, () -> {
            for (int i = 0; i < 100; i++) {
                final long start = System.currentTimeMillis();
                final JSONObject config = zhihuFeign.getConfig();
                final JSONObject getlist = biliFegin.getlist();
                final JSONObject bili = acfunFeign.searchDefault();
                log.info("一共用了{}",System.currentTimeMillis()-start);
                sum.add(System.currentTimeMillis()-start);
            }
        });
        log.info("一共运行了{}次",sum.size());
        final List<Long> longs = sum.subList(sum.size() - 10, sum.size());
        log.info("一共用了{}",concurrencyTester.getInterval());
        log.info("平均用了{}",longs.stream().mapToLong(Long::longValue).average().getAsDouble());
    }

    @Test
    void asyncTest(){
        final ConcurrencyTester concurrencyTester = ThreadUtil.concurrencyTest(20, this::getThread);

    }

    private void getThread(){
        log.info("当前线程是{}",Thread.currentThread().getName());
    }
}

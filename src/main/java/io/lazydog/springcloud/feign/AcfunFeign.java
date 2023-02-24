package io.lazydog.springcloud.feign;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

// https://www.acfun.cn/rest/pc-direct/homePage/searchDefault
@FeignClient(name="acfun",url = "https://www.acfun.cn/rest")

public interface AcfunFeign {

    @GetMapping("/pc-direct/homePage/searchDefault")
    JSONObject searchDefault();



}

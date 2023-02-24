package io.lazydog.springcloud.feign;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

// https://api.live.bilibili.com/xlive/web-interface/v1/webMain/getMoreRecList?platform=web
@FeignClient(name = "bili",url = "https://api.live.bilibili.com/xlive")
public interface BiliFegin {
    @GetMapping("/web-interface/v1/webMain/getMoreRecList?platform=web")
    JSONObject getlist();
}

package io.lazydog.springcloud.feign;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

// https://zhuanlan.zhihu.com/api/articles/133378040/concerned_upvoters
//https://www.zhihu.com/api/v4/comment_v5/articles/133378040/config
@FeignClient(name="third-party-api",url = "https://www.zhihu.com/api")

public interface ZhihuFeign {
    @GetMapping("/articles/133378040/concerned_upvoters")
    JSONObject getBut401();

    @GetMapping("/v4/comment_v5/articles/133378040/config")
    JSONObject getConfig();
}

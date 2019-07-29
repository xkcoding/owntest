package com.xkcoding.test.test35;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * AuthState 测试
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019-07-29 17:50
 */
@Slf4j
public class Test35 {
    private static final Cache<String, String> CACHE_STATE = CacheBuilder.newBuilder().maximumSize(200).expireAfterAccess(5, TimeUnit.SECONDS).build();

    public static void main(String[] args) throws InterruptedException {
        AuthConfig config = new AuthConfig("qq-cxxxx").state(IdUtil.fastSimpleUUID());
        log.info("【config】= {}", config);

        String state = CACHE_STATE.getIfPresent(config.getState());
        log.info("【state】= {}", state);

        AuthCallback callback = new AuthCallback(state);
        boolean check = callback.check();
        log.info("【check】= {}", check);

        state = CACHE_STATE.getIfPresent(config.getState());
        log.info("【state】= {}", state);
    }

    @Data
    @RequiredArgsConstructor
    static class AuthConfig {
        private final String clientId;
        private String state;

        AuthConfig state(String state) {
            this.state = state;
            CACHE_STATE.put(state, state);
            return this;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class AuthCallback {
        private String state;

        boolean check() {
            String state = CACHE_STATE.getIfPresent(this.state);
            if (StrUtil.isNotBlank(state)) {
                CACHE_STATE.invalidate(state);
                return true;
            } else {
                return false;
            }
        }
    }
}

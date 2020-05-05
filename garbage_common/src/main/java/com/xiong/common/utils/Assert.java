package com.xiong.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author <a herf="mailto:wuqi@terminus.io">xunyard</a>
 * @date 2019-02-15
 */
@Slf4j
public class Assert {

    private static Function<String, ? extends RuntimeException> ERROR_BUILD_CONSUMER;

    static {
        setErrorCreate(ServiceException::new);
    }

    public synchronized static void setErrorCreate(Function<String, ? extends RuntimeException> function) {
        ERROR_BUILD_CONSUMER = function;
    }

    private static void throwException(String message) {
        throw ERROR_BUILD_CONSUMER.apply(message);
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throwException(message);
        }
    }

    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throwException(message);
        }
    }

    public static void equals(Object source, Object target) {
        if (!Objects.equals(source, target)) {
            throwException("mismatch.source.and.target");
        }
    }

    public static void equals(Object source, Object target, String message) {
        if (!Objects.equals(source, target)) {
            throwException(message);
        }
    }

    public static void isNull(Object obj, String message) {
        if (obj != null) {
            throwException(message);
        }
    }

    public static void nonNull(Object obj) {
        if (obj == null) {
            throwException("object.is.null");
        }
    }

    public static void nonNull(Object... objects) {
        if (objects == null) {
            return;
        }

        for (Object object : objects) {
            Assert.nonNull(object);
        }
    }

    public static void nonNull(Object obj, String message) {
        if (obj == null) {
            throwException(message);
        }
    }

    public static void notEmpty(Collection<?> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            throwException("collection.is.empty");
        }
    }

    public static void notEmpty(Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throwException(message);
        }
    }

    public static void isEmpty(Collection<?> collection, String message) {
        if (!CollectionUtils.isEmpty(collection)) {
            throwException(message);
        }
    }

    public static void notEmpty(Map<?, ?> map) {
        if (CollectionUtils.isEmpty(map)) {
            throwException("map.is.empty");
        }
    }

    public static void notEmpty(Map<?, ?> map, String message) {
        if (CollectionUtils.isEmpty(map)) {
            throwException(message);
        }
    }

    public static void notEmpty(String str) {
        if (StringUtils.isEmpty(str)) {
            throwException("string.is.empty");
        }
    }

    public static void notEmpty(String str, String message) {
        if (StringUtils.isEmpty(str)) {
            throwException(message);
        }
    }

    /**
     * 从RPC中获取结果
     *
     * @param rpcResult rpc结果
     * @param <T>       结果类型
     * @return 成功返回结果，否则抛出{@link ServiceException}错误
     */
    public static <T> T take(Response<T> rpcResult) {
        Assert.nonNull(rpcResult, "result.cannot.be.null");

        if (!rpcResult.isSuccess()) {
            throwException(rpcResult.getError());
        }

        return rpcResult.getResult();
    }

    /**
     * 从RPC中获取结果（安静模式）。此模式下认为调用方在发生错误时也不希望以抛错的形式处理，当RPC调用
     * 失败时，直接返回null结果
     *
     * @param rpcResult rpc结果
     * @param <T>       结果类型
     * @return 成功返回结果，否则返回null
     */
    public static <T> T takeQuietly(Response<T> rpcResult) {
        Assert.nonNull(rpcResult, "rpc.result.cannot.be.null");

        if (!rpcResult.isSuccess()) {
            log.error("fail to invoke rpc, cause: {}", rpcResult.getError());
        }

        return rpcResult.isSuccess()
                ? rpcResult.getResult()
                : null;
    }
}


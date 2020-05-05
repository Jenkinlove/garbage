package com.xiong.common.utils;

import com.google.common.collect.Maps;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AssembleDataUtils {

    /**
     * List转Map对象
     *
     * @param objList     对象集合
     * @param keyFunction 键生成方法
     * @param valFunction 值生成方法
     * @param <TKey>      键类型
     * @param <TVal>      值类型
     * @param <TObj>      源对象类型
     * @return Map
     */
    public static <TKey, TVal, TObj> Map<TKey, TVal> list2map(List<TObj> objList,
                                                              Function<TObj, TKey> keyFunction,
                                                              Function<TObj, TVal> valFunction) {
        if (CollectionUtils.isEmpty(objList)) {
            return Collections.emptyMap();
        }

        return objList.stream().collect(Collectors.toMap(keyFunction, valFunction));
    }

    /**
     * List转map对象，使用对象作value
     *
     * @param list        对象集合
     * @param keyFunction 键生成方法
     * @param <TKey>      键类型
     * @param <TValue>    对象类型
     * @return Map
     */
    public static <TKey, TValue> Map<TKey, TValue> list2map(List<TValue> list,
                                                            Function<TValue, TKey> keyFunction) {
        return list2map(list, keyFunction, Function.identity());
    }

    /**
     * List转List
     *
     * @param objList     源对象集合
     * @param mapFunction 转换方法
     * @param <TR>        转换后类型
     * @param <TI>        转换前类型
     * @return List集合
     */
    public static <TR, TI> List<TR> list2list(List<TI> objList, Function<TI, TR> mapFunction) {
        if (CollectionUtils.isEmpty(objList)) {
            return Collections.emptyList();
        }

        return objList.stream().map(mapFunction).collect(Collectors.toList());
    }

    /**
     * List转Set
     *
     * @param objList     源集合
     * @param mapFunction 转换方法
     * @param <TR>        转换后类型
     * @param <TI>        转换前类型
     * @return Set集合
     */
    public static <TR, TI> Set<TR> list2set(List<TI> objList, Function<TI, TR> mapFunction) {
        if (CollectionUtils.isEmpty(objList)) {
            return Collections.emptySet();
        }

        return objList.stream().map(mapFunction).collect(Collectors.toSet());
    }

    /**
     * Set转List
     *
     * @param objSet      源对象集合
     * @param mapFunction 转换方法
     * @param <TR>        转换后类型
     * @param <TI>        转换前类型
     * @return List集合
     */
    public static <TR, TI> List<TR> set2list(Set<TI> objSet, Function<TI, TR> mapFunction) {
        if (CollectionUtils.isEmpty(objSet)) {
            return Collections.emptyList();
        }

        return objSet.stream().map(mapFunction).collect(Collectors.toList());
    }

    /**
     * set集合转map对象
     *
     * @param objSet      set对象集合
     * @param keyFunction key值转换方法
     * @param <TKey>      Key类型
     * @param <TObj>      对象类型
     * @return map对象
     */
    public static <TKey, TObj> Map<TKey, TObj> set2map(Set<TObj> objSet, Function<TObj, TKey> keyFunction) {
        if (CollectionUtils.isEmpty(objSet)) {
            return Collections.emptyMap();
        }

        return objSet.stream().collect(Collectors.toMap(keyFunction, Function.identity()));
    }

    /**
     * set集合转map对象
     *
     * @param objSet      对象集合
     * @param keyFunction key值转换方法
     * @param valFunction val值转换方法
     * @param <TKey>      key值类型
     * @param <TVal>      val值类型
     * @param <TObj>      对象类型
     * @return map对象
     */
    public static <TKey, TVal, TObj> Map<TKey, TVal> set2map(Set<TObj> objSet,
                                                             Function<TObj, TKey> keyFunction,
                                                             Function<TObj, TVal> valFunction) {
        if (CollectionUtils.isEmpty(objSet)) {
            return Collections.emptyMap();
        }

        Map<TKey, TVal> resultMap = Maps.newHashMapWithExpectedSize(objSet.size());

        for (TObj obj : objSet) {
            TKey key = keyFunction.apply(obj);
            TVal val = valFunction.apply(obj);

            resultMap.put(key, val);
        }

        return resultMap;
    }

    /**
     * List转Set
     *
     * @param list 集合
     * @param <T>  源类型
     * @return Set集合
     */
    public static <T> Set<T> list2set(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptySet();
        }

        return new HashSet<>(list);
    }

    /**
     * Set转List
     *
     * @param set Set集合
     * @param <T> 源类型
     * @return List集合
     */
    public static <T> List<T> set2list(Set<T> set) {
        if (CollectionUtils.isEmpty(set)) {
            return Collections.emptyList();
        }

        return new ArrayList<>(set);
    }

    /**
     * set集合转换<br>
     * 注意：转换后可能出现值冲突，谨慎使用!
     *
     * @param set      源set集合
     * @param transfer 转换方法
     * @param <TI>     输入类型
     * @param <TR>     输入类型
     * @return 转换后Set集合
     */
    public static <TI, TR> Set<TR> set2set(Set<TI> set, Function<TI, TR> transfer) {
        if (CollectionUtils.isEmpty(set)) {
            return Collections.emptySet();
        }

        return set.stream().map(transfer).collect(Collectors.toSet());
    }

    /**
     * 集合过滤
     *
     * @param <T>        源数据类型
     * @param collection 集合
     * @param predicate  判断方法
     * @return List集合
     */
    public static <T> List<T> predicate2list(Collection<T> collection, Predicate<? super T> predicate) {
        if (CollectionUtils.isEmpty(collection)) {
            return Collections.emptyList();
        }

        return collection.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * 集合过滤
     *
     * @param <T>        源数据类型
     * @param collection 集合
     * @param predicate  判断方法
     * @return Set集合
     */
    public static <T> Set<T> predicate2set(Collection<T> collection, Predicate<? super T> predicate) {
        if (CollectionUtils.isEmpty(collection)) {
            return Collections.emptySet();
        }

        return collection.stream().filter(predicate).collect(Collectors.toSet());
    }

    /**
     * 求取sourceSet集合相比于toCompareSet集合缺少的元素
     *
     * @param sourceSet    源数据集合
     * @param toCompareSet 待对比数据集合
     * @param <T>          数据类型
     * @return 不在toCompareSet中的数据集合
     */
    public static <T> Set<T> notInSet(Set<T> sourceSet, Set<T> toCompareSet) {
        if (CollectionUtils.isEmpty(sourceSet)) {
            return toCompareSet;
        }

        if (CollectionUtils.isEmpty(toCompareSet)) {
            return Collections.emptySet();
        }

        Set<T> missElementSet = new HashSet<>();
        for (T toCompareElement : toCompareSet) {
            if (!sourceSet.contains(toCompareElement)) {
                missElementSet.add(toCompareElement);
            }
        }

        return missElementSet;
    }

    /**
     * 将集合转换后放置为map形式 - 值索引
     *
     * @param collection 源集合
     * @param function   转换方法
     * @param <TI>       输入类型
     * @param <TR>       转换后类型
     * @return map对象
     */
    public static <TI, TR> Map<TR, TI> collection2mapR(Collection<TI> collection, Function<TI, TR> function) {
        if (CollectionUtils.isEmpty(collection)) {
            return Collections.emptyMap();
        }

        if (function == null) {
            throw new IllegalArgumentException("转换方法不可为空");
        }

        return collection.stream().collect(Collectors.toMap(function, Function.identity()));
    }

    /**
     * map对象的key转换
     *
     * @param map         map对象
     * @param keyTransfer 转换方法
     * @param <TIO>       旧的key类型
     * @param <TIN>       新的key类型
     * @param <TR>        value类型
     * @return 转换后的map对象
     */
    public static <TIO, TIN, TR> Map<TIN, TR> mapKeyTransfer(Map<TIO, TR> map, Function<TIO, TIN> keyTransfer) {
        if (CollectionUtils.isEmpty(map)) {
            return Collections.emptyMap();
        }

        if (keyTransfer == null) {
            throw new IllegalArgumentException("Key转换方法不可为空");
        }

        Map<TIN, TR> transferredMap = new HashMap<>();
        for (Map.Entry<TIO, TR> entry : map.entrySet()) {
            TIN newKey = keyTransfer.apply(entry.getKey());
            transferredMap.put(newKey, entry.getValue());
        }

        return transferredMap;
    }

    /**
     * map对象的value转换
     *
     * @param map           map对象
     * @param valueTransfer 转换方法
     * @param <TIO>         旧的value类型
     * @param <TIN>         新的value类型
     * @param <TR>          key类型
     * @return 转换后的map对象
     */
    public static <TIO, TIN, TR> Map<TR, TIN> mapValueTransfer(Map<TR, TIO> map, Function<TIO, TIN> valueTransfer) {
        if (CollectionUtils.isEmpty(map)) {
            return Collections.emptyMap();
        }

        Assert.nonNull(valueTransfer, "value转换方法不可为空");
        Map<TR, TIN> transferredMap = new HashMap<>();
        for (Map.Entry<TR, TIO> entry : map.entrySet()) {
            TIN newValue = valueTransfer.apply(entry.getValue());
            transferredMap.put(entry.getKey(), newValue);
        }

        return transferredMap;
    }

    /**
     * list集合过滤
     *
     * @param source    源
     * @param predicate 过滤
     * @param <T>       类型
     * @return 过滤后结果
     */
    public static <T> List<T> listFilter(List<T> source, Predicate<T> predicate) {
        if (CollectionUtils.isEmpty(source)) {
            return Collections.emptyList();
        }

        Assert.nonNull(predicate, "过滤方法不可为空");
        return source.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * 从集合中获取第一个满足过滤要求的对象
     *
     * @param collection 集合
     * @param predicate  过滤方法
     * @param <T>        对象类型
     * @return 存在时返回第一个对象，否则null
     */
    public static <T> T firstMatch(Collection<T> collection, Predicate<T> predicate) {
        if (CollectionUtils.isEmpty(collection)) {
            return null;
        }

        Assert.nonNull(predicate, "过滤方法不可为空");
        for (T element : collection) {
            if (predicate.test(element)) {
                return element;
            }
        }

        return null;
    }

    /**
     * 数组转字符串(join)版本
     *
     * @param <T>      类型
     * @param array    源数组
     * @param joiner   join元素
     * @param transfer 兑现转string方法，为空时，调用toString方法
     * @return 字符串
     */
    public static <T> String join(T[] array, String joiner, Function<T, String> transfer) {
        if (array == null || array.length == 0) {
            return "";
        }

        return join(new StringBuilder(), array, joiner, transfer).toString();
    }

    /**
     * 将list集合按照key执行group操作
     *
     * @param source   源集合
     * @param groupBy  归组依据
     * @param <TKey>   键类型
     * @param <TValue> 值类型
     * @return map对象
     */
    public static <TKey, TValue> Map<TKey, List<TValue>> list2group(List<TValue> source, Function<TValue, TKey> groupBy) {
        if (CollectionUtils.isEmpty(source)) {
            return Collections.emptyMap();
        }

        Assert.nonNull(groupBy, "归组方法不可为空");
        return source.stream().collect(Collectors.groupingBy(groupBy));
    }


    public static <T> StringBuilder join(StringBuilder sb, T[] array, String joiner, Function<T, String> transfer) {
        if (array == null || array.length == 0) {
            return sb;
        }

        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(joiner);
            }

            sb.append(transfer == null
                    ? array[i]
                    : transfer.apply(array[i])
            );
        }

        return sb;
    }

    /**
     * 从collection集合中获取第一个元素<br>
     * <p>
     * 适用于只是需要从collection集合中获取一个元素，或者指定第一个元素
     * </p>
     *
     * @param collection collection集合
     * @param <T>        Collection泛型
     * @param <E>        元素泛型
     * @return 元素
     */
    @SuppressWarnings("unchecked")
    public static <T extends Collection<E>, E> E takeFirst(T collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return null;
        }

        if (collection instanceof LinkedList) {
            return (E) ((LinkedList) collection).getFirst();
        } else if (collection instanceof ArrayList) {
            return (E) ((ArrayList) collection).get(0);
        } else {
            return collection.iterator().next();
        }
    }

    /**
     * 将嵌套集合铺开为list集合
     *
     * @param source 数据源
     * @param <T>    元素类型
     * @return list集合
     */
    public static <T> List<T> expandToList(Collection<? extends Collection<T>> source) {
        if (CollectionUtils.isEmpty(source)) {
            return Collections.emptyList();
        }

        LinkedList<T> result = new LinkedList<>();
        source.forEach(result::addAll);
        return result;
    }

    /**
     * 将嵌套集合铺开为set集合
     *
     * @param source 数据源
     * @param <T>    元素类型
     * @return set集合
     */
    public static <T> Set<T> expandToSet(Collection<? extends Collection<T>> source) {
        if (CollectionUtils.isEmpty(source)) {
            return Collections.emptySet();
        }

        HashSet<T> result = new HashSet<>();
        source.forEach(result::addAll);
        return result;
    }

    /**
     * map转list
     *
     * @param map      map对象
     * @param transfer 转换方法
     * @param <TKey>   key的类型
     * @param <TValue> value的类型
     * @param <T>      输出集合元素类型
     * @return list集合元素
     */
    public static <TKey, TValue, T> List<T> map2list(Map<TKey, TValue> map, BiFunction<TKey, TValue, T> transfer) {
        if (CollectionUtils.isEmpty(map)) {
            return Collections.emptyList();
        }

        ArrayList<T> result = new ArrayList<>(map.size());
        for (Map.Entry<TKey, TValue> entry : map.entrySet()) {
            result.add(transfer.apply(entry.getKey(), entry.getValue()));
        }

        return result;
    }
}


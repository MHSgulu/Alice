package com.uw.alice.common;


import java.util.Arrays;
import java.util.List;

/**
 * 基础操作类
 */
public class Basic {


    /**
     * 围绕给定正则表达式的匹配项拆分此字符串。
     *
     * 此方法的工作方式与使用给定表达式和限制参数为零的双参数方法{@link
     * * #split(String, int) split} 类似。因此，结果数组中不包括尾随的空字符串。
     *
     * The string {@code "boo:and:foo"}, for example,
     *      <tr>
     *        <th>Regex</th>
     *        <th>Result</th>
     *      </tr>
     *   <tr><td align=center>:</td>
     *     <td>{@code { "boo", "and", "foo" }}</td></tr>
     *   <tr><td align=center>o</td>
     *     <td>{@code { "b", "", ":and:f" }}</td></tr>
     *
     *
     * @param data  调用者传递的 String 数据
     * @param regex 分隔正则表达式
     * @return 通过在给定正则表达式的匹配项周围拆分此字符串而计算出的字符串数组
     *
     * @throws PatternSyntaxException 如果正则表达式的语法无效
     * @see java.util.regex.Pattern
     * @since 1.4
     * @spec JSR-51
     */
    public static String[] split(String data,String regex) {
        return data.split(regex);
    }


    /**
     * 数组转List
     *
     * 返回由指定数组支持的固定大小的列表。 （更改为返回列表，将其“写入”到数组。）
     * 与{@link Collection ＃toArray}结合使用，此方法充当基于数组的API和基于集合的API之间的桥梁。
     * 返回的列表是可序列化的，并且实现了{@link RandomAccess}。
     * 此方法还提供了一种方便的方法来创建固定大小的列表，该列表初始化为包含几个元素：
     * <pre>
     *  List<String> stooges = Arrays.asList("Larry", "Moe", "Curly");
     * </pre>
     *
     * @param a  将支持列表的数组
     * @param <T> 数组中对象的类
     * @return 返回指定数组的列表视图
     */
    @SafeVarargs
    public static <T> List<T> ArraysToList(T... a) {
        return Arrays.asList(a);
    }





}

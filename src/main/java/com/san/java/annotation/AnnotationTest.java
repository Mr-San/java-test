package com.san.java.annotation;

import com.san.java.exception.ExceptionTest;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Java注释测试类
 * <p>
 * 更长的注释<br>
 * 更长的注释
 * 测试符合JavaDoc的Java注释<br>
 * 更长的注释
 * 更长的注释
 * 更长的注释
 * </p>
 * Created by San on 2019/5/20.
 *
 * @author z.x.shan@qq.com （address@example.com）
 * @version 0.2.0   （程序的当前版本号）
 * @see ExceptionTest
 * @since 0.1.0   （加入该类时程序的版本号）
 */
public class AnnotationTest {
    /**
     * CONSTANT_A常量的描述
     */
    public static final String CONSTANT_A = "1";

    /**
     * a变量的描述
     */
    private int a;

    /**
     * b变量的描述
     */
    private int b;

    public AnnotationTest() {
        this(0);
    }

    /**
     * 指定入参构造一个AnnotationTest
     *
     * @param a 入参a
     */
    public AnnotationTest(int a) {
        super();

        this.a = a;
    }

    /**
     * 私有方法
     *
     * <p>
     *     为了完成XXX，需要做如下步骤：
     *     <ul>
     *         <li>1、步骤一</li>
     *         <li>2、步骤二</li>
     *     </ul>
     *
     *     <i>注：为XZXX，需要XXXX</i>
     * </p>
     * @param param1 入参1
     * @param param2 入参2
     * @throws SQLException 数据库异常
     */
    private void privateMethod(int param1, String param2) throws SQLException {
        /*
         * 1、步骤一
         */

        // 1.1、XXX
        // 1.2、XX

        /*
         * 2、步骤二
         */
    }

    /**
     * 保护方法
     *
     * @param param1 入参1
     * @param param2 入参2
     * @return 当{@code param1}、{@code param2}都不为null时返回{@linkplain #privateMethod privateMethod}的结果值，否则返回null
     * @see #privateMethod
     */
    protected int protectedMethod(int param1, String param2) {
        return 0;
    }

    /**
     * 公开方法
     * <p>
     *     XXXXXX
     * </p>
     *
     * @param key Map.key入参
     * @param value Map.value入参
     * @return XXX结果返回
     * @throws NumberFormatException 输入{@code key}和{@code value}非数值
     */
    public Map<String, Integer> publicMethod(String key, String value) throws NumberFormatException {
        return new HashMap<>();
    }

    /**
     * Main方法
     *
     * <pre>
     *     class NetworkClassLoader extends ClassLoader {
     *         String host;
     *         int port;
     *
     *         public Class findClass(String name) {
     *             byte[] b = loadClassData(name);
     *             return defineClass(name, b, 0, b.length);
     *         }
     *
     *         private byte[] loadClassData(String name) {
     *             // load the class data from the connection
     *             &nbsp;.&nbsp;.&nbsp;.
     *         }
     *     }
     * </pre>
     *
     * {@link java.awt.Graphics}
     * {@link java.awt.Graphics Graphics}
     * {@linkplain java.awt.Graphics Graphics}
     * {@link ExceptionTest}
     * {@link ExceptionTest#testReturn}
     * {@linkplain ExceptionTest#testReturn}
     *
     * @param args 主程序入参
     */
    public static void main(String[] args) {
        // sdfsd
        System.out.println("Hello World");
    }
}

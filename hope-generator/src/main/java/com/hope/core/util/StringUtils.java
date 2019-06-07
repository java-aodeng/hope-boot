package com.hope.core.util;

/**
 * @program:hope-boot
 * @ClassName:tetst
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-04-17 09:44
 * @Description: TODO
 * @Version 1.0
 **/
public class StringUtils {

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String upperCaseFirst(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param str
     * @return
     */
    public static String lowerCaseFirst(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * 下划线，转换为驼峰式
     *
     * @param underscoreName
     * @return
     */
    public static String underlineToCamelCase(String underscoreName) {
        StringBuilder result = new StringBuilder();
        if (underscoreName != null && underscoreName.trim().length() > 0) {
            boolean flag = false;
            for (int i = 0; i < underscoreName.length(); i++) {
                char ch = underscoreName.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }

}

package com.hope.core.util;


import com.hope.core.exception.CodeGenerateException;
import com.hope.core.model.ClassInfo;
import com.hope.core.model.FieldInfo;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program:hope-plus
 * @ClassName:tetst
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-04-17 09:44
 * @Description: TODO
 * @Version 1.0
 **/
public class TableParseUtil {

    /**
     * 解析建表SQL生成代码（model-dao-xml）
     *
     * @param tableSql
     * @return
     */
    public static ClassInfo processTableIntoClassInfo(String tableSql) throws IOException {
        if (tableSql==null || tableSql.trim().length()==0) {
            throw new CodeGenerateException("Table structure can not be empty.");
        }
        tableSql = tableSql.trim();

        // table Name
        String tableName = null;
        if (tableSql.contains("TABLE") && tableSql.contains("(")) {
            tableName = tableSql.substring(tableSql.indexOf("TABLE")+5, tableSql.indexOf("("));
        } else if (tableSql.contains("table") && tableSql.contains("(")) {
            tableName = tableSql.substring(tableSql.indexOf("table")+5, tableSql.indexOf("("));
        } else {
            throw new CodeGenerateException("Table structure anomaly.");
        }

        if (tableName.contains("`")) {
            tableName = tableName.substring(tableName.indexOf("`")+1, tableName.lastIndexOf("`"));
        }

        // class Name
        String className = StringUtils.upperCaseFirst(StringUtils.underlineToCamelCase(tableName));
        if (className.contains("_")) {
            className = className.replaceAll("_", "");
        }

        // class Comment
        String classComment = "";
        if (tableSql.contains("COMMENT=")) {
            String classCommentTmp = tableSql.substring(tableSql.lastIndexOf("COMMENT=")+8).trim();
            if (classCommentTmp.contains("'") || classCommentTmp.indexOf("'")!=classCommentTmp.lastIndexOf("'")) {
                classCommentTmp = classCommentTmp.substring(classCommentTmp.indexOf("'")+1, classCommentTmp.lastIndexOf("'"));
            }
            if (classCommentTmp!=null && classCommentTmp.trim().length()>0) {
                classComment = classCommentTmp;
            }
        }

        // field List
        List<FieldInfo> fieldList = new ArrayList<FieldInfo>();

        String fieldListTmp = tableSql.substring(tableSql.indexOf("(")+1, tableSql.lastIndexOf(")"));

        // replave "," by "，" in comment
        Matcher matcher = Pattern.compile("\\ COMMENT '(.*?)\\'").matcher(fieldListTmp);     // "\\{(.*?)\\}"
        while(matcher.find()){

            String commentTmp = matcher.group();
            commentTmp = commentTmp.replaceAll("\\ COMMENT '|\\'", "");      // "\\{|\\}"

            if (commentTmp.contains(",")) {
                String commentTmpFinal = commentTmp.replaceAll(",", "，");
                fieldListTmp = fieldListTmp.replace(commentTmp, commentTmpFinal);
            }
        }

        // remove invalid data
        for (Pattern pattern: Arrays.asList(
                Pattern.compile("[\\s]*PRIMARY KEY .*(\\),|\\))"),      // remove PRIMARY KEY
                Pattern.compile("[\\s]*UNIQUE KEY .*(\\),|\\))"),       // remove UNIQUE KEY
                Pattern.compile("[\\s]*KEY .*(\\),|\\))")               // remove KEY
        )) {
            Matcher patternMatcher = pattern.matcher(fieldListTmp);
            while(patternMatcher.find()){
                fieldListTmp = fieldListTmp.replace(patternMatcher.group(),"");
            }
        }

        String[] fieldLineList = fieldListTmp.split(",");
        if (fieldLineList.length > 0) {
            for (String columnLine :fieldLineList) {
                columnLine = columnLine.trim();		                                        // `userid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                if (columnLine.startsWith("`")){

                    // column Name
                    columnLine = columnLine.substring(1);			                        // userid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                    String columnName = columnLine.substring(0, columnLine.indexOf("`"));	// userid

                    // field Name
                    String fieldName = StringUtils.lowerCaseFirst(StringUtils.underlineToCamelCase(columnName));
                    if (fieldName.contains("_")) {
                        fieldName = fieldName.replaceAll("_", "");
                    }

                    // field class
                    columnLine = columnLine.substring(columnLine.indexOf("`")+1).trim();	// int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                    String fieldClass = Object.class.getSimpleName();
                    if (columnLine.startsWith("int") || columnLine.startsWith("tinyint") || columnLine.startsWith("smallint")) {
                        fieldClass = Integer.TYPE.getSimpleName();
                    } else if (columnLine.startsWith("bigint")) {
                        fieldClass = Long.TYPE.getSimpleName();
                    } else if (columnLine.startsWith("float")) {
                        fieldClass = Float.TYPE.getSimpleName();
                    } else if (columnLine.startsWith("double")) {
                        fieldClass = Double.TYPE.getSimpleName();
                    } else if (columnLine.startsWith("datetime") || columnLine.startsWith("timestamp")) {
                        fieldClass = Date.class.getSimpleName();
                    } else if (columnLine.startsWith("varchar") || columnLine.startsWith("text") || columnLine.startsWith("char")) {
                        fieldClass = String.class.getSimpleName();
                    } else if (columnLine.startsWith("decimal")) {
                        fieldClass = BigDecimal.class.getSimpleName();
                    }

                    // field comment
                    String fieldComment = "";
                    if (columnLine.contains("COMMENT")) {
                        String commentTmp = fieldComment = columnLine.substring(columnLine.indexOf("COMMENT")+7).trim();	// '用户ID',
                        if (commentTmp.contains("'") || commentTmp.indexOf("'")!=commentTmp.lastIndexOf("'")) {
                            commentTmp = commentTmp.substring(commentTmp.indexOf("'")+1, commentTmp.lastIndexOf("'"));
                        }
                        fieldComment = commentTmp;
                    }

                    FieldInfo fieldInfo = new FieldInfo();
                    fieldInfo.setColumnName(columnName);
                    fieldInfo.setFieldName(fieldName);
                    fieldInfo.setFieldClass(fieldClass);
                    fieldInfo.setFieldComment(fieldComment);

                    fieldList.add(fieldInfo);
                }
            }
        }

        if (fieldList.size() < 1) {
            throw new CodeGenerateException("Table structure anomaly.");
        }

        ClassInfo codeJavaInfo = new ClassInfo();
        codeJavaInfo.setTableName(tableName);
        codeJavaInfo.setClassName(className);
        codeJavaInfo.setClassComment(classComment);
        codeJavaInfo.setFieldList(fieldList);

        return codeJavaInfo;
    }

}

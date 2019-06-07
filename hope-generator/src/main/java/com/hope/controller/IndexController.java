package com.hope.controller;

import com.hope.core.CodeGeneratorTool;
import com.hope.core.model.ClassInfo;
import com.hope.model.ReturnT;
import com.hope.util.FreemarkerTool;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program:hope-boot
 * @ClassName:tetst
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-04-17 09:44
 * @Description: TODO
 * @Version 1.0
 **/
@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Resource
    private FreemarkerTool freemarkerTool;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/codeGenerate")
    @ResponseBody
    public ReturnT<Map<String, String>> codeGenerate(String tableSql) {

        try {

            if (StringUtils.isBlank(tableSql)) {
                return new ReturnT<Map<String, String>>(ReturnT.FAIL_CODE, "表结构信息不可为空");
            }

            // parse table
            ClassInfo classInfo = CodeGeneratorTool.processTableIntoClassInfo(tableSql);

            // code genarete
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("classInfo", classInfo);

            // result
            Map<String, String> result = new HashMap<String, String>();

            result.put("controller_code", freemarkerTool.processString("xxl-code-generator/controller.ftl", params));
            result.put("service_code", freemarkerTool.processString("xxl-code-generator/service.ftl", params));
            result.put("service_impl_code", freemarkerTool.processString("xxl-code-generator/service_impl.ftl", params));

            result.put("dao_code", freemarkerTool.processString("xxl-code-generator/dao.ftl", params));
            result.put("mybatis_code", freemarkerTool.processString("xxl-code-generator/mybatis.ftl", params));
            result.put("model_code", freemarkerTool.processString("xxl-code-generator/model.ftl", params));

            // 计算,生成代码行数
            int lineNum = 0;
            for (Map.Entry<String, String> item: result.entrySet()) {
                if (item.getValue() != null) {
                    lineNum += StringUtils.countMatches(item.getValue(), "\n");
                }
            }
            logger.info("生成代码行数：{}", lineNum);

            return new ReturnT<Map<String, String>>(result);
        } catch (IOException | TemplateException e) {
            logger.error(e.getMessage(), e);
            return new ReturnT<Map<String, String>>(ReturnT.FAIL_CODE, "表结构解析失败");
        }

    }

}
package com.hope.util;

import com.hope.core.CodeGeneratorTool;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
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
@Component
public class FreemarkerTool {
    private static final Logger logger = LoggerFactory.getLogger(CodeGeneratorTool.class);

    @Autowired
    private Configuration configuration;

    /**
     * process Template Into String
     *
     * @param template
     * @param model
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public String processTemplateIntoString(Template template, Object model)
            throws IOException, TemplateException {

        StringWriter result = new StringWriter();
        template.process(model, result);
        return result.toString();
    }

    /**
     * process String
     *
     * @param templateName
     * @param params
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public String processString(String templateName, Map<String, Object> params)
            throws IOException, TemplateException {

        Template template = configuration.getTemplate(templateName);
        String htmlText = processTemplateIntoString(template, params);
        return htmlText;
    }


}

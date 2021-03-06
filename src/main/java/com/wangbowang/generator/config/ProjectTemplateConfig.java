package com.wangbowang.generator.config;

import com.baomidou.mybatisplus.generator.config.TemplateConfig;

/**
 * <p>
 * 模板路径静态常量类
 *
 * @author wangliang
 * @since 2018/4/27
 */
public class ProjectTemplateConfig extends TemplateConfig {

    /**
     * provider项目模板
     */
    public final static String provider_controller = "/templates/provider/java/controller.java.vm";
    public final static String provider_entity = "/templates/provider/java/entity.java.vm";
    public final static String provider_mapper = "/templates/provider/java/mapper.java.vm";
    public final static String provider_mapper_xml = "/templates/provider/resources/mapper.xml.vm";
    public final static String provider_service = "/templates/provider/java/service.java.vm";
    public final static String provider_service_impl = "/templates/provider/java/serviceImpl.java.vm";
    public final static String provider_service_exception = "/templates/provider/java/serviceException.java.vm";
    public final static String provider_exception_constant = "/templates/provider/java/exceptionCodeConstant.java.vm";
    public final static String provider_page_request = "/templates/provider/java/pageRequest.java.vm";
    public final static String provider_request = "/templates/provider/java/request.java.vm";
    public final static String provider_response = "/templates/provider/java/response.java.vm";
    public final static String provider_page_vo = "/templates/provider/java/pageVo.java.vm";
    public final static String provider_resp_vo = "/templates/provider/java/respVo.java.vm";
    public final static String provider_service_pom = "/templates/provider/service.pom.vm";

    /**
     * service项目模板
     */
    public final static String seq_constant = "/templates/service/java/seqConstant.java.vm";
    public final static String entity = "/templates/service/java/entity.java.vm";
    public final static String mapper = "/templates/service/java/mapper.java.vm";
    public final static String mapper_xml = "/templates/service/resources/mapper.xml.vm";
    public final static String service = "/templates/service/java/service.java.vm";
    public final static String service_impl = "/templates/service/java/serviceImpl.java.vm";
    public final static String service_exception = "/templates/service/java/serviceException.java.vm";
    public final static String page_request = "/templates/service/java/pageRequest.java.vm";
    public final static String request = "/templates/service/java/request.java.vm";
    public final static String response = "/templates/service/java/response.java.vm";
    public final static String service_pom = "/templates/service/service.pom.vm";

    /**
     * web项目模板
     */
    public final static String controller = "/templates/web/java/controller.java.vm";
    public final static String spring_mvc_config = "/templates/web/java/springMvcConfig.java.vm";
    public final static String web_application = "/templates/web/java/webApplication.java.vm";
    public final static String test = "/templates/web/java/test.java.vm";
    public final static String application_yml = "/templates/web/resources/application.yml.vm";
    public final static String applicationContext_xml = "/templates/web/resources/applicationContext-web.xml.vm";
    public final static String logback_xml = "/templates/web/resources/logback-spring.xml.vm";
    public final static String web_pom = "/templates/web/web.pom.vm";

    public final static String modules_pom = "/templates/modules.pom.vm";


}

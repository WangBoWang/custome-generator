package com.wangbowang.generator.service;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.wangbowang.generator.config.ProjectTemplateConfig;
import com.wangbowang.generator.dto.request.GeneratorMulitModuleRequest;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 多模块代码生成逻辑
 * @author wangb
 * @create 2019/5/8
 * @since 1.0.0
 */
public class MuliteModuleGenerator {

    private static volatile MuliteModuleGenerator generator;

    private MuliteModuleGenerator(){

    }

    public static MuliteModuleGenerator getInstance(){
        if (generator == null) {
            synchronized (MuliteModuleGenerator.class) {
                if (generator == null) {
                    generator = new MuliteModuleGenerator();
                }
            }
        }
        return generator;
    }

    /**
     * 生成多模块代码
     */
    public void multiModuleGeneratorProject(GeneratorMulitModuleRequest request){

    }



    /**
     * 代码生成服务实现入口
     */
    public void generatorProject(GeneratorMulitModuleRequest request){
        //配置数据源
        DataSourceConfig dataSourceConfig = GeneratorCommonService.buildDataSource(request);
        //全局配置
        GlobalConfig globalConfig = GeneratorCommonService.buildGlobalConfig(request);
        //策略配置
        StrategyConfig strategyConfig = GeneratorCommonService.buildStrategyConfig(request);
        //包配置
        PackageConfig packageConfig = GeneratorCommonService.buildPackadeConfig(request);
        //自定义配置
        InjectionConfig injectionConfig = buildInjectionConfig(request);
        //模板配置
        ProjectTemplateConfig projectTemplateConfig = GeneratorCommonService.buildTemplateConfig();

        //生成modules
        if(StringUtils.isNotEmpty(request.getModules())){
            generatorModules(request,dataSourceConfig,strategyConfig,globalConfig,packageConfig,injectionConfig,projectTemplateConfig);
        }
        //生成web项目代码
        if(StringUtils.isNotEmpty(request.getWebProjectName())){
            generatorWebProject(request,dataSourceConfig,strategyConfig,globalConfig,packageConfig,injectionConfig,projectTemplateConfig);
        }
        //生成service代码
        if(StringUtils.isNotEmpty(request.getServiceProjectName())){
            generatorServiceProject(request,dataSourceConfig,strategyConfig,globalConfig,packageConfig,injectionConfig,projectTemplateConfig);
        }
    }

    /**
     * 自定义配置
     * 模板可以使用cfg调用
     */
    public static final InjectionConfig buildInjectionConfig(GeneratorMulitModuleRequest InjectionConfig){
        return new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap();
                map.put("outputDir", InjectionConfig.getOutputDir());
                map.put("groupId", InjectionConfig.getPackageName());
                map.put("modules", InjectionConfig.getModules());
                //包路径
                map.put("package", InjectionConfig.getPackageName());
                map.put("serviceProjectName", InjectionConfig.getServiceProjectName());
                map.put("webProjectName", InjectionConfig.getWebProjectName());
                //表信息集合
                map.put("tableList", this.getConfig().getTableInfoList());
                this.setMap(map);
            }
        };
    }

    /**
     * 生成modules
     */
    private void generatorModules(GeneratorMulitModuleRequest request, DataSourceConfig dataSourceConfig, StrategyConfig strategyConfig,
                                  GlobalConfig globalConfig, PackageConfig packageConfig, InjectionConfig injectionConfig,
                                  ProjectTemplateConfig templateConfig) {
        StringBuilder projectDir = new StringBuilder();
        projectDir.append(request.getOutputDir());
        projectDir.append("\\");
        projectDir.append(request.getModules());
        globalConfig.setOutputDir(projectDir.toString());

        //自定义文件输出
        List<FileOutConfig> fileOutConfigList = new ArrayList<FileOutConfig>();
       GeneratorCommonService.addFileOutConfig(fileOutConfigList, templateConfig.modules_pom,
                projectDir + "/",
                "pom.xml");

        injectionConfig.setFileOutConfigList(fileOutConfigList);
        new AutoGenerator()
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setCfg(injectionConfig)
                .setTemplate(templateConfig)
                .execute();
    }

    private static void generatorWebProject(GeneratorMulitModuleRequest request, DataSourceConfig dataSourceConfig, StrategyConfig strategyConfig,
                                            GlobalConfig globalConfig, PackageConfig packageConfig,
                                            InjectionConfig injectionConfig, ProjectTemplateConfig templateConfig) {
        StringBuilder projectDir = new StringBuilder();
        projectDir.append(request.getOutputDir());
        projectDir.append("\\");
        projectDir.append(request.getModules());
        projectDir.append("\\");
        projectDir.append(request.getWebProjectName());
        globalConfig.setOutputDir(projectDir.toString());
        //自定义配置
        packageConfig.setModuleName("web");

        //自定义文件输出
        List<FileOutConfig> fileOutConfigList = new ArrayList<FileOutConfig>();
       GeneratorCommonService.addFileOutConfig(fileOutConfigList, templateConfig.web_pom,
                projectDir + "/",
                "pom.xml");
       GeneratorCommonService.addFileOutConfigWithTableInfo(fileOutConfigList, templateConfig.controller,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/controller/",
                "Controller.java");
       GeneratorCommonService.addFileOutConfig(fileOutConfigList, templateConfig.web_application,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/",
                "WebApplication.java");
       GeneratorCommonService.addFileOutConfig(fileOutConfigList, templateConfig.spring_mvc_config,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/",
                "WebMvcConfig.java");
       GeneratorCommonService.addFileOutConfig(fileOutConfigList, templateConfig.test,
                projectDir + "/src/test/java/" + packageConfig.getParent() + "/",
                "ApplicationTest.java");
       GeneratorCommonService.addFileOutConfig(fileOutConfigList, templateConfig.applicationContext_xml,
                projectDir + "/src/main/resources/spring/",
                "applicationContext-web.xml");
       GeneratorCommonService.addFileOutConfig(fileOutConfigList, templateConfig.application_yml,
                projectDir + "/src/main/resources/",
                "application.yml");
       GeneratorCommonService.addFileOutConfig(fileOutConfigList, templateConfig.logback_xml,
                projectDir + "/src/main/resources/",
                "logback-spring.xml");

        injectionConfig.setFileOutConfigList(fileOutConfigList);

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator()
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setCfg(injectionConfig)
                .setTemplate(templateConfig);
        mpg.execute();
    }

    private static void generatorServiceProject(GeneratorMulitModuleRequest request, DataSourceConfig dataSourceConfig, StrategyConfig strategyConfig,
                                                GlobalConfig globalConfig, PackageConfig packageConfig,
                                                InjectionConfig injectionConfig, ProjectTemplateConfig templateConfig) {
        StringBuilder projectDir = new StringBuilder();
        projectDir.append(request.getOutputDir());
        projectDir.append("\\");
        if(StringUtils.isNotEmpty(request.getModules())){
            projectDir.append(request.getModules());
        }
        projectDir.append("\\");
        projectDir.append(request.getServiceProjectName());
        globalConfig.setOutputDir(projectDir.toString());

        packageConfig.setModuleName("service");

        List<FileOutConfig> fileOutConfigList = new ArrayList<FileOutConfig>();
       GeneratorCommonService.addFileOutConfig(fileOutConfigList, templateConfig.service_pom,
                projectDir + "/",
                "pom.xml");
       GeneratorCommonService.addFileOutConfigWithTableInfo(fileOutConfigList, templateConfig.controller,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/controller/",
                "Controller.java");
       GeneratorCommonService.addFileOutConfig(fileOutConfigList, templateConfig.seq_constant,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/constants/",
                "SeqConstant.java");
       GeneratorCommonService.addFileOutConfigWithTableInfo(fileOutConfigList, templateConfig.entity,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/entity/",
                ".java");
       GeneratorCommonService.addFileOutConfig(fileOutConfigList, templateConfig.service_exception,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/exceptions/",
                "ServiceException.java");
       GeneratorCommonService.addFileOutConfigWithTableInfo(fileOutConfigList, templateConfig.mapper,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/mapper/",
                "Mapper.java");
       GeneratorCommonService.addFileOutConfigWithTableInfo(fileOutConfigList, templateConfig.page_request,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/dto/request/",
                "PageRequest.java");
       GeneratorCommonService.addFileOutConfigWithTableInfo(fileOutConfigList, templateConfig.service,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/service/",
                "Service.java");
       GeneratorCommonService.addFileOutConfigWithTableInfo(fileOutConfigList, templateConfig.service_impl,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/service/impl/",
                "ServiceImpl.java");
       GeneratorCommonService.addFileOutConfigWithTableInfo(fileOutConfigList, templateConfig.mapper_xml,
                projectDir + "/src/main/resources/mapper/",
                "Mapper.xml");
       GeneratorCommonService.addFileOutConfigWithTableInfo(fileOutConfigList, templateConfig.request,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/dto/request/",
                "Request.java");
       GeneratorCommonService.addFileOutConfigWithTableInfo(fileOutConfigList, templateConfig.response,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/dto/response/",
                "Response.java");
        injectionConfig.setFileOutConfigList(fileOutConfigList);

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator()
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setCfg(injectionConfig)
                .setTemplate(templateConfig);
        mpg.execute();
    }
}

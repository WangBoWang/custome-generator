package com.wangbowang.generator.service;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.wangbowang.generator.config.ProjectTemplateConfig;
import com.wangbowang.generator.context.GeneratorConfigContext;
import com.wangbowang.generator.dto.request.GeneratorMicroServiceRequest;
import com.wangbowang.generator.dto.request.GeneratorMulitModuleRequest;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微服务代码生成逻辑
 * @author wangb
 * @create 2019/5/8
 * @since 1.0.0
 */
public class MicroServiceGenerator{

    private static volatile MicroServiceGenerator generator;

    private MicroServiceGenerator(){

    }

    public static MicroServiceGenerator getInstance(){
        if (generator == null) {
            synchronized (MicroServiceGenerator.class) {
                if (generator == null) {
                    generator = new MicroServiceGenerator();
                }
            }
        }
        return generator;
    }

    /**
     * 代码生成服务实现入口
     */
    public void generatorProject(GeneratorMicroServiceRequest request){
        //获取默认配置
        GeneratorConfigContext generatorConfigContext = GeneratorConfigContext.getDefaultGeneratorConfigContext(request);
        //自定义配置
        InjectionConfig injectionConfig = buildInjectionConfig(request);
        generatorConfigContext.setInjectionConfig(injectionConfig);
        generatorProviderProject(request,generatorConfigContext);
    }

    private static void generatorProviderProject(GeneratorMicroServiceRequest request,GeneratorConfigContext configContext){
        StringBuilder projectDir = new StringBuilder();
        projectDir.append(request.getOutputDir());
        projectDir.append("\\");
        projectDir.append("\\");
        projectDir.append(request.getProjectName());
        GlobalConfig globalConfig = configContext.getGlobalConfig();
        globalConfig.setOutputDir(projectDir.toString());
        PackageConfig packageConfig = configContext.getPackageConfig();

        List<FileOutConfig> fileOutConfigList = new ArrayList<FileOutConfig>();
        GeneratorCommonService.addFileOutConfig(fileOutConfigList, ProjectTemplateConfig.provider_service_pom,
                projectDir + "/",
                "pom.xml");
        GeneratorCommonService.addFileOutConfigWithTableInfo(fileOutConfigList, ProjectTemplateConfig.provider_controller,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/controller/",
                "Controller.java");
        GeneratorCommonService.addFileOutConfigWithTableInfo(fileOutConfigList, ProjectTemplateConfig.provider_entity,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/entity/",
                ".java");
        GeneratorCommonService.addFileOutConfig(fileOutConfigList, ProjectTemplateConfig.provider_service_exception,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/exceptions/",
                "ServiceException.java");
        GeneratorCommonService.addFileOutConfigWithTableInfo(fileOutConfigList, ProjectTemplateConfig.provider_mapper,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/mapper/",
                "Mapper.java");
        GeneratorCommonService.addFileOutConfigWithTableInfo(fileOutConfigList, ProjectTemplateConfig.provider_page_request,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/dto/request/",
                "PageRequest.java");
        GeneratorCommonService.addFileOutConfigWithTableInfo(fileOutConfigList, ProjectTemplateConfig.provider_service,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/service/",
                "Service.java");
        GeneratorCommonService.addFileOutConfigWithTableInfo(fileOutConfigList, ProjectTemplateConfig.provider_service_impl,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/service/impl/",
                "ServiceImpl.java");
        GeneratorCommonService.addFileOutConfigWithTableInfo(fileOutConfigList, ProjectTemplateConfig.provider_mapper_xml,
                projectDir + "/src/main/resources/mapper/",
                "Mapper.xml");
        GeneratorCommonService.addFileOutConfigWithTableInfo(fileOutConfigList, ProjectTemplateConfig.provider_request,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/dto/request/",
                "Request.java");
        GeneratorCommonService.addFileOutConfigWithTableInfo(fileOutConfigList, ProjectTemplateConfig.provider_response,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/dto/response/",
                "Response.java");
        InjectionConfig injectionConfig = configContext.getInjectionConfig();
        injectionConfig.setFileOutConfigList(fileOutConfigList);

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator()
                .setGlobalConfig(globalConfig)
                .setDataSource(configContext.getDataSourceConfig())
                .setStrategy(configContext.getStrategyConfig())
                .setPackageInfo(packageConfig)
                .setCfg(injectionConfig)
                .setTemplate(configContext.getProjectTemplateConfig());
        mpg.execute();
    }

    private static void generatorConsumerProject(){

    }

    private static void generatorAdminProject(){

    }


    /**
     * 自定义配置
     * 模板可以使用cfg调用
     */
    public static final InjectionConfig buildInjectionConfig(GeneratorMicroServiceRequest injectionConfig){
        return new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap();
                map.put("outputDir", injectionConfig.getOutputDir());
                map.put("groupId", injectionConfig.getPackageName());
                //包路径
                map.put("package", injectionConfig.getPackageName());
                map.put("projectName", injectionConfig.getProjectName());
                //表信息集合
                map.put("tableList", this.getConfig().getTableInfoList());
                this.setMap(map);
            }
        };
    }
}

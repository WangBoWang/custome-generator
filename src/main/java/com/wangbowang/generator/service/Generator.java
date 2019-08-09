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
import com.wangbowang.generator.dto.request.GeneratorRequest;
import org.apache.commons.lang.StringUtils;

import java.io.File;
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
public class Generator {

    private static volatile Generator generator;

    private Generator(){

    }

    public static Generator getInstance(){
        if (generator == null) {
            synchronized (Generator.class) {
                if (generator == null) {
                    generator = new Generator();
                }
            }
        }
        return generator;
    }

    /**
     * 生成多模块代码
     */
    public void multiModuleGeneratorProject(GeneratorRequest request){

    }



    /**
     * 代码生成服务实现入口
     */
    public void generatorProject(GeneratorRequest request){
        //配置数据源
        DataSourceConfig dataSourceConfig = buildDataSource(request);
        //全局配置
        GlobalConfig globalConfig = buildGlobalConfig(request);
        //策略配置
        StrategyConfig strategyConfig = buildStrategyConfig(request);
        //包配置
        PackageConfig packageConfig = buildPackadeConfig(request);
        //
        InjectionConfig injectionConfig = buildInjectionConfig(request);
        //模板配置
        ProjectTemplateConfig projectTemplateConfig = buildTemplateConfig();

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
     * 生成modules
     */
    private void generatorModules(GeneratorRequest request,DataSourceConfig dataSourceConfig, StrategyConfig strategyConfig,
                                  GlobalConfig globalConfig, PackageConfig packageConfig, InjectionConfig injectionConfig,
                                  ProjectTemplateConfig templateConfig) {
        StringBuilder projectDir = new StringBuilder();
        projectDir.append(request.getOutputDir());
        projectDir.append("\\");
        projectDir.append(request.getModules());
        globalConfig.setOutputDir(projectDir.toString());

        //自定义文件输出
        List<FileOutConfig> fileOutConfigList = new ArrayList<FileOutConfig>();
        addFileOutConfig(fileOutConfigList, templateConfig.modules_pom,
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

    private static void generatorWebProject(GeneratorRequest request, DataSourceConfig dataSourceConfig,StrategyConfig strategyConfig,
                                            GlobalConfig globalConfig,PackageConfig packageConfig,
                                            InjectionConfig injectionConfig,ProjectTemplateConfig templateConfig) {
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
        addFileOutConfig(fileOutConfigList, templateConfig.web_pom,
                projectDir + "/",
                "pom.xml");
        addFileOutConfigWithTableInfo(fileOutConfigList, templateConfig.controller,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/controller/",
                "Controller.java");
        addFileOutConfig(fileOutConfigList, templateConfig.web_application,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/",
                "WebApplication.java");
        addFileOutConfig(fileOutConfigList, templateConfig.spring_mvc_config,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/",
                "WebMvcConfig.java");
        addFileOutConfig(fileOutConfigList, templateConfig.test,
                projectDir + "/src/test/java/" + packageConfig.getParent() + "/",
                "ApplicationTest.java");
        addFileOutConfig(fileOutConfigList, templateConfig.applicationContext_xml,
                projectDir + "/src/main/resources/spring/",
                "applicationContext-web.xml");
        addFileOutConfig(fileOutConfigList, templateConfig.application_yml,
                projectDir + "/src/main/resources/",
                "application.yml");
        addFileOutConfig(fileOutConfigList, templateConfig.logback_xml,
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

    private static void generatorServiceProject(GeneratorRequest request, DataSourceConfig dataSourceConfig,StrategyConfig strategyConfig,
                                                GlobalConfig globalConfig,PackageConfig packageConfig,
                                                InjectionConfig injectionConfig,ProjectTemplateConfig templateConfig) {
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
        addFileOutConfig(fileOutConfigList, templateConfig.service_pom,
                projectDir + "/",
                "pom.xml");
        addFileOutConfigWithTableInfo(fileOutConfigList, templateConfig.controller,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/controller/",
                "Controller.java");
        addFileOutConfig(fileOutConfigList, templateConfig.seq_constant,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/constants/",
                "SeqConstant.java");
        addFileOutConfigWithTableInfo(fileOutConfigList, templateConfig.entity,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/entity/",
                ".java");
        addFileOutConfig(fileOutConfigList, templateConfig.service_exception,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/exceptions/",
                "ServiceException.java");
        addFileOutConfigWithTableInfo(fileOutConfigList, templateConfig.mapper,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/mapper/",
                "Mapper.java");
        addFileOutConfigWithTableInfo(fileOutConfigList, templateConfig.page_request,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/dto/request/",
                "PageRequest.java");
        addFileOutConfigWithTableInfo(fileOutConfigList, templateConfig.service,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/service/",
                "Service.java");
        addFileOutConfigWithTableInfo(fileOutConfigList, templateConfig.service_impl,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/service/impl/",
                "ServiceImpl.java");
        addFileOutConfigWithTableInfo(fileOutConfigList, templateConfig.mapper_xml,
                projectDir + "/src/main/resources/mapper/",
                "Mapper.xml");
        addFileOutConfigWithTableInfo(fileOutConfigList, templateConfig.request,
                projectDir + "/src/main/java/" + packageConfig.getParent() + "/dto/request/",
                "Request.java");
        addFileOutConfigWithTableInfo(fileOutConfigList, templateConfig.response,
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

    /**
     * 数据源配置
     */
    private DataSourceConfig buildDataSource(GeneratorRequest dbConfig){
        //默认使用mysql配置数据源
        StringBuilder dbUrl = new StringBuilder();
        dbUrl.append("jdbc:mysql://");
        dbUrl.append(dbConfig.getDbHost());
        dbUrl.append(":");
        dbUrl.append(dbConfig.getDbPort().toString());
        dbUrl.append("/");
        dbUrl.append(dbConfig.getDbScheme());
        dbUrl.append("?characterEncoding=UTF-8&serverTimezone=UTC");
        String driverName = "com.mysql.jdbc.Driver";
        return new DataSourceConfig().setDbType(DbType.MYSQL)
                .setUrl(dbUrl.toString())
                .setDriverName(driverName)
                .setUsername(dbConfig.getDbUserName())
                .setPassword(dbConfig.getDbPassword())
                .setTypeConvert(new MySqlTypeConvert() {
                    // 自定义数据库表字段类型转换【可选】
                    @Override
                    public DbColumnType processTypeConvert(String fieldType) {
                        System.out.println("转换类型：" + fieldType);
                        return super.processTypeConvert(fieldType);
                    }
                });
    }

    /**
     * 全局配置
     */
    private GlobalConfig buildGlobalConfig(GeneratorRequest globalConfig) {
        return new GlobalConfig()
//                .setOutputDir(globalConfig.getOutputDir())
                .setFileOverride(true)// 是否覆盖文件
                .setActiveRecord(true)// 开启 activeRecord 模式
                .setEnableCache(false)// XML 二级缓存
                .setBaseResultMap(true)// XML ResultMap
                .setBaseColumnList(true)// XML columList
                .setOpen(false)// 是否打开文件
                .setAuthor(globalConfig.getAuthor());
    }

    /**
     * 策略配置
     */
    private StrategyConfig buildStrategyConfig(GeneratorRequest strategyConfig){
        if (StringUtils.isNotEmpty(strategyConfig.getTableName())) {
            strategyConfig.setTableNames(strategyConfig.getTableName().split(","));
        }
        if (StringUtils.isNotEmpty(strategyConfig.getTablePrefix())) {
            strategyConfig.setTablePrefixes(strategyConfig.getTablePrefix().split(","));
        }
        return new StrategyConfig()
                // 全局大写命名 ORACLE 注意
//                .setCapitalMode(true)
//                .setEntityLombokModel(false)
                // 表名、字段名、是否使用下划线命名（默认 false）
//                .setDbColumnUnderline(true)
                //从数据库表到文件的命名策略
                .setNaming(NamingStrategy.underline_to_camel)
                // 此处可以修改为您的表前缀
                .setTablePrefix(strategyConfig.getTablePrefixes())
                //需要生成的的表名，多个表名传数组
                .setInclude(strategyConfig.getTableNames());
    }

    /**
     * 包配置
     */
    private PackageConfig buildPackadeConfig(GeneratorRequest packadeConfig){
        return  new PackageConfig()
                .setParent(packadeConfig.getPackageName())
//                .setXml("mapper")
//                .setMapper("dao")
//                .setEntity("entity")
                .setController("controller");
    }

    /**
     *
     */
    private InjectionConfig buildInjectionConfig(GeneratorRequest InjectionConfig){
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
     * 模板配置
     */
    private ProjectTemplateConfig buildTemplateConfig(){
        ProjectTemplateConfig projectTemplateConfig = new ProjectTemplateConfig();
        projectTemplateConfig.setController(null)
        .setXml(null)
        .setEntity(null)
        .setMapper(null)
        .setService(null)
        .setServiceImpl(null);
        return projectTemplateConfig;
    }

    private static List<FileOutConfig> addFileOutConfig(List<FileOutConfig> focList, String template, final String path, final String fileName) {
        FileOutConfig outConfig = new FileOutConfig(template) {
            public String outputFile(TableInfo tableInfo) {
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                return path + fileName;
            }
        };
        focList.add(outConfig);
        return focList;
    }

    private static List<FileOutConfig> addFileOutConfigWithTableInfo(List<FileOutConfig> focList, String template, final String path, final String fileSuffix) {
        FileOutConfig outConfig = new FileOutConfig(template) {
            public String outputFile(TableInfo tableInfo) {
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String fileName = tableInfo.getEntityName() + fileSuffix;
                return path + fileName;
            }
        };
        focList.add(outConfig);
        return focList;
    }

}

package com.wangbowang.generator.service;

import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.wangbowang.generator.config.ProjectTemplateConfig;
import com.wangbowang.generator.dto.request.GeneratorCommonRequest;
import com.wangbowang.generator.dto.request.GeneratorMulitModuleRequest;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成公共逻辑
 * @author wangb
 * @create 2019/5/8
 * @since 1.0.0
 */
public class GeneratorCommonService {

    /**
     * 数据源配置
     */
    public static final DataSourceConfig buildDataSource(GeneratorCommonRequest dbConfig){
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
    public static final GlobalConfig buildGlobalConfig(GeneratorCommonRequest globalConfig) {
        return new GlobalConfig()
                // 是否覆盖文件
                .setFileOverride(true)
                // 开启 activeRecord 模式
                .setActiveRecord(true)
                // XML 二级缓存
                .setEnableCache(false)
                // XML ResultMap
                .setBaseResultMap(true)
                // XML columList
                .setBaseColumnList(true)
                // 是否打开文件
                .setOpen(false)
                .setAuthor(globalConfig.getAuthor());
    }

    /**
     * 策略配置
     */
    public static final StrategyConfig buildStrategyConfig(GeneratorCommonRequest strategyConfig){
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
    public static final PackageConfig buildPackadeConfig(GeneratorCommonRequest packadeConfig){
        return  new PackageConfig()
                .setParent(packadeConfig.getPackageName())
//                .setXml("mapper")
//                .setMapper("dao")
//                .setEntity("entity")
                .setController("controller");
    }

    /**
     * 自定义配置
     * 模板可以使用cfg调用
     */
    public static final InjectionConfig buildInjectionConfig(GeneratorCommonRequest InjectionConfig){
        return new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap();
                map.put("outputDir", InjectionConfig.getOutputDir());
                map.put("groupId", InjectionConfig.getPackageName());
                //包路径
                map.put("package", InjectionConfig.getPackageName());
                //表信息集合
                map.put("tableList", this.getConfig().getTableInfoList());
                this.setMap(map);
            }
        };
    }

    /**
     * 模板配置
     */
    public static final ProjectTemplateConfig buildTemplateConfig(){
        ProjectTemplateConfig projectTemplateConfig = new ProjectTemplateConfig();
        projectTemplateConfig.setController(null)
                .setXml(null)
                .setEntity(null)
                .setMapper(null)
                .setService(null)
                .setServiceImpl(null);
        return projectTemplateConfig;
    }

    public  static final List<FileOutConfig> addFileOutConfig(List<FileOutConfig> focList, String template, final String path, final String fileName) {
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

    public static final List<FileOutConfig> addFileOutConfigWithTableInfo(List<FileOutConfig> focList, String template, final String path, final String fileSuffix) {
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

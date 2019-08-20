package com.wangbowang.generator.context;

import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.wangbowang.generator.config.ProjectTemplateConfig;
import com.wangbowang.generator.dto.request.GeneratorCommonRequest;
import com.wangbowang.generator.service.GeneratorCommonService;
import lombok.Builder;
import lombok.Getter;

/**
 * 代码生成配置上下文
 * @author wangb
 * @version 1.0.0
 * @since 2019/8/13
 */
@Getter
@Builder
public class GeneratorConfigContext {

    /**
     * 数据源配置
     */
    private DataSourceConfig dataSourceConfig;

    /**
     * 全局配置
     */
    private GlobalConfig globalConfig;

    /**
     * 策略配置
     */
    private StrategyConfig strategyConfig;

    /**
     * 包配置
     */
    private PackageConfig packageConfig;

    /**
     * 自定义配置
     */
    private InjectionConfig injectionConfig;


    /**
     * 模板配置
     */
    private ProjectTemplateConfig projectTemplateConfig;


    public void setInjectionConfig(InjectionConfig injectionConfig) {
        this.injectionConfig = injectionConfig;
    }

    public static GeneratorConfigContext getDefaultGeneratorConfigContext(GeneratorCommonRequest request){
        //配置数据源
        DataSourceConfig dataSourceConfig =  GeneratorCommonService.buildDataSource(request);
        //全局配置
        GlobalConfig globalConfig = GeneratorCommonService.buildGlobalConfig(request);
        //策略配置
        StrategyConfig strategyConfig = GeneratorCommonService.buildStrategyConfig(request);
        //包配置
        PackageConfig packageConfig = GeneratorCommonService.buildPackadeConfig(request);
        //自定义配置
        InjectionConfig injectionConfig = GeneratorCommonService.buildInjectionConfig(request);
        //模板配置
        ProjectTemplateConfig projectTemplateConfig = GeneratorCommonService.buildTemplateConfig();
        return GeneratorConfigContext.builder().dataSourceConfig(dataSourceConfig)
                .globalConfig(globalConfig)
                .strategyConfig(strategyConfig)
                .packageConfig(packageConfig)
                .injectionConfig(injectionConfig)
                .projectTemplateConfig(projectTemplateConfig)
                .build();
    }
}

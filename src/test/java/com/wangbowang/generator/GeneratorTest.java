package com.wangbowang.generator;

import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.wangbowang.generator.constant.ServiceTypeConstant;
import com.wangbowang.generator.dto.request.GeneratorMicroServiceRequest;
import com.wangbowang.generator.dto.request.GeneratorMulitModuleRequest;
import com.wangbowang.generator.service.MicroServiceGenerator;
import com.wangbowang.generator.service.MuliteModuleGenerator;
import org.junit.Test;

/**
 * 代码生成测试
 * @author wangb
 * @create 2019/5/8
 * @since 1.0.0
 */
public class GeneratorTest {

    /**
     * 多项目整合项目生成案列
     */
    @Test
    public void multiModulegenerator(){
        GeneratorMulitModuleRequest generatorRequest = new GeneratorMulitModuleRequest();
        //配置数据源
        generatorRequest.setDbType(DbType.MYSQL.getValue()); //数据库类型
        generatorRequest.setDbHost("localhost"); //数据库host
        generatorRequest.setDbPort(3306); //数据库port，不填默认3306
        generatorRequest.setDbUserName("root"); //用户名
        generatorRequest.setDbPassword("wangbowang"); //密码
        generatorRequest.setDbScheme("beerich"); //数据库实列
        generatorRequest.setAuthor("wbw");
        //配置策略
//        generatorRequest.setTableName("vip_behavior_log");// 表名，多个逗号分隔
//        generatorRequest.setTablePrefix("vip_");// 表前缀，多个逗号分隔
        //配置项目结构和输出路径
        generatorRequest.setOutputDir("E:\\generator\\");// 文件输出路径
        generatorRequest.setModules("data-sync-integration");// 模块名称
        generatorRequest.setServiceProjectName("data-sync-service");// service项目名称
        generatorRequest.setWebProjectName("beerich_interface_web");// web项目名称
        generatorRequest.setPackageName("com.beerich.integration.data.sync");// 自定义包路径

        //生成项目
        MuliteModuleGenerator.getInstance().generatorProject(generatorRequest);
    }

    /**
     * 单独项目生成案列
     */
    @Test
    public void singleModulegenerator(){
        GeneratorMulitModuleRequest generatorRequest = new GeneratorMulitModuleRequest();
        //配置数据源
        generatorRequest.setDbType(DbType.MYSQL.getValue()); //数据库类型
        generatorRequest.setDbHost("localhost"); //数据库host
        generatorRequest.setDbPort(3306); //数据库port，不填默认3306
        generatorRequest.setDbUserName("root"); //用户名
        generatorRequest.setDbPassword("wangbowang"); //密码
        generatorRequest.setDbScheme("beerich"); //数据库实列
        generatorRequest.setAuthor("wbw");
        //配置策略
//        generatorRequest.setTableName("user_compose_info,zb_loan_order,zb_loan_scheme,yj_user_service_evaluation");// 表名，多个逗号分隔
//        generatorRequest.setTablePrefix("vip_");// 表前缀，多个逗号分隔
        //配置项目结构和输出路径
        generatorRequest.setOutputDir("E:\\generator\\");// 文件输出路径
        generatorRequest.setPackageName("com.beerich.integration.data.sync");// 自定义包路径
        generatorRequest.setServiceProjectName("data-sync-service");// service项目名称
        //生成项目
        MuliteModuleGenerator.getInstance().generatorProject(generatorRequest);
    }


    /**
     * 测试微服务provider生成
     */
    @Test
    public void microProviderGenerator(){
        GeneratorMicroServiceRequest generatorRequest = new GeneratorMicroServiceRequest();
        //配置数据源
        generatorRequest.setDbType(DbType.MYSQL.getValue()); //数据库类型
        generatorRequest.setDbHost("rm-wz9e39c807z0hz6viuo.mysql.rds.aliyuncs.com"); //数据库host
        generatorRequest.setDbPort(3306); //数据库port，不填默认3306
        generatorRequest.setDbUserName("beerich_dev"); //用户名
        generatorRequest.setDbPassword("m2iEFYl#6IH"); //密码
        generatorRequest.setDbScheme("beerich_product_dev"); //数据库实列
        generatorRequest.setAuthor("wangb");
        //配置策略
        generatorRequest.setTableName("planning_report_product_config");// 表名，多个逗号分隔
//        generatorRequest.setTablePrefix("vip_");// 表前缀，多个逗号分隔
        //配置项目结构和输出路径
//        generatorRequest.setOutputDir("E:\\workSpace\\beerich-integration");// 文件输出路径
//        generatorRequest.setTableName("holiday_config");// 表名，多个逗号分隔
        generatorRequest.setOutputDir("E:\\generator\\");// 文件输出路径
        generatorRequest.setPackageName("com.beerich.integration.product");// 自定义包路径
        generatorRequest.setProjectName("product-service");// service项目名称
        generatorRequest.setServiceType(ServiceTypeConstant.PROVIDER);
        //生成项目
        MicroServiceGenerator.getInstance().generatorProject(generatorRequest);
    }

}

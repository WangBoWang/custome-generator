package com.wangbowang.generator;

import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.wangbowang.generator.dto.request.GeneratorRequest;
import com.wangbowang.generator.service.Generator;
import org.junit.Test;

/**
 * 代码生成测试
 * @author wangb
 * @create 2019/5/8
 * @since 1.0.0
 */
public class GeneratorTest {

    @Test
    public void generator(){
        GeneratorRequest  generatorRequest = new GeneratorRequest();
        //配置数据源
        generatorRequest.setDbType(DbType.MYSQL.getValue()); //数据库类型
        generatorRequest.setDbHost("localhost"); //数据库host
        generatorRequest.setDbPort(3306); //数据库port，不填默认3306
        generatorRequest.setDbUserName("root"); //用户名
        generatorRequest.setDbPassword("wangbowang"); //密码
        generatorRequest.setDbScheme("beerich"); //数据库实列
        //配置策略
        generatorRequest.setTableName("vip_behavior_log");// 表名，多个逗号分隔
        generatorRequest.setTablePrefix("vip_");// 表前缀，多个逗号分隔
        //配置项目结构和输出路径
        generatorRequest.setOutputDir("E:\\generator");// 文件输出路径
        generatorRequest.setPackageName("com.anjiebang");// 自定义包路径
        generatorRequest.setModules("beerich_interface");// 模块名称
        generatorRequest.setServiceProjectName("beerich_interface_api");// service项目名称
        generatorRequest.setWebProjectName("beerich_interface_web");// web项目名称
        //生成项目
        Generator.getInstance().generatorProject(generatorRequest);
    }
}

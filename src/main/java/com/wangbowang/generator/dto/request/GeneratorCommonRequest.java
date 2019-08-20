package com.wangbowang.generator.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 代码生成通用请求参数
 * @author wangb
 * @create 2019/08/12
 * @since 1.0.0
 */
@Data
public class GeneratorCommonRequest {
    /**
     * 数据库类型
     * mysql,oracle,等等
     * 目前只支持mysql
     */
    private String dbType;
    /**
     * 数据库连接地址
     */
    @NotBlank(message = "数据库连接地址不能为空")
    private String dbHost;

    /**
     * 数据库连接端口
     */
    @NotNull(message = "数据库连接端口不能为空")
    private Integer dbPort = 3306;

    /**
     * 数据库用户名
     */
    @NotBlank(message = "数据库用户名不能为空")
    private String dbUserName;
    /**
     * 数据库密码
     */
    @NotBlank(message = "数据库密码不能为空")
    private String dbPassword;
    /**
     * 数据库实例
     */
    @NotBlank(message = "数据库实例不能为空")
    private String dbScheme;

    /**
     * 表名，多个逗号分隔
     */
    @NotBlank(message = "表名不能为空")
    private String tableName;
    private String[] tableNames;
    /**
     * 表前缀，多个逗号分隔
     */
    @NotBlank(message = "表前缀不能为空")
    private String tablePrefix;
    private String[] tablePrefixes;
    /**
     * 自定义包路径
     */
    @NotBlank(message = "自定义包路径不能为空")
    private String packageName;
    /**
     * 文件输出路径
     */
    @NotBlank(message = "文件输出路径不能为空")
    private String outputDir;
    /**
     * 作者
     */
    @NotBlank(message = "作者不能为空")
    private String author;
}

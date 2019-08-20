package com.wangbowang.generator.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 代码生成请求参数
 * @author wangb
 * @create 2019/5/8
 * @since 1.0.0
 */
@Data
public class GeneratorMulitModuleRequest extends GeneratorCommonRequest{
    /**
     * service项目名称
     */
    @NotBlank(message = "service项目名称不能为空")
    private String serviceProjectName;
    /**
     * web项目名称
     */
    @NotBlank(message = "web项目名称不能为空")
    private String webProjectName;

    /**
     * 模块名称
     * java包下面最外层的包名
     */
    @NotBlank(message = "模块名称不能为空")
    private String modules;
}

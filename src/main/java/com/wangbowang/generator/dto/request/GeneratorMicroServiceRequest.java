package com.wangbowang.generator.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 微服务代码生成请求
 * @author wangb
 * @version 1.0.0
 * @since 2019/8/12
 */
@Data
public class GeneratorMicroServiceRequest extends GeneratorCommonRequest{

    /**
     * provider、consumer、admin
     * 见ServiceTypeConstant
     */
    private String serviceType;

    /**p
     * 项目名称
     */
    @NotBlank(message = "项目名称不能为空")
    private String projectName;
}

package com.ryan.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Springfox提供Docket对象，为其设置相关属性，
 * 将其注册成为spring的bean后，可以在接口文档中展示（可配置多个Docket的bean，对应不同分组的接口）
 */
@Configuration
@EnableSwagger2
public class Swagger2Conf {

    @Value(value = "${swagger.enabled}")
    private boolean swaggerEnabled;

    @Value(value = "${swagger.title}")
    private String swaggerTitle;

    @Value(value = "${swagger.description}")
    private String swaggerDescription;

    @Value(value = "${swagger.version}")
    private String swaggerVersion;

    @Value(value = "${swagger.package}")
    private String swaggerPackage;

    @Value(value = "${swagger.header.key:}")
    private String swaggerHeaderKey;

    @Value(value = "${swagger.header.name:}")
    private String swaggerHeaderName;

    @Value(value = "${swagger.contact.name}")
    private String swaggerContactName;

    @Value(value = "${swagger.contact.url:}")
    private String swaggerContactUrl;

    @Value(value = "${swagger.contact.email}")
    private String swaggerContactEmail;


    @Bean
    public Docket createRestApi(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(swaggerEnabled)
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerPackage))
                .paths(PathSelectors.any())
                .build();
        if (!StringUtils.isEmpty(swaggerHeaderKey)) {
            //添加head参数start
            ParameterBuilder tokenPar = new ParameterBuilder();
            List<Parameter> pars = new ArrayList<Parameter>();
            tokenPar.name(swaggerHeaderKey).description(swaggerHeaderName).modelRef(new ModelRef("string")).parameterType("header").required(false).build();
            pars.add(tokenPar.build());
            docket.globalOperationParameters(pars);
        }

        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerTitle)//api标题
                .description(swaggerDescription)//api描述
                .version(swaggerVersion)//版本号
                .contact(new Contact(swaggerContactName,swaggerContactUrl,swaggerContactEmail))
                .build();
    }
}

/**
 * 常用注解如下：
 *
 * 注解	使用的地方	用途
 * @Api 类/接口	描述类/接口主要用途
 * @ApiOperation 方法    描述方法的用途
 * @ApiImplicitParam 方法    用于描述接口的非对象参数
 * @ApiImplicitParams 方法    用于描述接口的非对象参数集
 * @ApiIgnore 类/方法/参数	Swagger 文档不会显示拥有该注解的接口
 * @ApiModel 参数实体类    可设置接口相关实体的描述
 * ApiModelProperty	参数实体类属性	可设置实体属性的相关描述
 *
 */

package com.ryan.server.controller;

import com.ryan.db.dao.CodeGenerator;
import com.ryan.common.Util.StringUtil;
import com.ryan.db.dao.CodeGeneratorInfo;
import com.ryan.common.dao.R;
import com.ryan.db.entity.DatasourceManageEntity;
import com.ryan.db.service.DatasourceManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("代码生成器")
@RestController
@RequestMapping("codeGenerator")
public class CodeGeneratorController {

    @Autowired
    private DatasourceManageService datasourceManageService;

    @ApiOperation("执行")
    @PostMapping("/exe")
    public R execute(@RequestBody CodeGeneratorInfo codeGeneratorInfo){

        DatasourceManageEntity datasourceInfo;

        // 保存路径和数据库名为空，直接返回错误信息
        if(StringUtil.isEmpty(codeGeneratorInfo.getProjectPath()) || StringUtil.isEmpty(codeGeneratorInfo.getTableName())){
            return R.error().data("提供数据源数据不完整");
        }
        //如果数据源id不为空，则从数据库中获取数据源，否则，根据传入的数据去查询
        if(!StringUtil.isEmpty(codeGeneratorInfo.getDatasourceId())){
            DatasourceManageEntity datasourceManageEntity = new DatasourceManageEntity();
            datasourceManageEntity.setId(codeGeneratorInfo.getDatasourceId());
            datasourceInfo = datasourceManageService.selectById(datasourceManageEntity);
            if(datasourceInfo == null){
                return R.error().data("没有找到数据源");
            }

            codeGeneratorInfo.setDriver(datasourceInfo.getDriver());
            codeGeneratorInfo.setUrl(datasourceInfo.getUrl());
            codeGeneratorInfo.setUsername(datasourceInfo.getUsername());
            codeGeneratorInfo.setPassword(datasourceInfo.getPassword());
            codeGeneratorInfo.setSchema(datasourceInfo.getSchema());
            codeGeneratorInfo.setProjectPath(codeGeneratorInfo.getProjectPath());
            codeGeneratorInfo.setTableName(codeGeneratorInfo.getTableName());
        }else if (StringUtil.isEmpty(codeGeneratorInfo.getDriver()) || StringUtil.isEmpty(codeGeneratorInfo.getUrl())
                || StringUtil.isEmpty(codeGeneratorInfo.getSchema()) || StringUtil.isEmpty(codeGeneratorInfo.getUsername())
                || StringUtil.isEmpty(codeGeneratorInfo.getPassword()) || StringUtil.isEmpty(codeGeneratorInfo.getProjectPath())
                || StringUtil.isEmpty(codeGeneratorInfo.getTableName())){
            return R.error().data("提供数据源数据不完整");
        }

        // 调用生成器
        CodeGenerator.execute(codeGeneratorInfo);
        return R.ok();
    }
}

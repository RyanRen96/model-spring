package com.ryan.server.controller;


import com.ryan.common.dao.R;
import com.ryan.db.entity.DatasourceManageEntity;
import com.ryan.db.service.DatasourceManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ryan
 * @since 2021-01-21
 */
@RestController
@RequestMapping("datasourceManage")
public class DatasourceManageController {

    @Autowired
    private DatasourceManageService datasourceManageService;

    public R selectDatasourceManageById(@RequestBody DatasourceManageEntity datasourceManageEntity){
        DatasourceManageEntity entity = datasourceManageService.selectById(datasourceManageEntity);
        return R.ok().data(entity);
    }
}


package com.ryan.db.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ryan.db.entity.DatasourceManageEntity;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Ryan
 * @since 2021-01-21
 */
public interface DatasourceManageService extends IService<DatasourceManageEntity> {

    DatasourceManageEntity selectById(DatasourceManageEntity datasourceManageEntity);

}

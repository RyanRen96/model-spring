package com.ryan.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ryan.db.entity.DatasourceManageEntity;
import com.ryan.db.mapper.DatasourceManageMapper;
import com.ryan.db.service.DatasourceManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Ryan
 * @since 2021-01-21
 */
@Service("DatasourceManageService")
public class DatasourceManageServiceImpl extends ServiceImpl<DatasourceManageMapper, DatasourceManageEntity> implements DatasourceManageService {

    @Autowired
    private DatasourceManageMapper datasourceManageMapper;

    @Override
    public DatasourceManageEntity selectById(DatasourceManageEntity datasourceManageEntity) {
        return datasourceManageMapper.selectById(datasourceManageEntity);
    }

}

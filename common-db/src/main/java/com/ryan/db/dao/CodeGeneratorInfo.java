package com.ryan.db.dao;

import lombok.Data;

/**
 * 代码生成器参数
 *
 * @author RyanRen
 */
@Data
public class CodeGeneratorInfo {

    /**
     * 数据源id
     */
    private String datasourceId;
    /**
     * 数据源信息
     */
    private String driver;
    private String url;
    private String username;
    private String password;
    private String schema;
    /**
     * 文件生成后保存的位置
     */
    private String projectPath;
    /**
     * 表名
     */
    private String tableName;

}

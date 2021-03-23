package com.ryan.common.dao;

import com.ryan.common.dao.IEnum;

public enum CodeDefined implements IEnum {

    SUCCESS("0000", "成功"),
    ERROR("9999", "系统异常,请稍后再试"),

    /* ------------------ 以下为自定义异常类型 千位：大分类  百位：小分类   个位十位：自定义---------------------------- */
    CD_BOOT_SUCCESS("200", "成功"),

    ERROR_RESPONSE("0005", "服务响应对象类型转换错误"),
    VALID("0009", "自定义验证"),

    // URL 参数 错误
    ERROR_URL_NOT_FOUND("2100", "URL地址错误"),
    ERROR_METHOD("2101", "请求方法类型错误，可以使用' %s '等类型"),
    ERROR_PARAMETER("2200", "参数错误"),
    ERROR_SYNTAX("2201", "请求语法错误"),
    ERROR_CAPTCHA_LACK_PARAM("2202", "缺少获取验证码的必要参数"),
    ERROR_OBJECT_TO_JSONSTRING("2202", "对象转json格式字符串异常"),
    ERROR_NO_DATASOURCEORSQL("2401", "数据源信息或sql信息错误，接口正确调用，请检查数据源连通性及sql信息"),
    ERROR_NO_ACCESS_TASK("2500", "参数错误，缺少accessTask参数"),

    // 账户 错误
    ERROR_LOCK_LOGIN("3000", "该账户已被锁定"),
    ERROR_LOCK_LOGIN_PASS_MISTAKE("3001", "该账户因为密码错误次数过多，已被暂时锁定，请30分钟后重试登录"),

    //组织机构 通知公告 应用 错误
    ERROR_ORGANIZATION_NAME_NULL("3100", "organizationName字段必填！！"),
    ERROR_ORGANIZATION_EXIST("3101", "组织机构名称已存在"),
    ERROR_NOTICE_MESSAGEDIGEST_NULL("3102", "公告标题不能为空！！"),
    ERROR_NOTICE_MESSAGECONTENT_NULL("3103", "公告内容不能为空！！"),
    ERROR_ILLEGAL_STRING("3104", "公告内容包含非法字符' %s '！！请重新编写"),
    ERROR_ORGANIZATION_NO_REPORT("3105", "您所属的组织机构上报国家后方可上报应用，请您耐心等待！"),
    ERROR_ANNOUNCEMENT_MESSAGEDIGEST_NULL("3106", "通知标题不能为空！！"),
    ERROR_ANNOUNCEMENT_MESSAGECONTENT_NULL("3107", "通知内容不能为空！！"),
    ERROR_ILLEGAL_ANNOUNCEMENT_STRING("3108", "通知包含非法字符' %s '！！请重新编写"),

    // oauth 二次验证
    ERROR_OAUTH_USER("3201", "验证错误"),
    ERROR_SHIRO_AUTH("3202", "鉴权失败，暂无权限执行当前操作"),

    // 其他
    ERROR_RESOURCE_AGENT("4001", "资源未被代理"),
    ERROR_UPDATE_ERROR("4002", "更新失败"),
    ERROR_WITHDRAW_ERROR("4003", "撤回失败"),
    ERROR_NULL_ORGID_ERROR("4004", "组织结构id不能为空"),
    ERROR_NO_ORGID_ERROR("4005", "该组织机构没有相关的绑定信息"),
    ERROR_CAN_NOT_OPERATE_CURRENT_USER("4006", "不能操作当前用户"),

    // 数据脱敏
    ERROR_START_MODE("4100", "该任务启动模式不为定时启动"),
    ERROR_DEPLOY_STATUS("4101", "该任务部署未部署"),
    ERROR_STOP_TASK_FAIL("4102", "停止部署任务失败"),
    ERROR_START_TASK_FAIL("4103", "任务部署失败"),

    // 数据库错误
    ERROR_DUPLICATE_KEY("5001", "存在重复信息，请确认后重新输入！"),

    // 文件类型错误
    ERROR_NOT_FIND_FILE("6002", "未找到资源文件"),

    // MQ 错误
    MQ_SEND_ERROR("7001", "发送MQ消息异常"),
    MQ_RECEIVE_ERROR("7002", "接收MQ消息异常"),


    //elasticSearch日志错误
    NO_SUCH_INDEX("7100", "日志中没有' %s '模块"),
    NO_INDEX("7101", "没有指定日志模块"),


    // 业务错误
    ERROR_QUERY_TYPE("8000", "不存在的查询类型，1-待分配 2-已分配 3-待开库 4-已开库 5-待测试 6-已测试"),
    ERROR_TABLE_TYPE("8001", "不存在的查询类型，0-未上传 1-已上传"),
    ERROR_PREPOSITIONCONFIGURE_TYPE("8002", "前置机类型有误, 0为归集前置机，1为共享前置机，2为全部前置机"),


    // 第三方信息 账户信息 错误
    ERROR_TOKEN("9900", "TOKEN认证错误"),
    ERROR_USER_OR_PASS("9901", "用户名或密码错误，请注意区分用户名或密码的大小写"),
    ERROR_SINGLE_DEVICE_LOGIN("9902", "你的账号在其他地方被登录，请尽快确定账号安全！"),
    ERROR_CAPTCHA("9903", "验证码错误或失效"),
    ERROR_USER_EXIST("9904", "用戶名已存在,请修改用戶名"),
    ERROR_REQUEST_URL("9990", "第三方URL调用异常"),
    ERROR_CONNECTION("9998", "第三方API调用异常"),

    //添加节点重复
    ERROR_NODENAME_EXIST("9904", "节点名称已存在,请修改节点名称"),
    ERROR_NODELOGO_EXIST("9904", "节点标识已存在,请修改节点标识"),
    ERROR_VERSIONNAME_EXIST("9904", "版本包号已存在,请修改版本包号"),
    ERROR_CATALOGNAME_EXIST("9904", "目录名称已存在，请修改目录名称"),
    ERROR_DATAELEMENTCLASSNAME_EXIST("9904", "数据元分类名称已存在，请修改数据元分类名称"),
    ERROR_VALUERANGECLASSNAME_EXIST("9904", "值域分类名称已存在，请修改值域分类名称"),
    ERROR_VALUERANGENAME_EXIST("9904", "值域名称已存在，请修改值域名称"),
    ERROR_DATAELEMENTNAME_EXIST("9904", "数据元名称已存在，请修改数据元名称"),
    ERROR_DATASTANDARDNAME_EXIST("9904", "数据标准名称已存在，请修改数据标准名称"),
    ERROR_APINAME_EXIST("9904", "api名称名称已存在，请修改api名称"),
    ERROR_DATASTANDARDNAMECLASSNAME_EXIST("9904", "目录分类名称已存在，请修改目录分类名称"),
    ERROR_DATAUSENAME_EXIST("9904", "分析任务名称已存在，请修改分析任务名称"),
    ERROR_DATA_SET_NAME_EXIST("9904", "数据名称已存在，请修改数据名称"),
    ERROR_DATA_SET_EXCHANGE_NAME_EXIST("9904", "数据交换任务名称已存在，请修改数据交换任务名称"),
    ERROR_ACCESSNAME_EXIST("9904", "接入引擎名称已存在，请修改接入引擎名称"),
    ERROR_ANALYZENAME_EXIST("9904", "分析工具名称已存在，请修改分析工具名称"),
    ERROR_ANALYZEMODELNAME_EXIST("9904", "分析工具模型名称已存在，请修改分析工具模型名称"),
    ERROR_SANDBOXNAME_EXIST("9904", "沙箱名称已存在，请修改沙箱名称"),
    ERROR_DATASOURCE_MANAGE_NAME_EXIST("9904", "数据源名称已存在，请修改数据源名称"),
    ERROR_CATALOG_BASIC_NAME_EXIST("9904", "当前版本该目录名称已存在，请修改目录名称或版本号"),
    ERROR_JARGON_NAME_EXIST("9904", "术语名称已存在，请修改术语名称"),
    ERROR_JARGON_CLASS_NAME_EXIST("9904", "术语分类名称已存在，请修改术语分类名称"),
    ERROR_VOCABULARY_NAME_EXIST("9904", "词汇表名称已存在，请修改词汇表名称"),
    ERROR_DETECTIONRULECLASSNAME_EXIST("9904", "规则分类已存在，请修改规则分类表名称"),
    ERROR_DETECTIONRULENAME_EXIST("9904", "规则名称已存在，请修改规则表名称"),
    ERROR_QUALITYNAME_EXIST("9904", "质量任务名称已存在，请修改质量任务名称"),
    ERROR_DETECTIONRULECODE_EXIST("9904", "规则编码已存在，请修改规则编码"),

    //添加字典重复
    ERROR_CODE_KEY_EXIST("9904", "字典Code与key已存在,请修改字典Code或key"),

    //删除自定义表单存在数据
    ERROR_CUSTOM_FROM_EXIST_DATA("9880", "自定义表单中存在数据!"),
    /***************************************************************************/

    //数据交换接入任务
    ERROR_DATAX_DELETE("9870", "还有部署中和接入中子任务，请取消部署或者等任务执行完毕再删除");

    private String code;

    private String msg;

    CodeDefined(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.msg;
    }
}

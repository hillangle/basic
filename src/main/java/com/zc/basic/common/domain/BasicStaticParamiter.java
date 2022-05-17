package com.zc.basic.common.domain;

import lombok.Data;

@Data
public class BasicStaticParamiter {

    /**
     * 性别字典-未知
     */
    public static String SEX_UNKNOWN = "0";
    /**
     * 性别字典-男
     */
    public static String SEX_MAN = "1";
    /**
     * 性别字典-女
     */
    public static String SEX_WOMEN = "2";

    /**
     * 数据状态-启用
     */
    public static String ENABLE = "Y";

    /**
     * 数据状态-禁用
     */
    public static String NOTENABLE = "N";

    /**
     * 删除状态-已删除
     */
    public static String DELETED = "Y";

    /**
     * 删除状态-未删除
     */
    public static String NOTDELETE = "N";
}

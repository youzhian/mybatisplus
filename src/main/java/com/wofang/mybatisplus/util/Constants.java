package com.wofang.mybatisplus.util;

/**
 * 常量
 * @author 游志安
 */
public final class Constants {
    /**
     * 请求类型-资格验证
     */
    public static final String REQUEST_TYPE_VERIFY = "1";
    /**
     * 请求类型-表单提交
     */
    public static final String REQUEST_TYPE_SUBMIT = "2";
    /**
     * 请求类型-选号
     */
    public static final String REQUEST_TYPE_STATE = "3";
    /**
     * 请求结果-成功
     */
    public static final String SUCCESS_FLG_SUCCESS = "1";
    /**
     * 请求结果-失败
     */
    public static final String SUCCESS_FLG_FAIL = "2";

    /**
     * 流水号最大值
     */
    public static int MAX_NUM = 18;//999999999;

    /**
     * 证类型-身份证：11
     */
    public static final String CERT_TYPE_ID = "11";
    /**
     * 用户ID
     */
    public static final String uid = "jixian";
    /**
     * MD5加密时所需的key
     */
    public static final String KEY = "jx10253#947+xj--";
    /**
     * key在请求参数中的参数名
     */
    public static final String KEY_NAME = "key";

    /**
     * 编码格式
     */
    public static final String CHARACTER_ENCODING_UTF8 = "UTF-8";
    /**
     * 用户资格验证请求地址
     */
    //public static final String URL_USER_VERIFY = "https://www.73110010.com/portal/woSale/query/userVerify";
    public static final String URL_USER_VERIFY = "https://www.73110010.com/portal/simpleWoSale/query/checkAllowedOrder";
    /**
     * 大王卡表单提交
     */
    public static final String URL_KING_ORDER_SUBMIT = "https://www.73110010.com/portal/simpleWoSale/order/KOrderSubmit";
    /**
     * 产品ID
     */
    public static final String PRODUCT_PRODUCT_ID = "90063345";
    /**
     * 产品code
     */
    public static final String PRODUCT_PRODUCT_CODE = "981610241535";
    /**
     * 产品名称
     */
    public static final String PRODUCT_PRODUCT_NAME = "腾讯大王卡";
    /**
     * 项目ID
     */
    public static final String PRODUCT_PROJECT_ID = "07_13_csjxkjh_1177657";
    /**
     * 触点ID
     */
    public static final String PRODUCT_TOUCH_ID = "08-2278-3948-2976";
    /**
     * 触点名称
     */
    public static final String PRODUCT_TOUCH_NAME = "吉先K计划引流";
    /**
     * 发展人ID
     */
    public static final String PRODUCT_DEVELOPER_ID = "A11437905";
    /**
     * 发展人名称
     */
    public static final String PRODUCT_DEVELOPER_NAME = "市含浦吉先沃店（线上）";
    /**
     * 联通接口成功标识
     */
    public static final String KING_ORDER_SUCCESS_CODE = "0000";

    public static final String KING_ORDER_RESP_CODE_KEY = "respCode";

    public static final String KING_ORDER_RET_CDOE_KEY = "retCode";
}

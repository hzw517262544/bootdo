package com.bootdo.common.config;

public class Constant {
    //演示系统账户
    public static String DEMO_ACCOUNT = "test";
    //自动去除表前缀
    public static String AUTO_REOMVE_PRE = "true";
    //停止计划任务
    public static String STATUS_RUNNING_STOP = "stop";
    //开启计划任务
    public static String STATUS_RUNNING_START = "start";
    //通知公告阅读状态-未读
    public static String OA_NOTIFY_READ_NO = "0";
    //通知公告阅读状态-已读
    public static int OA_NOTIFY_READ_YES = 1;
    //部门根节点id
    public static Long DEPT_ROOT_ID = 0l;
    //缓存方式
    public static String CACHE_TYPE_REDIS ="redis";

    public static String LOG_ERROR = "error";
    //是否删除的标志-删除
    public static final String DELETE_FLAG_DEL = "1";
    //是否删除的标志-正常
    public static final String DELETE_FLAG_NORMAL = "0";

    public static final String TEMPORARY_VISITOR_NAME = "游客";

    public static final String TEMPORARY_VISITOR_ID = "0";
    //游客默认头像
    public static final String TEMPORARY_VISITOR_PICURL = "/img/blog/post-sample-image.jpg";
    //附件类型：1-通知
    public static final int SOURCE_TYPE_NOTIFY = 1;
    //附件类型：2-博客
    public static final int SOURCE_TYPE_BLOG = 2;
    
}

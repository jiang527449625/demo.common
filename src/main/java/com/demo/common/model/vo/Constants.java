package com.demo.common.model.vo;

/**
 * <p>Description: 系统静态变量</p>
 * <p>Title: Constants.java</p>
 * <p> </p>
 */
public class Constants {
    /***用于token **************** start**********************************************************/
    public static final String USERID = "userId";

    public static final String USERNAME = "userName";

    public static final String ROLEIDS = "roleIds";

    public static final String USER = "user";

    public static final String ORGUUID = "orgUuid";

    public static final String TOKEN = "token";
    /***用于token **************** end************************************************************/

    /***用于素材管理审核流程 **************** start****************************************************/
    public static final String NotAudit = "0"; //未审核
    public static final String ToAudit = "1";  //审核中、待审核
    public static final String OverAudit = "2";  //已审核
    public static final String Rejected = "3";//已驳回
    public static final String messageManagerStatus = "'0','1','3'";//素材管理列表状态
    public static final String messageAuditStatus = "'1','2'";//素材审核列表状态
    /***用于素材管理审核流程 **************** end******************************************************/

    /***用于树形结构 **************** start****************************************************/
    public static final String TREEPID = "0"; //父节点id
    /***用于树形结构 **************** end******************************************************/

    /***用于AOP操作日志类型 **************** start****************************************************/
    public static final String AOPLOGINSERT = "新增"; //新增
    public static final String AOPLOGUPDATE = "修改"; //修改
    public static final String AOPLOGDELETE = "删除"; //删除
    /***用于AOP操作日志类型 **************** end******************************************************/

}

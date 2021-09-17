package com.demo.common.utils;

public class OrgSqlUtils {

	public static String getOrgIdSQL(String columnName,String orgId) {
		if(StringUtil.isEmpty(columnName) || StringUtil.isEmpty(orgId) || orgId.equals("1")){
			return null;
		}
		StringBuffer orgIdSQL = new StringBuffer();
		orgIdSQL.append(" AND ");
		orgIdSQL.append(columnName);
		orgIdSQL.append(" in (");
		orgIdSQL.append("WITH RECURSIVE r AS ( SELECT * FROM t_bus_sys_org WHERE org_uuid = '").append(orgId);
		orgIdSQL.append("' union ALL SELECT t_bus_sys_org.* FROM t_bus_sys_org, r WHERE t_bus_sys_org.org_parent_uuid = r.org_uuid ) SELECT org_uuid FROM r");
		orgIdSQL.append(")");
		return orgIdSQL.toString();
	}
}

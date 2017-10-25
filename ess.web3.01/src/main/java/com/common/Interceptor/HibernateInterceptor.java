package com.common.Interceptor;

import org.hibernate.EmptyInterceptor;
import org.springframework.stereotype.Service;

@Service
public class HibernateInterceptor extends EmptyInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String targetTableName;//目标母表名
	private String tempTableName;//操作子表名
	private String currentSql;
	
	public HibernateInterceptor(){
		
	}

	@Override
	public String onPrepareStatement(String sql) {
		if(currentSql!=null){
			sql = currentSql;
			return sql;
		}
		if(targetTableName==null||tempTableName==null){
			return sql;
		}
		sql = sql.replaceAll(targetTableName, tempTableName);
		return sql;
	}

	public void setTargetTableName(String targetTableName) {
		this.targetTableName = targetTableName;
	}

	public void setTempTableName(String tempTableName) {
		this.tempTableName = tempTableName;
	}

	public String getTargetTableName() {
		return targetTableName;
	}

	public String getTempTableName() {
		return tempTableName;
	}

	public String getCurrentSql() {
		return currentSql;
	}

	public void setCurrentSql(String currentSql) {
		this.currentSql = currentSql;
	}
	
}

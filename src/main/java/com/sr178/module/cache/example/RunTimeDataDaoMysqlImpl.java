package com.sr178.module.cache.example;


import org.springframework.beans.factory.annotation.Autowired;

import com.sr178.common.jdbc.Jdbc;
import com.sr178.common.jdbc.SqlParameter;


public class RunTimeDataDaoMysqlImpl {

	@Autowired
	private Jdbc jdbcUser;

	public final static String table = "runtime_data";

	public final static String columns = "*";

	public boolean add(RuntimeData runtimeData) {
		return this.jdbcUser.insert(runtimeData) > 0;
	}

	public RuntimeData get(String key) {

		String sql = "SELECT " + columns + " FROM " + table + " WHERE value_key = ? ";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(key);

		return this.jdbcUser.get(sql, RuntimeData.class, parameter);
	}

	public boolean inc(String key) {

		String sql = "UPDATE " + table + " SET value = value + 1 WHERE value_key = ? ";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(key);

		return this.jdbcUser.update(sql, parameter) > 0;
	}
	
	public boolean inc(String key,int value) {

		String sql = "UPDATE " + table + " SET value = value + ? WHERE value_key = ? ";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(key);
		parameter.setInt(value);
		return this.jdbcUser.update(sql, parameter) > 0;
	}

	public boolean set(String key, long value) {

		String sql = "UPDATE " + table + " SET value = ? WHERE value_key = ? ";
		SqlParameter parameter = new SqlParameter();
		parameter.setLong(value);
		parameter.setString(key);

		return this.jdbcUser.update(sql, parameter) > 0;
	}

}

package org.quartz.ui.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.quartz.ui.web.model.ScheduleInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class SchedulerDao {
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("unchecked")
	public List<ScheduleInfo> queryScheduleInfos() {
		String sql = "select * from QRTZ_SCHEDULER_STATE";
		return jdbcTemplate.query(sql, new Object[]{},new RowMapper() {
			
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				ScheduleInfo scheduleInfo = new ScheduleInfo();
				scheduleInfo.setSchedName(rs.getString("SCHED_NAME"));
				scheduleInfo.setInstanceName(rs.getString("INSTANCE_NAME"));
				scheduleInfo.setLastCheckinTime(rs.getLong("LAST_CHECKIN_TIME"));
				scheduleInfo.setCheckinInterval(rs.getLong("CHECKIN_INTERVAL"));
				return scheduleInfo;
			}
		});
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}

package org.quartz.ui.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.quartz.ui.web.model.JobInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JobDao {
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("unchecked")
	public List<JobInfo> queryJobInfos() {
		String sql = "select * from QRTZ_JOB_DETAILS";
		return jdbcTemplate.query(sql, new Object[]{},new RowMapper() {
			
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				JobInfo Info = new JobInfo();
				Info.setSchName(rs.getString("SCHED_NAME"));
				Info.setJobName(rs.getString("JOB_NAME"));
				Info.setJobClassName(rs.getString("JOB_CLASS_NAME"));
				Info.setJobGroup(rs.getString("JOB_GROUP"));
				Info.setJobDate(rs.getString("JOB_DATA"));
				//Info.setIsNocurrent(rs.getString("IS_NONCONCURRENT"));
				//Info.setIsDuarble(rs.getString("IS_DURABLE"));
			   // Info.setJobDisc(rs.getString("DESCRPTION"));
			    //Info.setIsUpdate(rs.getString("IS_UPDATE_DATA"));
			   // Info.setReustRe(rs.getString("REQUESTS_RECOVERY"));
				return Info;
			}
		});
	}
	@SuppressWarnings("unchecked")
	public List<JobInfo> queryJobInfosByName(String JOB_NAME) {
		String sql = "select * from QRTZ_JOB_DETAILS WHERE　JOB_NAME＝?";
		return jdbcTemplate.query(sql, new Object[]{JOB_NAME},new RowMapper() {
			
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				JobInfo Info = new JobInfo();
				Info.setSchName(rs.getString("SCHED_NAME"));
				Info.setJobName(rs.getString("JOB_NAME"));
				Info.setJobClassName(rs.getString("JOB_CLASS_NAME"));
				Info.setJobGroup(rs.getString("JOB_GROUP"));
				Info.setJobDate(rs.getString("JOB_DATA"));
				//Info.setIsNocurrent(rs.getString("IS_NONCONCURRENT"));
				//Info.setIsDuarble(rs.getString("IS_DURABLE"));
			   // Info.setJobDisc(rs.getString("DESCRPTION"));
			    //Info.setIsUpdate(rs.getString("IS_UPDATE_DATA"));
			   // Info.setReustRe(rs.getString("REQUESTS_RECOVERY"));
				return Info;
			}
		});
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}

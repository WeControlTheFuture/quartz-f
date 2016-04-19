package org.quartz.ui.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.quartz.ui.web.action.trigger.TriggerAction;
import org.quartz.ui.web.model.CronTriggerInfo;
import org.quartz.ui.web.model.JobInfo;
import org.quartz.ui.web.model.SimpleTriggerInfo;
import org.quartz.ui.web.model.TriggerHistory;
import org.quartz.ui.web.model.TriggerInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class TriggerDao {
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("unchecked")
	public List<TriggerInfo> queryTriggerInfos() {
		long nextFireTime = System.currentTimeMillis() + TriggerAction.ONEYEAR;
		String sql = "select * from QRTZ_TRIGGERS where NEXT_FIRE_TIME<" + nextFireTime;
		return jdbcTemplate.query(sql, new Object[] {}, new RowMapper() {

			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				TriggerInfo Info = new TriggerInfo();
				Info.setSchedName(rs.getString("SCHED_NAME"));
				Info.setTriggerName(rs.getString("TRIGGER_NAME"));
				Info.setTriggerGropuo(rs.getString("TRIGGER_GROUP"));
				Info.setJobName(rs.getString("JOB_NAME"));
				Info.setJobGroup(rs.getString("JOB_GROUP"));
				Info.setDes(rs.getString("DESCRIPTION"));
				Info.setTriggerType(rs.getString("TRIGGER_TYPE"));
				Info.setPreFireTime(rs.getLong("PREV_FIRE_TIME"));
				Info.setNextFireTime(rs.getLong("NEXT_FIRE_TIME"));
				Info.setStartTime(rs.getLong("START_TIME"));
				Info.setEndTime(rs.getLong("END_TIME"));
				Info.setTriggerState(rs.getString("TRIGGER_STATE"));
				return Info;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<TriggerInfo> queryTriggerInfosByjob(String jobName) {
		long nextFireTime = System.currentTimeMillis() + TriggerAction.ONEYEAR;
		String sql = "select * from QRTZ_TRIGGERS WHERE NEXT_FIRE_TIME<" + nextFireTime + " and JOB_NAME like '%" + jobName + "%'";
		return jdbcTemplate.query(sql, new RowMapper() {

			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				TriggerInfo Info = new TriggerInfo();
				Info.setSchedName(rs.getString("SCHED_NAME"));
				Info.setTriggerName(rs.getString("TRIGGER_NAME"));
				Info.setTriggerGropuo(rs.getString("TRIGGER_GROUP"));
				Info.setJobName(rs.getString("JOB_NAME"));
				Info.setJobGroup(rs.getString("JOB_GROUP"));
				Info.setDes(rs.getString("DESCRIPTION"));
				Info.setPreFireTime(rs.getLong("PREV_FIRE_TIME"));
				Info.setNextFireTime(rs.getLong("NEXT_FIRE_TIME"));
				Info.setStartTime(rs.getLong("START_TIME"));
				Info.setEndTime(rs.getLong("END_TIME"));
				Info.setTriggerType(rs.getString("TRIGGER_TYPE"));
				Info.setTriggerState(rs.getString("TRIGGER_STATE"));
				return Info;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<JobInfo> queryJobByTrigger(String JOB_NAME) {
		String sql = "select * from QRTZ_JOB_DETAILS  WHERE JOB_NAME=?";
		return jdbcTemplate.query(sql, new Object[] { JOB_NAME }, new RowMapper() {

			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				JobInfo Info = new JobInfo();
				Info.setSchName(rs.getString("SCHED_NAME"));
				Info.setJobName(rs.getString("JOB_NAME"));
				Info.setJobClassName(rs.getString("JOB_CLASS_NAME"));
				Info.setJobGroup(rs.getString("JOB_GROUP"));
				Info.setJobDate(rs.getString("JOB_DATA"));
				// Info.setIsNocurrent(rs.getString("IS_NONCONCURRENT"));
				// Info.setIsDuarble(rs.getString("IS_DURABLE"));
				// Info.setJobDisc(rs.getString("DESCRPTION"));
				// Info.setIsUpdate(rs.getString("IS_UPDATE_DATA"));
				// Info.setReustRe(rs.getString("REQUESTS_RECOVERY"));
				return Info;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<CronTriggerInfo> queryconTriggerByName(String TRIGGER_NAME) {
		String sql = "select * from QRTZ_CRON_TRIGGERS  WHERE TRIGGER_NAME=?";
		return jdbcTemplate.query(sql, new Object[] { TRIGGER_NAME }, new RowMapper() {

			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				CronTriggerInfo Info = new CronTriggerInfo();
				Info.setTriggerName(rs.getString("TRIGGER_NAME"));
				Info.setSchedName(rs.getString("SCHED_NAME"));
				Info.setCronExpression(rs.getString("CRON_EXPRESSION"));
				Info.setTimeZone(rs.getString("TIME_ZONE_ID"));
				Info.setTriggerGroup(rs.getString("TRIGGER_GROUP"));
				return Info;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<SimpleTriggerInfo> querysimTriggerByName(String TRIGGER_NAME) {
		String sql = "select * from QRTZ_SIMPLE_TRIGGERS  WHERE TRIGGER_NAME=?";
		return jdbcTemplate.query(sql, new Object[] { TRIGGER_NAME }, new RowMapper() {

			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				SimpleTriggerInfo Info = new SimpleTriggerInfo();
				Info.setTriggerName(rs.getString("TRIGGER_NAME"));
				Info.setSchedName(rs.getString("SCHED_NAME"));
				Info.setTriggerGroup(rs.getString("TRIGGER_GROUP"));
				return Info;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<TriggerHistory> queryTriggerHistorysByName(String jobName, String triggerName) {
		StringBuilder sql = new StringBuilder("select * from QRTZ_TRIGGERS_HISTORY  where TRIGGER_TYPE='SIMPLE' ");
		if (StringUtils.isNotEmpty(jobName))
			sql.append(" and JOB_NAME like '%" + jobName + "%'");
		if (StringUtils.isNotEmpty(triggerName))
			sql.append(" and TRIGGER_NAME like '%" + triggerName + "%'");
		return jdbcTemplate.query(sql.toString(), new Object[] {}, new RowMapper() {

			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				TriggerHistory Info = new TriggerHistory();
				Info.setShcedName(rs.getString("SCHED_NAME"));
				Info.setTriggerName(rs.getString("TRIGGER_NAME"));
				Info.setTriggerGroup(rs.getString("TRIGGER_GROUP"));
				Info.setJobName(rs.getString("JOB_NAME"));
				Info.setJobGroup(rs.getString("JOB_GROUP"));
				Info.setDes(rs.getString("DESCRIPTION"));
				Info.setTriggerType(rs.getString("TRIGGER_TYPE"));
				Info.setPreFireTime(rs.getLong("PREV_FIRE_TIME"));
				Info.setNextFireTime(rs.getLong("NEXT_FIRE_TIME"));
				Info.setStartTime(rs.getLong("START_TIME"));
				Info.setEndTime(rs.getLong("END_TIME"));
				Info.setTriggerState(rs.getString("TRIGGER_STATE"));
				return Info;
			}
		});
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] { "/applicationContext.xml" });
		TriggerDao dao = (TriggerDao) ctx.getBean("triggerDao");
		System.out.println(dao.queryTriggerInfos().toString());
	}

}

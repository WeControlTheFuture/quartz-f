package org.quartz.ui.web.action.job;

import java.util.List;

import org.quartz.ui.util.StringUtil;
import org.quartz.ui.web.action.schedule.ScheduleBase;
import org.quartz.ui.web.dao.JobDao;
import org.quartz.ui.web.dao.TriggerDao;
import org.quartz.ui.web.model.CronTriggerInfo;
import org.quartz.ui.web.model.JobInfo;
import org.quartz.ui.web.model.SimpleTriggerInfo;
import org.quartz.ui.web.model.TriggerHistory;
import org.quartz.ui.web.model.TriggerInfo;

public class ListJdbcTriggers extends ScheduleBase {
	protected List<JobInfo> jobInfos;
	TriggerInfo triggerInfo;
	protected List<TriggerInfo> triggerInfos;
	private JobDao jobDao;
	private JobInfo job;
	private TriggerDao triggerDao;
	private TriggerInfo trigger;
	public String jobName;
	public String triggerName;
	public CronTriggerInfo cronTrigger;
	public SimpleTriggerInfo simpleTrigger;
	public String triggerType;
	public List<TriggerHistory> triggerHistorys = null;
	public String jobHisName;
	public String triggerHisName;
	public String jobSearchName;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String viewHis() {
		triggerHistorys = triggerDao.queryTriggerHistorysByName(jobHisName, triggerHisName);
		return SUCCESS;

	}

	public String execute() {
		if (jobSearchName == null) {
			this.triggerInfos = triggerDao.queryTriggerInfos();
		} else {
			triggerInfos = triggerDao.queryTriggerInfosByjob(jobSearchName);
		}

		// System.out.println(triggerInfos.get(0).getTriggerType());
		return SUCCESS;

	}

	public String view() {
		// String jobname=trigger.getJobName();
		jobInfos = triggerDao.queryJobByTrigger(jobName);

		job = jobInfos.get(0);
		// 如果是cron类型的触发器，取出这个触发器的详情
		if (triggerType.equals("CRON")) {
			cronTrigger = triggerDao.queryconTriggerByName(triggerName).get(0);
		}
		// 如果是simple类型的触发器，取出这个触发器的详情
		if (triggerType.equals("SIMPLE")) {
			simpleTrigger = triggerDao.querysimTriggerByName(triggerName).get(0);
		}

		return SUCCESS;
	}

	// 判断历史搜索是否匹配
	private boolean matchesHis(String jobName, String triggerName) {
		if (StringUtil.isEmpty(jobHisName) && StringUtil.isEmpty(triggerHisName)) {
			return true;
		} else {
			return jobName.contains(jobHisName) && triggerName.contains(triggerHisName);
		}
	}

	public SimpleTriggerInfo getSimpleTrigger() {
		return simpleTrigger;
	}

	public void setSimpleTrigger(SimpleTriggerInfo simpleTrigger) {
		this.simpleTrigger = simpleTrigger;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public CronTriggerInfo getCronTrigger() {
		return cronTrigger;
	}

	public void setCronTrigger(CronTriggerInfo cronTrigger) {
		this.cronTrigger = cronTrigger;
	}

	public String getTriggerType() {
		return triggerType;
	}

	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}

	public List<JobInfo> getJobInfos() {
		return jobInfos;
	}

	public void setJobInfos(List<JobInfo> jobInfos) {
		this.jobInfos = jobInfos;
	}

	public TriggerInfo getTriggerInfo() {
		return triggerInfo;
	}

	public void setTriggerInfo(TriggerInfo triggerInfo) {
		this.triggerInfo = triggerInfo;
	}

	public List<TriggerInfo> getTriggerInfos() {
		return triggerInfos;
	}

	public List<TriggerHistory> getTriggerHistorys() {
		return triggerHistorys;
	}

	public void setTriggerHistorys(List<TriggerHistory> triggerHistorys) {
		this.triggerHistorys = triggerHistorys;
	}

	public void setTriggerInfos(List<TriggerInfo> triggerInfos) {
		this.triggerInfos = triggerInfos;
	}

	public JobDao getJobDao() {
		return jobDao;
	}

	public void setJobDao(JobDao jobDao) {
		this.jobDao = jobDao;
	}

	public JobInfo getJob() {
		return job;
	}

	public void setJob(JobInfo job) {
		this.job = job;
	}

	public TriggerDao getTriggerDao() {
		return triggerDao;
	}

	public TriggerInfo getTrigger() {
		return trigger;
	}

	public void setTrigger(TriggerInfo trigger) {
		this.trigger = trigger;
	}

	public String getJobHisName() {
		return jobHisName;
	}

	public void setJobHisName(String jobHisName) {
		this.jobHisName = jobHisName;
	}

	public String getTriggerHisName() {
		return triggerHisName;
	}

	public void setTriggerHisName(String triggerHisName) {
		this.triggerHisName = triggerHisName;
	}

	public void setTriggerDao(TriggerDao triggerDao) {
		this.triggerDao = triggerDao;
	}

	public String getJobSearchName() {
		return jobSearchName;
	}

	public void setJobSearchName(String jobSearchName) {
		this.jobSearchName = jobSearchName;
	}

}

package org.quartz.ui.web.model;

public class TriggerInfo {
	public String schedName;
    public String triggerName;
    public String triggerGropuo;
    public String jobName;
    public String jobGroup;
    public String des;
    public Long nextFireTime;
    public Long preFireTime;
    public String triggerState;

    public String getSchedName() {
		return schedName;
	}
	public void setSchedName(String schedName) {
		this.schedName = schedName;
	}
	public String getTriggerName() {
		return triggerName;
	}
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	public String getTriggerGropuo() {
		return triggerGropuo;
	}
	public void setTriggerGropuo(String triggerGropuo) {
		this.triggerGropuo = triggerGropuo;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Long getNextFireTime() {
		return nextFireTime;
	}
	public void setNextFireTime(Long nextFireTime) {
		this.nextFireTime = nextFireTime;
	}
	public Long getPreFireTime() {
		return preFireTime;
	}
	public void setPreFireTime(Long preFireTime) {
		this.preFireTime = preFireTime;
	}
	public String getTriggerState() {
		return triggerState;
	}
	public void setTriggerState(String triggerState) {
		this.triggerState = triggerState;
	}
	public String getTriggerType() {
		return triggerType;
	}
	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public String getPrioRity() {
		return prioRity;
	}
	public void setPrioRity(String prioRity) {
		this.prioRity = prioRity;
	}
	public String getCalendarName() {
		return calendarName;
	}
	public void setCalendarName(String calendarName) {
		this.calendarName = calendarName;
	}
	public String getJobData() {
		return jobData;
	}
	public void setJobData(String jobData) {
		this.jobData = jobData;
	}
	public String triggerType;
    public Long startTime;
    public Long endTime;
    public String prioRity;
    public String calendarName;
    public String jobData;
    
    
}

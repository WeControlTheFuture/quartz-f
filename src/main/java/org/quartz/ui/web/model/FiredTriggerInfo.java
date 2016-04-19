package org.quartz.ui.web.model;

public class FiredTriggerInfo {
	public String schedName;
	public String triggerName;
	public String triggerGroup;
	public String entryId;
	public String instanceName;
	public String firedTime;
	public String schedTime;
	public String state;
	public String jobName;
	public String jobGroup;
	public String isNoncorrent;
	public String requestsRecovery;
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
	public String getTriggerGroup() {
		return triggerGroup;
	}
	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}
	public String getEntryId() {
		return entryId;
	}
	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}
	public String getInstanceName() {
		return instanceName;
	}
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}
	public String getFiredTime() {
		return firedTime;
	}
	public void setFiredTime(String firedTime) {
		this.firedTime = firedTime;
	}
	public String getSchedTime() {
		return schedTime;
	}
	public void setSchedTime(String schedTime) {
		this.schedTime = schedTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public String getIsNoncorrent() {
		return isNoncorrent;
	}
	public void setIsNoncorrent(String isNoncorrent) {
		this.isNoncorrent = isNoncorrent;
	}
	public String getRequestsRecovery() {
		return requestsRecovery;
	}
	public void setRequestsRecovery(String requestsRecovery) {
		this.requestsRecovery = requestsRecovery;
	}
	
	
	
	

}

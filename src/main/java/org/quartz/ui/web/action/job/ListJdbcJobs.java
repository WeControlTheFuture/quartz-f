package org.quartz.ui.web.action.job;

import java.util.List;

import org.quartz.ui.web.action.schedule.ScheduleBase;
import org.quartz.ui.web.dao.JobDao;
import org.quartz.ui.web.dao.TriggerDao;
import org.quartz.ui.web.model.JobInfo;
import org.quartz.ui.web.model.TriggerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ListJdbcJobs extends ScheduleBase{
	protected List<JobInfo> jobInfos;
	TriggerInfo triggerInfo;
	protected List<TriggerInfo> trirggerInfos;
	private JobDao jobDao;
	private JobInfo job ;
	private TriggerDao triggerDao;
	public String jobSearchName;
	public String execute() {
		/*ApplicationContext ctx = new ClassPathXmlApplicationContext(
				new String[] { "/applicationContext.xml" });
		jobDao=(JobDao)ctx.getBean("jobDao");*/
		if(jobSearchName==null){
		this.jobInfos= jobDao.queryJobInfos();}
		else{
		this.jobInfos=jobDao.queryJobInfosByName(jobSearchName);}
		
		return SUCCESS;

	}
	public String view(){
	
		
		//triggerInfo=triggerDao.queryTriggerInfosByjob(jobInfos.get(0)).get(0);
		
		return SUCCESS;
		
	}
	public TriggerDao getTriggerDao() {
		return triggerDao;
	}
	public void setTriggerDao(TriggerDao triggerDao) {
		this.triggerDao = triggerDao;
	}
	public List<JobInfo> getJobInfos() {
		return jobInfos;
	}
	public void setJobInfos(List<JobInfo> jobInfos) {
		this.jobInfos = jobInfos;
	}
	public JobDao getJobDao() {
		return jobDao;
	}
	public void setJobDao(JobDao jobDao) {
		this.jobDao = jobDao;
	}
	public TriggerInfo getTriggerInfo() {
		return triggerInfo;
	}
	public void setTriggerInfo(TriggerInfo triggerInfo) {
		this.triggerInfo = triggerInfo;
	}
	public static void main(String[] args) {
		JobDao jobdao=new JobDao();
		System.out.println(jobdao.queryJobInfos());
		
	}

}

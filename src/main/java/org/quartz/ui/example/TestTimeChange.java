package org.quartz.ui.example;
import static org.quartz.JobBuilder.newJob;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
public class TestTimeChange {
	public static String startTime = "2014-01-13";
	public static String changesTime = "2018-01-13";
	public static String stopTime = "2019-05-13";
	public static String cronExpression = "* * * * * ?";
	private int misFireInstruction;
	public static  String jobName = "test";
	public static  String jobGroup = "group";
	public static String triggerName = "contrigger";
	public static String triggerGroup = "triggergroup";
	public String description;
	private JobDetail detail = new JobDetailImpl();
	
	static DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	private String type = new String();
	static CronTrigger trigger = null;
	Trigger trigger1=null;
	public static void main(String[] args) throws SchedulerException, ParseException, InterruptedException {
		
		SchedulerFactory sf=new StdSchedulerFactory();;
        Scheduler scheduler=sf.getScheduler();
        JobDetail detail = newJob(NoOpJob.class).withIdentity(jobName, jobGroup)
				.build();
        trigger = new CronTriggerImpl(getTriggerName(), getTriggerGroup(),
				getJobName(), getJobGroup(), getCronExpression());

        ((CronTriggerImpl) trigger).setStartTime(fmt.parse(startTime));
		((CronTriggerImpl) trigger).setEndTime(fmt.parse(stopTime));
		((CronTriggerImpl) trigger).setCronExpression(cronExpression);
		scheduler.scheduleJob(detail, trigger);
		scheduler.start();
		System.out.println(trigger.getStartTime());
		Thread.sleep(2000);
		scheduler.pauseAll();	
		scheduler.unscheduleJob(trigger.getKey());
		scheduler.deleteJob(detail.getKey());	
		System.out.println(scheduler.getCurrentlyExecutingJobs());
		((CronTriggerImpl) trigger).setStartTime(fmt.parse(changesTime));
		scheduler.scheduleJob(detail, trigger);
		System.out.println(trigger.getStartTime());
		scheduler.start();
		Thread.sleep(2000);
		
		
		
		
		scheduler.shutdown(false);
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getChangesTime() {
		return changesTime;
	}
	public void setChangesTime(String changesTime) {
		this.changesTime = changesTime;
	}
	public String getStopTime() {
		return stopTime;
	}
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}
	public static String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public int getMisFireInstruction() {
		return misFireInstruction;
	}
	public void setMisFireInstruction(int misFireInstruction) {
		this.misFireInstruction = misFireInstruction;
	}
	public static String getJobName() {
		return jobName;
	}
	public static void setJobName(String jobName) {
		TestTimeChange.jobName = jobName;
	}
	public static String getJobGroup() {
		return jobGroup;
	}
	public static void setJobGroup(String jobGroup) {
		TestTimeChange.jobGroup = jobGroup;
	}
	public static String getTriggerName() {
		return triggerName;
	}
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	public static String getTriggerGroup() {
		return triggerGroup;
	}
	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public JobDetail getDetail() {
		return detail;
	}
	public void setDetail(JobDetail detail) {
		this.detail = detail;
	}
	public DateFormat getFmt() {
		return fmt;
	}
	public void setFmt(DateFormat fmt) {
		this.fmt = fmt;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public static CronTrigger getTrigger() {
		return trigger;
	}
	public static void setTrigger(CronTrigger trigger) {
		TestTimeChange.trigger = trigger;
	}
	public Trigger getTrigger1() {
		return trigger1;
	}
	public void setTrigger1(Trigger trigger1) {
		this.trigger1 = trigger1;
	}
	
}

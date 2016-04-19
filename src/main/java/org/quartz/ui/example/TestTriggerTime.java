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
import org.quartz.impl.triggers.AbstractTrigger;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.ui.web.action.schedule.ScheduleBase;


public class TestTriggerTime extends ScheduleBase {
	public String startTime = "2017-01-13";
	public String changesTime = "2018-01-13";
	public String stopTime = "2019-05-13";
	public String cronExpression = "* * * * * ?";
	private int misFireInstruction;
	public  String jobName = "test";
	public  String jobGroup = "group";
	public String triggerName = "contrigger";
	public String triggerGroup = "triggergroup";
	public String description;
	private JobDetail detail = new JobDetailImpl();
	SchedulerFactory sf;
    Scheduler scheduler;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	private String type = new String();
	CronTrigger trigger = null;
	Trigger trigger1=null;
	public static void main(String[] args) {

		TestTriggerTime test = new TestTriggerTime();

		try {
			test.start();
			test.executeTrigger();
			test.getScheduler().start();
			Thread.sleep(3000);
			/*
			test.getScheduler().pauseAll();
			test.getScheduler().deleteJob(test.getDetail().getKey());
       		test.ChangeTriggerTime();
       		test.getScheduler().start();
		   // test.getScheduler().scheduleJob(test.getTrigger());
			//Thread.sleep(3000);
			test.getScheduler().shutdown(false);

			SchedulerMetaData metaData = test.scheduler.getMetaData();*/

		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}

	public void start() throws SchedulerException {

		sf = new StdSchedulerFactory();
		scheduler = sf.getScheduler();
		try {
			Scheduler sched = sf.getScheduler();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		this.detail = newJob(NoOpJob.class).withIdentity(jobName, jobGroup)
				.build();
		// this.detail = ScheduleBase.getCurrentScheduler().getJobDetail(new
		// JobKey(jobName, jobGroup));

	}

	public void executeTrigger() throws ParseException {

		boolean startTimeHasValue = ((startTime != null) && (startTime.length() > 0));
		boolean stopTimeHasValue = ((stopTime != null) && (stopTime.length() > 0));

		// all weird constraints are handled by the validate method

		if (cronExpression.length() > 2) {
			trigger = new CronTriggerImpl(getTriggerName(), getTriggerGroup(),
					getJobName(), getJobGroup(), getCronExpression());

		}

		// test for parse expression erro error.cronExpression.parseError

		((AbstractTrigger<CronTrigger>) trigger).setDescription(this
				.getDescription());

		if (startTimeHasValue) {
			((CronTriggerImpl) trigger).setStartTime(fmt.parse(startTime));
		}

		if (stopTimeHasValue) {
			((CronTriggerImpl) trigger).setEndTime(fmt.parse(stopTime));
		}

		// Trigger trigger1 = newTrigger().withIdentity("trigger1",
		// "group1").startAt(runTime).build();

		try {
			scheduler.scheduleJob(detail, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

	}

	public void ChangeTriggerTime() throws ParseException, SchedulerException {
		trigger1 = new CronTriggerImpl("1","2",getJobName(), getJobGroup(), getCronExpression());
		
		//((CronTriggerImpl) trigger1).setKey(new TriggerKey());
		((CronTriggerImpl) trigger1).setStartTime(fmt.parse(changesTime));
		((CronTriggerImpl) trigger1).setEndTime(fmt.parse(stopTime));
		((CronTriggerImpl) trigger1).setCronExpression(cronExpression);
		scheduler.scheduleJob(detail, trigger1);
		System.out.println(trigger1.getStartTime()+""+trigger1.getEndTime());
		System.out.print(true);
		// scheduler.scheduleJob(detail, trigger);
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStopTime() {
		return stopTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

	public String getCronExpression() {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getChangesTime() {
		return changesTime;
	}

	public void setChangesTime(String changesTime) {
		this.changesTime = changesTime;
	}

	public JobDetail getDetail() {
		return detail;
	}

	public void setDetail(JobDetail detail) {
		this.detail = detail;
	}

	public SchedulerFactory getSf() {
		return sf;
	}

	public void setSf(SchedulerFactory sf) {
		this.sf = sf;
	}

	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public DateFormat getFmt() {
		return fmt;
	}

	public void setFmt(DateFormat fmt) {
		this.fmt = fmt;
	}

	public CronTrigger getTrigger() {
		return trigger;
	}

	public void setTrigger(CronTrigger trigger) {
		this.trigger = trigger;
	}

}

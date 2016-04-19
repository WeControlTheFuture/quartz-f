package org.quartz.ui.web.action.schedule;

/**
 * @since Tue Feb 17 22:27:33 EST 2004
 * @version Revision:
 * @author Matthew Payne
 *  TODO
 */

import java.text.ParseException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.AbstractTrigger;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.ui.CronExpressionHelper;
import org.quartz.ui.UICronTrigger;

public class UICronTriggerAction extends ScheduleBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1451830362977979819L;

	protected transient static final Log log = LogFactory.getLog(UICronTriggerAction.class);
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork.Action#execute()
	 */

	// the misfireinstruction
	private int misFireInstruction;
	private JobDetail jobDetail = new JobDetailImpl();
	private String type = new String();
	// UICronTrigger trigger = new UICronTrigger();
	CronExpressionHelper trigger = new CronExpressionHelper();

	Integer[] daysOfMonth = new Integer[31];

	private String cronExpression = "";
	CronExpressionHelper model = new CronExpressionHelper();

	@Override
	public String input() throws SchedulerException {

		UICronTrigger cronTrigger = new UICronTrigger();
		log.debug("jobname is" + super.jobName);
		this.jobDetail = ScheduleBase.getCurrentScheduler().getJobDetail(new JobKey(jobName, jobGroup));

		return INPUT;
	}

	@Override
	public String execute() throws ParseException {

		boolean startTimeHasValue = ((startTime != null) && (startTime.length() > 0));
		boolean stopTimeHasValue = ((stopTime != null) && (stopTime.length() > 0));

		CronTrigger cronTrigger = new CronTriggerImpl(getTriggerName(), getTriggerGroup());
		// all weird constraints are handled by the validate method

		((AbstractTrigger<CronTrigger>) cronTrigger).setJobGroup(this.getJobGroup());
		((AbstractTrigger<CronTrigger>) cronTrigger).setJobName(this.getJobName());
		((JobDetailImpl) cronTrigger).setDescription(this.getDescription());

		// todo look at volativily later
		//((Object) cronTrigger).setVolatility(false);
		//LOG.info(jobDetail.getFullName() + " scheduled with" + trigger.getExpressionSummary());

		try {

			ScheduleBase.getCurrentScheduler().scheduleJob(cronTrigger);

		} catch (SchedulerException e) {
			this.addActionError("SchedulerException, Could not schedule the trigger " + trigger + " "
					+ e.getLocalizedMessage());
			return ERROR;
		} catch (UnsupportedOperationException ue) {

			LOG.error("UnsupportedOperation in CronSchedule", ue);
			this.addActionError("Could not schedule the trigger " + trigger + " " + ue.getLocalizedMessage());
			return ERROR;
		}

		return SUCCESS;
	}

	/**
	 * returns the value of the misFireInstruction
	 * 
	 * @return the misFireInstruction
	 */
	public int getMisFireInstruction() {
		return misFireInstruction;
	}

	/**
	 * sets the value of the misFireInstruction
	 * 
	 * @param misFireInstruction
	 */
	public void setMisFireInstruction(int misFireInstruction) {
		this.misFireInstruction = misFireInstruction;
	}

	/**
	 * returns the value of the type
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * sets the value of the type
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return Returns the cronExpression.
	 */
	public String getCronExpression() {
		return cronExpression;
	}

	/**
	 * @param cronExpression
	 *            The cronExpression to set.
	 */
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	/**
	 * @return Returns the jobDetail.
	 */
	public JobDetail getJobDetail() {
		return jobDetail;
	}

	/**
	 * @param jobDetail
	 *            The jobDetail to set.
	 */
	public void setJobDetail(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
	}

	/**
	 * @return Returns the daysOfMonth.
	 */
	public Integer[] getDaysOfMonth() {
		return daysOfMonth;
	}

	/**
	 * @param daysOfMonth
	 *            The daysOfMonth to set.
	 */
	public void setDaysOfMonth(Integer[] daysOfMonth) {
		this.daysOfMonth = daysOfMonth;
	}

	/**
	 * @return Returns the trigger.
	 */
	public CronExpressionHelper getTrigger() {
		return trigger;
	}

	/**
	 * @param trigger
	 *            The trigger to set.
	 */
	public void setTrigger(CronExpressionHelper trigger) {
		this.trigger = trigger;
	}

}

package org.quartz.ui.web.action.schedule;

import org.quartz.SchedulerException;
import org.quartz.TriggerKey;

public class UnSchedule extends ScheduleBase {

	/**
     * 
     */
    private static final long serialVersionUID = -6379871384500763364L;
	String jobName="";
	String jobGroup="";
	String triggerGroup = "";
			
	@Override
	public String execute()  {

	  	 	try {
				getCurrentScheduler().unscheduleJob(new TriggerKey(triggerName, triggerGroup));
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return SUCCESS;

		}


	/**
	 * @return
	 */
	@Override
	public String getJobName() {
		return jobName;
	}

	/**
	 * @param string
	 */
	@Override
	public void setJobName(String string) {
		jobName = string;
	}

	/**
	 * @param string
	 */
	@Override
	public void setTriggerGroup(String string) {
		triggerGroup = string;
	}

	/**
	 * @param string
	 */
	@Override
	public void setTriggerName(String string) {
		triggerName = string;
	}

	/**
	 * @return
	 */
	@Override
	public String getJobGroup() {
		return jobGroup;
	}

	/**
	 * @param string
	 */
	@Override
	public void setJobGroup(String string) {
		jobGroup = string;
	}

}

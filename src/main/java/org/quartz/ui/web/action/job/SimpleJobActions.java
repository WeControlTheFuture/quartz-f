package org.quartz.ui.web.action.job;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import org.quartz.JobKey;
import org.apache.commons.collections.SetUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.components.Set;
import org.quartz.JobDetail;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdScheduler;
import org.quartz.ui.util.FormatUtil;
import org.quartz.ui.web.TriggerHelperl;
import org.quartz.ui.web.action.schedule.ScheduleBase;
import org.quartz.ui.web.action.base.BaseAction;
import org.quartz.ui.web.form.JobDetailForm;
import org.quartz.ui.web.form.ListenerDTO;
import org.quartz.ui.web.form.TriggerForm;
import org.quartz.utils.Key;

import com.opensymphony.xwork2.ActionSupport;

public class SimpleJobActions extends BaseAction {
	private java.util.Set jobListeners = SetUtils.orderedSet(new HashSet());
	/**
     * 
     */
	private static final long serialVersionUID = 4630381325813014310L;

	protected transient static final Log log = LogFactory.getLog(ActionSupport.class);

	String jobName = "";

	String jobGroup = "";

	JobDetail jobDetail = new JobDetailImpl();

	JobDetailForm form = new JobDetailForm();

	private List<TriggerForm> jobTriggers = new ArrayList();

	public String runNow() {

		Scheduler scheduler = ScheduleBase.getCurrentScheduler();

		try {
			scheduler.triggerJob(new JobKey(jobName, jobGroup));
		} catch (SchedulerException e) {
			log.error("error executing job", e);
			return ERROR;
		}
		return SUCCESS;
	}

	public String delete() {
		Scheduler scheduler = ScheduleBase.getCurrentScheduler();
		try {
			scheduler.deleteJob(new JobKey(jobName, jobGroup));
		} catch (SchedulerException e) {
			log.warn("error deleting job", e);
			return ERROR;
		}
		return SUCCESS;
	}

	public String view() throws Exception {

		Scheduler scheduler = ScheduleBase.getCurrentScheduler();
		try {
			JobKey key = jobDetail.getKey();
			if (key == null) {
				key = new JobKey(jobName, jobGroup);
			}
			jobDetail = scheduler.getJobDetail(new JobKey(jobName, jobGroup));
		} catch (SchedulerException e) {
			// error.job.notFound
			throw new Exception("When reading the jobs", e);
		}
		if (jobDetail != null)
			populateForm(jobDetail, form, scheduler);
		return SUCCESS;
	}

	private void populateForm(JobDetail jobDetail, JobDetailForm form, Scheduler scheduler) throws ServletException, SchedulerException {

		if (jobDetail.getKey() != null) {

			Trigger[] triggers = TriggerHelperl.getTriggersFromJob(scheduler, jobDetail.getKey().getName(), jobDetail.getKey().getGroup());

			for (Trigger trigger : triggers) {
				TriggerForm tForm = new TriggerForm();
				tForm.setDescription(trigger.getDescription());
				tForm.setJobGroup(trigger.getJobKey().getGroup());
				tForm.setJobName(trigger.getKey().getName());
				tForm.setMisFireInstruction(trigger.getMisfireInstruction());
				tForm.setStartTime(FormatUtil.getDateAsString(trigger.getStartTime()));
				tForm.setStopTime(FormatUtil.getDateAsString(trigger.getEndTime()));
				tForm.setTriggerGroup(trigger.getKey().getGroup());
				tForm.setTriggerName(trigger.getKey().getName());
				tForm.setNextFireTime(FormatUtil.getDateAsString(trigger.getNextFireTime()));
				tForm.setPreviousFireTime(FormatUtil.getDateAsString(trigger.getPreviousFireTime()));
				tForm.setType(TriggerHelperl.getTriggerType(trigger));
				this.jobTriggers.add(tForm);
			}

			try {
				String[] jobListenerNames = getJobListenerNames(jobDetail);
				List<JobListener> list = scheduler.getListenerManager().getJobListeners();
				for (JobListener jobListener : list) {
					for (String element : jobListenerNames) {
						if (jobListener.getName().equals(element)) {
							ListenerDTO listenerForm = new ListenerDTO();
							listenerForm.setName(jobListener.getName());
							listenerForm.setListenerClass(jobListener.getClass().getName());
							form.getJobListeners().add(listenerForm);
						}
					}
				}
			} catch (SchedulerException e) {
			}
		}
	}

	public String[] getJobListenerNames(JobDetail jobDetail) {
		return (String[]) jobListeners.toArray(new String[jobListeners.size()]);
	}

	/**
	 * @return Returns the jobDetail.
	 * 
	 */
	public JobDetail getJobDetail() {
		return jobDetail;
	}

	/**
	 * @param jobDetail The jobDetail to set.
	 */
	public void setJobDetail(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
	}

	/**
	 * @return Returns the jobGroup.
	 */
	public String getJobGroup() {
		return jobGroup;
	}

	/**
	 * @param jobGroup The jobGroup to set.
	 */
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	/**
	 * @return Returns the jobName.
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * @param jobName The jobName to set.
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * @return Returns the jobTriggers.
	 */
	public List getJobTriggers() {
		return jobTriggers;
	}

	/**
	 * @param jobTriggers The jobTriggers to set.
	 */
	public void setJobTriggers(List jobTriggers) {
		this.jobTriggers = jobTriggers;
	}
}

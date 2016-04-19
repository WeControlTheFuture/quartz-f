package org.quartz.ui.web.action.job;

import java.util.Iterator;
import java.util.Set;

import org.quartz.JobDataMap;
import org.quartz.SchedulerException;
import org.quartz.impl.JobDetailImpl;
import org.quartz.ui.web.action.base.BaseAction;
import org.quartz.ui.web.action.schedule.ScheduleBase;
import org.quartz.ui.web.model.JobDefinition;
import org.quartz.ui.web.model.JobParameter;

public class CreateDefJob extends CreateJob {

	private static final long serialVersionUID = 2666588323639172008L;

	JobDefinition jobDefinition = new JobDefinition();

	String definitionName = "";

	@Override
	public String start() {
//		((JobDetailImpl) jobDetail).setJobDataMap(new JobDataMap());
//
//		if (definitionName.length() > 0) {
//			jobDefinition = BaseAction.getDefinitionManager().getDefinition(definitionName);
//		}
//
//		if (jobDefinition != null) {
//			return SUCCESS;
//		} else {
//
//			this.addActionError("error.jobdefinition.missing");
//			return INPUT;
//		}
		return SUCCESS;
	}

	@Override
	public String execute() {

		if (definitionName.length() > 0) {
			jobDefinition = BaseAction.getDefinitionManager().getDefinition(definitionName);
		}

		Class jobClass = null;
		try {
			jobClass = Class.forName(className);
			((JobDetailImpl) jobDetail).setJobClass(jobClass);

			for (int i = 0; i < parameterNames.length; i++) {
				if (parameterNames[i].trim().length() > 0 && parameterValues[i].trim().length() > 0) {
					jobDetail.getJobDataMap().put(parameterNames[i].trim(), parameterValues[i].trim());
				}

			}

			if (this.validateJobData()) {
				boolean replace = true;
				ScheduleBase.getCurrentScheduler().addJob(jobDetail, replace);
			} else {

				return ERROR;

			}

		} catch (ClassNotFoundException e) {
			this.addFieldError("className", "error " + className + " class is not found");
			return ERROR;
		} catch (SchedulerException e) {
			this.addActionError(e.getMessage());
			return ERROR;
		}

		jobName = ((JobDetailImpl) jobDetail).getName();
		jobGroup = ((JobDetailImpl) jobDetail).getGroup();
		return SUCCESS;
	}

	private boolean validateJobData() {

		Iterator<JobParameter> itr = jobDefinition.getParameters().iterator();
		Set keys = this.getJobDetail().getJobDataMap().keySet();

		while (itr.hasNext()) {
			JobParameter param = itr.next();

			if (param.isRequired() && !(keys.contains(param.getName()))) {
				this.addActionError("missing.parameter" + param.getName());
				return false;
			}

		}

		return true;

	}

	/**
	 * @return Returns the jobDefinition.
	 */
	public JobDefinition getJobDefinition() {
		return jobDefinition;
	}

	/**
	 * @param jobDefinition The jobDefinition to set.
	 */
	public void setJobDefinition(JobDefinition jobDefinition) {
		this.jobDefinition = jobDefinition;
	}

	/**
	 * @return Returns the definitionName.
	 */
	public String getDefinitionName() {
		return definitionName;
	}

	/**
	 * @param definitionName The definitionName to set.
	 */
	public void setDefinitionName(String definitionName) {
		this.definitionName = definitionName;
	}
}

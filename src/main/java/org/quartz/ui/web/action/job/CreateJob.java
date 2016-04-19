package org.quartz.ui.web.action.job;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.impl.JobDetailImpl;
import org.quartz.ui.util.FormatUtil;
import org.quartz.ui.util.JaxbUtil;
import org.quartz.ui.web.action.base.BaseAction;
import org.quartz.ui.web.dao.ZkConfigDao;
import org.quartz.ui.web.model.JobSchedulingData;
import org.quartz.ui.web.model.JobSchedulingData.Schedule.JobDataMap;
import org.quartz.ui.web.model.JobSchedulingData.Schedule.JobDataMap.Entry;

public class CreateJob extends BaseAction {
	private static final long serialVersionUID = 8783145977504063591L;
	private static final Log log = LogFactory.getLog(CreateJob.class);
	JobDetail jobDetail = new JobDetailImpl();
	JobSchedulingData jobSchedulingData = new JobSchedulingData();

	String className = "";

	boolean recoverable = true;

	String parameterNames[] = {};

	String parameterValues[] = {};

	Map parameters = new HashMap<String, String>();
	String jobName = "";
	String jobGroup = "";
	private ZkConfigDao zkConfigDao;

	@Override
	public void validate() {
		// 验证job类型是否存在

		// if ((className != null) && (className.length() > 0)) {
		// try {
		// Class.forName(className);
		// } catch (ClassNotFoundException e) {
		// this.addFieldError("className", "error " + className
		// + " class is not found");
		// }
		// }

	}

	public String start() {
		System.out.println("name===========" + jobSchedulingData.getSchedule().getJob().getName());
		// Scheduler scheduler = ScheduleBase.getCurrentScheduler();
		//
		// ((JobDetailImpl) jobDetail).setJobDataMap(new JobDataMap());
		return SUCCESS;
	}

	public String edit() throws SchedulerException {
		try {
			if (jobName.length() > 0) {
				String configContent = zkConfigDao.getConfig(jobName);
				if (StringUtils.isEmpty(configContent))
					return ERROR;
				jobSchedulingData = JaxbUtil.unmarshal(configContent, JobSchedulingData.class);
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			log.error("error edit job", e);
			this.addFieldError("zookeeper error ", "error edit job to zookeeper,message is" + e.getMessage());
			return ERROR;
		}

	}

	@Override
	public String execute() {
		JobDataMap jobDataMap = jobSchedulingData.getSchedule().getJob().getJobDataMap();
		if (jobDataMap != null) {
			List<Entry> entry = jobSchedulingData.getSchedule().getJob().getJobDataMap().getEntry();
			if (CollectionUtils.isNotEmpty(entry))
				FormatUtil.removeNull(entry);
		}
		try {
			if (zkConfigDao.isConfigExist(jobSchedulingData.getSchedule().getJob().getName())) {
				String content = zkConfigDao.getConfig(jobSchedulingData.getSchedule().getJob().getName());
				JobSchedulingData tempJSD = JaxbUtil.unmarshal(content, JobSchedulingData.class);
				tempJSD.getSchedule().setJob(jobSchedulingData.getSchedule().getJob());
				zkConfigDao.modifyConfig(tempJSD.getSchedule().getJob().getName(), JaxbUtil.marshal(tempJSD, JobSchedulingData.class));
			} else
				zkConfigDao.addNewConfig(jobSchedulingData.getSchedule().getJob().getName(), JaxbUtil.marshal(jobSchedulingData, JobSchedulingData.class));
		} catch (Exception e) {
			log.error("error save job", e);
			this.addFieldError("zookeeper error ", "error save job to zookeeper,message is" + e.getMessage());
			return ERROR;
		}
		return "list";
	}

	public String delete() {
		try {
			zkConfigDao.deleteConfig(jobName);
		} catch (Exception e) {
			log.error("error delete job", e);
			this.addFieldError("zookeeper error ", "error delete job to zookeeper,message is" + e.getMessage());
			return ERROR;
		}
		return "list";
	}

	/**
	 * @return
	 */
	public JobDetail getJobDetail() {
		return jobDetail;
	}

	/**
	 * @param detail
	 */
	public void setJobDetail(JobDetail detail) {
		jobDetail = detail;
	}

	/**
	 * @return
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param string
	 */
	public void setClassName(String string) {
		className = string.trim();
	}

	/**
	 * @return
	 */
	public boolean isRecoverable() {
		return recoverable;
	}

	/**
	 * @param map
	 */
	public void setParameter(int i, String p) {
		System.out.println("param is +" + p);

		// params[i] = p;
	}

	public Object getParameters(String key) {
		return parameters.get(key);
	}

	public void setParameters(String key, String value) {
		parameters.put(key, value);
	}

	/**
	 * @param b
	 */
	public void setRecoverable(boolean b) {
		recoverable = b;
	}

	public String getParameterName(int i) {
		if (i >= parameterNames.length) {
			return "";
		} else {
			return parameterNames[i];
		}
	}

	/**
	 * @return
	 */
	public String[] getParameterNames() {
		return parameterNames;
	}

	/**
	 * @return
	 */
	public String[] getParameterValues() {
		return parameterValues;
	}

	/**
	 * @param strings
	 */
	public void setParameterNames(String[] strings) {
		parameterNames = strings;
	}

	/**
	 * @param strings
	 */
	public void setParameterValues(String[] strings) {
		parameterValues = strings;
	}

	/**
	 * @param string
	 */
	public void setJobGroup(String string) {
		jobGroup = string;
	}

	/**
	 * @param string
	 */
	public void setJobName(String string) {
		jobName = string;
	}

	public JobSchedulingData getJobSchedulingData() {
		return jobSchedulingData;
	}

	public void setZkConfigDao(ZkConfigDao zkConfigDao) {
		this.zkConfigDao = zkConfigDao;
	}
}
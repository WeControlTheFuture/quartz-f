package org.quartz.ui.web.action.schedule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.ui.util.StringUtil;

public class ListJobs extends ScheduleBase {

	/**
     * 
     */
	private static final long serialVersionUID = 1433908530815520652L;
	private List<JobDetail> jobList;
	protected HashMap jobsByGroup = new HashMap(25);
	String searchName;
	protected final Object triggerLock = new Object();

	/**
	 * Returns the jobs.
	 * 
	 * @return ArrayList
	 */
	public List<JobDetail> getJobs() {
		return jobList;
	}

	@Override
	public String execute() throws Exception {

		Scheduler scheduler = ScheduleBase.getCurrentScheduler();
		jobList = new ArrayList<JobDetail>();

		try {

			List<String> jobGroups = scheduler.getJobGroupNames();
			List<String> addedJobs = new ArrayList<String>(jobGroups.size());
			//
			// have had some problems multiple jobs showing
			for (String groupName : jobGroups) {

				Set<JobKey> jobs = scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName));

				for (JobKey job : jobs) {
					JobDetail jobDetail = scheduler.getJobDetail(job);
					String key = job + groupName;
					if (!addedJobs.contains(key) && matchesSearch(((JobDetailImpl) jobDetail).getName())) {
						jobList.add(jobDetail);
						addedJobs.add(key);
					}
				}
			}

		} catch (SchedulerException e) {
			LOG.error("Problem listing jobs, schedule may be paused or stopped", e);
		}

		return SUCCESS;

	}

	private boolean matchesSearch(String jobName) {
		if (StringUtil.isEmpty(searchName)) {
			return true;
		} else {
			return jobName.contains(searchName);
		}
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

}

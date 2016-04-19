package org.quartz.ui.web.action.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.matchers.GroupMatcher;

public class ListTriggers extends ScheduleBase {

	/**
     * 
     */
    private static final long serialVersionUID = -5522888318824166108L;
	String jobName="";
	String jobGroup=""; 
	String triggerGroup = "";
		
	private List triggerList = new ArrayList();
	
	/**
	 * Returns the jobs.
	 * @return ArrayList
	 */
	public java.util.List getTriggers() {
		return  triggerList;
	}
	
				
	@Override
	public String execute() throws Exception  {
            Scheduler scheduler = ScheduleBase.getCurrentScheduler();
			this.triggerList = new ArrayList<Trigger>(5);		
			List<String> triggerGroups =null;
				try {

					triggerGroups = scheduler.getTriggerGroupNames();
					} catch (SchedulerException e) {
					LOG.error("Problem listing triggers, schedule may be paused or stopped", e);
			   }		
							for (String groupName : triggerGroups) {
								Set<TriggerKey> triggerNames = scheduler.getTriggerKeys(GroupMatcher.triggerGroupEquals(groupName));
								for (TriggerKey triggerName : triggerNames) {
									Trigger trigger =
										scheduler.getTrigger(triggerName);
					
								/*	tForm.setDescription(trigger.getDescription());
									tForm.setJobGroup(trigger.getJobGroup());
									tForm.setJobName(trigger.getJobName());
									tForm.setMisFireInstruction(trigger.getMisfireInstruction());
				
									tForm.setStartTime(Util.getDateAsString(trigger.getStartTime()));
									tForm.setStopTime(Util.getDateAsString(trigger.getEndTime()));
				
									tForm.setTriggerGroup(trigger.getGroup());
									tForm.setTriggerName(trigger.getName());
				
									tForm.setNextFireTime(Util.getDateAsString(trigger.getNextFireTime()));
									tForm.setPreviousFireTime(Util.getDateAsString(trigger.getPreviousFireTime()));
									tForm.setType(Util.getTriggerType(trigger));*/
								
								this.triggerList.add(trigger);
								
							}
						}
							
				
			
		return SUCCESS;

		}



}

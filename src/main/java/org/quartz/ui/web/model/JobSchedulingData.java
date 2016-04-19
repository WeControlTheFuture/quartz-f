package org.quartz.ui.web.model;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.quartz.ui.util.JaxbUtil;
import org.quartz.ui.web.model.JobSchedulingData.Schedule.Cron;
import org.quartz.ui.web.model.JobSchedulingData.Schedule.Job;
import org.quartz.ui.web.model.JobSchedulingData.Schedule.Trigger;

@XmlRootElement(name = "job-scheduling-data")
public class JobSchedulingData {
	private String xmlnsXsi = "http://www.w3.org/2001/XMLSchema-instance";
	private String xsiSchemaLocation = "http://www.quartz-scheduler.org/xml/JobSchedulingData http://www.quartz-scheduler.org/xml/job_scheduling_data_1_8.xsd";
	private String version = "1.8";
	private Schedule schedule;

	public String getVersion() {
		return version;
	}

	@XmlAttribute
	public void setVersion(String version) {
		this.version = version;
	}

	public String getXsiSchemaLocation() {
		return xsiSchemaLocation;
	}

	@XmlAttribute(name = "xsi:schemaLocation")
	public void setXsiSchemaLocation(String xsiSchemaLocation) {
		this.xsiSchemaLocation = xsiSchemaLocation;
	}

	public String getXmlnsXsi() {
		return xmlnsXsi;
	}

	@XmlAttribute(name = "xmlns:xsi")
	public void setXmlnsXsi(String xmlnsXsi) {
		this.xmlnsXsi = xmlnsXsi;
	}

	public JobSchedulingData() {
		this.schedule = new Schedule();
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public static class Schedule {

		private Job job;

		private List<Trigger> trigger;

		public Schedule() {
			this.job = new Job();
			this.trigger = new ArrayList<Trigger>();
		}

		public static class Trigger {
			private Cron cron;
			private DateInterval dateInterval;
			private Simple simple;

			public String efficientName() {
				if (cron != null)
					return cron.getName();
				if (dateInterval != null)
					return dateInterval.getName();
				if (simple != null)
					return simple.getName();
				return "";
			}

			@XmlElement(name = "date-interval")
			public void setDateInterval(DateInterval dateInterval) {
				this.dateInterval = dateInterval;
			}

			public Cron getCron() {
				return cron;
			}

			public void setCron(Cron cron) {
				this.cron = cron;
			}

			public DateInterval getDateInterval() {
				return dateInterval;
			}

			public Simple getSimple() {
				return simple;
			}

			public void setSimple(Simple simple) {
				this.simple = simple;
			}

			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + ((cron == null) ? 0 : cron.hashCode());
				result = prime * result + ((dateInterval == null) ? 0 : dateInterval.hashCode());
				result = prime * result + ((simple == null) ? 0 : simple.hashCode());
				return result;
			}

//			@Override
//			public boolean equals(Object obj) {
//				if (this == obj)
//					return true;
//				if (obj == null)
//					return false;
//				if (getClass() != obj.getClass())
//					return false;
//				Trigger other = (Trigger) obj;
//				if (cron == null) {
//					if (other.cron != null)
//						return false;
//				} else if (!cron.equals(other.cron))
//					return false;
//				if (dateInterval == null) {
//					if (other.dateInterval != null)
//						return false;
//				} else if (!dateInterval.equals(other.dateInterval))
//					return false;
//				if (simple == null) {
//					if (other.simple != null)
//						return false;
//				} else if (!simple.equals(other.simple))
//					return false;
//				return true;
//			}
		}

		public Job getJob() {
			return job;
		}

		public void setJob(Job job) {
			this.job = job;
		}

		public List<Trigger> getTrigger() {
			return trigger;
		}

		public void setTrigger(List<Trigger> trigger) {
			this.trigger = trigger;
		}

		public static class JobDataMap {
			private List<Entry> entry;

			public JobDataMap() {
				this.entry = new ArrayList<Entry>();
			}

			public List<Entry> getEntry() {
				return entry;
			}

			public void setEntry(List<Entry> entry) {
				this.entry = entry;
			}

			public static class Entry {
				private String key;
				private String value;

				public Entry() {
					this.key = "";
					this.value = "";
				}

				public String getKey() {
					return key;
				}

				public void setKey(String key) {
					this.key = key;
				}

				public String getValue() {
					return value;
				}

				public void setValue(String value) {
					this.value = value;
				}
			}
		}

		@XmlType(propOrder = { "name", "group", "description", "jobClass", "durability", "recover", "jobDataMap" })
		public static class Job {
			private String name;
			private String group;
			private String description;
			private String jobClass;
			private String durability = "true";
			private String recover = "true";
			private JobDataMap jobDataMap;

			public Job() {
			}

			@XmlElement(name = "job-class")
			public void setJobClass(String jobClass) {
				this.jobClass = jobClass;
			}

			@XmlElement(name = "job-data-map")
			public void setJobDataMap(JobDataMap jobDataMap) {
				this.jobDataMap = jobDataMap;
			}

			public void setName(String name) {
				this.name = name;
				this.group = name + "Group";
			}

			public void setGroup(String group) {
			}

			public void setDescription(String description) {
				this.description = description;
			}

			public void setDurability(String durability) {
				this.durability = durability;
			}

			public void setRecover(String recover) {
				this.recover = recover;
			}

			public String getName() {
				return name;
			}

			public String getGroup() {
				return group;
			}

			public String getDescription() {
				return description;
			}

			public String getJobClass() {
				return jobClass;
			}

			public String getDurability() {
				return durability;
			}

			public String getRecover() {
				return recover;
			}

			public JobDataMap getJobDataMap() {
				return jobDataMap;
			}
		}

		@XmlType(propOrder = { "name", "group", "description", "jobName", "jobGroup", "startTime", "endTime", "cronExpression", "timeZone", "misfireInstruction", "jobDataMap" })
		public static class Cron {
			private String name;
			private String group;
			private String jobName;
			private String jobGroup;
			private String description;
			private String startTime;
			private String endTime;
			private String timeZone = "";
			private String misfireInstruction;
			private String cronExpression;
			private JobDataMap jobDataMap;

			public Cron() {
			}

			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + ((name == null) ? 0 : name.hashCode());
				return result;
			}

			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				Cron other = (Cron) obj;
				if (name == null) {
					if (other.name != null)
						return false;
				} else if (!name.equals(other.name))
					return false;
				return true;
			}

			@XmlElement(name = "job-name")
			public void setJobName(String jobName) {
				this.jobName = jobName;
				this.group = jobName + "TriggerGroup";
			}

			@XmlElement(name = "job-group")
			public void setJobGroup(String jobGroup) {
				this.jobGroup = jobGroup;
			}

			@XmlElement(name = "start-time")
			public void setStartTime(String startTime) {
				this.startTime = startTime;
			}

			@XmlElement(name = "end-time")
			public void setEndTime(String endTime) {
				this.endTime = endTime;
			}

			@XmlElement(name = "time-zone")
			public void setTimeZone(String timeZone) {
				this.timeZone = timeZone;
			}

			@XmlElement(name = "cron-expression")
			public void setCronExpression(String cronExpression) {
				this.cronExpression = cronExpression;
			}

			@XmlElement(name = "misfire-instruction")
			public void setMisfireInstruction(String misfireInstruction) {
				this.misfireInstruction = misfireInstruction;
			}

			@XmlElement(name = "job-data-map")
			public void setJobDataMap(JobDataMap jobDataMap) {
				this.jobDataMap = jobDataMap;
			}

			public void setName(String name) {
				this.name = name;
			}

			public void setGroup(String group) {
			}

			public void setDescription(String description) {
				this.description = description;
			}

			public String getName() {
				return name;
			}

			public String getGroup() {
				return group;
			}

			public String getJobName() {
				return jobName;
			}

			public String getJobGroup() {
				return jobGroup;
			}

			public String getDescription() {
				return description;
			}

			public String getStartTime() {
				return startTime;
			}

			public String getEndTime() {
				return endTime;
			}

			public JobDataMap getJobDataMap() {
				return jobDataMap;
			}

			public String getTimeZone() {
				return timeZone;
			}

			public String getCronExpression() {
				return cronExpression;
			}

			public String getMisfireInstruction() {
				return misfireInstruction;
			}

		}

		@XmlType(propOrder = { "name", "group", "description", "jobName", "jobGroup", "startTime", "endTime", "repeatInterval", "repeatIntervalUnit", "jobDataMap" })
		public static class DateInterval {
			private String name;
			private String group;
			private String jobName;
			private String jobGroup;
			private String description;
			private String startTime;
			private String endTime;
			private String repeatInterval;
			private String repeatIntervalUnit;
			private JobDataMap jobDataMap;

			public DateInterval() {
			}

			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + ((name == null) ? 0 : name.hashCode());
				return result;
			}

			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				DateInterval other = (DateInterval) obj;
				if (name == null) {
					if (other.name != null)
						return false;
				} else if (!name.equals(other.name))
					return false;
				return true;
			}

			@XmlElement(name = "repeat-interval")
			public void setRepeatInterval(String repeatInterval) {
				this.repeatInterval = repeatInterval;
			}

			@XmlElement(name = "repeat-unit")
			public void setRepeatIntervalUnit(String repeatIntervalUnit) {
				this.repeatIntervalUnit = repeatIntervalUnit;
			}

			@XmlElement(name = "job-name")
			public void setJobName(String jobName) {
				this.jobName = jobName;
				this.group = jobName + "TriggerGroup";
			}

			@XmlElement(name = "job-group")
			public void setJobGroup(String jobGroup) {
				this.jobGroup = jobGroup;
			}

			@XmlElement(name = "start-time")
			public void setStartTime(String startTime) {
				this.startTime = startTime;
			}

			@XmlElement(name = "end-time")
			public void setEndTime(String endTime) {
				this.endTime = endTime;
			}

			@XmlElement(name = "job-data-map")
			public void setJobDataMap(JobDataMap jobDataMap) {
				this.jobDataMap = jobDataMap;
			}

			public void setName(String name) {
				this.name = name;
			}

			public void setGroup(String group) {
			}

			public void setDescription(String description) {
				this.description = description;
			}

			public String getName() {
				return name;
			}

			public String getGroup() {
				return group;
			}

			public String getJobName() {
				return jobName;
			}

			public String getJobGroup() {
				return jobGroup;
			}

			public String getDescription() {
				return description;
			}

			public String getStartTime() {
				return startTime;
			}

			public String getEndTime() {
				return endTime;
			}

			public JobDataMap getJobDataMap() {
				return jobDataMap;
			}

			public String getRepeatInterval() {
				return repeatInterval;
			}

			public String getRepeatIntervalUnit() {
				return repeatIntervalUnit;
			}

		}

		@XmlType(propOrder = { "name", "group", "description", "jobName", "jobGroup", "startTime", "endTime", "repeatCount", "repeatInterval", "jobDataMap" })
		public static class Simple {
			private String name;
			private String group;
			private String jobName;
			private String jobGroup;
			private String description;
			private String startTime;
			private String endTime;
			private String repeatInterval;
			private String repeatCount;
			private JobDataMap jobDataMap;

			public Simple() {
			}

			@XmlElement(name = "repeat-interval")
			public void setRepeatInterval(String repeatInterval) {
				this.repeatInterval = repeatInterval;
			}

			@XmlElement(name = "repeat-count")
			public void setRepeatCount(String repeatCount) {
				this.repeatCount = repeatCount;
			}

			@XmlElement(name = "job-name")
			public void setJobName(String jobName) {
				this.jobName = jobName;
				this.group = jobName + "TriggerGroup";
			}

			@XmlElement(name = "job-group")
			public void setJobGroup(String jobGroup) {
				this.jobGroup = jobGroup;
			}

			@XmlElement(name = "start-time")
			public void setStartTime(String startTime) {
				this.startTime = startTime;
			}

			@XmlElement(name = "end-time")
			public void setEndTime(String endTime) {
				this.endTime = endTime;
			}

			@XmlElement(name = "job-data-map")
			public void setJobDataMap(JobDataMap jobDataMap) {
				this.jobDataMap = jobDataMap;
			}

			public void setName(String name) {
				this.name = name;
			}

			public void setGroup(String group) {
			}

			public void setDescription(String description) {
				this.description = description;
			}

			public String getName() {
				return name;
			}

			public String getGroup() {
				return group;
			}

			public String getJobName() {
				return jobName;
			}

			public String getJobGroup() {
				return jobGroup;
			}

			public String getDescription() {
				return description;
			}

			public String getStartTime() {
				return startTime;
			}

			public String getEndTime() {
				return endTime;
			}

			public JobDataMap getJobDataMap() {
				return jobDataMap;
			}

			public String getRepeatInterval() {
				return repeatInterval;
			}

			public String getRepeatCount() {
				return repeatCount;
			}
		}
	}

	public static void main(String[] args) throws JAXBException, UnsupportedEncodingException {
		JobSchedulingData jsd = new JobSchedulingData();
		Schedule schedule = new Schedule();
		Job job = new Job();
		job.name = "test";
		Cron cron = new Cron();
		cron.cronExpression = "0 * * ? * *";
		schedule.setJob(job);
		Trigger t = new Trigger();
		t.setCron(cron);
		List<Trigger> tlist = new ArrayList<Trigger>();
		tlist.add(t);
		t = new Trigger();
		t.setCron(cron);
		tlist.add(t);
		schedule.setTrigger(tlist);
		jsd.setSchedule(schedule);
		String s = JaxbUtil.marshal(jsd, JobSchedulingData.class);
		System.out.println(s);
	}
}

package org.quartz.ui.web.action.trigger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.quartz.ui.util.JaxbUtil;
import org.quartz.ui.web.TriggerType;
import org.quartz.ui.web.action.base.BaseAction;
import org.quartz.ui.web.dao.ZkConfigDao;
import org.quartz.ui.web.model.JobSchedulingData;
import org.quartz.ui.web.model.JobSchedulingData.Schedule.Cron;
import org.quartz.ui.web.model.JobSchedulingData.Schedule.DateInterval;
import org.quartz.ui.web.model.JobSchedulingData.Schedule.Simple;
import org.quartz.ui.web.model.JobSchedulingData.Schedule.Trigger;

public class TriggerAction extends BaseAction {
	private static final Log log = LogFactory.getLog(TriggerAction.class);
	private TriggerType type;
	private String jobName;
	private String jobGroupName;
	private String triggerName;
	private Trigger trigger = new Trigger();
	private ZkConfigDao zkConfigDao;
	private SimpleDateFormat sdfYMd = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat sdfHms = new SimpleDateFormat("HH:mm:ss");
	public static final long ONEYEAR = 365 * 24 * 3600 * 1000l;

	public String create() {
		if (type.equals(TriggerType.CRON))
			trigger.setCron(new Cron());
		if (type.equals(TriggerType.INTERVAL))
			trigger.setDateInterval(new DateInterval());
		if (type.equals(TriggerType.SIMPLE))
			trigger.setSimple(new Simple());
		return type.toString();
	}

	public String list() {
		return "list";
	}

	public void start() {
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			Date date = new Date(System.currentTimeMillis() + 5000);
			Date dateEnd = new Date(System.currentTimeMillis() + ONEYEAR * 100);
			String time = sdfYMd.format(date) + "T" + sdfHms.format(date);
			String timeEnd = sdfYMd.format(dateEnd) + "T" + sdfHms.format(dateEnd);
			String content = zkConfigDao.getConfig(jobName);
			JobSchedulingData jsd = JaxbUtil.unmarshal(content, JobSchedulingData.class);
			List<Trigger> triggers = jsd.getSchedule().getTrigger();
			if (triggers != null) {
				for (Trigger tri : triggers) {
					if (tri.efficientName().equals(trigger.efficientName())) {
						if (tri.getCron() != null) {
							tri.getCron().setStartTime(time);
							tri.getCron().setEndTime(timeEnd);
						}
						if (tri.getDateInterval() != null) {
							tri.getDateInterval().setStartTime(time);
							tri.getDateInterval().setEndTime(timeEnd);
						}
						if (tri.getSimple() != null) {
							tri.getSimple().setStartTime(time);
							tri.getSimple().setEndTime(time);
						}

					}
				}
			}
			zkConfigDao.modifyConfig(jobName, JaxbUtil.marshal(jsd, JobSchedulingData.class));
			response.getWriter().write("{'code':'0'}");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error start trigger", e);
			try {
				response.getWriter().write("{'code':'1'}");
			} catch (IOException e1) {
				log.error("error stop trigger", e);
			}
		}

	}

	public void stop() {
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			Date date = new Date(0L);
			Date dateEnd = new Date(0L);
			String time = sdfYMd.format(date) + "T" + sdfHms.format(date);
			String timeEnd = sdfYMd.format(dateEnd) + "T" + sdfHms.format(dateEnd);
			String content = zkConfigDao.getConfig(jobName);
			JobSchedulingData jsd = JaxbUtil.unmarshal(content, JobSchedulingData.class);
			List<Trigger> triggers = jsd.getSchedule().getTrigger();
			if (triggers != null) {
				for (Trigger tri : triggers) {
					if (tri.efficientName().equals(trigger.efficientName())) {
						if (tri.getCron() != null) {
							tri.getCron().setStartTime(time);
							tri.getCron().setEndTime(timeEnd);
						}
						if (tri.getDateInterval() != null) {
							tri.getDateInterval().setStartTime(time);
							tri.getDateInterval().setEndTime(timeEnd);
						}
						if (tri.getSimple() != null) {
							tri.getSimple().setStartTime(time);
							tri.getSimple().setEndTime(timeEnd);
						}
					}
				}
			}
			zkConfigDao.modifyConfig(jobName, JaxbUtil.marshal(jsd, JobSchedulingData.class));

			response.getWriter().write("{'code':'0'}");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error stop trigger", e);
			try {
				response.getWriter().write("{'code':'1'}");
			} catch (IOException e1) {
				log.error("error stop trigger", e);
			}
		}
	}

	public String save() {
		try {
			Date date = new Date(System.currentTimeMillis() + ONEYEAR * 100);
			Date dateEnd = new Date(System.currentTimeMillis() + ONEYEAR * 100);
			String time = sdfYMd.format(date) + "T" + sdfHms.format(date);
			String timeEnd = sdfYMd.format(dateEnd) + "T" + sdfHms.format(dateEnd);
			if (trigger.getCron() != null) {
				trigger.getCron().setStartTime(time);
				trigger.getCron().setEndTime(timeEnd);
			} else if (trigger.getDateInterval() != null) {
				trigger.getDateInterval().setStartTime(time);
				trigger.getDateInterval().setEndTime(time);
			} else if (trigger.getSimple() != null) {
				trigger.getSimple().setStartTime(time);
				trigger.getSimple().setEndTime(time);
			}
			String content = zkConfigDao.getConfig(jobName);
			JobSchedulingData jsd = JaxbUtil.unmarshal(content, JobSchedulingData.class);
			List<Trigger> triggers = jsd.getSchedule().getTrigger();
			if (triggers != null) {
				int index = -1;
				for (int i = 0; i < triggers.size(); i++) {
					if (triggers.get(i).efficientName().equals(trigger.efficientName()))
						index = i;
				}
				if (index != -1)
					triggers.remove(index);
				triggers.add(trigger);
			} else {
				triggers = new ArrayList<Trigger>();
				triggers.add(trigger);
				jsd.getSchedule().setTrigger(triggers);
			}
			zkConfigDao.modifyConfig(jobName, JaxbUtil.marshal(jsd, JobSchedulingData.class));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error save triggers", e);
		}
		return "list";
	}

	public String edit() {
		try {
			String content = zkConfigDao.getConfig(jobName);
			JobSchedulingData jsd = JaxbUtil.unmarshal(content, JobSchedulingData.class);
			List<Trigger> triggers = jsd.getSchedule().getTrigger();

			for (int i = 0; i < triggers.size(); i++) {
				if (triggers.get(i).efficientName().equals(triggerName)) {
					trigger = triggers.get(i);
					return type.toString();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("error save triggers", e);
		}
		return type.toString();
	}

	public String delete() {
		try {
			String content = zkConfigDao.getConfig(jobName);
			JobSchedulingData jsd = JaxbUtil.unmarshal(content, JobSchedulingData.class);
			List<Trigger> triggers = jsd.getSchedule().getTrigger();
			int index = -1;
			for (int i = 0; i < triggers.size(); i++) {
				if (triggers.get(i).efficientName().equals(triggerName)) {
					index = i;
				}
			}
			if (index != -1)
				triggers.remove(index);
			zkConfigDao.modifyConfig(jobName, JaxbUtil.marshal(jsd, JobSchedulingData.class));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error save triggers", e);
		}
		return "list";
	}

	public Map<String, Collection<Trigger>> getTriggers() {
		Map<String, Collection<Trigger>> map = new HashMap<String, Collection<Trigger>>();
		map.put(TriggerType.CRON.toString(), new ArrayList<Trigger>());
		map.put(TriggerType.INTERVAL.toString(), new ArrayList<Trigger>());
		map.put(TriggerType.SIMPLE.toString(), new ArrayList<Trigger>());
		List<Trigger> result = new ArrayList<Trigger>();
		try {
			String content = zkConfigDao.getConfig(jobName);
			JobSchedulingData jsd = JaxbUtil.unmarshal(content, JobSchedulingData.class);
			if (jsd.getSchedule().getTrigger() != null)
				result = jsd.getSchedule().getTrigger();
			for (Trigger t : result) {
				if (t.getCron() != null) {
					map.get(TriggerType.CRON.toString()).add(t);
				} else if (t.getDateInterval() != null) {
					map.get(TriggerType.INTERVAL.toString()).add(t);
				} else if (t.getSimple() != null) {
					map.get(TriggerType.SIMPLE.toString()).add(t);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error get triggers", e);
		}
		return map;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Trigger getTrigger() {
		return trigger;
	}

	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public ZkConfigDao getZkConfigDao() {
		return zkConfigDao;
	}

	public void setZkConfigDao(ZkConfigDao zkConfigDao) {
		this.zkConfigDao = zkConfigDao;
	}

	public TriggerType getType() {
		return type;
	}

	public void setType(TriggerType type) {
		this.type = type;
	}

	public String getJobGroupName() {
		return jobGroupName;
	}

	public void setJobGroupName(String jobGroupName) {
		this.jobGroupName = jobGroupName;
	}

}

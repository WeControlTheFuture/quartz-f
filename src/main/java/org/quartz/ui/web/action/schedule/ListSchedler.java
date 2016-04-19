package org.quartz.ui.web.action.schedule;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.quartz.JobDetail;
import org.quartz.ui.web.action.base.BaseAction;
import org.quartz.ui.web.model.ScheduleInfo;
import org.quartz.ui.web.dao.SchedulerDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.opensymphony.xwork2.ActionContext;

public class ListSchedler extends ScheduleBase {
	// private static final String SUCCESS = null;
	protected ScheduleInfo shceduleinfo;
	protected List<ScheduleInfo> schedulelist;
	protected HashMap schedulelistByGroup = new HashMap(25);
	private final static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();
	private static boolean show_sql = false;
	private SchedulerDao schedulerDao;

	public void setSchedulerDao(SchedulerDao schedulerDao) {
		this.schedulerDao = schedulerDao;
	}


	/**
	 * Returns the schedulelist.
	 * 
	 * @return ArrayList
	 */
	public List<ScheduleInfo> getSchedulelist() {
		return schedulelist;
	}

	public static List<ScheduleInfo> list() {
		SchedulerDao dao = new SchedulerDao();
		return dao.queryScheduleInfos();
	}

	@Override
	public String execute() {
		this.schedulelist = schedulerDao.queryScheduleInfos();		
		return SUCCESS;

	}
}

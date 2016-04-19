package org.quartz.ui;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TimeZone;
import java.util.TreeSet;

import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.ScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.triggers.CronTriggerImpl;

public class UICronTrigger implements Trigger {

    /**
     * 
     */
    private static final long serialVersionUID = -3299719419093997942L;

    public static final int MISFIRE_INSTRUCTION_FIRE_ONCE_NOW = 1;

    public static final int MISFIRE_INSTRUCTION_DO_NOTHING = 2;

    private static final int ALL_SPEC_INT = 99;

    private static final int NO_SPEC_INT = 98;

    private static final Integer ALL_SPEC = new Integer(ALL_SPEC_INT);

    private static final Integer NO_SPEC = new Integer(NO_SPEC_INT);

    private static Map monthMap = new HashMap(20);

    private static Map dayMap = new HashMap(60);

    static {

	monthMap.put("JAN", new Integer(0));

	monthMap.put("FEB", new Integer(1));

	monthMap.put("MAR", new Integer(2));

	monthMap.put("APR", new Integer(3));

	monthMap.put("MAY", new Integer(4));

	monthMap.put("JUN", new Integer(5));

	monthMap.put("JUL", new Integer(6));

	monthMap.put("AUG", new Integer(7));

	monthMap.put("SEP", new Integer(8));

	monthMap.put("OCT", new Integer(9));

	monthMap.put("NOV", new Integer(10));

	monthMap.put("DEC", new Integer(11));

	dayMap.put("SUN", new Integer(1));

	dayMap.put("MON", new Integer(2));

	dayMap.put("TUE", new Integer(3));

	dayMap.put("WED", new Integer(4));

	dayMap.put("THU", new Integer(5));

	dayMap.put("FRI", new Integer(6));

	dayMap.put("SAT", new Integer(7));

    }

    private Date startTime = null;

    private Date endTime = null;

    private Date nextFireTime = null;

    private TimeZone timeZone = null;

    private Date previousFireTime = null;

    private TreeSet seconds = null;

    private TreeSet minutes = null;

    private TreeSet hours = null;

    private TreeSet daysOfMonth = null;

    private TreeSet months = null;

    private TreeSet daysOfWeek = null;

    private TreeSet years = null;

    private final transient boolean lastdayOfWeek = false;

    private final transient int nthdayOfWeek = 0;

    private final transient boolean lastdayOfMonth = false;

    private final transient boolean calendardayOfWeek = false;

    private final transient boolean calendardayOfMonth = false;

    public void reset() {

	seconds = new TreeSet();

	minutes = new TreeSet();

	hours = new TreeSet();

	daysOfMonth = new TreeSet();

	months = new TreeSet();

	daysOfWeek = new TreeSet();

	years = new TreeSet();

	seconds.add(new Integer(0));

	minutes.add(ALL_SPEC);

	for (int i = 0; i < 60; i++) {
	    minutes.add(new Integer(i));
	}

	hours.add(ALL_SPEC);

	for (int i = 0; i < 24; i++) {
	    hours.add(new Integer(i));
	}

	daysOfMonth.add(ALL_SPEC);

	for (int i = 1; i <= 31; i++) {
	    daysOfMonth.add(new Integer(i));
	}

	months.add(ALL_SPEC);

	for (int i = 1; i <= 12; i++) {
	    months.add(new Integer(i));
	}

	daysOfWeek.add(NO_SPEC);

	years.add(ALL_SPEC);

	for (int i = 1970; i <= 2099; i++) {
	    years.add(new Integer(i));
	}

	startTime = new Date();

	setStartTime(startTime);

	setTimeZone(TimeZone.getDefault());

    }

    public UICronTrigger() {

	super();

	reset();

    }

    public UICronTrigger(String name, String group) {

	super();

	reset();

    }

    public UICronTrigger(String name, String group, String jobName, String jobGroup) {

	super();

	reset();

    }

    public Date getStartTime() {

	return this.startTime;

    }

    public void setStartTime(Date startTime) {

	if (startTime == null) {
	    throw new IllegalArgumentException("Start time cannot be null");
	}

	Date eTime = getEndTime();

	if (eTime != null && startTime != null && eTime.before(startTime)) {
	    throw new IllegalArgumentException("End time cannot be before start time");
	}

	Calendar cl = Calendar.getInstance();

	cl.setTime(startTime);

	cl.set(Calendar.MILLISECOND, 0);

	this.startTime = cl.getTime();

    }

    public Date getEndTime() {

	return this.endTime;

    }

    public void setEndTime(Date endTime) {

	Date sTime = getStartTime();

	if (sTime != null && endTime != null && sTime.after(endTime)) {
	    throw new IllegalArgumentException("End time cannot be before start time");
	}

	this.endTime = endTime;

    }

    public Date getNextFireTime() {

	return this.nextFireTime;

    }

    public void updateAfterMisfire(org.quartz.Calendar cal) {

	int instr = getMisfireInstruction();

	if (instr == MISFIRE_INSTRUCTION_SMART_POLICY) {
	    instr = MISFIRE_INSTRUCTION_DO_NOTHING;
	}

	if (instr == MISFIRE_INSTRUCTION_DO_NOTHING) {

	    Date newFireTime = getFireTimeAfter(new Date());

	    while (newFireTime != null && cal != null && !cal.isTimeIncluded(newFireTime.getTime())) {

		newFireTime = getFireTimeAfter(newFireTime);

	    }

	    setNextFireTime(newFireTime);

	} else if (instr == MISFIRE_INSTRUCTION_FIRE_ONCE_NOW) {

	    setNextFireTime(new Date());

	}

    }

    public Date getPreviousFireTime() {

	return this.previousFireTime;

    }

    public void setPreviousFireTime(Date previousFireTime) {

	this.previousFireTime = previousFireTime;

    }

    public void setNextFireTime(Date nextFireTime) {

	this.nextFireTime = nextFireTime;

    }

    public TimeZone getTimeZone() {

	return this.timeZone;

    }

    public void setTimeZone(TimeZone timeZone) {

	this.timeZone = timeZone;

    }

    public Date getFireTimeAfter(Date afterTime) {

	if (afterTime == null) {
	    afterTime = new Date();
	}

	if (startTime.after(afterTime)) {
	    afterTime = new Date(startTime.getTime() - 1000L);
	}

	Date pot = getTimeAfter(afterTime);

	if (endTime != null && pot != null && pot.after(endTime)) {
	    return null;
	}

	return pot;

    }

    public Date getFinalFireTime() {

	if (this.endTime != null) {
	    return getTimeBefore(this.endTime);
	} else {
	    return null;
	}

    }

    public boolean mayFireAgain() {

	return (getNextFireTime() != null);

    }

    protected boolean validateMisfireInstruction(int misfireInstruction) {

	if (misfireInstruction < MISFIRE_INSTRUCTION_SMART_POLICY) {
	    return false;
	}

	if (misfireInstruction > MISFIRE_INSTRUCTION_DO_NOTHING) {
	    return false;
	}

	return true;

    }

    public void updateAfterMisfire() {

	int instr = getMisfireInstruction();

	if (instr == MISFIRE_INSTRUCTION_SMART_POLICY) {
	    instr = MISFIRE_INSTRUCTION_DO_NOTHING;
	}

	if (instr == MISFIRE_INSTRUCTION_DO_NOTHING) {

	    setNextFireTime(getFireTimeAfter(new Date()));

	} else if (instr == MISFIRE_INSTRUCTION_FIRE_ONCE_NOW) {

	    setNextFireTime(new Date());

	}

    }

    public boolean willFireOn(Calendar test) {

	Integer second = new Integer(test.get(Calendar.SECOND));

	Integer minute = new Integer(test.get(Calendar.MINUTE));

	Integer hour = new Integer(test.get(Calendar.HOUR_OF_DAY));

	Integer day = new Integer(test.get(Calendar.DAY_OF_MONTH));

	Integer month = new Integer(test.get(Calendar.MONTH));

	if ((seconds.contains(second) || seconds.contains(ALL_SPEC))
		&& (minutes.contains(minute) || minutes.contains(ALL_SPEC))
		&& (hours.contains(hour) || hours.contains(ALL_SPEC))
		&& (daysOfMonth.contains(day) || daysOfMonth.contains(ALL_SPEC))
		&& (months.contains(month) || months.contains(ALL_SPEC))) {

	    return true;

	}

	return false;

    }

    public void executionComplete(JobExecutionContext context, JobExecutionException result) {

	/*if (result != null && result.refireImmediately()) {
	    return Trigger.INSTRUCTION_RE_EXECUTE_JOB;
	}

	if (result != null && result.refireImmediately()) {
	    return INSTRUCTION_RE_EXECUTE_JOB;
	}

	if (result != null && result.unscheduleFiringTrigger()) {
	    return INSTRUCTION_SET_TRIGGER_COMPLETE;
	}

	if (result != null && result.unscheduleAllTriggers()) {
	    return INSTRUCTION_SET_ALL_JOB_TRIGGERS_COMPLETE;
	}

	if (!mayFireAgain()) {
	    return INSTRUCTION_DELETE_TRIGGER;
	}

	return INSTRUCTION_NOOP;*/

    }

    public void triggered(org.quartz.Calendar calendar) {

	previousFireTime = nextFireTime;

	nextFireTime = getFireTimeAfter(nextFireTime);

	while (nextFireTime != null && calendar != null && !calendar.isTimeIncluded(nextFireTime.getTime())) {

	    nextFireTime = getFireTimeAfter(nextFireTime);

	}

    }

    public void updateWithNewCalendar(org.quartz.Calendar calendar, long misfireThreshold) {

	nextFireTime = getFireTimeAfter(previousFireTime);

	Date now = new Date();

	do {

	    while (nextFireTime != null && calendar != null && !calendar.isTimeIncluded(nextFireTime.getTime())) {

		nextFireTime = getFireTimeAfter(nextFireTime);

	    }

	    if (nextFireTime != null && nextFireTime.before(now)) {

		long diff = now.getTime() - nextFireTime.getTime();

		if (diff >= misfireThreshold) {

		    nextFireTime = getFireTimeAfter(nextFireTime);

		    continue;

		}

	    }

	} while (false);

    }

    public Date computeFirstFireTime(org.quartz.Calendar calendar) {

	nextFireTime = getFireTimeAfter(new Date(startTime.getTime() - 1000L));

	while (nextFireTime != null && calendar != null && !calendar.isTimeIncluded(nextFireTime.getTime())) {

	    nextFireTime = getFireTimeAfter(nextFireTime);

	}

	return nextFireTime;

    }

    public String getExpressionSummary() {

	StringBuffer buf = new StringBuffer();

	buf.append("seconds: ");

	buf.append(getExpressionSetSummary(seconds));

	buf.append("\n");

	buf.append("minutes: ");

	buf.append(getExpressionSetSummary(minutes));

	buf.append("\n");

	buf.append("hours: ");

	buf.append(getExpressionSetSummary(hours));

	buf.append("\n");

	buf.append("daysOfMonth: ");

	buf.append(getExpressionSetSummary(daysOfMonth));

	buf.append("\n");

	buf.append("months: ");

	buf.append(getExpressionSetSummary(months));

	buf.append("\n");

	buf.append("daysOfWeek: ");

	buf.append(getExpressionSetSummary(daysOfWeek));

	buf.append("\n");

	buf.append("lastdayOfWeek: ");

	buf.append(lastdayOfWeek);

	buf.append("\n");

	buf.append("lastdayOfMonth: ");

	buf.append(lastdayOfMonth);

	buf.append("\n");

	buf.append("calendardayOfWeek: ");

	buf.append(calendardayOfWeek);

	buf.append("\n");

	buf.append("calendardayOfMonth: ");

	buf.append(calendardayOfMonth);

	buf.append("\n");

	buf.append("years: ");

	buf.append(getExpressionSetSummary(years));

	buf.append("\n");

	return buf.toString();

    }
    
    public String getCronExpression() {

	StringBuilder buf = new StringBuilder();

	buf.append(getExpressionSetSummary(seconds));
	buf.append(" ");
	buf.append(getExpressionSetSummary(minutes));
	buf.append(" ");
	buf.append(getExpressionSetSummary(hours));
	buf.append(" ");
	buf.append(getExpressionSetSummary(daysOfMonth));
	buf.append(" ");
	buf.append(getExpressionSetSummary(months));
	buf.append(" ");
	buf.append(getExpressionSetSummary(daysOfWeek));
	buf.append(" ");
	buf.append(getExpressionSetSummary(years));
	buf.append("\n");

	return buf.toString();

    }


    private String getExpressionSetSummary(Set set) {

	if (set.contains(NO_SPEC)) {
	    return "?";
	}

	if (set.contains(ALL_SPEC)) {
	    return "*";
	}

	StringBuffer buf = new StringBuffer();

	Iterator itr = set.iterator();

	boolean first = true;

	while (itr.hasNext()) {

	    Integer iVal = (Integer) itr.next();

	    String val = iVal.toString();

	    if (!first) {
		buf.append(",");
	    }

	    buf.append(val);

	    first = false;

	}

	return buf.toString();

    }

    private Date getTimeAfter(Date afterTime) {

	Calendar cl = Calendar.getInstance(timeZone);

	afterTime = new Date(afterTime.getTime() + 1000);

	cl.setTime(afterTime);

	cl.set(Calendar.MILLISECOND, 0);

	boolean gotOne = false;

	while (!gotOne) {

	    if (endTime != null && cl.getTime().after(endTime)) {
		return null;
	    }

	    SortedSet st = null;

	    int t = 0;

	    int sec = cl.get(Calendar.SECOND);

	    int min = cl.get(Calendar.MINUTE);

	    st = seconds.tailSet(new Integer(sec));

	    if (st != null && st.size() != 0) {

		sec = ((Integer) st.first()).intValue();

	    } else {

		sec = ((Integer) seconds.first()).intValue();

		min++;

		cl.set(Calendar.MINUTE, min);

	    }

	    cl.set(Calendar.SECOND, sec);

	    min = cl.get(Calendar.MINUTE);

	    int hr = cl.get(Calendar.HOUR_OF_DAY);

	    t = -1;

	    st = minutes.tailSet(new Integer(min));

	    if (st != null && st.size() != 0) {

		t = min;

		min = ((Integer) st.first()).intValue();

	    } else {

		min = ((Integer) minutes.first()).intValue();

		hr++;

	    }

	    if (min != t) {

		cl.set(Calendar.SECOND, 0);

		cl.set(Calendar.MINUTE, min);

		cl.set(Calendar.HOUR_OF_DAY, hr);

		continue;

	    }

	    cl.set(Calendar.MINUTE, min);

	    hr = cl.get(Calendar.HOUR_OF_DAY);

	    int day = cl.get(Calendar.DAY_OF_MONTH);

	    t = -1;

	    st = hours.tailSet(new Integer(hr));

	    if (st != null && st.size() != 0) {

		t = hr;

		hr = ((Integer) st.first()).intValue();

	    } else {

		hr = ((Integer) hours.first()).intValue();

		day++;

	    }

	    if (hr != t) {

		cl.set(Calendar.SECOND, 0);

		cl.set(Calendar.MINUTE, 0);

		cl.set(Calendar.HOUR_OF_DAY, hr);

		cl.set(Calendar.DAY_OF_MONTH, day);

		continue;

	    }

	    cl.set(Calendar.HOUR_OF_DAY, hr);

	    day = cl.get(Calendar.DAY_OF_MONTH);

	    int mon = cl.get(Calendar.MONTH) + 1;

	    t = -1;

	    boolean dayOfMSpec = !daysOfMonth.contains(NO_SPEC);

	    boolean dayOfWSpec = !daysOfWeek.contains(NO_SPEC);

	    if (dayOfMSpec && !dayOfWSpec) {

		st = daysOfMonth.tailSet(new Integer(day));

		if (lastdayOfMonth) {

		    t = day;

		    day = getLastDayOfMonth(mon);

		} else if (st != null && st.size() != 0) {

		    t = day;

		    day = ((Integer) st.first()).intValue();

		} else {

		    day = ((Integer) daysOfMonth.first()).intValue();

		    mon++;

		}

		if (day != t) {

		    cl.set(Calendar.SECOND, 0);

		    cl.set(Calendar.MINUTE, 0);

		    cl.set(Calendar.HOUR_OF_DAY, 0);

		    cl.set(Calendar.DAY_OF_MONTH, day);

		    cl.set(Calendar.MONTH, mon - 1);

		    continue;

		}

	    } else if (dayOfWSpec && !dayOfMSpec) {

		if (lastdayOfWeek) {

		    int dow = ((Integer) daysOfWeek.first()).intValue();

		    int cDow = cl.get(Calendar.DAY_OF_WEEK);

		    int daysToAdd = 0;

		    if (cDow < dow) {
			daysToAdd = dow - cDow;
		    }

		    if (cDow > dow) {
			daysToAdd = dow + (7 - cDow);
		    }

		    int lDay = getLastDayOfMonth(mon);

		    if (day + daysToAdd > lDay) {

			cl.set(Calendar.SECOND, 0);

			cl.set(Calendar.MINUTE, 0);

			cl.set(Calendar.HOUR_OF_DAY, 0);

			cl.set(Calendar.DAY_OF_MONTH, 1);

			cl.set(Calendar.MONTH, mon);

			continue;

		    }

		    while ((day + daysToAdd + 7) <= lDay) {
			daysToAdd += 7;
		    }

		    day += daysToAdd;

		} else if (nthdayOfWeek != 0) {

		    int dow = ((Integer) daysOfWeek.first()).intValue();

		    int cDow = cl.get(Calendar.DAY_OF_WEEK);

		    int daysToAdd = 0;

		    if (cDow < dow) {
			daysToAdd = dow - cDow;
		    } else if (cDow > dow) {
			daysToAdd = dow + (7 - cDow);
		    }

		    day += daysToAdd;

		    int weekOfMonth = day / 7;

		    if (day % 7 > 0) {
			weekOfMonth++;
		    }

		    daysToAdd = (nthdayOfWeek - weekOfMonth) * 7;

		    day += daysToAdd;

		    if (daysToAdd < 0 || day > getLastDayOfMonth(mon)) {

			cl.set(Calendar.SECOND, 0);

			cl.set(Calendar.MINUTE, 0);

			cl.set(Calendar.HOUR_OF_DAY, 0);

			cl.set(Calendar.DAY_OF_MONTH, 1);

			cl.set(Calendar.MONTH, mon);

			continue;

		    }

		} else {

		    int cDow = cl.get(Calendar.DAY_OF_WEEK);

		    int dow = ((Integer) daysOfWeek.first()).intValue();

		    st = daysOfWeek.tailSet(new Integer(cDow));

		    if (st != null && st.size() > 0) {

			dow = ((Integer) st.first()).intValue();

		    }

		    int daysToAdd = 0;

		    if (cDow < dow) {
			daysToAdd = dow - cDow;
		    }

		    if (cDow > dow) {
			daysToAdd = dow + (7 - cDow);
		    }

		    int lDay = getLastDayOfMonth(mon);

		    if (day + daysToAdd > lDay) {

			cl.set(Calendar.SECOND, 0);

			cl.set(Calendar.MINUTE, 0);

			cl.set(Calendar.HOUR_OF_DAY, 0);

			cl.set(Calendar.DAY_OF_MONTH, 1);

			cl.set(Calendar.MONTH, mon);

			continue;

		    } else if (daysToAdd > 0) {

			cl.set(Calendar.SECOND, 0);

			cl.set(Calendar.MINUTE, 0);

			cl.set(Calendar.HOUR_OF_DAY, 0);

			cl.set(Calendar.DAY_OF_MONTH, day + daysToAdd);

			cl.set(Calendar.MONTH, mon - 1);

			continue;

		    }

		}

	    } else {

		throw new UnsupportedOperationException(
			"Support for specifying both a day-of-week AND a day-of-month parameter is not implemented.");

	    }

	    cl.set(Calendar.DAY_OF_MONTH, day);

	    mon = cl.get(Calendar.MONTH) + 1;

	    int year = cl.get(Calendar.YEAR);

	    t = -1;

	    st = months.tailSet(new Integer(mon));

	    if (st != null && st.size() != 0) {

		t = mon;

		mon = ((Integer) st.first()).intValue();

	    } else {

		mon = ((Integer) months.first()).intValue();

		year++;

	    }

	    if (mon != t) {

		cl.set(Calendar.SECOND, 0);

		cl.set(Calendar.MINUTE, 0);

		cl.set(Calendar.HOUR_OF_DAY, 0);

		cl.set(Calendar.DAY_OF_MONTH, 1);

		cl.set(Calendar.MONTH, mon - 1);

		cl.set(Calendar.YEAR, year);

		continue;

	    }

	    cl.set(Calendar.MONTH, mon - 1);

	    year = cl.get(Calendar.YEAR);

	    t = -1;

	    st = years.tailSet(new Integer(year));

	    if (st != null && st.size() != 0) {

		t = year;

		year = ((Integer) st.first()).intValue();

	    } else {
		return null;
	    }

	    if (year != t) {

		cl.set(Calendar.SECOND, 0);

		cl.set(Calendar.MINUTE, 0);

		cl.set(Calendar.HOUR_OF_DAY, 0);

		cl.set(Calendar.DAY_OF_MONTH, 1);

		cl.set(Calendar.MONTH, mon - 1);

		cl.set(Calendar.YEAR, year);

		continue;

	    }

	    cl.set(Calendar.YEAR, year);

	    gotOne = true;

	}

	return cl.getTime();

    }

    private Date getTimeBefore(Date endTime) {

	return null;

    }

    public boolean isLeapYear() {

	Calendar cl = Calendar.getInstance(timeZone);

	int year = cl.get(Calendar.YEAR);

	if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
	    return true;
	} else {
	    return false;
	}

    }

    public int getLastDayOfMonth(int monthNum) {

	switch (monthNum) {
	case 1:
	    return 31;

	case 2:
	    return (isLeapYear()) ? 29 : 28;

	case 3:
	    return 31;

	case 4:
	    return 30;

	case 5:
	    return 31;

	case 6:
	    return 30;

	case 7:
	    return 31;

	case 8:
	    return 31;

	case 9:
	    return 30;

	case 10:
	    return 31;

	case 11:
	    return 30;

	case 12:
	    return 31;

	default:
	    throw new IllegalArgumentException("Illegal month number: " + monthNum);

	}

    }

    public Integer[] getSecondsValues() {

	Integer[] list = new Integer[60];

	for (int i = 0; i < 60; i++) {

	    list[i] = new Integer(i);

	}

	return list;

    }

    public Integer[] getSecondsLabels() {

	return getSecondsValues();

    }

    public Integer[] getSeconds() {

	Integer[] list = new Integer[seconds.size()];

	if (seconds != null) {

	    int i = 0;

	    for (Iterator it = seconds.iterator(); it.hasNext(); i++) {

		list[i] = (Integer) it.next();

	    }

	}

	return list;

    }

    public void setSeconds(Integer[] val) {

	if (seconds != null) {
	    seconds.clear();
	} else {
	    seconds = new TreeSet();
	}

	for (Integer element : val) {

	    seconds.add(element);

	}

    }

    public Integer[] getMinutesValues() {

	Integer[] list = new Integer[60];

	for (int i = 0; i < 60; i++) {

	    list[i] = new Integer(i);

	}

	return list;

    }

    public Integer[] getMinutesLabels() {

	return getMinutesValues();

    }

    public Integer[] getMinutes() {

	Integer[] list = new Integer[minutes.size()];

	if (minutes != null) {

	    int i = 0;

	    for (Iterator it = minutes.iterator(); it.hasNext(); i++) {

		list[i] = (Integer) it.next();

	    }

	}

	return list;

    }

    public void setMinutes(Integer[] val) {

	if (minutes != null) {
	    minutes.clear();
	} else {
	    minutes = new TreeSet();
	}

	for (Integer element : val) {

	    minutes.add(element);

	}

    }

    public Integer[] getHoursValues() {

	Integer[] list = new Integer[24];

	for (int i = 0; i < 24; i++) {

	    list[i] = new Integer(i);

	}

	return list;

    }

    public String[] getHoursLabels() {

	String[] vals = { "12AM (Midnight)", "1AM", "2AM", "3AM", "4AM", "5AM", "6AM", "7AM", "8AM", "9AM", "10AM",
		"11AM", "12PM (Noon)", "1PM", "2PM", "3PM", "4PM", "5PM", "6PM", "7PM", "8PM", "9PM", "10PM", "11PM" };

	return vals;

    }

    public Integer[] getHours() {

	Integer[] list = new Integer[hours.size()];

	if (hours != null) {

	    int i = 0;

	    for (Iterator it = hours.iterator(); it.hasNext(); i++) {

		list[i] = (Integer) it.next();

	    }

	}

	return list;

    }

    public void setHours(Integer[] val) {

	if (hours != null) {
	    hours.clear();
	} else {
	    hours = new TreeSet();
	}

	for (Integer element : val) {

	    hours.add(element);

	}

    }

    public Integer[] getDaysOfMonthValues() {

	Integer[] list = new Integer[31];

	for (int i = 0; i < 31; i++) {

	    list[i] = new Integer(i + 1);

	}

	return list;

    }

    public Integer[] getDaysOfMonthLabels() {

	return getDaysOfMonthValues();

    }

    public Integer[] getDaysOfMonth() {

	Integer[] list = new Integer[daysOfMonth.size()];

	if (daysOfMonth != null) {

	    int i = 0;

	    for (Iterator it = daysOfMonth.iterator(); it.hasNext(); i++) {

		list[i] = (Integer) it.next();

	    }

	}

	return list;

    }

    public void setDaysOfMonth(Integer[] val) {

	if (daysOfMonth != null) {
	    daysOfMonth.clear();
	} else {
	    daysOfMonth = new TreeSet();
	}

	for (Integer element : val) {

	    daysOfMonth.add(element);

	}

	daysOfWeek.clear();

	daysOfWeek.add(NO_SPEC);

    }

    public Integer[] getMonthsValues() {

	Integer[] list = new Integer[12];

	for (int i = 0; i < 12; i++) {

	    list[i] = new Integer(i + 1);

	}

	return list;

    }

    public String[] getMonthsLabels() {

	String[] vals = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
		"October", "November", "December" };

	return vals;

    }

    public Integer[] getMonths() {

	Integer[] list = new Integer[months.size()];

	if (months != null) {

	    int i = 0;

	    for (Iterator it = months.iterator(); it.hasNext(); i++) {

		list[i] = (Integer) it.next();

	    }

	}

	return list;

    }

    public void setMonths(Integer[] val) {

	if (months != null) {
	    months.clear();
	} else {
	    months = new TreeSet();
	}

	for (Integer element : val) {

	    months.add(element);

	}

    }

    public String[] getDaysOfWeekLabels() {

	String[] list = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

	return list;

    }

    public Integer[] getDaysOfWeekValues() {

	Integer[] list = new Integer[7];

	for (int i = 0; i < 7; i++) {
	    list[i] = new Integer(i + 1);
	}

	return list;

    }

    public Integer[] getDaysOfWeek() {

	Integer[] list = new Integer[daysOfWeek.size()];

	if (daysOfWeek != null) {

	    int i = 0;

	    for (Iterator it = daysOfWeek.iterator(); it.hasNext(); i++) {

		list[i] = (Integer) it.next();

	    }

	}

	return list;

    }

    public void setDaysOfWeek(Integer[] val) {

	if (daysOfWeek != null) {
	    daysOfWeek.clear();
	} else {
	    daysOfWeek = new TreeSet();
	}

	for (Integer element : val) {

	    daysOfWeek.add(element);

	}

	daysOfMonth.clear();

	daysOfMonth.add(NO_SPEC);

    }

    public Integer[] getYearsValues() {

	Integer[] list = new Integer[20];

	Calendar now = Calendar.getInstance();

	int year = now.get(Calendar.YEAR);

	for (int i = 0; i < 20; i++) {

	    list[i] = new Integer(i + year);

	}

	return list;

    }

    public Integer[] getYearsLabels() {

	return getYearsValues();

    }

    public Integer[] getYears() {

	Integer[] list = new Integer[years.size()];

	if (years != null) {

	    int i = 0;

	    for (Iterator it = years.iterator(); it.hasNext(); i++) {

		list[i] = (Integer) it.next();

	    }

	}

	return list;

    }

    public void setYears(Integer[] val) {

	if (years != null) {
	    years.clear();
	} else {
	    years = new TreeSet();
	}

	for (Integer element : val) {

	    years.add(element);

	}

    }

    public static void main(String[] argv) {

	CronTrigger ct = new CronTriggerImpl("a", "a");

	try {

	    ((CronTriggerImpl) ct).setCronExpression("0 * * * * ? *");

	} catch (ParseException e) {

	}

	((CronTriggerImpl) ct).setStartTime(new Date());

	((CronTriggerImpl) ct).setTimeZone(TimeZone.getDefault());

	System.out.println(ct.getExpressionSummary());

	((CronTriggerImpl) ct).computeFirstFireTime(null);

	UICronTrigger uict = new UICronTrigger("a", "a");

	Integer[] set = new Integer[1];

	set[0] = new Integer(1);

	uict.setSeconds(set);

	System.out.println(ct.getExpressionSummary());

	uict.computeFirstFireTime(null);

    }

	public TriggerKey getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	public JobKey getJobKey() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCalendarName() {
		// TODO Auto-generated method stub
		return null;
	}

	public JobDataMap getJobDataMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getMisfireInstruction() {
		// TODO Auto-generated method stub
		return 0;
	}

	public TriggerBuilder<? extends Trigger> getTriggerBuilder() {
		// TODO Auto-generated method stub
		return null;
	}

	public ScheduleBuilder<? extends Trigger> getScheduleBuilder() {
		// TODO Auto-generated method stub
		return null;
	}

	public int compareTo(Trigger other) {
		// TODO Auto-generated method stub
		return 0;
	}

}

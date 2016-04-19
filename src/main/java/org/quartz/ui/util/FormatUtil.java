package org.quartz.ui.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.commons.collections.CollectionUtils;

public class FormatUtil {

	public static final String DATE_FORMAT_PATTERN = "yy.MM.dd hh:mm";
	static SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT_PATTERN);

	public static void removeNull(List list) {
		Collection nullCon = new Vector();
		nullCon.add(null);
		list.removeAll(nullCon);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateAsString(Date date) {
		if (date == null) {
			return null;
		}
		return dateFormatter.format(date);
	}

	/**
	 * 
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static Date parseStringToDate(String dateStr) throws ParseException {
		if (dateStr == null) {
			return null;
		}
		return dateFormatter.parse(dateStr);
	}

}

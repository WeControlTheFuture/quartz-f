/* 
 * Copyright 2004-2005 OpenSymphony 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not 
 * use this file except in compliance with the License. You may obtain a copy 
 * of the License at 
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0 
 *   
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
 * License for the specific language governing permissions and limitations 
 * under the License.
 * 
 */

/*
 * Previously Copyright (c) 2001-2004 James House
 */
package org.quartz.ui.example;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * <p>
 * An implementation of Job, that does absolutely nothing - useful for system
 * which only wish to use <code>{@link org.quartz.TriggerListener}s</code> and
 * <code>{@link org.quartz.JobListener}s</code>, rather than writing Jobs that
 * perform work.
 * </p>
 * 
 * @author James House
 */
@DisallowConcurrentExecution
public class NoOpJob implements Job {

	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * 
	 * Constructors.
	 * 
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */

	public NoOpJob() {
	}

	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * 
	 * Interface.
	 * 
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */

	/**
	 * <p>
	 * Do nothing.
	 * </p>
	 */
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		/*JobDataMap datamap = context.getMergedJobDataMap();
		Integer i = (Integer) datamap.get("counter");
		i = (i == null) ? 100 : i;
		while (i > 0) {
			try {
				Thread.sleep(1000);
				System.out.println(this+"========================"+i+"+++++"
						+ System.currentTimeMillis());
				i--;
				datamap.put("counter",i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(this+"========================"+1+"+++++"
				+ System.currentTimeMillis());
	}

}
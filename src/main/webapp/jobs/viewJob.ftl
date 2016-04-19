<h1><@s.text name="title.viewJob"/></h1>
<form name="jobDetailForm" method="post" action="viewJobDetail.do">
<table>
	<tr>
		<td><@s.text name="label.job.group"/></td>
		<td><input type="hidden" name="groupName" value="${jobSchedulingData.schedule.job.group}"/>${jobSchedulingData.schedule.job.group}</td>
	</tr>
	<tr>
		<td><@s.text name="label.job.name"/></td>
		<td><input type="hidden" name="name" value="${jobSchedulingData.schedule.job.name}">${jobSchedulingData.schedule.job.name}</td>
	</tr>
	<tr>
		<td><@s.text name="label.job.jobClass"/></td>
		<td>${jobSchedulingData.schedule.job.jobClass}</td>
	</tr>
	<tr>
		<td><@s.text name="label.job.description"/></td>
		<td>${jobSchedulingData.schedule.job.description!}</td>
	</tr>
	<tr>
		<td><@s.text name="label.job.recover"/></td>
		<td><input type="checkbox" name="jobSchedulingData.schedule.job.recover" value="true"></td>
	</tr>
	<tr>
		<td><@s.text name="label.job.durable"/></td>
		<td><input type="checkbox" name="jobSchedulingData.schedule.job.durability" value="true" ></td>
	</tr>
</table>

<h3><@s.text name="title.viewJob.listeners"/></h3>
<table>
</table>

<h3><@s.text name="title.viewJob.variables"/></h3>
<table>
	<table>

	<tr>
		<td><@s.text name="label.job.variable.key"/></td>
		<td><@s.text name="label.job.variable.value"/></td>
	</tr>
	<#list jobSchedulingData.schedule.job.jobDataMap.entry as entry>
	<tr>
		<td>${entry.key}</td>
		<td>${entry.value}</td>
	</tr>
	</#list>
</table>
<h3><@s.text name="title.viewJob.triggers"/></h3>
<table>
	<tr>
		<td><em><@s.text name="label.global.actions"/></em></td>
		<td><@s.text name="label.trigger.group"/></td>
		<td><@s.text name="label.trigger.name"/></td>
		<td><@s.text name="label.trigger.type"/></td>
		<td><@s.text name="label.trigger.nextFireTime"/></td>
	</tr>
	<#list jobSchedulingData.schedule.trigger as trigger>
	<tr>
		<td><a href="${base}/schedule/unschedule.action?jobName=${jobSchedulingData.schedule.job.name}&jobGroup=${jobSchedulingData.schedule.job.group!}&triggerGroup=${trigger.triggerGroup!}&triggerName=${trigger.triggerName!}"><@s.text name="label.global.unschedule"/></a></td>	
		<td>${trigger.triggerGroup}</td>	
		<td>${trigger.triggerName}</td>	
		<td>${trigger.type}</td>	
		<td>${trigger.nextFireTime}</td>	
		</tr>
	</#list>
</table>
<h3><@s.text name="label.global.actions"/></h3>
 &nbsp;<a href="${base}/jobs/editJob.action?jobName=${jobSchedulingData.schedule.job.name}&jobGroup=${jobSchedulingData.schedule.job.group}"><@s.text name="label.global.edit"/></a>
 &nbsp;<a href="${base}/jobs/deleteJob.action?jobName=${jobSchedulingData.schedule.job.name}&jobGroup=${jobSchedulingData.schedule.job.group}"><@s.text name="label.global.delete"/></a>
 &nbsp;<a href="${base}/jobs/executeJob.action?jobName=${jobSchedulingData.schedule.job.name}&jobGroup=${jobSchedulingData.schedule.job.group}"><@s.text name="label.global.executenow"/></a>
 &nbsp;<a href="${base}/schedule/startSchedule.action?jobName=${jobSchedulingData.schedule.job.name}&jobGroup=${jobSchedulingData.schedule.job.group}"><@s.text name="label.global.schedule"/></a>
 &nbsp;<a href="${base}/schedule/startCronSchedule.action?jobName=${jobSchedulingData.schedule.job.name}&jobGroup=${jobSchedulingData.schedule.job.group}"><@s.text name="label.global.schedule"/> cron</a>
 &nbsp;<a href="${base}/schedule/startUICronSchedule.action?jobName=${jobSchedulingData.schedule.job.name}&jobGroup=${jobSchedulingData.schedule.job.group}"><@s.text name="label.global.schedule"/> UI</a>
</form>

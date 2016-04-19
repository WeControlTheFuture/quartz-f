<h1><@s.text name="title.viewJob"/></h1>
<form name="jobDetailForm" method="post" action="viewJobDetail.do">
<table>
	<tr>
		<td><@s.text name="任务组"/></td>
		<td><input type="hidden" name="groupName" value="${job.jobGroup}"/>${job.jobGroup}</td>
	</tr>
	<tr>
		<td><@s.text name="label.job.name"/></td>
		<td><input type="hidden" name="name" value="${job.jobName}">${job.jobName}</td>
	</tr>
	<tr>
		<td><@s.text name="label.job.jobClass"/></td>
		<td>${job.jobClassName}</td>
	</tr>
	<tr>
		<td><@s.text name="label.job.description"/></td>
		<td>${job.jobDisc!}</td>
	</tr>	
</table>
<h1><@s.text name="title.viewJob.triggers"/></h1>
<table>
     <tr>
		<td><@s.text name="触发器名"/></td>
		<td>
		<#if cronTrigger.triggerName?exists>
		<input type="hidden" name="groupName" value="${cronTrigger.triggerName}"/>${cronTrigger.triggerName}
	    <#else>  
		<input type="hidden" name="schedName" value="${simpleTrigger.triggerName}"/>${simpleTrigger.triggerName}
	   </#if>
		</td>
	</tr>
	<tr>
		<td><@s.text name="调度名"/></td>
		<td>
		<#if cronTrigger.schedName?exists>
		<input type="hidden" name="schedName" value="${cronTrigger.schedName}"/>${cronTrigger.schedName}
		<#else>  
		<input type="hidden" name="schedName" value="${simpleTrigger.schedName}"/>${simpleTrigger.schedName}
		</#if>
		</td>
	</tr>
	<tr>
		<td><@s.text name="触发器表达式"/></td>
		<td>
		<#if cronTrigger.cronExpression?exists>
		<input type="hidden" name="name" value="${cronTrigger.cronExpression}">${cronTrigger.cronExpression}
		</#if>	
		</td>
	</tr>
	<tr>
		<td><@s.text name="触发器组"/></td>
		
		<td> 
		<#if cronTrigger.triggerGroup?exists>
		${cronTrigger.triggerGroup}
		<#else>  
		<input type="hidden" name="schedName" value="${simpleTrigger.triggerGroup}"/>${simpleTrigger.triggerGroup}
		</#if>	
		
		</td>
	</tr>
	
</table>

</form>
		

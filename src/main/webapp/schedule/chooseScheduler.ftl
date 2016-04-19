<html>
	<head>
		<title>Choose Schedulers</title>
		    <script type="text/javascript" src="${base}/include/js/jquery/tooltip/lib/jquery.bgiframe.js" ></script>
	 		<link rel=stylesheet type=text/css href='${base}/include/js/jquery/tooltip/jquery.tooltip.css'/>
    		<script type="text/javascript" src="${base}/include/js/jquery/tooltip/jquery.tooltip.min.js"></script>
		<script language="JavaScript">
			 $().ready(function() { 
			 	
			 	$("#controls > button").click(
			 		function() {
						$("#command").attr("value", this.value);
						$('#frmChooseScheduler').submit();
					}
				);	

				$('#controls > button').tooltip();
			  }); 
		</script>
	</head>
<#if scheduleInfo.scheduler?exists>	
<#assign scheduler = scheduleInfo.scheduler />
</#if>
<form id="frmChooseScheduler" name="chooseSchedulerForm" method="get" action="${base}/schedule/scheduleControl.action">
<input type="hidden" id="command" name="command" value=""/>
<table>
	<tr>
		<td width="200"><@s.text name="label.scheduler"/></td>
		<td>
		<select id="schedulerName" name="schedulerName" onchange="submit()">
			 <#list scheduleInfo.schedulers as schedule>
				<option id="${schedule.schedulerName}" value="${schedule.schedulerName}">${schedule.schedulerName}</option>
			</#list>
		</select>
		</td>
	</tr>
<#if scheduleInfo.scheduler?exists>	
	<tr>
		<td><@s.text name="label.scheduler.schedulerName"/></td><td>${(scheduler.schedulerName)!}</td>
	</tr>
	<tr>
		<td><@s.text name="label.scheduler.state"/></td><td><@s.text name="${(scheduler.state)!}"/></td>
	</tr>
	<tr>
		<td><@s.text name="label.scheduler.runningSince"/></td><td>${(scheduler.runningSince)!}</td>
	</tr>
	<tr>
		<td><@s.text name="label.scheduler.numJobsExecuted"/></td><td>${(scheduler.numJobsExecuted)!}</td>
	</tr>
	<tr>
		<td><@s.text name="label.scheduler.persistenceType"/></td><td><@s.text name="${(scheduler.persistenceType)!}"/></td>
	</tr>
	<tr>
		<td><@s.text name="label.scheduler.threadPoolSize"/></td><td>${(scheduler.threadPoolSize)!}</td>
	</tr>
	<tr>
		<td><@s.text name="label.scheduler.version"/></td><td>${scheduler.version!}</td>
	</tr>
</#if>	
</table>	
	<span id="controls">
	<button name="play" value="start" type="submit" title="<@s.text name="hint.scheduler.start"/>"><img type="image"  value="start" src="${base}/icons/Play24.gif" alt="Start Scheduler" title="Start Scheduler" /></button>
	<button name="pause" value="pause" type="submit" title="<@s.text name="hint.scheduler.pause"/>"><img type="image" value="pause" src="${base}/icons/Pause24.gif"  alt="Pause Scheduler"  /></button> 
	<button name="stop" value="stop" type="submit" title="<@s.text name="hint.scheduler.stop"/>"><img type="image" value="stop" src="${base}/icons/Stop24.gif"  alt="Stop Scheduler"  /></button>
	<button name="waitAndStop" value="waitAndStopScheduler" type="submit"  title="<@s.text name="hint.scheduler.waitAndStopScheduler"/>"><img type="image" value="waitAndStopScheduler" src="${base}/icons/Stop24.gif"  /> &nbsp;</button>
	</span>
	<br/>
<@s.text name="title.chooseScheduler.setCurrentScheduler"/>: <input type="submit" class="submit" value="set" property="btnSetSchedulerAsCurrent"/>
</form>
<hr/>
<@s.text name="title.chooseScheduler.executingJobs"/>
<table><tr>
<td><@s.text name="label.job.group"/></td>
<td><@s.text name="label.job.name"/></td>
<td><@s.text name="label.job.description"/></td>
<td><@s.text name="label.job.jobClass"/></td>
</tr>
<#if scheduleInfo.executingJobs?exists>
 <#list scheduleInfo.executingJobs as job>
	<tr>
		<td>${job.groupName}</td>
		<td>${job.name}</td>
		<td>${job.description}</td>
		<td>${job.jobClass}</td>
	</tr>
</#list>
</#if>
</table>
<table>
	<tr>
		<td width="30">
			<img src="${base}/icons/Pause24.gif" value="btnPauseAllJobs" alt="Pause all jobs"/>
		</td>
		<td width="30">
			<img src="${base}/icons/Play24.gif" value="btnResumeAllJobs" alt="Resume all jobs"/>
		</td>
	</tr>
</table>
<hr/>
<p><@s.text name="label.scheduler.summary"/>: <i><pre>${(scheduler.summary)!}</pre></i></p>
</html>
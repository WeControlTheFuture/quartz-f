<html>
	<head>
		<title>Choose Schedulers</title>
		    <link rel="stylesheet" href="${base}/include/js/jquery/tablesorter/themes/blue/style.css" type="text/css" media="print, projection, screen" />
		 <script type="text/javascript" src="${base}/include/js/jquery/tablesorter/jquery.tablesorter.min.js"></script>
<script type="text/javascript">
$(document).ready( function() {
	 $("#schedulerTable").tablesorter({
	 		 widgets: ['zebra'],
	        	// assign the secound column (we start counting zero) 
            	0: { 
                	sorter: false 
            	} 
            }
   	});
	 $("#command").focus(); 	
});
 </script>
	</head>
	<#if scheduleInfo.scheduler?exists>	
<#assign scheduler = scheduleInfo.scheduler />
</#if>
<br />
<form id="frmChooseScheduler" name="chooseSchedulerForm" method="get" action="${base}/schedule/scheduleControl.action">
<input type="hidden"  id="command" name="command" value="${command!}" />
	<select id="schedulerName" name="schedulerName" onchange="submit()">
			 <#list scheduleInfo.schedulers as schedule>
				<option id="${schedule.schedulerName}" value="${schedule.schedulerName}">${schedule.schedulerName}</option>
			</#list>
		</select>
		</td>
<br />
</form>
<table id="schedulerTable" cellspacing="0" cellpadding="3" class="tablesorter">
    <thead>
        <th><@s.text name="label.scheduler.schedulerName"/></th>
        <th><@s.text name="label.scheduler.state"/></th>
        <th><@s.text name="label.scheduler.runningSince"/></th>
        <th><@s.text name="label.scheduler.numJobsExecuted"/></th>  
        <th><@s.text name="label.scheduler.persistenceType"/></th>
        <th><@s.text name="label.scheduler.threadPoolSize"/></th>
        <th><@s.text name="label.scheduler.version"/></th>
    </thead><tbody>
	<tr>
		
	
	</tr>
<#if scheduleInfo.scheduler?exists>	
<#list scheduleInfo.schedulers as schedule>	
		<td>${(scheduler.schedulerName)!}</td>
		<td>${(scheduler.state)!}</td>
		<td>${(scheduler.runningSince)!}</td>
		<td>${(scheduler.numJobsExecuted)!}</td>
		<td>${(scheduler.persistenceType)!}</td>
		<td>${(scheduler.threadPoolSize)!}</td>
		<td>${scheduler.version!}</td>
 	</#list>
</#if>	
</table>	
	<br/>

</html>
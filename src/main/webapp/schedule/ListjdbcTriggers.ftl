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
	        	 headers: { 
            	8: { 
                	sorter: false 
            	} ,
            	9: {sorter: false }
            	}
   	});
	 $("#command").focus(); 	
});

function stopTrigger(jobName,triggerName,type)
{
		var data = "jobName="+jobName+"&";
		if(type=='CRON')
			data += "trigger.cron.name="+triggerName;
		else if(type=='SIMPLE')
			data += "trigger.simple.name="+triggerName;
		$.ajax({
			type:"post",
			url:"stopTrigger.action",
			data:data,
			success:function(msg)
			{
				alert("该触发器已经停止！");
			    location.reload();
				//window.location.href="${base}/schedule/ListjdbcTriggers.action";
				//alert(msg);
			}
		});
}
 </script>
	</head>
	任务名称:
<br />
<form name="JobSearchForm" method="post" action="ListjdbcTriggers.action"><input type="text"
	id="jobSearchName" name="jobSearchName" value="${jobSearchName!}" /> 
	<input type="submit" value="查询"  name="jobSearchSubmit" />	
</form>
<br />
	<#if triggerInfos?exists>	
</#if>
<br />
<table id="triggerTable" cellspacing="0" cellpadding="3" class="tablesorter">
    <thead>
     <tr>
     	<th><@s.text name="任务名称"/></th> 
        <th><@s.text name="触发器名"/></th> 
        <th><@s.text name="描述"/></th>
        <th><@s.text name="类型"/></th>
        <th><@s.text name="上次执行时间"/></th> 
        <th><@s.text name="下次执行时间"/></th> 
        <th><@s.text name="运行状态"/></th>    
        <th><em><@s.text name="label.global.stop"/></em></th>     
	</tr>
   </thead><tbody> 
<#list triggerInfos as trigger>	
 <#if trigger_index % 2 == 0><tr ><#else><tr></#if>
	    <td>${(trigger.jobName)!}</td>	
		<td>${(trigger.triggerName)!}</td>		
		<td>${(trigger.des)!}</td>	
		<td>${(trigger.triggerType)!}</td>	
		<td>${(trigger.preFireTime)?number_to_datetime!}</td>	
		<td>${(trigger.nextFireTime)?number_to_datetime!}</td>	
		<td>${(trigger.triggerState)!}</td>		
		<td>
        	<button name="play" value="stop" onclick="stopTrigger('${trigger.jobName}','${trigger.triggerName}','${trigger.triggerType}')"><img type="image" value="stop" src="${base}/icons/Stop24.gif" alt="Stop Scheduler"/></button>
        </td>		
 	</#list>
  </tbody>
    </table>
</body>
	<br/>
</html>
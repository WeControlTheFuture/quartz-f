<html>
<head>
		<title><@s.text name="title.definitions.heading"/></title>
	    <link rel="stylesheet" href="${base}/include/js/jquery/tablesorter/themes/blue/style.css" type="text/css" media="print, projection, screen" />
		 <script type="text/javascript" src="${base}/include/js/jquery/tablesorter/jquery.tablesorter.min.js"></script>
<script type="text/javascript">
$(document).ready( function() {
	 $("#cronTable").tablesorter({
	 		 widgets: ['zebra'],
	 		  headers: { 
            	4: { sorter: false },
            	5: { sorter: false }
            }
		});
	 $("#intervalTable").tablesorter({
	 		 widgets: ['zebra'],
	 		  headers: { 
            	5: { sorter: false },
            	6: { sorter: false }
            }
		});
	$("#simpleTable").tablesorter({
	 		 widgets: ['zebra'],
	 		  headers: { 
            	5: { sorter: false },
            	6: { sorter: false }
            }
		});
});
function start(jobName,triggerName)
{
	$.ajax({
		type:"post",
		url:"trigger!start.action",
		data:"jobName="+jobName+"&"+triggerName,
		success:function(msg)
		{
			alert("触发器已经启动，请到运行信息和运行历史中查看！");
		}
	});
}
 </script>
</head>
<body>
<h3><@s.text name="title.schedule.cron"/></h3>
<a href="${base}/triggers/trigger!create.action?jobName=${jobName}&jobGroupName=${jobGroupName}&type=CRON"><@s.text name="label.create.definition"/></a>
<table id="cronTable" cellspacing="0" cellpadding="3" width="100%"  class="tablesorter">
    <thead>
    <tr>
        <th><@s.text name="label.definitions.name"/></th>
        <th><@s.text name="label.trigger.name"/></th>
        <th><@s.text name="label.trigger.description"/></th>
        <th><@s.text name="label.trigger.cronExpression"/></th>
        <!--<th><@s.text name="label.definitions.params"/></th> -->
        <th><em><@s.text name="label.definitions.actions"/></em></th>   
        <th><em><@s.text name="label.global.start"/></em></th>
   </tr>
   </thead><tbody>
   <#assign cron=triggers["CRON"] >
    <#list cron as trigger>
    <#if trigger_index % 2 == 0><tr ><#else><tr></#if>
        <td>${jobName}</td>
        <td>${trigger.cron.name}</td>
        <td>${trigger.cron.description!}</td>
        <td>${trigger.cron.cronExpression}</td>
       <!-- <td>
        	<#if trigger.cron.jobDataMap??>
	        	<#list trigger.cron.jobDataMap.entry as entry>
	        		${entry.key}----${entry.value}<br>
	        	</#list>
        	</#if>
        </td>-->
        <td  nowrap="true">
        <a href="${base}/triggers/trigger!edit.action?jobName=${jobName}&jobGroupName=${jobGroupName}&triggerName=${trigger.cron.name}&type=CRON"><@s.text name="label.global.edit"/></a>
        <a onclick="javascript:return confirm('一定要删除吗？');" href="${base}/triggers/trigger!delete.action?jobName=${jobName}&jobGroupName=${jobGroupName}&triggerName=${trigger.cron.name}"><@s.text name="label.global.delete"/></a>
        </td>
        <td>
        	<button name="play" value="start" onclick="start('${jobName}','trigger.cron.name=${trigger.cron.name}')"><img type="image" value="start" src="${base}/icons/Play24.gif" alt="Start Scheduler"/></button>
        </td>
    </tr>
    </#list>
    </tbody>
    </table>
 
    
   <h3><@s.text name="title.schedule.simple"/></h3>
<a href="${base}/triggers/trigger!create.action?jobName=${jobName}&jobGroupName=${jobGroupName}&type=SIMPLE"><@s.text name="label.create.definition"/></a>
<table id="simpleTable" cellspacing="0" cellpadding="3" width="100%"  class="tablesorter">
    <thead>
    <tr>
        <th><@s.text name="label.definitions.name"/></th>
        <th><@s.text name="label.trigger.name"/></th>
        <th><@s.text name="label.trigger.description"/></th>
        <th><@s.text name="label.trigger.repeatInterval"/></th>
        <th><@s.text name="label.trigger.repeatCount"/></th>
        <!--<th><@s.text name="label.definitions.params"/></th>  -->
        <th><em><@s.text name="label.definitions.actions"/></em></th>  
        <th><em><@s.text name="label.global.start"/></em></th> 
   </tr>
   </thead><tbody>
    <#assign interval=triggers["SIMPLE"] >
    <#list interval as trigger>
    <#if trigger_index % 2 == 0><tr ><#else><tr></#if>
        <td>${jobName}</td>
        <td>${trigger.simple.name}</td>
        <td>${trigger.simple.description!}</td>
        <td>${trigger.simple.repeatInterval!}</td>
        <td>${trigger.simple.repeatCount!}</td>
       <!-- <td>
        	<#if trigger.simple.jobDataMap??>
	        	<#list trigger.simple.jobDataMap.entry as entry>
	        		${entry.key}----${entry.value}<br>
	        	</#list>
        	</#if>
        </td>-->
        <td  nowrap="true">
        <a href="${base}/triggers/trigger!edit.action?jobName=${jobName}&jobGroupName=${jobGroupName}&triggerName=${trigger.simple.name}&type=SIMPLE"><@s.text name="label.global.edit"/></a>
        <a onclick="javascript:return confirm('一定要删除吗？');" href="${base}/triggers/trigger!delete.action?jobName=${jobName}&jobGroupName=${jobGroupName}&triggerName=${trigger.simple.name}"><@s.text name="label.global.delete"/></a>
        </td>
        <td>
        	<button name="play" value="start" onclick="start('${jobName}','trigger.simple.name=${trigger.simple.name}')"><img type="image"  value="start" src="${base}/icons/Play24.gif" alt="Start Scheduler"/></button>
        </td>
    </tr>
    </#list>
    </tbody>
    </table>
    
<h3><@s.text name="title.schedule.interval"/></h3>
<a href="${base}/triggers/trigger!create.action?jobName=${jobName}&jobGroupName=${jobGroupName}&type=INTERVAL"><@s.text name="label.create.definition"/></a>
<table id="intervalTable" cellspacing="0" cellpadding="3" width="100%"  class="tablesorter">
    <thead>
    <tr>
        <th><@s.text name="label.definitions.name"/></th>
        <th><@s.text name="label.trigger.name"/></th>
        <th><@s.text name="label.trigger.description"/></th>
        <th><@s.text name="label.trigger.repeatInterval"/></th>
        <th><@s.text name="label.trigger.repeatIntervalUnit"/></th>
       <!-- <th><@s.text name="label.definitions.params"/></th>  -->
        <th><em><@s.text name="label.definitions.actions"/></em></th>   
        <th><em><@s.text name="label.global.start"/></em></th>
   </tr>
   </thead><tbody>
    <#assign interval=triggers["INTERVAL"] >
    <#list interval as trigger>
    <#if trigger_index % 2 == 0><tr ><#else><tr></#if>
        <td>${jobName}</td>
        <td>${trigger.dateInterval.name}</td>
        <td>${trigger.dateInterval.description!}</td>
        <td>${trigger.dateInterval.repeatInterval!}</td>
        <td>${trigger.dateInterval.repeatIntervalUnit!}</td>
       <!-- <td>
	        <#if trigger.dateInterval.jobDataMap??>
	        	<#list trigger.dateInterval.jobDataMap.entry as entry>
	        		${entry.key}----${entry.value}<br>
	        	</#list>
        	</#if>
        </td>-->
        <td  nowrap="true">
        <a href="${base}/triggers/trigger!edit.action?jobName=${jobName}&jobGroupName=${jobGroupName}&triggerName=${trigger.dateInterval.name}&type=INTERVAL"><@s.text name="label.global.edit"/></a>
        <a onclick="javascript:return confirm('一定要删除吗？');" href="${base}/triggers/trigger!delete.action?jobName=${jobName}&jobGroupName=${jobGroupName}&triggerName=${trigger.dateInterval.name}"><@s.text name="label.global.delete"/></a>
        </td>
        <td>
        	<button name="play" value="start" onclick="start('${jobName}','trigger.dateInterval.name=${trigger.dateInterval.name}')"><img type="image"  value="start" src="${base}/icons/Play24.gif" alt="Start Scheduler"/></button>
        </td>
    </tr>
    </#list>
    </tbody>
    </table>
 
</body>
</html>
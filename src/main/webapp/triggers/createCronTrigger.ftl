<script type="text/javascript" src="${base}/include/js/jquery/jquery-1.11.1.min.js"></script>
<script language="JavaScript">
var paramUIIndex =<#if trigger.cron.jobDataMap??>${trigger.cron.jobDataMap.entry?size}<#else>0</#if>;

$(function() {
	for(var i=0;i<paramUIIndex;i++)
	{
        $("#imgRemove"+i).click(function(){
        	  $(this).parent().parent().remove();
        });
	}
	$("#cmdAddParameter").click(function(){
        $("#paramterHolder").append("<tr id='paramTr"+paramUIIndex+"'><td><input type='text' name='trigger.cron.jobDataMap.entry["+paramUIIndex+"].key'/></td><td><input type='text' name='trigger.cron.jobDataMap.entry["+paramUIIndex+"].value'/></td><td><img type='image' id='imgRemove"+paramUIIndex+"' src='${base}/icons/close20.gif'/></td></tr>");
        $("#imgRemove"+paramUIIndex).click(function(){
        	  $(this).parent().parent().remove();
        });
        paramUIIndex++;
    });
});
</script>
<form action="trigger!save.action">
<h1><@s.text name="title.schedule.cron"/></h1>
<input type="hidden" name="jobName" value="${jobName}" />
<input type="hidden" name="jobGroupName" value="${jobGroupName}" />
<input type="hidden" name="trigger.cron.jobName" value="${jobName}"/>
<input type="hidden" name="trigger.cron.jobGroup" value="${jobGroupName}"/>
<input type="hidden" name="trigger.cron.group" value="${jobName}TriggerGroup"/>
<table>
	<tr>
		<td><@s.text name="label.trigger.name"/></td>
		<td><input type="text" name="trigger.cron.name" value="${trigger.cron.name!}"/></td>
		<!--<td><i><@s.text name="hint.trigger.name"/></i></td>-->
	</tr>
	<tr>
		<td><@s.text name="label.trigger.description"/></td>
		<td><textarea rows="5" cols="50" name="trigger.cron.description">${trigger.cron.description!}</textarea></td>
	</tr>
	<tr>
		<td><@s.text name="label.trigger.cronExpression"/></td>
		<td><input type="text" name="trigger.cron.cronExpression" value="${trigger.cron.cronExpression!}"/></td>
		<!--<td><i><@s.text name="hint.trigger.cronExpression"/></i></td>-->
	</tr>
</table>
<!--<h3><@s.text name="title.createTrigger.params"/></h3>
<input type="button" class="submit" id="cmdAddParameter" value="<@s.text name="增加参数"/>"/>
<table  id="paramterHolder">	
	<tr>
		<td><@s.text name="label.job.parameter.name"/></td>
		<td><@s.text name="label.job.parameter.value"/></td>
		<td></td>
	</tr>
	<#if trigger.cron.jobDataMap??>
    <#list trigger.cron.jobDataMap.entry as entry>
	<tr>
		<td><input type="text" name="trigger.cron.jobDataMap.entry[${entry_index}].key" value="${entry.key}"/></td>
		<td><input type="text" name="trigger.cron.jobDataMap.entry[${entry_index}].value" value="${entry.value}"/></td>
		<td><img type='image' id='imgRemove${entry_index}' src='${base}/icons/close20.gif'/></td>
	</tr>
	</#list>
	</#if>
</table>-->
<input type="submit" value="<@s.text name="label.global.save"/>"/>
<form>
<#include "../schedule/cronExpressionHelp.htm"/>


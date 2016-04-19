<form action="cronSchedule.action">
<h3><@s.text name="title.schedule.cron"/></h3>
<input type="hidden" name="jobName" value="${jobName!}" />
<input type="hidden" name="jobGroup" value="${jobGroup!}"/>
<table>
	<tr>
		<td><@s.text name="label.trigger.group"/></td>
		<td><input type="text" name="triggerGroup" value="${triggerGroup}"/></td>
		<td><i><@s.text name="hint.trigger.group"/></i></td>
	</tr>
	<tr>
		<td><@s.text name="label.trigger.name"/></td>
		<td><input type="text" name="triggerName" value="${triggerName}"/></td>
		<td><i><@s.text name="hint.trigger.name"/></i></td>
	</tr>
	<tr>
		<td><@s.text name="label.trigger.description"/></td>
		<td><textarea rows="5" cols="50" name="description">${description}</textarea></td>
		<td></td>
	</tr>
	<tr>
		<td><@s.text name="label.trigger.startTime"/></td>
		<td><input type="text" name="startTime" value="${startTime}"/></td>
		<td><i><@s.text name="hint.trigger.startTime"/></i></td>
	</tr>
	<tr>
		<td><@s.text name="label.trigger.stopTime"/></td>
		<td><input type="text" name="stopTime" value="${stopTime}"/></td>
		<td><i><@s.text name="hint.trigger.stopTime"/></i></td>
	</tr>
	<tr>
		<td><@s.text name="label.trigger.cronExpression"/></td>
		<td><input type="text" name="cronExpression" value="${cronExpression}"/></td>
		<td><i><@s.text name="hint.trigger.cronExpression"/></i></td>
	</tr>
	<!--<tr>
		<td><@s.text name="label.trigger.timeZone"/></td>
		<td><input type="text" name="timeZone" value="$timeZone"/></td>
		<td><i><@s.text name="hint.trigger.timeZone"/></i></td>
	</tr>-->
<!--
    <tr>
        <td>volatility</td><td><input type="checkbox" name="volatility"/></td><td><i></i></td>
    </tr>
-->
</table>
<input type="submit" value="<@s.text name="label.global.schedule"/>"/>
<form>
<#include "cronExpressionHelp.htm"/>


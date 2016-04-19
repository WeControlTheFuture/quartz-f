<form name="jobDetailForm" method="post" action="trigger!save.action">
<h1><@s.text name="title.schedule.interval"/></h1>
	<input type="hidden" name="jobName" value="${jobName}">
	<input type="hidden" name="trigger.dateInterval.jobName" value="${jobName!}"/>
	<input type="hidden" name="trigger.dateInterval.group" value="${jobName!}TriggerGroup"/>
<table>
	<tr>
		<td><@s.text name="label.trigger.name"/></td>
		<td><input type="text" name="trigger.dateInterval.name" value="${trigger.dateInterval.name!}"/></td>
		<!--<td><i><@s.text name="hint.trigger.name"/></i></td>-->
	</tr>
	<tr>
		<td><@s.text name="label.trigger.description"/></td>
		<td><textarea rows="5" cols="50"  name="trigger.dateInterval.description">${trigger.dateInterval.description!}</textarea></td>
		<td></td>
	</tr>
	<tr>
		<td><@s.text name="label.trigger.startTime"/></td>
		<td><input type="text" name="trigger.dateInterval.startTime" value="${trigger.dateInterval.startTime!}"/></td>
		<!--<td><i><@s.text name="hint.trigger.startTime"/></i></td>-->
	</tr>
	<tr>
		<td><@s.text name="label.trigger.stopTime"/></td>
		<td><input type="text" name="trigger.dateInterval.stopTime" value="${trigger.dateInterval.stopTime!}"/></td>
		<!--<td><i><@s.text name="hint.trigger.stopTime"/></i></td>-->
	</tr>
	<tr>
		<td><@s.text name="label.trigger.repeatInterval"/></td>
		<td><input type="text" name="trigger.dateInterval.repeatInterval" value="${trigger.dateInterval.repeatInterval!}"/></td>
		<!--<td><i><@s.text name="hint.trigger.repeatInterval"/></i></td>-->
	</tr>
	<tr>
		<td><@s.text name="label.trigger.repeatIntervalUnit"/></td>
		<td><input type="text" name="trigger.dateInterval.repeatIntervalUnit" value="${trigger.dateInterval.repeatIntervalUnit!}"/></td>
		<!--<td><i><@s.text name="hint.trigger.repeatCount"/></i></td>-->
	</tr>
</table>

<input type="submit" value="<@s.text name="label.global.schedule"/>" />

</form>



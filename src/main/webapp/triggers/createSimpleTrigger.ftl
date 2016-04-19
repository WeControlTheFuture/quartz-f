<form name="jobDetailForm" method="post" action="trigger!save.action">
<h1><@s.text name="title.schedule.simple"/></h1>
	<input type="hidden" name="jobName" value="${jobName}">
	<input type="hidden" name="jobGroupName" value="${jobGroupName}" />
	<input type="hidden" name="trigger.simple.jobName" value="${jobName}"/>
	<input type="hidden" name="trigger.simple.jobGroup" value="${jobGroupName}"/>
	<input type="hidden" name="trigger.simple.group" value="${jobName}TriggerGroup"/>
<table>
	<tr>
		<td><@s.text name="label.trigger.name"/></td>
		<td><input type="text" name="trigger.simple.name" value="${trigger.simple.name!}"/></td>
		<!--<td><i><@s.text name="hint.trigger.name"/></i></td>-->
	</tr>
	<tr>
		<td><@s.text name="label.trigger.description"/></td>
		<td><textarea rows="5" cols="50"  name="trigger.simple.description">${trigger.simple.description!}</textarea></td>
		<td></td>
	</tr>
	<tr>
		<td><@s.text name="label.trigger.repeatInterval"/></td>
		<td><input type="text" name="trigger.simple.repeatInterval" value="${trigger.simple.repeatInterval!}"/></td>
		<!--<td><i><@s.text name="hint.trigger.repeatInterval"/></i></td>-->
	</tr>
	<tr>
		<td><@s.text name="label.trigger.repeatCount"/></td>
		<td><input type="text" name="trigger.simple.repeatCount" value="${trigger.simple.repeatCount!}"/></td>
		<!--<td><i><@s.text name="hint.trigger.repeatCount"/></i></td>-->
	</tr>
</table>

<input type="submit" value="<@s.text name="label.global.save"/>" />

</form>



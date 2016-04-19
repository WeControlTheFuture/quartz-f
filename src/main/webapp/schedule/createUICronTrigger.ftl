<#assign trig = trigger/>
<#assign daysOfWeekVals = trigger.daysOfWeekValues/>
<#assign dayLabels = trigger.daysOfWeekLabels/>

<form name="UICronTriggerForm" method="post" action="uiCronSchedule.action">
<h3><@s.text name="title.schedule.cron"/></h3>
<input type="hidden" name="jobName" value="${jobName}"/>
<input type="hidden" name="jobGroup" value="${jobGroup}"/>
<table>
      <tr class="listHeading"><td colspan="2">&nbsp;</td></tr>
    	<tr>
		<td><@s.text name="label.trigger.group"/></td>
		<td><input type="text" name="triggerGroup" value="${triggerGroup!}"/></td>
		<td><i><@s.text name="hint.trigger.group"/></i></td>
	</tr>
	<tr>
		<td><@s.text name="label.trigger.name"/></td>
		<td><input type="text" name="triggerName" value="${triggerName!}"/></td>
		<td><i><@s.text name="hint.trigger.name"/></i></td>
	</tr>
    <tr class="listHeading"><td colspan="2">&nbsp;</td></tr>
    <tr class="listRowUnshaded">
      <td width="80"><@s.text name="label.date"/></td>
      <td align="center">
	<select name="trigger.daysOfWeek" size="5" multiple="true" >
        <#list dayLabels as day>
            <option value="${day_index + 1}" <#if 1==2> selected="selected" </#if>>${day}</option>
        </#list>
    </select>   
	<select name="trigger.daysOfMonth" size="5" multiple="true" >
        <#list trig.daysOfMonthValues as day>
            <option value="${day_index+1}" <#if 1==2> selected="selected" </#if>>${day}</option>
        </#list>
    </select>   
	<select name="trigger.months" size="5" multiple="true" >
        <#list trigger.monthsLabels as month>
            <option value="${month_index+1}" <#if 1==2> selected="selected" </#if>>${month}</option>
        </#list>
    </select>       
      <select name="trigger.years" size="5" multiple="true" >
        <#list trigger.yearsLabels as year>
            <option value="${year?string("0000")}" <#if 1==2> selected="selected" </#if>>${year?string("0000")}</option>
        </#list>
    </select>                                                                                  
    </td>
    <td valign="top">
            <@s.text name="label.dayorweek"/>
    </td>
    </tr>
    <tr class="listRowShaded">
      <td width="80"><@s.text name="label.time"/></td>
      <td colspan="2" align="left">
	  <select name="trigger.hours" size="5" multiple="true"> 
		<#list trig.hoursLabels as label >
			<option value="$stack.findValue("trigger.hoursValues[${label_index}]")">${label}</option>
		</#list>
	  </select>
	  <select name="trigger.minutes" size="5" multiple="true"> 
		<#list trig.minutesLabels as label >
			<option value="$stack.findValue("trigger.minutesValues[${label_index}]")">${label}</option>
		</#list>
	   </select>	
	<#-- select( "trigger.minutes"   $trig.minutes $trig.minutesLabels $trig.minutesValues   )-->
      </td>
    </tr>
    </tr>
</table>
<input type="submit" value="<@s.text name="label.global.schedule"/>" name="scheduleAction"/>
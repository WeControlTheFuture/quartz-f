#set ($def = $jobDefinition)
<h1><@s.text name="title.createDefinitionJob"/></h1>
<form name="jobDetailForm" method="post" action="${req.contextPath}/jobs/saveDefinedJob.action">

<table>
	<tr>
		<td><@s.text name="label.job.group"/></td>
		<td><input type="text" name="jobDetail.group" value="${jobDetail.group!}"/></td>
	</tr>
	<tr>
		<td><@s.text name="label.job.name"/></td>
		<td><input type="text" name="jobDetail.name" value="${jobDetail.name!}"/> </td>
	</tr>
	<tr>
		<td><@s.text name="label.job.jobClass"/></td>
		<td><input type="text" name="className" size="27" value="${jobDefinition.className!}" /></td>
	</tr> 																			
	<tr>
		<td><@s.text name="label.job.description"/></td>
		<td><textarea name="jobDetail.description"><@s.text name="jobDefintions.description"/></textarea></td>
	</tr>
	<tr>
		<td><@s.text name="label.job.recover"/></td>
		<td><input type="checkbox" name="jobDetail.requestsRecovery" value="true" checked="true" /></td>
	</tr>
	<tr>
		<td><@s.text name="label.job.durable"/></td>
		<td><input type="checkbox" name="jobDetail.durability" value="true" checked="true" /></td>
	</tr>
</table>

<h3><@s.text name="title.createJob.listeners"/></h3>
<table>
</table>

<h3><@s.text name="title.createDefinitionJob.params"/></h3>
<table>
	<tr>
		<td><@s.text name="label.job.parameter.name"/></td>
		<td><@s.text name="label.job.parameter.value"/></td>
		<td><@s.text name="label.job.parameter.required"/></td>
	</tr>
<#assign params = def.parameters>
<#list params as p>
    <tr>
    	<td>${p.name}: <input type="hidden" name="parameterNames" value="${p.name}"/>  </td>
    	<td><input type="text" name="parameterValues"/></td>
    	<td>${p.required?string}</td>
    </tr>
</#list>
    <#list jobDetail.jobDataMap.keys as key>
	<tr>
		<td><input type="text" name="parameterNames" value="${key}"/></td>
		<td><input type="text" name="parameterValues" value="${jobDetail.jobDataMap.getString(key)}"/></td>
	</tr>
    </#list>
</table>
<input type="hidden" name="definitionName" value="${def.name}" />
<input type="submit"  class="submit" name="saveAction" value="<@s.text name="label.global.save"/>"/>
<form>



<script type="text/javascript" src="${base}/include/js/jquery/jquery-1.11.1.min.js"></script>
<script language="JavaScript">
var paramUIIndex =<#if jobSchedulingData.schedule.job.jobDataMap??>${jobSchedulingData.schedule.job.jobDataMap.entry?size}<#else>0</#if>;

$(function() {
	for(var i=0;i<paramUIIndex;i++)
	{
        $("#imgRemove"+i).click(function(){
        	  $(this).parent().parent().remove();
        });
	}
	$("#cmdAddParameter").click(function(){
        $("#paramterHolder").append("<tr id='paramTr"+paramUIIndex+"'><td><input type='text' name='jobSchedulingData.schedule.job.jobDataMap.entry["+paramUIIndex+"].key'/></td><td><input type='text' name='jobSchedulingData.schedule.job.jobDataMap.entry["+paramUIIndex+"].value'/></td><td><img type='image' id='imgRemove"+paramUIIndex+"' src='${base}/icons/close20.gif'/></td></tr>");
        $("#imgRemove"+paramUIIndex).click(function(){
        	  $(this).parent().parent().remove();
        });
        paramUIIndex++;
    });
});
</script>
<#if jobDefinition?exists>
<#assign def = jobDefinition/>
</#if>
<h1><@s.text name="title.createJob"/></h1>
<form name="jobDetailForm" method="post" action="saveJob.action">
<table >
	<tr>
		<td><@s.text name="label.job.name"/></td>
		<td><input type="text" style="width:200px;" name="jobSchedulingData.schedule.job.name" value="${jobSchedulingData.schedule.job.name!}"/>请输入英文名称。不支持直接修改名称，如果修改，则会新增一条！</td>
	</tr>
	<tr>
		<td><@s.text name="label.job.jobClass"/></td>
		
		<td><input type="text" style="width:400px;" name="jobSchedulingData.schedule.job.jobClass" value="${jobSchedulingData.schedule.job.jobClass!}"/></td>
	</tr> 																			<#-- put one or the other -->
	<tr>
		<td><@s.text name="label.job.description"/></td>
		<td><textarea style="width:400px;height:100px;" name="jobSchedulingData.schedule.job.description">${jobSchedulingData.schedule.job.description!}</textarea></td>
	</tr>
	<tr>
		<td><@s.text name="label.job.recover"/></td>
		<td><input type="checkbox" name="jobSchedulingData.schedule.job.recover" value="true" <#if jobSchedulingData.schedule.job.recover??><#if jobSchedulingData.schedule.job.recover=="true"> checked="true" </#if> </#if>/></td>
	</tr>
	<!--<tr>
		<td><@s.text name="label.job.durable"/></td>
		<td><input type="checkbox" name="jobSchedulingData.schedule.job.durability" value="true" <#if jobSchedulingData.schedule.job.durability??> <#if jobSchedulingData.schedule.job.durability=="true"> checked="true" </#if> </#if>  /></td>
	</tr>-->
</table>
<!--<h3><@s.text name="title.createJob.listeners"/></h3>-->
<!-- todo add job listener functionality -->
<h3><@s.text name="title.createDefinitionJob.params"/></h3>
<input type="button" class="submit" id="cmdAddParameter" value="<@s.text name="增加参数"/>"/>

<!--<tr>
	<td><input type="hidden" name="definitionName" value="${(definition.name)!}"/>
	<input id="parameter.name" type="text" name="parameter.name"/></td>
	<td><input type="text" id="parameter.value" name="parameter.value"/></td>
</tr>-->

<!--    增加参数-->
<table  id="paramterHolder">	
	<tr>
		<td><@s.text name="label.job.parameter.name"/></td>
		<td><@s.text name="label.job.parameter.value"/></td>
		<td></td>
	</tr>
	<#if jobSchedulingData.schedule.job.jobDataMap??>
    <#list jobSchedulingData.schedule.job.jobDataMap.entry as entry>
	<tr>
		<td><input type="text" name="jobSchedulingData.schedule.job.jobDataMap.entry[${entry_index}].key" value="${entry.key}"/></td>
		<td><input type="text" name="jobSchedulingData.schedule.job.jobDataMap.entry[${entry_index}].value" value="${entry.value}"/></td>
		<td><img type='image' id='imgRemove${entry_index}' src='${base}/icons/close20.gif'/></td>
	</tr>
	</#list>
	</#if>
</table>
<input type="submit"  class="submit" name="saveAction" value="<@s.text name="label.global.save"/>"/>
<input type="submit" class="submit" name="cancel" value="<@s.text name="取消"/>"/>
</form>
<table  style="display: none">
  <tr id="proto" >
    <td><input type="hidden" name="parameter.name"/>
	<input type="hidden" name="parameter.value"/>
	<input type="hidden" value="true" name="parameter.required"/></td>
    	<td></td>
    	<td><a href="#" onclick="removeParameter(this)"><@s.text name="label.global.remove"/></a></td>
    </tr>
</table>

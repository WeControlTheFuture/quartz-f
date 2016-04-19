<html>
<head>
		<title><@s.text name="title.definitions.heading"/></title>
	    <link rel="stylesheet" href="${base}/include/js/jquery/tablesorter/themes/blue/style.css" type="text/css" media="print, projection, screen" />
		 <script type="text/javascript" src="${base}/include/js/jquery/tablesorter/jquery.tablesorter.min.js"></script>
<script type="text/javascript">
$(document).ready( function() {
	 $("#definitionTable").tablesorter({
	 		 widgets: ['zebra'],
	 		  headers: { 
            	// assign the secound column (we start counting zero) 
            	4: { 
                	// disable it by setting the property sorter to false 
                	sorter: false 
            	} ,
            	5: {sorter: false}
            }
		});
});
 </script>
</head>
<body>
<!--<h1><@s.text name="title.definitions.heading"/></h1>-->
<!--<i><@s.text name="title.definitions.hint"/></i><br/>-->
任务名称:
<br />
<form name="JobSearchForm" method="post" action="list.action"><input type="text"
	id="searchName" name="searchName" value="${searchName!}" /> 
	<input type="submit" value="查询"  name="jobSearchSubmit" />
</form>
<a href="${base}/jobs/createJob.action"><@s.text name="label.create.definition"/></a>
<!--<a href="raw.action"><@s.text name="label.dump"/></a>-->

<table id="definitionTable" cellspacing="0" cellpadding="3" width="100%"  class="tablesorter">
    <thead>
    <tr>
        <th><@s.text name="label.definitions.name"/></th>
        <th><@s.text name="label.definitions.description"/></th>
        <th><@s.text name="label.definitions.class"/></th>
        <th><@s.text name="label.definitions.params"/></th>  
        <th class="{sorter: false}"><em><@s.text name="label.definitions.actions"/></em></th>   
        <th><@s.text name="label.definitions.triggers"/></th>   
   </tr>
   </thead><tbody>
    <#list definitions as def>
    <#if def_index % 2 == 0><tr ><#else><tr></#if>
        <td>${def.schedule.job.name}</td>
        <td>${def.schedule.job.description}</td>
        <td>${def.schedule.job.jobClass}</td>
        <td>
        	<#if def.schedule.job.jobDataMap??>
        	<#list def.schedule.job.jobDataMap.entry as entry>
        		${entry.key}----${entry.value}<br>
        	</#list>
        	</#if>
        </td>
        <td  nowrap="true">
        <!--<a href="${base}/jobs/createDefinedJob.action?method=start&definitionName=${def.schedule.job.name!}"><@s.text name="label.global.createJob"/></a>-->
        <a href="${base}/jobs/createJob!edit.action?jobName=${def.schedule.job.name!}"><@s.text name="label.global.edit"/></a>
        <a onclick="javascript:return confirm('<@s.text name="label.confirm.deleteDefinition"/>');" href="${base}/jobs/createJob!delete.action?jobName=${def.schedule.job.name!}"><@s.text name="label.global.delete"/></a>
        </td>
        <td nowrap="true">
        	<a href="${base}/triggers/trigger!list.action?jobName=${def.schedule.job.name!}&jobGroupName=${def.schedule.job.group!}"><@s.text name="label.global.edit"/></a>
        </td>
    </tr>
    </#list>
    </tbody>
    </table>
</body>
</html>
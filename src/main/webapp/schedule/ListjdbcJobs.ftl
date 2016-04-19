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
            	0: { 
                	sorter: false 
            	} 
            }
   	});
	 $("#command").focus(); 	
});
 </script>
	</head>
		任务名称:
<br />
<form name="JobSearchForm" method="post" action="ListjdbcJobs.action"><input type="text"
	id="jobSearchName" name="jobSearchName" value="${jobSearchName!}" /> 
	<input type="submit" value="查询"  name="jobSearchSubmit" />	
</form>
<br />
	<#if jobInfos?exists>	
</#if>
<br />
<table id="schedulerTable" cellspacing="0" cellpadding="3" class="tablesorter">
    <thead>
        <th><@s.text name="调度名"/></th>
        <th><@s.text name="任务名"/></th>
        <th><@s.text name="任务组"/></th>
        <th><@s.text name="任务类"/></th>
        <th><@s.text name="任务描述"/></th>
        <th><@s.text name="任务数据"/></th>
          
	<tr>
		
	
	</tr>
<#if jobInfos?exists>	
<#list jobInfos as job>	
<td nowrap="true">
        </td>
   <#if job_index % 2 == 0><tr ><#else><tr></#if>
		<td>${(job.schName)!}</td>
		<td>${(job.jobName)!}</td>
		<td>${(job.jobGroup)!}</td>
		<td>${(job.jobClassName)!}</td>
		<td>${(job.jobDisc)!}</td>
		<td>${(job.jobDate)!}</td>
		
 	</#list>
</#if>	
</table>	
	<br/>

</html>
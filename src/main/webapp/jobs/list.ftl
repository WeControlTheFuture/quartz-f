<html>
<head>
		<title>Jobs</title>
	    <link rel="stylesheet" href="${base}/include/js/jquery/tablesorter/themes/blue/style.css" type="text/css" media="print, projection, screen" />
		 <script type="text/javascript" src="${base}/include/js/jquery/tablesorter/jquery.tablesorter.min.js"></script>
<script type="text/javascript">
$(document).ready( function() {
	 $("#jobTable").tablesorter({
	 		 widgets: ['zebra'],
	 		 headers: { 
            	// assign the secound column (we start counting zero) 
            	0: { 
                	// disable it by setting the property sorter to false 
                	sorter: false 
            	} 
            }
   	});
	 $("#searchName").focus(); 	
	 //$("#summaryTable").tableHover(); 
});
 </script>
</head>
<body>
<h3><s:text name="title.listAllJobs" /></h3>
Find job(s) by name:
<br />
<form name="JobSearchForm" method="post"
	action="listJobs.action"><input type="text"
	id="searchName" name="searchName" value="${searchName!}" /> 
	<input type="submit" value="Search"  name="jobSearchSubmit" />
</form>
<br />
<br />
<table id="jobTable" cellspacing="0" cellpadding="3" class="tablesorter">
    <thead>
    <tr>
	<th class="{sorter: false}"><em><@s.text name="label.global.actions"/></em></th>
        <th><@s.text name="label.job.group"/></th>
        <th><@s.text name="label.job.name"/></th>
        <th><@s.text name="label.job.description"/></th>
        <th><@s.text name="label.job.jobClass"/></th>      
   </tr>
   </thead><tbody>
    <#list jobs as job>
    <#if job_index % 2 == 0><tr ><#else><tr></#if>
        <td nowrap="true">
        <a href='${base}/jobs/viewJob.action?jobName=${job.name!}&jobGroup=${job.group!}'><@s.text name="label.global.view" /></a> |
		<a href='${base}/jobs/editJob.action?jobName=${job.name!}&jobGroup=${job.group!}'><@s.text name="label.global.edit" /></a> |
		<a href='${base}/jobs/executeJob.action?jobName=${job.name!}&jobGroup=${job.group!}'><@s.text name="label.global.execute" /></a>
        </td>
        <td>${job.group!}</td>
        <td>${job.name!}</td>
        <td>${job.description!}</td>
        <td>${job.jobClass!}</td>
    </tr>
    </#list>
    </tbody>
    </table>
</body>
</html>
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
	

<form name="JobSearchForm" method="post" action="ListjdbcTriggersHis.action">
	任务名称:
	<input type ="text" id="jobHisName" name="jobHisName" value="${jobHisName!}" /> 
	触发器名称:
	<input type ="text" id="triggerHisName" name="triggerHisName" value="${triggerHisName!}" /> 
	<input type="submit" value="查询"  name="triggerSearchSubmit" />	
</form>

	

	<#if triggerHistorys?exists>	
</#if>
<br />
<table id="triggerTable" cellspacing="0" cellpadding="3" class="tablesorter">
    <thead>
     <tr>
     	<th><@s.text name="任务名称"/></th>
        <th><@s.text name="触发器名"/></th> 
        <th><@s.text name="描述"/></th>
         <th><@s.text name="类型"/></th>
        <th><@s.text name="上次执行时间"/></th> 
        <th><@s.text name="下次执行时间"/></th> 
        <th><@s.text name="运行状态"/></th>            
	</tr>
   </thead><tbody> 
<#list triggerHistorys as trigger>	
 <#if trigger_index % 2 == 0><tr ><#else><tr></#if>
   
      
        <td>${(trigger.jobName)!}</td>
		<td>${(trigger.triggerName)!}</td>		
		<td>${(trigger.des)!}</td>	
		<td>${(trigger.triggerType)!}</td>	
		<td>${(trigger.preFireTime)?number_to_datetime!}</td>	
		<td>${(trigger.nextFireTime)?number_to_datetime!}</td>	
		<td>${(trigger.triggerState)!}</td>				
 	</#list>
  </tbody>
    </table>
</body>
	<br/>
</html>
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
	<#if schedulelist?exists>	
</#if>
<br />
<table id="schedulerTable" cellspacing="0" cellpadding="3" class="tablesorter">
    <thead>
        <th><@s.text name="调度名"/></th>
        <th><@s.text name="实例名"/></th>
        <th><@s.text name="最新更新时间"/></th>
        <th><@s.text name="更新间隔(ms)"/></th>  
	<tr>
		
	
	</tr>
<#if schedulelist?exists>	
<#list schedulelist as shceduleinfo>	
   <#if shceduleinfo_index % 2 == 0><tr ><#else><tr></#if>
		<td>${(shceduleinfo.schedName)!}</td>
		<td>${(shceduleinfo.instanceName)!}</td>
		<td>${(shceduleinfo.lastCheckinTime)?number_to_datetime!}</td>
		<td>
		<#if shceduleinfo.checkinInterval==0>
		   未设置
		<#else>
		${(shceduleinfo.checkinInterval)!}
		</#if>	
		</td>
 	</#list>
</#if>	
</table>	
	<br/>

</html>
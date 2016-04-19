<#if session.getValue("User.SESSION_NAME")?exists>
	<#assign currentUser = session.getValue("User.SESSION_NAME")/>
</#if>

		<a href="${base}/schedule/index.action">首页</a><br/>
		
		<a href="${base}/schedule/ListSchedule.action">调度实例</a><br/>
		
		<a href="${base}/jobs/definition/list.action">任务管理</a><br/>
		
		<!--<a href="${base}/jobs/createJob.action">创建任务</a><br/>-->
		
		<!--<a href="${base}/schedule/ListjdbcJobs.action">任务列表</a><br/>-->
		
		<a href="${base}/schedule/ListjdbcTriggers.action">运行信息</a><br/>
	
	     <a href="${base}/schedule/ListjdbcTriggersHis.action">运行历史</a><br/>
	
	
	
	
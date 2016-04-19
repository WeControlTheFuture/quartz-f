<#--shead("themem=ajax")-->
<#if session.getValue("User.SESSION_NAME")?exists>
	<#assign currentUser = session.getValue("User.SESSION_NAME")/>
</#if>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td align="left"><a href="http://www.quartzscheduler.com/" target="_blank"><img src="${base}/icons/log.jpg"/></td>
		<td align="right" valign="bottom" nowrap >
		&nbsp;<span  class="noteMacro"><#if currentUser?exists>
			User: ${currentUser.username!} <a href="${base}/login/logout.action">[Sign out] </a>
			<#else> 
				<a href="${base}/logout.action">[Sign In]</a>
		    </#if></span>
		 </td>
	</tr>
	<tr><td height="10" width="145"  colspan="3" class="nav" >&nbsp; </td>
	</tr>
</table>
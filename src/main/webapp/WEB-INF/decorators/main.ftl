<html>
  <head>
   <#if request.getAttribute("struts.valueStack")?exists>
   	<#assign stack = request.getAttribute("struts.valueStack")/></#if>
  <#if stack?exists>
  	<#assign actionErrors = stack.findValue("actionErrors")/>
  	<#assign fieldErrors = stack.findValue("fieldErrors")/>
  </#if>
    <title>${title}</title>
    <link rel=stylesheet type=text/css href='${base}/include/style/default.css'/>
    <link rel=stylesheet type=text/css href='${base}/include/style/form.css'/>
    <script type="text/javascript" src="${base}/include/js/jquery/jquery-1.2.3.pack.js"></script>
    <script type="text/javascript" src="${base}/include/js/jquery/validate/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${base}/include/js/jquery/validate/jquery.validate.pml.js"></script>
    <script type="text/javascript" src="${base}/include/js/jquery/validate/jquery.tooltip.min.js"></script>
    ${head}
  </head>
  	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr><td><#include "/WEB-INF/decorators/head.ftl"/></td></tr>
			<tr><td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="145" height="100%" valign="top"><#include "/WEB-INF/decorators/nav.ftl"/></td>
						<td width="10">&nbsp;</td>
						<td><#if (actionMessages?exists && actionMessages.size() > 0)><#include "/WEB-INF/decorators/msg.ftl"/></#if>
						<#if (fieldErrors?exists && actionErrors?exists) && (fieldErrors.size() > 0 || actionErrors.size() > 0)>
							<#include "/WEB-INF/decorators/error_msg.ftl"/>
						</#if>
						${body}
						</td>
					</tr>
				</table>
			</td>
			</tr>
			<tr><td><#include "/WEB-INF/decorators/foot.ftl"/></td></tr>
		</table>
	</body>
</html>

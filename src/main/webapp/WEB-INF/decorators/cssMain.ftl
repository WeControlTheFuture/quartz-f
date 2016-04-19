<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head> 
   <title>${title}</title>
    <link rel=stylesheet type=text/css href='${base}/include/style/default.css'/>
    <link rel=stylesheet type=text/css href='${base}/include/style/layout.css'/>
    <link rel=stylesheet type=text/css href='${base}/include/style/form.css'/>
    <script type="text/javascript" src="${base}/include/js/jquery/jquery-1.2.3.pack.js"></script>
    <script type="text/javascript" src="${base}/include/js/jquery/validate/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${base}/include/js/jquery/validate/jquery.validate.pml.js"></script>
    ${head}
</head>
<body>

<h1>
<#include "/WEB-INF/decorators/head.ftl"/>
</h1>

<div id="leftbox">
	<#include "/WEB-INF/decorators/nav.ftl"/> 
</div>

<#if externalResultUrl?exists>
<div class="return">
		<a href="${externalResultUrl}"><strong>Return to mothership</strong></a>
</div>
</#if>

<div id="middlebox">
<#if (actionMessages?? && actionMessages.size() > 0)><#include "/WEB-INF/decorators/msg.ftl"/></#if>
<#if !(page.getProperty('meta.noerrors')?exists)>
		<#if (fieldErrors?? && actionErrors??) && (fieldErrors.size() > 0 || actionErrors.size() > 0)>
			<#include "/WEB-INF/decorators/error_msg.ftl"/>
		</#if>
</#if>
${body}
</div>

<div id="footer" ><#include "/WEB-INF/decorators/foot.ftl"/></div>
</body>
</html>
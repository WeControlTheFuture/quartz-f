<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    <title>触发器列表</title>
  </head>
<body>
<h1><s:text name="title.listAllTriggers"/></h1>
<s:set name="triggers" value="triggers" scope="request" />
<display:table name="triggers" class="simple" id="row" requestURI="listTriggers.action">
  <display:column  titleKey="label.global.actions" > 
	<s:url  id="joburl" value='/jobs/viewJob.action'>
   		   <s:param name="jobName">${row.jobName}</s:param>
   		   <s:param name="jobGroup">${row.group}</s:param>
   	</s:url>
	<a href='${joburl}'><s:text name="label.global.view"/></a>
  </display:column> 
  <display:column property="group" title="Job/Group" sortable="true" >
	  ${row.jobName} - ${row.jobGroup} 
  </display:column>
  <display:column property="group" titleKey="label.trigger.group" sortable="true"   />
  <display:column property="name" titleKey="label.trigger.name" sortable="true"  />
  <display:column property="description" titleKey="label.trigger.description" />
  <display:column property="class.name" titleKey="label.trigger.type" sortable="true"  />
  <display:column property="nextFireTime" titleKey="label.trigger.nextFireTime" sortable="true"  />
  <display:column property="startTime" titleKey="label.trigger.startTime" sortable="true"  />
  <display:column property="endTime" titleKey="label.trigger.stopTime" sortable="true"  />
  <display:column property="previousFireTime" titleKey="label.trigger.previousFireTime" sortable="true"  />
  <display:column property="misfireInstruction" titleKey="label.trigger.misFireInstruction" sortable="true"  />
</display:table>
</body>
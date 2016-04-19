<style type="text/css">
div.error{
	background: #ffffe1 url(${base}/icons/cancel.gif) 5px 5px no-repeat;
	border: #d6d8d6 1px solid;
	width: 90%;
	padding: 10px 5px 5px 30px;
	#font-size: 10px;
	display: block;
	#height: 45px;
	margin: 5px;
}
</style>
<#--<table>
	<tr>
		<th><decorator:title /></th>
	</tr>
	<tr>
		<td class="error"><#if (fieldErrors?exists && fieldErrors.size() > 0)>
		<p><font color="red"> <b>Field errors:</b><br />
		<ul>
			<#list fieldErrors as error>
			<li>${error}</li>
			</#list>
		</ul>
		</font></p>
		</#if>
		</td>
	</tr>
</table>-->
<#if (actionErrors?exists && actionErrors.size() > 0)>
		<div class="warningMacro">Some errors were found, please correct them.
		<ul>
			<#list actionErrors as error>
			<li>${error}</li>
			</#list>
		</ul>
		</div>
</#if>
<#if (fieldErrors?exists && fieldErrors.size() > 0)>
		<div class="warningMacro">Field Errors
		<ul>
			<#list fieldErrors.keySet() as errorKey>
			<li>Field: ${errorKey} 
				<ul>
					<#list fieldErrors.get(errorKey) as errorMsg>
						<li>${errorMsg}</li>
					</#list>					
				</ul>
			</li>
			</#list>
		</ul>
		</div>
</#if>


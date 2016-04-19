<html>
<body>	
<center>
<form id="loginForm" name="loginForm" action="authenticate.action" method="POST">
<table cellspacing="0" cellpadding="5" >
    <tr>
	<td bgcolor="#eeeeee"><em><@s.text name="label.username"/></em></td>
        <td bgcolor="#eeeeee"><input type="text" id="username" name="username" value="${username!}" /></td>
   </tr>
   <tr>
		<td bgcolor="#eeeeee"><em><@s.text name="label.password"/></em></td>
        <td bgcolor="#eeeeee"><input type="password" name="password"" /></td>
   </tr>
   <tr>
		<td bgcolor="#eeeeee"><input type="submit" value="<@s.text name="label.signin"/>"> </em></td>
        <td bgcolor="#eeeeee"></td>
   </tr>
</table>
</form>
</center>
<script language="Javascript">
	$().ready(function() { 
		$("#username").focus();
	 });
</script>
</body>
</html>
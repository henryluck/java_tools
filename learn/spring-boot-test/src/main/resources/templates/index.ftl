<!DOCTYPE html>

<html lang="en">

<body>
	<table border="1">
		<thead>
			<tr>
				<td>id</td>
				<td>name</td>
			</tr>
		</thead>
		<tbody>
<#list tests as test>
			<tr>
				<td>${test.id}</td>
				<td>${test.name}</td>
			</tr>
</#list>
		</tbody>
	</table>
</body>

</html>

<%-- 
    Document   : display
    Created on : 2020.01.23., 11:17:28
    Author     : GicziD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>${month}</title>

</head>
<body style="text-align: center">


	<br>
	<br>



	<table
		style="width: 75%; border: 0px solid transparent; border-collapse: collapse"
		align="center">

		<tr>
			<th style="border: 0px solid transparent; border-collapse: collapse"><font
				size="4" face="Arial">Hétfő</font></th>
			<th style="border: 0px solid transparent; border-collapse: collapse"><font
				size="4" face="Arial">Kedd</font></th>
			<th style="border: 0px solid transparent; border-collapse: collapse"><font
				size="4" face="Arial">Szerda</font></th>
			<th style="border: 0px solid transparent; border-collapse: collapse"><font
				size="4" face="Arial">Csütörtök</font></th>
			<th style="border: 0px solid transparent; border-collapse: collapse"><font
				size="4" face="Arial">Péntek</font></th>
			<th style="border: 0px solid transparent; border-collapse: collapse"><font
				size="4" face="Arial">Szombat</font></th>
			<th style="border: 0px solid transparent; border-collapse: collapse"><font
				size="4" face="Arial">Vasárnap</font></th>
		</tr>


		<c:forEach begin="0" end="${rows}" varStatus="i">

			<tr>
				<c:forEach begin="0" end="6" varStatus="j">


					<td
						style="text-align: right; border: 1px solid black; border-collapse: collapse; vertical-align: top; height: 90px">
						<font style="color: red" size="1" face="Arial">${holidays[i.index*7+j.index]}&nbsp;&nbsp;&nbsp;</font><br>
						<font style="color: black" size="1" face="Arial">${names[i.index*7+j.index]}</font>
						<font size="4" face="Arial"
						style="color: ${colors[i.index*7+j.index]}">&nbsp;${days[i.index*7+j.index]}&nbsp;</font>
						
						<c:if test="${not empty notestore[i.index*7+j.index].noteFileName}">
						
							<br>

							<c:if test="${notestore[i.index*7+j.index].preContent eq 'registration'}">
								<a id="${days[i.index*7+j.index]}" style="cursor: pointer"><font
									size="${notestore[i.index*7+j.index].size}"
									style="color:transparent">${notestore[i.index*7+j.index].preContent}
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></a>
							</c:if>

							<c:if test="${notestore[i.index*7+j.index].preContent ne 'registration'}">
								
								<c:if test="${not empty notestore[i.index*7+j.index].preContent}">
								
								<a id="${days[i.index*7+j.index]}" style="cursor: pointer"><font
									size="${notestore[i.index*7+j.index].size}"
									style="color:${notestore[i.index*7+j.index].color}">${notestore[i.index*7+j.index].preContent}
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></a>
									
									</c:if>
									
								<c:if test="${empty notestore[i.index*7+j.index].preContent}">
								
									<a id="${days[i.index*7+j.index]}" style="cursor: pointer"><font
									size="${notestore[i.index*7+j.index].size}"
									style="color:${notestore[i.index*7+j.index].color}">${notestore[i.index*7+j.index].content}
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></a>
									
									</c:if>
									
							</c:if>
</c:if>
					</td>


				</c:forEach>

			</tr>


		</c:forEach>

	</table>

	<font id="yearmonth" size="4" face="Arial">${year}<b> |
			${month} </b></font>

	<input id="monthsize" type="hidden" value="${monthsize}"
		name="monthsize">

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>



	<button id="prev"
		style="float: left; background-color: white; color: grey; cursor: pointer"><<</button>

	<button id="next"
		style="float: right; background-color: white; color: grey; cursor: pointer">>></button>

	<a id="search" style="color: grey; cursor: pointer">Névnap
		keresése&nbsp;&nbsp;</a>

	<a id="getyear" style="color: grey; cursor: pointer">Év
		megadása&nbsp;&nbsp;</a>

	<font style="color: grey">Bejegyzés színe:</font>
	
	<select id="color" style="background-color:#FFE">
	
	<c:forEach items="${colorstore}" var="color" begin="0" varStatus="i">
		
		<c:if test="${color eq selectedcolor}">
		<option value="${color}" style="color:${color};" selected="selected">&#9724; ${colornames[i.index]}</option>
		</c:if>
		<c:if test="${color ne selectedcolor}">
		<option value="${color}" style="color:${color};">&#9724; ${colornames[i.index]}</option>
		</c:if>
		
	</c:forEach>	
	</select>&nbsp;&nbsp;
	
	<a id="del" style="color: grey; cursor: pointer">Bejegyzés
		törlése&nbsp;&nbsp;</a>
	
	<a id="print" style="color: grey; cursor: pointer">Oldal
		nyomtatása&nbsp;&nbsp;</a>

	<a id="close" style="color: grey; cursor: pointer">Bezárás</a>



	<form action="createMonth" method="POST" id="stepper">

		<input type="hidden" id="actyear" value="${year}" name="inputyear">
		<input type="hidden" id="actmonth" value="${month}" name="inputmonth">
		<input type="hidden" id="actcolor" value="" name="inputcolor">

	</form>


	<form action="print" method="POST" id="printForm"
		accept-charset="UTF-8">

		<input type="hidden" name="html" id="str" value="">

	</form>


	<script src="script/dispscript.js"></script>


</body>
</html>

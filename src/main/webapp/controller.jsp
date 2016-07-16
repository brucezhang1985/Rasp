<%@ page import="com.myself.rasp.common.vo.Hlogs" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: root
  Date: 7/15/16
  Time: 2:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Controller</title>
</head>
<body>
<form name="myform" action="/rasp/cp" method="POST">
    <table>

        <tr><td>  <button type="submit" name="healthRun" value="healthRun" >healthRun</button></td></tr>
        <tr><td>  <button type="submit" name="fanRun" value="fanRun" >fanRun</button></td></tr>
        <tr><td>  <button type="submit" name="fanClose" value="fanClose" >fanClose</button></td></tr>
        <tr><td>  <button type="submit" name="bServo" value="bServo" >BottomServo_Angle_Test</button></td></tr>
        <tr><td>  <button type="submit" name="hServo" value="hServo" >HandServo_Angle_Test</button></td></tr>
        <tr><td>  <button type="submit" name="moneyStart" value="moneyStart" >money from Start</button></td></tr>
        <tr><td>  <button type="submit" name="moneyEnd" value="moneyEnd" >money from End</button></td></tr>
    </table>

</form>


<textarea style="width:800px;height:200px;">  <%=request.getAttribute("result")%> </textarea>

</body>
</html>

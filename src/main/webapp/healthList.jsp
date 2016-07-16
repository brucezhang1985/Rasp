<%@ page import="com.myself.rasp.common.vo.Hlogs" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: root
  Date: 7/15/16
  Time: 4:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sleep status list</title>
    <script src="js/jquery1.6.js"></script>
</head>
<body>
<h2>睡眠监控系统</h2>
<br>
查询条件:
<form name="myform" action="/rasp/Health" method="POST">
    <table>

        <tr>
            <td>
                ID matches:
                <select name="orderBy">
                    <option value="1">ASC</option>
                    <option value="0">DESC</option>
                </select>
                <select name="matches">
                    <option value="1"> > </option>
                    <option value="0"> = </option>
                    <option value="-1"> < </option>
                </select>

                <input type="input" name="hid" />
            </td>
        </tr>
        <tr>
            <td>
                <input type="button" onclick="javascript:$('#fullSql').val('');" value="clear" />
                SQL:
                <textarea id="fullSql" style="width:500px; height:80px" name="fullSql" >select * from hlogs where id > 0 order by id desc</textarea>
            </td>
        </tr>
        <tr><td>  <button type="submit" name="submit "  >search</button></td></tr>
    </table>

</form>

<table>
    <thead>
    <tr>
        <td>id</td>
        <td>message</td>
        <td>CreateTime</td>
    </tr>
    </thead>
    <tbody>
    <%
        List<Hlogs> logs = (List<Hlogs>) request.getAttribute("logs");
        for (Hlogs log : logs) {

    %>

    <tr>
        <td><%=log.getId()%>
        </td>
        <td><%=log.getLog()%>
        </td>
        <td><%=log.getCreateTime()%>
        </td>
    </tr>

    <%}%>
    </tbody>
</table>
</body>
</html>

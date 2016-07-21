<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Controller</title>
    <jsp:include page="header.jsp"></jsp:include>
    <%--<script src="js/jquery1.6.js"></script>--%>
    <script>

        function formAjaxSubmit() {
            var frm = $('#myform');
            $.ajax({
                type: "POST",
                url: "/rasp/cp",
//                dataType: "json",
                data: frm.serialize(),
                success: function (json) {
                    json = json.replace("<br>","\n");
                    $("#output").val("request success:\n" + json);

//                    if (json.result != "") {
//                        var rs = eval(json);
//                        rs = rs.result.replace("<br>","\n");
//                        $("#output").val("request success:\n" + rs);
//                    }
                },
                error: function (e) {
                    alert("error" + e);
                    console.log(e);
                },
                complete: function () {
                    $("#openOptions").val(0);
                    $("#closeOptions").val(0);
                    $("#queryProcessName").val(0);
                    console.log("complete.")
                }

            });
        }


    </script>
</head>
<body>

<br>
<form id="myform" name="myform" action="/rasp/cp" method="POST">
    <table>
        <tr>
            <td>
                选择程序:
                <select id="openOptions" name="openOptions" onchange="formAjaxSubmit()">
                    <option value="0" selected="select">选择</option>
                    <option value="autoStart.py 1">狂野飙车8 Start</option>
                    <option value="autoStart.py 2">狂野飙车8 End</option>
                    <option value="health.py">检测睡眠质量</option>
                    <option value="DHT11_Python/temperature.py">测量温度</option>
                    <option value="fanOpen.py">打开风扇</option>
                    <option value="fanClose.py">关闭风扇</option>
                </select>
            </td>
        </tr>

        <tr>
            <td>
                关闭程序:
                <select id="closeOptions" name="closeOptions" onchange="formAjaxSubmit()">
                    <option value="0" selected="select">选择</option>
                    <option value="autoStart.py">狂野飙车8 - autoStart.py</option>
                    <option value="health.py">睡觉质量检测 - health.py</option>
                    <option value="temperature.py">温度检测 - temperature.py</option>
                </select>
            </td>
        </tr>

        <tr>
            <td>
                查看程序:
                <select id="queryProcessName" name="queryProcessName" onchange="formAjaxSubmit()">
                    <option value="0" selected="select">选择</option>
                    <option value="autoStart.py">狂野飙车8 - autoStart.py</option>
                    <option value="health.py">睡觉质量检测 - health.py</option>
                    <option value="temperature.py">温度检测 - temperature.py</option>
                    <option value="tomcat">Tomcat</option>
                    <option value="java">java</option>
                </select>
            </td>

        </tr>

        <tr>
            <td>
                <div class="col-sm-6 col-lg-4">
                    <h2 class="h4">Animate</h2>
                    <p>
                        <input id="switch-animate" type="checkbox" checked data-off-color="info">
                    </p>

                </div>
            </td>
        </tr>

    </table>

</form>


<textarea id="output" style="width:1200px;height:200px;">  <%=request.getAttribute("result")%> </textarea>

</body>
</html>

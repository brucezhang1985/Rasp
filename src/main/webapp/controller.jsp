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
                dataType: "json",
                data: frm.serialize(),
                success: function (json) {
                    if (json.result != "") {
                        $("#output").val("request success:\n" + json.result);
                    }
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
                Open Process:
                <select id="openOptions" name="openOptions" onchange="formAjaxSubmit()">
                    <option value="0" selected="select">Select Process</option>
                    <option value="autoStart.py 1">Money Digger from Start</option>
                    <option value="autoStart.py 2">Money Digger from End</option>
                    <option value="health.py">Health Detection - health.py</option>
                    <option value="fanOpen.py">Open Fan</option>
                    <option value="fanClose.py">Close Fan</option>
                </select>
            </td>
        </tr>

        <tr>
            <td>
                KIll:
                <select id="closeOptions" name="closeOptions" onchange="formAjaxSubmit()">
                    <option value="0" selected="select">Select Process</option>
                    <option value="autoStart.py">Money Digger - autoStart.py</option>
                    <option value="health.py">Health Detection - health.py</option>
                </select>
            </td>
        </tr>

        <tr>
            <td>
                Query:
                <select id="queryProcessName" name="queryProcessName" onchange="formAjaxSubmit()">
                    <option value="0" selected="select">Select Process</option>
                    <option value="autoStart.py">Money Digger - autoStart.py</option>
                    <option value="health.py">Health Detection - health.py</option>
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


<textarea id="output" style="width:800px;height:200px;">  <%=request.getAttribute("result")%> </textarea>

</body>
</html>

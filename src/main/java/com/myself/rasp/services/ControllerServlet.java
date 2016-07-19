package com.myself.rasp.services;

import com.myself.rasp.common.BaseServlet;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by bruce.zhang on 7/15/16.
 */
public class ControllerServlet extends BaseServlet {
    private static String scriptName = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("controller.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String closeOptions = req.getParameter("closeOptions");
        String openOptions = req.getParameter("openOptions");

        String bServo = req.getParameter("bServo");
        String hServo = req.getParameter("hServo");

        String queryProcessName = req.getParameter("queryProcessName");

        String result = "null";

        if (StringUtils.isNotBlank(queryProcessName) && !"0".equals(queryProcessName)) {
            result = queryProcessId(queryProcessName);
            result +="result success";
        } else if (StringUtils.isNotBlank(closeOptions) && !"0".equals(closeOptions)) {
            result = killProcessByName(closeOptions);
            result +="Kill success";
        } else if (StringUtils.isNotBlank(openOptions) && !"0".equals(openOptions)) {
            result = executePython(msg("python.homepath"), openOptions);
            result +="Open success";
        } else {
            result = executePython(msg(openOptions));
        }
        req.setAttribute("result", result);
        PrintWriter writer = resp.getWriter();
        writer.write("{\"result\":\"" + result + "\"" +"}");
        writer.flush();
        writer.close();
    }
}

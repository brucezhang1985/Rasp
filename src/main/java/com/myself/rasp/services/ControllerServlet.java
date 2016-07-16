package com.myself.rasp.services;

import com.myself.rasp.common.database.MessageUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by bruce.zhang on 7/15/16.
 */
public class ControllerServlet extends HttpServlet {
    private static String scriptName = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("controller.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String healthRun = req.getParameter("healthRun");
        String fanRun = req.getParameter("fanRun");
        String fanClose = req.getParameter("fanClose");
        String moneyStart = req.getParameter("moneyStart");
        String moneyEnd = req.getParameter("moneyEnd");
        String bServo = req.getParameter("bServo");
        String hServo = req.getParameter("hServo");
        String key = "";
        if (StringUtils.isNotBlank(healthRun)) {
            key = "health";
        } else if (StringUtils.isNotBlank(fanRun)) {
            key = "fan.open";
        }else if (StringUtils.isNotBlank(fanClose)) {
            key = "fan.close";
        }else if (StringUtils.isNotBlank(moneyStart)) {
            key = "money.start";
        }else if (StringUtils.isNotBlank(moneyEnd)) {
            key = "money.end";
        }else if (StringUtils.isNotBlank(bServo)) {
            key = "serInput";
        }else if (StringUtils.isNotBlank(hServo)) {
            key = "serHand";
        }
        Process process = Runtime.getRuntime().exec("sudo python " + MessageUtils.getMessage(key));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder content = new StringBuilder();
        String cc  ;
        while ((cc = bufferedReader.readLine()) != null) {
            content.append(cc);
            System.out.println("Execute result --->\n" + content.toString());
        }
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        req.setAttribute("result", content);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("controller.jsp");
        requestDispatcher.forward(req,resp);
        //sendRedirect this will lost attribute
//        resp.sendRedirect("/controller.jsp");
    }


}

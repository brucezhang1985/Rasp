package com.myself.rasp.services;

import com.myself.rasp.common.RunLocalCommandUtils;
import com.myself.rasp.common.database.MessageUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        String moneyStart = req.getParameter("moneyStart");
        String moneyEnd = req.getParameter("moneyEnd");

        String moneyClose = req.getParameter("moneyClose");

        String healthRestart = req.getParameter("healthRestart");
        String moneyStartRestart = req.getParameter("moneyStartRestart");
        String moneyEndRestart = req.getParameter("moneyEndRestart");

        String fanRun = req.getParameter("fanRun");
        String fanClose = req.getParameter("fanClose");
        String bServo = req.getParameter("bServo");
        String hServo = req.getParameter("hServo");

        if (StringUtils.isNotBlank(moneyClose)) {

            RunLocalCommandUtils.killProcessByName(MessageUtils.getMessage("kill.money.start"));

        } else {
            String key = "";
            if (StringUtils.isNotBlank(healthRun)) {
                key = "health";
            } else if (StringUtils.isNotBlank(fanRun)) {
                key = "fan.open";
            } else if (StringUtils.isNotBlank(fanClose)) {
                key = "fan.close";
            } else if (StringUtils.isNotBlank(moneyStart)) {
                key = "money.start";
            } else if (StringUtils.isNotBlank(moneyEnd)) {
                key = "money.end";
            } else if (StringUtils.isNotBlank(bServo)) {
                key = "serInput";
            } else if (StringUtils.isNotBlank(hServo)) {
                key = "serHand";
            }

            String killProcessName = "";
            boolean needToKill = false;
            if (StringUtils.isNotBlank(healthRestart)) {
                killProcessName = MessageUtils.getMessage("kill.health");
                needToKill = true;
                key = "health";
            } else if (StringUtils.isNotBlank(moneyStartRestart)) {
                killProcessName = MessageUtils.getMessage("kill.money.start");
                key = "money.start";
                needToKill = true;
            } else if (StringUtils.isNotBlank(moneyEndRestart)) {
                killProcessName = MessageUtils.getMessage("kill.money.start");
                key = "money.end";
                needToKill = true;
            }

            if (needToKill) {
                System.out.println("KIll process " + killProcessName);
                if (RunLocalCommandUtils.killProcessByName(killProcessName)) {
                    System.out.println("KIll successfully");
                } else {
                    System.out.println("KIll Failed. ");
                }
            }

            String command = "sudo python " + MessageUtils.getMessage(key);
            String result = RunLocalCommandUtils.execute(command);
            req.setAttribute("result", result);
        }
        req.setAttribute("result", "");

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("controller.jsp");
        requestDispatcher.forward(req, resp);
        //sendRedirect this will lost attribute
//        resp.sendRedirect("/controller.jsp");
    }


}

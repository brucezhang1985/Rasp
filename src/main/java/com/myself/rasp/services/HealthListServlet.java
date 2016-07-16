package com.myself.rasp.services;

import com.myself.rasp.common.database.MysqlUtils;
import com.myself.rasp.common.vo.Hlogs;
import org.apache.commons.lang.StringUtils;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by bruce.zhang on 7/15/16.
 */
public class HealthListServlet extends HttpServlet {
    String searchSQL = "select * from hlogs order by id desc";
    public static String TABLE_NAME = " hlogs ";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MysqlUtils instance = MysqlUtils.getInstance();
        List<Hlogs> hlogses = instance.querySQL(searchSQL);
        req.setAttribute("logs", hlogses);
        RequestDispatcher dd = req.getRequestDispatcher("healthList.jsp");
        dd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String m = req.getParameter("matches");
        String hid = req.getParameter("hid");
        String fullSql = req.getParameter("fullSql");
        String ascDesc = req.getParameter("orderBy");

        if (StringUtils.isNotBlank(fullSql)) {
            searchSQL = fullSql;
        } else {
            String condition = "";
            String sortBy = "";
            if (StringUtils.isNotBlank(m)) {
                Integer matches = Integer.parseInt(m);
                switch (matches) {
                    case 0:
                    case 1:
                        condition = " id >= " + hid;
                        break;
                    case -1:
                        condition = " id <= " + hid;
                        break;
                }
            }

            if (StringUtils.isNotBlank(ascDesc)) {
                Integer sort = Integer.parseInt(ascDesc);
                switch (sort) {
                    case 0:
                        sortBy = " DESC ";
                        break;
                    case 1:
                        sortBy = " ASC ";
                        break;

                }
            }
            searchSQL = combineQuerySQL(TABLE_NAME, "id " + sortBy, condition);
        }

        MysqlUtils instance = MysqlUtils.getInstance();
        List<Hlogs> hlogses = instance.querySQL(searchSQL);
        req.setAttribute("logs", hlogses);
        RequestDispatcher dd = req.getRequestDispatcher("healthList.jsp");
        dd.forward(req, resp);
    }

    public String combineQuerySQL(String table, String orderBy, String condition) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * from ")
                .append(table)
                .append(" WHERE ")
                .append(condition).append(" order by ")
                .append(orderBy);
        return sql.toString();
    }

}

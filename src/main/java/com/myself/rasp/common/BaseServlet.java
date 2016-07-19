package com.myself.rasp.common;

import com.myself.rasp.common.database.MessageUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServlet;

/**
 * Created by bruce.zhang on 7/19/16.
 */
public class BaseServlet extends HttpServlet {

    public String queryProcessId(String processName) {
        return executeCommandKey("checkProcessIdSH", processName);
    }

    public String executePython(String path, String commandFile) {
        return executeCommandKey("python.command", path + commandFile);
    }

    public String executePython(String commandFile) {
        return executeCommandKey("python.command", commandFile);
    }

    public String killProcessByName(String processName) {
        return executeCommandKey("killProcessSH", processName);
    }

    public String executeCommand(String command) {
        return RunLocalCommandUtils.execute(command);
    }

    public String executeCommandKey(String commandKey) {
        return RunLocalCommandUtils.execute(msg(commandKey));
    }

    public String executeCommandKey(String commandKey, String... args) {
        return executeCommand(msg(commandKey, args));
    }

    public String msg(String property) {
        return StringUtils.isBlank(property) ? "" : MessageUtils.getMessage(property);
    }

    public String msg(String property, String... formats) {
        return String.format(msg(property), formats);
    }

}

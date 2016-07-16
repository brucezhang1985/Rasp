package com.myself.rasp.common;

import com.myself.rasp.common.database.MessageUtils;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruce.zhang on 7/16/16.
 */
public class RunLocalCommandUtils {
    public static String KILL_PROCESS = "ps -ef|grep %s |grep -v grep|cut -c 9-15|xargs sudo kill -9";
    public static String QUERY_PROCESS = "ps -ef|grep %s |grep -v grep";

    public static String execute(String command) {
        StringBuilder sb = new StringBuilder();
        try {
            Process exec = Runtime.getRuntime().exec(new String[]{"bash", "-c", command});
            System.out.println("Execute command [" + command + "]");
            InputStream inputStream = exec.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                System.out.println("Execute command get result:\n" + s);
                sb.append(s);
            }
            exec.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static boolean startProcess(String command, String processName) {
        String execute = execute(command);
        boolean existence = checkProcessExistence(processName);
        if (existence) {
            System.out.println("Run successfully");
        } else {
            System.out.println("Run failed");
        }
        return existence;
    }

    /**
     * @param processName
     * @return
     */
    public static boolean killProcessByName(String processName) {
        if (checkProcessExistence(processName)) {
            String execute = execute(String.format(MessageUtils.getMessage("killProcessSH"), processName));
            System.out.printf("Execute result:" + execute);
            return !checkProcessExistence(processName);
        }
        System.out.println("Process " + processName + " is not exist. no need to kill.");
        return false;
    }

    /**
     * @param processName
     * @return
     */
    private static boolean checkProcessExistence(String processName) {
        String execute = execute(String.format(MessageUtils.getMessage("checkProcessSH"), processName));
        System.out.println(" CHeck Exist or not processName " + processName + " result: [" + execute + "]");
        return StringUtils.isNotBlank(execute);
    }

    public static void main(String args[]) throws IOException {
        Process process = null;
        List<String> processList = new ArrayList<String>();
        try {
            process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "ps -au"});
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = input.readLine()) != null) {
                processList.add(line);
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : processList) {
            System.out.println(line);
        }
    }

}

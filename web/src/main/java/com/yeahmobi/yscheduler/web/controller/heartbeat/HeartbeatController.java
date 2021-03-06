package com.yeahmobi.yscheduler.web.controller.heartbeat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yeahmobi.yscheduler.monitor.ActiveAgentManager;
import com.yeahmobi.yscheduler.web.controller.AbstractController;

/**
 * @author wukezhu
 */
@Controller
@RequestMapping(value = { HeartbeatController.SCREEN_NAME })
public class HeartbeatController extends AbstractController {

    private static final Logger LOGGER      = LoggerFactory.getLogger(HeartbeatController.class);

    public static final String  SCREEN_NAME = "heartbeat";

    @Autowired
    private ActiveAgentManager  activeAgentManager;

    @RequestMapping(value = { "agent/active" }, method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object hearbeat(HttpServletRequest request, HttpServletResponse response, long agentId, String agentVersion)
                                                                                                                       throws ServletException,
                                                                                                                       IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            this.activeAgentManager.heartbeat(agentId);
            // 判断agentVersion是否改变，若改变则持久化到数据库，并更新缓存
            this.activeAgentManager.checkAndUpdateAgentVersion(agentId, agentVersion);
            map.put("success", true);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            map.put("notice", e.getMessage());
            map.put("success", false);
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = { "agent/isActive" }, method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object isActive(HttpServletRequest request, HttpServletResponse response, long agentId)
                                                                                                  throws ServletException,
                                                                                                  IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            boolean active = this.activeAgentManager.isActive(agentId);

            map.put("active", active);
            map.put("success", true);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            map.put("notice", e.getMessage());
            map.put("success", false);
        }
        return JSON.toJSONString(map);
    }

}

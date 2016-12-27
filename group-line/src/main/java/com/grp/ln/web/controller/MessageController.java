package com.grp.ln.web.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grp.ln.core.featue.orm.mybatis.Page;
import com.grp.ln.core.util.RootNode;
import com.grp.ln.web.model.Groups;
import com.grp.ln.web.model.Message;
import com.grp.ln.web.model.MsgGroup;
import com.grp.ln.web.model.UserInfo;
import com.grp.ln.web.service.MessageService;

@Controller
@RequestMapping("/mobile/msg")
public class MessageController {

    @Resource
    private MessageService messageService;

    /**
     * 发送消息,通过推送模式
     *
     * @param message
     */
    @RequestMapping("/send")
    public void send(@Valid Message message) {

    }

    /**
     * 通过msgid 去获取消息
     *
     * @param message
     * @return
     */
    @RequestMapping("/get")
    @ResponseBody
    public Message getMsg(Message message) {
        return messageService.selectById(message.getMsgId());
    }

    /**
     * 获取某个用户的消息
     *
     * @param page
     * @return
     */
    @RequestMapping("/user/userId")
    @ResponseBody
    public RootNode getMsgByUserId(Page<Message> page, UserInfo userInfo) {
        messageService.selectByUsetId(page, userInfo.getUserId());
        RootNode rootNode = new RootNode();
        rootNode.setSucceed(true);
        rootNode.setContent(page);
        return rootNode;
    }

    /**
     * 获取多个用户的聊天记录
     *
     * @param page userId = "id2,id2"
     * @return
     */
    @RequestMapping("/user/userIds")
    @ResponseBody
    public RootNode getMsgByUserIds(Page<Message> page, UserInfo userInfo) {
        messageService.selectByUsetIds(page, userInfo.getUserId());
        RootNode rootNode = new RootNode();
        rootNode.setSucceed(true);
        rootNode.setContent(page);
        return rootNode;
    }


    /**
     * 通过 群/组 获取的消息
     *
     * @param page
     * @return
     */
    @RequestMapping("/group/grpId")
    @ResponseBody
    public Page<Message> getMsgByGroup(Page<Message> page, MsgGroup msgGroup) {
        return messageService.getMsgByGroup(page, msgGroup.getGrpId());
    }
}

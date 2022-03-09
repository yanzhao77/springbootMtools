package com.yz.securitydemo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.yz.securitydemo.entity.Message;
import com.yz.securitydemo.entity.Users;
import com.yz.securitydemo.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MessageController {

    @Autowired
    public MessageMapper messageMapper;

    @RequestMapping("/getMsg")
    @ResponseBody
    public List<Message> getMsg() {

        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (null == user) {
            return new ArrayList<>();
        }
        List<Message> msgs = messageMapper.selectByid(user.getId());
        log.info("用戶[{}]", user.getId());

        return msgs;

    }

    @RequestMapping("/sendMsg")
    @ResponseBody
    public void sendMsg(@RequestBody Message message) {
        log.info(message.toString());
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        message.setMsgProducer(user.getId());
        message.setId(UUID.randomUUID().toString());
        message.setCreatetime(new Date());
        message.setMqLevel(message.getMsgLevel());
        messageMapper.insert(message);
        log.info(user.toString());
    }

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PageInfo<Message> pg = new PageInfo<Message>(messageMapper.getMessage(user.getId()));
        model.addAttribute("users", pg);
        return "message";
    }

    @RequestMapping("/getCount")
    @ResponseBody
    public int getCount() {
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Message msg = new Message();
        msg.setMsgConsumer(user.getId());
        int count = messageMapper.selectCount(msg);

        return count;
    }
}

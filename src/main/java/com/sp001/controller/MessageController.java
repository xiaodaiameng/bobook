package com.sp001.controller;

import com.sp001.mapper.MessagesMapper;
import com.sp001.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MessageController {

    @Autowired
    private MessagesMapper messagesMapper;

    // 显示留言板页面
    @GetMapping("/message-board")
    public String showMessageBoard(Model model) {
        // 获取公开留言
        List<Message> messages = messagesMapper.selectPublicMessages();
        model.addAttribute("messages", messages);
        return "message-board";
    }

    // 提交留言
    @PostMapping("/submit-message")
    public String submitMessage(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam(required = false, defaultValue = "其他") String leixing,
            @RequestParam(required = false) String bm,  // 保密选项
            @RequestParam String content,
            Model model) {

        Message message = new Message();
        message.setName(name);
        message.setEmail(email);
        message.setMessageType(leixing);
        message.setContent(content);
        message.setStatus(1);  // 正常状态

        // 处理保密选项
        if ("保密".equals(bm)) {
            message.setIsSecret(1);
        } else {
            message.setIsSecret(0);
        }

        try {
            int result = messagesMapper.insertMessage(message);
            if (result > 0) {
                // 提交成功，重定向到留言板页面
                return "redirect:/message-board";
            } else {
                model.addAttribute("error", "提交失败，请重试");
            }
        } catch (Exception e) {
            model.addAttribute("error", "服务器错误");
        }

        // 如果出错，返回留言板页面并显示错误
        return showMessageBoard(model);
    }
}
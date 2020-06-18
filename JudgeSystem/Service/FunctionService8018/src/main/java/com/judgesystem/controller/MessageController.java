package com.judgesystem.controller;


import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@ServerEndpoint("/Room/{username}")
public class MessageController {
   /* @Autowired
    SimpMessagingTemplate messagingTemplate;
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public ChatMsg greeting(ChatMsg chatMsg)throws Exception{
        return chatMsg;
    }
    @MessageMapping("/ws/chat")
    public void handleChat(Principal principal, ChatMsg chatResp){
      *//*  String message = msg.substring(msg.lastIndexOf(",") + 1, msg.length());
        String destUser = msg.substring(0, msg.lastIndexOf(","));*//*
      //String destUser=chatResp.getTo();
      chatResp.setDate(new Date());
      System.out.println(chatResp);
        messagingTemplate.convertAndSendToUser(chatResp.getTo(), "/queue/chat", chatResp);

    }*/

    private static List<Session> sessions = new ArrayList<Session>();
     @OnOpen
     public void OnOpen(Session session, @PathParam(value = "username") String username) {
                 sessions.add(session);
                 sendTextMsg("好友 [" + username + "]加入群聊");
             }
     @OnMessage
     public void OnMsg(String msg,@PathParam(value = "username") String username) {
                 sendTextMsg(username + ":" +msg);
             }
     @OnClose
     public void OnClose(Session session, @PathParam("username") String username) throws IOException {
                 sessions.remove(session);
                 sendTextMsg("好友 ["+ username + "] 退出群聊");
             }
     @OnError
     public void OnError(Throwable e) {
                 e.printStackTrace();
             }
     private void sendTextMsg(String msg) {
                 for (Session session : sessions) {
                         session.getAsyncRemote().sendText(msg);
                     }
             }

}

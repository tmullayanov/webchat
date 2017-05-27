package org.localhost.chat;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatService {
    private Set<ChatWebSocket> webSockets;

    public ChatService() {
        this.webSockets = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    public void sendMessage(String data) {
        for (ChatWebSocket user : webSockets) {
            try {
                user.sendString(data);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void addUser(ChatWebSocket user) {
        if (user != null) {
            System.out.println("hey!");
            this.webSockets.add(user);
        }
    }

    public void removeUser(ChatWebSocket user) {
        if (user != null) {
            this.webSockets.remove(user);
        }
    }
}

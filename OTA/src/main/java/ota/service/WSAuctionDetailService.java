package ota.service;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/wsAuctionDetail")
public class WSAuctionDetailService {
	private static Set<Session> clients = new CopyOnWriteArraySet<>();
	
	@OnOpen
	public void onOpen(Session session) {
		clients.add(session);
		System.out.println("클라이언트가 연결되었습니다.");
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		// if message > 현재 최고 입찰가
		System.out.println("[클라이언트 메세지] " + message);
		String serverMessage = "echo " + message;
		System.out.println("서버가 보내는 메세지 : " + serverMessage);
		
		// 모든 클라이언트에게 메시지 전송
        for (Session client : clients) {
            if (client != session && client.isOpen()) {
                client.getAsyncRemote().sendText(message);
            }
        }
		
//		return serverMessage;
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.println("클라이언트와 연결이 끊어졌습니다...");
	}
	
	@OnError
	public void onError(Throwable error) {
		error.printStackTrace();
	}
}

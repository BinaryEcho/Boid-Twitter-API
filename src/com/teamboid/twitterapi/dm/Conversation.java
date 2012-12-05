package com.teamboid.twitterapi.dm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import com.teamboid.twitterapi.user.User;

public class Conversation implements Serializable {

	private static final long serialVersionUID = 8016196511797727337L;

	public Conversation(DirectMessage m) {
		messages = new ArrayList<DirectMessage>();
		messages.add(m);
		threadId = m.getThreadId();
		if(m.isOutgoing()) {
			endUser = m.getRecipientUser();
			endUserId = m.getRecipientId();
			endUserScreen = m.getRecipient();
			if(m.getRecipientName() == null || m.getRecipientName().trim().isEmpty()) {
				endUserName = "@" + endUserScreen;
			} else {
				endUserName = m.getRecipientName();
			}
			endUserProfilePic = m.getRecipientProfilePic();
		} else {
			endUser = m.getSenderUser();
			endUserId = m.getSenderId();
			endUserScreen = m.getSender();
			if(m.getSenderName() == null || m.getSenderName().trim().isEmpty()) {
				endUserName = "@" + endUserScreen;
			} else {
				endUserName = m.getSenderName();
			}
			endUserProfilePic = m.getSenderProfilePic();
		}
	}
	
	private String threadId;
	private long endUserId;
	private User endUser;
	private String endUserScreen;
	private String endUserName;
	private String endUserProfilePic;
	private ArrayList<DirectMessage> messages;

	public void sort() {
		Collections.sort(messages, new DirectMessage.MessageComparator());
	}
	
	public void sortReversed() {
		Collections.sort(messages, new DirectMessage.MessageReverseComparator());
	}
	
	public String getThreadId() {
		return threadId;
	}
	
	public long getEndUserId() {
		return endUserId;
	}
	
	public User getEndUser() {
		return endUser;
	}
	
	public String getEndUserScreen() {
		return endUserScreen;
	}
	
	public String getEndUserName() {
		return endUserName;
	}
	
	public String getEndUserProfilePic() {
		return endUserProfilePic;
	}
	
	public void addMessage(DirectMessage msg) {
		int addIndex = findAddIndex(msg);
		if(addIndex == -1) {
			messages.add(msg);
		} else {
			messages.add(addIndex, msg);
		}
	}
	
	private int findAddIndex(DirectMessage msg) {
		for(int i = 0; i < messages.size(); i++) {
			if(messages.get(i).getCreatedAt().compareTo(msg.getCreatedAt()) < 0) {
				return i;
			}
		}
		return -1;
	}
	
	public ArrayList<DirectMessage> getMessages() {
		return messages;
	}
}
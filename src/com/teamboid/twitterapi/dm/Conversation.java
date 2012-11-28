package com.teamboid.twitterapi.dm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import android.util.Log;

public class Conversation implements Serializable {

	private static final long serialVersionUID = 8016196511797727337L;

	public Conversation(BaseMessage m) {
		messages = new ArrayList<BaseMessage>();
		messages.add(m);
		threadId = m.getThreadId();
		if(m.isOutgoing()) {
			endUserId = m.getRecipientId();
			endUser = m.getRecipient();
			if(m.getRecipientName() == null || m.getRecipientName().trim().isEmpty()) {
				endUserName = endUser;
			} else {
				endUserName = m.getRecipientName();
			}
			endUserProfilePic = m.getRecipientProfilePic();
		} else {
			endUserId = m.getSenderId();
			endUser = m.getSender();
			if(m.getSenderName() == null || m.getSenderName().trim().isEmpty()) {
				endUserName = endUser;
			} else {
				endUserName = m.getSenderName();
			}
			endUserProfilePic = m.getSenderProfilePic();
		}
	}
	
	private String threadId;
	private long endUserId;
	private String endUser;
	private String endUserName;
	private String endUserProfilePic;
	private ArrayList<BaseMessage> messages;

	public void sort() {
		Collections.sort(messages, new BaseMessage.MessageComparator());
	}
	
	public void sortReversed() {
		Collections.sort(messages, new BaseMessage.MessageReverseComparator());
	}
	
	public String getThreadId() {
		return threadId;
	}
	
	public long getEndUserId() {
		return endUserId;
	}
	
	public String getEndUser() {
		return endUser;
	}
	
	public String getEndUserName() {
		return endUserName;
	}
	
	public String getEndUserProfilePic() {
		return endUserProfilePic;
	}
	
	public void addMessage(BaseMessage msg) {
		int addIndex = findAddIndex((BaseMessage)msg);
		if(addIndex == -1) {
			messages.add(msg);
		} else {
			messages.add(addIndex, msg);
		}
	}
	
	private int findAddIndex(BaseMessage msg) {
		for(int i = 0; i < messages.size(); i++) {
			if(messages.get(i).getCreatedAt().compareTo(msg.getCreatedAt()) < 0) {
				return i;
			}
		}
		return -1;
	}
	
	public ArrayList<BaseMessage> getMessages() {
		return messages;
	}
}
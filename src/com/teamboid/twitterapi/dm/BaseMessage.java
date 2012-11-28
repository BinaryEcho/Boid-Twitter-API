package com.teamboid.twitterapi.dm;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Comparator;

/**
 * Interface used by the application "Banter"
 * @author Aidan Follestad
 */
public interface BaseMessage extends Serializable {

	public abstract long getId();
	
	public abstract String getSender();
	
	public abstract long getSenderId();
	
	public abstract String getSenderName();
	
	public abstract String getRecipient();
	
	public abstract long getRecipientId();
	
	public abstract String getRecipientName();
	
	public abstract String getBody();
	
	public abstract Calendar getCreatedAt();
	
	public abstract String getThreadId();
	
	public abstract String getSenderProfilePic();
	
	public abstract String getRecipientProfilePic();
	
	public abstract boolean isOutgoing();
	
    public static class MessageComparator implements Comparator<BaseMessage> {
		public int compare(BaseMessage left, BaseMessage right) {
			return right.getCreatedAt().compareTo(left.getCreatedAt());
		}
	}

	public static class MessageReverseComparator implements Comparator<BaseMessage> {
		public int compare(BaseMessage left, BaseMessage right) {
			return left.getCreatedAt().compareTo(right.getCreatedAt());
		}
	}
}
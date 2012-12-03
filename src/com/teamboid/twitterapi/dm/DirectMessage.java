package com.teamboid.twitterapi.dm;

import com.teamboid.twitterapi.user.User;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Comparator;

/**
 * Represents a direct message, sent to another user or received from another user.
 *
 * @author Aidan Follestad
 */
public interface DirectMessage extends Serializable {

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
	
    public abstract User getSenderUser();

    public abstract User getRecipientUser();
 
    public abstract void setIsError(boolean error);
    
    public abstract boolean isError();
    
    public abstract void setIsSending(boolean sending);
    
	public abstract boolean isSending();
    
    public static class MessageComparator implements Comparator<DirectMessage> {
		public int compare(DirectMessage left, DirectMessage right) {
			return right.getCreatedAt().compareTo(left.getCreatedAt());
		}
	}

	public static class MessageReverseComparator implements Comparator<DirectMessage> {
		public int compare(DirectMessage left, DirectMessage right) {
			return left.getCreatedAt().compareTo(right.getCreatedAt());
		}
	}
}
package com.teamboid.twitterapi.dm;

import java.util.ArrayList;

public class ConversationOrganizer {

	public ConversationOrganizer() {
		convos = new ArrayList<Conversation>();
	}
	
	private ArrayList<Conversation> convos;
	
	public void add(DirectMessage baseMessage) {
		String screenName = baseMessage.getRecipient();
		if(baseMessage.isOutgoing()) {
			screenName = baseMessage.getSender();
		}
		int index = findConvoByUser(screenName);
		if(index > -1) {
			Conversation toSet = convos.get(index);
			toSet.addMessage(baseMessage);
			convos.set(index, toSet);
		} else {
			convos.add(new Conversation(baseMessage));
		}
	}
	
	public void clear() {
		convos.clear();
	}
	
	public int findConvoByUser(String screenName) {
		for(int i = 0; i < convos.size(); i++) {
			if(convos.get(i).getEndUser().equals(screenName)) {
				return i;
			}
		}
		return -1;
	}
	
	public ArrayList<Conversation> getConvos() {
		return convos;
	}
}

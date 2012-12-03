package com.teamboid.twitterapi.dm;

import com.teamboid.twitterapi.user.User;
import com.teamboid.twitterapi.user.UserJSON;
import com.teamboid.twitterapi.utilities.Time;
import com.teamboid.twitterapi.utilities.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Handles parsing JSON and assigning values into a {@link DirectMessage} interface.
 * @author Aidan Follestad
 */
public class DirectMessageJSON implements DirectMessage, Serializable {

	private static final long serialVersionUID = -6166266603350967003L;

	public DirectMessageJSON() {
	}
	public DirectMessageJSON(JSONObject json) throws Exception {
		_createdAt = Time.getTwitterDate(json.getString("created_at"));
		_senderScreenName = json.getString("sender_screen_name");
		_sender = new UserJSON(json.getJSONObject("sender"));
		_text = Utils.unescape(json.getString("text"));
		_recipientScreenName = json.getString("recipient_screen_name");
		_id = json.getLong("id");
		_recipient = new UserJSON(json.getJSONObject("recipient"));
		_recipientId = json.getLong("recipient_id");
		_senderId = json.getLong("sender_id");
	}
	public DirectMessageJSON(JSONObject json, boolean outgoing) throws Exception {
		_createdAt = Time.getTwitterDate(json.getString("created_at"));
		_senderScreenName = json.getString("sender_screen_name");
		_sender = new UserJSON(json.getJSONObject("sender"));
		_text = Utils.unescape(json.getString("text"));
		_recipientScreenName = json.getString("recipient_screen_name");
		_id = json.getLong("id");
		_recipient = new UserJSON(json.getJSONObject("recipient"));
		_recipientId = json.getLong("recipient_id");
		_senderId = json.getLong("sender_id");
		_outgoing = outgoing;
	}
	public DirectMessageJSON(JSONObject json, long myId) throws Exception {
		_createdAt = Time.getTwitterDate(json.getString("created_at"));
		_senderScreenName = json.getString("sender_screen_name");
		_sender = new UserJSON(json.getJSONObject("sender"));
		_text = Utils.unescape(json.getString("text"));
		_recipientScreenName = json.getString("recipient_screen_name");
		_id = json.getLong("id");
		_recipient = new UserJSON(json.getJSONObject("recipient"));
		_recipientId = json.getLong("recipient_id");
		_senderId = json.getLong("sender_id");
		_outgoing = (myId == _senderId);
	}

	private Calendar _createdAt;
	private String _senderScreenName;
	private User _sender;
	private String _text;
	private String _recipientScreenName;
	private long _id;
	private User _recipient;
	private long _recipientId;
	private long _senderId;
	private boolean _outgoing;
	private boolean _error;
	private boolean _sending;
	
	@Override
	public long getId() {
		return _id;
	}
	@Override
	public String getSender() {
		return _senderScreenName;
	}
	@Override
	public long getSenderId() {
		return _senderId;
	}
	@Override
	public String getSenderName() {
		return _sender.getName();
	}
	@Override
	public String getRecipient() {
		return _recipientScreenName;
	}
	@Override
	public long getRecipientId() {
		return _recipientId;
	}
	@Override
	public String getRecipientName() {
		return _recipient.getName();
	}
	@Override
	public String getBody() {
		return _text;
	}
	@Override
	public Calendar getCreatedAt() {
		return _createdAt;
	}
	@Override
	public User getSenderUser() {
		return _sender;
	}
	@Override
	public User getRecipientUser() {
		return _recipient;
	}
	@Override
	public String getThreadId() {
		if(_outgoing) {
			return _recipientId + "";
		} else {
			return _senderId + "";
		}
	}
	@Override
	public String getSenderProfilePic() {
		return _sender.getProfileImageUrl();
	}
	@Override
	public String getRecipientProfilePic() {
		return _recipient.getProfileImageUrl();
	}
	@Override
	public boolean isOutgoing() {
		return _outgoing;
	}
	public void setIsError(boolean error) {
		_error = error;
	}
	public boolean isError() {
		return _error;
	}
	public void setIsSending(boolean sending) {
		_sending = sending;
	}
	public boolean isSending() {
		return _sending;
	}
	
	public static DirectMessage createErrorMessage(User sender, long recipientId, String recipientScreen, String recipientProfileImg, String body) {
		DirectMessageJSON msg = new DirectMessageJSON();
		msg._sender = sender;
		msg._senderId = sender.getId();
		msg._senderScreenName = sender.getScreenName();
		msg._recipientId = recipientId;
		msg._recipientScreenName = recipientScreen;
		msg._recipient = new UserJSON(recipientId, recipientScreen, recipientProfileImg);
		msg._text = body;
		return msg;
	}
	
	public static DirectMessage[] createMessageList(JSONArray array, boolean outgoing) throws Exception {
        ArrayList<DirectMessage> toReturn = new ArrayList<DirectMessage>();
        for(int i = 0; i < array.length(); i++) {
            toReturn.add(new DirectMessageJSON(array.getJSONObject(i), outgoing));
        }
        return toReturn.toArray(new DirectMessage[0]);
    }
}
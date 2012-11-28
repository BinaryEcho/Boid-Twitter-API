package com.teamboid.twitterapi.dm;

import com.teamboid.twitterapi.user.User;

import java.io.Serializable;

/**
 * Represents a direct message, sent to another user or received from another user.
 *
 * @author Aidan Follestad
 */
public interface DirectMessage extends Serializable, BaseMessage {

    /**
     * Gets the {@link User} object of the user that sent the direct message.
     * If you only want the screen name of the sending user, use {@link com.teamboid.twitterapi.dm.DirectMessage#getSenderScreenName()}
     */
    User getSenderUser();

    /**
     * Gets the {@link User} object of the user that received the direct message.
     * If you only want the screen name of the recipient user, use {@link com.teamboid.twitterapi.dm.DirectMessage#getRecipientScreenName()}
     */
    User getRecipientUser();
}

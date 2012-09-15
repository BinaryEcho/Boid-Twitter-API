package com.teamboid.twitterapi.client;

/**
 * Passes paging information to any API functions that can include page, count, since_id, or max_id parameters.
 * @author Aidan Follestad
 */
public class Paging {
	
	/**
     * Initializes new Paging instance.
     */
    public Paging() {
    	_count = 25;
    }

    /**
     * Initializes new Paging instance.
     * @param count The number of results to retrieve.
     */
    public Paging(int count) {
        _count = count;
    }

    private int _page;
    private int _count;
    private long _sinceId;
    private long _maxId;

    /**
     * @deprecated See https://dev.twitter.com/docs/working-with-timelines
     */
    public int getPage() { return _page; }
    public int getCount() { return _count; }
    public long getSinceId() { return _sinceId; }
    public long getMaxId() { return _maxId; }

    /**
     * @deprecated See https://dev.twitter.com/docs/working-with-timelines
     */
    public Paging setPage(int page) {
        _page = page;
        return this;
    }

    public Paging setCount(int count) {
        _count = count;
        return this;
    }

    public Paging setSinceId(long sinceId) {
        _sinceId = sinceId;
        return this;
    }

    public Paging setMaxId(long maxId) {
        _maxId = maxId;
        return this;
    }

    public String getUrlString(char startingCharacter, boolean includeSinceMaxId) {
        String toReturn = "";
        boolean insertedStarting = false;
        if(_page > 0) {
            toReturn += startingCharacter;
            insertedStarting = true;
            toReturn += "page=" + _page;
        }
        if(_count > 0) {
            if(!insertedStarting) {
                toReturn += startingCharacter;
                insertedStarting = true;
            } else toReturn += "&";
            toReturn += "count=" + _count;
        }
        if(includeSinceMaxId) {
            if(_sinceId > 0) {
                if(!insertedStarting) {
                    toReturn += startingCharacter;
                    insertedStarting = true;
                } else toReturn += "&";
                toReturn += "since_id=" + _sinceId;
            }
            if(_maxId > 0) {
                if(!insertedStarting) {
                    toReturn += startingCharacter;
                    insertedStarting = true;
                } else toReturn += "&";
                toReturn += "max_id=" + _maxId;
            }
        }
        return toReturn;
    }

    @Override
    public String toString() {
        return getUrlString('?', true);
    }
}
package com.terasoft.terautils.TeraTwitter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import twitter4j.DirectMessage;
import twitter4j.Friendship;
import twitter4j.IDs;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TeraTwitter {

	private final String apiKey, apiSecret, aktokKey,aktokSecret;
	private final Twitter twitter;
	
	public Twitter getTwitter() {
		return twitter;
	}

	public TeraTwitter(String apiKey, String apiSecret, String aktokKey,
			String aktokSecret) {
		super();
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
		this.aktokKey = aktokKey;
		this.aktokSecret = aktokSecret;
		
		twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(apiKey, apiSecret);
		twitter.setOAuthAccessToken(new AccessToken(aktokKey, aktokSecret));
	}

	public void tweet(String tweet) throws TwitterException {
		// TODO Auto-generated method stub
		twitter.updateStatus(tweet);
	}

	public void tweet(String tweet, String img) throws TwitterException {
		StatusUpdate status = new StatusUpdate(tweet);
		status.setMedia(new File(img));
		twitter.updateStatus(status);
	}

	public QueryResult search(String query) throws TwitterException {
		// TODO Auto-generated method stub
		 return twitter.search(new Query(query));			 
	}

	public IDs getWhoIsFollowingMe() throws IllegalStateException, TwitterException
	{
		return twitter.getFollowersIDs(twitter.getScreenName(),-1);
	}
	
	public void followWhoIAmNotFollwing(IDs users) throws TwitterException
	{
		ResponseList<Friendship> friendships = twitter.lookupFriendships(users.getIDs());		
		for(Friendship friendship : friendships)
		{
			if(!friendship.isFollowing())
			{				
				twitter.createFriendship(friendship.getId());
			}
		}
	}
	
	public void delDirectMessage(long id) throws TwitterException
	{
		twitter.destroyDirectMessage(id);
	}
}

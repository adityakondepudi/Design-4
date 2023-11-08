//Time Complexity  : O(n)
//Space Complexity : O(n)

class Twitter {
    class Tweet{
        int tid;
        int createdAt;
        public Tweet(int id, int time){
            this.tid = id;
            this.createdAt = time;
        }
    }

    HashMap<Integer, HashSet<Integer>> usersMap;
    HashMap<Integer, List<Tweet>> tweets;
    int time;

    public Twitter() {
        this.usersMap = new HashMap<>();
        this.tweets = new HashMap<>();
    }
    
    public void postTweet(int userId, int tweetId) {
       // if(!usersMap.containsKey(userId)){
       //     follow(userId, userId);
      //  }
        if(!tweets.containsKey(userId)){
            follow(userId, userId);
            tweets.put(userId, new ArrayList<>());
        }
        time++;
        tweets.get(userId).add(new Tweet(tweetId, time));
    }
    
    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> pq = new PriorityQueue<>((a,b) -> a.createdAt - b.createdAt);
        List<Integer> result = new ArrayList<>();
        if(!usersMap.containsKey(userId)){
            HashSet<Integer> followeds = usersMap.get(userId);
            for(int fId: followeds){
                List<Tweet> fTweets = tweets.get(fId);
                if(fTweets!=null){
                    int k=fTweets.size();
                    for(int j=k-1; j>=0 && j>=k -12;j--){
                        pq.add(fTweets.get(j));
                        if(pq.size() > 10){
                            pq.poll();
                        }
                    }
                }
            }
        } 
        while(!pq.isEmpty()){
            result.add(0, pq.poll().tid);
        }
        return result;
    }
    
    public void follow(int followerId, int followeeId) {
        if(!usersMap.containsKey(followeeId)){
            usersMap.put(followerId, new HashSet<>());
        }
        usersMap.get(followerId).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        if(followerId != followeeId && usersMap.containsKey(followerId)){
            usersMap.get(followerId).add(followeeId);
        }
    }
}

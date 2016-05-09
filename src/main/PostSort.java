package main;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import java.util.Comparator;
import java.util.List;

/**
 * Created by rishabh on 7/5/16.
 */
public class PostSort {

    List<Status> getPosts() throws TwitterException {

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey("wFzJrRbsyvgH1RIAhDc1SM4Mr")
                .setOAuthConsumerSecret("yK8nvVGgGqyELcelwZ1lZvSPJz6h4v3os0pkOY6WqzECncozy8")
                .setOAuthAccessToken("4920232963-S70ZejB9CTMWHIxT3dL9etLUR4jpYJIKA6rXCZ2")
                .setOAuthAccessTokenSecret("HwFVQGEjHPi51ZJzrwkgxc1p32PgaU8ApPVP3I7BDKpWV");

        Twitter twitter = new TwitterFactory(configurationBuilder.build()).getInstance();
        return twitter.getHomeTimeline();
    }

    void pretty(Status status) {
        System.out.println("##############  Text  #############:" + status.getText() + "\n");
        System.out.println("############   Likes   ##############:" + status.getFavoriteCount() + "\n");
        System.out.println("##########    ReTweets   ############:" + status.getRetweetCount() + "\n");
    }

    public static void main(String[] args) {

        try {
            PostSort obj = new PostSort();
            List<Status> list = obj.getPosts();

            Comparator<Status> dateComparator = (Status first, Status second) -> first.getCreatedAt().compareTo(second.getCreatedAt());
            Comparator<Status> reTweetComparator = (Status one, Status two) -> ((Integer) one.getRetweetCount()).compareTo((Integer) two.getRetweetCount());
            Comparator<Status> likeComparator = (Status one, Status two) -> ((Integer) one.getFavoriteCount()).compareTo((Integer) two.getFavoriteCount());

            list.sort(dateComparator);
            System.out.println("Date:");
            list.stream().forEach(obj::pretty);

            list.sort(likeComparator);
            System.out.println("Likes:");
            list.stream().forEach(obj::pretty);

            list.sort(reTweetComparator);
            System.out.println("ReTweets:");
            list.stream().forEach(obj::pretty);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
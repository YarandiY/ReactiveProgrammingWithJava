package ir.tourismit.tit.AkkaStream;


import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.stream.javadsl.Source;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class AkkaStreamMain {

    static ActorSystem system = ActorSystem.create("reactive-tweets");
    static final Hashtag AKKA = new Hashtag("#akka");

    public static void main(String[] args) {
        TweetHandler tweetHandler = new TweetHandler(sampleTweets(), system);
        System.out.println("First, it prints the authors of the tweets that have #akka and then prints the number of all tweets.");
        tweetHandler.printAuthors(AKKA); //This function is asynchronous
        CompletionStage<Integer> sum = tweetHandler.numberOfTweets();//This function is asynchronous
        sum.thenRun(() -> {
            try {
                System.out.println(sum.toCompletableFuture().get());
            } catch (Exception e) {
                e.printStackTrace();
            }
            system.terminate();
        });
    }

    private static Source<Tweet, NotUsed> sampleTweets() {
        List<Tweet> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Tweet tweet;
            if (i % 4 == 0)
                tweet = new Tweet(new Author("author name is: akka" + i), System.currentTimeMillis(), "tweet" + i + " #akka");
            else
                tweet = new Tweet(new Author("author name is: rx" + i), System.currentTimeMillis(), "tweet" + i + " #rx");
            list.add(tweet);
        }
        return Source.from(list);
    }
}

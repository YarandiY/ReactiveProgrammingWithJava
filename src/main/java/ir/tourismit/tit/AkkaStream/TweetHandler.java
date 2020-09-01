package ir.tourismit.tit.AkkaStream;

import akka.Done;
import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;

import java.util.concurrent.CompletionStage;


public class TweetHandler {

    private final Source<Tweet, NotUsed> tweets;
    private final ActorSystem system;

    public TweetHandler(Source<Tweet, NotUsed> tweets, ActorSystem system) {
        this.tweets = tweets;
        this.system = system;
    }


    public void printAuthors(Hashtag hashtag) {
        Source<Author, NotUsed> authors = tweets.filter(t -> t.hashtags.contains(hashtag)).map(t -> t.author);
        Sink<Author, CompletionStage<Done>> printSink = Sink.foreach(a -> System.out.println(a.name));
        authors.runWith(printSink, system);
    }

    public CompletionStage<Integer> numberOfTweets() {
        Sink<Integer, CompletionStage<Integer>> sumSink = Sink.fold(0, (acc, elem) -> acc + elem);
        return tweets.map(t -> 1).runWith(sumSink, system);
    }

}

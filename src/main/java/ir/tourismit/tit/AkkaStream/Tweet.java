package ir.tourismit.tit.AkkaStream;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Tweet {
    public final Author author;
    public final long timestamp;
    public final String body;
    public final Set<Hashtag> hashtags;

    public Tweet(Author author, long timestamp, String body) {
        this.author = author;
        this.timestamp = timestamp;
        this.body = body;
        hashtags = Arrays.asList(body.split(" ")).stream()
                .filter(a -> a.startsWith("#"))
                .map(a -> new Hashtag(a))
                .collect(Collectors.toSet());
    }

    // ...
}

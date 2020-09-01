package ir.tourismit.tit.AkkaStream;

import java.util.Objects;

public class Hashtag {
    public final String name;

    public Hashtag(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hashtag hashtag = (Hashtag) o;
        return Objects.equals(name, hashtag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}

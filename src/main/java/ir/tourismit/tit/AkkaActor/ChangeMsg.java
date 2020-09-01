package ir.tourismit.tit.AkkaActor;

public class ChangeMsg implements Commend {

    public final String body;

    public ChangeMsg(String body) {
        this.body = body;
    }
}

package ir.tourismit.tit.AkkaActor;


import akka.actor.typed.ActorRef;

public class ChangeMsg implements Commend {

    public final String body;
    public final ActorRef<Commend> sender;

    public ChangeMsg(String body, ActorRef<Commend> sender) {
        this.body = body;
        this.sender = sender;
    }

}

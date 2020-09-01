package ir.tourismit.tit.AkkaActor;

import akka.actor.typed.ActorSystem;

public class AkkaActorMain {

    static ActorSystem<Commend> system = ActorSystem.create(Commander.create(), "reactive-message-passing");

    public static void main(String[] args) {
        system.tell(SimpleCommend.CHANGE);
        system.terminate();
    }
}

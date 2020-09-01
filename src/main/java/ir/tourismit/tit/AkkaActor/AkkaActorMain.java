package ir.tourismit.tit.AkkaActor;

import akka.actor.typed.ActorSystem;

public class AkkaActorMain {

    static ActorSystem<Commend> system = ActorSystem.create(SampleActor.create(), "reactive-message-passing");

    public static void main(String[] args) {
        system.tell(PrintMsg.INSTANCE);
        system.tell(new ChangeMsg("this value has been changed"));
        system.tell(PrintMsg.INSTANCE);
    }
}

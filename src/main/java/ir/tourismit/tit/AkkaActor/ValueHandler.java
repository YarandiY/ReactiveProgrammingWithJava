package ir.tourismit.tit.AkkaActor;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;

public class ValueHandler {
    private final ActorContext<Commend> context;

    private ValueHandler(ActorContext<Commend> context){
        this.context = context;
    }

    public static Behavior<Commend> create(){
        return Behaviors.setup(
                ctx -> new ValueHandler(ctx).changeState("default value"));
    }

    private Behavior<Commend> changeState(String value) {
        return Behaviors.receive(Commend.class)
                .onMessageEquals(SimpleCommend.PRINT, () -> onPrintMsg(value))
                .onMessage(ChangeMsg.class, this::onChangeMsg)
                .build();
    }

    private Behavior<Commend> onChangeMsg(ChangeMsg message) {
        message.sender.tell(SimpleCommend.DONE);
        return changeState(message.body);
    }

    private Behavior<Commend> onPrintMsg(String value) {
        System.out.println(value);
        return Behaviors.same();
    }
}
package ir.tourismit.tit.AkkaActor;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class ValueHandler extends AbstractBehavior<Commend> {
    private String value = "hello world!";

    public static Behavior<Commend> create(){
        return Behaviors.setup(contex -> new ValueHandler(contex));
    }

    private ValueHandler(ActorContext<Commend> context) {
        super(context);
    }

    @Override
    public Receive<Commend> createReceive() {
        return newReceiveBuilder()
                .onMessageEquals(SimpleCommend.PRINT, this::onPrintMsg)
                .onMessage(ChangeMsg.class,this::onChangeMsg)
                .build();
    }

    private Behavior<Commend> onChangeMsg(ChangeMsg message) {
        value = message.body;
        message.sender.tell(SimpleCommend.DONE);
        return this;
    }

    private Behavior<Commend> onPrintMsg(){
        System.out.println(value);
        return this; // In this case we don’t need to update any state, so we return this, which means the next behavior is “the same as the current one”.
    }
}
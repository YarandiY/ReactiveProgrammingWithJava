package ir.tourismit.tit.AkkaActor;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class SampleActor extends AbstractBehavior<Commend> {
    private String value = "hello world!";

    public static Behavior<Commend> create(){
        return Behaviors.setup(contex -> new SampleActor(contex));
    }

    private SampleActor(ActorContext<Commend> context) {
        super(context);
    }

    @Override
    public Receive<Commend> createReceive() {
        return newReceiveBuilder()
                .onMessageEquals(PrintMsg.INSTANCE, this::onPrintMsg)
                .onMessage(ChangeMsg.class,this::onChangeMsg)
                .build();
    }

    private Behavior<Commend> onChangeMsg(ChangeMsg message) {
        value = message.body;
        return this;
    }

    private Behavior<Commend> onPrintMsg(){
        System.out.println(value);
        return this;
    }
}
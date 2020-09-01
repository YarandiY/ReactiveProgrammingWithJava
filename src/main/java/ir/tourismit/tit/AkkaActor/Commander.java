package ir.tourismit.tit.AkkaActor;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class Commander extends AbstractBehavior<Commend> {

    private final ActorRef<Commend> valueHandler = getContext().spawn(ValueHandler.create(), "valueHandler");

    public static Behavior<Commend> create() {
        return Behaviors.setup(Commander::new);
    }

    private Commander(ActorContext<Commend> context) {
        super(context);
    }

    @Override
    public Receive<Commend> createReceive() {
        return newReceiveBuilder()
                .onMessageEquals(SimpleCommend.CHANGE, this::onChange)
                .onMessageEquals(SimpleCommend.DONE, this::onDone)
                .build();
    }

    private Behavior<Commend> onDone() {
        valueHandler.tell(SimpleCommend.PRINT);
        return Behaviors.same();
    }

    private Behavior<Commend> onChange() {
        valueHandler.tell(new ChangeMsg("new value", getContext().getSelf()));
        return Behaviors.same();
    }

}

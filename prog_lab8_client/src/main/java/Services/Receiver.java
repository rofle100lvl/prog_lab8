package Services;

import model.Flat;
import utils.Connector;
import utils.Response;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Receiver implements Callable<Response> {
    private Connector connector;
    Consumer<Response> answerWithCollection;
    Consumer<Response> answerWithoutCollection;

    public Receiver(Connector connector,
                    Consumer<Response> answerWithCollection,
                    Consumer<Response> answerWithoutCollection) {
        this.connector = connector;
        this.answerWithCollection = answerWithCollection;
        this.answerWithoutCollection = answerWithoutCollection;
    }

    @Override
    public Response call() throws Exception {

        while (true) {
            Response serverResponse = connector.receive();
            if (serverResponse != null) {
                if (Objects.nonNull(serverResponse.getFlats())) {
                    //Перерисовываем таблицу
                    answerWithCollection.accept(serverResponse);
                    answerWithoutCollection.accept(serverResponse);
                    System.out.println(serverResponse.getFlats());
                }
                else {
                    //Выводим окошко с респонсом
                    answerWithoutCollection.accept(serverResponse);
                }
            }
        }

    }
}

package Services;

import utils.Connector;
import utils.Response;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Receiver implements Callable<Response> {
    private Connector connector;
    Runnable answerWithCollection;
    Runnable answerWithoutCollection;

    public Receiver(Connector connector, Runnable answerWithCollection, Runnable answerWithoutCollection) {
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
                    answerWithCollection.run();
                    System.out.println(serverResponse.getFlats());
                }
                else {
                    //Выводим окошко с респонсом
                    answerWithoutCollection.run();
                }
            }
        }

    }
}

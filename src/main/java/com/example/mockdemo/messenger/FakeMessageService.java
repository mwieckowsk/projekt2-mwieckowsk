

import java.util.Random;

public class FakeMessageService implements MessageService {
    @Override
    public ConnectionStatus checkConnection(String server) {
        if (server.matches("[a-z].*.pl$"))
            return ConnectionStatus.SUCCESS;

        return ConnectionStatus.FAILURE;
    }

    @Override
    public SendingStatus send(String server, String contents) throws MalformedRecipientException {
        if (server.length() < 4 || contents.length() < 3)
            throw new MalformedRecipientException();

        if (new Random().nextBoolean())
            return SendingStatus.SENT;

        return SendingStatus.SENDING_ERROR;
    }
}

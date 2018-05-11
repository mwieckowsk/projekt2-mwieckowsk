package messenger;
import messenger.ConnectionStatus;

public class FakeMessageService implements MessageService {
    @Override
    public ConnectionStatus checkConnection(String server) {
        if (server == "google.pl") {
            return ConnectionStatus.SUCCESS;
        } else return ConnectionStatus.FAILURE;
    }

    @Override
    public SendingStatus send(String server, String message) throws MalformedRecipientException {
        if (checkConnection(server) == ConnectionStatus.SUCCESS && message.length() >= 3) {
        	return SendingStatus.SENT;
        } else if (checkConnection(server) == ConnectionStatus.FAILURE) {
        	return SendingStatus.SENDING_ERROR;
        } else throw new MalformedRecipientException();
    }
}

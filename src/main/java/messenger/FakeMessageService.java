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
    	if (server == "ErrorSending") {
    	   	return SendingStatus.SENDING_ERROR;
    	}
        if (server == "google.pl" && message.length() >= 3) {
        	return SendingStatus.SENT;
        } else throw new MalformedRecipientException();
    }
}

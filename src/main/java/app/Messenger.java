package app;

import messenger.ConnectionStatus;
import messenger.MalformedRecipientException;
import messenger.MessageService;
import messenger.SendingStatus;
import messenger.FakeMessageService;

public class Messenger {
    private MessageService ms;

    public Messenger(MessageService ms) {
        this.ms = ms;
    }

    public Messenger() {

    }

    public int testConnection(String server) {
        if (ms.checkConnection(server) != ConnectionStatus.SUCCESS) {
            return 1;
        } else return 0;
    }

    public int sendMessage(String server, String message) {

        try {
            if (ms.send(server, message) == SendingStatus.SENT)
                return 1;
        } catch (MalformedRecipientException e) {
            return 2;
        }
        return 0;
    }
}
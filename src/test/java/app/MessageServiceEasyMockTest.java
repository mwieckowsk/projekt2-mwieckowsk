package app;

import org.junit.Before;
import org.junit.Test;

import messenger.MalformedRecipientException;
import messenger.MessageService;
import messenger.SendingStatus;
import messenger.ConnectionStatus;

import org.easymock.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.easymock.EasyMock.*;

public class MessageServiceEasyMockTest {

    private final String SERVER = "google.pl";
    private final String MESS = "Hello I iz Me";
    private Messenger mes;
    private MessageService ms;
    
    @Before
    public void Initialize() {
    	ms = createNiceMock(MessageService.class);
        mes = new Messenger(ms);
    }
    
    @Test
    public void CheckingConnectionToSuccess(){
        expect(ms.checkConnection(SERVER)).andReturn(ConnectionStatus.SUCCESS);
        replay(ms);
        assertThat(mes.testConnection(SERVER), is(0));
    }
    
    @Test
    public void CheckingConnectionToFailure() {
        expect(ms.checkConnection(SERVER)).andReturn(ConnectionStatus.FAILURE);
        replay(ms);
        assertThat(mes.testConnection(SERVER), is(1));
    }
        
    @Test
    public void CheckingSendingAndConnectionSuccess() throws MalformedRecipientException {
    	expect(ms.checkConnection(SERVER)).andReturn(ConnectionStatus.SUCCESS);
    	expect(ms.send(SERVER, MESS)).andReturn(SendingStatus.SENT);
        replay(ms);
        assertThat(mes.sendMessage(SERVER, MESS), is(0));
    }
    
    @Test
    public void CheckingSendingMethodSendingError() throws MalformedRecipientException {
    	expect(ms.checkConnection(SERVER)).andReturn(ConnectionStatus.FAILURE);
        expect(ms.send(SERVER, MESS)).andReturn(SendingStatus.SENDING_ERROR);
        replay(ms);
        assertThat(mes.sendMessage(SERVER, MESS), is(1));
    }
    
    @Test
    public void CheckingSendingMethodException() throws MalformedRecipientException {
        expect(ms.send(null, null)).andThrow(new MalformedRecipientException());
        replay(ms);
        assertThat(mes.sendMessage(null, null), is(2));
    }
    
    @Test
    public void CheckingConnectionSuccessAndSendingError() throws MalformedRecipientException {
    	expect(ms.checkConnection(SERVER)).andReturn(ConnectionStatus.SUCCESS);
    	expect(ms.send(SERVER, null)).andThrow(new MalformedRecipientException());
        replay(ms);
        assertThat(mes.sendMessage(SERVER, null), is(2));
    }
    
    
}

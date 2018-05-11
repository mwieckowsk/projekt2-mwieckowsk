package app;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import messenger.MalformedRecipientException;
import messenger.MessageService;
import messenger.SendingStatus;
import messenger.ConnectionStatus;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class MessageServiceMockitoTest {

    private final String SERVER = "google.pl";
    private final String MESS = "Hello I iz Me";
    
	@Mock
    private Messenger mes;
    private MessageService ms;
    
    @Before
    public void Initialize() {
    	ms = mock(MessageService.class);
        mes = new Messenger(ms);
    }
    
    @Test
    public void CheckingConnectionToSuccess() {
    	when(ms.checkConnection(SERVER)).thenReturn(ConnectionStatus.SUCCESS);
    	assertThat(mes.testConnection(SERVER), is(0));
    }
    
    @Test
    public void CheckingConnectionToFailure() {
    	when(ms.checkConnection(SERVER)).thenReturn(ConnectionStatus.FAILURE);
    	assertThat(mes.testConnection(SERVER), is(1));
    }
    
    @Test
    public void CheckingSendingMethodSent() throws MalformedRecipientException {
    	when(ms.checkConnection(SERVER)).thenReturn(ConnectionStatus.SUCCESS);
    	when(ms.send(SERVER, MESS)).thenReturn(SendingStatus.SENT);
    	assertThat(mes.sendMessage(SERVER, MESS), is(0));
    }
    
    @Test
    public void CheckingSendingMethodSendingError() throws MalformedRecipientException {
    	when(ms.checkConnection(SERVER)).thenReturn(ConnectionStatus.FAILURE);
    	when(ms.send(SERVER, MESS)).thenReturn(SendingStatus.SENDING_ERROR);
    	assertThat(mes.sendMessage(SERVER, MESS), is(1));
    }
    
    @Test
    public void CheckingSendingMethodException() throws MalformedRecipientException {
    	when(ms.send(null, null)).thenThrow(new MalformedRecipientException());
    	assertThat(mes.sendMessage(null, null), is(2));
    }
    
    @Test
    public void CheckingConnectionSuccessAndSendingError() throws MalformedRecipientException {
    	when(ms.checkConnection(SERVER)).thenReturn(ConnectionStatus.SUCCESS);
    	when(ms.send(SERVER, null)).thenThrow(new MalformedRecipientException());
    	assertThat(mes.sendMessage(SERVER, null), is(2));
    }
}

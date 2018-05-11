package app;

import org.mockito.Mock;
import org.junit.*;
import messenger.*;

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
    	int checking = mes.testConnection(SERVER);
    	verify(ms).checkConnection(SERVER);
    	assertThat(checking, is(0));
    }
    
    @Test
    public void CheckingConnectionToFailure() {
    	when(ms.checkConnection(SERVER)).thenReturn(ConnectionStatus.FAILURE);
    	int checking = mes.testConnection(SERVER);
    	verify(ms).checkConnection(SERVER);
    	assertThat(checking, is(1));
    }
    
    @Test
    public void CheckingSendingMethodSent() throws MalformedRecipientException {
    	when(ms.checkConnection(SERVER)).thenReturn(ConnectionStatus.SUCCESS);
    	when(ms.send(SERVER, MESS)).thenReturn(SendingStatus.SENT);
    	int checking = mes.sendMessage(SERVER, MESS);
    	verify(ms).send(SERVER, MESS);
    	assertThat(checking, is(0));
    }
    
    @Test
    public void CheckingSendingMethodSendingError() throws MalformedRecipientException {
    	when(ms.checkConnection(SERVER)).thenReturn(ConnectionStatus.FAILURE);
    	when(ms.send(SERVER, MESS)).thenReturn(SendingStatus.SENDING_ERROR);
    	int checking = mes.sendMessage(SERVER, MESS);
    	verify(ms).send(SERVER, MESS);
    	assertThat(checking, is(1));
    }
    
    @Test
    public void CheckingSendingMethodException() throws MalformedRecipientException {
    	when(ms.send(null, null)).thenThrow(new MalformedRecipientException());
    	int checking = mes.sendMessage(null, null);
    	verify(ms).send(null, null);
    	assertThat(checking, is(2));
    }
    
    @Test
    public void CheckingConnectionSuccessAndSendingError() throws MalformedRecipientException {
    	when(ms.checkConnection(SERVER)).thenReturn(ConnectionStatus.SUCCESS);
    	when(ms.send(SERVER, null)).thenThrow(new MalformedRecipientException());
    	int checking = mes.sendMessage(SERVER, null);
    	verify(ms).send(SERVER, null);
    	assertThat(checking, is(2));
    }
}

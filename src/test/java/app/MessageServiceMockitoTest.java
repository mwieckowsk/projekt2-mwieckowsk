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
    	when(ms.checkConnection("google.pl")).thenReturn(ConnectionStatus.SUCCESS);
    	int result = mes.testConnection("google.pl");
    	assertThat(result, is(equalTo(0)));
    }
}

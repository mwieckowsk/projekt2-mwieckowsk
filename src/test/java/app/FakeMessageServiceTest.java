package app;

import messenger.FakeMessageService;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class FakeMessageServiceTest {

    private Messenger mes;

    @Before
    public void Initialize() {
        mes = new Messenger(new FakeMessageService());
    }

    @Test
    public void CheckingConnectionToSuccess() {
        assertThat(mes.testConnection("google.pl"), is(0));
    }

    @Test
    public void CheckingConnectionToFailure() {
        assertThat(mes.testConnection("google.com"), is(1));
    }

    @Test
    public void ValidMessageAndServer() {
        assertThat(mes.sendMessage("google.pl", "cos tam cos"), is(equalTo(0)));
    }
    
    @Test
    public void InvalidMessageAndValidServer() {
    	assertThat(mes.sendMessage("google.pl", "C"), is(2));
    }
    
    @Test
    public void ValidMessageAndInvalidServer() {
    	assertThat(mes.sendMessage("ErrorSending", "cos tam cos"), is(equalTo(1)));
    }
    
    @Test 
    public void InvalidMessageAndInvalidServer() {
    	assertThat(mes.sendMessage("ErrorSending", "c"), is(anyOf(equalTo(1), equalTo(2))));
    }
    
}

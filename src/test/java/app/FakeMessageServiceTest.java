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
    public void ConnectionToValidServer() {
        assertThat(mes.testConnection("google.pl"), is(0));
    }

    @Test
    public void ConnectionToInvalidServer() {
        assertThat(mes.testConnection("google.com"), is(1));
    }

    @Test
    public void ValidMessageAndValidServer() {
        assertThat(mes.sendMessage("Ciastko", "google.pl"), is(0));
    }
}

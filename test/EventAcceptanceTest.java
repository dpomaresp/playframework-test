import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import models.Event;
import org.junit.Test;
import play.libs.Json;
import play.test.WithServer;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class EventAcceptanceTest extends WithServer{
    @Test
    public void testInServer() throws Exception {
        int timeout = 5000;
        Event event = new Event(null, 10023L, 12222L, null, null, null, null, null, null, null, null, "adsf");

        Unirest unirest = new Unirest();
        unirest.setObjectMapper(new com.mashape.unirest.http.ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        String url = "http://localhost:" + this.testServer.port() + "/event/save";
        HttpResponse<Boolean> response = unirest.post(url)
                .header("content-type", "application/json")
                .body(event)
                .asObject(Boolean.class);

        assertEquals(true, response.getBody());


        /*try (WSClient ws = WS.newClient(this.testServer.port())) {
            CompletionStage<WSResponse> stage = ws.url(url).get();
            WSResponse response = stage.toCompletableFuture().get();
            assertEquals(NOT_FOUND, response.getStatus());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

}

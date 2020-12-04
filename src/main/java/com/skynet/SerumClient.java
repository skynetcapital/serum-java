package com.skynet;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ProtocolException;

public class SerumClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(SerumClient.class);
    private String host;
    private int port;

    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();
    private JsonParser jsonParser = new JsonParser();
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private String skynetPubkey = "skynetDj29GH6o6bAqoixCpDuYtWqi1rm8ZNx1hB3vq";

    // TODO: get serum balance of a given address/pubkey (or just our own balance is fine)

    public String getBalance() {
        String json = "{ \"jsonrpc\":\"2.0\", \"id\": 1, \"method\": \"getBalance\", \"params\": [\"" + skynetPubkey + "\"]}";
        String endpoint = "getBalance";
        final String url = String.format("https://api.mainnet-beta.solana.com/%s", endpoint);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            LOGGER.info("Getting balance of pubkey: {}", skynetPubkey);
            LOGGER.info("Response: {}", responseBody);
            return responseBody;
        } catch (ProtocolException exception) {
            throw new RuntimeException("Error communicating with Wallet API, check IP and ports.");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}

package com.faisaldev.openkitchen.dtos;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class PaymentRequest {
    private String msisdn = "254708633994";
    private String amount = "10";
    private String currencycode = "KES";
    private String timestamp = "2025-06-25 12:28:42.000";
    private String accountno = "254711959143";
    private String username = "janty";
    private String password = "b0c95e2144bdd4c86b94501a814f9bbd9d025651d8497df04b7b7f318fe5172088c491906756a67727f6ea964e9caf1c034bf9bb267b821e6b43cb3dcc569d0f";
    private String clientid = "5094";
    private String serviceid = "6164";
//    private String resulturl = "https://test-api.ekenya.co.ke/pg3ds/api/v1/cards3dsResult/receive";
    private String resulturl = "https://test-portal.ekenya.co.ke/mobile-banking/pg3DS-portal";
    private String narration = "Three Dimension";
    private String transactionid = UUID.randomUUID().toString();
    private String payload = "kfHraexF4Hn7jkM3gZpKeEa5n9eUkCv33W9VnNQamT40ROrOW4SLO1fR20o9yDFOEGM+cwEW1cXPNaGXQ1sLO8TlHBx/XhoAEgz+Grl9PEONJ1k+2mVXVb4p1gY27nlgJvfdn5sF8FJO758iYZsRAGsxJ4gTv79TFpmBW6RvMvBuX/zAW4RKYlELkELxVJq9Y5DD7o0RAWbw1vqnqB55o68ZLi0AiemUrvEs/aHqzqFKMbFANW2gfmxcvG8AHsCqTSC9WWRndIsM6v3urIuOyqR9hSHevt8zb9PtsYVy001sExli3gLAEsJvUQcOhxwfP1Ki+8JODa6NYzVROJpW3g==";


    // Default Constructor
    public PaymentRequest() {}

    // Getters and Setters
    public String getMsisdn() { return msisdn; }
    public void setMsisdn(String msisdn) { this.msisdn = msisdn; }

    public String getAmount() { return amount; }
    public void setAmount(String amount) { this.amount = amount; }

    public String getCurrencycode() { return currencycode; }
    public void setCurrencycode(String currencycode) { this.currencycode = currencycode; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public String getAccountno() { return accountno; }
    public void setAccountno(String accountno) { this.accountno = accountno; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getClientid() { return clientid; }
    public void setClientid(String clientid) { this.clientid = clientid; }

    public String getServiceid() { return serviceid; }
    public void setServiceid(String serviceid) { this.serviceid = serviceid; }

    public String getResulturl() { return resulturl; }
    public void setResulturl(String resulturl) { this.resulturl = resulturl; }

    public String getNarration() { return narration; }
    public void setNarration(String narration) { this.narration = narration; }

    public String getTransactionid() { return transactionid; }
    public void setTransactionid(String transactionid) { this.transactionid = transactionid; }

    public String getPayload() { return payload; }
    public void setPayload(String payload) { this.payload = payload; }

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "msisdn='" + msisdn + '\'' +
                ", amount='" + amount + '\'' +
                ", currencycode='" + currencycode + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", accountno='" + accountno + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", clientid='" + clientid + '\'' +
                ", serviceid='" + serviceid + '\'' +
                ", resulturl='" + resulturl + '\'' +
                ", narration='" + narration + '\'' +
                ", transactionid='" + transactionid + '\'' +
                ", payload='" + payload + '\'' +
                '}';
    }


    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("msisdn", msisdn);
        json.put("amount", amount);
        json.put("currencycode", currencycode);
        json.put("timestamp", timestamp);
        json.put("accountno", accountno);
        json.put("username", username);
        json.put("password", password);
        json.put("clientid", clientid);
        json.put("serviceid", serviceid);
        json.put("resulturl", resulturl);
        json.put("narration", narration);
        json.put("transactionid", transactionid);
        json.put("payload", payload);
        return json;
    }

}

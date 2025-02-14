package com.faisaldev.openkitchen.dtos;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentRequest {
    private String msisdn = "254708633994";
    private String amount = "10";
    private String currencycode = "KES";
    private String timestamp = "2019-06-25 12:28:42.000";
    private String accountno = "254711959143";
    private String username = "user@ekenya.co.ke";
    private String password = "e177c5bc4be6fd29add7ba7f5056609e1cecd408f7166c0705f744f7f64a2806cd3c9a448fa6c4e2bb810b41fdd945d9fb";
    private String clientid = "1030";
    private String serviceid = "6125";
    private String resulturl = "https://domain.com";
    private String narration = "Three Dimension";
    private String transactionid = "ECL_2.0_CARD_VER_001";
    private String payload = "M4pOMv19fnK0Gw76mbB/3v+o9F8XW2Zg==";

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

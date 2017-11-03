package com.capstone.djohnpulle.incidentreporter;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ReportRequest extends StringRequest {
    private static final String REPORT_REQUEST_URL = "http://10.0.2.2/insert.php";
    private Map<String, String> params;

    public ReportRequest(String title, String desc, int priority, String username, Response.Listener<String> listener) {
        super(Method.POST, REPORT_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("title", title);
        params.put("desc", desc);
        params.put("priority", priority + "");
        params.put("username", username);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

package com.capstone.djohnpulle.incidentreporter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.app.AlertDialog;

import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class ReportActivity extends AppCompatActivity {
    private String[] arraySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");

        TextView tvWelcomeMsg = (TextView) findViewById(R.id.welcome);

        // Display user details
        String message = "Welcome " + username;
        tvWelcomeMsg.setText(message);

        final EditText etTitle = (EditText) findViewById(R.id.title);
        final EditText etDesc = (EditText) findViewById(R.id.desc);
        final Button Logout = (Button) findViewById(R.id.logout);
        final Button Submit = (Button) findViewById(R.id.submit);

        this.arraySpinner = new String[] {
                "0", "1", "2", "3", "4"
        };
        final Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportActivity.this, LoginActivity.class);
                ReportActivity.this.startActivity(intent);
            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String title = etTitle.getText().toString();
                final String desc = etDesc.getText().toString();
                final int priority = s.getSelectedItemPosition();

                // REST code for POST to database goes here
                if (!title.equals("") && !desc.equals("")) {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ReportActivity.this);
                                    builder.setMessage("Submit succeeded")
                                            .setPositiveButton("Ok", null)
                                            .create()
                                            .show();
                                    etTitle.setText("");
                                    etDesc.setText("");
                                    s.setSelection(0);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ReportActivity.this);
                                    builder.setMessage("Submit failed")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    ReportRequest reportRequest = new ReportRequest(title, desc, priority, username, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(ReportActivity.this);
                    queue.add(reportRequest);
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ReportActivity.this);
                    builder.setMessage("Please enter a title and description")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                    return;
                }
            }
        });
        }
}
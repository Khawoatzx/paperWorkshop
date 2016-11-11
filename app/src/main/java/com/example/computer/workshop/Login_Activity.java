package com.example.computer.workshop;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Login_Activity extends AppCompatActivity {

    private Button btLog;
    private Button btRegis;
    private EditText edUser;
    private EditText edPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);


        setListener();
        validate();
    }

    private boolean validate() {
        String username = edUser.getText().toString();
        String password = edPass.getText().toString();

        if (username.isEmpty()) return false;

        if (password.isEmpty()) return  false;

        return true;
    }

    private void setListener() {
        btLog = (Button)findViewById(R.id.btLog);
        btRegis = (Button)findViewById(R.id.btRegis);
        edUser = (EditText)findViewById(R.id.edUser);
        edPass = (EditText)findViewById(R.id.edPass);

        btLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    //TODO
                    new Login_Activity.Login2(edUser.getText().toString(),
                            edPass.getText().toString()).execute();
                }else{
                    Toast.makeText(Login_Activity.this,"กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login_Activity.this, register_Activity.class);
                startActivity(i);
            }
        });
    }
    private class Login2 extends AsyncTask<Void, Void, String> {

        private String username;
        private String password;

        public Login2(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            Request request;
            Response response;

            RequestBody requestBody = new FormBody.Builder()
                    .add("username", username)
                    .add("password", password)
                    .build();
            request = new Request.Builder()
                    .url("http://kimhun55.com/pollservices/login.php")
                    .post(requestBody)
                    .build();
            try {
                response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    return response.body().string();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(Login_Activity.this,s,Toast.LENGTH_SHORT).show();

            try {
                JSONObject rootObj = new JSONObject(s);
                if(rootObj.has("result")){
                    JSONObject resultObj = rootObj.getJSONObject("result");
                    if(resultObj.getInt("result") == 1) {
                        Intent i = new Intent(Login_Activity.this, new_list_Activity.class);
                        startActivity(i);
                    }else {
                        Toast.makeText(Login_Activity.this, resultObj.getString("result_desc"), Toast.LENGTH_SHORT).show();
                    }
                }

            } catch (JSONException ex) {

            }

        }
    }
}

/*    private EditText edUser;
    private EditText edPass;
    private Button btLog;
    private Button btRegis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        setListener();
        validate();

    }

    private boolean validate() {

        //TODO Validate from
        String username = edUser.getText().toString();
        String password = edPass.getText().toString();

        if (username.isEmpty())
            return false;

        if (password.isEmpty())
            return false;

        return true;
    }

    private void setListener() {
        edUser = (EditText) findViewById(R.id.edUser);
        edPass = (EditText) findViewById(R.id.edPass);
        btLog = (Button) findViewById(R.id.btLog);
        btRegis = (Button) findViewById(R.id.btRegis);
        btLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){
                    //TODO

                    new Login(edUser.getText().toString(),
                            edPass.getText().toString()).execute();
                }else{
                    Toast.makeText(Login_Activity.this,"กรุณากรอกข้อมูลให้ครบถ้วน" , Toast.LENGTH_SHORT).show();
                }
            }
        });
        btRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login_Activity.this, register_Activity.class);
                startActivity(i);
            }
        });
    }


    }
    class Login extends AsyncTask<Void,Void,String>{
        private String username;
        private String password;

        public Login(String username,String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected String doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            Request request;
            Response response;

            RequestBody requestBody = new FormBody.Builder()
                    .add("username" , username)
                    .add("password" , password)
                    .build();

            request = new Request.Builder()
                    .url("http://kimhun55.com/pollservices/login.php")
                    .post(requestBody)
                    .build();

            try {

                response = client.newCall(request).execute(); //ทำการรอขอกับ Server

                if (response.isSuccessful()){
                    return response.body().string();
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(Login_Activity.this,s,Toast.LENGTH_SHORT).show();
            //Toast.makeText(Login_Activity.this, s , Toast.LENGTH_SHORT).show();
            try {
                JSONObject rootObj = new JSONObject(s);
                if (rootObj.has("result")){
                    JSONObject resultObj = rootObj.getJSONObject("result");
                    if (resultObj.getInt("result") == 1){
                        Intent i = new Intent(Login_Activity.this,new_list_Activity.class);
                        startActivity(i);

                    }else{
                        Toast.makeText(Login_Activity.this, resultObj.getString("result_desc"),Toast.LENGTH_SHORT).show();
                    }
                }


            }catch (JSONException ex) {

            }

        }
    }*/


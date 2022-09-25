package com.example.root.asynctask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private  Button button;
    private TextView FinalResult;
    private EditText Time;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tv1 =(TextView)findViewById(R.id.tv1);
        //tv1 =(TextView)findViewById(R.id.tv);
        button =(Button)findViewById(R.id.b1);
        FinalResult=(TextView)findViewById(R.id.tv1);
        Time  =(EditText)findViewById(R.id.et1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleepTime = Time.getText().toString();
                runner.execute(sleepTime);
            }
        });
    }
    public class AsyncTaskRunner extends AsyncTask<String,String,String>
    {
        private String resp;
        ProgressDialog progressDialog;


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this,
                    "ProgressDialog",
                    "Wait for "+Time.getText().toString()+ " seconds");
        }

        @Override
        protected String doInBackground(String... params)
        {

            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            //Toast.makeText(MainActivity.this, "hai", Toast.LENGTH_SHORT).show();
            try {
                int time = Integer.parseInt(params[0])*1000;

                Thread.sleep(time);
                resp = "Slept for " + params[0] + " seconds";
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }






        @Override
        protected void onProgressUpdate(String... text) {
            FinalResult.setText(text[0]);

        }

        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation

            FinalResult.setText(result);
            progressDialog.dismiss();


        }



    }
}

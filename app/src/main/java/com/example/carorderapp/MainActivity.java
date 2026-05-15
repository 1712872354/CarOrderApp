package com.example.carorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView statusText;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        statusText = findViewById(R.id.statusText);
        resultText = findViewById(R.id.resultText);

        new FetchOrderTask().execute();
    }

    private class FetchOrderTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            statusText.setText("正在发起请求...");
            resultText.setText("");
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://api.m.jdmobile.com/mtop/car-order/order/detail");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("POST");
                connection.setRequestProperty("content-length", "52");
                connection.setRequestProperty("deviceappversion", "2.3.0");
                connection.setRequestProperty("content-type", "application/json; charset=utf-8");
                connection.setRequestProperty("x-user-agent", "channel/car platform/car.wxlite");
                connection.setRequestProperty("configselectorversion", "2");
                connection.setRequestProperty("duid", "27j2rbcggx42iuvso3chhpeo7q707");
                connection.setRequestProperty("charset", "utf-8");
                connection.setRequestProperty("referer", "https://servicewechat.com/wx183d85f5e5e273c6/156/page-frame.html");
                connection.setRequestProperty("user-agent", "Mozilla/5.0 (Linux; Android 16; 2509FPN0BC Build/BP2A.250605.031.A3; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/146.0.7680.177 Mobile Safari/537.36 XWEB/1460075 MMWEBSDK/20260202 MMWEBID/7945 MicroMessenger/8.0.71.3080(0x2800473D) WeChat/arm64 Weixin NetType/5G Language/zh_CN ABI/arm64 MiniProgramEnv/android");
                connection.setRequestProperty("accept-encoding", "gzip, deflate, br");

                String cookies = "serviceTokenCar=+j+WuMaS2xQl5fdEmgwG2SJ3WAf0nxmXkJZg5SRYYr8wBqHPf25NZkapYNQvYtM5FS9g4hr6mo20FAK6m74IB5s9x7zTikkCdMBe5IxXUdiM688/q9H9X+o2MIrwanAE; " +
                        "cUserIdCar=yUM9qydwo9_eLEQbGnUSZihVbiA; " +
                        "serviceToken=+j+WuMaS2xQl5fdEmgwG2SJ3WAf0nxmXkJZg5SRYYr8wBqHPf25NZkapYNQvYtM5FS9g4hr6mo20FAK6m74IB5s9x7zTikkCdMBe5IxXUdiM688/q9H9X+o2MIrwanAE; " +
                        "cUserId=yUM9qydwo9_eLEQbGnUSZihVbiA; " +
                        "userId=2205299461; " +
                        "xmuuid=XMGUEST-96efb473-7201-42a0-b306-6598d22298dc-1776226972136";
                connection.setRequestProperty("cookie", cookies);

                connection.setDoOutput(true);
                String jsonInputString = "[{\"orderId\":\"5265773198862625\",\"userId\":2205299461}]";

                try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                    wr.writeBytes(jsonInputString);
                    wr.flush();
                }

                int responseCode = connection.getResponseCode();

                BufferedReader in;
                if (responseCode >= 200 && responseCode < 400) {
                    in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                } else {
                    in = new BufferedReader(new InputStreamReader(connection.getErrorStream(), "UTF-8"));
                }

                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return "HTTP状态码: " + responseCode + "\n\n响应数据:\n" + response.toString();

            } catch (Exception e) {
                return "请求失败: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressBar.setVisibility(View.GONE);
            statusText.setText("请求完成");
            resultText.setText(result);
        }
    }
}
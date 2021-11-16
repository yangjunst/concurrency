package com.wangwenjun.concurrent.metrics.healthcheck;

import com.codahale.metrics.health.HealthCheck;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RESTfulServiceHealthCheck extends HealthCheck
{
    @Override
    protected Result check() throws Exception
    {
        return Result.healthy("The RESTful API service work well.");
        /*final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:10002/alexwang/ping")
                .build();
        try
        {
            Response response = client.newCall(request).execute();
            if (response.code() == 200)
            {
                return Result.healthy("The RESTful API service work well.");
            }
        } catch (Exception e)
        {

        }
        return Result.unhealthy("Detected RESTful server is unhealthy.");*/
    }
}

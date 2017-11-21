package com.example.im.retro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static OkHttpClient defaultHttpClient = new OkHttpClient();
    private static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        OkHttpClient defaultHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public okhttp3.Response intercept(Chain chain) throws IOException {
                                Request request = chain.request().newBuilder()
                                        .addHeader("authorization", "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoicnV0dXJhaiB0ZXN0MiIsImVtYWlsIjoiZGV2ZWxvcGVyQG1vb3ZsZWUuY29tIiwicGhvbmUiOiI5NjMyMDY1NjQ5IiwicGFzc3dvcmQiOiJ0ZXN0IiwidHlwZSI6ImN1c3RvbSIsInN1YnNjcmliZWRUbyI6W10sInJvbGUiOiJ1c2VyIiwiaXNfb3RwX3ZlcmlmaWVkIjp0cnVlLCJyYXRpbmciOjEsImNyZWF0ZWRBdCI6IjIwMTYtMTEtMjlUMDY6MTg6MTIuNTE2WiIsInVwZGF0ZWRBdCI6IjIwMTctMDgtMDJUMjE6MTI6MTYuOTUxWiIsIm90cCI6ODIzNTYsInJpZGVfY291bnQiOjE4MiwicmVmZXJyYWxfY29kZSI6IlJFRjk2MzIwNjU2NDkiLCJyZWZlcnJhbF9ib251cyI6MCwicmVmZXJyZWRfYnkiOm51bGwsImltYWdlX3VybCI6Imh0dHBzOi8vdHctdXNlci1pbWFnZXMuczMuYW1hem9uYXdzLmNvbS82ZWU4NWJlMC0wMGE0LTQyNDMtOGMwMS0xMzYzMGMyOTE1ZmQuanBnIiwicG9saWN5X2FjY2VwdGVkIjp0cnVlLCJpc19wYXl0bV9saW5rZWQiOnRydWUsInBheXRtIjp7InRva2VuIjoiSTR0THBIR3NRMVBjNXV5b0ZDVVFGZlI3dm9TaDA0RjdxNUFTTkdlVVRYdjJZSDBxV2YxRFZWcDFocVFJRmYyWiIsInBob25lIjoiODA4Nzg0NDM2NiIsImV4cGlyZXMiOiIxNTAyNDM4MzE1OTM4In0sImlkIjoiNTgzZDFkYTRmYWEwODhlZjFjYTdlM2JiIiwiaWF0IjoxNTAxNzU4OTU2LCJleHAiOjE1MzMyOTQ5NTZ9.i_D48-t_sOUBCoNNVXVrvgmQjasxA_kpF_P9dZIhQ74").build();
                                return chain.proceed(request);
                            }
                        }).build();
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(ApiInterface.BASE_URL)
                .client(defaultHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiInterface apiService =
                retrofit.create(ApiInterface.class);

        Call<List<RideHistory>> call = apiService.getDetail();
        call.enqueue(new Callback<List<RideHistory>>() {
            @Override
            public void onResponse(Call<List<RideHistory>>call, Response<List<RideHistory>> response) {
                List<RideHistory> rideHistory = response.body();
//                Log.d(TAG, "Number of movies received: " + movies.size());
                //setting adapter to recyclerview
               MyAdapter adapter = new MyAdapter(MainActivity.this, rideHistory);
                recyclerView.setAdapter(adapter);
                for(RideHistory m: rideHistory)
                {

                    Log.d("Booking_id : ",m.getBooking_id());
                    Log.d("Image : ",m.getImage());
                }
                Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<RideHistory>>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

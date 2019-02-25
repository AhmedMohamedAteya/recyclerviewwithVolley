package com.example.uranusw.calculatorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<Model> arrayList;
    MyAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    RequestQueue mRequestQueue;

    String url = "http://5c6d96fad51de300146f5d93.mockapi.io/api/v1/contacts?";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);
        arrayList = new ArrayList<>();
        adapter = new MyAdapter(getApplicationContext(), arrayList);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(rv.getContext(), linearLayoutManager.getOrientation());

        rv.setHasFixedSize(true);
        rv.setLayoutManager(linearLayoutManager);
        rv.addItemDecoration(dividerItemDecoration);
        mRequestQueue = Volley.newRequestQueue(this);
       Parsing();
    }

    public void Parsing()
    {
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++)
                {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        Model model = new Model();
                        String id = object.getString("id");
                        model.setId(id);
                        model.setName(object.getString("name"));
                        model.setNum(object.getString("phoneNumber"));
                        arrayList.add(model);

                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }

                adapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });
        RequestQueue queue = Volley.newRequestQueue(this);
        request.setShouldCache(false);
        queue.add(request);
    }

}

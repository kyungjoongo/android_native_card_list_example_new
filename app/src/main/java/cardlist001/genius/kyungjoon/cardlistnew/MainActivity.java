package cardlist001.genius.kyungjoon.cardlistnew;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    ListView listview;

    //커스텀 아답타 입니다sdlkfsdlkflsdkflksldkf
    ListViewAdapter adapter = new ListViewAdapter(MainActivity.this);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getHttpResponseDataAndsetListItems();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Toast.makeText(getApplicationContext(), "home.", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_dashboard:

                    Toast.makeText(getApplicationContext(), "dash.", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_notifications:
                    Toast.makeText(getApplicationContext(), "noti.", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };


    public void getHttpResponseDataAndsetListItems() {


        final List<HashMap> contactList2 = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        //title, image,url
        client.get("http://35.194.150.240:8080/receipe/receipeListToJson", params, new TextHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String jsonString) {


                        Gson gson = new Gson();
                        Type type = new TypeToken<List<HashMap>>() {
                        }.getType();

                        //jsonString to arrayList make
                        List<HashMap> contactList = gson.fromJson(jsonString, type);

                        try {
                            addArrayListToAdaptor(contactList);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                    }
                }
        );


    }

    public void addArrayListToAdaptor(List<HashMap> responseArrList) throws Exception {


        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);


        for (HashMap contactOne : responseArrList) {

            Log.d("sdlkflskdf-->", (String) contactOne.get("title"));

            String imgUrl = "http://35.194.150.240:8080/receipeImage/" + contactOne.get("image");


            adapter.addItem(imgUrl, (String) contactOne.get("url"), (String) contactOne.get("title"));
        }



    }

}

package alfaisal.firebase.abubaker_200453;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity3 extends AppCompatActivity {

    String x;
    String weatherWebserviceURL;
    TextView temperature, humidity;
    Button tempBttn;
    EditText cityTemp;
    ImageView w_icon;
    String t_icon = "notav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        temperature = (TextView) findViewById(R.id.temp_textview);
        humidity = (TextView) findViewById(R.id.humid_textview);
        cityTemp = (EditText) findViewById(R.id.cityTemp_edittxt);
        w_icon = (ImageView) findViewById(R.id.w_icon);
        tempBttn = (Button) findViewById(R.id.tempBttn);

        tempBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x = cityTemp.getText().toString();
                weatherWebserviceURL = "https://api.openweathermap.org/data/2.5/weather?q="+x+"&appid=50ba39989d0554169754c92486a2e86f&units=metric";
                weather(weatherWebserviceURL);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("ticon",t_icon);
                editor.commit();

            }
        });
    }
    public void weather(String url){
        JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("Faisal","Response OK");
                Log.d("Faisal",response.toString());
                try {
                    JSONObject jsonMain = response.getJSONObject("main");
                    Double temp = jsonMain.getDouble("temp");
                    temperature.setText(String.valueOf(temp));
                    Log.d("Faisal-temp",String.valueOf(temp));
                    Double humid = jsonMain.getDouble("humidity");
                    Log.d("Faisal-humidity", String.valueOf(humid));
                    humidity.setText(String.valueOf(humid));

                    JSONArray jsonArray = response.getJSONArray("weather");
                    for(int i=0; i<jsonArray.length(); i++){
                        Log.d("Faisal-array",jsonArray.getString(i));
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String weather = jsonObject.getString("main");
                        Log.d("Faisal-weather",weather);

                        if(weather.equals("Clear")){
                            w_icon.setImageResource(R.drawable.sunny);
                            t_icon = "sunny";
                        }
                        else if(weather.equals("Clouds")){
                            w_icon.setImageResource(R.drawable.cloudy);
                            t_icon = "cloudy";
                        }
                        else if(weather.equals("Rain")){
                            w_icon.setImageResource(R.drawable.rainy);
                            t_icon = "rainy";
                        }
                        else{
                            w_icon.setImageResource(R.drawable.notav);
                            t_icon = "notav";
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Faisal","Response ERROR");
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObj);
    }
}
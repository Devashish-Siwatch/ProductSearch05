package com.example.productsearch05;



import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    EditText editText;
    TextView textView;
    Button button;
    String imgSrc;
    String text="mouse";
    String str;
    Element line;
    // int buttonclick;
    //  String  url = "https://firebase.google.com/";
    String url;
    private class AT extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            //url = "https://firebase.google.com/";
           //  url = "https://www.amazon.in/s?k=laptop";
            String x=text;
            url = "https://www.amazon.in/s?k="+x;
            try {
                Document document = Jsoup.connect(url).get();

                Element img=document.select("img").get(8);
                Elements words=document.select("span[class=\"a-size-medium a-color-base a-text-normal\"]").eq(0);
                // String words = document.body().text();
               str=words.text();

                // Element words = document.select("span").get(500);
                //  System.out.println(words.toString());
                // line = document.select("div#test").get(4);
                // System.out.println(line.toString());
         /*         Elements img = document.select("img");
                    for (Element image : img) {
                    String imageUrl = image.attr("data-original");
                    System.out.println(imageUrl);
                }*/

                imgSrc = img.absUrl("src");

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Catch");
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            System.out.println("poste");
            textView.setText(str);

            Glide.with(MainActivity.this)
                    .load(imgSrc)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .fitCenter()
                    .into(imageView);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        imageView=findViewById(R.id.image);
        editText=findViewById(R.id.input);
        textView=findViewById(R.id.output);
        button= findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                System.out.println(v);
                //     buttonclick++;
                // AT.cancel(true);
                text=editText.getText().toString();
                System.out.println(imgSrc);



                new AT().execute();



            }
        });
        new AT().execute();
    }
}

package com.articlegure.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.articlegure.R;
import com.articlegure.Helper.SQLiteHandler;

public class SplashScreen extends AppCompatActivity {
    private SQLiteHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        db = new SQLiteHandler(this);
        db.deleteUsers();

            db.addArticle("1","http://articlegure.com/images/the%20unsung%20prince.jpeg","The world is surrounded by different people with different perception","0");
            db.addArticle("2","http://articlegure.com/images/articlegure%20presents%20(1).png","The world is surrounded by different people with different perception ","0");
            db.addArticle("3","http://articlegure.com/images/way%20of%20life.jpg","As I walk through the valley of the shadow of death I wear my crown of thorns and pull the knife out my chest I keep searching for something Now I'm stuck with this,\n" +
                    "that will never change always a part of me until the very last day","0");
            db.addArticle("4","http://articlegure.com/images/rishika.jpg","Transgender people are no strangers to society: the concept of not feeling comfortable in one's birth-assigned","0");
            db.addArticle("5","http://articlegure.com/images/people%20with%20love.jpg","The sun rose up slowly turning the clouds golden brown. His light filled life into the dark and cold night world. The morning grass received his warm greetings with pleasure.","0");
            db.addArticle("6","http://articlegure.com/images/open%20your%20eyes.jpeg","It’s a hot summer afternoon. People moving hither and thither. Vendors disturbing my peace of mind. Children crying.","0");
            db.addArticle("7","http://articlegure.com/images/never%20stop.jpeg","From the time he was born, he never stopped. In his infancy, he never ","0");
            db.addArticle("8","http://articlegure.com/images/Copy%20of%20first%20encounter.jpg","Manifesting fantasy is a scary thing! He commented","0");
            db.addArticle("9","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoRpbpFGzOlYv6Kyn90VzTXkevwMu64R5t_Rq0a-SKh53H8F7g","Once upon a time lived a boy named Zihan, an ordinary one. ","0");


            Thread thread = new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        Intent main_intent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(main_intent);
                        finish();
                    }
                }
            };
        thread.start();
    }
}

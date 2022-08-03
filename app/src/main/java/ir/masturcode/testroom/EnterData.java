package ir.masturcode.testroom;

import androidx.appcompat.app.AppCompatActivity;
import ir.masturcode.testroom.DatabaseRoom.MainAdapter;
import ir.masturcode.testroom.DatabaseRoom.MainData;
import ir.masturcode.testroom.DatabaseRoom.RoomDB;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;
import java.util.List;

public class EnterData extends AppCompatActivity {
    EditText editText, editTextUrl;
    Button btnAdd, showPhoto, goToList;
    ImageView viewPhoto;
    List<MainData> dataList = new ArrayList<>();
    RoomDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_data);

        editText = findViewById(R.id.edt_txt);
        editTextUrl = findViewById(R.id.edit_txt_photo1);
        btnAdd = findViewById(R.id.record_photo1);
        showPhoto = findViewById(R.id.show_photo1);
        viewPhoto = findViewById(R.id.view_photo1);
        goToList = findViewById(R.id.go_to_mainactivity);


        database = RoomDB.getInstance(this);
        dataList = database.mainDao().getAll();

        //Add name and image to database
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sText = editText.getText().toString().trim();
                String urlText = editTextUrl.getText().toString().trim();
                if (!sText.equals("") & !urlText.equals("")) {
                    MainData data = new MainData();
                    data.setText(sText);
                    data.setImage(urlText);
                    database.mainDao().insert(data);
                    editText.setText("");
                    editTextUrl.setText("");
                    GlideApp.with(EnterData.this)
                            .load(R.mipmap.ic_launcher_round)
                            .into(viewPhoto);
                    dataList.clear();
                    dataList.addAll(database.mainDao().getAll());
                }
            }
        });

        //Photo preview
        showPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlText = editTextUrl.getText().toString().trim();
                GlideApp
                        .with(getApplicationContext())
                        .load(urlText)
                        .skipMemoryCache(true)
                        .error(R.mipmap.ic_launcher_round)
                        .thumbnail(0.2f)
                        .transition(DrawableTransitionOptions.withCrossFade(800))
                        .into(viewPhoto);
            }
        });

        //Show list
        goToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EnterData.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
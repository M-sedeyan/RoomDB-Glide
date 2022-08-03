package ir.masturcode.testroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ir.masturcode.testroom.DatabaseRoom.MainAdapter;
import ir.masturcode.testroom.DatabaseRoom.MainData;
import ir.masturcode.testroom.DatabaseRoom.RoomDB;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
Button btnDelete;
    List<MainData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDelete = findViewById(R.id.delet_all);
        recyclerView = findViewById(R.id.recycler_view);
//Initialize database
        database = RoomDB.getInstance(this);
        dataList = database.mainDao().getAll();

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //Initialize adapter
        mainAdapter = new MainAdapter(dataList, MainActivity.this);
        //Set adapter
        recyclerView.setAdapter(mainAdapter);

        //Delete all query
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.mainDao().reset(dataList);
                dataList.clear();
                dataList.addAll(database.mainDao().getAll());
                mainAdapter.notifyDataSetChanged();
            }
        });
    }
}
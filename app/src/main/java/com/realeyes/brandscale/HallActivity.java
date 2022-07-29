package com.realeyes.brandscale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HallActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adaptor;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Brandinfo> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall);

        recyclerView = findViewById(R.id.Brandlist); //activity_hall과 연결
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();
        database = FirebaseDatabase.getInstance(); //Firebase 데이터베이스 연동
        databaseReference = database.getReference("BRAND");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear(); //기존 배열이 존재하지 않게 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { //반복문으로 Data List를 추출
                    Brandinfo brandinfo = snapshot.getValue(Brandinfo.class); //만들어둔 Brand객체에 데이터 담음
                    arrayList.add(brandinfo);
                }
                adaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //DB가져오던 중 에러발생 시
                Log.e("HallActivity", String.valueOf(error.toException()));
            }
        });

        adaptor = new BrandlistAdaptor(arrayList, this);
        recyclerView.setAdapter(adaptor); //recyclerView에 adaptor 연결



    }
}
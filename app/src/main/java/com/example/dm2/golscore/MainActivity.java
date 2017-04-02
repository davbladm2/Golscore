package com.example.dm2.golscore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.dm2.golscore.Holder.ClubHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference dbPrediccion;
    private ChildEventListener eventListener;
    private TextView id, nombre;
    private RecyclerView listaRV;
    private ArrayList<Club> datos;
    private FirebaseRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbPrediccion = FirebaseDatabase.getInstance().getReference().child("Club");
        RecyclerView recycler = (RecyclerView) findViewById(R.id.listaRV);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new FirebaseRecyclerAdapter<Club, ClubHolder>(Club.class, R.layout.club_list_item, ClubHolder.class, dbPrediccion) {
                    @Override
                    public void populateViewHolder(ClubHolder predViewHolder, Club club, int position) {
                        Log.e("Club",""+club.getId());
                        predViewHolder.setId(""+club.getId());
                        predViewHolder.setNombre(club.getNombre());
                    }
                };
        recycler.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }
}
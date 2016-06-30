package com.example.wind.minstory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.wind.minstory.adapter.GridviewGameAdapter;
import com.example.wind.minstory.content.GameModel;
import com.example.wind.minstory.content.GameModelData;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    private ImageView back;
    private GridView gridView;
    private List<GameModel> gameModels;
    private GameModelData gameModelData;
    private GridviewGameAdapter gridviewGameAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        back =  (ImageView)findViewById(R.id.back);
        gridView = (GridView)findViewById(R.id.gridview_game);
        gameModelData = new GameModelData();
        gameModels = new ArrayList<>();
        for (int i = 0; i <gameModelData.getTitle().length;i++){
            GameModel mainContent = new GameModel();
            mainContent.setTitle(gameModelData.getTitle()[i]);
            mainContent.setImg(gameModelData.getImg()[i]);
            mainContent.setConntect(gameModelData.getConntent()[i]);
            mainContent.setOuther(gameModelData.getOuther()[i]);
            mainContent.setVersion(gameModelData.getTime()[i]);
            gameModels.add(mainContent);
        }

        gridviewGameAdapter = new GridviewGameAdapter(GameActivity.this,gameModels,R.layout.item_game);
        gridView.setAdapter(gridviewGameAdapter);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}

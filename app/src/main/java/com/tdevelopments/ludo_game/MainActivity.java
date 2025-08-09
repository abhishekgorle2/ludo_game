package com.tdevelopments.ludo_game;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onPlayClick(View v) {
        // Show player selection dialog when Play button is clicked
        showPlayerSelectionDialog();
    }

    private void showPlayerSelectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Number of Players");

        String[] options = {"2 Players", "3 Players", "4 Players"};

        builder.setItems(options, (dialog, which) -> {
            int playerCount = which + 2;

            Intent intent = new Intent(MainActivity.this, LudoActivity.class);
            intent.putExtra("PLAYER_COUNT", playerCount);
            startActivity(intent);
        });

        builder.setCancelable(true);
        builder.show();
    }
}



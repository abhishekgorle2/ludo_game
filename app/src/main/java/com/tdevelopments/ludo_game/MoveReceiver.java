package com.tdevelopments.ludo_game;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class MoveReceiver {
    private static final String TAG = "MoveReceiver";
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final String serverIp;
    private final MoveListener listener;
    private Timer timer;

    public MoveReceiver(String serverIp, MoveListener listener) {
        this.serverIp = serverIp;
        this.listener = listener;
    }

    public void start() {
        timer = new Timer();
        timer.schedule(new PollTask(), 0, 2000); // Every 2 seconds
    }

    public void stop() {
        if (timer != null) timer.cancel();
    }

    private class PollTask extends TimerTask {
        @Override
        public void run() {
            Log.d(TAG, "🔄 Polling server...");

            try {
                URL url = new URL("http://" + serverIp + ":5050/api/move");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                int responseCode = conn.getResponseCode();
                Log.d(TAG, "🌐 Response Code: " + responseCode);

                if (responseCode == 200) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = reader.readLine();
                    Log.d(TAG, "📥 Response Body: " + line);
                    reader.close();

                    if (line != null && !line.equalsIgnoreCase("none") && line.contains(":")) {
                        String[] parts = line.split(":");
                        if (parts.length == 2) {
                            String color = parts[0].trim();
                            int number = Integer.parseInt(parts[1].trim());

                            Log.d(TAG, "📬 Valid move received: " + color + " = " + number);

                            if (listener != null) {
                                mainHandler.post(() -> listener.onMoveReceived(color, number));
                            } else {
                                Log.w(TAG, "⚠️ Listener is null! Cannot deliver move.");
                            }
                        } else {
                            Log.w(TAG, "❌ Invalid move format received");
                        }
                    } else {
                        Log.d(TAG, "⏳ No new move (response was 'none' or empty)");
                    }
                }
                conn.disconnect();
            } catch (Exception e) {
                Log.e(TAG, "❌ Error polling move: " + e.getMessage(), e);
            }
        }
    }
}

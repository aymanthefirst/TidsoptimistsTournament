package com.example.lenovo.tidsoptimiststournament;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.example.lenovo.tidsoptimiststournament.R.id.addMorePlayersButton;
import static com.example.lenovo.tidsoptimiststournament.R.id.backButton1;
import static com.example.lenovo.tidsoptimiststournament.R.id.nextRoundButton;




public class MainActivity extends AppCompatActivity{

// Hcode

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);


    }
    List<String> strings = new ArrayList<>();
    List<TextView> textViews = new ArrayList<>();
    List<TextView> textBoxes = new ArrayList<TextView>();
    public static List<Player> players = new ArrayList<>();
    Map<Player, Player> playerVsPlayer = new HashMap<Player, Player>();
    List<List<String>> finalResult = new ArrayList<>();
    public static MainActivity main = new MainActivity();
    int round = 0;
    int morePlayers = 0;
    TextView textBox1;
    TextView textBox2;
    TextView textBox3;
    TextView textBox4;
    TextView textBox5;
    TextView textBox6;
    Context context = this;

    public List<List<String>> getFinalResult() {
        return finalResult;
    }

    public List<String> getStrings() {
        return strings;
    }


    public void DoneButtonClick(View v) {
        getTextboxes();
        getPlayersFromTextView();
        setContentView(R.layout.roundone);
        getTextViews();
        newRound();
        createTextViewProgrammatically();
        round++;
    }

    public void addMorePlayersClick(View v) {
        setContentView(R.layout.layout);
        for (TextView each : textBoxes) {
            each.setText("");
        }
    }

    public void createTextViewProgrammatically() {
        setContentView(R.layout.roundone);
        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.relativeLayout1);
        int num = 200;
        int i = 1;
        textViews.clear();
        RelativeLayout.LayoutParams params;
        for (String each : strings) {
            TextView textView = new TextView(context);
            textView.setText(each);
            textViews.add(textView);
            params = new RelativeLayout.LayoutParams(RelativeLayout.
                    LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            params.setMargins(310, num, 200, 200);
            mainLayout.addView(textView, params);
            num = num + 300;
            i++;
        }
        Button addMorePlayers = (Button) findViewById(addMorePlayersButton);
        params = new RelativeLayout.LayoutParams(RelativeLayout.
                LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(310, num, 200, 200);
        addMorePlayers.setLayoutParams(params);
    }

    public void morePlayersButtonClick(View v) {
        Button b = (Button) findViewById(backButton1);
        b.setVisibility(View.VISIBLE);
        getPlayersFromTextView();
        setTextsForMorePlayers();
    }

    public void setTextsForMorePlayers() {
        int aa = players.size() + 1;
        for (TextView each : textBoxes) {
            String player = "player " + aa;
            each.setText(player);
            aa++;
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void backButtonClick(View v) {
        round = round - 1;
        if (round == 0) {
            finalResult.clear();
            strings.clear();
            players.clear();
            setContentView(R.layout.layout);
            Button b = (Button) findViewById(backButton1);
            b.setVisibility(View.INVISIBLE);
        }
        if (round > 0) {
            //display the new list
            finalResult.remove(finalResult.size() - 1);
            strings = getFinalResult().get(round-1);
            createTextViewProgrammatically();
        }

    }

    public void buttonOnClickNextRound(View v) {
        newRound();
        createTextViewProgrammatically();
        round++;
        if (round == players.size() - 1) {
            Button nextButton = (Button) findViewById(nextRoundButton);
            nextButton.setText("Go again");
        }
        if (round == players.size()){
            round = 0;
        }
    }

    public void getTextboxes() {
        textBox1 = (TextView) findViewById(R.id.player1);
        textBox2 = (TextView) findViewById(R.id.player2);
        textBox3 = (TextView) findViewById(R.id.player3);
        textBox4 = (TextView) findViewById(R.id.player4);
        textBox5 = (TextView) findViewById(R.id.player5);
        textBox6 = (TextView) findViewById(R.id.player6);

        textBoxes.add(textBox1);
        textBoxes.add(textBox2);
        textBoxes.add(textBox3);
        textBoxes.add(textBox4);
        textBoxes.add(textBox5);
        textBoxes.add(textBox6);
    }

    public void getPlayersFromTextView() {

        String f = textBox1.getText().toString();
        String g = textBox2.getText().toString();
        String h = textBox3.getText().toString();
        String i = textBox4.getText().toString();
        String j = textBox5.getText().toString();
        String k = textBox6.getText().toString();

        if (!f.equals("")) {
            players.add(new Player(f));
        }
        if (!g.equals("")) {
            players.add(new Player(g));
        }
        if (!h.equals("")) {
            players.add(new Player(h));
        }
        if (!i.equals("")) {
            players.add(new Player(i));
        }
        if (!j.equals("")) {
            players.add(new Player(j));
        }
        if (!k.equals("")) {
            players.add(new Player(k));
        }
    }

    public void getTextViews() {
        TextView text1 = (TextView) findViewById(R.id.textView1);
        TextView text2 = (TextView) findViewById(R.id.textView2);
        TextView text3 = (TextView) findViewById(R.id.textView3);
        TextView text4 = (TextView) findViewById(R.id.textView4);
        TextView text5 = (TextView) findViewById(R.id.textView5);
        TextView text6 = (TextView) findViewById(R.id.textView6);

        textViews.add(text1);
        textViews.add(text2);
        textViews.add(text3);
        textViews.add(text4);
        textViews.add(text5);
        textViews.add(text6);

    }




    public void newRound() {
        strings.clear();
        playerVsPlayer.clear();
        LinkedList<Player> linked = new LinkedList();

        if (getPlayers().size() % 2 != 0) {
            linked.add(new Player("bye")); // If odd number of teams add a dummy
        }
        linked.addAll(players);
        Player firstPlayer = linked.get(0);
        linked.remove(firstPlayer);
        Collections.rotate(linked, round);//1: 645 2:564 3:456
        linked.add(0, firstPlayer);
        List<Player> players1 = linked.subList(0, linked.size() / 2);
        List<Player> players2 = linked.subList(linked.size() / 2, linked.size());
        Collections.reverse(players2);
        for (int i = 0; i < players1.size(); i++) {
            playerVsPlayer.put(players1.get(i), players2.get(i));
            players1.get(i).played(players2.get(i));
            if (players1.get(i).getName().contains("bye") || players2.get(i).getName().contains("bye")) {
                if (players1.get(i).getName().contains("bye")) {
                    strings.add(players2.get(i).getName() + " sits out");
                }
                if (players2.get(i).getName().contains("bye")) {
                    strings.add(players1.get(i).getName() + " sits out");
                }
            } else {
                strings.add(players1.get(i).getName() + " vs " + players2.get(i).getName());
            }
        }
        finalResult.add(new ArrayList<>(getStrings()));
    }


}
















package com.example.lenovo.tidsoptimiststournament;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Admin on 23.03.2015.
 */
public class Player {


    private String mName;
    public List<Player> beenWith = new ArrayList<>();


    public Player(String name) //H:this was changed//
    {
        mName = name;
    }

    public String getName() {
        return mName;
    }



    @Override
    public String toString() {
        return new StringBuilder()
                .append(mName)
                .toString();
    }

    public void played(Player p) {
        beenWith.add(p);
        p.beenWith.add(this);
    }

}

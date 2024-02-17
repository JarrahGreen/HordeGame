package org.example.entity;

import java.util.ArrayList;
import java.util.Collections;

public record PlayerController(
        int up,
        int right,
        int down,
        int left,
        int shoot
) {
    public PlayerController randomise() {
        ArrayList<Integer> keys = new ArrayList<>(4);
        keys.add(up);
        keys.add(right);
        keys.add(down);
        keys.add(left);
        Collections.shuffle(keys);
        return new PlayerController(keys.get(0), keys.get(1), keys.get(2), keys.get(3), shoot);
    }
}

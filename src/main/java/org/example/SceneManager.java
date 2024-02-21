package org.example;

import org.example.scene.Scene;
import org.example.scene.StartScene;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class SceneManager implements KeyListener {
    private static SceneManager _instance;

    private Scene activeScene;
    private SceneManager(){
        activeScene = new StartScene();
    }
    public static SceneManager getSceneManager(){
        if (_instance == null) {
            _instance = new SceneManager();
        }
        return _instance;
    }

    public void setActiveScene(Scene activeScene) {
        this.activeScene = activeScene;
    }

    public void update(double dt) {
        activeScene.update(dt);
    }

    public void draw(Graphics g) {
        activeScene.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        activeScene.keyPressed(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        activeScene.keyTyped(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        activeScene.keyReleased(e);
    }
}

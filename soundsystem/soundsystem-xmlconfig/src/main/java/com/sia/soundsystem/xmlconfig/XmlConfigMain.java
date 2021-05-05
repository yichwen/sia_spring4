package com.sia.soundsystem.xmlconfig;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlConfigMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("cdplayer.xml");
        MediaPlayer player = context.getBean(MediaPlayer.class);
        player.play();
    }
}

/**
 * This program is a demonstration of the Observer design pattern.
 * It notifies registered NewsChannels when a NewsAgency updates its news.
 */

package ioprogramming.designpatterns;

import java.util.*;

interface Observer { void update(String news); }

interface Subject {
    void register(Observer o);
    void notifyObservers();
}

class NewsAgency implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String news;

    public void setNews(String news) {
        this.news = news;
        notifyObservers();
    }
    public void register(Observer o) { observers.add(o); }
    public void notifyObservers() {
        for (Observer o : observers) o.update(news);
    }
}

class NewsChannel implements Observer {
    private String name;
    public NewsChannel(String name) { this.name = name; }
    public void update(String news) {
        System.out.println(name + " received news: " + news);
    }
}

public class ObserverDemonstration {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();
        agency.register(new NewsChannel("BBC"));
        agency.register(new NewsChannel("CNN"));

        agency.setNews("Design Patterns are awesome!");
    }
}

package com.example.todo;

public class Todo {
    String titletodo,desctodo,datetodo,keys;
    public Todo() {
    }
    public Todo(String titletodo, String desctodo, String datetodo, String keys) {
        this.titletodo = titletodo;
        this.desctodo = desctodo;
        this.datetodo = datetodo;
        this.keys = keys;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public String getTitletodo() {
        return titletodo;
    }


    public void setTitletodo(String titletodo) {
        this.titletodo = titletodo;
    }

    public String getDesctodo() {
        return desctodo;
    }

    public void setDesctodo(String desctodo) {
        this.desctodo = desctodo;
    }

    public String getDatetodo() {
        return datetodo;
    }

    public void setDatetodo(String datetodo) {
        this.datetodo = datetodo;
    }
}

package ru.mezenova.MySecondTestAppSpringBoot.model;

public enum Systems {

    ERP("Enterprise Resource Planning"),   // Первый элемент с именем ERP и описанием "Enterprise Resource Planning"
    CRM("Customer Relationship Management"), // Второй элемент с именем CRM и описанием "Customer Relationship Management"
    WMS("Warehouse Management System"); // Третий элемент с именем WMS и описанием "Warehouse Management System"

    private final String description;

    Systems(String description){  // Конструктор, который принимает описание
        this.description=description; // Присваиваем описание
    }

    @Override
    public String toString(){
        return description; // Переопределенный метод toString() возвращает описание элемента
    }
}
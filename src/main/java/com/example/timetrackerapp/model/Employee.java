package com.example.timetrackerapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String surname;

    private String patronymic;

    private String stuffId; // переписал int на String
    // чтобы в поле добавления сотрудников не устанавливался 0 по умолчанию
    // аналогично исправил ниже в гетерах и сетерах и в конструкторе

    private String employeePost;

    private String role;

    private String login;

    private String password;

    public Employee() {} // пустой констурктор, обязателен при создании таблицы

    public Employee(int id, String name, String surname, String patronymic, String stuffId, String employeePost, String role, String login, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.stuffId = stuffId;
        this.employeePost = employeePost;
        this.role = role;
        this.login = login;
        this.password = password;
    } // полный конструктор, тоже сделал, т.к. так надо

    // далее идет создание гетеров и сетеров

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getStuffId() {
        return stuffId;
    }

    public void setStuffId(String stuffId) {
        this.stuffId = stuffId;
    }

    public String getEmployeePost() {
        return employeePost;
    }

    public void setEmployeePost(String employeePost) {
        this.employeePost = employeePost;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

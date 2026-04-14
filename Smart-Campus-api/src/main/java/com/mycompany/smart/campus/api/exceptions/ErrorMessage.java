/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smart.campus.api.exceptions;

/**
 *
 * @author rsoha
 */
public class ErrorMessage {

    private String errorMessage;
    private int errorCode;
    private String details;

    // Constructors
    public ErrorMessage() {
    }

    public ErrorMessage(String errorMessage, int errorCode, String details) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.details = details;
    }

    // Getters
    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getDetails() {
        return details;
    }

    // Setters
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

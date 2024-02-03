package com.kaio.gerenciamento.util;

import java.io.Serializable;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

public class FacesMessages implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private void add(String msg, FacesMessage.Severity severity) {
        FacesMessage facesMessage = new FacesMessage(msg);
        facesMessage.setSeverity(severity);
        
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
    
    public void info(String msg) {
        add(msg, FacesMessage.SEVERITY_INFO);
    }

}
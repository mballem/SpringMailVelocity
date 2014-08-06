package com.mballem.tutorial.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcio Ballem on 05/08/2014.
 */
public class MailMessage {

    private String from;
    private String personal;
    private String text;
    private String subject;
    private List<String> attachments = new ArrayList<String>();
    private String to;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    //adicionar os anexos da mensagem
    public void addAttachment(String... files) {
        for (String file : files) {
            this.attachments.add(file);
        }
    }
}

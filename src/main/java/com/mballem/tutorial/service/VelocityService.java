package com.mballem.tutorial.service;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Marcio Ballem on 05/08/2014.
 */
@Service
public class VelocityService {

    private static final String ENCODING = "UTF-8";
    private static final String TEMPLATE_URL = "velocity/mail.vm";

    private VelocityEngine velocityEngine;

    @Autowired
    public VelocityService(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    private Map<String, Object> model = new HashMap<String, Object>();

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }

    public String getVelocityBody() {
        return VelocityEngineUtils.mergeTemplateIntoString(
                this.velocityEngine,
                TEMPLATE_URL,
                ENCODING,
                this.model
        );
    }
}

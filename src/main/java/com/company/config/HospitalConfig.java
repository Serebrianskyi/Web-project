package com.company.config;

import java.util.ResourceBundle;

public interface HospitalConfig {
        ResourceBundle config = ResourceBundle.getBundle("i18n_config");
        String MESSAGES = config.getString("msg.bundle");

}

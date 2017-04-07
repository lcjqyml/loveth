package com.milin.loveth.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by Milin on 2016/11/10.
 */
public abstract class BaseController {

    @Value("${page.import.mode}")
    private String importMode;

    @ModelAttribute("importMode")
    public String getImportMode() {
        return importMode;
    }

}

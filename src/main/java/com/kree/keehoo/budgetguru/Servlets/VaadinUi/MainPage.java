package com.kree.keehoo.budgetguru.Servlets.VaadinUi;


import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;

import java.io.File;

@Theme("mytheme")
@CDIUI("")
public class MainPage extends UI {
    @Override
    protected void init(VaadinRequest request) {

        VerticalLayout layout = new VerticalLayout();


        // Find the application directory
        String basepath = VaadinService.getCurrent()
                .getBaseDirectory().getAbsolutePath();

// Image as a file resource
        FileResource resource = new FileResource(new File(basepath +
                "/WEB-INF/logo_half_size.png"));

// Show the image in the application
        Image image = new Image("", resource);



// Let the user view the file in browser or download it
        Link link = new Link("Link to the image file", resource);
        layout.addComponent(image);
        layout.setComponentAlignment(image, Alignment.MIDDLE_CENTER);
        setContent(layout);
    }
}

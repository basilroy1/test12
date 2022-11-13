package com.example.sampledeploy.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class MainApp extends AppLayout {
  public MainApp() {
    addDrawerContent();
  }
  private void addDrawerContent() {
    H1 appName = new H1("My App");
    appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
    Header header = new Header(appName);

    Scroller scroller = new Scroller(createNavigation());

    addToDrawer(header, scroller);
  }

  private AppNav createNavigation() {
    // AppNav is not yet an official component.
    // For documentation, visit https://github.com/vaadin/vcf-nav#readme
    AppNav nav = new AppNav();

    //nav.addItem(new AppNavItem("Hello World", HelloWorldView.class, "la la-globe"));
    //nav.addItem(new AppNavItem("About", AboutView.class, "la la-file"));
    nav.addItem(new AppNavItem("", MainView.class));

    return nav;
  }
}

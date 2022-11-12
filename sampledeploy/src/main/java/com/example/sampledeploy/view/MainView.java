package com.example.sampledeploy.view;

import com.example.sampledeploy.constants.BloodPressureConstants;
import com.example.sampledeploy.service.BusinessLogic;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@PageTitle("BloodPressureCalculator")
@Route("")
//@Service
public class MainView extends VerticalLayout {

  public MainView() {

    H1 header = new H1("Blood Pressure Calculator");
    header.getStyle().set("margin","auto");
    header.getStyle().set("color","orange");
    add(header);

    IntegerField systolicValue = new IntegerField("Systolic Value (mmHG)");
    IntegerField diastolicValue = new IntegerField("Diastolic Value (mmHG)");


    systolicValue.setRequiredIndicatorVisible(true);
    systolicValue.setWidth(275,Unit.PIXELS);

    diastolicValue.setRequiredIndicatorVisible(true);
    diastolicValue.setWidth(275, Unit.PIXELS);

    systolicValue.setPlaceholder("Enter Systolic Value");
    diastolicValue.setPlaceholder("Enter Diastolic Value");

    HorizontalLayout horizontalLayout = new HorizontalLayout(systolicValue,diastolicValue);
    horizontalLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);
    horizontalLayout.getStyle().set("margin","auto");
    add(horizontalLayout);

    Button calculateBP = new Button("Calculate BP");
    calculateBP.addThemeVariants(ButtonVariant.LUMO_ICON);
    calculateBP.getStyle().set("margin","auto");
    calculateBP.setId("calculate-bp-btn");

    add(calculateBP);
    add(new Hr());
    calculateBPonClick(calculateBP,systolicValue,diastolicValue);

    calculateBP.addClickShortcut(Key.ENTER);
  }
  public void calculateBPonClick(Button calculateBP,IntegerField systolicValue,IntegerField diastolicValue){
    calculateBP.addClickListener(click -> {
      String res = BusinessLogic.checkBloodPressureStatus(
          (systolicValue.getValue()),diastolicValue.getValue());

      onlineGPAdvice(systolicValue,diastolicValue,res);
      notificationPopUp(systolicValue,diastolicValue,res);
    });
  }
  public void notificationPopUp(IntegerField systolicValue,IntegerField diastolicValue,String res){
    Notification notification =
        Notification.show(systolicValue.getValue()+ " / " +diastolicValue.getValue()+ " , "+res,6000, Notification.Position.MIDDLE);

    switch (res) {
      case BloodPressureConstants.LOW:
        notification.addThemeVariants(NotificationVariant.LUMO_CONTRAST);
        break;
      case BloodPressureConstants.IDEAL:
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        break;
      case BloodPressureConstants.PREHIGH:
        notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
        break;
      default:
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        break;
    }
    systolicValue.setValue(null);
    diastolicValue.setValue(null);
  }
  public void onlineGPAdvice(IntegerField systolicValue,IntegerField diastolicValue, String res){
    MessageList resultInfoDr = new MessageList();
    Instant today = LocalDateTime.now().toInstant(ZoneOffset.UTC);
    switch (res) {
      case BloodPressureConstants.LOW:
        MessageListItem messageLow = new MessageListItem(
            "Dear User, Your blood pressure reading is of " + systolicValue.getValue() + " / " + diastolicValue.getValue() + " is " + BloodPressureConstants.LOW + "." +
                "That's usually not a problem, as it's naturally low for some people. " +
                "But sometimes it can be caused by illness, a health condition, or some medicines.",
            today, BloodPressureConstants.ONLINE_GP);
        messageLow.setUserColorIndex(1);
        resultInfoDr.setItems(List.of(messageLow));
        add(resultInfoDr);
        add(new Hr());
        break;
      case BloodPressureConstants.IDEAL:
        MessageListItem messageIdeal = new MessageListItem(
            "Dear User, Your blood pressure reading of " + systolicValue.getValue() + " / " + diastolicValue.getValue() + " is " + BloodPressureConstants.IDEAL + "." +
                "A healthy lifestyle can help to keep your blood pressure this way – and lower your risk of having a heart attack or stroke. It can help with other health issues too." +
                "Pass the message on to friends and family – high blood pressure has no symptoms, so everyone should be tested regularly." +
                "Sticking to a healthy lifestyle can prevent high blood pressure.",
            today, BloodPressureConstants.ONLINE_GP);
        messageIdeal.setUserColorIndex(2);
        resultInfoDr.setItems(List.of(messageIdeal));
        add(resultInfoDr);
        add(new Hr());
        break;
      case BloodPressureConstants.PREHIGH:
        MessageListItem messagePreHigh = new MessageListItem(
            "Dear User, Your blood pressure reading is of " + systolicValue.getValue() + " / " + diastolicValue.getValue() + " is " + BloodPressureConstants.PREHIGH + ".Ideally, it should be below 120/80 mmHg." +
                "You should get your blood pressure checked at a GP practice or pharmacy within the next month." +
                "High blood pressure is sometimes called the \"silent killer\" because it rarely causes symptoms and can lead to a heart attack or stroke if it's not treated." +
                "The good news is you may be able to lower it through lifestyle changes.",
            today, BloodPressureConstants.ONLINE_GP);
        messagePreHigh.setUserColorIndex(3);
        resultInfoDr.setItems(List.of(messagePreHigh));
        add(resultInfoDr);
        add(new Hr());
        break;
      case BloodPressureConstants.HIGH:
        MessageListItem messageHigh = new MessageListItem(
            "Dear User, Your blood pressure reading of " + systolicValue.getValue() + " / " + diastolicValue.getValue() + " is " + BloodPressureConstants.HIGH + "." +
                "You should get your blood pressure checked at a GP practice or pharmacy within the next week." +
                "High blood pressure is sometimes called the \"silent killer\" because it rarely causes symptoms and can cause a heart attack or stroke if not treated." +
                "You can still think about making healthy changes to your diet and lifestyle but talk to a GP about this first." +
                "Some people can lower their blood pressure through lifestyle changes.",
            today, BloodPressureConstants.ONLINE_GP);
        messageHigh.setUserColorIndex(4);
        resultInfoDr.setItems(List.of(messageHigh));
        add(resultInfoDr);
        add(new Hr());
        break;
      default:
        add(new Hr()); //need to do something here cant do HR
        break;
    }
  }
}

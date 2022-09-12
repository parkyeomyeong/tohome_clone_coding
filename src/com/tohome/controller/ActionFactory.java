package com.tohome.controller;

import com.tohome.controller.action.*;

public class ActionFactory {

    // ----- Singlton Patten -----
    private static ActionFactory instance = new ActionFactory();
    private ActionFactory() {
        super();
    }
    public static ActionFactory getInstance() {
        return instance;
    }
    // ----- Singlton Patten -----
    public Action getAction(String command) {
        Action action = null;
        if(command.equals("main")){
            action = new MainAction();
        } else if(command.equals("login")){
            action = new LoginAction();
        } else if(command.equals("login_form")){
            action = new LoginFormAction();
        } else if(command.equals("logout")){
            action = new LogoutAction();
        } else if (command.equals("join_form")) {
            action = new JoinFormAction();
        } else if (command.equals("id_check_form")) {
            action = new IdCheckFormAction();
        }
        return action;
    }
}

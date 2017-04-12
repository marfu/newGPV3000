///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package org.primefaces.sentinel;
//
//import ci.prosuma.gpv3000.entities.user.management.Menu;
//import ci.prosuma.gpv3000.entities.user.management.dto.MenuDTO;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import javax.annotation.PostConstruct;
//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.context.FacesContext;
//import javax.servlet.http.HttpSession;
//import org.primefaces.model.menu.DefaultMenuItem;
//import org.primefaces.model.menu.DefaultMenuModel;
//import org.primefaces.model.menu.DefaultSubMenu;
//import org.primefaces.model.menu.MenuModel;
//
///**
// *
// * @author tagsergi
// */
//@ManagedBean
//public class TestMenu {
//
//    private MenuModel model;
//
//    public static final Integer TOP_LEVEL = 1;
//
//    @PostConstruct
//    public void init() {
//        System.out.println(" @@@@@@@@ apppel method init : ");
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
//        model = new DefaultMenuModel();
//        String mag = (String) session.getAttribute("mag");
////        HashMap<String, List<Menu>> menuMap = (HashMap<String, List<Menu>>) session.getAttribute("menuMap");
////        if (mag != null && menuMap != null) {
////            List<Menu> listMenu = menuMap.get(mag);
////            if (listMenu != null) {
////                for (Menu menu : listMenu) {
////                    if (menu.getLevel() == TOP_LEVEL) {
////                        DefaultMenuItem item1 = new DefaultMenuItem(menu.getName());
////                        item1.setOutcome(menu.getOutcome());
////                        item1.setIcon(menu.getIcone());
////                        model.addElement(item1);
////                    }
////                }
////            }
////        }
//        List<MenuDTO> menuMap = (List<MenuDTO>) session.getAttribute("menuMap");
//        System.out.println(" @@@@@@@@  init   menuMap : " + menuMap.toString());
//        List<MenuDTO> tempList = new ArrayList<>();
//        for (MenuDTO menus : menuMap) {
//            MenuDTO mtemp = new MenuDTO();
//            mtemp.setIcone(menus.getIcone());
//            mtemp.setId(menus.getId());
//            mtemp.setLevel(menus.getLevel());
//            mtemp.setName(menus.getName());
//            mtemp.setOutcome(menus.getOutcome());
//            mtemp.setParentId(menus.getParentId());
//            mtemp.setRank(menus.getRank());
//            mtemp.setSousMenu(menus.getSousMenu());
//            mtemp.setUsed(menus.isUsed());
//            tempList.add(mtemp);
//
//        }
//
//        if (mag != null && tempList.size() > 0) {
//            //boucle pour le premier niveau du menu
//            menuMap.forEach((menu) -> {
//                //boucle pour le deuxieme niveau du menu
//                if (menu.getSousMenu() != null) {
//                    DefaultSubMenu sousMenu = new DefaultSubMenu(menu.getName());
//                    sousMenu.setIcon(menu.getIcone());
//                    menu.getSousMenu().forEach((sousM) -> {
//                        if (sousM.getSousMenu() != null) {
//
//                            DefaultSubMenu sousMenu2 = new DefaultSubMenu(sousM.getName());
//                            sousMenu2.setIcon(sousM.getIcone());
//                            //boucle pour le troisieme niveau du menu
//                            sousM.getSousMenu().forEach((sm1) -> {
//
//                                if (sm1.getSousMenu() != null) {
//                                    DefaultSubMenu sousMenu3 = new DefaultSubMenu(sm1.getName());
//                                    sousMenu3.setIcon(sm1.getIcone());
//                                    //boucle pour le quatrieme niveau du menu
//                                    sm1.getSousMenu().forEach((sm2) -> {
//                                        System.out.println("@@@@@@@@@@@@@@@@@@@@ Menu niveau QUATRE : " + sm2.getName());
//                                        DefaultMenuItem item1 = new DefaultMenuItem(sm2.getName());
//                                        item1.setIcon(sm2.getIcone());
//                                        item1.setOutcome(sm2.getOutcome());
//                                        sousMenu3.addElement(item1);
//                                    });
//                                } else {
//                                    System.out.println("################ Menu niveau TROIS : " + sm1.getName());
//                                    DefaultMenuItem item1 = new DefaultMenuItem(sm1.getName());
//                                    item1.setIcon(sm1.getIcone());
//                                    item1.setOutcome(sm1.getOutcome());
//                                    sousMenu2.addElement(item1);
//                                }
//
//                            });
//                            sousMenu.addElement(sousMenu2);
//                        } else {
//                            DefaultMenuItem item1 = new DefaultMenuItem(sousM.getName());
//                            item1.setIcon(sousM.getIcone());
//                            item1.setOutcome(sousM.getOutcome());
//                            sousMenu.addElement(item1);
//                            System.out.println("@@@@@@@@@@@@@@@@@@ Menu niveau DEUX : " + sousM.getName());
//                        }
//
//                    });
//                    model.addElement(sousMenu);
//                } else {
//                    DefaultMenuItem item1 = new DefaultMenuItem(menu.getName());
//                    item1.setIcon(menu.getIcone());
//                    item1.setOutcome(menu.getOutcome());
//                    model.addElement(item1);
//                    System.out.println("################### Menu niveau UN : " + menu.getName());
//                }
//
////                if (menu.getSousMenu() == null) {
////                    DefaultMenuItem item1 = new DefaultMenuItem(menu.getName());
////                    item1.setIcon(menu.getIcone());
////                    item1.setOutcome(menu.getOutcome());
////                    model.addElement(item1);
////                    System.out.println(" Ajout au menu principal | name : " + menu.getName());
////                } else {
////                    DefaultSubMenu sousMenu = new DefaultSubMenu(menu.getName());
////                    sousMenu.setIcon(menu.getIcone());
////                    System.out.println(" Cr\u00E9ation de sous menu | name : " + menu.getName());
////                    List<MenuDTO> sMenu = menu.getSousMenu();
////                    System.out.println(" sMenu.size()  : " + sMenu.size());
////                    while (sMenu.size() > 0) {
////                        System.out.println(" Debut du while  ");
////                        //Iterator<MenuDTO> it = menu.getSousMenu().iterator();
////                        Iterator<MenuDTO> it = sMenu.iterator();
////                        while (it.hasNext()) {
////                            MenuDTO nextMenu = it.next();
////                            System.out.println(" Debut du while  | nextMenu : " + nextMenu.toString());
////                            DefaultMenuItem subMenu = new DefaultMenuItem(nextMenu.getName());
////                            subMenu.setIcon(nextMenu.getIcone());
////
////                            if (nextMenu.getSousMenu() == null) {
////                                System.out.println(" @@@@@@@@@  Test SOUS MENU NO ");
////                                subMenu.setOutcome(menu.getOutcome());
////                                sousMenu.addElement(subMenu);
////                                it.remove();
////                            } else {
////                                System.out.println(" #########  Test SOUS MENU YES ");
////                                System.out.println(" #########  nextMenu.getSousMenu().size :  " + nextMenu.getSousMenu().size());
////                                sMenu = nextMenu.getSousMenu();
////                                System.out.println(" sMenu  : " + sMenu.toString());
////                            }
////
////                            //System.out.println(" sMenu  : " + sMenu.toString());
////                        }
////
////                    }
////                    model.addElement(sousMenu);
////                }
//            });
//        }
//
//    }
//
//    public MenuModel getModel() {
//        return model;
//    }
//
//    public void save() {
//        addMessage("Success", "Data saved");
//    }
//
//    public void update() {
//        addMessage("Success", "Data updated");
//    }
//
//    public void delete() {
//        addMessage("Success", "Data deleted");
//    }
//
//    public void addMessage(String summary, String detail) {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
//        FacesContext.getCurrentInstance().addMessage(null, message);
//    }
//
////    public Object getSubMenu(MenuDTO m){
////        if(m.getSousMenu().isEmpty()){
////            DefaultMenuItem item = new DefaultMenuItem(m.getName());
////            item.setIcon(m.getIcone());
////            item.setOutcome(m.getOutcome());
////            return item;
////        }else{
////            DefaultSubMenu sbM= new DefaultSubMenu(m.getName());
////            sbM.setIcon(m.getIcone());
////            m.getSousMenu().forEach((menu)->{
////                
////            });
////        }
////    }
//}

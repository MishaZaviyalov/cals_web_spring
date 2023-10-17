package com.example.prackone.controllers;

import com.example.prackone.classes.Course;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class HomeController {

    private ArrayList<Course> makeInfo(){
        ArrayList<Course> currencies = new ArrayList<Course>();
        currencies.add(new Course("Доллар США", 97.90));
        currencies.add(new Course("Дирхам ОАЭ", 26.60));
        currencies.add(new Course("Eвро", 98));
        currencies.add(new Course("Грузинский лари", 36.52));
        currencies.add(new Course("Армянский драм", 0.25));
        return currencies;
    }

    @GetMapping("/")
    String index(Model model){
        model.addAttribute("title", "Главная страница");
        return "index";
    }

    @GetMapping("/money")
    String money(Model model, @RequestParam(name = "count", defaultValue = "0") String startValue, @RequestParam(name = "typeOff", defaultValue = "Ничего") String typeOff, @RequestParam(name = "typeOn", defaultValue = "Ничего") String typeOn){
        ArrayList<Course> currencies = makeInfo();
        double finalResult = 0;

        if(typeOn.equals("Рубль") && !typeOff.equals("Ничего")){
            Optional<Course> courseNOW = currencies.stream().filter(el -> el.getName().equals(typeOff)).findFirst();
            double valueNOW = courseNOW.get().getValue();
            finalResult = Double.parseDouble(startValue) * valueNOW;
        }

        model.addAttribute("title", "Перевод валют");
        model.addAttribute("courses", currencies);
        model.addAttribute("finalResult", finalResult);
        return "exchange";
    }

}

package com.example.prackone.controllers;

import com.example.prackone.classes.MathMaker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CalcController {

    @GetMapping("/calc")
    String index(Model model){
        model.addAttribute("title", "Калькулятор");
        return "calc";
    }

    @PostMapping("/calc")
    String solution(Model model, @RequestParam(name = "mathematical_expression") String mathExp, RedirectAttributes redirectAttributes) {
        //Математика
        model.addAttribute("title", "Решение");
        mathExp = String.valueOf(MathMaker.solveMathExpression(mathExp));
        redirectAttributes.addAttribute("finalResult", mathExp);

        return "redirect:/calc/answer";
    }

    @GetMapping("/calc/answer")
    String answer(Model model, @RequestParam(name = "finalResult", defaultValue = "Значение пустое") String result){
        model.addAttribute("title", "Ответ");
        model.addAttribute("result", result);
        return "result";
    }
}

package org.trading_journal.example.trading_journal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.trading_journal.example.trading_journal.model.Strategy;
import org.trading_journal.example.trading_journal.service.StrategyService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/strategies")
public class StrategyController {

    @Autowired
    private StrategyService strategyService;

    @GetMapping
    public String index(Model model) {

        model.addAttribute("strategies", strategyService.findAllStrategies());

        return "strategy/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("strategy", strategyService.findByIdStrategy(id).get());
        return "strategy/show";
    }

    @GetMapping("/create")
    public String getCreate(Model model) {
        model.addAttribute("strategy", new Strategy());
        model.addAttribute("edit", false);
        return "strategy/create-or-edit";
    }

    @PostMapping("/create")
    public String postCreate(@Valid @ModelAttribute("strategy") Strategy newStrategy, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("strategy", new Strategy());
            model.addAttribute("edit", false);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/strategy/create-or-edit";
        }

        strategyService.create(newStrategy);

        return "redirect:/strategies";
    }

    @GetMapping("/edit/{id}")
    public String getUpdate(@PathVariable Integer id, Model model) {
        model.addAttribute("strategy", strategyService.findByIdStrategy(id).get());
        model.addAttribute("edit", true);
        return "/strategy/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String postUpdate(@Valid @ModelAttribute("strategy") Strategy newStrategy, BindingResult bindingResult,
            @PathVariable Integer id, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("strategy", strategyService.findByIdStrategy(id).get());
            model.addAttribute("edit", true);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/strategy/create-or-edit";
        }

        strategyService.create(newStrategy);

        return "redirect:/strategies";

    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        strategyService.deleteById(id);
        return "redirect:/strategies";
    }
}

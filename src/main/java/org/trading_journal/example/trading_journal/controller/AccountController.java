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
import org.trading_journal.example.trading_journal.model.Account;
import org.trading_journal.example.trading_journal.service.AccountService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("accounts", accountService.findAllAccounts());
        return "/account/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("account", accountService.findById(id).get());
        return "/account/show";
    }

    @GetMapping("/create")
    public String getCreate(Model model) {

        model.addAttribute("account", new Account());
        model.addAttribute("edit", false);

        return "/account/create-or-edit";
    }

    @PostMapping("/create")
    public String postCreate(@Valid @ModelAttribute("account") Account newAccount, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("account", new Account());
            model.addAttribute("edit", false);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/account/create-or-edit";
        }

        accountService.create(newAccount);
        return "redirect:/accounts";

    }

    @GetMapping("/edit/{id}")
    public String getUpdate(@PathVariable Integer id, Model model) {
        model.addAttribute("account", accountService.findById(id).get());
        model.addAttribute("edit", true);
        return "/account/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String postUpdate(@Valid @ModelAttribute("account") Account newAccount, BindingResult bindingResult,
            @PathVariable Integer id, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("account", accountService.findById(id).get());
            model.addAttribute("edit", true);
            model.addAttribute("errros", bindingResult.getAllErrors());
            return "/account/create-or-edit";
        }

        accountService.update(newAccount);

        return "redirect:/accounts";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        accountService.deleteById(id);
        return "redirect:/accounts";
    }
}

package org.trading_journal.example.trading_journal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.trading_journal.example.trading_journal.model.Trade;
import org.trading_journal.example.trading_journal.service.AccountService;
import org.trading_journal.example.trading_journal.service.StrategyService;
import org.trading_journal.example.trading_journal.service.TradeService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/trades")
public class TradeController {

    // Autowired dei servizi necessari

    @Autowired
    private TradeService tradeService;

    @Autowired
    private StrategyService strategyService;

    @Autowired
    private AccountService accountService;

    // Endpoint chiamata Index
    @GetMapping
    public String index(Model model) {
        List<Trade> trades = tradeService.findAllTrades();
        model.addAttribute("trades", trades);
        return "trade/index";
    }

    // Endpoint chiamata show
    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Trade> trade = tradeService.findTradeById(id);

        if (trade.isEmpty()) {
            return "trade/index";
        }

        model.addAttribute("trade", trade.get());

        return "trade/show";
    }

    // Endpoint chiamata create get
    @GetMapping("/create")
    public String getCreate(Model model) {

        // Faccio un attributo con un oggetto trade che poi verrà popolato nel form
        model.addAttribute("trade", new Trade());
        // Passo un edit falso per l'action del form (ternario)
        model.addAttribute("edit", false);
        // Passo un attributo che contiene la lista delle strategie
        model.addAttribute("strategies", strategyService.findAllStrategies());
        // Passo un attributo che contiene la lista degli account
        model.addAttribute("accounts", accountService.findAllAccounts());

        return "trade/create-or-edit";

    }

    // Entpoint chiamata create post
    @PostMapping("/create")
    public String postCreate(@Valid @ModelAttribute("trade") Trade newTrade, BindingResult bindingResult, Model model) {

        // Se ci sono errori reindirizzo all'endpoint precedente
        if (bindingResult.hasErrors()) {
            // Se ci sono errori nel form passo tutti gli attributi necessari
            model.addAttribute("strategies", strategyService.findAllStrategies());
            model.addAttribute("accounts", accountService.findAllAccounts());
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("edit", false);
            return "trade/create-or-edit";
        }

        // Salvo il nuovo trade
        tradeService.create(newTrade);

        return "redirect:/trades";

    }

    // Endpoint chiamata update get
    @GetMapping("/edit/{id}")
    public String getUpdate(@PathVariable Integer id, Model model) {

        model.addAttribute("trade", tradeService.findTradeById(id).get());
        model.addAttribute("edit", true);
        model.addAttribute("strategies", strategyService.findAllStrategies());
        model.addAttribute("accounts", accountService.findAllAccounts());

        return "trade/create-or-edit";
    }

    // Endpoint chiamata update put
    @PostMapping("/edit/{id}")
    public String postUpdate(@Valid @ModelAttribute("trade") Trade newTrade, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            // Se ci sono errori nel form passo tutti gli attributi necessari
            model.addAttribute("strategies", strategyService.findAllStrategies());
            model.addAttribute("accounts", accountService.findAllAccounts());
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("edit", true);
            return "trade/create-or-edit";
        }

        tradeService.update(newTrade);

        return "redirect:/trades";

    }

    // Endpoint chiamata delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Trade trade = tradeService.findTradeById(id).get();

        tradeService.delete(trade);
        return "redirect:/trades";
    }

}

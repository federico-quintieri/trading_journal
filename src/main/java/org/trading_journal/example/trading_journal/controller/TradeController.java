package org.trading_journal.example.trading_journal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.trading_journal.example.trading_journal.model.Trade;
import org.trading_journal.example.trading_journal.service.TradeService;
import org.springframework.security.core.Authentication;

@Controller
@RequestMapping("/trades")
public class TradeController {

    // Autowired dei servizi necessari

    @Autowired
    private TradeService tradeService;

    @GetMapping
    public String index(Authentication authentication, Model model) {
        List<Trade> trades = tradeService.findAllTrades();
        model.addAttribute("trades", trades);
        model.addAttribute("username", authentication.getName());
        return "trade/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Trade> trade = tradeService.findTradeById(id);

        if (trade.isEmpty()) {
            return "trade/index";
        }

        model.addAttribute("trade", trade.get());

        return "trade/show";
    }

}

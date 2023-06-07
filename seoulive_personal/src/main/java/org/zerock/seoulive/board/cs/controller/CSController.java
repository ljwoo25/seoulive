package org.zerock.seoulive.board.cs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/cs/*")
public class CSController {

    @RequestMapping("/view")
    public String view() {
        return "board/cs/view";
    }
}

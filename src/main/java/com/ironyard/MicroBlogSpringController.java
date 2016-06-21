package com.ironyard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by illladell on 6/20/16.
 */

@Controller
public class MicroBlogSpringController {
    ArrayList<Message> messages = new ArrayList<>();

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        String name = (String) session.getAttribute("username");
        User user = null;
        if (name != null) {
            user = new User(name);
        }
        for (int i = 0; i < messages.size(); i++) {
            messages.get(i).id = i+1;
        }
        model.addAttribute("user", user);
        model.addAttribute("messages", messages);

        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(String username, HttpSession session) throws Exception {
        if (username == null) {
            throw new Exception("Didn't receive username");
        }
        session.setAttribute("username", username);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path = "/add-message", method = RequestMethod.POST)
    public String add(String text, HttpSession session) {
        Message message = new Message(messages.size() + 1, text);
        messages.add(message);
        session.setAttribute("message", message);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete-message", method = RequestMethod.POST)
    public String delete(Integer id) {
        messages.remove(id - 1);
        return "redirect:/";
    }


}

package com.ironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by illladell on 6/20/16.
 */

@Controller
public class MicroBlogSpringController {

    @Autowired
    UserRepository users;

    @Autowired
    MessageRepository messages;
   // ArrayList<Message> messages = new ArrayList<>();

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        String name = (String) session.getAttribute("username");
        if (name != null) {
            model.addAttribute("user", users.findByName(name));
        }
        /*for (int i = 0; i < messages.size(); i++) {
            messages.get(i).id = i+1;
        }*/
        Iterable<Message> mess = messages.findAll();
        model.addAttribute("messages", mess);

        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(String username, HttpSession session) throws Exception {
        User user = users.findByName(username);
        if (user == null) {
            user = new User(username);
            users.save(user);
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
    public String add(Integer id, String text, HttpSession session) {
        Message message = new Message(id, text);
        messages.save(message);
        session.setAttribute("message", message);
        return "redirect:/";
    }

    @RequestMapping(path = "/delete-message", method = RequestMethod.POST)
    public String delete(Integer id) {
        messages.delete(id);
        return "redirect:/";
    }

    @RequestMapping(path = "edit-message", method = RequestMethod.POST)
    public String edit(Integer id, String text, HttpSession session) {
        Message editMessage = new Message(id, text);
        messages.save(editMessage);
        session.setAttribute("message",editMessage);
        return "redirect:/";
    }
}

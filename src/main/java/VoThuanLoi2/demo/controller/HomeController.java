package VoThuanLoi2.demo.controller;

import VoThuanLoi2.demo.entity.Blog;
import VoThuanLoi2.demo.entity.Book;
import VoThuanLoi2.demo.entity.Category;
import VoThuanLoi2.demo.entity.Subscribe;
import VoThuanLoi2.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BookService bookService;
    @Autowired
    private MailService mailService;
    @Autowired
    private SubscribeService subscribeService;

    @GetMapping("/")
    public String handel(){


        return "redirect:/home";
    }

    @GetMapping("/home")
    public String index(Model model){
        List<Category> listCategory = categoryService.getAllCategories();
        List<List<Category>> handelSubList = new ArrayList<>();
        for (int i = 0; i < listCategory.size(); i += 3) {
            int end = Math.min(i + 3, listCategory.size());
            List<Category> subList = listCategory.subList(i, end);
            handelSubList.add(subList);
        }

        model.addAttribute("handelSubList", handelSubList);
        model.addAttribute("listSeller", bookService.getTopBestSeller(6));
        model.addAttribute("listNewProduct", bookService.getTopNewProduct(3));
        model.addAttribute("listLastedBlog", blogService.getLastedBlog(4));
        //model.addAttribute("sliderList", blogService.getLastedBlog(2));
        model.addAttribute("listTopSale", bookService.getListTopSale(4));
        model.addAttribute("subscribe", new Subscribe());

        return "home/index";
    }

    @PostMapping("/subscribe")
    public String handelForm(@RequestParam String email){
        Subscribe checkSub = subscribeService.findByEmail(email);

        if (checkSub == null){
            Subscribe s = new Subscribe();
            s.setEmail(email);
            subscribeService.save(s);

            //Handel notify to mail
            String body = "Thank you very much for subscribing to our website! " +
                    "\nWe will send you the latest offers exclusively. ";
            //mail Customer
            String mailAddress = email;
            //send mail
            mailService.sendNewMail(mailAddress,"You have successfully subscribed to the LTech", body);
        }

        return "success";
    }
}

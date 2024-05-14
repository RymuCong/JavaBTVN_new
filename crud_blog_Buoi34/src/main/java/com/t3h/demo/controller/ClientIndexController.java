package com.t3h.demo.controller;

import com.t3h.demo.entity.Blog;
import com.t3h.demo.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ClientIndexController {

    private BlogRepository blogRepo;

    @Autowired
    public ClientIndexController(BlogRepository blogRepo) {
        this.blogRepo = blogRepo;
    }

    @GetMapping("")
    public String clientIndex (Model theModel)
    {
        String page = "client_index";
        theModel.addAttribute("page",page);
        return "client_index";
    }

    @GetMapping("contact")
    public String clientContact (Model model)
    {
        String page = "client_contact";

        model.addAttribute("page",page);

        return "client_index";
    }

    @GetMapping("admin")
    public String adminIndex (Model model)
    {
        String page = "admin_index";
        model.addAttribute("page",page);
        return "admin_index";
    }

    @GetMapping("blog")
    public String getAllBlog (Model theModel)
    {
        List<Blog> blogs = blogRepo.findAll();
        String page = "blog_list_client";
        theModel.addAttribute("blogs", blogs);
        theModel.addAttribute("page", page);
        return "client_index";
    }

    @GetMapping("/blog/{id}")
    public String getSingleBlog(@PathVariable("id") Integer id, Model model) {
        Optional<Blog> OpBlog = blogRepo.findById(id);

        Blog blog = OpBlog.get();

        String page = "blog_detail_client";
        model.addAttribute("page",page);

        model.addAttribute("blog", blog);
        return "client_index"; // return the view name
    }

    @GetMapping("search")
    public String searchBlog (Model model, @RequestParam(required = false) String data)
    {
        String page = "blog_search";
        model.addAttribute("page",page);

        // if data is null, return all blogs

        List<Blog> blogs;

        if (data == null || data.isEmpty() || data.trim().isEmpty())
        {
            blogs = blogRepo.findAll();
            model.addAttribute("blogs", blogs);
        }
        else
            blogs = blogRepo.findByBlogNameContaining(data);

        model.addAttribute("blogs", blogs);
        return "client_index";
    }
}

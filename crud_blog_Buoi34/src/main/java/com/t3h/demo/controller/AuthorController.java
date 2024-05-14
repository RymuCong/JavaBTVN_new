package com.t3h.demo.controller;

import com.t3h.demo.entity.Author;
import com.t3h.demo.entity.Blog;
import com.t3h.demo.repository.BlogRepository;
import com.t3h.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/author")
public class AuthorController {
    private AuthorRepository authorRepo;

    private BlogRepository blogRepo;

    @Autowired
    public AuthorController(AuthorRepository authorRepo, BlogRepository blogRepo) {
        this.authorRepo = authorRepo;
        this.blogRepo = blogRepo;
    }

    @GetMapping("")
    public String getAll (Model model)
    {
        List <Author> authors = authorRepo.findAll();
        String page = "authors_list";
        model.addAttribute("authors",authors);
        model.addAttribute("page",page);
        return "admin_index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id)
    {
        Optional<Author> tempauthor = authorRepo.findById(id);
        if (tempauthor.isEmpty())
        {
            System.out.println("Not found the id :" + id);
        }
        else
        {
            List<Blog> blogs = blogRepo.findByauthorId(id);
            // Set the author of these blogs to null
            for (Blog blog : blogs) {
                blog.setAuthor(null);
                blogRepo.save(blog);
            }
            // Delete the author
            authorRepo.deleteById(id);
        }

        return "redirect:/admin/author";
    }

}

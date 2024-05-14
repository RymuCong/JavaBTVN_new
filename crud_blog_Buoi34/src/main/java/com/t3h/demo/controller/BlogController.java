package com.t3h.demo.controller;

import com.t3h.demo.entity.Blog;
import com.t3h.demo.repository.AuthorRepository;
import com.t3h.demo.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/blog")
public class BlogController {

    @Autowired
    private BlogRepository blogRepo;

    @Autowired
    private AuthorRepository authorRepo;

    @GetMapping("")
    public String getAll(Model theModel) {
        List<Blog> blogs = blogRepo.findAll();

        String page = "blog_list";
        theModel.addAttribute("blogs", blogs);
        theModel.addAttribute("page", page);
        return "admin_index";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Blog> tempBlog = blogRepo.findById(id);
        if (tempBlog.isEmpty()) {
            System.out.println("Not found the id :" + id);
        } else
            blogRepo.deleteById(id);
        return "redirect:/admin/blog";
    }

    @GetMapping("addForm")
    public String showFormForAdd (Model theModel)
    {
        // create model attribute to the bind form data
        Blog theBlog = new Blog();

        theModel.addAttribute("page","admin-blog-add");

        theModel.addAttribute("blog", theBlog);

        theModel.addAttribute("authors",  authorRepo.findAll());

        return "admin_index";
    }

    @PostMapping("save")
    public String save(@ModelAttribute Blog blog, @RequestParam("imageFile") MultipartFile file) throws IOException
    {
        // url save image
        String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images";

        // not updated blog createdAt if it has already
        if (blog.getCreatedAt() == null)
            blog.setCreatedAt(new Date());

        blog.setUpdatedAt(new Date());

        System.out.println(blog.getContent());

        if (!file.isEmpty())
        {
            // url image
            blog.setImage(file.getOriginalFilename());

            // luu anh vao /images/
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
        }

        // save the blog
        blogRepo.save(blog);

        return "redirect:/admin/blog";
    }

    @GetMapping("edit/{id}")
    public String edit (@PathVariable Integer id, Model model)
    {
        Optional <Blog> editBlog = blogRepo.findById(id);

        if (editBlog.isEmpty())
            System.out.println("Not found blog id: " + id);

        Blog blog = editBlog.get();

        model.addAttribute("page", "admin-blog-edit");

        model.addAttribute("blog", blog);

        model.addAttribute("authors",  authorRepo.findAll());

        return "admin_index";
    }

}

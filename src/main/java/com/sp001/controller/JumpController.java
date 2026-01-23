package com.sp001.controller;

import com.sp001.mapper.BooksMapper;
import com.sp001.mapper.StudentsMapper;
import com.sp001.pojo.Books;
import com.sp001.pojo.Students;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping
public class JumpController {
    //Mapper应该在这里用 @Autowired注入：在成员变量上
    @Autowired
    private StudentsMapper studentsMapper;// 注意变量名应为小写开头
    @Autowired
    private BooksMapper booksMapper;
    @GetMapping("/register")
    public String toRegisterPage() {
        return "register";
    }
    @PostMapping("/register")
    public String register(
                           @RequestParam("nickname") String nickname,
                           @RequestParam("pwd") String pwd,
                           @RequestParam("repwd") String repwd,
                           Model model) {
        if (!pwd.equals(repwd)) {
            model.addAttribute("registerMsg", "两次密码不一致。");
            return "register";
        }
        Students existingStudent = studentsMapper.findByNickname(nickname);
        if (existingStudent != null) {
            model.addAttribute("registerMsg", "该昵称已被注册。");
            return "register";
        }
        Students student = new Students();
        student.setNickname(nickname);
        student.setPwd(pwd);
        studentsMapper.insert(student);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String toLoginPage(Model model, HttpServletRequest request) {
        String msg000 = (String) request.getAttribute("msg000");
        if (msg000 != null) {
            model.addAttribute("msg000", msg000);
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("nickname") String nickname,
            @RequestParam("pwd") String pwd,
            RedirectAttributes redirectAttributes, // 新增此参数
            HttpSession session) {
        Students student = studentsMapper.findByNickname(nickname);
        // 用户不存在
        if (student == null || !pwd.equals(student.getPwd())) {
            redirectAttributes.addFlashAttribute("msg", "账号或密码错误。"); // 修改此处
            return "redirect:/login";
//            使用return "redirect:/login"时，会发起新的 HTTP 请求，原model中的数据会丢失，导致登录页无法获取msg属性
//            RedirectAttributes的addFlashAttribute方法可以将数据暂存到会话中，供重定向后的页面读取，读取后自动清除，适合传递临时消息（如错误提示）
        }
        session.setAttribute("loginId", student.getId()); // 将ID存入Session
        session.setAttribute("loginUser", student);      // 将整个用户对象存入Session

        return "redirect:/button";
    }
    @GetMapping("/button")
    public String toButtonPage(Model model, HttpServletRequest request) {
        String msg000 = (String) request.getAttribute("msg000");
        if (msg000 != null) {
            model.addAttribute("msg000", msg000);
            return "login";
        }
        return "button";
    }
    @GetMapping("/my-page")
    public String myPage() {
        return "my-page";
    }
    @GetMapping("/buybooks")
    public String buyBooks(
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {

        Map<String, Books> booksMap = new HashMap<>(); // 初始化空集合

        if (keyword != null && !keyword.trim().isEmpty()) {
            // 调用上面的模糊匹配查询
            Books book = booksMapper.findByBookname(keyword);

            if (book != null) { // 只有查询到相关书籍时才添加
                booksMap.put(book.getBookname(), book);
            }
            model.addAttribute("keyword", keyword);
        }

        model.addAttribute("booksMap", booksMap); // 空集合会触发前端提示
        return "buybooks";
    }
    @GetMapping("/sellbooks")
    public String sellBooks() {
        return "sellbooks";
    }

    // 修正书籍详情页逻辑
    @GetMapping("/bookdetails/{bookId}")
    public String bookDetail(
            @PathVariable Integer bookId,
            Model model,
            HttpSession session) {

        Students currentBuyer = (Students) session.getAttribute("loginUser");
        if (currentBuyer == null) {
            return "redirect:/login";
        }
        Books book = booksMapper.getBookById(bookId);
        if (book == null) {
            model.addAttribute("error", "书籍不存在");
            return "error";
        }
        // 修正：使用studentsMapper调用findById方法
        int sellerId = book.getSellerId();
        Students sellerStudent = studentsMapper.findById(sellerId);
        model.addAttribute("book", book);
        model.addAttribute("currentBuyer", currentBuyer);
        model.addAttribute("sellerStudent", sellerStudent);
        model.addAttribute("buyerId", currentBuyer.getId()); // 补充传递买家ID到前端
        return "bookdetails";
    }
    // 修正购买接口逻辑
    @PostMapping("/purchase")
    @Transactional // 补充正确的事务注解包
    public String purchase(
            @RequestParam Integer bookId,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        Students currentBuyer = (Students) session.getAttribute("loginUser");
        if (currentBuyer == null) {
            return "redirect:/login";
        }

        // 修正：使用booksMapper获取书籍
        Books book = booksMapper.getBookById(bookId); // 原代码错误：books.getId(bookId)
        if (book == null) {
            model.addAttribute("error", "书籍不存在");
            return "bookdetails";
        }
        Students seller = studentsMapper.findById(book.getSellerId()); // 原代码错误：student.getStudentById(...)
        if (!password.equals(currentBuyer.getPwd())) {
            model.addAttribute("error", "密码错误");
            return "bookdetails";
        }
        currentBuyer.setPurchase_times(currentBuyer.getPurchase_times() + 1);
        seller.setSales_times(seller.getSales_times() + 1);

        studentsMapper.update(currentBuyer);
        studentsMapper.update(seller);

        return "redirect:/purchase-success";
    }
    @GetMapping("/purchase-success")
    public String purchaseSuccess() {
        return "purchase-success";
    }
}

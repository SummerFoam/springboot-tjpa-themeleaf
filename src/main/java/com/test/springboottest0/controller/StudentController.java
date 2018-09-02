package com.test.springboottest0.controller;

import com.test.springboottest0.model.Student;
import com.test.springboottest0.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/show")
    public String showPage(){
        return "stu_add";
    }
    //添加
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(Student student, HttpServletRequest request){
        System.out.println(request.getParameter("name"));
        student.setName(request.getParameter("name"));
        int age=Integer.parseInt(request.getParameter("age"));
        student.setAge(age);
        studentService.saveStudent(student);
        return "redirect:/student/findAll";
    }
    //查找所有
    @RequestMapping(value = "/findAll")
    public ModelAndView queryStudentList(){
        ModelAndView mv=new ModelAndView("stu_list");
        List<Student> list = studentService.queryStudentList();
        mv.addObject("list",list);
        return mv;
    }

    //根据Id查询，修改时回显数据
    @RequestMapping(value = "/before/{stuId}",method = RequestMethod.GET)
    public String updateBefore(@PathVariable Integer stuId, Model model){
        Student student = studentService.queryStudentById(stuId);
        System.out.println(student);
        model.addAttribute("student",student);
        return "stu_update";
    }

    //修改
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public String updateStudent(Student student,HttpServletRequest request){
        student.setName(request.getParameter("name"));
        int age=Integer.parseInt(request.getParameter("age"));
        student.setAge(age);
        student = studentService.updateStudent(student);
        if (student != null){
            return "redirect:/student/findAll";
        }else{
            return "stu_list";
        }
    }

    //删除
    @RequestMapping(value = "/delete/{stuId}")
    public String deleteStudent(@PathVariable Integer stuId){
        studentService.delteStudent(stuId);
        return "redirect:/student/findAll";
    }
}

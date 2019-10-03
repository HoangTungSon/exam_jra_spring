package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import source.model.ClassRoom;
import source.model.Student;
import source.service.ClassService;
import source.service.StudentService;

import java.util.Optional;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassService classService;

    @ModelAttribute("classes")
    public Iterable<ClassRoom> classes(){
        return classService.findAll();
    }

    @GetMapping("/students")
    public ModelAndView listStudents(@RequestParam("s") Optional<String> s, @PageableDefault(value = 10, sort = "name") Pageable pageable ){
        Page<Student> students;
        if(s.isPresent()){
            students = studentService.findAllByClassRoomContaining(s.get(), pageable);
        } else {
            students = studentService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/student/list");
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @GetMapping("/create-student")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/student/create");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @PostMapping("/create-student")
    public ModelAndView saveStudent(@ModelAttribute("student") Student student, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("/student/create");
        modelAndView.addObject("student", new Student());

        if(!bindingResult.hasErrors()) {
            studentService.save(student);
            modelAndView.addObject("message", "New student created successfully");
            return modelAndView;
        } else {
            modelAndView.addObject("message", "New student has failed to create ");
            return modelAndView;
        }
    }

    @GetMapping("/edit-student/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Student student = studentService.findById(id);
        if(student != null) {
            ModelAndView modelAndView = new ModelAndView("/student/edit");
            modelAndView.addObject("student", student);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-student")
    public ModelAndView updateStudent(@ModelAttribute("student") Student student, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("/student/edit");

        if(!bindingResult.hasErrors()) {
            studentService.save(student);
            modelAndView.addObject("student", student);
            modelAndView.addObject("message", "Student updated successfully");
            return modelAndView;
        } else {
            modelAndView.addObject("message", "Student updated failed");
            return modelAndView;
        }
    }

    @GetMapping("/delete-student/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Student student = studentService.findById(id);
        if(student != null) {
            ModelAndView modelAndView = new ModelAndView("/student/delete");
            modelAndView.addObject("student", student);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-student")
    public String deleteStudent(@ModelAttribute("student") Student student){
        studentService.remove(student.getId());
        return "redirect:students";
    }

}

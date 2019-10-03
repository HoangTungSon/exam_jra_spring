package source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import source.model.ClassRoom;
import source.model.Student;
import source.service.ClassService;
import source.service.StudentService;

@Controller
public class ClassController {
    @Autowired
    private ClassService classService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/classes")
    public ModelAndView listClass() {
        Iterable<ClassRoom> classRooms = classService.findAll();
        ModelAndView modelAndView = new ModelAndView("/class/list");
        modelAndView.addObject("classes", classRooms);
        return modelAndView;
    }

    @GetMapping("/create-class")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/class/create");
        modelAndView.addObject("classRoom", new ClassRoom());
        return modelAndView;
    }

    @PostMapping("/create-class")
    public ModelAndView saveClass(@ModelAttribute("classRoom") ClassRoom classRoom) {
        classService.save(classRoom);

        ModelAndView modelAndView = new ModelAndView("/class/create");
        modelAndView.addObject("classRoom", new ClassRoom());
        modelAndView.addObject("message", "New class created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-class/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        ClassRoom classRoom = classService.findById(id);
        if (classRoom != null) {
            ModelAndView modelAndView = new ModelAndView("/class/edit");
            modelAndView.addObject("classRoom", classRoom);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-class")
    public ModelAndView updateClass(@ModelAttribute("classRoom") ClassRoom classRoom) {
        classService.save(classRoom);
        ModelAndView modelAndView = new ModelAndView("/class/edit");
        modelAndView.addObject("classRoom", classRoom);
        modelAndView.addObject("message", "Class updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-class/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        ClassRoom classRoom = classService.findById(id);
        if (classRoom != null) {
            ModelAndView modelAndView = new ModelAndView("/class/delete");
            modelAndView.addObject("classRoom", classRoom);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-class")
    public String deleteClass(@ModelAttribute("class") ClassRoom classRoom) {
        classService.remove(classRoom.getId());
        return "redirect:provinces";
    }

    @GetMapping("/view-class/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id) {
        ClassRoom classRoom = classService.findById(id);
        if (classRoom == null) {
            return new ModelAndView("/error.404");
        }

        Iterable<Student> students = studentService.findAllByClassRoom(classRoom);

        ModelAndView modelAndView = new ModelAndView("/class/view");
        modelAndView.addObject("classRoom", classRoom);
        modelAndView.addObject("students", students);
        return modelAndView;
    }
}

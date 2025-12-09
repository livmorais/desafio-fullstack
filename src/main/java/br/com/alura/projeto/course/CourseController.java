package br.com.alura.projeto.course;

import br.com.alura.projeto.category.Category;
import br.com.alura.projeto.category.CategoryRepository;
import br.com.alura.projeto.user.Role;
import br.com.alura.projeto.user.User;
import br.com.alura.projeto.user.UserRepository;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class CourseController {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public CourseController(CourseRepository courseRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/admin/courses")
    public String list(Model model) {
        List<CourseDTO> courses = courseRepository.findAll()
                .stream()
                .map(CourseDTO::new)
                .toList();

        model.addAttribute("courses", courses);
        return "admin/course/list";
    }

    @GetMapping("/admin/course/new")
    public String create(NewCourseForm newCourseForm, Model model) {
        List<Category> categories = categoryRepository.findAll();
         List<User> instructors = userRepository.findByRole(Role.INSTRUCTOR);
        
        model.addAttribute("categories", categories);
        model.addAttribute("instructors", instructors);
        return "admin/course/newForm";
    }

    @Transactional
    @PostMapping("/admin/course/new")
    public String save(@Valid NewCourseForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return create(form, model);
        }

        if (courseRepository.existsByCode(form.getCode())) {
            result.rejectValue("code", null, "Já existe um curso com esse código.");
        }

        if (result.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("instructors", userRepository.findByRole(Role.INSTRUCTOR));
            return "admin/course/newForm";
        }

        User instructor = userRepository.findById(form.getInstructorId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instrutor não encontrado"));
        Category category = categoryRepository.findById(form.getCategoryId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));

        courseRepository.save(form.toModel(instructor, category));
        return "redirect:/admin/courses";
    }

    @Transactional
    @PostMapping("/course/{code}/inactive")
    public String updateStatus(@PathVariable("code") String courseCode) {

        Course course = courseRepository.findByCode(courseCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));

        course.setStatus(CourseStatus.INACTIVE);
        course.setInactiveAt(LocalDateTime.now());


        return "redirect:/admin/courses";
    }

    @Transactional
    @PostMapping("/course/{code}/active")
    public String activate(@PathVariable("code") String courseCode) {

        Course course = courseRepository.findByCode(courseCode).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));

        course.setStatus(CourseStatus.ACTIVE);
        course.setInactiveAt(null);

        return "redirect:/admin/courses";
    }

    @GetMapping("/admin/course/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));

        NewCourseForm form = new NewCourseForm();
        form.setId(course.getId());
        form.setName(course.getName());
        form.setCode(course.getCode());
        form.setDescription(course.getDescription());
        form.setInstructorId(course.getInstructor().getId());
        form.setCategoryId(course.getCategory().getId());

        model.addAttribute("newCourseForm", form);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("instructors", userRepository.findAll());

        return "admin/course/editForm";
    }

    @Transactional
    @PostMapping("/admin/course/{id}/edit")
    public String updateCourse(@PathVariable Long id, @Valid NewCourseForm form, BindingResult result, Model model) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));

        User instructor = userRepository.findById(form.getInstructorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instrutor não encontrado"));

        Category category = categoryRepository.findById(form.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));

        courseRepository.findByCode(form.getCode()).ifPresent(existingCourse -> {
            if (!existingCourse.getId().equals(course.getId())) {
                result.rejectValue("code", null, "Já existe um curso com esse código.");
            }
        });

         if (result.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("instructors", userRepository.findAll());
            return "admin/course/editForm";
        }

        course.setName(form.getName());
        course.setCode(form.getCode());
        course.setDescription(form.getDescription());
        course.setInstructor(instructor);
        course.setCategory(category);

        courseRepository.flush();

        return "redirect:/admin/courses";
    }

    @GetMapping("/admin/course/{code}/details")
    public String courseDetailsPage(@PathVariable String code, Model model) {
        Course course = courseRepository.findByCode(code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));

        CourseDTO dto = new CourseDTO(course);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        model.addAttribute("course", dto);
        model.addAttribute("formatter", formatter);
        return "admin/course/details";
    }
}

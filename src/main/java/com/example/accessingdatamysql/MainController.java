package com.example.accessingdatamysql;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.*;
import java.sql.SQLOutput;
import java.util.HashMap;

@Controller
@RequestMapping(path = "/demo")
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private Configuration freemarkerConfig;

    @RequestMapping(path = "/add", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    String addNewUser(@RequestBody @Valid User user) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

//		User n = new User();
//		n.setName(name);
//		n.setEmail(email);
        userRepository.save(user);
        return "Saved";
    }

    @RequestMapping(path = "/addquestions", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    String addNewQuestions(@RequestBody @Valid Post questions) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

//		User n = new User();
//		n.setName(name);
//		n.setEmail(email);
        questionsRepository.save(questions);
        return "Saved";
    }


    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }


    @GetMapping(path = "/gethtml", produces = {MediaType.TEXT_HTML_VALUE})
    public @ResponseBody
    String getHtml() throws IOException {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

//		User n = new User();
//		n.setName(name);
//		n.setEmail(email);

        Template t = freemarkerConfig.getTemplate("input2.ftl");
        String html = t.toString();
        System.out.println(html);

        return html;
    }


    @RequestMapping(value = "/getpdf", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody
    ResponseEntity<InputStreamResource> getPdf() throws IOException {

        Template t = freemarkerConfig.getTemplate("input2.ftl");
        String html = t.toString();
        File htmlSource = new File(html);
//        File pdfDest = new File("./pdf/output.pdf");

		ByteArrayOutputStream out = new ByteArrayOutputStream();
        // pdfHTML specific code
        ConverterProperties converterProperties = new ConverterProperties();
        HtmlConverter.convertToPdf(new ByteArrayInputStream(html.getBytes()),
                out, converterProperties);



		ByteArrayInputStream bis = new ByteArrayInputStream(out.toByteArray());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));


    }
}

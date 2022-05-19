/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umy.project.ktp.coba;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author user
 */
@Controller
public class DummyController {

    DummyJpaController dummyController = new DummyJpaController();
    List<Dummy> data = new ArrayList<>();

    @RequestMapping("/read")
    @ResponseBody
    public List<Dummy> getDummy() {
        try {
            data = dummyController.findDummyEntities();
        } catch (Exception e) {}
        return data;
    }
    @RequestMapping("/create")
    public String createDummy() {
        return "create";
    }
    

}

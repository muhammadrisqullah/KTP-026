/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umy.project.ktp.coba;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 *
 * @author user
 */
@Controller
public class DummyController {

    DummyJpaController dummyController = new DummyJpaController();
    List<Dummy> data = new ArrayList<>();
    
    @RequestMapping("/dummy")
    
      public String getDummy(Model model){
    
        int record = dummyController.getDummyCount();
        String result = "";
        try{
            data = dummyController.findDummyEntities().subList(0, record);
        }
        catch (Exception e){
            result=e.getMessage();
        }
        
        model.addAttribute("goDummy", data);
         model.addAttribute("record", record);
         
        return "dummy";
    }

    @RequestMapping("/create")
    public String createDummy() {
        return "dummy/create";
    }

    @PostMapping(value = "/newdata", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String newDummy(HttpServletRequest data, @RequestParam("gambar") MultipartFile file) throws ParseException, Exception {
        Dummy dumdata = new Dummy();

        String id = data.getParameter("id");
        int iid = Integer.parseInt(id);

        String tanggal = data.getParameter("tanggal");
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(tanggal);

        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        byte[] image = file.getBytes();

        dumdata.setId(iid);
        dumdata.setTanggal(date);
        dumdata.setGambar(image);

        dummyController.create(dumdata);

        return "dummy/create";
    }
    

}

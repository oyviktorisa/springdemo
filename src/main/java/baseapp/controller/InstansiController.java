package baseapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import baseapp.domain.Instansi;
import baseapp.service.InstansiService;

@Controller
@RequestMapping("/user")
public class InstansiController
{
    @Autowired
    private InstansiService instansiServiceImpl;


    @RequestMapping(value = "/view-instansi", method = RequestMethod.GET)
    public String viewInstansi (Model model)
    {

        model.addAttribute ("instansiList",
                instansiServiceImpl.getInstansiList ());
        model.addAttribute ("instansi", new Instansi ());
        return "view-instansi";
    }


    @RequestMapping(value = "/add-instansi", method = RequestMethod.GET)
    public String addInstansi (Model model)
    {
        model.addAttribute ("instansi", new Instansi ());
        model.addAttribute ("isEdit", false);
        return "add-instansi";
    }


    @RequestMapping(value = "/add-instansi", method = RequestMethod.POST, params = "action=save")
    public String saveInstansi (@ModelAttribute("instansi") Instansi instansi,
            final BindingResult result, Model model)
    {
        List<FieldError> errList = new ArrayList<FieldError> ();
        if (instansi.getNama () == null || instansi.getNama ().isEmpty ()) {
            FieldError err = new FieldError ("instansi", "nama",
                    "Nama tidak boleh kosong");
            errList.add (err);
        }
        if (instansi.getDeskripsi () == null
                || instansi.getDeskripsi ().isEmpty ()) {
            FieldError err = new FieldError ("instansi", "deskripsi",
                    "Deskripsi tidak boleh kosong");
            errList.add (err);
        }
        if (!errList.isEmpty ()) {
            for (FieldError err : errList) {
                result.addError (err);
            }
        }
        if (instansi.getId () == null) {
            if (!errList.isEmpty ()) {
                model.addAttribute ("instansi", instansi);
                model.addAttribute ("isEdit", false);
                return "add-instansi";
            }

            instansiServiceImpl.addInstansi (instansi);
            return "redirect:/user/view-instansi?success";
        } else {
            if (!errList.isEmpty ()) {
                model.addAttribute ("instansi", instansi);
                model.addAttribute ("isEdit", false);
                return "add-instansi";
            }
            instansiServiceImpl.editInstansi (instansi);
            return "redirect:/user/view-instansi?successEdit";
        }

    }
    
    @RequestMapping(value = "/add-instansi", method = RequestMethod.POST, params = "action=cancel")
    public String cancelAddInstansi (@ModelAttribute("instansi") Instansi instansi,
            final BindingResult result, Model model)
    {
        return "redirect:/user/view-instansi";
    }
    
    @RequestMapping(value = "/edit-instansi", method = RequestMethod.POST, params = "action=edit")
    public String editInstansi (@ModelAttribute("instansi") Instansi instansi,
            Model model)
    {
        model.addAttribute ("instansi", instansiServiceImpl.getInstansiById (instansi.getId ()));
        model.addAttribute ("isEdit", true);
        return "add-instansi";
    }
    
    @RequestMapping(value = "/edit-instansi", method = RequestMethod.POST, params = "action=delete")
    public String deleteInstansi (@ModelAttribute("instansi") Instansi instansi,
            Model model)
    {
        instansiServiceImpl.deleteInstansiById (instansi.getId ());
        return "redirect:/user/view-instansi";
    }
}

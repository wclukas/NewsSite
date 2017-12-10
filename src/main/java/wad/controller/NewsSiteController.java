
package wad.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import wad.domain.File;
import wad.domain.Kategoria;
import wad.domain.Uutinen;
import wad.repository.FileRepository;
import wad.repository.UutinenRepository;
import wad.service.NewsSiteService;

@Controller
public class NewsSiteController {
    
    @Autowired
    private NewsSiteService newsSiteService;
    
    @Autowired
    private UutinenRepository uutinenRepository;
    
    @Autowired
    private FileRepository fileRepository;
    
    @GetMapping("/etusivu")
    public String getEtusivu(Model model) {
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "julkaisuAika");
        model.addAttribute("kategoriat", newsSiteService.tulostaKategoriat());
        model.addAttribute("loopit", uutinenRepository.findAll(pageable));
//        Tee tähän vielä parannuksia
        int uutismäärä = (int) uutinenRepository.count();
        Pageable pageable2 = PageRequest.of(0, uutismäärä, Sort.Direction.DESC, "julkaisuAika");
        model.addAttribute("uutiset", uutinenRepository.findAll(pageable2));
        return "etusivu";
    }
    
    @PostMapping("/form")
    public String post(@RequestParam String otsikko, @RequestParam String ingressi, 
            @RequestParam String teksti, @RequestParam("file") MultipartFile file) throws IOException{
//        Tähän pitää tulla se että tallentaa kuvan ja kirjoittajat jne
        if (!file.getContentType().equals("image/png")) {
            return "redirect:/kuva";
        }
        File fo = new File();
        fo.setContent(file.getBytes());
        fileRepository.save(fo);
       
        newsSiteService.uusiUutinen(otsikko, ingressi, teksti, fo);
        return "redirect:/etusivu";
    }
    
    @GetMapping("/{id}")
    public String getUutinen(Model model, @PathVariable Long id) {
        model.addAttribute("uutinen", newsSiteService.getUutinen(id));
        return "uutinen";
    }
    
    @GetMapping("/kategoriat/{id}")
    public String getKategoria(Model model, @PathVariable Long id) {
        Kategoria kategoria = newsSiteService.getKategoria(id);
        model.addAttribute("kategoria", kategoria);
        model.addAttribute("uutiset", newsSiteService.uutinenByKategoria(kategoria));
        return "kategoria";
    }
    
    @GetMapping("/form")
    public String getForm(Model model) {
        model.addAttribute("kategoriat", newsSiteService.tulostaKategoriat());
        model.addAttribute("kirjoittajat", newsSiteService.tulostaKirjoittajat());
        return "form";
    }
    
    @GetMapping("/kuva")
    public String kuva() {
        return "kuva";
    }
    
    @GetMapping(path = "/files/{id}", produces = "image/png")
    @ResponseBody
    public byte[] get(@PathVariable Long id) {
        Uutinen uutinen = uutinenRepository.getOne(id);
        return uutinen.getKuva().getContent();
    }
    
    
}

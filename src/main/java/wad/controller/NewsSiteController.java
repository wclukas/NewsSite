
package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wad.domain.Kategoria;
import wad.repository.UutinenRepository;
import wad.service.NewsSiteService;

@Controller
public class NewsSiteController {
    
    @Autowired
    private NewsSiteService newsSiteService;
    
    @Autowired
    private UutinenRepository uutinenRepository;
    
    @GetMapping("/")
    public String eka() {
        return "redirect:/etusivu";
    }
    
    @GetMapping("/etusivu")
    public String getEtusivu(Model model) {
        PageRequest pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "julkaisuAika");
        model.addAttribute("kategoriat", newsSiteService.tulostaKategoriat());
        model.addAttribute("lööpit", pageable);
        model.addAttribute("uutiset", newsSiteService.tulostaUutiset());
        return "etusivu";
    }
    
    @GetMapping("/{id}")
    public String getUutinen(Model model, @PathVariable Long id) {
        model.addAttribute("uutinen", uutinenRepository.getOne(id));
        return "uutinen";
    }
    
    @GetMapping("/kategoriat/{id}")
    public String getKategoria(Model model, @PathVariable Long id) {
        Kategoria kategoria = newsSiteService.getKategoria(id);
        model.addAttribute("kategoria", kategoria);
        model.addAttribute("uutiset", newsSiteService.uutinenByKategoria(kategoria));
        return "kategoria";
    }
    
    
}

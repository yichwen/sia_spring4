package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spittr.Spittle;
import spittr.data.SpittleRepository;

import java.util.List;

@Controller
@RequestMapping("/spittles")
public class SpittleController {

    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Spittle> spittles(Model model) {
        return spittleRepository.findSpittles(Long.MAX_VALUE, 20);
    }

    // we can use Model e.g.
//    public String spittles(Model model) {
//        model.addAttribute("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));
//        return "spittles";
//    }

    // we can use Map instead of Model e.g.
//    public String spittles(Map model) {
//        model.put("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));
//        return "spittles";
//    }

}

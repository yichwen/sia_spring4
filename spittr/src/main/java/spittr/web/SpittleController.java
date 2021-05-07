package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spittr.Spittle;
import spittr.data.SpittleRepository;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/spittles")
public class SpittleController {

    // not working, because it is not a constant expression
//    private static final String MAX_LONG_AS_STRING = Long.toString(Long.MAX_VALUE);
    private static final String MAX_LONG_AS_STRING = "" + Long.MAX_VALUE;

    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    // replace the spittles() method that works with the before and count parameters
    // view name is spittles
    @RequestMapping(method = RequestMethod.GET)
    public List<Spittle> spittles(
            @RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
            @RequestParam(value = "count", defaultValue = "20") int count) {
        return spittleRepository.findSpittles(max, count);
    }

    @RequestMapping(value="/{spittleId}", method=RequestMethod.GET)
    public String spittle(@PathVariable("spittleId") long spittleId, Model model) {
        Spittle spittle = spittleRepository.findOne(spittleId);
        if (spittle == null) {
            throw new SpittleNotFoundException();
        }
        model.addAttribute(spittle);
        return "spittle";
    }

    @RequestMapping(method=RequestMethod.POST)
    public String saveSpittle(Spittle spittle, Model model) {
        // yc001: using handleDuplicateSpittle() method to handle DuplicateSpittleException
//        try {
            spittleRepository.save(new Spittle(null, spittle.getMessage(), new Date(), spittle.getLongitude(), spittle.getLatitude()));
            return "redirect:/spittles";
//        } catch (DuplicateSpittleException e) {
//            return "error/duplicate";
//        }
    }


    // yc002: using AppWideExceptionHandler class, so that the exception handlers in th class
    // can handle exception across all controller classes
    // this exception handler will catch the exception happened within this controller class
//    @ExceptionHandler(DuplicateSpittleException.class)
//    public String handleDuplicateSpittle() {
//        return "error/duplicate";
//    }

}

package mtschellenberg.website.rebeccafinelli

import org.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring controller for the website.
 */
@Controller
class WebsiteController {

    /**
     * Returns the home page.
     *
     * @param model
     *
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    String getHomePage(Model model) {

        model.addAttribute("homeArt", "http://placehold.it/800x600");

        // The name of the JSP.
        return "Home";
    }

    /**
     * Returns the portfolio page.
     *
     * @param model
     *
     * @return
     */
    @RequestMapping(value = "/Portfolio", method = RequestMethod.GET)
    String getPortfolioPage(Model model) {

        // The name of the JSP.
        return "Portfolio";
    }

    /**
     * Returns the portfolio pictures for the portfolio page.
     *
     * @return
     */
    @RequestMapping(value = "/Portfolio/getPortfolio",
            method = RequestMethod.POST)
    @ResponseBody
    String getPortfolio() {

        String[] largePictures = new String[12];
        String[] smallPictures = new String[12];

        smallPictures[0] = "http://placehold.it/200x200";
        smallPictures[1] = "http://placehold.it/300x300";
        smallPictures[2] = "http://placehold.it/400x400";
        smallPictures[3] = "http://placehold.it/400x400";
        smallPictures[4] = "http://placehold.it/400x400";
        smallPictures[5] = "http://placehold.it/400x400";
        smallPictures[6] = "http://placehold.it/400x400";
        smallPictures[7] = "http://placehold.it/400x400";
        smallPictures[8] = "http://placehold.it/400x400";
        smallPictures[9] = "http://placehold.it/400x400";
        smallPictures[10] = "http://placehold.it/400x400";
        smallPictures[11] = "http://placehold.it/400x400";

        largePictures[0] = "http://placehold.it/200x200";
        largePictures[1] = "http://placehold.it/200x400";
        largePictures[2] = "http://placehold.it/400x200";
        largePictures[3] = "http://placehold.it/400x400";
        largePictures[4] = "http://placehold.it/400x600";
        largePictures[5] = "http://placehold.it/600x400";
        largePictures[6] = "http://placehold.it/600x600";
        largePictures[7] = "http://placehold.it/200x600";
        largePictures[8] = "http://placehold.it/600x200";
        largePictures[9] = "http://placehold.it/100x100";
        largePictures[10] = "http://placehold.it/100x200";
        largePictures[11] = "http://placehold.it/200x100";

        JSONObject jsonObject = new JSONObject("{}");
        jsonObject.put("large", largePictures);
        jsonObject.put("small", smallPictures);

        return jsonObject.toString();
    }

    /**
     * Returns the about page.
     *
     * @param model
     *
     * @return
     */
    @RequestMapping(value = "/About", method = RequestMethod.GET)
    String getAboutPage(Model model) {

        model.addAttribute("aboutText", "Rebecca Finelli is an intelligent, beautiful, and talented medical illustrator with an awesome website and a wonderful boyfriend.");

        // The name of the JSP.
        return "About";
    }

    /**
     * Returns the contact page.
     *
     * @param model
     *
     * @return
     */
    @RequestMapping(value = "/Contact", method = RequestMethod.GET)
    String getContactPage(Model model) {

        model.addAttribute("contactInfo", "Email:  rebecca at rebeccafinelli dot com");

        // The name of the JSP.
        return "Contact";
    }
}

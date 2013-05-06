package mtschellenberg.website.rebeccafinelli

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.servlet.ServletContext;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
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

    static final String PORTFOLIO_HOME = "../data/RebeccaFinelli/portfolio";
    static final String THUMBNAIL_HOME =
            "../data/RebeccaFinelli/portfolio/thumbnails";
    static final String THUMBNAIL_SUFFIX = "Thumb";
    static final String TITLE_HOME = "../data/RebeccaFinelli/portfolio/titles";
    static final String TITLE_SUFFIX = "Title";
    static final String CAPTION_HOME =
            "../data/RebeccaFinelli/portfolio/captions";
    static final String CAPTION_SUFFIX = "Caption";
    static final String ABOUT_FILE = "../data/RebeccaFinelli/about/about.txt";
    static final String RESUME_FILE = "../data/RebeccaFinelli/resume.pdf";
    static final String CONTACT_FILE =
            "../data/RebeccaFinelli/contact/contact.txt";

    @Autowired
    ServletContext servletContext;

    /**
     * Returns the home page.
     *
     * @param model
     *
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    String getHomePage(Model model) {

        // model.addAttribute("homeArt", "http://placehold.it/1000x800");

        // The name of the JSP.
        // return "Home";
        return "Portfolio";
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

        String[] pictures = getPortfolioPictures();
        String[] thumbnails = getPortfolioThumbnails(pictures);
        String[] titles = getPortfolioTitles(pictures);
        String[] captions = getPortfolioCaptions(pictures);

        for(int i = 0; i < pictures.length; ++i) {
            pictures[i] = PORTFOLIO_HOME + "/" + pictures[i];
        }

        JSONObject jsonObject = new JSONObject("{}");
        jsonObject.put("large", pictures);
        jsonObject.put("small", thumbnails);
        jsonObject.put("title", titles);
        jsonObject.put("caption", captions);

        return jsonObject.toString();
    }

    /**
     * Gets the pictures from the portfolio directory and returns their file
     * paths relative to the servlet.
     *
     * @return
     */
    private String[] getPortfolioPictures() {

        File directory = new File(servletContext.getRealPath("/") +
                PORTFOLIO_HOME);

        if(!directory.exists() || !directory.isDirectory()) {
            return new String[0];
        }

        File[] files = directory.listFiles();

        int count = 0;
        for(File file : files) {
            if(file.isDirectory()) {
                ++count;
            }
        }

        String[] pictures = new String[files.length - count];

        int i = 0;
        for(File file : files) {
            if(!file.isDirectory()) {
                pictures[i++] = file.name;
            }
        }

        return pictures;
    }

    /**
     * Gets the thumbnails from the portfolio's thumbnails directory that
     * correspond with the pictures found on the given file paths and returns
     * their file paths relative to the servlet.
     *
     * @param pictures
     *
     * @return
     */
    private String[] getPortfolioThumbnails(String[] pictures) {

        File directory = new File(servletContext.getRealPath("/") +
                THUMBNAIL_HOME);

        if(!directory.exists() || !directory.isDirectory()) {

            String[] thumbnails = new String[pictures.length];

            for(int i = 0; i < pictures.length; ++i) {
                thumbnails[i] = PORTFOLIO_HOME + "/" + pictures[i];
            }

            return thumbnails;
        }

        File[] files = directory.listFiles();
        String[] thumbnails = new String[files.length];

        for(int i = 0; i < files.length; ++i) {
            thumbnails[i] = files[i].name;
        }

        String[] sortedThumbnails = new String[pictures.length];

        for(int i = 0; i < pictures.length; ++i) {

            sortedThumbnails[i] = PORTFOLIO_HOME + "/" + pictures[i];

            for(String thumbnail : thumbnails) {

                String pictureBase = getPictureBase(pictures[i]);
                String thumbnailBase = getThumbnailBase(thumbnail);

                if(pictureBase.equals(thumbnailBase)) {

                    sortedThumbnails[i] = THUMBNAIL_HOME + "/" + thumbnail;
                    break;
                }
            }
        }

        return sortedThumbnails;
    }

    /**
     * Returns the base for the given picture file without the extension.
     *
     * @param name
     *
     * @return
     */
    private String getPictureBase(String name) {

        return name.substring(0, name.lastIndexOf("."));
    }

    /**
     * Returns the base for the given thumbnail file without the suffix or
     * extension.
     *
     * @param name
     *
     * @return
     */
    private String getThumbnailBase(String name) {

        if(name.lastIndexOf(THUMBNAIL_SUFFIX + ".") < 0) {
            return "";
        }

        return name.substring(0, name.lastIndexOf(THUMBNAIL_SUFFIX + "."));
    }

    /**
     * Gets the titles from the portfolio's titles directory that correspond
     * with the pictures found on the given file paths and returns their
     * content.
     *
     * @param pictures
     *
     * @return
     */
    private String[] getPortfolioTitles(String[] pictures) {

        File directory = new File(servletContext.getRealPath("/") +
                TITLE_HOME);

        if(!directory.exists() || !directory.isDirectory()) {

            String[] titles = new String[pictures.length];

            for(int i = 1; i <= pictures.length; ++i) {
                titles[i-1] = "Image " + i;
            }

            return titles;
        }

        File[] files = directory.listFiles();
        String[] titles = new String[pictures.length];

        for(int i = 1; i <= pictures.length; ++i) {

            titles[i-1] = "Image " + i;

            for(File file : files) {

                String pictureBase = getPictureBase(pictures[i-1]);
                String fileBase = getTitleBase(file.name);

                if(pictureBase.equals(fileBase)) {

                    String title = "";
                    Scanner scanner = null;

                    try {
                        scanner = new Scanner(file);

                        while(scanner.hasNextLine()) {
                            title += "<p>" + scanner.nextLine() + "</p>\n";
                        }
                    }

                    catch(IOException e) {
                        e.printStackTrace();
                    }

                    finally {
                        if(scanner != null) {
                            scanner.close();
                        }
                    }

                    titles[i-1] = title;
                }
            }
        }

        return titles;
    }

    /**
     * Returns the base for the given title file without the suffix or
     * extension.
     *
     * @param name
     *
     * @return
     */
    private String getTitleBase(String name) {

        if(name.lastIndexOf(TITLE_SUFFIX + ".txt") < 0) {
            return "";
        }

        return name.substring(0, name.lastIndexOf(TITLE_SUFFIX + ".txt"));
    }

    /**
     * Gets the captions from the portfolio's captions directory that correspond
     * with the pictures found on the given file paths and returns their
     * content.
     *
     * @param pictures
     *
     * @return
     */
    private String[] getPortfolioCaptions(String[] pictures) {

        File directory = new File(servletContext.getRealPath("/") +
                CAPTION_HOME);

        if(!directory.exists() || !directory.isDirectory()) {

            String[] captions = new String[pictures.length];

            for(int i = 1; i <= pictures.length; ++i) {
                captions[i-1] = "Caption for Image " + i + ".";
            }

            return captions;
        }

        File[] files = directory.listFiles();
        String[] captions = new String[pictures.length];

        for(int i = 1; i <= pictures.length; ++i) {

            captions[i-1] = "Caption for Image " + i + ".";

            for(File file : files) {

                String pictureBase = getPictureBase(pictures[i-1]);
                String fileBase = getCaptionBase(file.name);

                if(pictureBase.equals(fileBase)) {

                    String caption = "";
                    Scanner scanner = null;

                    try {
                        scanner = new Scanner(file);

                        while(scanner.hasNextLine()) {
                            caption += "<p>" + scanner.nextLine() + "</p>\n";
                        }
                    }

                    catch(IOException e) {
                        e.printStackTrace();
                    }

                    finally {
                        if(scanner != null) {
                            scanner.close();
                        }
                    }

                    captions[i-1] = caption;
                }
            }
        }

        return captions;
    }

    /**
     * Returns the base for the given caption file without the suffix or
     * extension.
     *
     * @param name
     *
     * @return
     */
    private String getCaptionBase(String name) {

        if(name.lastIndexOf(CAPTION_SUFFIX + ".txt") < 0) {
            return "";
        }

        return name.substring(0, name.lastIndexOf(CAPTION_SUFFIX + ".txt"));
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

        String text = "Coming soon!";

        File file = new File(servletContext.getRealPath("/") + ABOUT_FILE);

        if(file.exists()) {

            Scanner scanner = null;

            try {

                scanner = new Scanner(file);
                text = "";

                while(scanner.hasNextLine()) {
                    text += "<p>" + scanner.nextLine() + "</p>\n";
                }
            }

            catch(IOException e) {
                e.printStackTrace();
            }

            finally {
                if(scanner != null) {
                    scanner.close();
                }
            }
        }

        model.addAttribute("aboutText", text);
        model.addAttribute("resumeLink", RESUME_FILE);

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

        String text = "Coming soon!";

        File file = new File(servletContext.getRealPath("/") + CONTACT_FILE);

        if(file.exists()) {

            Scanner scanner = null;

            try {

                scanner = new Scanner(file);
                text = "";

                while(scanner.hasNextLine()) {
                    text += "<p>" + scanner.nextLine() + "</p>\n";
                }
            }

            catch(IOException e) {
                e.printStackTrace();
            }

            finally {
                if(scanner != null) {
                    scanner.close();
                }
            }
        }

        model.addAttribute("contactInfo", text);
        model.addAttribute("emailAddress", "rebecca@rebeccafinelli.com");

        // The name of the JSP.
        return "Contact";
    }
}

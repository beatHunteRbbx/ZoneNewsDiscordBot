import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class NewsGenerator {


    private String name;
    private String surname;
    private String group;

    public NewsGenerator() {

    }

    public String generateNews() {
        String strNews = null;
        StringBuilder newsBuilder = new StringBuilder();
        StringBuilder nameBuilder = generateName();

        newsBuilder.append(nameBuilder);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readValue(new File("./input/dynamic_news.json"), JsonNode.class);

            //начинаем составлять новость
            //определяем тип новости:
            //  1 - о помощи,
            //  2 - специальная новость,
            //  3 - о выбросе
            //  4 - новости фракций
            //  5 - новости о нахождении артефактов
            //  6 - немедленные сообщения от сталкеров увидивших/услышивших что-то
            int newsNumb = 6;

            if (group.equals("Зомбированные")) newsNumb = 2;

            switch (newsNumb) {
                case 1:
//                    System.out.println("HELP_NEWS");
                    newsBuilder.append(genHelpNews(node));
                    break;
                case 2:
//                    System.out.println("SPECIAL_NEWS");
                    newsBuilder.append(genSpecialNews(node));
                    break;
                case 3:
//                    System.out.println("SURGE_NEWS");
                    newsBuilder.append(genSurgeNews(node));
                    break;
                case 4:
//                    System.out.println("FACTION_NEWS");
                    newsBuilder.append(genFactionNews(node));
                    break;
                case 5:
//                    System.out.println("ARTEFACT_NEWS");
                    newsBuilder.append(genArtefactNews(node));
                    break;
                case 6:
//                    System.out.println("INSTANT_NEWS");
                    newsBuilder.append(getInstantNews(node));
            }

            strNews = replaceTemplates(newsBuilder, node);

//            System.out.println(newsNumb + " " + newsBuilder);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return strNews;
    }


    private StringBuilder genHelpNews(JsonNode node) {
        StringBuilder newsBuilder = new StringBuilder();
        int newsNumb = getRndIntInRange(1,2); //определяем зовут ли на помощь: 1 - зовут, 2 - не зовут
        if (newsNumb == 1) {
            switch (group) {
                case "Одиночки":
                    newsBuilder.append(node.findValue("SOS").findValue("loner").findValues("text")
                            .get(getRndIntInRange(0, 3)).asText()).append(" ");
                    break;
                case "Монолит":
                    newsBuilder.append(node.findValue("SOS").findValue("monolith").findValues("text")
                            .get(getRndIntInRange(0, 3)).asText()).append(" ");
                    break;
                case "Бандиты":
                    newsBuilder.append(node.findValue("SOS").findValue("bandit").findValues("text")
                            .get(getRndIntInRange(0, 3)).asText()).append(" ");
                    break;
                case "Ренегаты":
                    newsBuilder.append(node.findValue("SOS").findValue("bandit").findValues("text")
                            .get(getRndIntInRange(0, 3)).asText()).append(" ");
                    break;
                case "Долг":
                    newsBuilder.append(node.findValue("SOS").findValue("dolg").findValues("text")
                            .get(getRndIntInRange(0, 3)).asText()).append(" ");
                    break;
                case "Учёные":
                    newsBuilder.append(node.findValue("SOS").findValue("ecolog").findValues("text")
                            .get(getRndIntInRange(0, 3)).asText()).append(" ");
                    break;
                case "Чистое Небо":
                    newsBuilder.append(node.findValue("SOS").findValue("clear_sky").findValues("text")
                            .get(getRndIntInRange(0, 3)).asText()).append(" ");
                    break;
                case "Военные":
                    newsBuilder.append(node.findValue("SOS").findValue("army").findValues("text")
                            .get(getRndIntInRange(0, 3)).asText()).append(" ");
                    break;
                case "Наёмники":
                    newsBuilder.append(node.findValue("SOS").findValue("mercenary").findValues("text")
                            .get(getRndIntInRange(0, 3)).asText()).append(" ");
                    break;
                case "Свобода":
                    newsBuilder.append(node.findValue("SOS").findValue("freedom").findValues("text")
                            .get(getRndIntInRange(0, 3)).asText()).append(" ");
                    break;
            }
        }
        newsBuilder.append(node.findValue("mutants").findValues("text").get(getRndIntInRange(0, 105)).asText());
        newsBuilder.append(". ");
        newsBuilder.append(generateLocation(node).toString());

        return newsBuilder;
    }

    private StringBuilder genSpecialNews(JsonNode node) {
        StringBuilder newsBuilder = new StringBuilder();
        if (group.equals("Зомбированные")) {
            newsBuilder.append(node.findValue("zombie_news").
                    findValue("dumb_zombies").
                    findValues("text").get(getRndIntInRange(0, 5)).asText()).
                    append(" ");
        }
        else {
            newsBuilder.append(node.findValue("special_news").findValues("text").get(getRndIntInRange(0, 175)).asText()).
                    append(" ");
        }
        return newsBuilder;
    }

    private StringBuilder genSurgeNews(JsonNode node) {
        StringBuilder newsBuilder = new StringBuilder();
        int newsNumb = getRndIntInRange(1, 2); //1 - случайная новость, 2 - составление новости фракции из кусков
        if (newsNumb == 1) {
            newsBuilder.append(node.findValue("surge_template").findValues("text")
                    .get(getRndIntInRange(0, 13)).asText()).append(" ");
        }
        else {
            switch (group) {
                case "Одиночки":
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("loner")
                            .findValue("start").findValues("text").get(getRndIntInRange(0, 6))
                            .asText()).append(" ");
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("loner")
                            .findValue("mid").findValues("text").get(getRndIntInRange(0, 5))
                            .asText()).append(". ");
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("loner")
                            .findValue("end").findValues("text").get(getRndIntInRange(0, 3))
                            .asText()).append(" ");
                    break;
                case "Монолит":
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("monolith")
                            .findValue("start").findValues("text").get(getRndIntInRange(0, 3))
                            .asText()).append(" ");
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("loner")
                            .findValue("mid").findValues("text").get(getRndIntInRange(0, 5))
                            .asText()).append(". ");
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("loner")
                            .findValue("end").findValues("text").get(getRndIntInRange(0, 3))
                            .asText()).append(" ");
                    break;
                case "Бандиты":
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("bandit")
                            .findValue("start").findValues("text").get(getRndIntInRange(0, 6))
                            .asText()).append(" ");
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("bandit")
                            .findValue("mid").findValues("text").get(getRndIntInRange(0, 5))
                            .asText()).append(". ");
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("bandit")
                            .findValue("end").findValues("text").get(getRndIntInRange(0, 4))
                            .asText()).append(" ");
                    break;
                case "Ренегаты":
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("bandit")
                            .findValue("start").findValues("text").get(getRndIntInRange(0, 6))
                            .asText()).append(" ");
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("bandit")
                            .findValue("mid").findValues("text").get(getRndIntInRange(0, 5))
                            .asText()).append(". ");
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("bandit")
                            .findValue("end").findValues("text").get(getRndIntInRange(0, 4))
                            .asText()).append(" ");
                    break;
                case "Долг":
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("dolg")
                            .findValue("start").findValues("text").get(getRndIntInRange(0, 6))
                            .asText()).append(" ");
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("dolg")
                            .findValue("mid").findValues("text").get(getRndIntInRange(0, 5))
                            .asText()).append(". ");
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("dolg")
                            .findValue("end").findValues("text").get(getRndIntInRange(0, 3))
                            .asText()).append(" ");
                    break;
                case "Учёные":
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("scientist")
                            .findValue("start").findValues("text").get(getRndIntInRange(0, 6))
                            .asText()).append(" ");
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("scientist")
                            .findValue("mid").findValues("text").get(getRndIntInRange(0, 5))
                            .asText()).append(". ");
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("scientist")
                            .findValue("end").findValues("text").get(getRndIntInRange(0, 3))
                            .asText()).append(" ");
                    break;
                case "Чистое Небо":
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("clear_sky")
                            .findValue("start").findValues("text").get(getRndIntInRange(0, 6))
                            .asText()).append(" ");
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("clear_sky")
                            .findValue("mid").findValues("text").get(getRndIntInRange(0, 5))
                            .asText()).append(". ");
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("clear_sky")
                            .findValue("end").findValues("text").get(getRndIntInRange(0, 3))
                            .asText()).append(" ");
                    break;
                case "Военные":
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("army")
                            .findValue("start").findValues("text").get(getRndIntInRange(0, 6))
                            .asText()).append(" ");
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("army")
                            .findValue("mid").findValues("text").get(getRndIntInRange(0, 5))
                            .asText()).append(". ");
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("army")
                            .findValue("end").findValues("text").get(getRndIntInRange(0, 3))
                            .asText()).append(" ");
                    break;
                case "Наёмники":
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("mercenary")
                            .findValue("start").findValues("text").get(getRndIntInRange(0, 6))
                            .asText()).append(" ");
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("mercenary")
                            .findValue("mid").findValues("text").get(getRndIntInRange(0, 5))
                            .asText()).append(". ");
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("mercenary")
                            .findValue("end").findValues("text").get(getRndIntInRange(0, 3))
                            .asText()).append(" ");
                    break;
                case "Свобода":
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("freedom")
                            .findValue("start").findValues("text").get(getRndIntInRange(0, 6))
                            .asText()).append(" ");
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("freedom")
                            .findValue("mid").findValues("text").get(getRndIntInRange(0, 5))
                            .asText()).append(". ");
                    newsBuilder.append(node.findValue("surge_builder_by_faction").findValue("freedom")
                            .findValue("end").findValues("text").get(getRndIntInRange(0, 3))
                            .asText()).append(" ");
                    break;
            }
        }
        return newsBuilder;
    }

    private StringBuilder genFactionNews(JsonNode node) {
        StringBuilder newsBuilder = new StringBuilder();

        switch (group) {
            case "Одиночки":
                newsBuilder.append(node.findValue("faction_news").findValue("clear_sky").findValues("text")
                        .get(getRndIntInRange(0, 11)).asText()).append(" ");
                break;
            case "Монолит":
                newsBuilder.append(node.findValue("faction_news").findValue("monolith").findValues("text")
                        .get(getRndIntInRange(0, 23)).asText()).append(" ");
                break;
            case "Бандиты":
                newsBuilder.append(node.findValue("faction_news").findValue("bandit").findValues("text")
                        .get(getRndIntInRange(0, 16)).asText()).append(" ");
                break;
            case "Ренегаты":
                newsBuilder.append(node.findValue("faction_news").findValue("bandit").findValues("text")
                        .get(getRndIntInRange(0, 16)).asText()).append(" ");
                break;
            case "Долг":
                newsBuilder.append(node.findValue("faction_news").findValue("dolg").findValues("text")
                        .get(getRndIntInRange(0, 28)).asText()).append(" ");
                break;
            case "Учёные":
                newsBuilder.append(node.findValue("faction_news").findValue("scientist").findValues("text")
                        .get(getRndIntInRange(0, 10)).asText()).append(" ");
                break;
            case "Чистое Небо":
                newsBuilder.append(node.findValue("faction_news").findValue("clear_sky").findValues("text")
                        .get(getRndIntInRange(0, 11)).asText()).append(" ");
                break;
            case "Военные":
                newsBuilder.append(node.findValue("faction_news").findValue("army").findValues("text")
                        .get(getRndIntInRange(0, 16)).asText()).append(" ");
                break;
            case "Наёмники":
                newsBuilder.append(node.findValue("faction_news").findValue("mercenary").findValues("text")
                        .get(getRndIntInRange(0, 17)).asText()).append(" ");
                break;
            case "Свобода":
                newsBuilder.append(node.findValue("faction_news").findValue("freedom").findValues("text")
                        .get(getRndIntInRange(0, 31)).asText()).append(" ");
                break;
        }
        return newsBuilder;
    }

    private StringBuilder genArtefactNews(JsonNode node) {
        StringBuilder newsBuilder = new StringBuilder();

        newsBuilder.append(node.findValue("found_artefacts").findValues("text")
                .get(getRndIntInRange(0, 3)).asText()).append(" ");

        return newsBuilder;
    }

    private StringBuilder getInstantNews(JsonNode node) {
        StringBuilder newsBuilder = new StringBuilder();

        int newsNumb = getRndIntInRange(1, 3);
        switch (newsNumb) {
            case 1: //новость об атаке кем-то
                newsBuilder.append(node.findValue("instant_news").findValue("start").findValue("attacked")
                                        .findValues("text").get(getRndIntInRange(0, 3)).asText()).append(" ")
                            .append(node.findValue("utilities").findValue("who_mutant")
                                        .findValues("text").get(getRndIntInRange(0, 42)).asText()).append("!");
                newsBuilder.append(" ");
                newsBuilder.append(generateLocation(node).toString());
                break;
            case 2: //новость о том, что что-то услышали
                newsBuilder.append(node.findValue("instant_news").findValue("start").findValue("hear")
                                        .findValues("text").get(getRndIntInRange(0, 8)).asText())
                            .append(" ").append(node.findValue("instant_news").findValue("hear_mid")
                                                    .findValues("text").get(getRndIntInRange(0, 13)).asText());
                newsBuilder.append(". ");
                newsBuilder.append(generateLocation(node).toString());
                newsBuilder.append(node.findValue("instant_news").findValue("end")
                                                    .findValues("text").get(getRndIntInRange(0, 14)).asText());
                break;
            case 3: //новость о том, что что-то увидели
                newsBuilder.append(node.findValue("instant_news").findValue("start").findValue("see")
                                        .findValues("text").get(getRndIntInRange(0, 8)).asText()).append(" ");
                switch (getRndIntInRange(1, 2)) { //выбираем один человек убил или несколько
                    case 1:
                        newsBuilder.append(node.findValue("who_human").findValue("who").findValue("single")
                                    .findValues("text").get(getRndIntInRange(0, 19)).asText()).append(" ")
                                    .append(node.findValue("how_killed").findValue("single")
                                                .findValues("text").get(getRndIntInRange(0, 9)).asText()).append(" ");
                        break;
                    case 2:
                        newsBuilder.append(node.findValue("who_human").findValue("who").findValue("multi")
                                .findValues("text").get(getRndIntInRange(0, 17)).asText()).append(" ")
                                .append(node.findValue("how_killed").findValue("multi")
                                        .findValues("text").get(getRndIntInRange(0, 9)).asText()).append(" ");
                        break;
                }
                switch (getRndIntInRange(1, 2)) { //выбираем убили одного человека или группу
                    case 1:
                        newsBuilder.append(node.findValue("who_human").findValue("whom").findValue("single")
                                    .findValues("text").get(getRndIntInRange(0, 17)).asText());
                        break;
                    case 2:
                        newsBuilder.append(node.findValue("who_human").findValue("whom").findValue("multi")
                                                .findValue("group_type").findValues("text")
                                                .get(getRndIntInRange(0, 2)).asText()).append(" ")
                                    .append(node.findValue("who_human").findValue("whom").findValue("multi")
                                                .findValue("who").findValues("text")
                                                .get(getRndIntInRange(0, 15)).asText());

                }
                newsBuilder.append(". ");
                newsBuilder.append(generateLocation(node).toString());
                break;
        }


        return newsBuilder;
    }

    private StringBuilder generateName() {
        StringBuilder nameBuilder = new StringBuilder();

        group = Resources.getGroupsList().get(getRndIntInRange(0, Resources.getGroupsList().size() - 1));

        if (group.equals("Военные") || group.equals("Долг")) {
            name = Resources.getMilitaryRanks().get(getRndIntInRange(0, Resources.getMilitaryRanks().size() - 1));
            surname = Resources.getMilitarySurnames().get(getRndIntInRange(0, Resources.getMilitarySurnames().size() - 1));
        }
        else if (group.equals("Учёные")) {
            name = Resources.getScientistNamesList().get(getRndIntInRange(0, Resources.getScientistNamesList().size() - 1));
            surname = Resources.getMilitarySurnames().get(getRndIntInRange(0, Resources.getMilitarySurnames().size() - 1));
        }
        else {
            name = Resources.getStalkerNamesList().get(getRndIntInRange(0, Resources.getStalkerNamesList().size() - 1));
            surname = Resources.getStalkerSurnamesList().get(getRndIntInRange(0, Resources.getStalkerSurnamesList().size() - 1));
        }


        nameBuilder.append(name).append(" ");
        nameBuilder.append(surname).append(" ");
        nameBuilder.append("(").append(group).append(")");
        nameBuilder.append(":").append("\n");

        return nameBuilder;
    }

    private StringBuilder generateLocation(JsonNode node) {
        StringBuilder locationBuilder = new StringBuilder();
        int rndNumb = getRndIntInRange(0, 32);
        JsonNode locNode  = node.findValue("level_description").findValues("location").get(rndNumb);
        List<JsonNode> blizkoPlaces = locNode.findValue("blizko").findValues("text");
        List<JsonNode> dalekoPlaces = locNode.findValue("daleko").findValues("text");
        String place = null;
        if (blizkoPlaces.size() != 0 || dalekoPlaces.size() != 0) {
            switch (getRndIntInRange(1, 2)) {
                case 1:
                    place = locNode.findValue("blizko").findValues("text")
                            .get(getRndIntInRange(0, blizkoPlaces.size() - 1)).asText();
                    break;
                case 2:
                    place = node.findValue("utilities").findValue("direction")
                            .findValues("text").get(getRndIntInRange(0, 9)).asText() + " " +
                            locNode.findValue("daleko").findValues("text")
                                    .get(getRndIntInRange(0, dalekoPlaces.size() - 1)).asText();
                    break;
            }
            locationBuilder.append(locNode.findValue("loc_name").asText()).append(", ").append(place).append(". ");
        }
        else {
            locationBuilder.append(locNode.findValue("loc_name").asText()).append(". ");
        }

        return locationBuilder;
    }

    private String replaceTemplates(StringBuilder newsBuilder, JsonNode node) {
        String strNews = newsBuilder.toString();

        if (newsBuilder.toString().contains("$who")) {
            String replacement = node.findValue("who_mutant").findValues("text").get(getRndIntInRange(0, 42)).asText();
            strNews = newsBuilder.toString().replaceAll("\\$who", replacement);
        }

        if (newsBuilder.toString().contains("$surge") && newsBuilder.toString().contains("$when")) {
            String replacement = node.findValue("surge_type").findValues("text").get(getRndIntInRange(0, 2)).asText();
            strNews = newsBuilder.toString().replaceAll("\\$surge", replacement);
            replacement = node.findValue("utilities").findValue("time_phase").findValues("text").get(getRndIntInRange(0, 2)).asText();
            strNews = strNews.replaceAll("\\$when", replacement);
        }
        else if (newsBuilder.toString().contains("$when")) {
            String replacement = node.findValue("utilities").findValue("time_phase").findValues("text").get(getRndIntInRange(0, 2)).asText();
            strNews = newsBuilder.toString().replaceAll("\\$when", replacement);
        }

        if (newsBuilder.toString().contains("$artefact")) {
            List<String> artefactsList = Resources.getArtefactsList();
            String replacement = artefactsList.get(getRndIntInRange(0, artefactsList.size() - 1));
            strNews = newsBuilder.toString().replaceAll("\\$artefact", replacement);
        }

        return strNews;
    }

    public String generateResponse(int responseType, MessageReceivedEvent event) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Main.mapOfThreads.get(event.getChannel().getName()).interrupt();
        }

        StringBuilder responseBuilder = new StringBuilder();
        StringBuilder nameBuilder = generateName();
        if (group.equals("Зомбированные")) {
            while (group.equals("Зомбированные")) {
                nameBuilder = generateName();
            }
        }
        responseBuilder.append(nameBuilder);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readValue(new File("./input/dynamic_news.json"), JsonNode.class);

            if (responseType == 1) {
                responseBuilder.append(node.findValue("response_dumb_zombies").
                                findValues("text").get(getRndIntInRange(0, 6)).asText()).
                                append(" ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseBuilder.toString();
    }


    private int getRndIntInRange(int min, int max){
        return (int) (Math.random()*((max-min)+1))+min;
    }

}

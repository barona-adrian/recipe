package com.deliciousapp.onetime.drivers;

import com.deliciousapp.elasticsearch.model.findbyingredient.output.Ingredient;
import com.deliciousapp.onetime.findByID.FindRecipeByID;
import com.deliciousapp.onetime.findByID.model.input.AnalyzedInstructionsRequest;
import com.deliciousapp.onetime.findByID.model.output.AnalyzedInstructionsResponse;
import com.deliciousapp.onetime.findbyingredients.model.output.FindByIngredientsResponse;
import com.deliciousapp.onetime.findbyingredients.FindByIngredients;
import com.deliciousapp.onetime.findbyingredients.model.input.FindByIngredientsRequest;

import com.deliciousapp.elasticsearch.model.findbyingredient.output.FindByElasticIngredientsResponse;

import io.searchbox.client.JestClient;

import java.util.*;

import com.deliciousapp.onetime.elasticupdate.ElasticUpdate;
import org.json.JSONException;


public class OneTimeLoad {

    static ArrayList<String> ids;
    static Set<String> uniqueIds;

    final static private String[] food = {
            "sweet potato",
            "mustard greens",
            "blueberries",
            "mizuna greens",
            "broccolini",
            "mussel",
            "mackerel",
            "pomegranate",
            "molluscs",
            "lamb's lettuce",
            "beet",
            "sierra leone bologi",
            "john dory",
            "hake",
            "shad roe",
            "marrow",
            "rapini",
            "lumpfish roe",
            "huckleberries",
            "feijoa",
            "key limes",
            "breadfruit",
            "black eyed peas",
            "wasabi",
            "radish",
            "peaches",
            "tomato",
            "cuttlefish",
            "parsley",
            "daikon",
            "oranges ",
            "fiddlehead",
            "kazunoko",
            "parsnip",
            "nectarines",
            "onions",
            "sea bass",
            "passion fruit",
            "collard greens",
            "olallieberries",
            "sturgeon",
            "amaranth",
            "blowfish",
            "kuka",
            "asian pear",
            "anchovy",
            "fennel",
            "shallot",
            "ribs,cheese",
            "vidalia onions",
            "champagne grapes",
            "cantaloupes",
            "tilefish",
            "endive",
            "celeriac",
            "dandelion",
            "carrot,grape",
            "radishes",
            "greater plantain",
            "bream",
            "lavender",
            "chili pepper",
            "dogfish",
            "anise",
            "pollock",
            "sweetcorn",
            "rosemary",
            "watermelons",
            "marjoram",
            "rhubarb ",
            "persimmon",
            "bitter melon",
            "summer squash",
            "figs",
            "lizard's tail",
            "brill",
            "masago",
            "sunchokes",
            "zucchini",
            "cactus pear",
            "ground beef",
            "pear",
            "pinto beans",
            "peas",
            "ramps",
            "pineapple ",
            "boysenberries",
            "tobiko",
            "whiting",
            "sugar snap peas",
            "sole",
            "soy beans",
            "yukon gold potatoes",
            "red banana",
            "flounder",
            "morel mushrooms",
            "onion,green pepper,pepper,onion powder",
            "dill",
            "avocados",
            "lima beans",
            "buttercup squash",
            "brussels sprouts",
            "soko",
            "cape gooseberries",
            "runner beans",
            "ikura",
            "chinese long beans",
            "pilchard",
            "turnips",
            "kiwano",
            "topinambur",
            "butter fish",
            "black-eyed peas",
            "kai-lan",
            "cilantro",
            "chaya",
            "pea",
            "fiddlehead ferns",
            "dorade",
            "shad",
            "butternut squash",
            "sea beet",
            "cranberries",
            "flour",
            "sablefish",
            "limes",
            "jujube",
            "loco",
            "beef,cinnamon,cheese",
            "mangel-wurzel",
            "tabasco pepper",
            "parsnips",
            "paracress",
            "burdock root",
            "sugar apple",
            "pike",
            "chamomile",
            "longan",
            "radicchio",
            "casaba melon",
            "okra",
            "chayote squash ",
            "kidney beans",
            "crayfish",
            "pearl onions",
            "potato",
            "swiss chard",
            "jalapeno peppers",
            "mung beans",
            "taro",
            "starfruit",
            "stridolo",
            "mahi mahi",
            "shark",
            "mushrooms",
            "jalapeño",
            "mulberries",
            "kingfish",
            "mallow",
            "cockle",
            "cod",
            "kale",
            "zucchini,onion",
            "jerusalem artichoke",
            "ilish",
            "spinach",
            "chicken,cheese",
            "fiddleheads",
            "turnip greens",
            "sardine",
            "artichoke",
            "butter lettuce",
            "chinese mallow",
            "jackfruit",
            "crenshaw melon",
            "navy beans",
            "red currants",
            "chickpeas",
            "scallion ",
            "sweet dumpling squash",
            "surimi",
            "strawberries",
            "pomfret",
            "nettles",
            "land cress",
            "salsify",
            "avocado",
            "swordfish",
            "basil",
            "suiter-fish",
            "sculpit",
            "red leaf lettuce",
            "broccoflower",
            "pea pods",
            "ginger",
            "salad savoy",
            "snapper",
            "broccoli,peppers,",
            "cantaloupe",
            "caraway",
            "chicken",
            "roe",
            "monkfish",
            "sorrel",
            "green beans",
            "gem squash",
            "bell pepper",
            "shepherd's purse",
            "tat soi",
            "sapadilla",
            "garlic",
            "caviar ",
            "artichokes ",
            "peppers",
            "banana squash",
            "oregano",
            "black currants",
            "asparagus ",
            "loganberries",
            "lemons",
            "beetroot",
            "lentils",
            "mango",
            "beet greens",
            "winter squash",
            "lychee",
            "pineapple",
            "garden rocket",
            "wasabi root",
            "pumpkin",
            "jujubes",
            "durian",
            "plantain,tomato,chicken",
            "crustaceans",
            "octopus",
            "water spinach",
            "kumquat",
            "raspberries",
            "catfish",
            "grouper",
            "pears",
            "tangelo",
            "apricots",
            "chicory",
            "cherries, sour",
            "sprat",
            "horseradish",
            "yucca root",
            "honeydew",
            "orange roughy",
            "snakehead",
            "sapote",
            "parrotfish",
            "hubbard squash",
            "fat hen",
            "bananas",
            "thyme",
            "tangerines",
            "habanero",
            "celery",
            "leeks",
            "cactus",
            "chives",
            "carrots",
            "crab apples",
            "eggs,peppers,cauliflower,garlic",
            "watermelon",
            "rib,cheese,peppers,onion,steak",
            "yam",
            "clementines",
            "celtuce",
            "split peas",
            "white radish",
            "corn salad",
            "chard",
            "ogden melons",
            "paprika",
            "komatsuna",
            "horned melon",
            "olives",
            "fish",
            "barbados cherries",
            "crookneck squash",
            "papaya",
            "black cod",
            "trout",
            "arrowroot",
            "kohlrabi",
            "turbot",
            "frisee",
            "muscadine grapes",
            "chickweed",
            "smelt",
            "chrysanthemum",
            "elderberries",
            "lagos bologi",
            "grapefruit",
            "herring",
            "pompano",
            "pak choy",
            "melokhia",
            "kiwifruit",
            "voavanga",
            "lobster",
            "fava beans",
            "coconut",
            "yellow watermelon",
            "persimmons",
            "rhubarb",
            "diakon radish",
            "napa cabbage",
            "ugli fruit",
            "pummelo",
            "bok choy",
            "grapes",
            "tamarind",
            "potatoes",
            "sweet potatoes",
            "broccoli",
            "cherimoya",
            "rose apple",
            "pickles,pickle",
            "currants",
            "squashes",
            "lamb's quarters",
            "bombay duck",
            "lychees",
            "watercress",
            "orache",
            "basa",
            "jicama",
            "lemon grass",
            "acorn squash",
            "bass",
            "patty pans",
            "cucumbers",
            "sea kale",
            "turnip",
            "guava",
            "tilapia",
            "plums",
            "cabbage",
            "bread fruit",
            "common purslane",
            "delicata",
            "legumes",
            "bluefish",
            "papayas",
            "spaghetti squash",
            "alfalfa",
            "scallop",
            "beets",
            "lamprey",
            "eggplant",
            "cress",
            "belgian endive",
            "quince",
            "rice,beans,chicken",
            "arugula",
            "fish,garclic,onion",
            "sapodillas",
            "dates",
            "lettuce",
            "yao choy",
            "golden samphire",
            "black radish",
            "quandong",
            "spring baby lettuce",
            "yarrow",
            "galangal root",
            "snow peas",
            "qquash",
            "broccoli,cheese,ham",
            "chocolate-fruit",
            "honeydew melons",
            "lingcod",
            "delicata squash",
            "tomatoes",
            "tubers",
            "periwinkle",
            "coriander",
            "ita palm",
            "samphire",
            "kumquats",
            "winged beans",
            "black salsify",
            "courgette",
            "whitefish",
            "persian melon",
            "fluted pumpkin",
            "ackee",
            "cayenne pepper",
            "mullet",
            "xigua melon",
            "good king henry",
            "mangos",
            "skirret",
            "haddock",
            "rutabagas",
            "wahoo",
            "onion",
            "cucumber",
            "corn",
            "cherries",
            "blackberries",
            "muskmelon",
            "mustard",
            "black beans",
            "manoa lettuce",
            "sage",
            "cherry tomatoes",
            "chinese eggplants",
            "borlotti bean",
            "salmon",
            "bilberries",
            "borage greens",
            "grape leaves",
            "poke",
            "tomatillo",
            "sanddab",
            "rice,beans,meat,steak",
            "belgian endive ",
            "sour cabbage",
            "garlic,flour",
            "halibut",
            "squid",
            "prickly pear",
            "skate",
            "apples",
            "rice,lentils,plantains,pepper,onion",
            "rutabaga",
            "mangosteen",
            "cauliflower",
            "tuna",
            "pluot",
            "shrimp",
            "chicken,steak,black pepper,eggs",
            "loquat",
            "green soybeans",
            "cai",
            "miner's lettuce",
            "eel",
            "chayote squash",
            "kiwis",
            "shallots",
            "sharon fruit",
            "new zealand spinach",
            "escargot",
            "bell peppers",
            "purple asparagus",
            "rambuton",
            "malabar spinach",
            "oranges",
            "patagonian toothfish",
            "summer purslane",
            "prawn",
            "leek",
            "water chestnut",
            "oyster",
            "shellfish",
            "root vegetables",
            "meat",
            "broad beans",
            "mangetout",
            "tatsoi",
            "wheatgrass",
            "gooseberries",
            "catsear",
            "carrot"
    };

    final static private String[] drink = {};

//    public static void main(String[] args) throws Exception {
//
//        ElasticUpdate load = new ElasticUpdate();
//        updateElastic(food, load, "Food");
////        updateElastic(drink, load, "Drink");
//    }

    public static void updateElastic(String[] ings, ElasticUpdate load, String type) throws Exception {
        ids = new ArrayList<>();
        for(int i = 0 ; i < ings.length ; i++){
            updateElasticIngredients(ings[i], load);
        }
        uniqueIds = new HashSet<>(ids);
        System.out.println("Count of all " + type + " ID's retrieved from searches     : " + ids.size());
        System.out.println("Unique count of all " + type +  " ID's updated into Elastic : " + uniqueIds.size());
    }

    private static void updateElasticIngredients(String food, ElasticUpdate load) throws Exception {
        FindByIngredients findByIngredients = new FindByIngredients();
        FindByIngredientsRequest find = new FindByIngredientsRequest();
        List<String> foods = Arrays.asList(food.split(","));
        find.setIngredients(foods);
        find.setNumber("100");
        find.setRanking("1");
        List<FindByIngredientsResponse> responses = findByIngredients.findByIngredients(find);
        System.out.println("'" + food + "' generated " + responses.size() + " responses.");

        for(FindByIngredientsResponse response : responses){
            FindRecipeByID recipe = new FindRecipeByID();
            AnalyzedInstructionsRequest req = new AnalyzedInstructionsRequest();
            req.setRecipeID(response.getId());

            AnalyzedInstructionsResponse instructionsResponse = null;
            try {
                instructionsResponse = recipe.analyzedInstructions(req);
                load.updateElasticRecipes(instructionsResponse, req.getRecipeID());
            } catch (NullPointerException ex){
                ex.printStackTrace();
                System.out.println(req.getRecipeID() + " id doesn't have a recipe :'(");
            } catch (JSONException ex){
                ex.printStackTrace();
                System.out.println(req.getRecipeID() + " id doesn't have a recipe :'(");
            }

            if(instructionsResponse != null) {
                String id = response.getId();
                String title = response.getTitle();
                String image = response.getImage();

                List<Ingredient> ingredients = new ArrayList<>();
                for (com.deliciousapp.onetime.findbyingredients.model.output.Ingredient ing : response.getMissedIngredients()) {
                    Ingredient ingredient = new Ingredient();

                    ingredient.setId(ing.getId());
                    ingredient.setUnit(ing.getUnit());
                    ingredient.setUnitLong(ing.getUnitLong());
                    ingredient.setUnitShort(ing.getUnitShort());
                    ingredient.setAisle(ing.getAisle());
                    ingredient.setName(ing.getName());
                    ingredient.setOriginalString(ing.getOriginalString());
                    ingredient.setMetaInformation(ing.getMetaInformation());
                    ingredient.setExtendedName(ing.getExtendedName());
                    ingredient.setImage(ing.getImage());

                    ingredients.add(ingredient);
                }

                for (com.deliciousapp.onetime.findbyingredients.model.output.Ingredient ing : response.getUsedIngredients()) {
                    Ingredient ingredient = new Ingredient();

                    ingredient.setId(ing.getId());
                    ingredient.setUnit(ing.getUnit());
                    ingredient.setUnitLong(ing.getUnitLong());
                    ingredient.setUnitShort(ing.getUnitShort());
                    ingredient.setAisle(ing.getAisle());
                    ingredient.setName(ing.getName());
                    ingredient.setOriginalString(ing.getOriginalString());
                    ingredient.setMetaInformation(ing.getMetaInformation());
                    ingredient.setExtendedName(ing.getExtendedName());
                    ingredient.setImage(ing.getImage());

                    ingredients.add(ingredient);
                }

                FindByElasticIngredientsResponse elasticIngredientsResponse = new FindByElasticIngredientsResponse();
                elasticIngredientsResponse.setId(id);
                elasticIngredientsResponse.setTitle(title);
                elasticIngredientsResponse.setImage(image);
                elasticIngredientsResponse.setIngredients(ingredients);

                ids.add(id);

                load.updateElasticIngredients(elasticIngredientsResponse);
            }
        }
    }



}



















//findByIngredientsResponse.setId("5316830000000001");
//        findByIngredientsResponse.setTitle("Barbecue Chicken Quesadillas");
//        findByIngredientsResponse.setImage("https://spoonacular.com/recipeImages/Barbecue-Chicken-Quesadillas-531683.jpg");
//
//
//        List<Ingredient> ingredients = new ArrayList<>();
//        Ingredient ingredient = new Ingredient();
//
//        ingredient.setId("18364");
//        ingredient.setAmount("12");
//        ingredient.setUnit("");
//        ingredient.setUnitLong("");
//        ingredient.setUnitShort("");
//        ingredient.setAisle("Bakery/Bread;Pasta and Rice;Ethnic Foods");
//        ingredient.setName("tortillas");
//        ingredient.setOriginalString("12 tortillas");
//        ingredient.setMetaInformation(new ArrayList<>());
//        ingredient.setExtendedName("");
//        ingredient.setImage("https://spoonacular.com/cdn/ingredients_100x100/flour-tortillas.jpg");
//
//        Ingredient ingredient2 = new Ingredient();
//
//        ingredient2.setId("1041009");
//        ingredient2.setAmount("1.5");
//        ingredient2.setUnit("cups");
//        ingredient2.setUnitLong("cups");
//        ingredient2.setUnitShort("cup");
//        ingredient2.setAisle("Cheese");
//        ingredient2.setName("cheese");
//        ingredient2.setOriginalString("1½ cups cheese");
//        ingredient2.setMetaInformation(new ArrayList<>());
//        ingredient2.setExtendedName("");
//        ingredient2.setImage("https://spoonacular.com/cdn/ingredients_100x100/cheddar-cheese.jpg");
//
//        Ingredient ingredient3 = new Ingredient();
//
//        List<String> metaInfo = new ArrayList<>();
//        metaInfo.add("shredded");
//
//        ingredient3.setId("5006");
//        ingredient3.setAmount("2");
//        ingredient3.setUnit("cups");
//        ingredient3.setUnitLong("cups");
//        ingredient3.setUnitShort("cup");
//        ingredient3.setAisle("Meat");
//        ingredient3.setName("chicken");
//        ingredient3.setOriginalString("2 cups shredded barbecue chicken");
//        ingredient3.setMetaInformation(metaInfo);
//        ingredient3.setExtendedName("shredded chicken");
//        ingredient3.setImage("https://spoonacular.com/cdn/ingredients_100x100/whole-chicken.jpg");
//
//
//        ingredients.add(ingredient);
//        ingredients.add(ingredient2);
//        ingredients.add(ingredient3);
//
//
//        findByIngredientsResponse.setIngredients(ingredients);

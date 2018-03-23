package com.foodapi.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OpenPage {

    // inject via application.properties
    @Value("${welcome.message:test}")
    private String message = "Hello World";

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {

        InetAddress ip;
        String hostname;
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
            System.out.println("Your current IP address : " + ip);
            System.out.println("Your current Hostname : " + hostname);

        } catch (UnknownHostException e) {

            e.printStackTrace();
        }

        model.put("message", this.message);
        return "welcome";
    }

    public static void main(String[] args){
        String s = "[\n" +
                "  {\n" +
                "    \"id\": \"531683\",\n" +
                "    \"title\": \"Barbecue Chicken Quesadillas\",\n" +
                "    \"image\": \"https://spoonacular.com/recipeImages/Barbecue-Chicken-Quesadillas-531683.jpg\",\n" +
                "    \"imageType\": \"jpg\",\n" +
                "    \"usedIngredientCount\": \"2\",\n" +
                "    \"missedIngredientCount\": \"1\",\n" +
                "    \"missedIngredients\": [\n" +
                "      {\n" +
                "        \"id\": \"18364\",\n" +
                "        \"amount\": \"12\",\n" +
                "        \"unit\": \"\",\n" +
                "        \"unitLong\": \"\",\n" +
                "        \"unitShort\": \"\",\n" +
                "        \"aisle\": \"Bakery/Bread;Pasta and Rice;Ethnic Foods\",\n" +
                "        \"name\": \"tortillas\",\n" +
                "        \"originalString\": \"12 tortillas\",\n" +
                "        \"metaInformation\": [],\n" +
                "        \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/flour-tortillas.jpg\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"usedIngredients\": [\n" +
                "      {\n" +
                "        \"id\": \"1041009\",\n" +
                "        \"amount\": \"1.5\",\n" +
                "        \"unit\": \"cups\",\n" +
                "        \"unitLong\": \"cups\",\n" +
                "        \"unitShort\": \"cup\",\n" +
                "        \"aisle\": \"Cheese\",\n" +
                "        \"name\": \"cheese\",\n" +
                "        \"originalString\": \"1½ cups cheese\",\n" +
                "        \"metaInformation\": [],\n" +
                "        \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/cheddar-cheese.jpg\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"5006\",\n" +
                "        \"amount\": \"2\",\n" +
                "        \"unit\": \"cups\",\n" +
                "        \"unitLong\": \"cups\",\n" +
                "        \"unitShort\": \"cup\",\n" +
                "        \"aisle\": \"Meat\",\n" +
                "        \"name\": \"chicken\",\n" +
                "        \"originalString\": \"2 cups shredded barbecue chicken\",\n" +
                "        \"metaInformation\": [\n" +
                "          \"shredded\"\n" +
                "        ],\n" +
                "        \"extendedName\": \"shredded chicken\",\n" +
                "        \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/whole-chicken.jpg\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"unusedIngredients\": [],\n" +
                "    \"likes\": \"37\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"531847\",\n" +
                "    \"title\": \"Chicken Salad Quesadillas\",\n" +
                "    \"image\": \"https://spoonacular.com/recipeImages/Chicken-Salad-Quesadillas-531847.jpg\",\n" +
                "    \"imageType\": \"jpg\",\n" +
                "    \"usedIngredientCount\": \"2\",\n" +
                "    \"missedIngredientCount\": \"1\",\n" +
                "    \"missedIngredients\": [\n" +
                "      {\n" +
                "        \"id\": \"18364\",\n" +
                "        \"amount\": \"12\",\n" +
                "        \"unit\": \"\",\n" +
                "        \"unitLong\": \"\",\n" +
                "        \"unitShort\": \"\",\n" +
                "        \"aisle\": \"Bakery/Bread;Pasta and Rice;Ethnic Foods\",\n" +
                "        \"name\": \"tortillas\",\n" +
                "        \"originalString\": \"12 tortillas\",\n" +
                "        \"metaInformation\": [],\n" +
                "        \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/flour-tortillas.jpg\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"usedIngredients\": [\n" +
                "      {\n" +
                "        \"id\": \"1041009\",\n" +
                "        \"amount\": \"1.5\",\n" +
                "        \"unit\": \"cups\",\n" +
                "        \"unitLong\": \"cups\",\n" +
                "        \"unitShort\": \"cup\",\n" +
                "        \"aisle\": \"Cheese\",\n" +
                "        \"name\": \"cheese\",\n" +
                "        \"originalString\": \"1½ cups cheese\",\n" +
                "        \"metaInformation\": [],\n" +
                "        \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/cheddar-cheese.jpg\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"5006\",\n" +
                "        \"amount\": \"3\",\n" +
                "        \"unit\": \"cups\",\n" +
                "        \"unitLong\": \"cups\",\n" +
                "        \"unitShort\": \"cup\",\n" +
                "        \"aisle\": \"Meat\",\n" +
                "        \"name\": \"chicken\",\n" +
                "        \"originalString\": \"3 cups chicken salad\",\n" +
                "        \"metaInformation\": [],\n" +
                "        \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/whole-chicken.jpg\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"unusedIngredients\": [],\n" +
                "    \"likes\": \"3\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"534410\",\n" +
                "    \"title\": \"10-Minute Salsa Chicken\",\n" +
                "    \"image\": \"https://spoonacular.com/recipeImages/10-Minute-Salsa-Chicken-534410.jpg\",\n" +
                "    \"imageType\": \"jpg\",\n" +
                "    \"usedIngredientCount\": \"1\",\n" +
                "    \"missedIngredientCount\": \"2\",\n" +
                "    \"missedIngredients\": [\n" +
                "      {\n" +
                "        \"id\": \"1021009\",\n" +
                "        \"amount\": \"6\",\n" +
                "        \"unit\": \"tbsp\",\n" +
                "        \"unitLong\": \"tablespoons\",\n" +
                "        \"unitShort\": \"Tbsp\",\n" +
                "        \"aisle\": \"Cheese\",\n" +
                "        \"name\": \"extra sharp cheddar cheese\",\n" +
                "        \"originalString\": \"6 tbsp extra sharp cheddar, divided\",\n" +
                "        \"metaInformation\": [\n" +
                "          \"divided\"\n" +
                "        ],\n" +
                "        \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/cheddar-cheese.jpg\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"6164\",\n" +
                "        \"amount\": \"6\",\n" +
                "        \"unit\": \"tbsp\",\n" +
                "        \"unitLong\": \"tablespoons\",\n" +
                "        \"unitShort\": \"Tbsp\",\n" +
                "        \"aisle\": \"Pasta and Rice;Ethnic Foods\",\n" +
                "        \"name\": \"salsa\",\n" +
                "        \"originalString\": \"6 tbsp salsa, divided\",\n" +
                "        \"metaInformation\": [\n" +
                "          \"divided\"\n" +
                "        ],\n" +
                "        \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/salsa.jpg\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"usedIngredients\": [\n" +
                "      {\n" +
                "        \"id\": \"5006\",\n" +
                "        \"amount\": \"1\",\n" +
                "        \"unit\": \"lb\",\n" +
                "        \"unitLong\": \"pound\",\n" +
                "        \"unitShort\": \"lb\",\n" +
                "        \"aisle\": \"Meat\",\n" +
                "        \"name\": \"chicken\",\n" +
                "        \"originalString\": \"1 lb boneless, skinless chicken\",\n" +
                "        \"metaInformation\": [\n" +
                "          \"boneless\",\n" +
                "          \"skinless\"\n" +
                "        ],\n" +
                "        \"extendedName\": \"skinless boneless chicken\",\n" +
                "        \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/whole-chicken.jpg\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"unusedIngredients\": [\n" +
                "      {\n" +
                "        \"id\": \"1041009\",\n" +
                "        \"amount\": \"1\",\n" +
                "        \"unit\": \"serving\",\n" +
                "        \"unitLong\": \"serving\",\n" +
                "        \"unitShort\": \"serving\",\n" +
                "        \"aisle\": \"Cheese\",\n" +
                "        \"name\": \"cheese\",\n" +
                "        \"originalString\": \"cheese\",\n" +
                "        \"metaInformation\": [],\n" +
                "        \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/cheddar-cheese.jpg\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"likes\": \"489\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"537481\",\n" +
                "    \"title\": \"Grilled Chicken Alfredo\",\n" +
                "    \"image\": \"https://spoonacular.com/recipeImages/grilled-chicken-alfredo-537481.jpg\",\n" +
                "    \"imageType\": \"jpg\",\n" +
                "    \"usedIngredientCount\": \"1\",\n" +
                "    \"missedIngredientCount\": \"2\",\n" +
                "    \"missedIngredients\": [\n" +
                "      {\n" +
                "        \"id\": \"1053\",\n" +
                "        \"amount\": \"0.75\",\n" +
                "        \"unit\": \"cup\",\n" +
                "        \"unitLong\": \"cups\",\n" +
                "        \"unitShort\": \"cup\",\n" +
                "        \"aisle\": \"Milk, Eggs, Other Dairy\",\n" +
                "        \"name\": \"heavy cream\",\n" +
                "        \"originalString\": \"3/4 cup heavy cream\",\n" +
                "        \"metaInformation\": [],\n" +
                "        \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/fluid-cream.jpg\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"1033\",\n" +
                "        \"amount\": \"1\",\n" +
                "        \"unit\": \"cup\",\n" +
                "        \"unitLong\": \"cup\",\n" +
                "        \"unitShort\": \"cup\",\n" +
                "        \"aisle\": \"Cheese\",\n" +
                "        \"name\": \"parmesan cheese\",\n" +
                "        \"originalString\": \"1 cup Parmesan cheese, grated\",\n" +
                "        \"metaInformation\": [\n" +
                "          \"grated\"\n" +
                "        ],\n" +
                "        \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/parmesan-or-romano.jpg\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"usedIngredients\": [\n" +
                "      {\n" +
                "        \"id\": \"5006\",\n" +
                "        \"amount\": \"2\",\n" +
                "        \"unit\": \"\",\n" +
                "        \"unitLong\": \"\",\n" +
                "        \"unitShort\": \"\",\n" +
                "        \"aisle\": \"Meat\",\n" +
                "        \"name\": \"chicken\",\n" +
                "        \"originalString\": \"2 large chicken breasted (I think 1/2 chicken breast per person is enough for this meal, but you could up it if you wanted.)\",\n" +
                "        \"metaInformation\": [\n" +
                "          \"large\",\n" +
                "          \"for this meal, but you could up it if you wanted.)\",\n" +
                "          \"per person is enough \"\n" +
                "        ],\n" +
                "        \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/whole-chicken.jpg\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"unusedIngredients\": [\n" +
                "      {\n" +
                "        \"id\": \"1041009\",\n" +
                "        \"amount\": \"1\",\n" +
                "        \"unit\": \"serving\",\n" +
                "        \"unitLong\": \"serving\",\n" +
                "        \"unitShort\": \"serving\",\n" +
                "        \"aisle\": \"Cheese\",\n" +
                "        \"name\": \"cheese\",\n" +
                "        \"originalString\": \"cheese\",\n" +
                "        \"metaInformation\": [],\n" +
                "        \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/cheddar-cheese.jpg\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"likes\": \"341\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"544373\",\n" +
                "    \"title\": \"Chicken Fajita Quesadilla\",\n" +
                "    \"image\": \"https://spoonacular.com/recipeImages/chicken-fajita-quesadilla-544373.jpg\",\n" +
                "    \"imageType\": \"jpg\",\n" +
                "    \"usedIngredientCount\": \"2\",\n" +
                "    \"missedIngredientCount\": \"1\",\n" +
                "    \"missedIngredients\": [\n" +
                "      {\n" +
                "        \"id\": \"18364\",\n" +
                "        \"amount\": \"4\",\n" +
                "        \"unit\": \"\",\n" +
                "        \"unitLong\": \"\",\n" +
                "        \"unitShort\": \"\",\n" +
                "        \"aisle\": \"Bakery/Bread;Pasta and Rice;Ethnic Foods\",\n" +
                "        \"name\": \"tortillas\",\n" +
                "        \"originalString\": \"4 tortillas\",\n" +
                "        \"metaInformation\": [],\n" +
                "        \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/flour-tortillas.jpg\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"usedIngredients\": [\n" +
                "      {\n" +
                "        \"id\": \"1041009\",\n" +
                "        \"amount\": \"1\",\n" +
                "        \"unit\": \"cup\",\n" +
                "        \"unitLong\": \"cup\",\n" +
                "        \"unitShort\": \"cup\",\n" +
                "        \"aisle\": \"Cheese\",\n" +
                "        \"name\": \"cheese\",\n" +
                "        \"originalString\": \"1 cup cheese (mexican cheese blend, mozzarella or cheddar), grated\",\n" +
                "        \"metaInformation\": [\n" +
                "          \"grated\",\n" +
                "          \"(mexican cheese blend, mozzarella or cheddar)\"\n" +
                "        ],\n" +
                "        \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/cheddar-cheese.jpg\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"5006\",\n" +
                "        \"amount\": \"1\",\n" +
                "        \"unit\": \"cup\",\n" +
                "        \"unitLong\": \"cup\",\n" +
                "        \"unitShort\": \"cup\",\n" +
                "        \"aisle\": \"Meat\",\n" +
                "        \"name\": \"chicken\",\n" +
                "        \"originalString\": \"1 cup chicken fajitas, chopped (chicken \\u0026 veggies)\",\n" +
                "        \"metaInformation\": [\n" +
                "          \"chopped\",\n" +
                "          \"(chicken \\u0026 veggies)\"\n" +
                "        ],\n" +
                "        \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/whole-chicken.jpg\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"unusedIngredients\": [],\n" +
                "    \"likes\": \"0\"\n" +
                "  }\n" +
                "]";

        System.out.println(s);
    }

}
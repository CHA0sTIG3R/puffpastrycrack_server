# Puffpastrycrack server 
## Spring Boot RESTful API Implementation

* Created JPA Entity [Recipe](src/main/java/com/cha0stig3r/recipe/server/model/Recipe.java)
* Implemented the ListCRUDRepository for the [repository](src/main/java/com/cha0stig3r/recipe/server/repository/RecipeRepository.java) layer
* Created a [DTO](src/main/java/com/cha0stig3r/recipe/server/model/RecipeDto.java) to be the response when queried.
* Created the [Controller](src/main/java/com/cha0stig3r/recipe/server/controller/RecipeController.java)
* Created The [Service](src/main/java/com/cha0stig3r/recipe/server/service/RecipeService.java) that holds the logic and communicates with the [controller](src/main/java/com/cha0stig3r/recipe/server/controller/RecipeController.java)
* Created [RequestDto](src/main/java/com/cha0stig3r/recipe/server/model/RecipeDto.java) and [RequestUpdate](src/main/java/com/cha0stig3r/recipe/server/model/RequestUpdate.java) as the RequestBodies
* Added utility [Methods](src/main/java/com/cha0stig3r/recipe/server/utility/FormatRecipeUtility.java) that converts [Recipe](src/main/java/com/cha0stig3r/recipe/server/model/Recipe.java) to [DTO](src/main/java/com/cha0stig3r/recipe/server/model/RecipeDto.java) 


## Google Cloud Implementation
* Implemented Cloud SQL
* Implemented Cloud Storage for images
* Created [GCSUtil](src/main/java/com/cha0stig3r/recipe/server/utility/GCSUtil.java) utility to help create, read, update, and delete images
* Deployed to App Engine Standard Env.
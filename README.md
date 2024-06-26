# CookBook
This is a project for Advanced Software Engineering. 
It allows users to create and share recipes.

## Features

### Already implemented
* add new Recipe
* see all Recipes
* read Recipes
* categorize Recipes
* bookmark Recipes
* update existing Recipe
* delete existing Recipe
* list the Recipes of the active User
* rate Recipes of other Users
* read ratings of other Users
* switch between different Users
* add new Users

### Planned
* filter existing Recipes by Category or User
* weekly plan
  * add Recipe to weekly plan
  * calculate the ingredients that need to be bought
  * display weekly plan?
* create new Categories

## Ubiquitous Language
* Cookbook: a collection of Recipes
* Recipe: an entry in the Cookbook
* User: person interacting with the Cookbook
* Author: User who created a Recipe
* Category: brief description that helps with classifying and grouping Recipes
* Rating: combination of a number of stars between 1 and 5, a comment and a title with the purpose of rating a Recipe

## Descriptions
* Cookbook 
  * date of creation
  * active user
* Recipe
  * name
  * author
  * date of creation
  * ingredients
  * content
  * categories
  * cooking time
  * rating
* User
  * name
  * password
  * bookmarked recipes
* Rating
  * Recipe ID
  * stars
  * title
  * comment

## Views
* Views only print lines to the console
* Views do not change values
* Views understand and process user input

## Service List
* UserService: used for listing existing Users and creating new Users
* AuthenticationService: used for logging in and logging out of the Cookbook
* RecipeService: used for listing, updating and deleting existing Recipes and creating new Recipes
* RatingService: used for listing, updating and deleting existing Ratings and creating new Ratings

## DataHandler List
* UserDataHandler: manages the connection to the file that stores the Users (db/users.json)
* RecipeDataHandler: manages the connection to the file that stores the Recipes (db/recipes.json)
* RatingDataHandler: manages the connection to the file that stores the Ratings (db/ratings.json)
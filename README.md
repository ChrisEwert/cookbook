# CookBook
This is a project for Advanced Software Engineering.

## Features
* add new Recipe
* update existing Recipe
* delete existing Recipe
* categorize Recipe
* see all Recipes
* filter existing Recipes by Category or User
* switch between different Users
* add new Users
* delete existing Users
* rate Recipes of other Users

## Ubiquitous Language
* CookBook: a collection of recipes
* Recipe: an entry in the CookBook
* User: person interacting with the CookBook
* Author: User who created a recipe
* Category: Brief description that helps with classifying and grouping recipes

## Descriptions
* CookBook 
  * date of creation
  * recipe list
  * active user
* Recipe
  * name
  * author
  * date of creation
  * content
  * categories
  * cooking time
  * ratings
* User
  * name
  * date of creation
  * recipes

## Views
  * Views only print lines to the console
  * Views do not change values
  * Views can understand and process user input

## Services
  * UserService: manages users and handles the logic for adding new users

## DataHandlers
  * UserDataHandler: manages the connection to the file that stores the names of the users (db/users)
# Calories Calculator
## Team project - REST API development ##

### Pitch
Ever wonder how many calories you burn doing a specific activity, or wonder how the duration of that activity affects the number of calories burned? # CaloriesCalculator # lets you input only a few bits of information like weight, activity and duration and will return the number burned. 
If you want to know how long you need to exercise to burn X number of calories, Calories Calculator can help with that too.

-- optional 
Need that little bit of extra motivation to keep going in your work out? Not only will we let you know how many calories you burned given your entered information, we will let you know how many calories you could burn if you kept the activity up for just a little longer. 

### Problem Statement

As people are becoming more aware of the importance of daily workout and keeping theirweight under control, it is helpful to provide information regarding caloriesburned during various exercise activities. This data can be combined with caloriesconsumed with food to develop effective weight management programs. 

This Calculator will show the users how many calories they used up while exercising.
They will be able to select a type of exercise from a list to provide accurate estimate. 
The following formula will be used for the calculations:
Total Calories Burned = METS x weight (kg) x time (hours) 
Where METS is number of kcal / kg / hour and will be obtained for different physical activities from a DataBase table.  

-- optional 
Secondary calculation, plus 20 minutes modifier to show additional calories burned for a little more efford

### Project Goal:
Create REST API to calculate total calorie expenditure for different sports activities.
This API will enable third-party developers to build applications, which will consume results of this API.

### Technology/Techniques

 * Database - MySql
    * Store tables for activities and METS
 * API
    * Endpoint 1 - returns all Activities (from table)
    * Endpoint 2 - requires 4 parameters (weight in kg/lb, activity, duration in hours, and weight unit) - returns calories burned in double 0.0
    * Endpoint 3 - requires 4 parameters (weight in kg/lb, activity, calories to burn, and weight unit) - returns duration in double 0.0
 * Logging
    * Log4J
 * Unit Testing
    * Junit
 * DB CRUD
    * Hibernate
 * Return formats
    * JSON
    * text
    * HTML
    
### Design [DB Design](https://github.com/ocollins/CaloriesCalculator/tree/master/db)
 
### [Project Plan](https://github.com/ocollins/CaloriesCalculator/blob/master/ProjectPlan.md)

### [Progress Log](https://github.com/ocollins/CaloriesCalculator/blob/master/ProgressLog.md)

### [User Guide](https://github.com/ocollins/CaloriesCalculator/blob/master/UserGuide.md)

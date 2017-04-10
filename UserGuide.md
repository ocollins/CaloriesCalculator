# Calories Calculator API 
## User Specs
|  |  | 
|------|------|
|**API Endpoint**|http://52.14.26.13:8080/CaloriesCalculator?|
|**Primary Category**| Health/Sports|
|**Terms Of Service URL**|Open source|
|**Scope**|Single purpose API|
|**Device Specific**|No|
|**Architectural Style**|REST|
|**Supported Request Formats**|URI Query String/CRUD|
|**Supported Response Formats**|Plain Text/JSON/HTML|
|**Is This a Hypermedia API?**|No|
|**Restricted Access (Requires Provider Approval)**|No|
|**Developers**|Ben Nisler; Mike Young; Olena Collins|
|--------------------------------------------|
|**Response Status Codes**| |
| | |
|--------------------------------------------|
|**Error Handling**|Message is displayed to the user informing them of the various paths and path variables needed in order to consume the service.|
 

## Usage

### Lists
|  path |     |
| ------- | --------- |
| /activities | returns plain text (table formatted) list of activities and associated METs |
| /activities/list | returns JSON formatted list of activities and associated METs (intended for consumers population of dropdown menu) |

### Calories burned Calculations
| parameter | format | accepted unit | required? | description |
| ------- | --------- | ------ | --------- | ------------- |
| response | string | text, json, html | yes | indicates the desired format for the api response |
| activity id | int | 0-? | yes | ID from activities list |
| weight | double | 0-? | yes | weight of athelete in lbs or kgs |
| duration | double | 0-? | yes | duration as expressed in fraction of an hour e.g. 30 minutes = 0.5 |
| unit | string | lb or kg | yes | specifies the unit used for weight |

### Duration Calculations
| parameter | format | accepted unit | required? | description |
| ------- | --------- | ------ | --------- | ---------------- |
| response | string | text or json | yes | indicates the desired format for the duration calculator | 
| activity id | int | 0-? | yes | ID from activities list |
| weight | double | 0-? | yes | weight of athelete in lbs or kgs |
| calories | int | 0-? | yes | specifies the desired number of calories to burn for the selected activity |
| unit | string | lb or kg | yes | specifies the unit used for weight |


## Examples

### Example 1 - Calories Burned Request
Acivity 1 - Walking, performed by a 200 lb person for 2 hours, response request as JSON.
 * request: /activities/json/1/200/2.0/lb
 * response: {"Calculation 0:":{"Calories Burned:":227.27272727272725,"Duration:":1.0},"Calculation 1:":{"Calories Burned:":454.5454545454545,"Duration:":2.0},"Calculation 2:":{"Calories Burned:":530.2272727272727,"Duration:":2.333}}
 
### Example 2 - Duration Request
Activity 3 - Calisthenics performed by a 110 kg person looking to burn 300 calories, response as plain text.
 * /duration/text/3/110/300/kg
 * response: Duration: 0.68 hours


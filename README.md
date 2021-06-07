# Orange-Talents-Challenge


REST API that will need to control user vehicles.

Rules:

-User registration, with the following required fields: name, e-mail, CPF, and date of birth; e-mail and CPF must be unique.
-Vehicle registration, where the following are required: Brand, Vehicle Model, and Year. And the service must consume FIPE's API (https://deividfortuna.github.io/fipe/) to obtain the vehicle's value data based on the information entered.
-Endpoint that will return a user with a list of all his registered vehicles.

You should build 3 endpoints into this system, the user endpoint, the vehicle endpoint, and the vehicle list for a specific user.

In the endpoint that will list your vehicles, we must consider some settings to be displayed to the end user. We will create two new attributes in the car object, these being:

1.) This car's rotation day, based on the vehicle's last year number, considering the conditionals:
End 0-1
End 2-3: Tuesday
Final 4-5: Wednesday
Final 6-7: Thursday
Final 8-9: Friday

2.) We must also create an active rotation attribute, that compares the current system date with the previous conditionals and, when it is the active day of the rotation, returns true; otherwise, false.

Example A: today is Monday, the car is a Fiat Uno model from 2001, which means that its rotation will be on Mondays and the active rotation attribute will be TRUE.
Example B: today is Thursday, the car is a Hyundai car, model HB20 from the year 2021, which means that its rotation will be on Mondays and the active rotation attribute will be FALSE.

- If the entries are correct, the Status 201 must be reset. If there are data entry errors, the Status must be 400.
- If the search is correct, you must return Status 200. If there is an error in the search, return the appropriate status and a friendly error message."

# Wisercat-assignment

TODO:
•	Create a Spring Boot application.  -TEHTUD
•	User is displayed a list of pets that the user has entered (see the provided html). -TEHTUD
•	User can add pets (see the provided html). -TEHTUD
•	A pet has a name, an identification code, a type (CAT, DOG, etc) and a fur color. -TEHTUD
•	When a new pet is saved, validate all mandatory fields. -TEHTUD
•	Store all input data to database. -TEHTUD
•	Values of select lists have to be populated with data from database. -TEHTUD

Optional TODO:
•	User can edit pets. -TEHTUD
•	Log in with one of the created user accounts (registration does not have to be implemented).
•	Create 3 user accounts into the database (username and password).
•	User is not allowed to see other user’s pets.
•	User can sort pets by all columns in the table.
•	A pet has a country of origin. -TEHTUD
•	Validate form fields both inline and in the back-end. -TEHTUD

Technical requirements:
•	Application has to run with 1 click or command. 
If front- and back-end are in different projects, then 1 click/command per project.
•	Use Spring Boot.
•	Embed a H2 database into the application.
•	Use Liquibase as a database migration tool, execute SQL scripts on app startup.
•	Use Angular for front end.
•	Use Bootstrap for design and styling. Do not use multiple CSS libraries.
•	Use latest versions of all used technologies.
•	API has to be RESTful.

Requirements on täidetud.





EDIT:
Abiks oli tavaliselt stackoverflow, vaatasin ka bezkoder-I (bezkoder.com) näiteid. Kogu ülesannet lahendasin ära umbes 14 tunniga 28.12-29.12. 
Backend oli valmis kiiresti, suuremad probleemid tekkisid frontendiga, kuna enne olin ainult Reacti kasutanud. Aga sain hakkama. 
Kui oleks varem ülesandega alustanud, siis muidugi teeksin ka userite implementatsiooni valmis, kuid kahjuks läksin niigi tähtajast üle. Viimased 2 tundi olin võidelnud sellega, et frontendil pet edit vaatel selectorid ei tahtnud näidata defaultina looma atribuute valuesi (type, fur color, country). Lahendus oli väga lihtne, kuid kulutas mul palju aega ära. Unustasin lisada value propertyt html templatis.

